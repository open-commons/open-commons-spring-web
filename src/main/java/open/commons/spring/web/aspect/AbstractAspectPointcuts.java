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
 * Date  : 2025. 6. 23. 오전 10:59:58
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.aspect;

import javax.validation.constraints.NotEmpty;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import open.commons.spring.web.utils.BeanUtils;

/**
 * AOP를 적용하기 위해서 공통적으로 제공하는 {@link Pointcut} 제공.
 * 
 * @param <T>
 * @since 2025. 6. 23.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractAspectPointcuts {

    protected final Logger logger;
    protected final ApplicationContext context;
    protected final BeanUtils BEAN_UTILS;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractAspectPointcuts(ApplicationContext context) {
        this(context, null);
    }

    public AbstractAspectPointcuts(ApplicationContext context, Logger logger) {
        this.context = context;
        this.BEAN_UTILS = BeanUtils.context(context);
        this.logger = logger != null ? logger : LoggerFactory.getLogger(getClass());
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
            + ") ")
    public final void withinAllControllerStereotypeComponent() {
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

    @Pointcut("(" //
            + " @within(org.springframework.stereotype.Component)" //
            + " || @within(org.springframework.stereotype.Service)" //
            + " || @within(org.springframework.stereotype.Repository)" //
            + ") ")
    public final void withinAllStereotypeComponentExceptController() {
    }

    @Pointcut("@within(org.springframework.stereotype.Component)")
    public final void withinComponentStereotypeComponent() {
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