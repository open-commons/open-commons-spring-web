/*
 * Copyright 2022 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2022. 12. 1. 오후 5:47:09
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * REST API URL에 패턴에는 만족하지만, 데이터가 존재하지 않는 경우 발생시키는 예외 클래스.
 * 
 * @since 2022. 12. 1.
 * @version 0.5.0
 * @author parkjunhong77@gmail.com
 * 
 * @see HttpStatus#NOT_FOUND
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     *
     * @since 2022. 12. 1.
     */
    private static final long serialVersionUID = 1L;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 12. 1.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2022. 12. 1.
     * @version 0.5.0
     * @author parkjunhong77@gmail.com
     */
    public NotFoundException() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 12. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     *
     * @since 2022. 12. 1.
     * @version 0.5.0
     * @author parkjunhong77@gmail.com
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 12. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     * @param cause
     *
     * @since 2022. 12. 1.
     * @version 0.5.0
     * @author parkjunhong77@gmail.com
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 12. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     *
     * @since 2022. 12. 1.
     * @version 0.5.0
     * @author parkjunhong77@gmail.com
     */
    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 12. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param cause
     *
     * @since 2022. 12. 1.
     * @version 0.5.0
     * @author parkjunhong77@gmail.com
     */
    public NotFoundException(Throwable cause) {
        super(cause);
    }

}
