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

package open.commons.spring.web.config;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import open.commons.spring.web.beans.authority.AuthorizedObjectMapperDecorator;
import open.commons.spring.web.beans.authority.AuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedObjectMapperDecorator;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
public class AuthorizedResourcesMetadataConfiguration {

    public static final String BEAN_QUALIFIER_DEFAULT_OBJECT_MAPPER = "open.commons.spring.web.config.AuthorizedResourcesMetadataConfiguration#DEFAULT_OBJECT_MAPPER";
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

    @Bean(AuthorizedObjectMapperDecorator.BEAN_QUALIFIER)
    @ConditionalOnMissingBean
    IAuthorizedObjectMapperDecorator authorizedObjectMapperDecorator() {
        IAuthorizedObjectMapperDecorator aomd = new AuthorizedObjectMapperDecorator();
        logger.info("[authorized-resources] authorized-object-mapper-decorator={}", aomd);
        return aomd;
    }

    @Bean(AuthorizedResourcesMetadata.BEAN_QUALIFIER)
    @ConfigurationProperties(PROPERTIES_AUTHOIRZED_OBJECT_METADATA)
    @ConditionalOnMissingBean
    IAuthorizedResourcesMetadata authorizedResourcesMetadataProvider() {
        IAuthorizedResourcesMetadata armp = new AuthorizedResourcesMetadata();
        logger.info("[authorized-resources] authorized-resources-metadata-provider={}", armp);
        return armp;
    }

    @Bean(BEAN_QUALIFIER_DEFAULT_OBJECT_MAPPER)
    @Primary
    ObjectMapper objectMapper(@NotNull IAuthorizedObjectMapperDecorator authorizedObjectMapperDecorator) {
        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
        authorizedObjectMapperDecorator.configureFeature(mapper);

        logger.info("[authorized-resources] default-object-mapper={}", mapper);

        return mapper;
    }

}
