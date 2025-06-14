/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2020. 1. 17. 오후 2:55:32
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.servlet.method.annotation;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import open.commons.core.collection.FIFOMap;
import open.commons.core.function.TripleFunction;
import open.commons.spring.web.servlet.BadRequestException;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.servlet.NotFoundException;
import open.commons.spring.web.servlet.UnauthorizedAccessException;
import open.commons.spring.web.servlet.binder.ExceptionHttpStatusBinder;
import open.commons.spring.web.utils.WebUtils;

import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 
 * @since 2020. 1. 17.
 * @version 0.2.3
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.servlet.method.annotation.DefaultGlobalExceptionHandler";

    public static final TripleFunction<WebRequest, Exception, HttpStatus, FIFOMap<String, Object>> FN_CREATE_ENTITY_DEFAULT = WebUtils::createEntity;

    /** {@link RequestBody} 정보 생성 함수 */
    private final TripleFunction<WebRequest, Exception, HttpStatus, FIFOMap<String, Object>> FN_CREATE_ENTITY;

    /** {@link Throwable}과 {@link HttpStatus} 매핑 정보 */
    private ExceptionHttpStatusBinder exceptionHttpStatusBinder;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @since 2020. 1. 17.
     * @version 0.2.3
     */
    public DefaultGlobalExceptionHandler() {
        this(null, FN_CREATE_ENTITY_DEFAULT);
    }

    /***
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param exceptionHttpStatusBinder
     *            {@link Throwable}과 {@link HttpStatus} 매핑 정보
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public DefaultGlobalExceptionHandler(@Qualifier(ExceptionHttpStatusBinder.BEAN_QUALIFIER) ExceptionHttpStatusBinder exceptionHttpStatusBinder) {
        this(exceptionHttpStatusBinder, FN_CREATE_ENTITY_DEFAULT);
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 17.     박준홍         최초 작성
     * </pre>
     * 
     * @param exceptionHttpStatusBinder
     *            {@link Throwable}과 {@link HttpStatus} 매핑 정보
     * @param funcCreateEntity
     *            응답 객체 생성 함수.
     *
     * @since 2025. 4. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public DefaultGlobalExceptionHandler(ExceptionHttpStatusBinder exceptionHttpStatusBinder,
            TripleFunction<WebRequest, Exception, HttpStatus, FIFOMap<String, Object>> funcCreateEntity) {
        this.exceptionHttpStatusBinder = exceptionHttpStatusBinder;
        this.FN_CREATE_ENTITY = funcCreateEntity != null ? funcCreateEntity : FN_CREATE_ENTITY_DEFAULT;
    }

    /**
     * 상태에 맞는 메시지를 생성한 후 예외처리를 진행한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param status
     * @param ex
     * @param request
     * @return
     *
     * @since 2020. 1. 17.
     * @version 0.2.3
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected ResponseEntity<Object> createEntity(HttpStatus status, Exception ex, WebRequest request) {
        FIFOMap<String, Object> entity = this.FN_CREATE_ENTITY.apply(request, ex, status);
        return handleExceptionInternal(ex, entity, new HttpHeaders(), status, request);
    }

    /**
     * 4xx 로 처리되는 클래스를 정의 및 처리. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 17.		박준홍			최초 작성
     * 2020. 7. 30.     박준홍         {@link BadRequestException} 추가
     * 2022. 12. 01.    박준홍         {@link NotFoundException} 추가.
     * 2025. 5. 19.     박준홍         {@link UnauthorizedAccessException} 추가.
     * 2025. 5. 28      박준홍         {@link #resolveAnnotatedResponseStatus(Exception, HttpStatus)} 적용
     * 2025. 5. 28      박준홍         deprecated 처리됨.
     * </pre>
     *
     * @param ex
     * @param request
     * @return
     *
     * @since 2020. 1. 17.
     * @version 0.2.3
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated {@link #handleAllExceptions(Exception, WebRequest)} 일괄적으로 {@link Throwable}를 처리함. 다음 버전에서 삭제될 예정.
     */
    // @ExceptionHandler(value = { //
    // BadRequestException.class, //
    // ConstraintViolationException.class, //
    // NotFoundException.class, //
    // UnauthorizedAccessException.class //
    // })
    public ResponseEntity<Object> handle4xxException(Exception ex, WebRequest request) {

        HttpStatus status = resolveAnnotatedResponseStatus(ex, HttpStatus.BAD_REQUEST);
        FIFOMap<String, Object> entity = this.FN_CREATE_ENTITY.apply(request, ex, status);

        return handleExceptionInternal(ex, entity, new HttpHeaders(), status, request);
    }

    /**
     * 5xx 로 처리되는 클래스 정의 및 처리 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 17.		박준홍			최초 작성
     * 2020. 7. 30.     박준홍         {@link InternalServerException} 추가
     * 2025. 5. 28      박준홍         {@link UnsupportedOperationException} 처리 분리
     * 2025. 5. 28      박준홍         {@link #resolveAnnotatedResponseStatus(Exception, HttpStatus)} 적용
     * 2025. 5. 28      박준홍         deprecated 처리됨.
     * </pre>
     * 
     * @param ex
     * @param request
     * @return
     *
     * @since 2020. 1. 17.
     * @version 0.2.3
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated {@link #handleAllExceptions(Exception, WebRequest)} 일괄적으로 {@link Throwable}를 처리함. 다음 버전에서 삭제될 예정.
     */
    // @ExceptionHandler(value = { //
    // NullPointerException.class, //
    // IllegalArgumentException.class, //
    // IllegalStateException.class, //
    // InternalServerException.class, //
    // UnsupportedOperationException.class, //
    // RuntimeException.class, //
    // Exception.class, // eclipse-javadoc:%E2%98%82=open-commons-spring-web/src%5C/main%5C/java%3Copen
    // })
    public ResponseEntity<Object> handle5xxException(Exception ex, WebRequest request) {

        HttpStatus status = null;
        if (UnsupportedOperationException.class.equals(ex.getClass())) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
        } else {
            status = resolveAnnotatedResponseStatus(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        FIFOMap<String, Object> entity = this.FN_CREATE_ENTITY.apply(request, ex, status);

        return handleExceptionInternal(ex, entity, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({ JsonMappingException.class })
    public ResponseEntity<Object> handleJsonMappingException(JsonMappingException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        FIFOMap<String, Object> entity = this.FN_CREATE_ENTITY.apply(request, ex, status);
        return handleExceptionInternal(ex, entity, new HttpHeaders(), status, request);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param ex
     * @param request
     * @return
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #resolveAnnotatedResponseStatus
     * 
     */
    @ExceptionHandler(value = { Throwable.class })
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        HttpStatus status = resolveAnnotatedResponseStatus(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        if (status == null) {
            // start - 기존에 CaseByCase로 처리되던 예외클래스 지원. : 2025. 5. 28. 오후 4:46:04
            // 4xx:
            if (ConstraintViolationException.class.equals(ex.getClass())) {
                status = HttpStatus.BAD_REQUEST;
            }
            // 5xx
            else if (UnsupportedOperationException.class.equals(ex.getClass())) {
                status = HttpStatus.SERVICE_UNAVAILABLE;
            } //
        }
        // end - 기존에 CaseByCase로 처리되던 예외클래스 지원. : 2025. 5. 28. 오후 4:46:04

        FIFOMap<String, Object> entity = this.FN_CREATE_ENTITY.apply(request, ex, status);

        return handleExceptionInternal(ex, entity, new HttpHeaders(), status, request);
    }

    /**
     * 
     *
     * @since 2020. 1. 17.
     * @version 0.2.3
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleExceptionInternal(java.lang.Exception,
     *      java.lang.Object, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
     *      org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = this.FN_CREATE_ENTITY.apply(request, ex, status);
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * 사용자 정의 예외 클래스에 선언된 {@link HttpStatus} 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param ex
     *            예외클래스.
     * @param defaultStatus
     *            설정된 {@link HttpStatus} 정보가 없는 경우 제공될 기본 {@link HttpStatus}
     * @return
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see ResponseStatus
     */
    protected HttpStatus resolveAnnotatedResponseStatus(Exception ex, HttpStatus defaultStatus) {

        HttpStatus httpStatus = this.exceptionHttpStatusBinder != null ? this.exceptionHttpStatusBinder.resolveHttpStatus(ex.getClass()) : null;
        if (httpStatus != null) {
            return httpStatus;
        } else {
            ResponseStatus resStatus = AnnotatedElementUtils.findMergedAnnotation(ex.getClass(), ResponseStatus.class);
            return resStatus != null ? resStatus.code() : defaultStatus;
        }
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param exceptionHttpStatusBinders
     *            the exceptionHttpStatusBinder to set
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #exceptionHttpStatusBinder
     */
    @Qualifier(ExceptionHttpStatusBinder.BEAN_QUALIFIER)
    @Autowired
    public void setExceptionHttpStatusBinder(ExceptionHttpStatusBinder exceptionHttpStatusBinder) {
        this.exceptionHttpStatusBinder = exceptionHttpStatusBinder;
    }
}
