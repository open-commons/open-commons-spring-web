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
import open.commons.spring.web.authority.metadata.AuthorizedFieldMetadata;
import open.commons.spring.web.authority.metadata.AuthorizedObjectMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.utils.BeanUtils;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
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

    /** {@link AuthorizedObject}, {@link AuthorizedField} 외부 설정 정보 제공 서비스 */
    private final IAuthorizedResourcesMetadata authorizedResourcesMetadata;
    /** 동적으로 bean을 제공 */
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
     * @param authorizedResourcesMetadata
     *            {@link AuthorizedObject}, {@link AuthorizedField} 외부 설정 정보 제공 서비스
     *
     *
     * @since 2025. 5. 25.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedFieldSerializerModifier(@NotNull ApplicationContext context, IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
        this.BEANS = BeanUtils.context(context);
        this.authorizedResourcesMetadata = authorizedResourcesMetadata;
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

        final AnnotatedClass annoClass = beanDesc.getClassInfo();
        final Class<?> serializedType = annoClass.getAnnotated();
        // #1. 타입에 정의된 어노테이션 조회
        final AuthorizedObject authorizedObject = annoClass.getAnnotation(AuthorizedObject.class);

        // #2. 필드 처리.
        AnnotatedField annoField = null;
        AuthorizedField authorizedField = null;
        IFieldAccessAuthorityProvider authority = null;
        IUnauthorizedFieldHandler unauthorized = null;

        Supplier<String> authorityBeanNameOnObject = null;
        Supplier<String> authorityBeanNameOnField = null;
        Supplier<String> fieldHandleBeanNamOnObject = null;
        Supplier<String> fieldHandleBeanNamOnField = null;
        for (BeanPropertyWriter writer : beanProperties) {
            annoField = beanDesc.findProperties().stream() //
                    .filter(p -> p.getName().equals(writer.getName())) //
                    .map(BeanPropertyDefinition::getField) //
                    .filter(Objects::nonNull) //
                    .findFirst() //
                    .orElse(null);

            // AuthorizedField가 없는 경우
            if (annoField == null) {
                continue;
            }

            String fieldName = annoField.getName();
            authorizedField = annoField.getAnnotation(AuthorizedField.class);
            // 조건1: ao 와 af 가 반드시 있어야 합니다.
            if (authorizedObject != null && authorizedField != null) {
                authorityBeanNameOnObject = authorizedObject::authorityBean;
                authorityBeanNameOnField = authorizedField::authorityBean;
                fieldHandleBeanNamOnObject = authorizedObject::fieldHandleBean;
                fieldHandleBeanNamOnField = authorizedField::fieldHandleBean;
            }
            // serialize 대상 데이터 유형(class)을 기준으로 검색
            else if (this.authorizedResourcesMetadata.isAuthorizedField(serializedType, fieldName)) {
                AuthorizedObjectMetadata aom = this.authorizedResourcesMetadata.getAuthorizedObjectMetadata(serializedType);
                authorityBeanNameOnObject = () -> aom.getAuthorityBean();
                fieldHandleBeanNamOnObject = () -> aom.getFieldHandleBean();

                AuthorizedFieldMetadata afm = this.authorizedResourcesMetadata.getAuthorizedFieldMetadata(serializedType, fieldName);
                authorityBeanNameOnField = () -> afm.getAuthorityBean();
                fieldHandleBeanNamOnField = () -> afm.getFieldHandleBean();

            } // 실제 field가 선언된 데이터 유형(class)을 기준으로 검색
            else if (this.authorizedResourcesMetadata.isAuthorizedField(annoField.getDeclaringClass(), fieldName)) {
                Class<?> declaringClass = annoField.getDeclaringClass();

                AuthorizedObjectMetadata aom = this.authorizedResourcesMetadata.getAuthorizedObjectMetadata(declaringClass);
                authorityBeanNameOnObject = () -> aom.getAuthorityBean();
                fieldHandleBeanNamOnObject = () -> aom.getFieldHandleBean();

                AuthorizedFieldMetadata afm = this.authorizedResourcesMetadata.getAuthorizedFieldMetadata(declaringClass, fieldName);
                authorityBeanNameOnField = () -> afm.getAuthorityBean();
                fieldHandleBeanNamOnField = () -> afm.getFieldHandleBean();
            } else {
                continue;
            }

            authority = getBean(IFieldAccessAuthorityProvider.class, authorityBeanNameOnObject, authorityBeanNameOnField);
            unauthorized = getBean(IUnauthorizedFieldHandler.class, fieldHandleBeanNamOnObject, fieldHandleBeanNamOnField);

            writer.assignSerializer(new AuthorizedFieldSerializer(serializedType, annoField, authority, unauthorized, this.authorizedResourcesMetadata));
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
