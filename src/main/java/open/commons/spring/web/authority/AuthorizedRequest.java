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
 * Date  : 2025. 5. 19. 오전 10:43:06
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.authority;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

import open.commons.spring.web.beans.authority.IRequestAccessAuthorityProvider;

/**
 * REST API 를 사용하기 위한 접근 권한을 정의하는 클래스.<br>
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Documented
@Inherited
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizedRequest {

    /**
     * 접근권한을 검증하는 Bean 정보를 설정합니다. <br>
     * 설정된 Bean은 {@link IRequestAccessAuthorityProvider} 인터페이스를 구현해야 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 16.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see IRequestAccessAuthorityProvider
     */
    @AliasFor("value")
    String authorityBean() default "";

    /**
     * 기능 설명 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 26.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    String descr() default "";

    /**
     * 기능 이름<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 26.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    String name() default "";

    /**
     * 접근권한을 검증하는 Bean 정보를 설정합니다. <br>
     * 설정된 Bean은 {@link IRequestAccessAuthorityProvider} 인터페이스를 구현해야 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @AliasFor("authorityBean")
    String value() default "";
}
