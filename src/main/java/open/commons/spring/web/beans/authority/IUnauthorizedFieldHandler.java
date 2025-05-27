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
 * Date  : 2025. 5. 20. 오후 4:41:27
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import open.commons.spring.web.authority.AuthorizedField;

/**
 * {@link AuthorizedField#mode()} 처리를 하는 기능 정의.
 * 
 * @since 2025. 5. 20.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IUnauthorizedFieldHandler {

    /**
     * 데이터 처리방식에 따라 데이터를 처리한 결과를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param handle
     *            데이터 처리방식 식별정보
     * @param data
     *            데이터
     * @return
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Object handleObject(int handle, Object data);

}