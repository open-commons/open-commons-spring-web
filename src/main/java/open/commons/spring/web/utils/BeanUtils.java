/*
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
 * Date  : 2025. 5. 21. 오후 1:37:11
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.exception.IllegalBeanNameFqnResolveException;

/**
 * Bean 관련 기능 제공.
 * 
 * @since 2025. 5. 21.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class BeanUtils {

    protected final ApplicationContext context;

    private BeanUtils(ApplicationContext context) {
        this.context = context;
    }

    public ApplicationContext context() {
        return this.context;
    }

    /**
     * 주어진 이름에 해당하는 Bean 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
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
     * 
     * @see ApplicationContext#getBean(String)
     * @see ApplicationContext#getBean(String, Class)
     * @see ApplicationContext#getBeansOfType(Class)
     * @see #findExplicitBean(Class)
     */
    public final <T, E extends T> T findBean(@NotNull String beanName, @NotNull Class<T> beanType, @NotNull Class<E> beanImplType, boolean required) throws BeansException {
        T bean = null;
        try {
            if (StringUtils.isNullOrEmptyString(beanName)) {
                // #1. 구현 타입에 해당하는 모든 Bean 조회
                Map<String, T> beanTypeCandidates = this.context.getBeansOfType(beanType);
                int count = 0;
                for (T candidate : beanTypeCandidates.values()) {
                    // Bean 구현 클래스 타입과 일치하지 않는 경우
                    if (beanImplType == null || beanImplType.equals(candidate.getClass())) {
                        if (bean == null) {
                            bean = candidate;
                        }
                        count++;
                    }
                }

                if (count > 1) {
                    throw new NoUniqueBeanDefinitionException(beanType, count, "Bean 이름이 설정되지 않았는데, Bean 인스턴스가 여러 개 존재합니다. Bean 이름을 명확히 설정하기 바랍니다.");
                }

                if (bean != null) {
                    return bean;
                } else if (beanImplType != null) {
                    // #2. {beanImplTye}을 제외하고 Bean 이 존재하지 않는 경우
                    bean = findExplicitBean(beanImplType);
                }
            } else {
                bean = this.context.getBean(beanName, beanType);
            }

            if (required && bean == null) {
                throw ExceptionUtils.newException(NoSuchBeanDefinitionException.class, "'%s'에 해당하는 Bean 이 존재하지 않습니다.", beanType);
            }
            return bean;
        } catch (BeansException e) {
            throw e;
        }
    }

    /**
     * 구현 클래스에 해당하는 Bean 을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param beanExplicitImplType
     * @return
     * @throws BeansException
     *
     * @since 2025. 5. 21.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see ApplicationContext#getBeansOfType(Class)
     */
    private <T> T findExplicitBean(@NotNull Class<T> beanExplicitImplType) throws BeansException {
        Map<String, T> candidates = this.context.getBeansOfType(beanExplicitImplType);
        T bean = null;
        int count = 0;
        for (T candidate : candidates.values()) {
            if (beanExplicitImplType.equals(candidate.getClass())) {
                bean = candidate;
                count++;
            }
        }
        if (count > 1) {
            throw ExceptionUtils.newException(NoUniqueBeanDefinitionException.class, "Bean 이름을 사용하지 않고 구현클래스(%s) 정보로만 조회시 여러 개(%s) 이상의 인스턴스가 존재합니다.", beanExplicitImplType, count);
        }
        return bean;
    }

    /**
     * 주어진 이름에 해당하는 Bean 객체를 제공합니다. <br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 21.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param beanName
     *            Bean 이름
     * @param beanType
     *            Bean 유형
     * @param defaultBean
     *            Bean 이름이 비어있는 경우, {beanType}에 해당하는 사용자 정의 bean이 없는 경우 제공할 bean 유형
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
     * 
     * @see ApplicationContext#getBean(String)
     * @see ApplicationContext#getBean(String, Class)
     */
    public final <T> T getBean(@NotNull String beanName, @NotNull Class<T> beanType, @NotNull T defaultBean, boolean required) throws BeansException {
        T bean = null;
        try {
            if (StringUtils.isNullOrEmptyString(beanName)) {
                bean = findBean(beanName, beanType, null, required);
                if (bean == null && defaultBean != null) {
                    bean = defaultBean;
                }
            } else {
                bean = this.context.getBean(beanName, beanType);
            }

            if (required && bean == null) {
                throw ExceptionUtils.newException(NoSuchBeanDefinitionException.class, "'%s'에 해당하는 Bean 이 존재하지 않습니다.", beanType);
            }

            return bean;
        } catch (BeansException e) {
            throw e;
        }
    }

    /**
     * 주어진 컨텍스트를 이용하여 Bean 관련 기능을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 21.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     * @return
     *
     * @since 2025. 5. 21.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static BeanUtils context(@NotNull ApplicationContext context) {
        Assert.notNull(context, "컨텍스트 정보는 반드시 설정되어야 합니다.");
        return new BeanUtils(context);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            데이터 유형 Generic Type 문자
     * @param environment
     *            {@link SpringApplication} 환경 정보
     * @param property
     *            데이터 속성값. (전체 경로)
     * @param type
     *            데이터 유형
     * @return
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <E> List<E> listOf(Environment environment, String property, Class<E> type) {
        return Binder.get(environment).bind(property, Bindable.listOf(type)).orElse(Collections.emptyList());
    }

    /**
     * FQN 문자열을 파싱하여 bean 이름을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param fqn
     *            Bean 이름을 표현하는 Full Qualified Name
     * @return
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String resolveBeanNameFromFqn(String fqn) {
        try {
            int lastDotIndex = fqn.lastIndexOf(".");
            String className = fqn.substring(0, lastDotIndex);
            String fieldName = fqn.substring(lastDotIndex + 1);

            Class<?> clazz = Class.forName(className);
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (String) field.get(null); // static field
        } catch (Exception e) {
            throw new IllegalBeanNameFqnResolveException("FQN 문자열에서 bean 이름을 추출할 수 없습니다: " + fqn, e);
        }
    }
}
