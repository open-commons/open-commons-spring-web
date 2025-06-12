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

package open.commons.spring.web.autoconfigure.configuration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import open.commons.spring.web.aspect.AuthorizedMethodAspect;
import open.commons.spring.web.aspect.AuthorizedRequestAspect;
import open.commons.spring.web.authority.configuratioon.AuthorizedObjectMetadata;
import open.commons.spring.web.beans.authority.AuthorizedResourcesMetadataProvider;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadataProvider;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IMethodAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IRequestAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.jackson.AuthorizedFieldSerializerModifier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@AutoConfigureAfter({ AuthorizedObjectForcedUnintelligibleConfiguration.class, AuthorizedResourcesMetadataConfiguration.class })
public class AuthorizedResourcesConfiguration {

    public static final String BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER = "open.commons.spring.web.autoconfigure.AuthorizedResourcesConfiguration#AUTHORIZED_OBJECT_MAPPER";

    private static Logger logger = LoggerFactory.getLogger(AuthorizedResourcesConfiguration.class);

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

    @Bean(BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER)
    @ConditionalOnBean(value = { IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    ObjectMapper authorizedObjectMapper(ApplicationContext context) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new AuthorizedFieldSerializerModifier(context));
        mapper.registerModule(module);

        logger.info("[authorized-resources] Registered authorized-object-mapper={}", mapper);

        return mapper;
    }

    @Bean
    @ConditionalOnBean(IRequestAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedRequestAspect authorizedRequestAspect(ApplicationContext context) {
        AuthorizedRequestAspect aspect = new AuthorizedRequestAspect(context);
        logger.info("[authorized-resources] Registered authorized-request-aspect={}", aspect);
        return aspect;
    }

    @Bean(AuthorizedResourcesMetadataProvider.BEAN_QUALIFIER)
    @ConditionalOnBean(name = { AuthorizedResourcesMetadataConfiguration.BEAN_QUALIFIER_AUTHORIZED_OBJECT_METADATA })
    IAuthorizedResourcesMetadataProvider authorizedResourcesMetadataProvider(
            @Qualifier(AuthorizedResourcesMetadataConfiguration.BEAN_QUALIFIER_AUTHORIZED_OBJECT_METADATA) List<AuthorizedObjectMetadata> authorizdedObjectMetadata) {
        return new AuthorizedResourcesMetadataProvider(authorizdedObjectMetadata);
    }
}
