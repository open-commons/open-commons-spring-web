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
 * Date  : 2025. 7. 2. 오후 2:48:20
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.rest.service;

import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.http.NoHttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import open.commons.core.Result;
import open.commons.core.text.NamedTemplate;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.rest.RestUtils2;
import open.commons.spring.web.servlet.InternalServerException;

/**
 * {@link RestTemplate}를 이용하여 외부 서비스와 연동하는 기능을 제공합니다.
 * 
 * @since 2025. 7. 2.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractRestApiClient {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected final RestTemplate restTemplate;

    protected final int retryCount;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 2.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 2.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractRestApiClient(@NotNull RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.retryCount = getRetryCount();
    }

    /**
     * 주어진 URI Path 값과 서버 정보를 이용하여 {@link URI} 객체를 제공합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected URI createURI(String path, @Nullable MultiValueMap<String, String> query, String fragment) {
        return createURI(getBaseUrl(), path, query, fragment);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query//
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {
        return execute(method, path, pathVariables, query, (String) null, createHttpEntity(requestBody, headers), responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , int retryCount) {
        return execute(method, path, pathVariables, query, (String) null, createHttpEntity(requestBody, headers), responseType, onSuccess, CallbackOn.error(), retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType //
            , int retryCount) {
        return execute(method, path, pathVariables, query, (String) null, createHttpEntity(requestBody, headers), responseType, CallbackOn.success(this.logger), CallbackOn.error(),
                retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {
        return execute(method, path, pathVariables, query, (String) null, createHttpEntity(requestBody, headers), responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , int retryCount) {
        return execute(method, path, pathVariables, query, (String) null, createHttpEntity(requestBody, headers), responseType, onSuccess, CallbackOn.error(), retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , int retryCount) {
        return execute(method, path, pathVariables, query, (String) null, createHttpEntity(requestBody, headers), responseType, CallbackOn.success(this.logger), CallbackOn.error(),
                retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등) TODO
     * @param entity
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query,
            String fragment //
            , @Nullable HttpEntity<REQ> entity //
            , @NotNull Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {

        if (pathVariables != null) {
            NamedTemplate tpl = new NamedTemplate(path);
            pathVariables.forEach((k, v) -> {
                tpl.addValue(k, v);
            });
            path = tpl.format();
        }

        URI uri = createURI(path, convertToMultiValueMap(query), fragment);

        return RestUtils2.exchange(restTemplate, method, uri, entity, responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param entity
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query,
            String fragment //
            , @Nullable HttpEntity<REQ> entity //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {

        if (pathVariables != null) {
            NamedTemplate tpl = new NamedTemplate(path);
            pathVariables.forEach((k, v) -> {
                tpl.addValue(k, v);
            });
            path = tpl.format();
        }

        URI uri = createURI(path, convertToMultiValueMap(query), fragment);

        return RestUtils2.exchange(restTemplate, method, uri, entity, responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query,
            String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {
        return execute(method, path, pathVariables, query, fragment, createHttpEntity(requestBody, headers), responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query,
            String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , int retryCount) {
        return execute(method, path, pathVariables, query, fragment, createHttpEntity(requestBody, headers), responseType, onSuccess, CallbackOn.error(), retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query,
            String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType//
            , int retryCount) {
        return execute(method, path, pathVariables, query, fragment, createHttpEntity(requestBody, headers), responseType, CallbackOn.success(this.logger), CallbackOn.error(),
                retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query,
            String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {
        return execute(method, path, pathVariables, query, fragment, createHttpEntity(requestBody, headers), responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query,
            String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , int retryCount) {
        return execute(method, path, pathVariables, query, fragment, createHttpEntity(requestBody, headers), responseType, onSuccess, CallbackOn.error(), retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 3.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param pathVariables
     *            <code>path</code>에 사용되는 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, Map<String, Object> pathVariables, @Nullable MultiValueMap<String, Object> query,
            String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , int retryCount) {
        return execute(method, path, pathVariables, query, fragment, createHttpEntity(requestBody, headers), responseType, CallbackOn.success(this.logger), CallbackOn.error(),
                retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query//
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {
        return execute(method, path, null, query, (String) null, createHttpEntity(requestBody, headers), responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , int retryCount) {
        return execute(method, path, null, query, (String) null, createHttpEntity(requestBody, headers), responseType, onSuccess, CallbackOn.error(), retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType //
            , int retryCount) {
        return execute(method, path, null, query, (String) null, createHttpEntity(requestBody, headers), responseType, CallbackOn.success(this.logger), CallbackOn.error(),
                retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {
        return execute(method, path, null, query, (String) null, createHttpEntity(requestBody, headers), responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , int retryCount) {
        return execute(method, path, null, query, (String) null, createHttpEntity(requestBody, headers), responseType, onSuccess, CallbackOn.error(), retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , int retryCount) {
        return execute(method, path, null, query, (String) null, createHttpEntity(requestBody, headers), responseType, CallbackOn.success(this.logger), CallbackOn.error(),
                retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등) TODO
     * @param entity
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query, String fragment //
            , @Nullable HttpEntity<REQ> entity //
            , @NotNull Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {
        URI uri = createURI(path, convertToMultiValueMap(query), fragment);
        return RestUtils2.exchange(restTemplate, method, uri, entity, responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param entity
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query, String fragment //
            , @Nullable HttpEntity<REQ> entity //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {
        URI uri = createURI(path, convertToMultiValueMap(query), fragment);
        return RestUtils2.exchange(restTemplate, method, uri, entity, responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query, String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {
        return execute(method, path, null, query, fragment, createHttpEntity(requestBody, headers), responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query, String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , int retryCount) {
        return execute(method, path, null, query, fragment, createHttpEntity(requestBody, headers), responseType, onSuccess, CallbackOn.error(), retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query, String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull Class<RES> responseType//
            , int retryCount) {
        return execute(method, path, null, query, fragment, createHttpEntity(requestBody, headers), responseType, CallbackOn.success(this.logger), CallbackOn.error(), retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query, String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess, @NotNull Function<Exception, Result<RET>> onError //
            , int retryCount) {
        return execute(method, path, null, query, fragment, createHttpEntity(requestBody, headers), responseType, onSuccess, onError, retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query, String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , @NotNull Function<ResponseEntity<RES>, Result<RET>> onSuccess //
            , int retryCount) {
        return execute(method, path, null, query, fragment, createHttpEntity(requestBody, headers), responseType, onSuccess, CallbackOn.error(), retryCount);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 유형
     * @param <RES>
     *            연동 서비스가 제공하는 데이터 유형
     * @param <RET>
     *            실제 제공하는 데이터 유형
     * @param method
     *            Http 요청 방식
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param retryCount
     *            오류 발생시 재시도 횟수
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected <REQ, RES, RET> Result<RET> execute(@NotNull HttpMethod method, String path, @Nullable MultiValueMap<String, Object> query, String fragment //
            , @Nullable HttpHeaders headers, @Nullable REQ requestBody //
            , @NotNull ParameterizedTypeReference<RES> responseType //
            , int retryCount) {
        return execute(method, path, null, query, fragment, createHttpEntity(requestBody, headers), responseType, CallbackOn.success(this.logger), CallbackOn.error(), retryCount);
    }

    /**
     * 연동하려는 대상과 접속하는 기본 {@link URL} 정보를 문자열로 제공합니다. <br>
     * <code>[scheme:][//[userinfo@]host[:port]</code> 유형을 값을 제공합니다.
     * <li>scheme: 필수 ( [http|https] )
     * <li>userinfo: 옵션
     * <li>host: 필수 (IP 또는 Domain)
     * <li>port: 옵션, 설정되지 않은 경우 <code>scheme</code>값이 'http' 인 경우 80, 'https' 인 경우 443으로 처리됩니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    @NotEmpty
    protected abstract String getBaseUrl();

    /**
     * {@link NoHttpResponseException} 또는 {@link ResourceAccessException} 예외상황이 발생한 경우 재시도 횟수를 반환합니다. <br>
     * {@link HttpClientErrorException} 또는 {@link HttpServerErrorException} 예외상황이 발생한 경우는 재시도를 하지 않습니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 1.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 7. 1.
     * @author 박준홍(jhpark@ymtech.co.kr)
     * 
     * @see NoHttpResponseException
     * @see ResourceAccessException
     * @see HttpClientErrorException
     * @see HttpServerErrorException
     * 
     */
    protected int getRetryCount() {
        return 3;
    }

    /**
     * {@link HttpHeaders}를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param data
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final HttpHeaders convertToHeaders(@Nullable MultiValueMap<String, Object> data) {
        return new HttpHeaders(convertToMultiValueMap(data));
    }

    /**
     * 2개의 데이터(i, i+1)로 <code>key=value</code> 형태의 관계를 갖는 데이터를 {@link MultiValueMap}로 변환하여 제공합니다. <br>
     * 일반적으로 'Query Parameters'를 생성하는데 유용합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param data
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final MultiValueMap<String, String> convertToMultiValueMap(@Nullable List<Object> data) {
        return toMultiValueMap(data.toArray(new Object[0]));
    }

    /**
     * <code>key</code>에 해당하는 값을 모두 {@link String} 으로 변환하여 반환합니다. <br>
     * 단, 값이 <code>null</code>인 경우 해당 값은 추가되지 않습니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param data
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final MultiValueMap<String, String> convertToMultiValueMap(@Nullable MultiValueMap<String, Object> data) {

        MultiValueMap<String, String> m = new LinkedMultiValueMap<>();

        if (data != null) {
            data.forEach((key, value) -> {
                if (value == null) {
                    return;
                }
                value.stream().filter(v -> v != null).forEach(v -> m.add(key, v.toString()));
            });
        }

        return m;
    }

    /**
     * Http 요청 데이터를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     * 
     * @param requestBody
     * @param headers
     * @param <REQ>
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final <REQ> HttpEntity<REQ> createHttpEntity(REQ requestBody, MultiValueMap<String, String> headers) {
        return new HttpEntity<REQ>(requestBody, headers);
    }

    /**
     * Http 요청 데이터를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <REQ>
     * @param requestBody
     * @param headers
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final <REQ> HttpEntity<REQ> createHttpEntity(REQ requestBody, String... headers) {
        return new HttpEntity<REQ>(requestBody, toMultiValueMap(headers));
    }

    /**
     * <code>[scheme:][//[userinfo@]host[:port]][/path][?query][#fragment]</code> 구조를 준수하는 {@link URI} 객체를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param scheme
     *            연동 대상 접속 scheme
     * @param host
     *            연동 대상 접속 host
     * @param port
     *            연동 대상 접속 port
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final URI createURI(@NotEmpty String scheme, @NotEmpty String host, @Min(1) int port, String path, @Nullable MultiValueMap<String, String> query,
            String fragment) {
        return createURI(StringUtils.concatenate("", scheme, "://", host, ":", port), path, query, fragment);
    }

    /**
     * <code>[scheme:][//[userinfo@]host[:port]][/path][?query][#fragment]</code> 구조를 준수하는 {@link URI} 객체를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param baseHttpUrl
     *            연동 대상 접속 URL
     * @param path
     *            서버 상의 자원의 경로. 일반적으로 연동하는 REST API URL 정보
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final URI createURI(@NotEmpty String baseHttpUrl, String path, @Nullable MultiValueMap<String, String> query, String fragment) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseHttpUrl).path(path);
        ifNotNull(query, builder::queryParams);
        ifNotNull(fragment, builder::fragment);

        return builder.build().toUri();
    }

    /**
     * <code>param</code>이 <code>null</code>이 아닌 경우 <code>action</code>을 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <P>
     * @param param
     * @param action
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static <P> void ifNotNull(P param, Consumer<P> action) {
        if (param != null) {
            action.accept(param);
        }
    }

    /**
     * <code>param</code>이 <code>null</code>이 아닌 경우 <code>action</code>을 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param <P>
     * @param <R>
     * @param param
     * @param action
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static <P, R> void ifNotNull(P param, Function<P, R> action) {
        if (param != null) {
            action.apply(param);
        }
    }

    /**
     * 2개의 데이터(i, i+1)로 <code>key=value</code> 형태의 관계를 갖는 데이터를 {@link HttpHeaders}로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param headerValues
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final HttpHeaders toHeaders(@NotNull List<String> headerValues) {
        return toHeaders(headerValues.toArray(new String[0]));
    }

    /**
     * {@link HttpHeaders}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param data
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final HttpHeaders toHeaders(@NotNull MultiValueMap<String, String> data) {
        return new HttpHeaders(data);
    }

    /**
     * 2개의 데이터(i, i+1)로 <code>key=value</code> 형태의 관계를 갖는 데이터를 {@link HttpHeaders}로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param headerValues
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final HttpHeaders toHeaders(@NotNull String... headerValues) {
        return new HttpHeaders(toMultiValueMap(headerValues));
    }

    /**
     * 2개의 데이터(i, i+1)로 <code>key=value</code> 형태의 관계를 갖는 데이터를 {@link MultiValueMap}로 변환하여 제공합니다. <br>
     * 일반적으로 'Query Parameters'를 생성하는데 유용합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param data
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final MultiValueMap<String, String> toMultiValueMap(Object... data) {
        if (data == null) {
            return new LinkedMultiValueMap<>();
        }
        if (data.length != 2) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "올바르지 않은 파라미터 입니다. data=%s", Arrays.toString(data));
        }
        return toMultiValueMap(Stream.of(data).map(d -> d != null ? d.toString() : (String) null).toArray(String[]::new));
    }

    /**
     * 2개의 데이터(i, i+1)로 <code>key=value</code> 형태의 관계를 갖는 데이터를 {@link MultiValueMap}로 변환하여 제공합니다. <br>
     * 일반적으로 'Query Parameters'를 생성하는데 유용합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜       | 작성자                           |  내용
     * ------------------------------------------------------------------------
     * 2025. 7. 2.      박준홍(jhpark@ymtech.co.kr)            최초 작성
     * </pre>
     *
     * @param data
     * @return
     *
     * @since 2025. 7. 2.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    protected static final MultiValueMap<String, String> toMultiValueMap(String... data) {
        if (data == null) {
            return new LinkedMultiValueMap<>();
        }
        if (data.length % 2 != 0) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "올바르지 않은 파라미터 입니다. data=%s", Arrays.toString(data));
        }

        LinkedMultiValueMap<String, String> m = new LinkedMultiValueMap<>();
        for (int i = 0; i < data.length; i += 2) {
            if (data[i] == null) {
                continue;
            }
            m.add(data[i], data[i + 1]);
        }

        return m;
    }

    public static class CallbackOn {
        private CallbackOn() {
        }

        public static <RET> Function<Exception, Result<RET>> error() {
            return e -> {
                return Result.error(e.getMessage());
            };
        }

        @SuppressWarnings("unchecked")
        public static <RES, RET> Function<ResponseEntity<RES>, Result<RET>> success(Logger logger) {
            return resEntity -> {
                try {
                    RES res = resEntity.getBody();
                    return Result.success((RET) res);
                } catch (Exception e) {
                    String errMsg = String.format("연동 데이터 변환 도중 오류가 발생하였습니다. 원인=%s", e.getMessage());
                    if (logger != null) {
                        logger.error(errMsg, e);
                    }
                    throw new InternalServerException(errMsg, e);
                }
            };
        }

        public static <RET> Function<Exception, Result<RET>> throwError() {
            return e -> {
                throw new InternalServerException(e);
            };
        }
    }

}
