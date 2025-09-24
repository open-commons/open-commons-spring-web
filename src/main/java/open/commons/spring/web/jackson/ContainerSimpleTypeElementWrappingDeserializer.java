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

package open.commons.spring.web.jackson;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

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

    private Object coercePrimitiveIfNeeded(Class<?> componentType, Object value) {
        if (!componentType.isPrimitive())
            return value;
        if (value == null) {
            // primitive에 null은 불가 → 0/false 기본값
            if (boolean.class.equals(componentType))
                return false;
            if (char.class.equals(componentType))
                return (char) 0;
            return 0;
        }
        if (boolean.class.equals(componentType)) {
            return (value instanceof Boolean) ? value : Boolean.parseBoolean(String.valueOf(value));
        }
        if (char.class.equals(componentType)) {
            if (value instanceof Character)
                return value;
            String s = String.valueOf(value);
            return s.isEmpty() ? (char) 0 : s.charAt(0);
        }
        // 수치형
        Number n = (value instanceof Number) ? (Number) value : parseNumber(String.valueOf(value));
        if (byte.class.equals(componentType))
            return n.byteValue();
        if (short.class.equals(componentType))
            return n.shortValue();
        if (int.class.equals(componentType))
            return n.intValue();
        if (long.class.equals(componentType))
            return n.longValue();
        if (float.class.equals(componentType))
            return n.floatValue();
        if (double.class.equals(componentType))
            return n.doubleValue();
        return value;
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
        // 기본 컨테이너 deserializer 확보 (이 시점엔 준비되어 있음)
        JsonDeserializer<Object> base = (JsonDeserializer<Object>) ctxt.findContextualValueDeserializer(containerType, property);
        return new ContainerSimpleTypeElementWrappingDeserializer(this.containerType, this.handler, this.handleType, base);
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
    @SuppressWarnings("unchecked")
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // Jackson이 컨테이너 전체를 먼저 만듦
        Object container = this.delegate.deserialize(p, ctxt);

        if (container == null) {
            return null;
        }

        // List 인 경우, 데이터를 복제하지 않고 처리
        if (container instanceof List) {
            ListIterator<Object> itr = ((List<Object>) container).listIterator();
            while (itr.hasNext()) {
                Object elemValue = itr.next();
                // itr.set(this.elemDeserializer.deserialize(ctxt.getParser(), ctxt));
                itr.set(handleObject(elemValue));
            }
            return container;
        }

        // 그 이외의 경우 데이터를 복제하여 처리
        if (container instanceof Collection) {
            Collection<Object> col = (Collection<Object>) container;
            Collection<Object> newCol = instantiateSameCollection(col);
            for (Object elem : col) {
                newCol.add(handleObject(elem));
            }
            return newCol;
        }

        // 배열: 원시/레퍼런스 배열 모두 지원
        Class<?> compType = container.getClass().getComponentType();
        if (compType != null && container.getClass().isArray()) {
            int len = Array.getLength(container);
            Object newArr = Array.newInstance(compType, len);
            for (int i = 0; i < len; i++) {
                Object v = Array.get(container, i);
                Object nv = handleObject(v);
                Array.set(newArr, i, coercePrimitiveIfNeeded(compType, nv));
            }
            return newArr;
        }

        return container;
    }

    private Object handleObject(Object value) {
        return this.handler.restoreValue(this.handleType, value);
    }

    @SuppressWarnings("unchecked")
    private Collection<Object> instantiateSameCollection(Collection<Object> col) {
        try {
            return col.getClass().getDeclaredConstructor().newInstance();
        } catch (Throwable ignore) {
            return new ArrayList<>(col.size());
        }
    }

    private Number parseNumber(String s) {
        try {
            if (s.indexOf('.') >= 0)
                return Double.valueOf(s);
            return Long.valueOf(s);
        } catch (Exception ignore) {
            return 0;
        }
    }

}
