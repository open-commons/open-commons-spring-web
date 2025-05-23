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
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;

import open.commons.spring.web.utils.BeanUtils;

/**
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractAuthorizedResourceAspect<T> implements IAuthorizedResource<T> {

    public static final int ORDER_METHOD = Ordered.HIGHEST_PRECEDENCE;
    public static final int ORDER_REQUEST = Ordered.HIGHEST_PRECEDENCE;
    public static final int ORDER_RESPONSE = Ordered.HIGHEST_PRECEDENCE + 1;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final ApplicationContext context;

    protected final BeanUtils BEAN_UTILS;

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
        this.BEAN_UTILS = BeanUtils.context(context);
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
     *            Bean 이름.
     * @return Bean 이름에 해당하는 Bean 객체. Bean 이름이 비어 있는 경우 {@link #providerType} 에 해당하는 Bean
     *
     * @throws NoSuchBeanDefinitionException
     *             Bean 이름에 해당하는 Bean이 존재하지 않는 경우
     * @throws BeanNotOfRequiredTypeException
     *             Bean 이름과 Bean 타입이 일치하지 않는 경우
     * @throws BeansException
     *             Bean을 생성할 수 없는 경우
     * 
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected final T getAuthorityBean(@NotEmpty String beanName) throws BeansException {
        return BEAN_UTILS.getBean(beanName, providerType, null, true);
    }

    /**
     * 주어진 이름에 해당하는 Bean 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
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
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected final <B> B getBean(@NotEmpty String beanName, Class<B> beanType, B defaultBean, boolean required) throws BeansException {
        return BEAN_UTILS.getBean(beanName, beanType, defaultBean, required);
    }

    /**
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
     * @param beanImplType
     *            Bean 이름이 비어있는 경우, {beanType}에 해당하는 사용자 정의 bean이 없는 경우 제공할 bean 구현 클래스. (일반적으로 시스템 Bean을 제공할 목적으로 사용)
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
     */
    protected final <I, E extends I> I getBean(@NotEmpty String beanName, Class<I> beanType, Class<E> beanImplType, boolean required) throws BeansException {
        return BEAN_UTILS.findBean(beanName, beanType, beanImplType, required);
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
    public final void withinAllControllerStereotypeComponent() {
    }

    @Pointcut("@within(org.springframework.stereotype.Repository)")
    public final void withinRepositoryStereotypeComponent() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RequestMapping)")
    public final void withinRequestMapping() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public final void withinRestControllerComponent() {
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public final void withinServiceStereotypeComponent() {
    }
}
