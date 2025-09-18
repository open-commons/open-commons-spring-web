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
 * Date  : 2025. 9. 18. 오후 12:46:42
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import open.commons.spring.web.authority.AuthorizedData;
import open.commons.spring.web.authority.AuthorizedField;

/**
 * {@link AuthorizedData#handleBean()}에서 사용되는 인터페이스.
 * 
 * @since 2025. 9. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IAuthorizedDataHandler {

    /**
     * 데이터 처리 방식에 따라서 {@link AuthorizedField#fieldHandleBean()}에 의해서 처리된 데이터를 원복하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param handle
     *            데이터 처리 방식 식별정보
     * @param value
     *            {@link AuthorizedField#fieldHandleBean()}에 의해서 처리된 데이터
     * @return
     * @throws UnsupportedOperationException
     *             지원하지 않는 <code>handle</code>을 전달받았을 때.
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Object restoreValue(int handle, Object value) throws UnsupportedOperationException;

}
