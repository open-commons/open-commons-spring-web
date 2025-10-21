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
 * Date  : 2025. 7. 31. 오후 5:32:17
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.mdc.MdcWrappedJob;

/**
 * 
 * @since 2025. 7. 31.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class DelegatingExecutorService<S extends ExecutorService> extends AbstractExecutorService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /** 실제 {@link ExecutorService} 기능을 제공하는 객체 */
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
     * 2025. 7. 31.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param delegate
     *            {@link ExecutorService} 기능을 제공하는 객체
     * @param symbol
     *            {@link Thread} 이름 뒤에 붙여서 식별정보로 활용
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public DelegatingExecutorService(@Nonnull S delegate, @Nullable String symbol) {
        this.delegate = delegate;
        this.symbol = symbol;
    }

    /**
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ExecutorService#awaitTermination(long, java.util.concurrent.TimeUnit)
     */
    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.delegate.awaitTermination(timeout, unit);
    }

    /**
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.Executor#execute(java.lang.Runnable)
     */
    @Override
    public void execute(Runnable command) {
        if (command instanceof MdcWrappedJob //
                || command instanceof RunnableFuture) {
            logger.trace("Skipping wrap for already-wrapped or FutureTask: {}", command.getClass());
            this.delegate.execute(command);
        } else {
            this.delegate.execute(wrap(command));
        }
    }

    /**
     * 현재 시점의 MDC 정보를 복제해서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 2.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param symbol
     * @return
     *
     * @since 2025. 8. 2.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected final Map<String, String> getCopyOfContextMap(String symbol) {
        Map<String, String> copiedMDC = MDC.getCopyOfContextMap();
        if (copiedMDC != null && !StringUtils.isNullOrEmptyString(symbol)) {
            copiedMDC.put(MdcWrappedJob.MDC_PROPERTY_THREAD_SYMBOL, symbol);
        }

        return copiedMDC;
    }

    /**
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.AbstractExecutorService#invokeAny(java.util.Collection)
     */
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return super.invokeAny(wrap(tasks));
    }

    /**
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.AbstractExecutorService#invokeAny(java.util.Collection, long,
     *      java.util.concurrent.TimeUnit)
     */
    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return super.invokeAny(wrap(tasks), timeout, unit);
    }

    /**
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ExecutorService#isShutdown()
     */
    @Override
    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    /**
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ExecutorService#isTerminated()
     */
    @Override
    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    /**
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.AbstractExecutorService#newTaskFor(java.util.concurrent.Callable)
     */
    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<>(wrap(callable));
    }

    /**
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.AbstractExecutorService#newTaskFor(java.lang.Runnable, java.lang.Object)
     */
    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new FutureTask<>(wrap(runnable), value);
    }

    /**
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ExecutorService#shutdown()
     */
    @Override
    public void shutdown() {
        this.delegate.shutdown();
    }

    /**
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.util.concurrent.ExecutorService#shutdownNow()
     */
    @Override
    public List<Runnable> shutdownNow() {
        return this.delegate.shutdownNow();
    }

    /**
     * 전달받은 {@link Callable} 객체가 {@link MDC} 정보를 사용할 수 있도록 감싼 {@link Callable} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 31.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param callable
     * @return
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <T> Callable<T> wrap(Callable<T> callable) {
        return MdcWrappedJob.wrap(getCopyOfContextMap(this.symbol), callable);
    }

    /**
     * 전달받은 {@link Callable} 객체가 {@link MDC} 정보를 사용할 수 있도록 감싼 {@link Callable} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 31.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param tasks
     * @return
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <T> Collection<? extends Callable<T>> wrap(Collection<? extends Callable<T>> tasks) {
        return MdcWrappedJob.wrap(getCopyOfContextMap(this.symbol), tasks);
    }

    /**
     * 전달받은 {@link Runnable} 객체가 {@link MDC} 정보를 사용할 수 있도록 감싼 {@link Runnable} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 31.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param runnable
     * @return
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Runnable wrap(Runnable runnable) {
        return MdcWrappedJob.wrap(getCopyOfContextMap(this.symbol), runnable, false);
    }

}
