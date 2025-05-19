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

import java.lang.reflect.Method;

import javax.validation.constraints.NotNull;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;

import open.commons.spring.web.ac.AuthorizedResponse;
import open.commons.spring.web.ac.provider.IResponseAccessAuthorityProvider;

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

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        AuthorizedResponse responseAnno = AnnotationUtils.findAnnotation(method, AuthorizedResponse.class);

        // #1. 메소드 결과괎
        Object result = pjp.proceed();

        // #2. 지원하는 Wrapper 클래스
        // - java.util.Collection
        // - java.util.List
        // - java.util.Map
        // - java.util.Set
        // - open.commons.core.Result

        logger.trace("result.type={}", result != null ? result.getClass() : null);
        logger.trace("result={}", result);

        return result;
    }
}
