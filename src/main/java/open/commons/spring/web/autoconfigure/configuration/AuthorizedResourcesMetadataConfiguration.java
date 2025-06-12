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

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import open.commons.spring.web.authority.configuratioon.AuthorizedObjectMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourcesMetadataConfiguration {

    public static final String BEAN_QUALIFIER_AUTHORIZED_OBJECT_METADATA = "open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesMetadataConfiguration#AUTHORIZED_OBJECT_METADATA";
    public static final String PROPERTIES_AUTHOIRZED_OBJECT_METADATA = "open-commons.application.authorized-object";

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

    @Bean(BEAN_QUALIFIER_AUTHORIZED_OBJECT_METADATA)
    @ConfigurationProperties(PROPERTIES_AUTHOIRZED_OBJECT_METADATA)
//    @ConditionalOnProperty(prefix = PROPERTIES_AUTHOIRZED_OBJECT_METADATA, name = "type")
    @ConditionalOnBean(value = { IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    public List<AuthorizedObjectMetadata> authorizdedObjectMetadata() {
        return new ArrayList<>();
    }
}
