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
 * Date  : 2025. 5. 23. 오후 4:57:07
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson;

import java.io.IOException;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.Result;
import open.commons.core.TwoValueObject;
import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.servlet.InternalServerException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

/**
 * {@link SecureField}가 적용된 Field를 JSON 문자열로 변한해 주는 클래스.
 * 
 * @since 2025. 5. 23.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedFieldSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    private Logger logger = LoggerFactory.getLogger(AuthorizedFieldSerializer.class);

    /** 권한 제어 대상 필드 */
    private final AnnotatedField annotatedField;
    /** 필드 접근 권한 서비스 */
    private final IFieldAccessAuthorityProvider authority;
    /** 권한제어 유형 처리 기능 */
    private final IUnauthorizedFieldHandler fieldHandler;

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
     * @param annotatedField
     *            필드 정보
     * @param authority
     *            필드 접근권한 서비스
     * @param fieldHandler
     *            데이터 처리 서비스
     * @since 2025. 5. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedFieldSerializer(AnnotatedField field, IFieldAccessAuthorityProvider authority, IUnauthorizedFieldHandler fieldHandler) {
        this.annotatedField = field;
        this.authority = authority;
        this.fieldHandler = fieldHandler;
    }

    /**
     *
     * @since 2025. 5. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see com.fasterxml.jackson.databind.ser.ContextualSerializer#createContextual(com.fasterxml.jackson.databind.SerializerProvider,
     *      com.fasterxml.jackson.databind.BeanProperty)
     */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        AuthorizedField annotation = property.getAnnotation(AuthorizedField.class);
        if (annotation != null) {
            return new AuthorizedFieldSerializer(this.annotatedField, this.authority, this.fieldHandler);
        }
        return prov.findValueSerializer(property.getType(), property);
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
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        // #1. 필드 접근권한 확인
        Field fieldInfo = this.annotatedField.getAnnotated();
        String type = fieldInfo.getDeclaringClass().getName();
        String field = fieldInfo.getName();

        try {
            Result<TwoValueObject<Boolean, Integer>> resultFieldAccessible = this.authority.isAllowed(type, field);
            if (resultFieldAccessible == null) {
                throw ExceptionUtils.newException(InternalServerException.class,
                        "Field 접근에 대한 판단은 'null'일 수가 없습니다. 원인=open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider.isAllowed(String, String) 구현이 올바르지 않습니다.");
            } else if (resultFieldAccessible.isError()) {
                throw ExceptionUtils.newException(InternalServerException.class, "필드 접근권한 조회시 오류가 발생하였습니다. 원인=%s", resultFieldAccessible.getMessage());
            } else if (resultFieldAccessible.getData() == null) {
                throw ExceptionUtils.newException(InternalServerException.class, "필드 접근권한 조회시 오류가 발생하였습니다. 원인='권한조회결과가 존재하지 않습니다.'");
            }

            // #2. 데이터 처리
            TwoValueObject<Boolean, Integer> fieldAccessible = resultFieldAccessible.getData();
            boolean accessible = fieldAccessible.first;
            int handle = fieldAccessible.second;
            if (accessible) {
                gen.writeObject(value); // 그대로 출력
            } else {
                Object newValue = this.fieldHandler.handleObject(handle, value);
                gen.writeObject(newValue);
            }
        } catch (Exception e) {
            String errMsg = String.format("데이터 변환 도중 오류가 발생했습니다. type=%s, field=%s, value=%s, 원인=%s", type, field, value, e.getMessage());
            logger.error(errMsg, e);
            throw ExceptionUtils.newException(InternalServerException.class, e, errMsg);
        }
    }
}
