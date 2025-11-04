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
 * Date  : 2025. 5. 19. 오후 1:08:42
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import open.commons.core.Result;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.utils.SecurityUtils;

/**
 * 자원 접근 권한 제공 기능 정의.
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IResourceAccessAuthorityProvider {

    /**
     * <code>Security Session</code>이 존재한다면, 사용자 정보(일반적으로 username)을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 16.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    default Result<String> getCurrentUserId() {
        String principal = SecurityUtils.getCurrentPrincipal();
        return new Result<String>(principal, StringUtils.isNullOrEmptyString(principal));
    }
}
