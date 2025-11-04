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
 * Date  : 2025. 10. 30. 오후 2:55:27
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.servlet.binder;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import open.commons.spring.web.utils.WebUtils;

/**
 * 예외 클래스를 응답 데이터로 제공하는 기능을 정의.
 * 
 * @since 2025. 10. 30.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public interface IExceptionResponseWriter {

    /**
     * 예외상황에 연결된 {@link HttpStatus} 를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param ex
     *            발생한 예외 상황
     *
     * @return
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    default HttpStatus bind(@Nonnull Exception ex) {
        return getBinder().resolveHttpStatus(ex.getClass(), defaultHttpStatus());
    }

    /**
     * 요청정보({@link HttpServletRequest})와 오류 정보({@link Exception})를 이용하여 응답 데이터를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param req
     *            요청 정보
     * @param ex
     *            예외 정보
     * @return
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    default Object createResponseEntity(HttpServletRequest req, Exception ex) {
        return WebUtils.createEntity(req, ex, bind(ex));
    }

    /**
     * {@link Exception}에 연결된 {@link HttpStatus}가 없는 경우 사용할 {@link HttpStatus}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    default HttpStatus defaultHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * {@link Exception}과 {@link HttpStatus} 관계를 제공하는 객체를 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    ExceptionHttpStatusBinder getBinder();

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param o
     * @return
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    String writeAsString(Object o);

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     *
     * @since 2025. 10. 30.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    default void writeExceptionResponse(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException, ServletException {
        HttpStatus status = bind(exception);
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        Object entity = createResponseEntity(request, exception);
        response.getWriter().write(writeAsString(entity));
    }

}
