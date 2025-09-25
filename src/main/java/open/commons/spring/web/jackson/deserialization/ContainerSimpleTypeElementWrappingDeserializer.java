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
 * Date  : 2025. 9. 23. 오후 2:13:14
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.deserialization;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;

import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;

/**
 * 배열(또는 {@link Array}, {@link Collection}의 데이터에 대한 'deserialization'을 처리하는 클래스.
 * 
 * @since 2025. 9. 23.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ContainerSimpleTypeElementWrappingDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {

    // List, Set, Collection, [] 등
    private final JavaType containerType;
    private final IAuthorizedRequestDataHandler handler;
    private final int handleType;

    // createContextual 이후 채워질 delegate
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
     * @param containerType
     *            TODO
     * @param handler
     *            {@link IAuthorizedRequestDataHandler} 구현 객체.
     * @param handleType
     *            데이터 처리 방식
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ContainerSimpleTypeElementWrappingDeserializer(JavaType containerType, IAuthorizedRequestDataHandler handler, int handleType) {
        this(containerType, handler, handleType, null);
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
     * @param containerType
     *            TODO
     * @param handler
     *            {@link IAuthorizedRequestDataHandler} 구현 객체.
     * @param handleType
     *            데이터 처리 방식
     * @param delegate
     *            기본 {@link CollectionDeserializer}
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ContainerSimpleTypeElementWrappingDeserializer(JavaType containerType, IAuthorizedRequestDataHandler handler, int handleType, JsonDeserializer<?> delegate) {
        this.containerType = containerType;
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
        // 컨테이너 타입 자체로 표준 delegate 획득 (property 컨텍스트 고려)
        JsonDeserializer<Object> std = (JsonDeserializer<Object>) ctxt.findContextualValueDeserializer(this.containerType, property);
        if (std == null) {
            std = (JsonDeserializer<Object>) ctxt.findRootValueDeserializer(this.containerType);
        }
        return new ContainerSimpleTypeElementWrappingDeserializer(this.containerType, this.handler, this.handleType, std);
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
        // Jackson이 컨테이너 전체를 먼저 만듦
        Object container = this.delegate.deserialize(p, ctxt);

        if (container == null) {
            return null;
        }

        // 그 다음 자바 객체를 재귀 후처리 (파서 재소모 없음)
        return AuthorizedRequestDataContainerWalker.processRecursively(container, this.containerType, this.handler, this.handleType);
    }
}
