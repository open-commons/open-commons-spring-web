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
 * Date  : 2025. 11. 21. 오후 3:36:19
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 'OCSW'에서 정의하는 {@link Aspect} 클래스들의 우선순위 값을 선언하는 클래스.
 * 
 * @since 2025. 11. 21.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class AspectOrder {

    /** {@link ProfilesOnAspect}의 {@link Order} 값 */
    public static final int PROFILES_ON = Ordered.HIGHEST_PRECEDENCE;
    /** {@link AuthorizedRequestAspect}의 {@link Order} 값 */
    public static final int AUTHROIZED_REQUEST = PROFILES_ON + 1;
    /** {@link AuthorizedMethodAspect}의 {@link Order} 값 */
    public static final int AUTHORIZED_METHOD = AUTHROIZED_REQUEST + 1;
    /** {@link LogFeatureAspect}의 {@link Order} 값 */
    public static final int LOG_FEATURE = AUTHORIZED_METHOD + 1;

    private AspectOrder() {
    }
}
