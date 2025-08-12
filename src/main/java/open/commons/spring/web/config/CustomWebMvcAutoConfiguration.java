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
 * Date  : 2025. 8. 11. 오후 5:21:01
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 
 * @since 2025. 8. 11.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration(proxyBeanMethods = false)
// 하위 클래스가 @Configuration/@Bean으로 등록돼 있으면 이 빈은 건너뜀
@ConditionalOnMissingBean(CustomWebMvcConfigurer.class)
public class CustomWebMvcAutoConfiguration {

    private final ApplicationContext context;
    private final Environment environment;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     * @param environment
     *
     * @since 2025. 8. 11.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public CustomWebMvcAutoConfiguration(ApplicationContext context, Environment environment) {
        super();
        this.context = context;
        this.environment = environment;
    }

    @Bean
    public CustomWebMvcConfigurer customWebMvcConfigurer() {
        return new CustomWebMvcConfigurer(this.context, this.environment);
    }
}
