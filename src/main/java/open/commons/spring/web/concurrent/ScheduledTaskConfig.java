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
 * Date  : 2025. 8. 1. 오후 4:06:59
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import open.commons.spring.web.config.ResourceConfiguration;
import open.commons.spring.web.resources.ThreadPoolTaskSchedulerConfig;

/**
 * 
 * @since 2025. 8. 1.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
public class ScheduledTaskConfig implements SchedulingConfigurer {

    private final Logger logger = LoggerFactory.getLogger(ScheduledTaskConfig.class);

    /** {@link ThreadPoolTaskSchedulerConfig} 객체에 사용할 설정 정보 */
    private final ThreadPoolTaskSchedulerConfig config;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 1.		박준홍			최초 작성
     * </pre>
     * 
     * @param config
     *            {@link ThreadPoolTaskScheduler} 객체에 사용할 설정 정보
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ScheduledTaskConfig(@Qualifier(ResourceConfiguration.CONFIGURATION_THREAD_POOL_TASK_SCHEDULER_CONFIG_ON_MDC) ThreadPoolTaskSchedulerConfig config) {
        this.config = config;
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.scheduling.annotation.SchedulingConfigurer#configureTasks(org.springframework.scheduling.config.ScheduledTaskRegistrar)
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        // 작업 실행 Executor
        ThreadPoolTaskScheduler delegate = ResourceConfiguration.createThreadPoolTaskScheduler(config);
        delegate.initialize();
        // MDC를 전파할 수 있는 ExecutorService
        TaskScheduler decorator = DelegatingExecutorSupportor.taskScheduler(delegate);
        // 등록
        taskRegistrar.setScheduler(decorator);

        logger.info("[delegating-scheduled-executor-service] Registered! -> {}", decorator);
    }
}
