/*
 * Copyright 2019 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2019. 7. 17. 오후 4:50:36
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.resources;

import javax.validation.constraints.Min;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * {@link ThreadPoolTaskExecutor} 설정 정보 클래스.
 * 
 * @since 2019. 7. 17.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ThreadPoolTaskExecutorConfig implements Cloneable {

    // --- org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor --- //
    private int corePoolSize = 1;
    private int keepAliveSeconds = 60;
    private int maxPoolSize = Integer.MAX_VALUE;
    private int queueCapacity = Integer.MAX_VALUE;
    private boolean allowCoreThreadTimeOut = false;
    private boolean prestartAllCoreThreads = false;
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

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @since 2019. 7. 17.
     * @version
     */
    public ThreadPoolTaskExecutorConfig() {
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
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the beanName
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #beanName
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * Return the ThreadPoolExecutor's core pool size. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the corePoolSize
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #corePoolSize
     */
    public int getCorePoolSize() {
        return corePoolSize;
    }

    /**
     * Return the ThreadPoolExecutor's keep-alive seconds. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the keepAliveSeconds
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #keepAliveSeconds
     */
    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    /**
     * Return the ThreadPoolExecutor's maximum pool size. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the maxPoolSize
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #maxPoolSize
     * @see java.util.concurrent.ThreadPoolExecutor#getPoolSize()
     */
    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the queueCapacity
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #queueCapacity
     */
    public int getQueueCapacity() {
        return queueCapacity;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the threadGroupName
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #threadGroupName
     */
    public String getThreadGroupName() {
        return threadGroupName;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the threadNamePrefix
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #threadNamePrefix
     */
    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the threadPriority
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #threadPriority
     */
    public int getThreadPriority() {
        return threadPriority;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the allowCoreThreadTimeOut
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #allowCoreThreadTimeOut
     */
    public boolean isAllowCoreThreadTimeOut() {
        return allowCoreThreadTimeOut;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the daemon
     *
     * @since 2019. 7. 17.
     * @version
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
     * @return the prestartAllCoreThreads
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #prestartAllCoreThreads
     */

    public boolean isPrestartAllCoreThreads() {
        return prestartAllCoreThreads;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     * 
     * @return the waitForTasksToCompleteOnShutdown
     *
     * @since 2019. 7. 17.
     * @version
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
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param allowCoreThreadTimeOut
     *            the allowCoreThreadTimeOut to set
     *
     * @since 2019. 7. 17.
     * @version
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
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param awaitTerminationSeconds
     *            the awaitTerminationSeconds to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #awaitTerminationSeconds
     */
    public void setAwaitTerminationSeconds(@Min(0) int awaitTerminationSeconds) {
        this.awaitTerminationMillis = awaitTerminationSeconds * 1000L;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param beanName
     *            the beanName to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #beanName
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param corePoolSize
     *            the corePoolSize to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #corePoolSize
     */
    public void setCorePoolSize(@Min(1) int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param daemon
     *            the daemon to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #daemon
     */
    public void setDaemon(boolean daemon) {
        this.daemon = daemon;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param keepAliveSeconds
     *            the keepAliveSeconds to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #keepAliveSeconds
     */
    public void setKeepAliveSeconds(@Min(1) int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param maxPoolSize
     *            the maxPoolSize to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #maxPoolSize
     */
    public void setMaxPoolSize(@Min(1) int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
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
     * @param prestartAllCoreThreads
     *            the prestartAllCoreThreads to set
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #prestartAllCoreThreads
     */
    public void setPrestartAllCoreThreads(boolean prestartAllCoreThreads) {
        this.prestartAllCoreThreads = prestartAllCoreThreads;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param queueCapacity
     *            the queueCapacity to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #queueCapacity
     */
    public void setQueueCapacity(@Min(1) int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param threadGroupName
     *            the threadGroupName to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #threadGroupName
     */
    public void setThreadGroupName(String threadGroupName) {
        this.threadGroupName = threadGroupName;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param threadNamePrefix
     *            the threadNamePrefix to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #threadNamePrefix
     */
    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param threadPriority
     *            the threadPriority to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #threadPriority
     */
    public void setThreadPriority(int threadPriority) {
        this.threadPriority = threadPriority;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 7. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param waitForTasksToCompleteOnShutdown
     *            the waitForTasksToCompleteOnShutdown to set
     *
     * @since 2019. 7. 17.
     * @version
     * 
     * @see #waitForTasksToCompleteOnShutdown
     */
    public void setWaitForTasksToCompleteOnShutdown(boolean waitForTasksToCompleteOnShutdown) {
        this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;
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
        builder.append("ThreadPoolTaskExecutorConfig [corePoolSize=");
        builder.append(corePoolSize);
        builder.append(", keepAliveSeconds=");
        builder.append(keepAliveSeconds);
        builder.append(", maxPoolSize=");
        builder.append(maxPoolSize);
        builder.append(", queueCapacity=");
        builder.append(queueCapacity);
        builder.append(", allowCoreThreadTimeOut=");
        builder.append(allowCoreThreadTimeOut);
        builder.append(", prestartAllCoreThreads=");
        builder.append(prestartAllCoreThreads);
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
