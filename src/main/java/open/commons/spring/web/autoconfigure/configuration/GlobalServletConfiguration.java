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
 * Date  : 2025. 6. 5. 오후 4:57:30
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import open.commons.spring.web.handler.DefaultGlobalInterceptor;

/**
 * 
 * @since 2025. 6. 5.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class GlobalServletConfiguration {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 5.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 5.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public GlobalServletConfiguration() {
    }

    @Bean(DefaultGlobalInterceptor.BEAN_QUALIFIER)
    @ConditionalOnMissingBean
    @Order(Ordered.LOWEST_PRECEDENCE)
    DefaultGlobalInterceptor defaultGlobalInterceptor() {
        return new DefaultGlobalInterceptor();
    }
}
