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
 * Date  : 2025. 5. 19. 오후 12:41:25
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.factory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import open.commons.spring.web.ac.AuthorizedMethod;
import open.commons.spring.web.ac.AuthorizedRequest;

/**
 * 권한에 기반하여 자원에 접근할 수 있는 어노테이션을 검증하는 클래스.
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Component
public class AuthorizedResourceAnnotationValidator implements BeanFactoryPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(AuthorizedResourceAnnotationValidator.class);

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
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedResourceAnnotationValidator() {
    }

    /**
     *
     * @since 2025. 5. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor#postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        boolean noAnnoOnClass = false;
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            Class<?> beanType = beanFactory.getType(beanName);
            if (beanType == null || beanType.isSynthetic())
                continue;

            // (1) 클래스 레벨 어노테이션 검사 (상속 고려)
            AuthorizedMethod classAM = AnnotationUtils.findAnnotation(beanType, AuthorizedMethod.class);
            AuthorizedRequest classAR = AnnotationUtils.findAnnotation(beanType, AuthorizedRequest.class);

            if (classAM != null && classAR != null) {
                throw new IllegalStateException(String.format("[권한 설정 오류] 클래스 '%s'에 @AuthorizedMethod 와 @AuthorizedRequest 를 동시에 사용할 수 없습니다.", beanType.getName()));
            }

            noAnnoOnClass = classAM == null && classAR == null;

            // (2) 모든 메서드 검사 (interface default 메서드 포함)
            Collection<Method> methods = getAllDeclaredMethods(beanType);

            boolean noAnnoOnMethod = false;
            for (Method method : methods) {
                // (3) 메서드에 직접 붙었거나, 메서드 상속 및 메타 어노테이션 고려
                AuthorizedMethod methodAM = AnnotationUtils.findAnnotation(method, AuthorizedMethod.class);
                AuthorizedRequest methodAR = AnnotationUtils.findAnnotation(method, AuthorizedRequest.class);

                noAnnoOnMethod = methodAM == null && methodAR == null;

                if (noAnnoOnClass && noAnnoOnMethod) {
                    continue;
                }

                if (methodAM != null && methodAR != null) {
                    throw new IllegalStateException(
                            String.format("[권한 설정 오류] 메서드 '%s.%s()'에 @AuthorizedMethod 와 @AuthorizedRequest 를 동시에 사용할 수 없습니다.", beanType.getName(), method.getName()));
                }

                if ((classAM != null && methodAR != null) || (classAR != null && methodAM != null)) {
                    throw new IllegalStateException(
                            String.format("[권한 설정 오류] '%s.%s()'는 클래스와 메서드에 @AuthorizedMethod / @AuthorizedRequest 가 교차 사용되었습니다.", beanType.getName(), method.getName()));
                }

                logger.trace("bean.name={}, bean.type={}, method={}", beanName, beanType, method);

            }
        }
    }

    private static Collection<Method> getAllDeclaredMethods(Class<?> clazz) {
        Set<Method> methods = new LinkedHashSet<>();
        methods.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        for (Class<?> iface : clazz.getInterfaces()) {
            methods.addAll(Arrays.asList(iface.getMethods())); // default methods 포함
        }

        return methods;
    }

}
