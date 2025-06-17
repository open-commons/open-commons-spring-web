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
 * Date  : 2025. 6. 17. 오후 3:06:29
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.config;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import open.commons.spring.web.jacksons.decoration.IObjectMapperDecorationConsolidator;
import open.commons.spring.web.jacksons.decoration.IObjectMapperDecorator;
import open.commons.spring.web.jacksons.decoration.ObjectMapperDecorationConsolidator;
import open.commons.spring.web.jacksons.decoration.ObjectMapperDecorator;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @since 2025. 6. 17.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
public class ObjectMapperConfiguration {

    public static final String BEAN_QUALIFIER_DEFAULT_OBJECT_MAPPER = "open.commons.spring.web.config.AuthorizedResourcesMetadataConfiguration#DEFAULT_OBJECT_MAPPER";

    private final Logger logger = LoggerFactory.getLogger(ObjectMapperConfiguration.class);

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ObjectMapperConfiguration() {
    }

    @Bean(ObjectMapperDecorator.BEAN_QUALIFIER)
    @ConditionalOnMissingBean
    IObjectMapperDecorator authorizedObjectMapperDecorator() {
        IObjectMapperDecorator omd = new ObjectMapperDecorator();

        logger.info("[object-mapper] object-mapper-decorator={}", omd);

        return omd;
    }

    @Bean(BEAN_QUALIFIER_DEFAULT_OBJECT_MAPPER)
    @Primary
    ObjectMapper objectMapper(@NotNull IObjectMapperDecorationConsolidator objectMapperConsolidator) {
        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapperConsolidator.configureFeature(mapper);

        logger.info("[object-mapper] default-object-mapper={}", mapper);

        return mapper;
    }

    @Bean(ObjectMapperDecorationConsolidator.BEAN_QUALIFIER)
    @Primary
    IObjectMapperDecorationConsolidator objectMapperDecorationConsolidator(Map<String, IObjectMapperDecorator> decorators) {
        IObjectMapperDecorationConsolidator omc = new ObjectMapperDecorationConsolidator();
        omc.addObjectMapperDecorator(decorators.values());

        logger.info("[object-mapper] object-mapper-decoration-consolidator={}", omc);

        return omc;
    }
}
