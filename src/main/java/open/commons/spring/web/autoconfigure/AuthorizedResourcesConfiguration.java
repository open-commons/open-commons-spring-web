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

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import open.commons.spring.web.ac.provider.IFieldAccessAuthorityProvider;
import open.commons.spring.web.ac.provider.IMethodAccessAuthorityProvider;
import open.commons.spring.web.ac.provider.IRequestAccessAuthorityProvider;
import open.commons.spring.web.aspect.AuthorizedMethodAspect;
import open.commons.spring.web.aspect.AuthorizedRequestAspect;
import open.commons.spring.web.aspect.AuthorizedResponseAspect;
import open.commons.spring.web.beans.DefaultAuthorizedResponseHandler;
import open.commons.spring.web.beans.DefaultUnauthorizedFieldHandler;
import open.commons.spring.web.beans.IAuthorizedResponseHandler;
import open.commons.spring.web.beans.IUnauthorizedFieldHandler;

/**
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourcesConfiguration {

    public AuthorizedResourcesConfiguration() {
    }

    @Bean
    @ConditionalOnBean(IMethodAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedMethodAspect authorizedMethodAspect(ApplicationContext context) {
        return new AuthorizedMethodAspect(context);
    }

    @Bean
    @ConditionalOnBean(IRequestAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedRequestAspect authorizedRequestAspect(ApplicationContext context) {
        return new AuthorizedRequestAspect(context);
    }

    @Bean
    @ConditionalOnBean(IFieldAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedResponseAspect authorizedResponseAspect(ApplicationContext context) {
        return new AuthorizedResponseAspect(context);
    }

    @Bean
    @ConditionalOnBean(AuthorizedResponseAspect.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    IAuthorizedResponseHandler authorizedResponseHandler(ApplicationContext context) {
        return new DefaultAuthorizedResponseHandler(context);
    }

    @Bean
    @ConditionalOnBean(AuthorizedResponseAspect.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    IUnauthorizedFieldHandler unauthorizedFieldHanlder(ApplicationContext context) {
        return new DefaultUnauthorizedFieldHandler(context);
    }

}
