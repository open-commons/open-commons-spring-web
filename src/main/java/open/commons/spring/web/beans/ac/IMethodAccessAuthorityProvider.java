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
 * Date  : 2025. 5. 16. 오후 3:58:47
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.ac;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import open.commons.core.Result;
import open.commons.spring.web.authority.AuthorizedMethod;
import open.commons.spring.web.authority.AuthorizedMethod.Operator;

/**
 * 메소드 접근 권한 제공 서비스.
 * 
 * @since 2025. 5. 16.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @see AuthorizedMethod
 */
public interface IMethodAccessAuthorityProvider extends IResourceAccessAuthorityProvider {

    /**
     * 사용자의 권한에 기반하여 접근여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param operator
     * @param authorities
     * @return
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Result<Boolean> isAllowed(@NotNull Operator operator, @NotEmpty String... authorities);

}
