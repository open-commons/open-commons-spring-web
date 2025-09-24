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
 * Date  : 2025. 9. 23. 오후 2:12:39
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson;

import java.io.IOException;
import java.util.Map;

import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;

/**
 * {@link Map}의 value(값)에 대한 'deserialization'을 처리하는 클래스.
 * 
 * @since 2025. 9. 23.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class MapSimpleTypeValueWrappingDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {

    // Map<*, SimpleType>
    private final JavaType mapType;
    private final IAuthorizedRequestDataHandler handler;
    private final int handleType;

    // createContextual 이후 주입
    private final JsonDeserializer<?> delegate;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 23.		박준홍			최초 작성
     * </pre>
     * 
     * @param mapType
     * @param handler
     *            {@link IAuthorizedRequestDataHandler} 구현 객체.
     * @param handleType
     *            데이터 처리 방식
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public MapSimpleTypeValueWrappingDeserializer(JavaType mapType, IAuthorizedRequestDataHandler handler, int handleType) {
        this(mapType, handler, handleType, null);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 23.		박준홍			최초 작성
     * </pre>
     * 
     * @param mapType
     * @param handler
     *            {@link IAuthorizedRequestDataHandler} 구현 객체.
     * @param handleType
     *            데이터 처리 방식
     * @param delegate
     *            기본 {@link MapDeserializer}
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public MapSimpleTypeValueWrappingDeserializer(JavaType mapType, IAuthorizedRequestDataHandler handler, int handleType, JsonDeserializer<?> delegate) {
        this.mapType = mapType;
        this.handler = handler;
        this.handleType = handleType;
        this.delegate = delegate;
    }

    /**
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see com.fasterxml.jackson.databind.deser.ContextualDeserializer#createContextual(com.fasterxml.jackson.databind.DeserializationContext,
     *      com.fasterxml.jackson.databind.BeanProperty)
     */
    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        if (this.delegate != null) {
            return this; // 이미 contextual-resolved
        }
        //  Map 타입 자체로 표준 delegate 획득
        JsonDeserializer<Object> std = (JsonDeserializer<Object>) ctxt.findContextualValueDeserializer(mapType, property);
        if (std == null) {
            std = (JsonDeserializer<Object>) ctxt.findRootValueDeserializer(mapType);
        }
        return new MapSimpleTypeValueWrappingDeserializer(mapType, handler, handleType, std);
    }

    /**
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser,
     *      com.fasterxml.jackson.databind.DeserializationContext)
     */
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Object mapObj = this.delegate.deserialize(p, ctxt);

        if (!(mapObj instanceof Map)) {
            return mapObj;
        }

        //  그 다음 자바 객체를 재귀 후처리 (값/value만 대상)
        return AuthorizedRequestDataContainerWalker.processRecursively(mapObj, mapType, handler, handleType);
    }
}
