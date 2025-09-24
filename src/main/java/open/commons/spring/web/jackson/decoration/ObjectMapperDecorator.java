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
 * Date  : 2025. 6. 17. 오전 10:28:19
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.decoration;

import java.util.Collections;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;

/**
 * 
 * @since 2025. 6. 17.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @deprecated {@link Jackson2ObjectMapperBuilder}를 {@link Bean}으로 제공받아 {@link ObjectMapper}를 생성하는 방식으로 변경됨에 따라 필요성이
 *             없어짐.
 */
public class ObjectMapperDecorator implements IObjectMapperDecorator {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.jacksons.decoration.ObjectMapperDecorator";

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ObjectMapperDecorator() {
    }

    /**
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.jackson. decoration.IObjectMapperDecorator#disables()
     */
    @Override
    public Set<Object> disables() {
        return Collections.emptySet();
    }

    /**
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.jackson. decoration.IObjectMapperDecorator#enables()
     */
    @Override
    public Set<Object> enables() {
        return Collections.emptySet();
    }

    /**
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.jackson. decoration.IObjectMapperDecorator#modules()
     */
    @Override
    public Set<Module> modules() {
        return Collections.emptySet();
    }

    /**
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.jackson. decoration.IObjectMapperDecorator#namedTypes()
     */
    @Override
    public Set<NamedType> namedTypes() {
        return Collections.emptySet();
    }

    /**
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.jackson. decoration.IObjectMapperDecorator#subtypes()
     */
    @Override
    public Set<Class<?>> subtypes() {
        return Collections.emptySet();
    }
}
