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
 * Date  : 2025. 5. 19. 오전 10:51:11
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedMethod;
import open.commons.spring.web.authority.AuthorizedRequest;

/**
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractAuthorizedResourceAspect<T> extends AbstractAspectPointcuts implements IAuthorizedResource<T> {

    public static final int ORDER_METHOD = Ordered.HIGHEST_PRECEDENCE;
    public static final int ORDER_REQUEST = Ordered.HIGHEST_PRECEDENCE;

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("^\\$\\{\\s*([^:}]+)(?::([^}]*))?\\s*}$");

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
        super(context);
        this.providerType = providerType;
    }

    /**
     * {@link AuthorizedMethod} 어노테이션이 설정된 메소드 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("@annotation(open.commons.spring.web.authority.AuthorizedMethod)")
    public final void annotationAuthorizedMethod() {
    }

    /**
     * {@link AuthorizedRequest} 어노테이션이 설정된 메소드 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("@annotation(open.commons.spring.web.authority.AuthorizedRequest)")
    public final void annotationAuthorizedRequest() {
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
     * Spring Environment Property Placeholder 패턴(${...:default})을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 29.     박준홍         최초 작성
     * </pre>
     *
     * @param input
     * @return
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected final String findConfigurationValue(String input) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(input);
        if (matcher.matches()) {
            String propertyName = matcher.group(1); // 속성 이름
            String defaultValue = matcher.group(2); // 기본값 (없으면 null)

            String configValue = this.env.getProperty(propertyName);
            if (StringUtils.isNullOrEmptyString(configValue)) {
                return defaultValue;
            } else {
                return configValue;
            }
        } else {
            return input;
        }
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
     * {@link AuthorizedMethod} 어노테이션이 설정된 클래스. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("@within(open.commons.spring.web.authority.AuthorizedMethod)")
    public final void withinAuthorizedMethod() {
    }

    /**
     * {@link AuthorizedRequest} 어노테이션이 설정된 클래스. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 19.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("@within(open.commons.spring.web.authority.AuthorizedRequest)")
    public final void withinAuthorizedRequest() {
    }
}
