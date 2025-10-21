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
 * Date  : 2025. 5. 19. 오후 5:39:00
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import javax.validation.constraints.NotEmpty;

import open.commons.core.Result;
import open.commons.core.TwoValueObject;

/**
 * 클래스 필드 접근권한 제공 서비스.
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IFieldAccessAuthorityProvider extends IResourceAccessAuthorityProvider {

    /**
     * 클래스의 특정 필드에 대한 처리방식을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 27.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param type
     *            데이터 유형<br>
     *            {@link Class#toString()} 값이 전달됨.
     * @param fieldName
     *            필드 이름
     * @return
     *         <li>{@link TwoValueObject#first}: 데이터 제공 허용 여부
     *         <li>{@link TwoValueObject#second}: 허용되지 않은 경우 처리 방식.
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Result<FieldAccessAuthorityDecision> isAllowed(@NotEmpty String type, @NotEmpty String fieldName);

}
