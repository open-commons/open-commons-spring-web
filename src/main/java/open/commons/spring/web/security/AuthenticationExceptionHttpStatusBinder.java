/*
 * Copyright 2025 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2025. 10. 22. 오후 5:33:49
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.security;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import open.commons.spring.web.servlet.binder.AbstractExceptionStatusWriter;
import open.commons.spring.web.servlet.binder.ExceptionHttpStatusBinder;

/**
 * 'Spring Security' 인증절차에서 발생하는 '인증 전 실패(AuthenticationException)' 에 대한 처리.
 * 
 * @since 2025. 10. 22.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class AuthenticationExceptionHttpStatusBinder extends AbstractExceptionStatusWriter implements AuthenticationEntryPoint {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 22.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param binder
     *            예외객체와 연결된 {@link HttpStatus}를 제공하는 객체.
     *
     * @since 2025. 10. 22.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public AuthenticationExceptionHttpStatusBinder(@Nonnull ExceptionHttpStatusBinder binder) {
        super(binder);
    }

    /**
     *
     * @since 2025. 10. 22.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        writeExceptionResponse(request, response, authException);
    }

    /**
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see open.commons.spring.web.servlet.binder.IExceptionResponseWriter#defaultHttpStatus()
     */
    @Override
    public HttpStatus defaultHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
