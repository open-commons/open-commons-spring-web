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
 * Date  : 2025. 8. 3. 오후 3:49:42
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.concurrent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.ScheduledMethodRunnable;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.aspect.LogFeatureAspect;
import open.commons.spring.web.log.LogFeature;
import open.commons.spring.web.mdc.MdcWrappedJob;

/**
 * 
 * @since 2025. 8. 3.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class DelegatingTaskScheduler<S extends TaskScheduler> implements TaskScheduler {

    protected final Logger logger = LoggerFactory.getLogger(DelegatingTaskScheduler.class);

    /** {@link TaskScheduler} 기능을 제공하는 객체. */
    protected final S delegate;
    /** {@link Thread} 이름 뒤에 붙여서 식별정보로 활용 */
    protected final String symbol;

    /**
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
     * @param symbol
     *            {@link Thread} 이름 뒤에 붙여서 식별정보로 활용
     *
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public DelegatingTaskScheduler(@Nonnull S delegate, @Nullable String symbol) {
        this.delegate = delegate;
        this.symbol = symbol;
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.scheduling.TaskScheduler#schedule(java.lang.Runnable, java.util.Date)
     */
    @Override
    public ScheduledFuture<?> schedule(Runnable task, Date startTime) {
        return this.delegate.schedule(wrap(task), startTime);
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.scheduling.TaskScheduler#schedule(java.lang.Runnable,
     *      org.springframework.scheduling.Trigger)
     */
    @Override
    public ScheduledFuture<?> schedule(Runnable task, Trigger trigger) {
        return this.delegate.schedule(wrap(task), trigger);
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleAtFixedRate(java.lang.Runnable, java.util.Date, long)
     */
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, Date startTime, long period) {
        return this.delegate.scheduleAtFixedRate(wrap(task), startTime, period);
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleAtFixedRate(java.lang.Runnable, long)
     */
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long period) {
        return this.delegate.scheduleAtFixedRate(wrap(task), period);
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleWithFixedDelay(java.lang.Runnable, java.util.Date,
     *      long)
     */
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, Date startTime, long delay) {
        return this.delegate.scheduleWithFixedDelay(wrap(task), startTime, delay);
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.scheduling.TaskScheduler#scheduleWithFixedDelay(java.lang.Runnable, long)
     */
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long delay) {
        return this.delegate.scheduleWithFixedDelay(wrap(task), delay);
    }

    /**
     * {@link LogFeature#thread()} 설정된 값을 {@link MDC} 복제정보체 추가하여 전달합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param runnable
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
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

    protected static String findSpecifiedThreadName(Runnable runnable) {
        // ScheduledMethodRunnable
        if (runnable instanceof ScheduledMethodRunnable) {
            ScheduledMethodRunnable r = (ScheduledMethodRunnable) runnable;
            LogFeature anno = AnnotationUtils.findAnnotation(r.getMethod(), LogFeature.class);
            if (anno != null) {
                return StringUtils.isNullOrEmptyString(anno.thread()) ? null : anno.thread().trim();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
