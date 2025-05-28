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
 * Date  : 2020. 7. 30. 오후 7:51:09
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.servlet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 서버 내부 에러가 발생한 경우 {@link ResponseEntityExceptionHandler} 에서 처리할 수 있도록 발생시키는 예외클래스.
 * 
 * @since 2020. 7. 30.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    /**
     *
     * @since 2020. 7. 30.
     */
    private static final long serialVersionUID = 1L;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @since 2020. 7. 30.
     */
    public InternalServerException() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param arg0
     * @since 2020. 7. 30.
     */
    public InternalServerException(String arg0) {
        super(arg0);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param arg0
     * @param arg1
     * @since 2020. 7. 30.
     */
    public InternalServerException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @since 2020. 7. 30.
     */
    public InternalServerException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param arg0
     * @since 2020. 7. 30.
     */
    public InternalServerException(Throwable arg0) {
        super(arg0);
    }

}
