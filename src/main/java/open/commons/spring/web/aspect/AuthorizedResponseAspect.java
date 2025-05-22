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
import open.commons.spring.web.ac.provider.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.DefaultAuthorizedResponseHandler;
import open.commons.spring.web.beans.IAuthorizedResponseHandler;

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

    /**
     * 응답 데이터에 대한 접근권한을 처리합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 19.     박준홍         최초 작성
     * </pre>
     *
     * @param pjp
     * @return
     * @throws Throwable
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see AbstractAuthorizedResourceAspect#withinAllStereotypeComponent()
     * @see AbstractAuthorizedResourceAspect#annotationAuthorizedResponse()
     *
     * @see open.commons.spring.web.aspect.IAuthorizedResource#validateAuthorizedResource(org.aspectj.lang.ProceedingJoinPoint)
     */
    @Around("withinAllStereotypeComponent() && annotationAuthorizedResponse()")
    @Override
    public Object validateAuthorizedResource(ProceedingJoinPoint pjp) throws Throwable {

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        // #1. 메소드 결과괎
        Object result = pjp.proceed();

        logger.trace("result.type={}", result != null ? result.getClass() : null);
        logger.trace("result={}", result);

        // #2. 메소드에 설정된 어노테이션
        AuthorizedResponse annoRes = AnnotationUtils.getAnnotation(method, AuthorizedResponse.class);
        // 데이터를 처리하는 Bean
        IAuthorizedResponseHandler handler = getBean(annoRes.dataHandleBean(), IAuthorizedResponseHandler.class, DefaultAuthorizedResponseHandler.class, true);
        return handler.handle(result);
    }
}
