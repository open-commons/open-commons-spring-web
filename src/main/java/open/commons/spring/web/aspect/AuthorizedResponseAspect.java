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
 * Date  : 2025. 5. 19. 오후 5:59:28
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.constraints.NotNull;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;

import open.commons.core.Result;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.ReflectionUtils;
import open.commons.spring.web.ac.AuthorizedField;
import open.commons.spring.web.ac.AuthorizedField.AuthorizedFieldModeHandle;
import open.commons.spring.web.ac.AuthorizedResponse;
import open.commons.spring.web.ac.provider.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.AbstractResponseDataHandler;
import open.commons.spring.web.beans.DefaultUnauthorizedFieldHandler;
import open.commons.spring.web.beans.IUnauthorizedFieldHandler;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.servlet.UnauthorizedException;

/**
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Aspect
@Order(AbstractAuthorizedResourceAspect.ORDER_RESPONSE)
public class AuthorizedResponseAspect extends AbstractAuthorizedResourceAspect<IFieldAccessAuthorityProvider> {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedResponseAspect(@NotNull ApplicationContext context) {
        super(context, IFieldAccessAuthorityProvider.class);
    }

    @Around("withinAllStereotypeComponent() && annotationAuthorizedResponse() ")
    public Object validateAuthorizedMethod(ProceedingJoinPoint pjp) throws Throwable {

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        // #1. 메소드 결과괎
        Object result = pjp.proceed();

        logger.trace("result.type={}", result != null ? result.getClass() : null);
        logger.trace("result={}", result);

        // #2. 메소드에 설정된 어노테이션
        AuthorizedResponse annoRes = AnnotationUtils.getAnnotation(method, AuthorizedResponse.class);
        // 데이터를 처리하는 Bean
        String beanName = annoRes.dataHandleBean();
        if (!beanName.isEmpty()) {
            AbstractResponseDataHandler handler = this.context.getBean(beanName, AbstractResponseDataHandler.class);
            result = handler.handle(result);
        } else {

            // #2. 지원하는 Wrapper 클래스
            // - java.util.Collection
            // - java.util.List
            // - java.util.Map
            // - java.util.Set
            // - open.commons.core.Result
            Class<?> outerClass = result.getClass();
            if (outerClass.isAssignableFrom(Collection.class)) {
                Collection<?> col = (Collection<?>) result;
                for (Object o : col) {
                    validateObject(o);
                }
            } else if (outerClass.isAssignableFrom(Map.class)) {
                Map<?, ?> m = (Map<?, ?>) result;
                for (Entry<?, ?> e : m.entrySet()) {
                    validateObject(e.getValue());
                }
            } else if (outerClass.isAssignableFrom(Result.class)) {
                Result<?> r = (Result<?>) result;
                validateObject(r.getData());
            } else {
                validateObject(result);
            }
        }
        return result;
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
            fieldAuthorityBean = getAuthorityBean(fieldAnno.authorityBean());

            if (fieldAnno.roles().length < 1) {
                throw new UnauthorizedException("올바르지 않은 접근입니다.");
            }

            validated = fieldAuthorityBean.isAllowed(fieldAnno.op(), fieldAnno.roles());
            if (!validated.getResult()) {
                throw ExceptionUtils.newException(InternalServerException.class, "데이터 접근권한 처리 중 오류가 발생하였습니다. 원인=%s", validated.getMessage());
            } else if (!validated.getData()) {
                fieldModeHandle = getBean(fieldAnno.modeHandleBean(), IUnauthorizedFieldHandler.class, typeModeHandle, false);
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
        logger.trace("generic.type={}", o.getClass());
        logger.trace("generic.data={}", o);
    }
}
