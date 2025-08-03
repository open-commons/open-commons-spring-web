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
 * Date  : 2025. 8. 2. 오전 10:25:59
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.scheduling.TaskScheduler;

/**
 * {@link ExecutorService} 구현 객체를 'delegate'로써 사용하는 'Delegating' {@link ExecutorService}를 제공하는 클래스.
 * 
 * @since 2025. 8. 2.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class DelegatingExecutorSupportor {

    private DelegatingExecutorSupportor() {
    }

    public static ExecutorService executorService(ExecutorService delegate) {
        return new DelegatingExecutorService<ExecutorService>(delegate, "@executor");
    }

    public static ExecutorService executorService(ExecutorService delegate, String threadSymbol) {
        return new DelegatingExecutorService<ExecutorService>(delegate, threadSymbol);
    }

    public static ScheduledExecutorService scheduledExecutorService(ScheduledExecutorService delegate) {
        return new DelegatingScheduledExecutorService(delegate, "@scheduled");
    }

    public static ScheduledExecutorService scheduledExecutorService(ScheduledExecutorService delegate, String threadSymbol) {
        return new DelegatingScheduledExecutorService(delegate, threadSymbol);
    }

    public static TaskScheduler taskScheduler(TaskScheduler delegate) {
        return new DelegatingTaskScheduler<TaskScheduler>(delegate, "@scheduled");
    }

    public static TaskScheduler taskScheduler(TaskScheduler delegate, String threadSymbol) {
        return new DelegatingTaskScheduler<TaskScheduler>(delegate, threadSymbol);
    }
}
