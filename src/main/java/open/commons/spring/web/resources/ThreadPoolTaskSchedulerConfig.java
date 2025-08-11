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
 * Date  : 2025. 8. 3. 오후 4:00:28
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.resources;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

/**
 * 
 * @since 2025. 8. 3.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */

public class ThreadPoolTaskSchedulerConfig {

    // -- org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler --//
    private volatile int poolSize = 1;
    private volatile boolean removeOnCancelPolicy;
    private volatile boolean continueExistingPeriodicTasksAfterShutdownPolicy;
    private volatile boolean executeExistingDelayedTasksAfterShutdownPolicy = true;
    // --------------------------------------------- //

    // --- org.springframework.scheduling.concurrent.ExecutorConfigurationSupport --- //
    private long awaitTerminationMillis = 0;
    private String beanName;
    private boolean waitForTasksToCompleteOnShutdown = false;
    // ---------------------------------------------------------- //

    // --- org.springframework.util.CustomizableThreadCreator --- //
    private boolean daemon = false;
    private String threadGroupName;
    private String threadNamePrefix;
    private int threadPriority = Thread.NORM_PRIORITY;
    // -------------------------------------------------- //

    /** 외부 설정 여부 */
    private boolean initialized = false;

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
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ThreadPoolTaskSchedulerConfig() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     *
     * @since 2025. 8. 11.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ThreadPoolTaskSchedulerConfig(@NotNull @Nonnull ThreadPoolTaskSchedulerConfig config) {
        this.poolSize = config.poolSize;
        this.removeOnCancelPolicy = config.removeOnCancelPolicy;
        this.continueExistingPeriodicTasksAfterShutdownPolicy = config.continueExistingPeriodicTasksAfterShutdownPolicy;
        this.executeExistingDelayedTasksAfterShutdownPolicy = config.executeExistingDelayedTasksAfterShutdownPolicy;
        this.awaitTerminationMillis = config.awaitTerminationMillis;
        this.beanName = config.beanName;
        this.waitForTasksToCompleteOnShutdown = config.waitForTasksToCompleteOnShutdown;
        this.daemon = config.daemon;
        this.threadGroupName = config.threadGroupName;
        this.threadNamePrefix = config.threadNamePrefix;
        this.threadPriority = config.threadPriority;
        this.initialized = config.initialized;

    }

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
     * @return the awaitTerminationMillis
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #awaitTerminationMillis
     */

    public long getAwaitTerminationMillis() {
        return awaitTerminationMillis;
    }

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
     * @return the beanName
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #beanName
     */

    public String getBeanName() {
        return beanName;
    }

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
     * @return the poolSize
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #poolSize
     */

    public int getPoolSize() {
        return poolSize;
    }

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
     * @return the threadGroupName
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #threadGroupName
     */

    public String getThreadGroupName() {
        return threadGroupName;
    }

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
     * @return the threadNamePrefix
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #threadNamePrefix
     */

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

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
     * @return the threadPriority
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #threadPriority
     */

    public int getThreadPriority() {
        return threadPriority;
    }

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
     * @return the continueExistingPeriodicTasksAfterShutdownPolicy
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #continueExistingPeriodicTasksAfterShutdownPolicy
     */

    public boolean isContinueExistingPeriodicTasksAfterShutdownPolicy() {
        return continueExistingPeriodicTasksAfterShutdownPolicy;
    }

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
     * @return the daemon
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #daemon
     */

    public boolean isDaemon() {
        return daemon;
    }

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
     * @return the executeExistingDelayedTasksAfterShutdownPolicy
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #executeExistingDelayedTasksAfterShutdownPolicy
     */

    public boolean isExecuteExistingDelayedTasksAfterShutdownPolicy() {
        return executeExistingDelayedTasksAfterShutdownPolicy;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the initialized
     *
     * @since 2025. 8. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #initialized
     */

    public boolean isInitialized() {
        return initialized;
    }

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
     * @return the removeOnCancelPolicy
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #removeOnCancelPolicy
     */

    public boolean isRemoveOnCancelPolicy() {
        return removeOnCancelPolicy;
    }

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
     * @return the waitForTasksToCompleteOnShutdown
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #waitForTasksToCompleteOnShutdown
     */

    public boolean isWaitForTasksToCompleteOnShutdown() {
        return waitForTasksToCompleteOnShutdown;
    }

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
     * @param awaitTerminationMillis
     *            the awaitTerminationMillis to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #awaitTerminationMillis
     */
    public void setAwaitTerminationMillis(long awaitTerminationMillis) {
        this.awaitTerminationMillis = awaitTerminationMillis;

        this.initialized = true;
    }

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
     * @param awaitTerminationSeconds
     *            the awaitTerminationSeconds to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #awaitTerminationSeconds
     */
    public void setAwaitTerminationSeconds(int awaitTerminationSeconds) {
        this.awaitTerminationMillis = awaitTerminationSeconds * 1000L;

        this.initialized = true;
    }

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
     * @param beanName
     *            the beanName to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #beanName
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;

        this.initialized = true;
    }

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
     * @param continueExistingPeriodicTasksAfterShutdownPolicy
     *            the continueExistingPeriodicTasksAfterShutdownPolicy to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #continueExistingPeriodicTasksAfterShutdownPolicy
     */
    public void setContinueExistingPeriodicTasksAfterShutdownPolicy(boolean continueExistingPeriodicTasksAfterShutdownPolicy) {
        this.continueExistingPeriodicTasksAfterShutdownPolicy = continueExistingPeriodicTasksAfterShutdownPolicy;

        this.initialized = true;
    }

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
     * @param daemon
     *            the daemon to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #daemon
     */
    public void setDaemon(boolean daemon) {
        this.daemon = daemon;

        this.initialized = true;
    }

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
     * @param executeExistingDelayedTasksAfterShutdownPolicy
     *            the executeExistingDelayedTasksAfterShutdownPolicy to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #executeExistingDelayedTasksAfterShutdownPolicy
     */
    public void setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean executeExistingDelayedTasksAfterShutdownPolicy) {
        this.executeExistingDelayedTasksAfterShutdownPolicy = executeExistingDelayedTasksAfterShutdownPolicy;

        this.initialized = true;
    }

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
     * @param poolSize
     *            the poolSize to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #poolSize
     */
    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;

        this.initialized = true;
    }

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
     * @param removeOnCancelPolicy
     *            the removeOnCancelPolicy to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #removeOnCancelPolicy
     */
    public void setRemoveOnCancelPolicy(boolean removeOnCancelPolicy) {
        this.removeOnCancelPolicy = removeOnCancelPolicy;

        this.initialized = true;
    }

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
     * @param threadGroupName
     *            the threadGroupName to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #threadGroupName
     */
    public void setThreadGroupName(String threadGroupName) {
        this.threadGroupName = threadGroupName;

        this.initialized = true;
    }

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
     * @param threadNamePrefix
     *            the threadNamePrefix to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #threadNamePrefix
     */
    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;

        this.initialized = true;
    }

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
     * @param threadPriority
     *            the threadPriority to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #threadPriority
     */
    public void setThreadPriority(int threadPriority) {
        this.threadPriority = threadPriority;

        this.initialized = true;
    }

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
     * @param waitForTasksToCompleteOnShutdown
     *            the waitForTasksToCompleteOnShutdown to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #waitForTasksToCompleteOnShutdown
     */
    public void setWaitForTasksToCompleteOnShutdown(boolean waitForTasksToCompleteOnShutdown) {
        this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;

        this.initialized = true;
    }

    /**
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ThreadPoolTaskSchedulerConfig [poolSize=");
        builder.append(poolSize);
        builder.append(", removeOnCancelPolicy=");
        builder.append(removeOnCancelPolicy);
        builder.append(", continueExistingPeriodicTasksAfterShutdownPolicy=");
        builder.append(continueExistingPeriodicTasksAfterShutdownPolicy);
        builder.append(", executeExistingDelayedTasksAfterShutdownPolicy=");
        builder.append(executeExistingDelayedTasksAfterShutdownPolicy);
        builder.append(", awaitTerminationMillis=");
        builder.append(awaitTerminationMillis);
        builder.append(", beanName=");
        builder.append(beanName);
        builder.append(", waitForTasksToCompleteOnShutdown=");
        builder.append(waitForTasksToCompleteOnShutdown);
        builder.append(", daemon=");
        builder.append(daemon);
        builder.append(", threadGroupName=");
        builder.append(threadGroupName);
        builder.append(", threadNamePrefix=");
        builder.append(threadNamePrefix);
        builder.append(", threadPriority=");
        builder.append(threadPriority);
        builder.append("]");
        return builder.toString();
    }
}
