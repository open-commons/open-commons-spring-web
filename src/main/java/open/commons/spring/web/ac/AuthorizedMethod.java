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
 * Date  : 2025. 5. 16. 오후 3:22:04
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.ac;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 메소드를 사용하기 위한 접근 권한을 정의하는 클래스.<br>
 *
 * {@link ElementType#TYPE}에 설정하는 경우는, 해당 클래스의 모든 <code>public</code> 메소드에 일괄적으로 적용하기 위해서입니다.
 * 
 * @since 2025. 5. 16.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Documented
@Inherited
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizedMethod {
    /**
     * 접근권한을 검증하는 Bean 정보를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 16.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    String bean() default "";

    /**
     * {@link #roles()}의 값이 2개 이상인 경우 처리하는 규칙. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 16.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Operator op() default Operator.OR;

    /**
     * 접근이 허용되는 권한(ROLE_XXX) 코드 목록입니다. <br>
     * 처리정책은 '화이트 리스트' 기반입니다. 즉, 이 값이 비어 있는 경우 모든 접근을 차단합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 16.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see AuthorizationManager
     * @see Authentication
     * @see GrantedAuthority
     */
    String[] roles();

    /**
     * {@link AuthorizedMethod#roles()}의 값이 2개 이상인 경우 처리하는 규칙.
     * 
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    enum Operator {
        /** 모든 'ROLE'이 권한이 허용되어야 하는 경우. */
        AND,
        /** 여러 개 중에 1개의 권한만 허용되어도 되는 경우. */
        OR
    }
}
