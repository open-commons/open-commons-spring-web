/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * This file is generated under this project, "open-commons-spring5".
 *
 * Date  : 2019. 6. 3. 오후 5:23:26
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * {@link Enum} 타입 중에 URL 처리 메소드에 파라미터로 사용되는 클래스를 생성하는 메소드를 선언.
 * 
 * @since 2019. 6. 3.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestValueConverter {

    /**
     * 대/소문자 파라미터가 있는지 여부를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 5. 29.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 5. 29.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    boolean hasIgnoreCase() default false;

}
