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
 * Date  : 2025. 7. 31. 오전 9:12:04
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.config;

import java.util.concurrent.Executor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

import open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig;

/**
 * 비동기 동작 관련 설정을 제공하는 클래스.
 * 
 * @since 2025. 7. 31.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
public class AsyncConfiguration {

    /** {@link Async} 어노테이션이 적용된 메소드가 실행될 때 기본값으로 사용되는 {@link Executor} 설정값 */
    public static final String CONFIGURATION_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG = "open.commons.spring.web.config.AsyncConfiguration#DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG";

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 31.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AsyncConfiguration() {
    }

    /**
     * {@link Async} 어노테이션이 적용된 메소드가 실행될 때 기본값으로 사용되는 {@link Executor} 설정값을 제공합니다. 단,
     * {@link #CONFIGURATION_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG} 이름을 갖는 {@link Bean} 이 생성되는 경우 실행되지 않습니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 31.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(CONFIGURATION_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG)
    @ConditionalOnMissingBean(name = { CONFIGURATION_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG })
    ThreadPoolTaskExecutorConfig defaultThreadPoolTaskExecutorConfig() {
        ThreadPoolTaskExecutorConfig config = new ThreadPoolTaskExecutorConfig();
        config.setThreadGroupName("CST");
        config.setThreadNamePrefix("cst-async-");
        config.setDaemon(true);
        return config;
    }

}
