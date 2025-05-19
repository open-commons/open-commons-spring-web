/*rmO !
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
 * Date  : 2025. 5. 19. 오후 1:13:50
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import open.commons.core.Result;
import open.commons.spring.web.ac.AuthorizedRequest;
import open.commons.spring.web.ac.provider.IRequestAccessAuthorityProvider;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.servlet.UnauthorizedException;

/**
 * REST API 메소드에 대한 접근권한을 중개합니다.
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Aspect
@Order(AbstractAuthorizedResourceAspect.ORDER_REQUEST)
public class AuthorizedRequestAspect extends AbstractAuthorizedResourceAspect<IRequestAccessAuthorityProvider> {

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
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedRequestAspect(@NotNull ApplicationContext context) {
        super(context, IRequestAccessAuthorityProvider.class);
    }

    /**
     * 메소드에 정의된 {@link HttpMethod} 어노테이션에서 경로 정보를 찾아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param <A>
     * @param method
     * @return
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    private <A extends Annotation> String pathOnMethod(Method method) {
        try {
            // Aspect 설정 조건에서 아래 XXXMapping 중에 반드시 1개는 설정이 되기 때문에 null 확인을 하지 않음.
            Class<?>[] annoTypes = { DeleteMapping.class, GetMapping.class, PatchMapping.class, PostMapping.class, PutMapping.class, RequestMapping.class };
            A anno = Stream.of(annoTypes) //
                    .map(hm -> AnnotationUtils.findAnnotation(method, (Class<A>) hm)) //
                    .filter(a -> a != null) //
                    .findAny().get();
            // XXXMapping 어노테이션은 value() 메소드를 통해서 '경로' 정보를 제공함.
            Method value = anno.getClass().getMethod("value");
            String path = String.join("", (String[]) value.invoke(anno));

            logger.trace("method={}, anno={}, path={}", method, anno, path);

            return path;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new InternalServerException(e);
        }
    }

    /**
     * 타입에 정의된 {@link HttpMethod} 어노테이션에서 경로 정보를 찾아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param <A>
     * @param type
     * @return
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private <A extends Annotation> String pathOnType(Class<?> type) {
        RequestMapping anno = AnnotationUtils.findAnnotation(type, RequestMapping.class);
        if (anno != null) {
            String path = String.join("", anno.value());
            logger.trace("type={}, anno={}, path={}", type, anno, path);
            return path;
        } else {
            return "";
        }
    }

    /**
     * REST API 요청에 대한 접근권한을 처리합니다.<br>
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
     * @see AbstractAuthorizedResourceAspect#withinControllerStereotypeComponent()
     * @see AbstractAuthorizedResourceAspect#annotationAuthorizedRequest()
     * @see AbstractAuthorizedResourceAspect#withinAuthorizedRequest()
     * @see AbstractAuthorizedResourceAspect#annotationAllRequestMapping()
     * @see AbstractAuthorizedResourceAspect#withinRequestMapping()
     */
    @Around("withinControllerStereotypeComponent()" //
            + " && ( annotationAuthorizedRequest() || withinAuthorizedRequest() )" //
            + " && annotationAllRequestMapping()" //
            + " && ( withinRequestMapping() || withinAuthorizedRequest() ) ")
    public Object validateAuthorizedRequest(ProceedingJoinPoint pjp) throws Throwable {

        Object target = pjp.getTarget();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();

        logger.trace("method.signature={}", pjp.getSignature());
        logger.trace("target={}", pjp.getTarget());

        StringBuilder pathBuilder = new StringBuilder();
        // #1. 타입에 정의된 최상위 경로, Nullable
        String pathOnType = pathOnType(target.getClass());
        pathBuilder.append(pathOnType);
        // 메소드에 정의된 세부 경로, NotNull
        String pathOnMethod = pathOnMethod(method);
        pathBuilder.append(pathOnMethod);

        AuthorizedRequest annotation = decideAnnotation(AuthorizedRequest.class, target.getClass(), method);
        String providerName = annotation.bean();
        IRequestAccessAuthorityProvider provider = getProvider(providerName);

        Result<Boolean> validated = provider.isAllowed(pathBuilder.toString());
        if (!validated.getResult() || !validated.getData()) {
            throw new UnauthorizedException("올바르지 않은 접근입니다.");
        }

        return pjp.proceed();
    }
}
