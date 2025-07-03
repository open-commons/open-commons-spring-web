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
 * Date  : 2025. 7. 3. 오전 10:15:30
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.rest;

import java.util.List;
import java.util.function.Function;

import javax.validation.constraints.NotEmpty;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import open.commons.core.Result;
import open.commons.spring.web.rest.service.AbstractRestApiClient.CallbackOn;

import io.micrometer.core.lang.Nullable;

/**
 * 
 * @since 2025. 7. 3.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IIdBasedRestApiService {

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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, Class<RES> responseType) {
        return execute(id, null, responseType, null, null, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, null, null, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, Function<ResponseEntity<RES>, Result<RET>> onSuccess,
            Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, null, null, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers) {
        return execute(id, null, responseType, headers, null, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, headers, null, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, Function<ResponseEntity<RES>, Result<RET>> onSuccess,
            Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, headers, null, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable MultiValueMap<String, Object> query) {
        return execute(id, null, responseType, headers, query, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable MultiValueMap<String, Object> query,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, headers, query, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable MultiValueMap<String, Object> query,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, headers, query, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable MultiValueMap<String, Object> query,
            @Nullable String fragment) {
        return execute(id, null, responseType, headers, query, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable MultiValueMap<String, Object> query,
            @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, headers, query, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable MultiValueMap<String, Object> query,
            @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, headers, query, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable String fragment) {
        return execute(id, null, responseType, headers, null, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, headers, null, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, headers, null, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query) {
        return execute(id, null, responseType, null, query, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, null, query, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, null, query, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query, @Nullable String fragment) {
        return execute(id, null, responseType, null, query, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, null, query, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, null, query, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, Class<RES> responseType, @Nullable String fragment) {
        return execute(id, null, responseType, null, null, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, null, null, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, Class<RES> responseType, @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess,
            Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, null, null, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType) {
        return execute(id, null, responseType, null, null, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, null, null, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, Function<ResponseEntity<RES>, Result<RET>> onSuccess,
            Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, null, null, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers) {
        return execute(id, null, responseType, headers, null, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, headers, null, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, headers, null, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query) {
        return execute(id, null, responseType, headers, query, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, headers, query, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, headers, query, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment) {
        return execute(id, null, responseType, headers, query, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, headers, query, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess,
            Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, headers, query, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers, @Nullable String fragment) {
        return execute(id, null, responseType, headers, null, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, headers, null, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, headers, null, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable MultiValueMap<String, Object> query) {
        return execute(id, null, responseType, null, query, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, null, query, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, null, query, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            @Nullable String fragment) {
        return execute(id, null, responseType, null, query, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, null, query, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, null, query, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable String fragment) {
        return execute(id, null, responseType, null, null, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, null, responseType, null, null, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 ({@link List}) 형태일 경우 사용<br>
     * 
     *            <pre>
     *            ParameterizedTypeReference&lt;List&lt;UserInfo&gt;&gt; restype = new ParameterizedTypeReference&lt;&gt;() {
     *            };
     *            </pre>
     * 
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, ParameterizedTypeReference<RES> responseType, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, null, responseType, null, null, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType) {
        return execute(id, requestBody, responseType, null, null, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, null, null, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, Function<ResponseEntity<RES>, Result<RET>> onSuccess,
            Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, null, null, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers) {
        return execute(id, requestBody, responseType, headers, null, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, headers, null, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, headers, null, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query) {
        return execute(id, requestBody, responseType, headers, query, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, headers, query, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, headers, query, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment) {
        return execute(id, requestBody, responseType, headers, query, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, headers, query, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param <RET>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    public <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess,
            Function<Exception, Result<RET>> onError);

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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable String fragment) {
        return execute(id, requestBody, responseType, headers, null, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, headers, null, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable HttpHeaders headers, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, headers, null, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query) {
        return execute(id, requestBody, responseType, null, query, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, null, query, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, null, query, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            @Nullable String fragment) {
        return execute(id, requestBody, responseType, null, query, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, null, query, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable MultiValueMap<String, Object> query,
            @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, null, query, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable String fragment) {
        return execute(id, requestBody, responseType, null, null, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, null, null, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
     * @param requestBody
     *            요청 데이터. <br>
     *            <code>method</code>가 {@link HttpMethod#GET}, {@link HttpMethod#DELETE} 등과 같이 없는 경우 <code>null</code>
     * @param responseType
     *            연동 서비스가 제공하는 데이터 유형<br>
     *            제공하는 데이터가 단일 데이터일 경우 사용
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, Class<RES> responseType, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, null, null, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType) {
        return execute(id, requestBody, responseType, null, null, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, null, null, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, null, null, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers) {
        return execute(id, requestBody, responseType, headers, null, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, headers, null, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, headers, null, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query) {
        return execute(id, requestBody, responseType, headers, query, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, headers, query, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, headers, query, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment) {
        return execute(id, requestBody, responseType, headers, query, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, headers, query, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param <RET>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    public <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess,
            Function<Exception, Result<RET>> onError);

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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable String fragment) {
        return execute(id, requestBody, responseType, headers, null, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, headers, null, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param headers
     *            요청 헤더 정보.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable HttpHeaders headers,
            @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, headers, null, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType,
            @Nullable MultiValueMap<String, Object> query) {
        return execute(id, requestBody, responseType, null, query, null, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType,
            @Nullable MultiValueMap<String, Object> query, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, null, query, null, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType,
            @Nullable MultiValueMap<String, Object> query, Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, null, query, null, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment) {
        return execute(id, requestBody, responseType, null, query, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, null, query, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param query
     *            <code>?</code> 뒤에 위치하며, key=value 형식의 파라미터.
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param headers
     *            요청 헤더 정보.
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType,
            @Nullable MultiValueMap<String, Object> query, @Nullable String fragment, Function<ResponseEntity<RES>, Result<RET>> onSuccess,
            Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, null, query, fragment, onSuccess, onError);
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES> Result<RES> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable String fragment) {
        return execute(id, requestBody, responseType, null, null, fragment, CallbackOn.success(null), CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess) {
        return execute(id, requestBody, responseType, null, null, fragment, onSuccess, CallbackOn.error());
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
     * @param <RES>
     * @param id
     *            연동 API 식별정보
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
     * @param fragment
     *            <code>#</code> 뒤에 위치하며, 문서 내의 특정 위치를 지정 (HTML 문서의 anchor 등)
     * @param onSuccess
     *            &lt;RES&gt; 데이터를 Result&lt;RET&gt; 데이터를 변환하는 함수
     * @param onError
     *            오류가 발생했을 경우 처리하는 함수.
     * @return
     *
     * @since 2025. 7. 3.
     * @author 박준홍(jhpark@ymtech.co.kr)
     */
    default <REQ, RES, RET> Result<RET> execute(@NotEmpty String id, @Nullable REQ requestBody, ParameterizedTypeReference<RES> responseType, @Nullable String fragment,
            Function<ResponseEntity<RES>, Result<RET>> onSuccess, Function<Exception, Result<RET>> onError) {
        return execute(id, requestBody, responseType, null, null, fragment, onSuccess, onError);
    }
}
