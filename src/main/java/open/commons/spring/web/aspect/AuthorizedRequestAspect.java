/*rmO !
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
import org.springframework.web.bind.annotation.RequestMethod;

import open.commons.core.Result;
import open.commons.core.TwoValueObject;
import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.authority.AuthorizedRequest;
import open.commons.spring.web.beans.authority.IRequestAccessAuthorityProvider;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.servlet.UnauthorizedAccessException;

/**
 * REST API 메소드에 대한 접근권한을 중개합니다.
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Aspect
@Order(AspectOrder.AUTHROIZED_REQUEST)
public class AuthorizedRequestAspect extends AbstractAuthorizedResourceAspect<IRequestAccessAuthorityProvider> {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		parkjunhong77@gmail.com			최초 작성
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
     * 메소드에 정의된 {@link RequestMapping} 및 확장 어노테이션 ({@link DeleteMapping}, {@link GetMapping}, {@link PatchMapping},
     * {@link PostMapping}, {@link PutMapping}) 에서에서 경로 정보를 찾아 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		parkjunhong77@gmail.com			최초 작성
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
    private <A extends Annotation> TwoValueObject<RequestMethod, String> mappingOnMethod(Method method) {
        try {
            // Aspect 설정 조건에서 아래 XXXMapping 중에 반드시 1개는 설정이 되기 때문에 null 확인을 하지 않음.
            Class<?>[] annoTypes = { DeleteMapping.class, GetMapping.class, PatchMapping.class, PostMapping.class, PutMapping.class, RequestMapping.class };

            TwoValueObject<RequestMethod, A> httpRequest = Stream.of(annoTypes) //
                    .map(hm -> {
                        A realAnno = AnnotationUtils.getAnnotation(method, (Class<A>) hm);
                        if (realAnno == null) {
                            return null;
                        }

                        RequestMapping reqMapping = AnnotationUtils.findAnnotation(realAnno.getClass(), RequestMapping.class);
                        RequestMethod reqMethod = reqMapping.method()[0];

                        return new TwoValueObject<RequestMethod, A>(reqMethod, realAnno);
                    }) //
                    .filter(a -> a != null) //
                    .findAny().get();

            // #1. 요청 Method
            RequestMethod requestMethod = httpRequest.first;
            // #2. URL 정보. XXXMapping 어노테이션은 value() 메소드를 통해서 '경로' 정보를 제공함.
            A anno = httpRequest.second;
            Method mValue = anno.getClass().getMethod("value");
            String path = String.join("", (String[]) mValue.invoke(anno));

            logger.trace("method={}, anno={}, req.method={}, req.path={}", method, anno, requestMethod, path);

            return new TwoValueObject<RequestMethod, String>(httpRequest.first, path);
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
     * 2025. 5. 19.		parkjunhong77@gmail.com			최초 작성
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
    private <A extends Annotation> String mappingOnType(Class<?> type) {
        RequestMapping anno = AnnotationUtils.getAnnotation(type, RequestMapping.class);
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
     * 2025. 5. 19.		parkjunhong77@gmail.com			최초 작성
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
     * @see AbstractAuthorizedResourceAspect#withinAllControllerStereotypeComponent()
     * @see AbstractAuthorizedResourceAspect#annotationAuthorizedRequest()
     * @see AbstractAuthorizedResourceAspect#withinAuthorizedRequest()
     * @see AbstractAuthorizedResourceAspect#annotationAllRequestMapping()
     * 
     * @see open.commons.spring.web.aspect.IAuthorizedResource#validateAuthorizedResource(org.aspectj.lang.ProceedingJoinPoint)
     */
    @Around("withinAllControllerStereotypeComponent()" //
            + " && ( annotationAuthorizedRequest() || withinAuthorizedRequest() )" //
            + " && annotationAllRequestMapping()" //
    )
    // + " && ( withinRequestMapping() || withinAuthorizedRequest() ) ")
    @Override
    public Object validateAuthorizedResource(ProceedingJoinPoint pjp) throws Throwable {

        Object target = pjp.getTarget();
        Method invokedMethod = ((MethodSignature) pjp.getSignature()).getMethod();

        logger.trace("method.signature={}", pjp.getSignature());
        logger.trace("target={}", pjp.getTarget());

        StringBuilder pathBuilder = new StringBuilder();
        // #1. 타입에 정의된 최상위 경로, Nullable
        String pathOnType = mappingOnType(target.getClass());
        pathBuilder.append(findConfigurationValue(pathOnType));
        // 메소드에 정의된 세부 경로, NotNull
        TwoValueObject<RequestMethod, String> mappingOnMethod = mappingOnMethod(invokedMethod);
        RequestMethod httpMethod = mappingOnMethod.first;
        String pathOnMethod = mappingOnMethod.second;
        pathBuilder.append(findConfigurationValue(pathOnMethod));

        AuthorizedRequest annotation = decideAnnotation(AuthorizedRequest.class, target.getClass(), invokedMethod);
        String beanName = annotation.authorityBean();
        IRequestAccessAuthorityProvider bean = getAuthorityBean(beanName);

        Result<Boolean> validated = bean.isAllowed(httpMethod, pathBuilder.toString());
        if (validated == null) {
            throw ExceptionUtils.newException(InternalServerException.class,
                    "REST API 접근에 대한 판단은 'null'일 수가 없습니다. 원인=open.commons.spring.web.beans.authority.IRequestAccessAuthorityProvider.isAllowed(RequestMethod, String) 구현이 올바르지 않습니다.");
        } else if (!validated.getResult() || !validated.getData()) {
            throw new UnauthorizedAccessException("올바르지 않은 접근입니다.");
        }

        return pjp.proceed();
    }
}
