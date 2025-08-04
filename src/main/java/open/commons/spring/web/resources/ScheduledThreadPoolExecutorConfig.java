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
 * Date  : 2025. 8. 1. 오후 4:38:45
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.resources;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * {@link ThreadPoolExecutor} 설정 정보 클래스.
 * 
 * @since 2025. 8. 1.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ScheduledThreadPoolExecutorConfig implements Cloneable {

    // -- java.util.concurrent.ThreadPoolExecutor -- //
    /**
     * the number of threads to keep in the pool, even if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * 
     * @see ThreadPoolExecutor#setCorePoolSize(int)
     */
    private int corePoolSize;
    /**
     * the maximum number of threads to allow in the pool
     * 
     * @see ThreadPoolExecutor#setMaximumPoolSize(int)
     */
    private int maximumPoolSize = Integer.MAX_VALUE;
    /**
     * when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait
     * for new tasks before terminating.
     * 
     * @see ThreadPoolExecutor#setKeepAliveTime(long, java.util.concurrent.TimeUnit)
     * @see #timeUnit
     */
    private long keepAliveTime;
    /**
     * the time timeUnit for the {@code keepAliveTime} argument
     * 
     * @see ThreadPoolExecutor#setKeepAliveTime(long, java.util.concurrent.TimeUnit)
     * @see #keepAliveTime
     */
    private TimeUnit timeUnit = TimeUnit.NANOSECONDS;
    // --------------------------------------------- //

    // -- java.util.concurrent.ScheduledThreadPoolExecutor -- //

    /**
     * If false (default), core threads stay alive even when idle. If true, core threads use keepAliveTime to time out
     * waiting for work.
     * 
     * @see ThreadPoolExecutor#allowCoreThreadTimeOut(boolean)
     */
    private boolean allowCoreThreadTimeOut;
    /**
     * if {@code true}, continue after shutdown, else don't
     * 
     * @see ScheduledThreadPoolExecutor#setContinueExistingPeriodicTasksAfterShutdownPolicy(boolean)
     */
    private boolean continueExistingPeriodicTasksAfterShutdown;
    /**
     * if {@code true}, execute after shutdown, else don't
     * 
     * @see ScheduledThreadPoolExecutor#setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean)
     */
    private boolean executeExistingDelayedTasksAfterShutdown = true;
    /**
     * if {@code true}, remove on cancellation, else don't
     * 
     * @see ScheduledThreadPoolExecutor#setRemoveOnCancelPolicy(boolean)
     */
    private boolean removeOnCancel = false;
    // --------------------------------------------- //

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
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ScheduledThreadPoolExecutorConfig() {
    }

    /**
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

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
     * @return the corePoolSize
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #corePoolSize
     */

    public int getCorePoolSize() {
        return corePoolSize;
    }

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
     * @return the keepAliveTime
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #keepAliveTime
     */

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

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
     * @return the maximumPoolSize
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #maximumPoolSize
     */

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

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
     * @return the timeUnit
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #timeUnit
     */

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

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
     * @return the allowCoreThreadTimeOut
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #allowCoreThreadTimeOut
     */

    public boolean isAllowCoreThreadTimeOut() {
        return allowCoreThreadTimeOut;
    }

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
     * @return the continueExistingPeriodicTasksAfterShutdown
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #continueExistingPeriodicTasksAfterShutdown
     */

    public boolean isContinueExistingPeriodicTasksAfterShutdown() {
        return continueExistingPeriodicTasksAfterShutdown;
    }

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
     * @return the executeExistingDelayedTasksAfterShutdown
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #executeExistingDelayedTasksAfterShutdown
     */

    public boolean isExecuteExistingDelayedTasksAfterShutdown() {
        return executeExistingDelayedTasksAfterShutdown;
    }

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
     * @return the removeOnCancel
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #removeOnCancel
     */

    public boolean isRemoveOnCancel() {
        return removeOnCancel;
    }

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
     * @param allowCoreThreadTimeOut
     *            the allowCoreThreadTimeOut to set
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #allowCoreThreadTimeOut
     */
    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

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
     * @param continueExistingPeriodicTasksAfterShutdown
     *            the continueExistingPeriodicTasksAfterShutdown to set
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #continueExistingPeriodicTasksAfterShutdown
     */
    public void setContinueExistingPeriodicTasksAfterShutdown(boolean continueExistingPeriodicTasksAfterShutdown) {
        this.continueExistingPeriodicTasksAfterShutdown = continueExistingPeriodicTasksAfterShutdown;
    }

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
     * @param corePoolSize
     *            the corePoolSize to set
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #corePoolSize
     */
    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

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
     * @param executeExistingDelayedTasksAfterShutdown
     *            the executeExistingDelayedTasksAfterShutdown to set
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #executeExistingDelayedTasksAfterShutdown
     */
    public void setExecuteExistingDelayedTasksAfterShutdown(boolean executeExistingDelayedTasksAfterShutdown) {
        this.executeExistingDelayedTasksAfterShutdown = executeExistingDelayedTasksAfterShutdown;
    }

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
     * @param keepAliveTime
     *            the keepAliveTime to set
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #keepAliveTime
     */
    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

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
     * @param maximumPoolSize
     *            the maximumPoolSize to set
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #maximumPoolSize
     */
    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

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
     * @param removeOnCancel
     *            the removeOnCancel to set
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #removeOnCancel
     */
    public void setRemoveOnCancel(boolean removeOnCancel) {
        this.removeOnCancel = removeOnCancel;
    }

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
     * @param timeUnit
     *            the timeUnit to set
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #timeUnit
     */
    public void setTimeUnit(TimeUnit unit) {
        this.timeUnit = unit;
    }

    /**
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ScheduledThreadPoolExecutorConfig [corePoolSize=");
        builder.append(corePoolSize);
        builder.append(", maximumPoolSize=");
        builder.append(maximumPoolSize);
        builder.append(", keepAliveTime=");
        builder.append(keepAliveTime);
        builder.append(", timeUnit=");
        builder.append(timeUnit);
        builder.append(", allowCoreThreadTimeOut=");
        builder.append(allowCoreThreadTimeOut);
        builder.append(", continueExistingPeriodicTasksAfterShutdown=");
        builder.append(continueExistingPeriodicTasksAfterShutdown);
        builder.append(", executeExistingDelayedTasksAfterShutdown=");
        builder.append(executeExistingDelayedTasksAfterShutdown);
        builder.append(", removeOnCancel=");
        builder.append(removeOnCancel);
        builder.append("]");
        return builder.toString();
    }
}
