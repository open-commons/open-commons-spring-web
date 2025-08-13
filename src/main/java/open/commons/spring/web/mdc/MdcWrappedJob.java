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
 * Date  : 2025. 8. 1. 오후 1:33:02
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.mdc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.TaskScheduler;

import open.commons.core.function.ExceptionableSupplier;
import open.commons.core.utils.FunctionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.core.utils.ThreadUtils;
import open.commons.spring.web.aspect.LogFeatureAspect;

/**
 * 
 * @since 2025. 8. 1.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class MdcWrappedJob<V> {

    /** {@link Thread} 이름 뒤에 붙여서 사용될 {@link MDC}내의 속성이름. */
    public static final String MDC_PROPERTY_THREAD_SYMBOL = String.join("::", "symbol", UUID.randomUUID().toString());
    /** {@link #MDC_PROPERTY_THREAD_SYMBOL} 기본값 */
    private static final String SYMBOL = "@mdc-wrapped";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /** {@link #MDC_PROPERTY_THREAD_SYMBOL} 값 */
    private String forwardedThreadSymbol;

    /** 작업이 수행될 때 공유할 {@link MDC} 정보 */
    @Nullable
    protected final Map<String, String> forwardedMDC;
    /** 동작하는 시점 {@link Thread} {@link MDC} 정보 */
    private Map<String, String> runtimeMDC;
    /** 작업을 수행시키는 Executor 시점 {@link Thread} 이름 */
    private String executorThreadName;
    /** 동작하는 시점 {@link Thread} 이름 */
    private String runtimeThreadNumberSelector;
    /** 동작하는 시점 {@link Thread} 번호 */
    private int runtimeTaskNumber = Integer.MIN_VALUE;

    /**
     * {@link TaskScheduler} 또는 {@link ScheduledExecutorService} 에 의해서 실행되는지 여부<br>
     * {@link Runnable}를 반복적으로 생성하지 않기 때문에, 전달받은 {@link MDC} 복제 데이터를 유지해야 함.
     */
    private boolean byScheduler;

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 1.      박준홍         최초 작성
     * </pre>
     *
     * @param mdc
     *            작업이 수행될 때 공유할 {@link MDC} 정보
     * @param byScheduler
     *            TODO
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    protected MdcWrappedJob(@Nullable Map<String, String> mdc, boolean byScheduler) {
        this.forwardedMDC = mdc;
        if (this.forwardedMDC != null) {
            FunctionUtils.runIf(this.forwardedMDC.get(MDC_PROPERTY_THREAD_SYMBOL) //
                    , (Predicate<String>) v -> StringUtils.isNullOrEmptyString(v) //
                    , (Consumer<String>) v -> this.forwardedThreadSymbol = SYMBOL //
                    , (Consumer<String>) v -> this.forwardedThreadSymbol = v.trim()//
            );
        }

        this.byScheduler = byScheduler;
    }

    protected final void afterExecute() {
        if (this.runtimeTaskNumber != Integer.MIN_VALUE) {
            TaskNumberManager.release(this.runtimeThreadNumberSelector, this.runtimeTaskNumber);
        }

        ThreadUtils.setThreadName(this.executorThreadName);
        if (this.runtimeMDC != null) {
            MDC.setContextMap(this.runtimeMDC);
        } else {
            MDC.clear();
        }
        if (this.forwardedMDC != null && !this.byScheduler) {
            forwardedMDC.clear();
        }
    }

    /**
     * 동작하는 시점 {@link Thread}의 {@link MDC} 정보를 백업하고, 외부 {@link MDC} 정보를 현재 {@link MDC} 정보로 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 1.      박준홍         최초 작성
     * </pre>
     *
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected final void beforeExecute() {
        // 동작하는 시점의 Thread MDC 백업
        this.runtimeMDC = MDC.getCopyOfContextMap();
        // MDC 변경
        if (this.forwardedMDC != null) {
            logger.trace("[mdc-wrapped-job] updated MDC. -> {}", this.forwardedMDC);
            MDC.setContextMap(this.forwardedMDC);
        }

        // 외부에서 전달한 Thread쓰레드 이름 확인
        this.executorThreadName = Thread.currentThread().getName();
        String intcptThreadName = MDC.get(LogFeatureAspect.FORWARDED_THREAD_NAME);
        if (StringUtils.isNullOrEmptyString(intcptThreadName)) {
            ThreadUtils.setThreadName(executorThreadName + "@" + this.forwardedThreadSymbol);
        } else {
            this.runtimeTaskNumber = TaskNumberManager.acquire(runtimeThreadNumberSelector = byScheduler ? intcptThreadName : this.executorThreadName);
            ThreadUtils.setThreadName(intcptThreadName //
                    + (runtimeTaskNumber != -1 ? "-" + runtimeTaskNumber : "") // 번호가 '0'인 경우 붙이지 않음.
                    + "@" + forwardedThreadSymbol);
        }
    }

    protected V execute(ExceptionableSupplier<V> job) throws Exception {
        try {
            beforeExecute();
            return job.get();
        } finally {
            afterExecute();
        }
    }

    /**
     * 현재 {@link Thread} {@link MDC} 정보를 복제하고, {@link #MDC_PROPERTY_THREAD_SYMBOL} 값을 추가하여 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param symbol
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static final Map<String, String> getCopyOfContextMap(String symbol) {
        Map<String, String> copiedMDC = MDC.getCopyOfContextMap();
        if (copiedMDC != null && !StringUtils.isNullOrEmptyString(symbol)) {
            copiedMDC.put(MdcWrappedJob.MDC_PROPERTY_THREAD_SYMBOL, symbol);
        }

        return copiedMDC;
    }

    /**
     * 전달받은 {@link Callable} 객체가 {@link MDC} 정보를 사용할 수 있도록 감싼 {@link Callable} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 7. 31.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param context
     *            작업이 수행될 때 공유할 {@link MDC} 정보
     * @param callable
     *            작업 객체
     * @return
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <T> Callable<T> wrap(Map<String, String> context, Callable<T> callable) {
        return new MdcWrappedCallable<T>(context != null ? new HashMap<>(context) : null, callable);
    }

    /**
     * 전달받은 {@link Callable} 객체가 {@link MDC} 정보를 사용할 수 있도록 감싼 {@link Callable} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 7. 31.     박준홍         최초 작성
     * </pre>
     * 
     * @param context
     *            작업이 수행될 때 공유할 {@link MDC} 정보
     * @param tasks
     *            작업 객체
     *
     * @param <T>
     * @return
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <T> Collection<? extends Callable<T>> wrap(Map<String, String> context, Collection<? extends Callable<T>> tasks) {
        return tasks.stream().map(task -> MdcWrappedJob.wrap(context, task)).collect(Collectors.toList());
    }

    /**
     * 전달받은 {@link Runnable} 객체가 {@link MDC} 정보를 사용할 수 있도록 감싼 {@link Runnable} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 7. 31.     박준홍         최초 작성
     * </pre>
     * 
     * @param context
     *            작업이 수행될 때 공유할 {@link MDC} 정보
     * @param runnable
     *            작업 객체
     * @param byScheduler
     *            {@link TaskScheduler} 또는 {@link ScheduledExecutorService}에 의해서 실행되는지 여부
     * @return
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static Runnable wrap(Map<String, String> context, Runnable runnable, boolean byScheduler) {
        return new MdcWrappedRunnable(context != null ? new HashMap<>(context) : null, runnable, byScheduler);
    }

    private static class MdcWrappedCallable<V> extends MdcWrappedJob<V> implements Callable<V> {
        @Nonnull
        private final Callable<V> callable;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 8. 1.      박준홍         최초 작성
         * </pre>
         *
         * @param mdc
         *            작업이 수행될 때 공유할 {@link MDC} 정보
         * @param callable
         *            작업 객체
         * @since 2025. 8. 1.
         * @version 0.8.0
         * @author parkjunhong77@gmail.com
         */
        MdcWrappedCallable(Map<String, String> mdc, @NotNull @Nonnull Callable<V> callable) {
            super(mdc, false);
            this.callable = callable;
        }

        /**
         *
         * @since 2025. 8. 1.
         * @version 0.8.0
         * @author parkjunhong77@gmail.com
         *
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        public V call() throws Exception {
            return execute(() -> this.callable.call());
        }
    }

    private static class MdcWrappedRunnable extends MdcWrappedJob<Void> implements Runnable {
        @Nonnull
        private final Runnable runnable;

        /**
         * 
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 8. 1.      박준홍         최초 작성
         * </pre>
         *
         * @param mdc
         *            작업이 수행될 때 공유할 {@link MDC} 정보
         * @param runnable
         *            작업 객체
         * @param byScheduler
         *            {@link TaskScheduler} 또는 {@link ScheduledExecutorService}에 의해서 실행되는지 여부
         * @since 2025. 8. 1.
         * @version 0.8.0
         * @author parkjunhong77@gmail.com
         */
        public MdcWrappedRunnable(@Nullable Map<String, String> mdc, @Nonnull Runnable runnable, boolean byScheduler) {
            super(mdc, byScheduler);
            this.runnable = runnable;
        }

        /**
         *
         * @since 2025. 8. 1.
         * @version 0.8.0
         * @author parkjunhong77@gmail.com
         *
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            try {
                execute(() -> {
                    this.runnable.run();
                    return null;
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class TaskNumberManager {

        private static final ConcurrentMap<String, NumberPool> POOLS = new ConcurrentHashMap<>();

        // 번호 발급: 재활용된 번호가 있으면 그 중 최소값, 없으면 새 번호
        public static int acquire(String key) {
            NumberPool pool = POOLS.computeIfAbsent(key, k -> new NumberPool());

            synchronized (pool) {
                pool.activated.incrementAndGet();// 활성화된 개수 증가
                if (!pool.recycled.isEmpty()) {
                    int i = pool.recycled.poll(); // 가장 작은 반환된 번호 재사용
                    return pool.activated.intValue() < 2 ? -1 : i;
                } else {
                    int i = pool.counter.incrementAndGet();
                    return i == 1 ? -1 : i;
                }
            }
        }

        // 번호 반환: 재사용을 위해 recycled 목록에 추가
        public static void release(String key, int number) {
            NumberPool pool = POOLS.get(key);
            if (pool == null) {
                return; // 없는 key는 무시
            }

            synchronized (pool) {
                if (number == -1) {
                    number = 1;
                }
                pool.recycled.offer(number); // 반환된 번호 저장
                pool.activated.decrementAndGet(); // 활성화된 개수 감수
            }
        }

        // 내부 구조
        private static class NumberPool {
            private final AtomicInteger counter = new AtomicInteger(0);
            private final PriorityQueue<Integer> recycled = new PriorityQueue<>();
            private final AtomicInteger activated = new AtomicInteger(0);
        }
    }
}
