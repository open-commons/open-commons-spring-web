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
 * Date  : 2025. 6. 5. 오후 12:55:54
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import open.commons.spring.web.autoconfigure.configuration.AuthorizedObjectMessageConfigureConfiguration;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedObjectMessageConverterConfiguration;
import open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration;
import open.commons.spring.web.config.AuthorizedResourcesMetadataConfiguration;

/**
 * Open-Commons Spring Web에서 제공하는 기능을 활성화하는 어노테이션.
 * 
 * @since 2025. 6. 5.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @see AuthorizedResourcesMetadataConfiguration
 * @see AuthorizedObjectMessageConfigureConfiguration
 * @see AuthorizedObjectMessageConverterConfiguration
 * @see GlobalServletConfiguration
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(OpenCommonsSpringWebAutoConfigurationImportSelector.class)
public @interface EnableOpenCommonsSpringWeb {

    /**
     * 제외시키고자 하는 Configuration 클래스. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 5.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 5.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Class<?>[] exclude() default {};
}
