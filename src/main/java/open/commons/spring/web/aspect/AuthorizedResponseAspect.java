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
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;

import open.commons.core.Result;
import open.commons.core.utils.AnnotationUtils;
import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.ac.AuthorizedField;
import open.commons.spring.web.ac.provider.IResponseAccessAuthorityProvider;
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
public class AuthorizedResponseAspect extends AbstractAuthorizedResourceAspect<IResponseAccessAuthorityProvider> {

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
        super(context, IResponseAccessAuthorityProvider.class);
    }

    @Around("withinAllStereotypeComponent() && annotationAuthorizedResponse() ")
    public Object validateAuthorizedMethod(ProceedingJoinPoint pjp) throws Throwable {

        // #1. 메소드 결과괎
        Object result = pjp.proceed();

        logger.trace("result.type={}", result != null ? result.getClass() : null);
        logger.trace("result={}", result);

        // #2. 지원하는 Wrapper 클래스
        // - java.util.Collection
        // - java.util.List
        // - java.util.Map
        // - java.util.Set
        // - open.commons.core.Result
        Class<?> outerClass = result.getClass();
        if (outerClass.isAssignableFrom(Collection.class)) {
            Collection<?> col = (Collection<?>) result;
            col.forEach(this::validateObject);
        } else if (outerClass.isAssignableFrom(Map.class)) {
            Map<?, ?> m = (Map<?, ?>) result;
            m.forEach((k, v) -> validateObject(v));
        } else if (outerClass.isAssignableFrom(Result.class)) {
            Result<?> r = (Result<?>) result;
            validateObject(r.getData());
        } else {
            validateObject(result);
        }

        return result;
    }

    private void validateObject(Object o) {
        if (o == null) {
            return;
        }

        // TODO: 여기서부터 클래스 필드를 하나씩 조사하면서 데이터 검증
        List<Field> fields = AnnotationUtils.getAnnotatedFieldsAllHierarchy(o.getClass(), AuthorizedField.class);
        AuthorizedField anno = null;
        IResponseAccessAuthorityProvider bean = null;
        Result<Boolean> validated = null;
        Object newValue = null;
        for (Field f : fields) {
            anno = f.getAnnotation(AuthorizedField.class);
            bean = getBean(anno.bean());

            validated = bean.isAllowed(anno.op(), anno.roles());
            if (!validated.getResult()) {
                throw ExceptionUtils.newException(InternalServerException.class, "데이터 접근권한 처리 중 오류가 발생하였습니다. 원인=%s", validated.getMessage());
            } else if (!validated.getData()) {
                switch (anno.mode()) {
                    case DENY:
                        throw new UnauthorizedException("올바르지 않은 접근입니다.");
                    case MASK:
                        // TODO: Masking 적용 확인
                        break;
                    case NULLIFY:
                        // TODO: 데이터 타입에 따른 NULL 처리
                        break;
                    default:
                        throw ExceptionUtils.newException(UnsupportedOperationException.class, "지원하지 않는 기능입니다. 원인=%s", anno.mode());
                }
            }
        }
        logger.trace("generic.type={}", o.getClass());
        logger.trace("generic.data={}", o);
    }
}
