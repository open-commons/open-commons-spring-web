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
 * Date  : 2025. 5. 19. 오후 4:33:07
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import open.commons.spring.web.aspect.AuthorizedMethodAspect;
import open.commons.spring.web.aspect.AuthorizedRequestAspect;
import open.commons.spring.web.beans.ac.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.ac.IMethodAccessAuthorityProvider;
import open.commons.spring.web.beans.ac.IRequestAccessAuthorityProvider;
import open.commons.spring.web.beans.ac.IUnauthorizedFieldHandler;
import open.commons.spring.web.config.AuthorizedObjectMessageConfigure;
import open.commons.spring.web.jackson.AuthorizedFieldSerializerModifier;
import open.commons.spring.web.jackson.AuthorizedObjectJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourcesConfiguration {

    public static final String BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER = "open.commons.spring.web.autoconfigure.AuthorizedResourcesConfiguration#AUTHORIZED_OBJECT_MAPPER";

    public AuthorizedResourcesConfiguration() {
    }

    @Bean
    @ConditionalOnBean(IMethodAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedMethodAspect authorizedMethodAspect(ApplicationContext context) {
        return new AuthorizedMethodAspect(context);
    }

    @Bean(BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER)
    @ConditionalOnBean(value = { IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    ObjectMapper authorizedObjectMapper(ApplicationContext context) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new AuthorizedFieldSerializerModifier(context));
        mapper.registerModule(module);

        return mapper;
    }

    @Bean
    WebMvcConfigurer authorizedObjectMessageConfigure(
            @Qualifier(AuthorizedObjectJackson2HttpMessageConverter.BEAN_QUALIFIER) @NotNull AuthorizedObjectJackson2HttpMessageConverter messageConverter) {
        return new AuthorizedObjectMessageConfigure(messageConverter);
    }

    @Bean(AuthorizedObjectJackson2HttpMessageConverter.BEAN_QUALIFIER)
    @ConditionalOnBean(name = { BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER })
    AuthorizedObjectJackson2HttpMessageConverter authorizedObjectMessageConverter(@NotNull Map<String, ObjectMapper> allObjectMappers) {
        return new AuthorizedObjectJackson2HttpMessageConverter(allObjectMappers);
    }

    @Bean
    @ConditionalOnBean(IRequestAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedRequestAspect authorizedRequestAspect(ApplicationContext context) {
        return new AuthorizedRequestAspect(context);
    }
}
