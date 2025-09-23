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
 * Date  : 2025. 6. 13. 오후 1:36:08
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.exception;

import org.springframework.beans.BeansException;

/**
 * 
 * @since 2025. 6. 13.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class InvalidBeanNameFqnResolveException extends BeansException {

    private static final long serialVersionUID = 1L;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public InvalidBeanNameFqnResolveException(String message) {
        super(message);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param message
     * @param cause
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public InvalidBeanNameFqnResolveException(String message, Throwable cause) {
        super(message, cause);
    }
}
