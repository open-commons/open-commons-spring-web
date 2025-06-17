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
 * Date  : 2025. 6. 17. 오전 10:28:19
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import java.util.Collections;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * @since 2025. 6. 17.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedObjectMapperDecorator implements IAuthorizedObjectMapperDecorator {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.beans.authority.AuthorizedObjectMapperDecorator";

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
    public AuthorizedObjectMapperDecorator() {
    }

    /**
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedObjectMapperDecorator#configureFeature(com.fasterxml.jackson.databind.ObjectMapper)
     */
    @Override
    public final void configureFeature(ObjectMapper objectMapper) {

        // #1. set module
        objectMapper.registerModules(modules());

        // #2. set NamedType
        Class<?>[] namedTypes = subtypes().stream().toArray(Class[]::new);
        objectMapper.registerSubtypes(namedTypes);

        // #3. configure ConfigFeature
        for (Object feature : enables()) {
            configureFeature(objectMapper, feature, true);
        }
        for (Object feature : disables()) {
            configureFeature(objectMapper, feature, false);
        }
    }

    /**
     * <p>
     * COPY FROM "org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.configureFeature(ObjectMapper,
     * Object, boolean)" on spring-web-5.3.29
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param objectMapper
     * @param feature
     * @param enabled
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @SuppressWarnings("deprecation") // on Jackson 2.13: configure(MapperFeature, boolean)
    private void configureFeature(ObjectMapper objectMapper, Object feature, boolean enabled) {
        if (feature instanceof JsonParser.Feature) {
            objectMapper.configure((JsonParser.Feature) feature, enabled);
        } else if (feature instanceof JsonGenerator.Feature) {
            objectMapper.configure((JsonGenerator.Feature) feature, enabled);
        } else if (feature instanceof SerializationFeature) {
            objectMapper.configure((SerializationFeature) feature, enabled);
        } else if (feature instanceof DeserializationFeature) {
            objectMapper.configure((DeserializationFeature) feature, enabled);
        } else if (feature instanceof MapperFeature) {
            objectMapper.configure((MapperFeature) feature, enabled);
        } else {
            throw new IllegalArgumentException("Unknown feature class: " + feature.getClass().getName());
        }
    }

    /**
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedObjectMapperDecorator#disables()
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
     * @see open.commons.spring.web.beans.authority.IAuthorizedObjectMapperDecorator#enables()
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
     * @see open.commons.spring.web.beans.authority.IAuthorizedObjectMapperDecorator#modules()
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
     * @see open.commons.spring.web.beans.authority.IAuthorizedObjectMapperDecorator#subtypes()
     */
    @Override
    public Set<Class<?>> subtypes() {
        return Collections.emptySet();
    }
}
