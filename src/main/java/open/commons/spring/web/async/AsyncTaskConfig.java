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
 * Date  : 2025. 7. 30. 오후 5:13:29
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.async;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import open.commons.spring.web.config.ResourceConfiguration;
import open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig;

/**
 * {@link Async} 어노테이션이 적용된 메소드가 실행될 때 기본값으로 사용되는 {@link Executor}를 제공하는 클래스.
 * 
 * @since 2025. 7. 30.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
public class AsyncTaskConfig implements AsyncConfigurer {

    private final Logger logger = LoggerFactory.getLogger(AsyncTaskConfig.class);

    private final ThreadPoolTaskExecutorConfig config;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AsyncTaskConfig(@Qualifier(ResourceConfiguration.CONFIGURATION_BUILTIN_THREAD_POOL_TASK_EXECUTOR_CONFIG_ON_ASYNC) ThreadPoolTaskExecutorConfig config) {
        this.config = config;
    }

    /**
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.scheduling.annotation.AsyncConfigurer#getAsyncExecutor()
     */
    @Override
    public Executor getAsyncExecutor() {
        // (C)ontext (S)haring (T)hread
        ThreadPoolTaskExecutor executor = ResourceConfiguration.createThreadPoolTaskExecutor(this.config, "@async");
        executor.initialize();

        logger.info("[async-exeuctor-service] Registered! -> {}", executor);

        return executor;
    }
}
