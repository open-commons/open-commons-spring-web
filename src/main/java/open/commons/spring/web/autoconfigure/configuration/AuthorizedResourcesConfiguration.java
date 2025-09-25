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
 * Date  : 2025. 5. 19. 오후 4:33:07
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure.configuration;

import java.util.List;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import open.commons.spring.web.aspect.AuthorizedMethodAspect;
import open.commons.spring.web.aspect.AuthorizedRequestAspect;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedResourceAuthenticationPause;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IMethodAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IRequestAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.beans.authority.builtin.AuthorizedResourceHandler;
import open.commons.spring.web.beans.authority.builtin.ResourceHandle;
import open.commons.spring.web.handler.AuthorizedModelAndViewHandlerInterceptor;
import open.commons.spring.web.jackson.deserialization.AuthorizedFieldDeserializerModifier;
import open.commons.spring.web.jackson.serialization.AuthorizedFieldSerializerModifier;
import open.commons.spring.web.servlet.filter.AuthorizedResourceFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@AutoConfigureAfter({ AuthorizedResourceBuiltinHandlerConfiguration.class })
public class AuthorizedResourcesConfiguration {

    public static final String BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER = "open.commons.spring.web.autoconfigure.AuthorizedResourcesConfiguration#AUTHORIZED_OBJECT_MAPPER";

    private static final Logger logger = LoggerFactory.getLogger(AuthorizedResourcesConfiguration.class);

    public AuthorizedResourcesConfiguration() {
    }

    @Bean
    @ConditionalOnBean(IMethodAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedMethodAspect authorizedMethodAspect(ApplicationContext context) {
        AuthorizedMethodAspect aspect = new AuthorizedMethodAspect(context);
        logger.info("[Registered] authorized-method-aspect={}", aspect);
        return aspect;
    }

    @Bean(name = BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER)
    @ConditionalOnBean({ IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    ObjectMapper authorizedObjectMapper(ApplicationContext context //
            , @NotNull @Nonnull IAuthorizedResourcesMetadata authorizedResourcesMetadata //
            , @NotNull @Nonnull IAuthorizedRequestDataMetadata authorizedRequestDataMetadata //
            , @NotNull @Nonnull Jackson2ObjectMapperBuilder objectMapperBuilder) {
        // #1. ObjectMapper 생성
        ObjectMapper mapper = objectMapperBuilder.build();
        // #2. AuthorizedObject 처리 모듈 등록
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new AuthorizedFieldSerializerModifier(context, authorizedResourcesMetadata));
        module.setDeserializerModifier(new AuthorizedFieldDeserializerModifier(context, authorizedRequestDataMetadata));
        mapper.registerModule(module);

        logger.info("[authorized-resources] authorized-object-mapper={}", mapper);

        return mapper;
    }

    @Bean
    @ConditionalOnBean(IRequestAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedRequestAspect authorizedRequestAspect(ApplicationContext context) {
        AuthorizedRequestAspect aspect = new AuthorizedRequestAspect(context);
        logger.info("[authorized-resources] authorized-request-aspect={}", aspect);
        return aspect;
    }

    @Bean
    @ConditionalOnBean({ IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    @Order(Integer.MAX_VALUE)
    AuthorizedResourceFilter authorizedResourceFilter(ApplicationContext context) {
        IAuthorizedResourceAuthenticationPause auth = null;
        try {
            auth = context.getBean(IAuthorizedResourceAuthenticationPause.class);
        } catch (BeansException ignored) {
        }
        AuthorizedResourceFilter f = new AuthorizedResourceFilter(auth);
        logger.info("[authorized-resources] authorized-resources-filter={}", f);
        return f;
    }

    @Bean(AuthorizedResourceHandler.BEAN_QUALIFIER)
    @ConditionalOnBean(name = { AuthorizedResourceBuiltinHandlerConfiguration.BEAN_QUALIFIER_AUTHORIZED_RESOURCE_HANDLERS })
    AuthorizedResourceHandler authorizedResourceHandlers(
            @Qualifier(AuthorizedResourceBuiltinHandlerConfiguration.BEAN_QUALIFIER_AUTHORIZED_RESOURCE_HANDLERS) List<ResourceHandle> handlers) {
        AuthorizedResourceHandler h = new AuthorizedResourceHandler();
        h.setAuthorizedResourceHandlers(handlers);
        logger.info("[authorized-resource-handlers] authorized-resources-handlers={}", h);
        return h;
    }

    @Bean
    @Primary
    @ConditionalOnBean({ IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    AuthorizedModelAndViewHandlerInterceptor authorizedModelAndViewHandlerInterceptor(ApplicationContext context //
            , @NotNull @Nonnull IAuthorizedResourcesMetadata authorizedResourcesMetadata //
    ) {
        AuthorizedModelAndViewHandlerInterceptor h = new AuthorizedModelAndViewHandlerInterceptor(context, authorizedResourcesMetadata);
        logger.info("[authorized-resources] authorized-model_and_view-handler-interceptor={}", h);
        return h;
    }
}
