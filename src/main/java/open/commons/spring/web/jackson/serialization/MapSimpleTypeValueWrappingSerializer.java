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
 * Date  : 2025. 9. 25. 오후 8:18:11
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.serialization;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import open.commons.spring.web.beans.authority.FieldAccessAuthorityDecision;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;

/**
 * Map의 단순타입 Value에 AuthorizedField 처리를 적용하는 Serializer.
 * <li>- Jackson 권장 API: writeStartObject(Object forValue)
 * <li>- 값 출력은 serializers.defaultSerializeValue(...) 사용
 * 
 * @since 2025. 9. 25.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class MapSimpleTypeValueWrappingSerializer extends AbstractWrappingSerializer {

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 25.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param context
     * @param serializedType
     *            데이터 유형
     * @param annotatedField
     *            필드 어노테이션
     * @param fieldAccessor
     *            필드 접근제어 서비스
     * @param fieldHandler
     *            필드 데이터 처리 서비스
     * @param authorizedResourcesMetadata
     *            메타데이터 제공 서비스
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public MapSimpleTypeValueWrappingSerializer(ApplicationContext context, Class<?> serializedType, AnnotatedField annotatedField, IFieldAccessAuthorityProvider fieldAccessor,
            IUnauthorizedFieldHandler fieldHandler, IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
        super(context, serializedType, annotatedField, fieldAccessor, fieldHandler, authorizedResourcesMetadata);
    }

    /**
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object,
     *      com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (!(value instanceof Map)) {
            serializers.defaultSerializeValue(value, gen);
            return;
        }

        FieldAccessAuthorityDecision decision = decide();

        Map<?, ?> map = (Map<?, ?>) value;

        try {
            gen.writeStartObject(value);
        } catch (Throwable t) {
            gen.setCurrentValue(value);
            gen.writeStartObject();
        }
        for (Map.Entry<?, ?> e : map.entrySet()) {
            gen.writeFieldName(String.valueOf(e.getKey()));
            writeValueRecursive(e.getValue(), gen, serializers, decision);
        }
        gen.writeEndObject();
    }

    // Map의 Value를 재귀 처리 (Collection/Array/Map/POJO 포함)
    private void writeValueRecursive(Object rawValue, JsonGenerator gen, SerializerProvider sp, FieldAccessAuthorityDecision decision) throws IOException {
        if (rawValue == null) {
            gen.writeNull();
            return;
        }

        Class<?> rawClass = rawValue.getClass();

        if (isSimpleType(rawClass)) {
            Object value = handleValue(rawValue, null);
            sp.defaultSerializeValue(value, gen);
            return;
        }
        if (rawClass.isArray()) {
            int len = Array.getLength(rawValue);
            try {
                gen.writeStartArray(rawValue, len);
            } catch (Throwable t) {
                gen.setCurrentValue(rawValue);
                gen.writeStartArray();
            }
            for (int i = 0; i < len; i++) {
                writeValueRecursive(Array.get(rawValue, i), gen, sp, decision);
            }
            gen.writeEndArray();
            return;
        }
        if (rawValue instanceof Collection<?>) {
            Collection<?> col = (Collection<?>) rawValue;
            try {
                gen.writeStartArray(rawValue, col.size());
            } catch (Throwable t) {
                gen.setCurrentValue(rawValue);
                gen.writeStartArray();
            }
            for (Object e : col) {
                writeValueRecursive(e, gen, sp, decision);
            }
            gen.writeEndArray();
            return;
        }
        if (rawValue instanceof Map<?, ?>) {
            Map<?, ?> m = (Map<?, ?>) rawValue;
            try {
                gen.writeStartObject(rawValue);
            } catch (Throwable t) {
                gen.setCurrentValue(rawValue);
                gen.writeStartObject();
            }
            for (Map.Entry<?, ?> en : m.entrySet()) {
                gen.writeFieldName(String.valueOf(en.getKey()));
                writeValueRecursive(en.getValue(), gen, sp, decision);
            }
            gen.writeEndObject();
            return;
        }

        // POJO
        sp.defaultSerializeValue(rawValue, gen);
    }
}
