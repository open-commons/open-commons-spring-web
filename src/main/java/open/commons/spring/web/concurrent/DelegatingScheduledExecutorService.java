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
 * Date  : 2025. 8. 1. 오후 6:18:15
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.concurrent;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.support.DelegatingErrorHandlingRunnable;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.aspect.LogFeatureAspect;
import open.commons.spring.web.log.LogFeature;
import open.commons.spring.web.mdc.MdcWrappedJob;

/**
 * 
 * @since 2025. 8. 1.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class DelegatingScheduledExecutorService extends DelegatingExecutorService<ScheduledExecutorService> implements ScheduledExecutorService {

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param delegate
     *            {@link ScheduledExecutorService} 기능을 제공하는 객체
     * @param symbol
     *            {@link Thread} 이름 뒤에 붙여서 식별정보로 활용
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public DelegatingScheduledExecutorService(@Nonnull ScheduledExecutorService delegate, @Nullable String symbol) {
        super(delegate, symbol);
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.Executor#execute(java.lang.Runnable)
     */
    @Override
    public void execute(Runnable command) {
        schedule(command, 0, NANOSECONDS);
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ScheduledExecutorService#schedule(java.util.concurrent.Callable, long,
     *      java.util.concurrent.TimeUnit)
     */
    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        if (!(callable instanceof MdcWrappedJob //
                || callable instanceof RunnableFuture)) {
            callable = wrap(callable);
        }

        return this.delegate.schedule(callable, delay, unit);
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ScheduledExecutorService#schedule(java.lang.Runnable, long,
     *      java.util.concurrent.TimeUnit)
     */
    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        if (!(command instanceof MdcWrappedJob //
                || command instanceof RunnableFuture)) {
            command = wrap(command);
        }

        return this.delegate.schedule(command, delay, unit);
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ScheduledExecutorService#scheduleAtFixedRate(java.lang.Runnable, long, long,
     *      java.util.concurrent.TimeUnit)
     */
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        if (!(command instanceof MdcWrappedJob //
                || command instanceof RunnableFuture)) {
            command = wrap(command);
        }
        return this.delegate.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ScheduledExecutorService#scheduleWithFixedDelay(java.lang.Runnable, long, long,
     *      java.util.concurrent.TimeUnit)
     */
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        if (!(command instanceof MdcWrappedJob //
                || command instanceof RunnableFuture)) {
            logger.trace("Wrap {} to forward MDC.", command);
            command = wrap(command);
        }
        return this.delegate.scheduleAtFixedRate(command, initialDelay, delay, unit);
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ExecutorService#submit(java.util.concurrent.Callable)
     */
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return schedule(task, 0, NANOSECONDS);
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ExecutorService#submit(java.lang.Runnable)
     */
    @Override
    public Future<?> submit(Runnable task) {
        return schedule(task, 0, NANOSECONDS);
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ExecutorService#submit(java.lang.Runnable, java.lang.Object)
     */
    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return schedule(Executors.callable(wrap(task), result), 0, NANOSECONDS);
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.concurrent.DelegatingExecutorService#wrap(java.lang.Runnable)
     */
    @Override
    protected Runnable wrap(Runnable runnable) {
        String tn = findSpecifiedThreadName(runnable);
        Map<String, String> executorMDC = MdcWrappedJob.getCopyOfContextMap(symbol);
        if (executorMDC == null) {
            executorMDC = new HashMap<>();
        }
        executorMDC.put(LogFeatureAspect.FORWARDED_THREAD_NAME, tn);
        Runnable wrapped = MdcWrappedJob.wrap(executorMDC, runnable, true);

        logger.trace("Wrap {} to 'executor' MDC.", runnable);

        return wrapped;
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
     * @param runnable
     *            {@link DelegatingErrorHandlingRunnable} 객체
     * 
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected static String findSpecifiedThreadName(Runnable runnable) {
        // ReschedulingRunnable: @SCheduled#cron() 이 아닌 다른 설정인 경우
        // DelegatingErrorHandlingRunnable: @Scheduled(initialDelay = 1, fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
        if (runnable instanceof DelegatingErrorHandlingRunnable) {
            try {
                Field delegateField = getFieldRecursively(runnable.getClass(), "delegate");
                delegateField.setAccessible(true);
                Object scheduledMethodRunnable = delegateField.get(runnable);

                if (scheduledMethodRunnable != null) {
                    Field methodField = scheduledMethodRunnable.getClass().getDeclaredField("method");
                    methodField.setAccessible(true);
                    Method method = (Method) methodField.get(scheduledMethodRunnable);
                    LogFeature anno = AnnotationUtils.findAnnotation(method, LogFeature.class);
                    if (anno != null) {
                        return StringUtils.isNullOrEmptyString(anno.thread()) ? null : anno.thread().trim();
                    } else {
                        return null;
                    }
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    protected static Field getFieldRecursively(Class<?> clazz, String fieldName) {
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException ignored) {
                clazz = clazz.getSuperclass(); // 상위 클래스에서 계속 탐색
            }
        }
        throw new IllegalStateException("Field '" + fieldName + "' not found in class hierarchy.");
    }
}
