/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 06. 11. 오전 12:04:31
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.http.NoHttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import open.commons.core.Result;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.ThreadUtils;

/**
 * {@link RestTemplate}을 이용하는 유틸리티 클래스2.
 * 
 * @since 2021. 06. 11.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @deprecated 2025. 8. 27. {@link RestFacade2} 를 사용하세요. 모든 메소드는 동일합니다.<br>
 *             <font color="red">다음 배포시 삭제 예정</font>
 */
public class RestUtils2 {

    private static final int DEFAULT_RETRY_COUNT = 5;

    private static final Logger logger = LoggerFactory.getLogger(RestUtils2.class);

    private RestUtils2() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 03. 06.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 03. 06.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 03. 06.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount //
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError, retryCount);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 03. 06.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
            , int retryCount //
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError, retryCount);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull URI uri //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, uri, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 03. 06.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull URI uri //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
            , int retryCount //
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, uri, entity, responseType, onSuccess, retryCount);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 06. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull URI uri //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, uri, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 03. 06.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2023. 03. 06.
     * @version 0.5.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull URI uri //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
            , int retryCount //
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, uri, entity, responseType, onSuccess, retryCount);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.        parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity, Class, Function, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
    ) throws URISyntaxException {
        return exchangeAsRaw(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.        parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity, Class, Function, Result, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount) throws URISyntaxException {
        return exchangeAsRaw(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.        parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity, ParameterizedTypeReference, Function, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
    ) throws URISyntaxException {
        return exchangeAsRaw(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.        parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity, ParameterizedTypeReference, Function, Function, int)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount) throws URISyntaxException {
        return exchangeAsRaw(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.        parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity, Class, Function, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
    ) throws URISyntaxException {
        try {
            return exchangeAsRaw(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            throw e;
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.        parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity, Class, Function, Function, int)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount) throws URISyntaxException {
        try {
            return exchangeAsRaw(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, retryCount);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            throw e;
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.    parkjunhong77@gmail.com     최초 작성
     * 2025. 7. 15.     parkjunhong77@gmail.com    {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity, ParameterizedTypeReference, Function, Function)}  메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
    ) throws URISyntaxException {
        try {
            return exchangeAsRaw(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            throw e;
        }
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.        parkjunhong77@gmail.com     최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity, ParameterizedTypeReference, Function, Function, int)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws URISyntaxException
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotEmpty String scheme, @NotEmpty String host, int port, String path, String query //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount) throws URISyntaxException {
        try {
            return exchangeAsRaw(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            throw e;
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.        parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, URI, HttpEntity, Class, Function, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull URI uri //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchangeAsRaw(sup, method, uri, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.        parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, URI, HttpEntity, Class, Function, Function, int)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull URI uri //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount //
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchangeAsRaw(sup, method, uri, entity, responseType, onSuccess, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 06. 11.        parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, URI, HttpEntity, ParameterizedTypeReference, Function, Function)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @return
     *
     * @since 2021. 06. 11.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull URI uri //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchangeAsRaw(sup, method, uri, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 03. 06.        parkjunhong77@gmail.com         최초 작성
     * 2025. 7. 14.         parkjunhong77@gmail.com     {@link RestUtils2#exchange(RestTemplate, HttpMethod, URI, HttpEntity, ParameterizedTypeReference, Function, Function, int)} 메소드의 반환데이터에서 {@link Result}를 제거함.
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 7. 14.
     * @version 0.8.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull URI uri //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount //
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchangeAsRaw(sup, method, uri, entity, responseType, onSuccess, retryCount);
    }

    private static <REQ, RES, RET> RET exchangeAsRaw(@NotNull Supplier<ResponseEntity<RES>> sup //
            , @NotNull HttpMethod method, Object url //
            , Object entity, Object responseType //
            , Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount //
    ) {
        final int RETRY_MAX_COUNT = retryCount;
        int retrial = 0;

        Exception unhandled = null;
        while (retrial < RETRY_MAX_COUNT) {
            try {
                ResponseEntity<RES> response = sup.get();

                HttpStatus statusCode = response.getStatusCode();

                // redirection
                if (statusCode.is3xxRedirection()) {
                    logger.info("URL is redirectioned. status={}, information={}", statusCode, response.getBody());
                } else
                // success
                if (statusCode.is2xxSuccessful()) {
                    logger.debug("Success to send information. target={}", url.toString());
                } else
                // informational...
                if (statusCode.is1xxInformational()) {
                    logger.debug("Information. status={}, information={}", statusCode, response.getBody());
                }

                return onSuccess.apply(response);
            } catch (HttpClientErrorException | HttpServerErrorException e) {

                logger.warn("'Request' -> method={}, uri={}, req.entity={}, res.type={}", method, url, entity, responseType);

                HttpStatus statusCode = e.getStatusCode();
                String occurs = null;
                // request error
                if (statusCode.is4xxClientError()) {
                    occurs = "Request Client Error.";
                } else
                // remote server internal error
                if (statusCode.is5xxServerError()) {
                    occurs = "Remote Server Error.";
                }

                logger.warn("'{}' -> res.status={}, res.status.raw={}, res.status.text={}, res.body={}", occurs, statusCode, e.getRawStatusCode(), e.getStatusText(),
                        e.getResponseBodyAsString());

                throw e;
            } catch (Exception e) {
                unhandled = e;
                logger.warn("{} Occured {}", "* * * * * ", e.getClass().getName());
                if (NoHttpResponseException.class.isAssignableFrom(e.getClass()) //
                        || ResourceAccessException.class.isAssignableFrom(e.getClass()) //
                ) {
                    retrial++;
                    logger.warn("{} Retry {} by {}", "* * * * * ", retrial, e.getClass().getName());
                    logger.warn("{} Request -> method={}, uri={}, req.entity={}, res.type={}", "* * * * * ", method, url, entity, responseType);
                    ThreadUtils.sleep(1000);
                } else {
                    throw ExceptionUtils.newException(RuntimeException.class, e, "예상하지 못한 에러가 발생하였습니다. 원인=%s, parent=%s", e.getMessage(), e);
                }
            }
        }

        if (unhandled != null) {
            if (RuntimeException.class.isAssignableFrom(unhandled.getClass())) {
                throw (RuntimeException) unhandled;
            } else {
                throw ExceptionUtils.newException(RuntimeException.class, unhandled, "서비스연동에 실패했습니다. 원인=%s, parent=%s", unhandled.getMessage(), unhandled);
            }
        } else {
            throw ExceptionUtils.newException(UnsupportedOperationException.class, "예상하지 못한 에러가 발생하였습니다.");
        }
    }

    /**
     * Template 형태의 <code>Full Qualified URL</code>를 기반으로 REST API 연동을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull String httpUrl, Map<String, ?> uriVariables //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, httpUrl, uriVariables, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * Template 형태의 <code>Full Qualified URL</code>를 기반으로 REST API 연동을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull String httpUrl, Map<String, ?> uriVariables //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
            , int retryCount //
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, httpUrl, uriVariables, entity, responseType, onSuccess, retryCount);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * Template 형태의 <code>Full Qualified URL</code>를 기반으로 REST API 연동을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull String httpUrl, Map<String, ?> uriVariables //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, httpUrl, uriVariables, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * Template 형태의 <code>Full Qualified URL</code>를 기반으로 REST API 연동을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <REQ, RES, RET> Result<RET> exchange(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull String httpUrl, Map<String, ?> uriVariables //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError//
            , int retryCount //
    ) {
        try {
            return exchangeAsRaw(restTemplate, method, httpUrl, uriVariables, entity, responseType, onSuccess, retryCount);
        } catch (Exception e) {
            return onError.apply(e);
        }
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull String httpUrl, Map<String, ?> uriVariables //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(httpUrl, method, entity, responseType, uriVariables);
        return exchangeAsRaw(sup, method, httpUrl, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull String httpUrl, Map<String, ?> uriVariables //
            , HttpEntity<REQ> entity //
            , Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount //
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(httpUrl, method, entity, responseType, uriVariables);
        return exchangeAsRaw(sup, method, httpUrl, entity, responseType, onSuccess, retryCount);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull String httpUrl, Map<String, ?> uriVariables //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(httpUrl, method, entity, responseType, uriVariables);
        return exchangeAsRaw(sup, method, httpUrl, entity, responseType, onSuccess, DEFAULT_RETRY_COUNT);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 26.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param <RET>
     *            메소드가 제공하는 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param httpUrl
     *            Fully Qualified URL 패턴을 만족하는 정보
     * @param uriVariables
     *            URL 을 구성하는 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param retryCount
     *            재시도 횟수
     * @return
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <REQ, RES, RET> RET exchangeAsRaw(@NotNull RestTemplate restTemplate //
            , @NotNull HttpMethod method, @NotNull String httpUrl, Map<String, ?> uriVariables //
            , HttpEntity<REQ> entity //
            , ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, RET> onSuccess //
            , int retryCount //
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(httpUrl, method, entity, responseType, uriVariables);
        return exchangeAsRaw(sup, method, httpUrl, entity, responseType, onSuccess, retryCount);
    }
}
