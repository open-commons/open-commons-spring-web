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

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.ac.AuthorizedMethod;
import open.commons.spring.web.ac.IResourceAccessAuthorityProvider;
import open.commons.spring.web.servlet.BadRequestException;

/**
 * 
 * @since 2025. 5. 16.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Aspect
@Component
// @ConditionalOnBean(IResourceAccessAuthorityProvider.class)
public class AuthorizedResourceAspect {

    private final Logger logger = LoggerFactory.getLogger(AuthorizedResourceAspect.class);

    private ApplicationContext context;

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
    public AuthorizedResourceAspect(ApplicationContext context) {
        this.context = context;
    }

    /**
     * 처리할 어노테이션 정의 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 16.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("@annotation(open.commons.spring.web.ac.AuthorizedMethod)")
    public void hasAuthorizedMethodAnnotation() {
    }

    /**
     * Bean 등록되는 클래스 범위 정의 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 16.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("(" //
            + "@target(org.springframework.stereotype.Controller)" //
            + " || @target(org.springframework.stereotype.Service)" //
            + " || @target(org.springframework.stereotype.Repository)" //
            + " || @target(org.springframework.web.bind.annotation.RestController)" //
            + ") ")
    public void targetComponentAnnotation() {
    }

    @Around("targetComponentAnnotation() && hasAuthorizedMethodAnnotation()")
    public Object validateAuthorizedMethos(ProceedingJoinPoint pjp) throws Throwable {

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        AuthorizedMethod annotation = method.getAnnotation(AuthorizedMethod.class);

        String providerName = annotation.provider();
        IResourceAccessAuthorityProvider provider = null;
        if (StringUtils.isNullOrEmptyString(providerName)) {
            provider = (IResourceAccessAuthorityProvider) this.context.getBean(IResourceAccessAuthorityProvider.class);
        } else {
            provider = (IResourceAccessAuthorityProvider) this.context.getBean(providerName);
        }

        if (!provider.isAllowed(annotation.op(), annotation.roles()).getResult()) {
            throw new BadRequestException();
        }

        return pjp.proceed();
    }

}
