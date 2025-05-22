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
 * Date  : 2025. 5. 20. 오전 11:17:20
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import open.commons.core.Result;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.ReflectionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.ac.AuthorizedField;
import open.commons.spring.web.ac.AuthorizedField.AuthorizedFieldModeHandle;
import open.commons.spring.web.ac.AuthorizedResponse;
import open.commons.spring.web.ac.provider.IFieldAccessAuthorityProvider;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.servlet.UnauthorizedException;
import open.commons.spring.web.utils.BeanUtils;

/**
 * {@link AuthorizedResponse}에 설정된 Bean 유형의 내장 클래스.
 * 
 * @since 2025. 5. 20.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class DefaultAuthorizedResponseHandler implements IAuthorizedResponseHandler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final ApplicationContext context;

    protected final BeanUtils BEAN_UTILS;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public DefaultAuthorizedResponseHandler(@NotNull ApplicationContext context) {
        this.context = context;
        this.BEAN_UTILS = BeanUtils.context(context);
    }

    /**
     * 주어진 이름에 해당하는 Bean 객체를 제공합니다. <br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <B>
     * @param beanName
     *            Bean 이름
     * @param beanType
     *            Bean 유형
     * @param defaultBean
     *            Bean 이름이 비어있는 경우 기본값.
     * @param required
     *            Bean 객체 반환 필수 여부
     * 
     * @return Bean 이름에 해당하는 Bean 객체. <br>
     *         Bean 이름이 비어 있는 경우 기본값을 반환. 단, 기본값이 null 인 경우 <code>beanType(Bean 유형)</code> 에 해당하는 Bean
     * 
     * @throws NoSuchBeanDefinitionException
     *             Bean 이름에 해당하는 Bean이 존재하지 않는 경우
     * @throws BeanNotOfRequiredTypeException
     *             Bean 이름과 Bean 타입이 일치하지 않는 경우
     * @throws BeansException
     *             Bean을 생성할 수 없는 경우
     *
     * @since 2025. 5. 21.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see ApplicationContext#getBean(String)
     * @see ApplicationContext#getBean(String, Class)
     */
    protected final <B> B getBean(@NotEmpty String beanName, Class<B> beanType, B defaultBean, boolean required) throws BeansException {
        B bean = null;
        try {
            if (StringUtils.isNullOrEmptyString(beanName)) {
                bean = defaultBean != null //
                        ? defaultBean //
                        : this.context.getBean(beanType);
            } else {
                bean = this.context.getBean(beanName, beanType);
            }
        } catch (BeansException e) {
            if (required) {
                throw e;
            }
        }

        return bean;
    }

    /**
     *
     * @since 2025. 5. 21.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     *
     * @see open.commons.spring.web.beans.IAuthorizedResponseHandler#handle(java.lang.Object)
     */
    @Override
    public Object handle(Object o) throws Throwable {
        return watchAndValidate(o);
    }

    private void validateObject(Object o) throws IllegalArgumentException, IllegalAccessException {
        if (o == null) {
            return;
        }

        // #1. 타입 수준에서 정의한 권한제한 필드 처리 조회
        AuthorizedFieldModeHandle annoModeHandle = AnnotationUtils.findAnnotation(o.getClass(), AuthorizedFieldModeHandle.class);
        IUnauthorizedFieldHandler typeModeHandle = null;
        if (annoModeHandle != null) {
            typeModeHandle = getBean(annoModeHandle.bean(), DefaultUnauthorizedFieldHandler.class, null, true);
        }

        // #2. 모든 필드 조회
        Map<Field, AuthorizedField> fields = ReflectionUtils.getAllAnnotatedFields(o, AuthorizedField.class);

        Field field = null;
        AuthorizedField fieldAnno = null;
        IFieldAccessAuthorityProvider fieldAuthorityBean = null;
        Result<Boolean> validated = null;
        IUnauthorizedFieldHandler fieldModeHandle = null;

        for (Entry<Field, AuthorizedField> entry : fields.entrySet()) {
            field = entry.getKey();
            fieldAnno = entry.getValue();

            if (fieldAnno.roles().length < 1) {
                throw new UnauthorizedException("올바르지 않은 접근입니다.");
            }

            fieldAuthorityBean = BEAN_UTILS.getBean(fieldAnno.authorityBean(), IFieldAccessAuthorityProvider.class, null, true);
            validated = fieldAuthorityBean.isAllowed(fieldAnno.op(), fieldAnno.roles());

            if (!validated.getResult()) {
                throw ExceptionUtils.newException(InternalServerException.class, "데이터 접근권한 처리 중 오류가 발생하였습니다. 원인=%s", validated.getMessage());
            } else if (!validated.getData()) {
                fieldModeHandle = BEAN_UTILS.getBean(fieldAnno.modeHandleBean(), IUnauthorizedFieldHandler.class, typeModeHandle, false);
                switch (fieldAnno.mode()) {
                    case DENY:
                        fieldModeHandle.deny(o, field);
                        break;
                    case MASK:
                        fieldModeHandle.mask(o, field, fieldAnno.masking());
                        break;
                    case NULLIFY:
                        fieldModeHandle.nullify(o, field);
                        break;
                    default:
                        throw ExceptionUtils.newException(UnsupportedOperationException.class, "지원하지 않는 기능입니다. 원인=%s", fieldAnno.mode());
                }
            }
        }
    }

    private Object watchAndValidate(Object o) throws Throwable {
        // # 지원하는 Wrapper 클래스
        // - java.util.Collection
        // - java.util.List
        // - java.util.Map
        // - java.util.Set
        // - open.commons.core.Result
        Class<?> outerClass = o.getClass();
        if (Collection.class.isAssignableFrom(outerClass)) {
            Collection<?> col = (Collection<?>) o;
            for (Object e : col) {
                watchAndValidate(e);
            }
        } else if (Map.class.isAssignableFrom(outerClass)) {
            Map<?, ?> m = (Map<?, ?>) o;
            for (Entry<?, ?> e : m.entrySet()) {
                watchAndValidate(e.getValue());
            }
        } else if (Result.class.isAssignableFrom(outerClass)) {
            Result<?> r = (Result<?>) o;
            watchAndValidate(r.getData());
        } else {
            validateObject(o);
        }
        return o;
    }

}
