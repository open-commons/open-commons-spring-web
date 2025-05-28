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
 * Date  : 2019. 6. 27. 오후 1:16:53
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import open.commons.spring.web.resources.RestTemplateRequestFactoryResource;
import open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig;
import open.commons.spring.web.rest.RestUtils;
import open.commons.spring.web.servlet.binder.ExceptionHttpStatusBinder;

/**
 * 
 * @since 2019. 6. 27.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@Configuration
public class ResourceConfiguration {

    public static final String BEAN_QUALIFIER_RESTTEMPLATE = "open.commons.spring.web.config.ResourceConfiguration#RESTTEMPLATE";
    public static final String BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA = "open.commons.spring.web.config.ResourceConfiguration#RESTTEMPLATE_ALLOW_PRIVATE_CA";
    public static final String BEAN_QUALIFIER_RESTTEMPLATE_REQUEST_SOURCE = "open.commons.spring.web.config.ResourceConfiguration#BEAN_QUALIFIER_RESTTEMPLATE_REQUEST_SOURCE";
    public static final String BEAN_QUALIFIER_THREAD_POOL = "open.commons.spring.web.config.ResourceConfiguration#THREADPOOL_TASK_EXECUTOR";
    public static final String BEAN_QUALIFIER_THREAD_POOL_CONFIG = "open.commons.spring.web.config.ResourceConfiguration#BEAN_QUALIFIER_THREAD_POOL_CONFIG";

    /** {@link Throwable} 과 그에 따르는 {@link HttpStatus} 매핑 제공 서비스 */
    public static final String BEAN_QUALIFIER_EXCETPION_HTTPSTATUS_PROPERTIES = "open.commons.spring.web.config.ResourceConfiguration#EXCETPION_HTTPSTATUS_PROPERTIES";
    /** {@link Throwable} 과 그에 따르는 {@link HttpStatus} 매핑 설정 */
    public static final String CONFIGURATION_PROPERTIES_EXCETPION_HTTPSTATUS_BINDER_PROPERTIS = "open-commons.spring.web.exception-httpstatus-binder.properties";

    private ApplicationContext context;

    @Autowired
    @Qualifier(BEAN_QUALIFIER_RESTTEMPLATE_REQUEST_SOURCE)
    private RestTemplateRequestFactoryResource reqFactoryResource;

    // @Autowired
    // @Qualifier(BEAN_QUALIFIER_THREAD_POOL_CONFIG)
    // private ThreadPoolTaskExecutorConfig taskExecConfig;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 27.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     *
     * @since 2019. 6. 27.
     * @version
     */
    public ResourceConfiguration(ApplicationContext context) {
        this.context = context;
    }

    @Bean(BEAN_QUALIFIER_EXCETPION_HTTPSTATUS_PROPERTIES)
    @ConfigurationProperties(CONFIGURATION_PROPERTIES_EXCETPION_HTTPSTATUS_BINDER_PROPERTIS)
    public Map<String, String> configureExceptionHttpStatusProperties() {
        return new HashMap<>();
    }

    /**
     * {@link RestTemplate}에 사용되는 {@link ClientHttpRequestFactory} 설정 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 27.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 6. 27.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE_REQUEST_SOURCE)
    @Primary
    @ConfigurationProperties("open-commons.spring.web.resttemplate.requestfactory")
    public RestTemplateRequestFactoryResource configureRestTemplateRequestFactoryResource() {
        return new RestTemplateRequestFactoryResource();
    }

    /**
     * ThreadPool 설정정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 27.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 6. 27.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @Bean(name = BEAN_QUALIFIER_THREAD_POOL_CONFIG)
    @Primary
    @ConfigurationProperties("open-commons.spring.async.thread-pool-task-executor")
    public ThreadPoolTaskExecutorConfig configureThreadPoolTaskExecutorConfig() {
        return new ThreadPoolTaskExecutorConfig();
    }

    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE)
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Primary
    public RestTemplate createBeanRestTemplate() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        HttpClient httpClient = RestUtils.createHttpsClient(false);
        HttpComponentsClientHttpRequestFactory reqFactory = getRequestFactory(httpClient, reqFactoryResource);

        RestTemplate tpl = new RestTemplate(reqFactory);
        return tpl;
    }

    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA)
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RestTemplate createBeanRestTemplateAllowPrivateCA() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        HttpClient httpClient = RestUtils.createHttpsClient(true);
        HttpComponentsClientHttpRequestFactory reqFactory = getRequestFactory(httpClient, reqFactoryResource);

        RestTemplate tpl = new RestTemplate(reqFactory);
        return tpl;
    }

    /**
     * ThreadPool을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 20.		박준홍			최초 작성
     * 2025. 5. 28.     박준홍         명시적으로 {@link ThreadPoolTaskExecutorConfig} 파라미터로 전달
     * </pre>
     * 
     * @param taskExecConfig
     *            ThreadPool 실행 설정
     *
     * @return
     *
     * @since 2020. 1. 20.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @Bean(name = BEAN_QUALIFIER_THREAD_POOL, destroyMethod = "destroy")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Primary
    public ThreadPoolTaskExecutor createBeanThreadPoolTaskExecutor(@Qualifier(BEAN_QUALIFIER_THREAD_POOL_CONFIG) ThreadPoolTaskExecutorConfig taskExecConfig) {
        return createThreadPoolTaskExecutor(taskExecConfig, "async-method");
    }

    @Bean(ExceptionHttpStatusBinder.BEAN_QUALIFIER)
    @Primary
    public ExceptionHttpStatusBinder exceptionHttpStatusBinder(@Qualifier(BEAN_QUALIFIER_EXCETPION_HTTPSTATUS_PROPERTIES) Map<String, String> exceptionHttpStatusProperties) {
        return new ExceptionHttpStatusBinder(exceptionHttpStatusProperties);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     * @param defaultThreadName
     * @return
     *
     * @since 2021. 8. 19.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static ThreadPoolTaskExecutor createThreadPoolTaskExecutor(ThreadPoolTaskExecutorConfig config, String defaultThreadName) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(config.getCorePoolSize());
        executor.setMaxPoolSize(config.getMaxPoolSize());
        executor.setQueueCapacity(config.getQueueCapacity());
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        executor.setAllowCoreThreadTimeOut(config.isAllowCoreThreadTimeOut());
        executor.setAwaitTerminationSeconds(config.getAwaitTerminationSeconds());
        executor.setWaitForTasksToCompleteOnShutdown(config.isWaitForTasksToCompleteOnShutdown());
        executor.setDaemon(config.isDaemon());
        String threadPrefix = config.getThreadNamePrefix();
        executor.setThreadNamePrefix(threadPrefix == null ? defaultThreadName : threadPrefix);
        String threadGroupName = config.getThreadGroupName();
        executor.setThreadGroupName(threadGroupName == null ? "execuor" : threadGroupName);
        executor.setThreadPriority(config.getThreadPriority());

        return executor;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 27.		박준홍			최초 작성
     * 2020. 12. 9.		박준홍			access modifier 변경 (private -> public static)
     * </pre>
     *
     * @param httpClient
     * @param reqFactoryResource
     * @return
     *
     * @since 2020. 12. 9.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static HttpComponentsClientHttpRequestFactory getRequestFactory(HttpClient httpClient, RestTemplateRequestFactoryResource reqFactoryResource) {
        HttpComponentsClientHttpRequestFactory reqFactory = httpClient != null //
                ? new HttpComponentsClientHttpRequestFactory(httpClient)//
                : new HttpComponentsClientHttpRequestFactory();
        reqFactory.setBufferRequestBody(reqFactoryResource.isBufferRequestBody());
        reqFactory.setConnectionRequestTimeout(reqFactoryResource.getConnectionRequestTimeout());
        reqFactory.setConnectTimeout(reqFactoryResource.getConnectionTimeout());
        reqFactory.setReadTimeout(reqFactoryResource.getReadTimeout());

        return reqFactory;
    }

}
