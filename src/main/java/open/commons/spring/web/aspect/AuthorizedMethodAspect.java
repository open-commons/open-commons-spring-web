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
 * Date  : 2025. 5. 16. 오후 4:53:59
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
import org.springframework.core.annotation.Order;

import open.commons.core.Result;
import open.commons.spring.web.ac.AuthorizedMethod;
import open.commons.spring.web.ac.provider.IMethodAccessAuthorityProvider;
import open.commons.spring.web.servlet.UnauthorizedException;

/**
 * 메소드에 대한 접근권한을 중개합니다.
 * 
 * @since 2025. 5. 16.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @see AuthorizedMethod
 */
@Aspect
@Order(AbstractAuthorizedResourceAspect.ORDER_METHOD)
public class AuthorizedMethodAspect extends AbstractAuthorizedResourceAspect<IMethodAccessAuthorityProvider> {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 16.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedMethodAspect(@NotNull ApplicationContext context) {
        super(context, IMethodAccessAuthorityProvider.class);
    }

    /**
     * 메소드에 대한 접근권한을 처리합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
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
     * @see AbstractAuthorizedResourceAspect#annotationAuthorizedMethod()
     * @see AbstractAuthorizedResourceAspect#withinAuthorizedMethod()
     * 
     * @see open.commons.spring.web.aspect.IAuthorizedResource#validateAuthorizedResource(org.aspectj.lang.ProceedingJoinPoint)
     */
    @Around("withinAllStereotypeComponent() && ( annotationAuthorizedMethod() || withinAuthorizedMethod() )")
    @Override
    public Object validateAuthorizedResource(ProceedingJoinPoint pjp) throws Throwable {

        Object target = pjp.getTarget();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        // 타입과 메소드에 모두 AuthorizedMethod 가 설정된 경우 메소드에 설정된 어노테이션을 사용함.
        AuthorizedMethod annotation = decideAnnotation(AuthorizedMethod.class, target.getClass(), method);
        String beanName = annotation.authorityBean();
        IMethodAccessAuthorityProvider bean = getAuthorityBean(beanName);

        logger.trace("annotation={}", annotation);
        logger.trace("provider={}", bean);

        if (annotation.roles().length < 1) {
            throw new UnauthorizedException("올바르지 않은 접근입니다.");
        }

        Result<Boolean> validated = bean.isAllowed(annotation.op(), annotation.roles());
        if (!validated.getResult() || !validated.getData()) {
            throw new UnauthorizedException("올바르지 않은 접근입니다.");
        }

        return pjp.proceed();
    }
}
