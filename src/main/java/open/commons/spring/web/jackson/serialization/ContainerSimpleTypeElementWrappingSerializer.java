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
 * Date  : 2025. 9. 25. 오후 8:03:54
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
 * 배열/Collection의 단순타입 원소에 AuthorizedField 처리를 적용하는 Serializer.
 * <li>- Jackson 2.12+ 권장 API 사용(writeStartArray(Object,int) / writeStartArray(Object))
 * <li>- 요소 출력은 serializers.defaultSerializeValue(...) 사용
 * <li>- 내부가 컨테이너일 경우 ContextualSerializer로 '중첩 래퍼 체인'을 구성하여 재귀 처리.
 * 
 * @since 2025. 9. 25.
 * 
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ContainerSimpleTypeElementWrappingSerializer extends AbstractWrappingSerializer {

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
    public ContainerSimpleTypeElementWrappingSerializer(ApplicationContext context, Class<?> serializedType, AnnotatedField annotatedField,
            IFieldAccessAuthorityProvider fieldAccessor, IUnauthorizedFieldHandler fieldHandler, IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
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

        if (value == null) {
            gen.writeNull();
            return;
        }

        // 현재 필드 정책 계산(한 번만)
        FieldAccessAuthorityDecision decision = decide();
        // 최상위가 배열/컬렉션이 아니더라도 방어적으로 재귀 진입 가능
        writeValueRecursive(value, gen, serializers, decision);
    }

    private void writeValueRecursive(Object rawValue, JsonGenerator gen, SerializerProvider sp, FieldAccessAuthorityDecision decision) throws IOException {
        if (rawValue == null) {
            gen.writeNull();
            return;
        }

        Class<?> rawClass = rawValue.getClass();

        // 1) 단순 타입 → 바로 변환 후 출력
        if (isSimpleType(rawClass)) {
            Object value = handleValue(rawValue, decision);
            sp.defaultSerializeValue(value, gen);
            return;
        }

        // 2) 배열
        if (rawClass.isArray()) {
            int len = Array.getLength(rawValue);
            try {
                gen.writeStartArray(rawValue, len);
            } catch (Throwable t) {
                gen.setCurrentValue(rawValue);
                gen.writeStartArray();
            }
            for (int i = 0; i < len; i++) {
                Object e = Array.get(rawValue, i);
                writeValueRecursive(e, gen, sp, decision);
            }
            gen.writeEndArray();
            return;
        }

        // 3) Collection
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

        // 4) Map → 값만 재귀 처리(키는 문자열화)
        if (rawValue instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) rawValue;
            try {
                gen.writeStartObject(rawValue);
            } catch (Throwable t) {
                gen.setCurrentValue(rawValue);
                gen.writeStartObject();
            }
            for (Map.Entry<?, ?> e : map.entrySet()) {
                gen.writeFieldName(String.valueOf(e.getKey()));
                writeValueRecursive(e.getValue(), gen, sp, decision);
            }
            gen.writeEndObject();
            return;
        }

        // 5) POJO → 기본 BeanSerializer에 위임 (내부 @AuthorizedField가 처리)
        sp.defaultSerializeValue(rawValue, gen);
    }
}
