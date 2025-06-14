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
 * Date  : 2025. 6. 13. 오후 1:45:29
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.authority.metadata;

import javax.validation.constraints.NotNull;

import open.commons.spring.web.utils.BeanUtils;

/**
 * 
 * @since 2025. 6. 13.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AuthorizedMetadata {

    private static final String PREFIX_CLASSPATH = "classpath:";

    protected final String resolveBeanName(@NotNull String beanName) {
        return beanName.startsWith(PREFIX_CLASSPATH) //
                ? BeanUtils.resolveBeanNameFromFqn(beanName.replace(PREFIX_CLASSPATH, "")) //
                : beanName;
    }
}
