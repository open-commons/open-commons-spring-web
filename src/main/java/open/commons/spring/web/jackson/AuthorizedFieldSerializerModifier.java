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
 * Date  : 2025. 5. 25. 오전 11:58:20
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import javax.validation.constraints.NotNull;

import org.springframework.context.ApplicationContext;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.beans.ac.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.ac.IUnauthorizedFieldHandler;
import open.commons.spring.web.utils.BeanUtils;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

/**
 * {@link SecureField}
 * 
 * @since 2025. 5. 25.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedFieldSerializerModifier extends BeanSerializerModifier {

    private final BeanUtils BEANS;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 25.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     *
     *
     * @since 2025. 5. 25.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedFieldSerializerModifier(@NotNull ApplicationContext context) {
        this.BEANS = BeanUtils.context(context);
    }

    /**
     *
     * @since 2025. 5. 25.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see com.fasterxml.jackson.databind.ser.BeanSerializerModifier#changeProperties(com.fasterxml.jackson.databind.SerializationConfig,
     *      com.fasterxml.jackson.databind.BeanDescription, java.util.List)
     */
    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {

        // #1. 타입에 정의된 어노테이션 조회
        AuthorizedObject annoObject = beanDesc.getClassInfo().getAnnotation(AuthorizedObject.class);

        // #2. 필드 처리.
        AnnotatedField field = null;
        AuthorizedField annoField = null;
        IFieldAccessAuthorityProvider authority = null;
        IUnauthorizedFieldHandler unauthorized = null;
        for (BeanPropertyWriter writer : beanProperties) {
            field = beanDesc.findProperties().stream() //
                    .filter(p -> p.getName().equals(writer.getName())) //
                    .map(BeanPropertyDefinition::getField) //
                    .filter(Objects::nonNull) //
                    .findFirst() //
                    .orElse(null);

            // AuthorizedField가 없는 경우
            if (field == null || !field.hasAnnotation(AuthorizedField.class)) {
                continue;
            }

            // AuthorizedField 처리
            annoField = field.getAnnotation(AuthorizedField.class);
            authority = getBean(IFieldAccessAuthorityProvider.class, annoObject::authorityBean, annoField::authorityBean);
            unauthorized = getBean(IUnauthorizedFieldHandler.class, annoObject::fieldHandleBean, annoField::fieldHandleBean);

            writer.assignSerializer(new AuthorizedFieldSerializer(field, authority, unauthorized));
        }

        return super.changeProperties(config, beanDesc, beanProperties);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param beanType
     *            Bean 유형
     * @param o
     *            타입에 정의된 어노테이션 이름
     * @param f
     *            필드에 정의된 어노테이션 이름
     * @return
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private <T> T getBean(Class<T> beanType, Supplier<String> o, Supplier<String> f) {
        String beanName = null;
        if (f == null) {
            beanName = o.get();
        } else {
            String fBean = f.get();
            beanName = StringUtils.isNullOrEmptyString(fBean) //
                    ? o.get()//
                    : fBean;
        }

        return BEANS.findBean(beanName, beanType, null, true);
    }
}
