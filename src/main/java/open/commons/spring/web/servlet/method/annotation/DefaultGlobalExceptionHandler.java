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

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import open.commons.core.collection.FIFOMap;
import open.commons.core.function.TripleFunction;
import open.commons.spring.web.servlet.BadRequestException;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.servlet.NotFoundException;
import open.commons.spring.web.servlet.UnauthorizedException;
import open.commons.spring.web.utils.WebUtils;

/**
 * 
 * @since 2020. 1. 17.
 * @version 0.2.3
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final TripleFunction<WebRequest, Exception, HttpStatus, FIFOMap<String, Object>> FN_CREATE_ENTITY_DEFAULT = WebUtils::createEntity;

    private final TripleFunction<WebRequest, Exception, HttpStatus, FIFOMap<String, Object>> FN_CREATE_ENTITY;

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
        this(null);
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param funcCreateEntity
     *            응답 객체 생성 함수.
     *
     * @since 2025. 4. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public DefaultGlobalExceptionHandler(TripleFunction<WebRequest, Exception, HttpStatus, FIFOMap<String, Object>> funcCreateEntity) {
        FN_CREATE_ENTITY = funcCreateEntity != null ? funcCreateEntity : FN_CREATE_ENTITY_DEFAULT;
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
     * 2025. 5. 19.    박준홍         {@link UnauthorizedException} 추가.
     * </pre>
     *
     * @param ex
     * @param request
     * @return
     *
     * @since 2020. 1. 17.
     * @version 0.2.3
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @ExceptionHandler(value = { //
            BadRequestException.class, //
            ConstraintViolationException.class, //
            NotFoundException.class, //
            UnauthorizedException.class //
    })
    public ResponseEntity<Object> handle4xxException(Exception ex, WebRequest request) {

        HttpStatus status = null;

        Class<?> exClass = ex.getClass();
        if (BadRequestException.class.equals(exClass) //
                || ConstraintViolationException.class.equals(exClass) //
        ) {
            status = HttpStatus.BAD_REQUEST;
        } else if (NotFoundException.class.equals(exClass)) {
            status = HttpStatus.NOT_FOUND;
        } else if (UnauthorizedException.class.equals(exClass)) {
            status = HttpStatus.UNAUTHORIZED;
        }

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
     * </pre>
     *
     * @param ex
     * @param request
     * @return
     *
     * @since 2020. 1. 17.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @ExceptionHandler(value = { //
            NullPointerException.class, //
            IllegalArgumentException.class, //
            IllegalStateException.class, //
            InternalServerException.class, //
            UnsupportedOperationException.class, //
            RuntimeException.class, //
            Exception.class, // eclipse-javadoc:%E2%98%82=open-commons-spring-web/src%5C/main%5C/java%3Copen
    })
    public ResponseEntity<Object> handle5xxException(Exception ex, WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        FIFOMap<String, Object> entity = this.FN_CREATE_ENTITY.apply(request, ex, status);

        return handleExceptionInternal(ex, entity, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = this.FN_CREATE_ENTITY.apply(request, ex, status);
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
