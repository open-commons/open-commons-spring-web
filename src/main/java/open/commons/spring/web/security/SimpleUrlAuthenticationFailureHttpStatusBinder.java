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
 * Date  : 2025. 10. 24. 오후 3:51:54
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import open.commons.spring.web.servlet.binder.ExceptionHttpStatusBinder;

/**
 * 'Spring Security' 인증절차에서 발생하는 예외상황을 'url'로 처리하는 기능을 제공.
 * 
 * @since 2025. 10. 24.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class SimpleUrlAuthenticationFailureHttpStatusBinder extends SimpleUrlAuthenticationFailureHandler {

    /** 예외상황과 {@link HttpStatus}를 연결하는 객체 */
    private final ExceptionHttpStatusBinder binder;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param defaultFailureUrl
     * @param binder
     *            예외상황과 {@link HttpStatus}를 연결하는 객체
     * 
     * @since 2025. 10. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public SimpleUrlAuthenticationFailureHttpStatusBinder(String defaultFailureUrl, ExceptionHttpStatusBinder binder) {
        super(defaultFailureUrl);
        this.binder = binder;
        setUseForward(true);
    }

    private HttpStatus getStatus(AuthenticationException exception) {
        return this.binder.resolveHttpStatus(exception.getClass(), HttpStatus.UNAUTHORIZED);
    }

    /**
     *
     * @since 2025. 10. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // AuthenticationException 클래스에 연결된 HttpStatus 설정
        HttpStatus status = getStatus(exception);
        response.setStatus(status.value());
        super.onAuthenticationFailure(request, response, exception);
    }
}
