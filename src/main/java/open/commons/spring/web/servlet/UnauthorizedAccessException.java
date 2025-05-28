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
 * Date  : 2025. 5. 19. 오후 4:00:06
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 권한이 없는 데이터 접근이 경우 발생시키는 예외 클래스.
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @see HttpStatus#UNAUTHORIZED
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedAccessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public UnauthorizedAccessException() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public UnauthorizedAccessException(String message) {
        super(message);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     * @param cause
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public UnauthorizedAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param cause
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public UnauthorizedAccessException(Throwable cause) {
        super(cause);
    }

}
