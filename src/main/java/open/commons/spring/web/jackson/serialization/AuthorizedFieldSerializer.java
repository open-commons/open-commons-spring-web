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
 * Date  : 2025. 5. 23. 오후 4:57:07
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.serialization;

import java.io.IOException;

import open.commons.core.TwoValueObject;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;

/**
 * {@link SecureField}가 적용된 Field를 JSON 문자열로 변한해 주는 클래스.
 * 
 * @since 2025. 5. 23.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedFieldSerializer extends AbstractWrappingSerializer {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 23.		박준홍			최초 작성
     * </pre>
     * 
     * @param serializedType
     *            데이터 유형
     * @param authority
     *            필드 접근권한 서비스
     * @param fieldHandler
     *            데이터 처리 서비스
     * @param authorizedResourcesMetadata
     *            {@link AuthorizedObject}, {@link AuthorizedField} 외부 설정 정보 제공 서비스
     * @since 2025. 5. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedFieldSerializer(Class<?> serializedType, AnnotatedField field, IFieldAccessAuthorityProvider authority, IUnauthorizedFieldHandler fieldHandler,
            IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
        super(serializedType, field, authority, fieldHandler, authorizedResourcesMetadata);
    }

    /**
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see com.fasterxml.jackson.databind.ser.ContextualSerializer#createContextual(com.fasterxml.jackson.databind.SerializerProvider,
     *      com.fasterxml.jackson.databind.BeanProperty)
     */
    // @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        return this;
    }

    /**
     *
     * @since 2025. 5. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object,
     *      com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(Object rawValue, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        if (rawValue == null) {
            gen.writeNull();
            return;
        }

        // 단순타입이 아니면 컨테이너/POJO → 위임 (컨테이너는 위 래퍼가 처리)
        if (!isSimpleType(rawValue.getClass())) {
            serializers.defaultSerializeValue(rawValue, gen);
            return;
        }

        TwoValueObject<Boolean, Integer> decision = decide();
        Object value = decision.first ? rawValue : this.fieldHandler.handleObject(decision.second, rawValue);
        serializers.defaultSerializeValue(value, gen);
    }
}
