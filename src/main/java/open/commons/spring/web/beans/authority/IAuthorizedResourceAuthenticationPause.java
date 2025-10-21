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
 * Date  : 2025. 6. 24. 오후 4:02:45
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import open.commons.core.Result;

/**
 * 권한기반 데이터 제어(Authorized-Resources) 비활성화 여부를 판단하는 기능.
 * 
 * @since 2025. 6. 24.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IAuthorizedResourceAuthenticationPause {

    /**
     * 현재 로그인한 사용자 정보를 기반으로 권한기반 데이터 제어(Authorized-Resources) 비활성화 여부를 판단합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 24.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public Result<Boolean> pause();

}
