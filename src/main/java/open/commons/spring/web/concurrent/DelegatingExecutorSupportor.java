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
 * Date  : 2025. 8. 2. 오전 10:25:59
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.Nonnull;

import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.TaskExecutor;
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

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param delegate
     *            {@link ExecutorService} 기능을 제공하는 객체.
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static ExecutorService executorService(ExecutorService delegate) {
        return executorService(delegate, "executor");
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param delegate
     *            {@link ExecutorService} 기능을 제공하는 객체.
     * @param threadSymbol
     *            {@link Thread} 이름 뒤에 붙여서 식별정보로 활용
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static ExecutorService executorService(ExecutorService delegate, @Nonnull String threadSymbol) {
        return new DelegatingExecutorService<ExecutorService>(delegate, threadSymbol);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param delegate
     *            {@link ScheduledExecutorService} 기능을 제공하는 객체.
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static ScheduledExecutorService scheduledExecutorService(ScheduledExecutorService delegate) {
        return scheduledExecutorService(delegate, "scheduled");
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param delegate
     *            {@link ScheduledExecutorService} 기능을 제공하는 객체.
     * @param threadSymbol
     *            {@link Thread} 이름 뒤에 붙여서 식별정보로 활용
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static ScheduledExecutorService scheduledExecutorService(ScheduledExecutorService delegate, @Nonnull String threadSymbol) {
        return new DelegatingScheduledExecutorService(delegate, threadSymbol);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 6.		박준홍			최초 작성
     * </pre>
     *
     * {@link TaskExecutor} 기능을 제공하는 객체.
     * 
     * @return
     *
     * @since 2025. 8. 6.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static AsyncListenableTaskExecutor taskExecutor(AsyncListenableTaskExecutor delegate) {
        return taskExecutor(delegate, "executor");
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 6.		박준홍			최초 작성
     * </pre>
     *
     * @param delegate
     *            {@link TaskExecutor} 기능을 제공하는 객체.
     * @param threadSymbol
     *            {@link Thread} 이름 뒤에 붙여서 식별정보로 활용
     * @return
     *
     * @since 2025. 8. 6.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static AsyncListenableTaskExecutor taskExecutor(AsyncListenableTaskExecutor delegate, @Nonnull String threadSymbol) {
        return new DelegatingTaskExecutor<AsyncListenableTaskExecutor>(delegate, threadSymbol);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param delegate
     *            {@link TaskScheduler} 기능을 제공하는 객체.
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <S extends TaskScheduler & AsyncListenableTaskExecutor> TaskScheduler taskScheduler(S delegate) {
        return taskScheduler(delegate, "scheduled");
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param delegate
     *            {@link TaskScheduler} 기능을 제공하는 객체.
     * @param threadSymbol
     *            {@link Thread} 이름 뒤에 붙여서 식별정보로 활용
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <S extends TaskScheduler & AsyncListenableTaskExecutor> TaskScheduler taskScheduler(S delegate, @Nonnull String threadSymbol) {
        return new DelegatingTaskScheduler<S>(delegate, threadSymbol);
    }

}
