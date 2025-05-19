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
 * Date  : 2025. 5. 19. 오전 10:51:11
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import open.commons.core.utils.AnnotationUtils;
import open.commons.core.utils.StringUtils;

/**
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractAuthorizedResourceAspect<T> {

    public static final int ORDER_METHOD = Integer.MIN_VALUE;
    public static final int ORDER_REQUEST = Integer.MIN_VALUE;
    public static final int ORDER_RESPONSE = 2;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final ApplicationContext context;

    protected final Class<T> providerType;

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
     *            스프링 프레임워크 컨텍스트 제공자
     * @param providerType
     *            권한 제공자 유형
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractAuthorizedResourceAspect(@NotNull ApplicationContext context, Class<T> providerType) {
        this.context = context;
        this.providerType = providerType;
    }

    @Pointcut("(" //
            + "@annotation(org.springframework.web.bind.annotation.DeleteMapping)" //
            + " || @annotation(org.springframework.web.bind.annotation.GetMapping)" //
            + " || @annotation(org.springframework.web.bind.annotation.PatchMapping)" //
            + " || @annotation(org.springframework.web.bind.annotation.PostMapping)" //
            + " || @annotation(org.springframework.web.bind.annotation.PutMapping)" //
            + " || @annotation(org.springframework.web.bind.annotation.RequestMapping)" //
            + ")")
    public final void annotationAllRequestMapping() {
    }

    @Pointcut("@annotation(open.commons.spring.web.ac.AuthorizedMethod)")
    public final void annotationAuthorizedMethod() {
    }

    @Pointcut("@annotation(open.commons.spring.web.ac.AuthorizedRequest)")
    public final void annotationAuthorizedRequest() {
    }

    @Pointcut("@annotation(open.commons.spring.web.ac.AuthorizedResponse)")
    public final void annotationAuthorizedResponse() {
    }

    /**
     * 타입과 메소드에 모두 동일한 어노테이션이 설정되어 있는 경우를 고려하여 사용할 어노테이션을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 19.     박준홍         최초 작성
     * </pre>
     *
     * @param <A>
     * @param annoType
     *            어노테이션 유형
     * @param o
     *            객체 타입
     * @param m
     *            호출된 메소드
     * @return
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected final <A extends Annotation> A decideAnnotation(Class<A> annoType, Class<?> o, Method m) {
        A annoM = (A) AnnotationUtils.getAnnotation(m, annoType);
        return annoM != null //
                ? annoM //
                : AnnotationUtils.getAnnotation(o, annoType);
    }

    /**
     * 주어진 이름에 해당하는 Bean 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param beanName
     *            Bean 타입.
     * @return
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    protected final T getBean(@NotEmpty String beanName) {
        T bean = null;
        if (StringUtils.isNullOrEmptyString(beanName)) {
            bean = this.context.getBean(providerType);
        } else {
            bean = (T) this.context.getBean(beanName);
        }

        return bean;
    }

    @Pointcut("(" //
            + "@within(org.springframework.stereotype.Controller)" //
            + " || @within(org.springframework.web.bind.annotation.RestController)" //
            + " || @within(org.springframework.stereotype.Component)" //
            + " || @within(org.springframework.stereotype.Service)" //
            + " || @within(org.springframework.stereotype.Repository)" //
            + ") ")
    public final void withinAllStereotypeComponent() {
    }

    @Pointcut("@within(open.commons.spring.web.ac.AuthorizedMethod)")
    public final void withinAuthorizedMethod() {
    }

    @Pointcut("@within(open.commons.spring.web.ac.AuthorizedRequest)")
    public final void withinAuthorizedRequest() {
    }

    @Pointcut("@within(org.springframework.stereotype.Component)")
    public final void withinComponentStereotypeComponent() {
    }

    @Pointcut("(" //
            + "@within(org.springframework.stereotype.Controller)" //
            + " || @within(org.springframework.web.bind.annotation.RestController)" //
            + ") ")
    public final void withinControllerStereotypeComponent() {
    }

    @Pointcut("@within(org.springframework.stereotype.Repository)")
    public final void withinRepositoryStereotypeComponent() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RequestMapping)")
    public final void withinRequestMapping() {
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public final void withinServiceStereotypeComponent() {
    }
}
