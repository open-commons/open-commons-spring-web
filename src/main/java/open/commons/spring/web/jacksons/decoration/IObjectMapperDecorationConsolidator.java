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
 * Date  : 2025. 6. 17. 오후 2:35:49
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jacksons.decoration;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @since 2025. 6. 17.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IObjectMapperDecorationConsolidator {

    public void addObjectMapperDecorator(@NotNull Collection<IObjectMapperDecorator> decorators);

    public void addObjectMapperDecorator(@NotNull IObjectMapperDecorator... decorators);

    public void configureFeature(@NotNull ObjectMapper objectMapper);

}
