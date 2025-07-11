/*
 * Copyright 2025 Park Jun-Hong_(parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 *
 * This file is generated under this project, "open-commons-spring-web".
 *
 * Date  : 2025. 7. 3. 오후 4:47:43
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.rest;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import open.commons.core.Result;
import open.commons.spring.web.exception.RequiredQueryNotFoundException;
import open.commons.spring.web.rest.service.AbstractRestApiClient;

/**
 * 
 * @since 2025. 7. 3.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractIdBasedRestApiService extends AbstractRestApiClient implements IIdBasedRestApiService {

    /** Backend REST API 정보 */
    private final Map<String, IdBasedRestApiDecl> apiInfo = new ConcurrentSkipListMap<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param restTemplate
     * @param restApis
     *            연동할 백엔드 REST API 정보
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractIdBasedRestApiService(@NotNull RestTemplate restTemplate, @NotNull List<IdBasedRestApiDecl> restApis) {
        super(restTemplate);
        this.apiInfo.putAll(restApis.stream().collect(Collectors.toMap(api -> api.getId(), api -> api)));
    }

    private RestEndpoint createRestEndpoint(String id, MultiValueMap<String, String> headers, MultiValueMap<String, Object> queries) {
        IdBasedRestApiDecl api = this.apiInfo.get(id);
        if (api == null) {
            logger.warn("'{}'에 해당하는 REST API 정보가 없습니다.", id);
            return null;
        }

        HttpMethod method = api.getMethod();
        String path = api.getPath();
        // #1. 헤더 병합
        final HttpHeaders staticHeaders = new HttpHeaders(api.getHeaders());
        if (headers != null) {
            staticHeaders.addAll(headers);
        }
        // #2. query 파라미터
        MultiValueMap<String, Object> finalQueries = new LinkedMultiValueMap<>();
        // #2-1. 전달받은 쿼리 파라미터가 있는 경우
        Map<String, Boolean> queryParams = api.getQueries();
        if (queries != null) {
            List<Object> params = null;
            for (Entry<String, Boolean> entry : queryParams.entrySet()) {
                params = queries.get(entry.getKey());
                if (params != null) {
                    finalQueries.add(entry.getKey(), params);
                }
                // 전달받은 파라미터는 없지만, 해당 쿼리가 '필수'인 경우
                else if (entry.getValue()) {
                    throw new RequiredQueryNotFoundException(String.format("필수 Query Parameters ('%s')를 찾을 수 없습니다.", entry.getKey()));
                }
            }
        } else {
            // 2-2. REST API에 필수 쿼리파라미터가 있는지 확인
            Set<String> requiredQueryNames = queryParams.entrySet().stream().filter(p -> p.getValue()).map(p -> p.getKey()).collect(Collectors.toSet());
            if (requiredQueryNames.size() > 0) {
                throw new RequiredQueryNotFoundException(String.format("필수 Query Parameters를 찾을 수 없습니다. query-names=%s", String.join(", ", requiredQueryNames)));
            }
        }

        return new RestEndpoint(method, path, staticHeaders, finalQueries);
    }

    /**
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.rest.IIdBasedRestApiService#execute(java.lang.String, java.lang.Object,
     *      java.lang.Class, org.springframework.http.HttpHeaders, org.springframework.util.MultiValueMap,
     *      java.lang.String, java.util.function.Function, java.util.function.Function)
     */
    @Override
    public <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, @NotNull Class<RES> responseType //
            , @Nullable HttpHeaders headers //
            , @Nullable MultiValueMap<String, Object> query, @Nullable String fragment //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError) {

        RestEndpoint api = createRestEndpoint(id, headers, query);

        if (api == null) {
            String errMsg = String.format("REST API('%s') 연동을 실패하였습니다. 원인=REST API가 존재하지 않습니다.", id);
            return Result.error(errMsg);
        }

        return execute(api.method, api.path, api.queries, api.headers, requestBody, responseType, onSuccess, onError, getRetryCount());
    }

    /**
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.rest.IIdBasedRestApiService#execute(java.lang.String, java.lang.Object,
     *      org.springframework.core.ParameterizedTypeReference, org.springframework.http.HttpHeaders,
     *      org.springframework.util.MultiValueMap, java.lang.String, java.util.function.Function,
     *      java.util.function.Function)
     */
    @Override
    public <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, @NotNull ParameterizedTypeReference<RES> responseType //
            , @Nullable HttpHeaders headers //
            , @Nullable MultiValueMap<String, Object> query, @Nullable String fragment //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError) {

        RestEndpoint api = createRestEndpoint(id, headers, query);

        if (api == null) {
            String errMsg = String.format("REST API('%s') 연동을 실패하였습니다. 원인=REST API가 존재하지 않습니다.", id);
            return Result.error(errMsg);
        }

        return execute(api.method, api.path, api.queries, api.headers, requestBody, responseType, onSuccess, onError, getRetryCount());
    }

    private class RestEndpoint {
        private final HttpMethod method;
        private final String path;
        private final HttpHeaders headers;
        private final MultiValueMap<String, Object> queries;

        public RestEndpoint(HttpMethod method, String path, HttpHeaders headers, MultiValueMap<String, Object> queries) {
            this.method = method;
            this.path = path;
            this.headers = headers;
            this.queries = queries;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("RestEndpoint [method=");
            builder.append(method);
            builder.append(", path=");
            builder.append(path);
            builder.append(", headers=");
            builder.append(headers);
            builder.append(", queries=");
            builder.append(queries);
            builder.append("]");
            return builder.toString();
        }

    }

}
