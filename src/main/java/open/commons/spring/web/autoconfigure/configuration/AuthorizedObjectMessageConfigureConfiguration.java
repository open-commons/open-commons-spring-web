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
 * Date  : 2025. 6. 10. 오후 5:44:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure.configuration;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import open.commons.spring.web.config.AuthorizedObjectMessageConfigure;
import open.commons.spring.web.jackson.AuthorizedObjectJackson2HttpMessageConverter;

/**
 * 
 * @since 2025. 6. 10.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@AutoConfigureAfter(AuthorizedObjectMessageConverterConfiguration.class)
public class AuthorizedObjectMessageConfigureConfiguration {

    private Logger logger = LoggerFactory.getLogger(AuthorizedObjectMessageConfigureConfiguration.class);

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
    public AuthorizedObjectMessageConfigureConfiguration() {
    }

    @Bean
    @ConditionalOnBean(name = { AuthorizedObjectJackson2HttpMessageConverter.BEAN_QUALIFIER })
    WebMvcConfigurer authorizedObjectMessageConfigure(
            @Qualifier(AuthorizedObjectJackson2HttpMessageConverter.BEAN_QUALIFIER) @NotNull AuthorizedObjectJackson2HttpMessageConverter messageConverter) {
        WebMvcConfigurer configure = new AuthorizedObjectMessageConfigure(messageConverter);
        logger.info("[authorized-resources] authorized-object-message-configure={}", configure);
        return configure;
    }

}
