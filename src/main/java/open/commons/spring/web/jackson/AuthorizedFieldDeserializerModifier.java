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
 * Date  : 2025. 9. 22. 오후 5:10:29
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata;
import open.commons.spring.web.exception.InvalidBeanNameException;
import open.commons.spring.web.utils.BeanUtils;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

/**
 * 
 * @since 2025. 9. 22.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedFieldDeserializerModifier extends BeanDeserializerModifier {

    private final Logger logger = LoggerFactory.getLogger(AuthorizedFieldDeserializerModifier.class);

    /** 메타데이터 형태로 기술된 {@link AuthorizedRequestData} 정보를 제공하는 서비스. */
    private final IAuthorizedRequestDataMetadata authorizedRequestDataMetadata;

    /** 동적으로 bean을 제공 */
    private final BeanUtils BEANS;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 22.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     * @param authorizedRequestDataMetadata
     *            메타데이터 형태로 기술된 {@link AuthorizedRequestData} 정보를 제공하는 서비스.
     *
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedFieldDeserializerModifier(ApplicationContext context, IAuthorizedRequestDataMetadata authorizedRequestDataMetadata) {
        this.BEANS = BeanUtils.context(context);
        this.authorizedRequestDataMetadata = authorizedRequestDataMetadata;
    }

    /**
     *
     * @param type
     * @return
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see org.springframework.beans.BeanUtils#isSimpleValueType(Class)
     */
    private boolean isSimpleType(@Nonnull Class<?> type) {
        return org.springframework.beans.BeanUtils.isSimpleValueType(type) //
                || UUID.class.equals(type);
    }

    /**
     * {@link IAuthorizedRequestDataHandler} 구현한 {@link Bean} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param handleBean
     *            {@link IAuthorizedRequestDataHandler} 를 구현한 {@link Bean} 이름.
     * @return
     * @throws NoSuchBeanDefinitionException
     *             이름에 해당하는 {@link Bean}이 존재하지 않을 경우
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private IAuthorizedRequestDataHandler resolveHandler(String handleBean) throws NoSuchBeanDefinitionException {
        return BEANS.findBean(handleBean, IAuthorizedRequestDataHandler.class, null, true);
    }

    /**
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see com.fasterxml.jackson.databind.deser.BeanDeserializerModifier#updateBuilder(com.fasterxml.jackson.databind.DeserializationConfig,
     *      com.fasterxml.jackson.databind.BeanDescription,
     *      com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder)
     */
    @Override
    public BeanDeserializerBuilder updateBuilder(DeserializationConfig config, BeanDescription beanDesc, BeanDeserializerBuilder builder) {

        List<SettableBeanProperty> toReplace = new ArrayList<>();
        Iterator<SettableBeanProperty> itrProperties = builder.getProperties();

        // POJO 타입 정보
        Class<?> targetClass = beanDesc.getBeanClass();
        // 필드 정보
        String fieldName = null;
        JavaType fieldType = null;
        Class<?> fieldRawType = null;
        // AuthorizedRequestData 관련 정보
        String handleBean = null;
        int handleType = AuthorizedRequestData.NO_ASSINGED_HANDLE_TYPE;
        while (itrProperties.hasNext()) {
            SettableBeanProperty prop = itrProperties.next();
            // BeanPropertyDefinition에서 해당 프로퍼티 찾기
            BeanPropertyDefinition def = findPropertyDef(beanDesc, prop.getName());
            if (def == null) {
                continue;
            }

            // 필드/메서드 등 우선 멤버에서 어노테이션 탐색
            AuthorizedRequestData anno = findAuthorizedAnnotation(def);
            if (anno != null) {
                handleBean = anno.handleBean();
                handleType = anno.handleType();
            } else {
                fieldName = prop.getName();
                // IAuthorizedRequestDataMetadata 에서 정보 획득
                handleBean = this.authorizedRequestDataMetadata.getHandleBeanName(targetClass, fieldName);
                handleType = this.authorizedRequestDataMetadata.getHandleType(targetClass, fieldName);
            }

            if (!validateBeanNameAndHandleType(handleBean, handleType)) {
                if (anno != null) {
                    String errMsg = String.format("'%s.%s'에 대한 '%s' 정보가 설정되어 있지만, 올바르지 않습니다. handleBean=%s, handleType=%s", targetClass.getName(), prop.getType().getRawClass(),
                            AuthorizedRequestData.class.getName(), handleBean, handleBean);
                    logger.error("{}", errMsg);
                    throw new InvalidBeanNameException(errMsg);
                }
                // 메타데이터가 없으면 이 필드는 패스
                continue;
            }

            // 타입 정보
            fieldType = prop.getType();
            fieldRawType = fieldType.getRawClass();
            // 단순 타입
            if (isSimpleType(fieldRawType)) {
                IAuthorizedRequestDataHandler handler = resolveHandler(handleBean);
                JsonDeserializer<Object> customDeser = new AuthorizedFieldDeserializer(handler, handleType);
                toReplace.add(prop.withValueDeserializer(customDeser));
            }
            // Array / Collection
            else if (fieldType.isArrayType() || fieldType.isCollectionLikeType()) {
                JavaType containerType = fieldType.getContentType();
                if (isSimpleType(containerType.getRawClass())) {
                    IAuthorizedRequestDataHandler handler = resolveHandler(handleBean);
                    // delegate 'deserializer'
                    JsonDeserializer<?> wrapper = new ContainerSimpleTypeElementWrappingDeserializer(fieldType, handler, handleType);
                    toReplace.add(prop.withValueDeserializer(wrapper));
                }
            }
            // Map
            else if (fieldType.isMapLikeType()) {
                JavaType mapType = fieldType.getContentType();
                if (isSimpleType(mapType.getRawClass())) {
                    IAuthorizedRequestDataHandler handler = resolveHandler(handleBean);
                    // delegate 'deserializer'
                    JsonDeserializer<?> wrapper = new MapSimpleTypeValueWrappingDeserializer(fieldType, handler, handleType);
                    toReplace.add(prop.withValueDeserializer(wrapper));
                }
            }
        }

        // 교체 적용
        for (SettableBeanProperty p : toReplace) {
            builder.addOrReplaceProperty(p, true);
        }

        return builder;
    }

    /**
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see com.fasterxml.jackson.databind.deser.BeanDeserializerModifier#modifyDeserializer(com.fasterxml.jackson.databind.DeserializationConfig,
     *      com.fasterxml.jackson.databind.BeanDescription, com.fasterxml.jackson.databind.JsonDeserializer)
     */
    // @Override
    // public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc,
    // JsonDeserializer<?> deserializer) {
    //
    // if (!(deserializer instanceof BeanDeserializerBase)) {
    // return deserializer;
    // }
    //
    // // POJO 타입 정보
    // Class<?> targetClass = beanDesc.getBeanClass();
    // // 필드 정보
    // String fieldName = null;
    // JavaType fieldType = null;
    // Class<?> fieldRawType = null;
    // // AuthorizedRequestData 관련 정보
    // String handleBean = null;
    // int handleType = AuthorizedRequestData.NO_ASSINGED_HANDLE_TYPE;
    //
    // BeanDeserializerBase base = (BeanDeserializerBase) deserializer;
    // Iterator<SettableBeanProperty> itrProperties = base.properties();
    //
    // Map<String, SettableBeanProperty> newProps = new LinkedHashMap<>();
    //
    // while (itrProperties.hasNext()) {
    // SettableBeanProperty prop = itrProperties.next();
    // // BeanPropertyDefinition에서 해당 프로퍼티 찾기
    // BeanPropertyDefinition def = findPropertyDef(beanDesc, prop.getName());
    // if (def == null) {
    // continue;
    // }
    //
    // // 필드/메서드 등 우선 멤버에서 어노테이션 탐색
    // AuthorizedRequestData anno = findAuthorizedAnnotation(def);
    // if (anno != null) {
    // handleBean = anno.handleBean();
    // handleType = anno.handleType();
    // } else {
    // fieldName = prop.getName();
    // // IAuthorizedRequestDataMetadata 에서 정보 획득
    // handleBean = this.authorizedRequestDataMetadata.getHandleBeanName(targetClass, fieldName);
    // handleType = this.authorizedRequestDataMetadata.getHandleType(targetClass, fieldName);
    // }
    //
    // if (!validateBeanNameAndHandleType(handleBean, handleType)) {
    // if (anno != null) {
    // String errMsg = String.format("'%s.%s'에 대한 '%s' 정보가 설정되어 있지만, 올바르지 않습니다. handleBean=%s, handleType=%s",
    // targetClass.getName(), prop.getType().getRawClass(),
    // AuthorizedRequestData.class.getName(), handleBean, handleBean);
    // logger.error("{}", errMsg);
    // throw new InvalidBeanNameException(errMsg);
    // }
    // // 메타데이터가 없으면 이 필드는 패스
    // continue;
    // }
    //
    // // 타입 정보
    // fieldType = prop.getType();
    // fieldRawType = fieldType.getRawClass();
    //
    // // 단순 값 → 바로 AuthorizedFieldDeserializer
    // if (isSimpleType(fieldRawType)) {
    // IAuthorizedRequestDataHandler handler = resolveHandler(handleBean);
    // JsonDeserializer<Object> customDeser = new AuthorizedFieldDeserializer(handler, handleType);
    // prop = prop.withValueDeserializer(customDeser);
    // }
    // // Collection/Array element simple type
    // else if (fieldType.isArrayType() || fieldType.isCollectionLikeType()) {
    // JavaType elemType = fieldType.getContentType();
    // if (isSimpleType(elemType.getRawClass())) {
    // IAuthorizedRequestDataHandler handler = resolveHandler(handleBean);
    // // delegate 'deserializer'
    // JsonDeserializer<?> collectionDeserializer = prop.getValueDeserializer();
    // JsonDeserializer<?> wrapper = new ContainerSimpleTypeElementWrappingDeserializer(collectionDeserializer, handler,
    // handleType);
    // prop = prop.withValueDeserializer(wrapper);
    // }
    // }
    // // Map value simple type
    // else if (fieldType.isMapLikeType()) {
    // JavaType valType = fieldType.getContentType();
    // if (isSimpleType(valType.getRawClass())) {
    // IAuthorizedRequestDataHandler handler = resolveHandler(handleBean);
    // // delegate 'deserializer'
    // JsonDeserializer<?> mapDeserializer = prop.getValueDeserializer();
    // JsonDeserializer<?> wrapper = new MapSimpleTypeValueWrappingDeserializer(mapDeserializer, handler, handleType);
    // prop = prop.withValueDeserializer(wrapper);
    // }
    // }
    //
    // newProps.put(prop.getName(), prop);
    // }
    //
    // return base.withBeanProperties(new BeanPropertyMap(true, newProps.values(), null, Locale.getDefault()));
    // }

    /**
     * {@link AuthorizedRequestData}에 사용될 정보를 검증합니다.
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param beanName
     *            {@link AuthorizedRequestData#handleBean()}
     * @param beanType
     *            {@link AuthorizedRequestData#handleType()}
     * @return
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private boolean validateBeanNameAndHandleType(String beanName, int beanType) {
        return !StringUtils.isNullOrEmptyString(beanName) && beanType != AuthorizedRequestData.NO_ASSINGED_HANDLE_TYPE;
    }

    private static AuthorizedRequestData findAuthorizedAnnotation(BeanPropertyDefinition def) {
        // 필드/Setter/Getter 등 우선순위 멤버에서 탐색
        AnnotatedMember m = def.getPrimaryMember();
        AuthorizedRequestData annotation = null;
        return m != null //
                ? (annotation = getAnnotation(def.getField(), AuthorizedRequestData.class)) != null //
                        ? annotation //
                        : (annotation = getAnnotation(def.getSetter(), AuthorizedRequestData.class)) != null //
                                ? annotation //
                                : (annotation = getAnnotation(def.getGetter(), AuthorizedRequestData.class)) != null //
                                        ? annotation //
                                        : null //
                : null;
    }

    private static BeanPropertyDefinition findPropertyDef(BeanDescription beanDesc, String name) {
        return beanDesc.findProperties().stream() //
                .filter(p -> p.getName().equals(name)) //
                .findFirst() //
                .orElse(null);
    }

    private static <A extends Annotation> A getAnnotation(Annotated annotated, Class<A> annoClass) {
        return annotated.getAnnotation(annoClass);
    }
}
