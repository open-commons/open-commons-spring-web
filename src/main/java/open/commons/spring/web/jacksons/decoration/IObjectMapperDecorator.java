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
 * Date  : 2025. 6. 17. 오전 10:10:20
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jacksons.decoration;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import open.commons.spring.web.authority.AuthorizedObject;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;

/**
 * {@link AuthorizedObject}를 처리하는 {@link ObjectMapper}에 대한 추가 기능을 설정합니다.
 * 
 * @since 2025. 6. 17.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @deprecated {@link Jackson2ObjectMapperBuilder}를 {@link Bean}으로 제공받아 {@link ObjectMapper}를 생성하는 방식으로 변경됨에 따라 필요성이
 *             없어짐.
 */
public interface IObjectMapperDecorator {

    /**
     * 비활성화 기능을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see JsonGenerator.Feature
     * @see JsonParser.Feature
     * @see SerializationFeature
     * @see DeserializationFeature
     * @see MapperFeature
     * 
     */
    public Set<Object> disables();

    /**
     * 활성화 기능을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see JsonGenerator.Feature
     * @see JsonParser.Feature
     * @see SerializationFeature
     * @see DeserializationFeature
     * @see MapperFeature
     */
    public Set<Object> enables();

    /**
     * {@link Module}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see ObjectMapper#registerModule(Module)
     * @see ObjectMapper#registerModules(Iterable)
     * @see ObjectMapper#registerModules(Module...)
     */
    public Set<Module> modules();

    /**
     * {@link NamedType}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 17.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     *
     * @see NamedType
     * @see ObjectMapper#registerSubtypes(NamedType...)
     */
    public Set<NamedType> namedTypes();

    /**
     * {@link NamedType}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     *
     * @see NamedType
     * @see ObjectMapper#registerSubtypes(Class...)
     * @see ObjectMapper#registerSubtypes(java.util.Collection)
     * @see ObjectMapper#registerSubtypes(NamedType...)
     */
    public Set<Class<?>> subtypes();

    // public JsonInclude.Include serializationInclusion();
    //
    // public JsonInclude.Value serializationInclusionValue();

}
