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
 * Date  : 2025. 8. 11. 오후 5:21:01
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import open.commons.core.utils.MapUtils;
import open.commons.spring.web.beans.authority.AuthorizedRequestDataMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata;
import open.commons.spring.web.beans.resolver.AuthorizedDataArgumentResolver;
import open.commons.spring.web.beans.resolver.AuthorizedDataModelAttributeResolver;
import open.commons.spring.web.beans.resolver.IAuthorizedDataResolver;

/**
 * 
 * @since 2025. 8. 11.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration(proxyBeanMethods = false)
// 하위 클래스가 @Configuration/@Bean으로 등록돼 있으면 이 빈은 건너뜀
@ConditionalOnMissingBean(CustomWebMvcConfigurer.class)
public class CustomWebMvcAutoConfiguration {

    public static final String BEAN_QUALIFIER_AUTHORIZED_DATA_RESOLVERS = "open.commons.spring.web.config.CustomWebMvcAutoConfiguration#AUTHORIZED_DATA_RESOLVERS";

    private static final Logger logger = LoggerFactory.getLogger(CustomWebMvcAutoConfiguration.class);

    private final ApplicationContext context;
    private final Environment environment;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     * @param environment
     *
     * @since 2025. 8. 11.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public CustomWebMvcAutoConfiguration(ApplicationContext context, Environment environment) {
        super();
        this.context = context;
        this.environment = environment;
    }

    @Bean
    @Primary
    @Order(Ordered.HIGHEST_PRECEDENCE)
    AuthorizedDataArgumentResolver authorizedDataArgumentRevolser(ApplicationContext context) {
        AuthorizedDataArgumentResolver resolver = new AuthorizedDataArgumentResolver(context);
        logger.info("[authorized-resources] authorized-data-argument-resolver={}", resolver);
        return resolver;
    }

    @Bean
    @Primary
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    AuthorizedDataModelAttributeResolver authorizedDataModelAttributeResolver(ApplicationContext context,
            @Qualifier(AuthorizedRequestDataMetadata.BEAN_QUALIFIER) IAuthorizedRequestDataMetadata authorizedRequestDataMetadata) {
        AuthorizedDataModelAttributeResolver resolver = new AuthorizedDataModelAttributeResolver(context, authorizedRequestDataMetadata);
        logger.info("[authorized-resources] authorized-data-model-attribute-resolver={}", resolver);
        return resolver;
    }

    @Bean(BEAN_QUALIFIER_AUTHORIZED_DATA_RESOLVERS)
    @Primary
    List<IAuthorizedDataResolver> authorizedDataResolver(@NotNull @Nonnull Map<String, IAuthorizedDataResolver> single //
            , @NotNull @Nonnull Map<String, List<IAuthorizedDataResolver>> multi) {
        List<IAuthorizedDataResolver> resolvers = MapUtils.toList(single, multi);
        logger.info("[authorized-resources] authorized-data-resolvers={}", resolvers);
        return resolvers;
    }

    @Bean
    @Order(CustomWebMvcConfigurer.ORDER)
    public CustomWebMvcConfigurer customWebMvcConfigurer() {
        CustomWebMvcConfigurer c = new CustomWebMvcConfigurer(this.context, this.environment);
        logger.info("[web-mvc-configurer] custom-web-mvc-configurer={}", c);
        return c;
    }

    @Bean
    public BeanPostProcessor reorderArgumentResolvers() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) {
                if (bean instanceof RequestMappingHandlerAdapter) {
                    RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
                    List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>(adapter.getArgumentResolvers());
                    Collections.sort(resolvers, new Comparator<HandlerMethodArgumentResolver>() {
                        @Override
                        public int compare(HandlerMethodArgumentResolver o1, HandlerMethodArgumentResolver o2) {
                            if (o1 instanceof IAuthorizedDataResolver) {
                                return -1;
                            } else if (o2 instanceof IAuthorizedDataResolver) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }
                    });

                    adapter.setArgumentResolvers(resolvers);
                }
                return bean;
            }
        };
    }

}
