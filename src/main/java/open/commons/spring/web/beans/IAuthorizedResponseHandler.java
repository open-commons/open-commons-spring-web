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
 * Date  : 2025. 5. 21. 오후 1:29:21
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans;

import open.commons.spring.web.ac.AuthorizedResponse;

/**
 * {@link AuthorizedResponse}를 처리하는 기능 정의.
 * 
 * @since 2025. 5. 21.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IAuthorizedResponseHandler {

    /**
     * 데이터를 처리합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 20.     박준홍         최초 작성
     * </pre>
     *
     * @param o
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Object handle(Object o) throws Throwable;

}