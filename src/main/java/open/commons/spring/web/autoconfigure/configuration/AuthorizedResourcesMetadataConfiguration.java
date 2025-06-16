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
 * Date  : 2025. 6. 12. 오후 6:04:48
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import open.commons.spring.web.beans.authority.AuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourcesMetadataConfiguration {

    public static final String PROPERTIES_AUTHOIRZED_OBJECT_METADATA = "open-commons.application.authorized-resources";

    private final Logger logger = LoggerFactory.getLogger(AuthorizedResourcesMetadataConfiguration.class);

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedResourcesMetadataConfiguration() {
    }

    @Bean(AuthorizedResourcesMetadata.BEAN_QUALIFIER)
    @ConfigurationProperties(PROPERTIES_AUTHOIRZED_OBJECT_METADATA)
    @ConditionalOnBean(value = { IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    @ConditionalOnMissingBean
    IAuthorizedResourcesMetadata authorizedResourcesMetadataProvider() {
        IAuthorizedResourcesMetadata armp = new AuthorizedResourcesMetadata();
        logger.info("[authorized-resources] authorized-resources-metadata-provider={}", armp);
        return armp;
    }

}
