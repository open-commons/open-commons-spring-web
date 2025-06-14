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
 * Date  : 2025. 6. 10. 오후 5:45:30
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure.configuration;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import open.commons.spring.web.beans.authority.AuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.jackson.AuthorizedObjectJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @since 2025. 6. 10.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@AutoConfigureAfter(AuthorizedResourcesConfiguration.class)
public class AuthorizedObjectMessageConverterConfiguration {

    private Logger logger = LoggerFactory.getLogger(AuthorizedObjectMessageConverterConfiguration.class);

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 10.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedObjectMessageConverterConfiguration() {
    }

    @Bean(AuthorizedObjectJackson2HttpMessageConverter.BEAN_QUALIFIER)
    @ConditionalOnBean(name = { AuthorizedResourcesConfiguration.BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER, AuthorizedResourcesMetadata.BEAN_QUALIFIER })
    AuthorizedObjectJackson2HttpMessageConverter authorizedObjectMessageConverter(@NotNull Map<String, ObjectMapper> allObjectMappers,
            @Qualifier(AuthorizedResourcesMetadata.BEAN_QUALIFIER) @NotNull IAuthorizedResourcesMetadata authorizedResourcesMetadataProvider) {
        AuthorizedObjectJackson2HttpMessageConverter converter = new AuthorizedObjectJackson2HttpMessageConverter(allObjectMappers, authorizedResourcesMetadataProvider);
        logger.info("[authorized-resources] authorized-object-message-converter={}", converter);
        return converter;
    }

}
