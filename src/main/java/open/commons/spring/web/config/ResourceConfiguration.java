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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import open.commons.spring.web.async.MdcTaskDecorator;
import open.commons.spring.web.handler.InterceptorIgnoreUrlProperties;
import open.commons.spring.web.resources.RestTemplateRequestFactoryResource;
import open.commons.spring.web.resources.ScheduledThreadPoolExecutorConfig;
import open.commons.spring.web.resources.ThreadPoolTaskExecutorConfig;
import open.commons.spring.web.resources.ThreadPoolTaskSchedulerConfig;
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

    public static final String PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH = "open-commons.spring.web";

    // --- java.util.concurrent.ScheduledThreadPoolExecutor --- //
    /**
     * 기본 {@link RestTemplate}<br>
     * <li>공인 인증서만 허용
     */
    public static final String BEAN_QUALIFIER_RESTTEMPLATE = "open.commons.spring.web.config.ResourceConfiguration#RESTTEMPLATE";
    /**
     * 기본 {@link RestTemplate}<br>
     * <li>'공인 + 비공인' 인증서 허용
     */
    public static final String BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA = "open.commons.spring.web.config.ResourceConfiguration#RESTTEMPLATE_ALLOW_PRIVATE_CA";
    /** 기본 {@link RestTemplate} 설정 */
    public static final String CONFIGURATION_RESTTEMPLATE_REQUEST_SOURCE = "open.commons.spring.web.config.ResourceConfiguration#CONFIGURATION_RESTTEMPLATE_REQUEST_SOURCE";
    /** 기본 {@link RestTemplate} 설정 경로 */
    private static final String PROPERTIES_RESTTEMPLATE_REQUEST_SOURCE = PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".resttemplate.requestfactory";
    // --------------------------------------------------------- //

    // Duplicate @ConfigurationProperties definition for prefix
    // 'open-commons.spring.web.concurrent.scheduled-thread-pool-executor'
    // --- org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor --- //
    /** 기본적으로 제공되는 {@link ThreadPoolTaskExecutor} */
    public static final String BEAN_QUALIFIER_DEFAULT_THREAD_POOL_TASK_EXECUTOR = "open.commons.spring.web.config.ResourceConfiguration#DEFAULT_THREADPOOL_TASK_EXECUTOR";
    /** 기본적으로 제공되는 {@link ThreadPoolTaskExecutor} 설정 */
    private static final String CONFIGURATION_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG = "open.commons.spring.web.config.ResourceConfiguration#CONFIGURATION_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG";
    /** 기본적으로 제공되는 {@link ThreadPoolTaskExecutor} 설정 경로 */
    private static final String PROPERTIES_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG = PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".concurrent.thread-pool-task-executor";
    /**
     * {@link ThreadPoolTaskExecutor} 설정 경로<br>
     * 
     * @deprecated 경로가 변경에 따라 사용되지 않음.{@link #PROPERTIES_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG}를 사용할 것.<br>
     *             <font color="red">다음 배포시 삭제될 예정.</font>
     */
    private static final String PROPERTIES_DEFAULT_ASYNC_THREAD_POOL_TASK_EXECUTOR_CONFIG = PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".async.thread-pool-task-executor";
    /** 내부적으로 사용되는 {@link ThreadPoolTaskExecutor} 설정 */
    public static final String CONFIGURATION_BUILTIN_THREAD_POOL_TASK_EXECUTOR_CONFIG = "open.commons.spring.web.config.ResourceConfiguration#CONFIGURATION_BUILTIN_THREAD_POOL_TASK_EXECUTOR_CONFIG";
    // -------------------------------------------------------------------------- //

    // --- org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler --- //
    /** 기본적으로 제공되는 {@link ThreadPoolTaskScheduler} */
    public static final String BEAN_QUALIFIER_DEFAULT_THREAD_POOL_TASK_SCHEDULER = "open.commons.spring.web.config.ResourceConfiguration#DEFAULT_THREAD_POOL_TASK_SCHEDULER";
    /** 기본적으로 제공되는 {@link ThreadPoolTaskExecutor} 설정 */
    private static final String CONFIGURATION_DEFAULT_THREAD_POOL_TASK_SCHEDULER_CONFIG = "open.commons.spring.web.config.ResourceConfiguration#CONFIGURATION_DEFAULT_THREAD_POOL_TASK_SCHEDULER_CONFIG";
    /** 기본적으로 제공되는 {@link ThreadPoolTaskExecutor} 설정 경로 */
    private static final String PROPERTIES_DEFAULT_THREAD_POOL_TASK_SCHEDULER_CONFIG = PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".concurrent.thread-pool-task-scheduler";
    /** 내부적으로 사용되는 {@link ThreadPoolTaskExecutor} 설정 */
    public static final String CONFIGURATION_BUILTIN_THREAD_POOL_TASK_SCHEDULER_CONFIG = "open.commons.spring.web.config.ResourceConfiguration#CONFIGURATION_BUILTIN_THREAD_POOL_TASK_SCHEDULER_CONFIG";
    // -------------------------------------------------------------------------- //

    // --- java.util.concurrent.ScheduledThreadPoolExecutor --- //
    /** 기본적으로 제공되는 {@link ScheduledThreadPoolExecutor} */
    public static final String BEAN_QUALIFIER_DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR = "open.commons.spring.web.config.ResourceConfiguration#DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR";
    /** 기본적으로 제공되는 {@link ScheduledThreadPoolExecutor} */
    private static final String CONFIGURATION_DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG = "open.commons.spring.web.config.ResourceConfiguration#CONFIGURATION_DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG";
    /** 기본적으로 제공되는 {@link ScheduledThreadPoolExecutor} */
    private static final String PROPERTIES_DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG = PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH
            + ".concurrent.scheduled-thread-pool-executor";
    /** 내부적으로 사용되는 {@link ScheduledThreadPoolExecutor} */
    public static final String CONFIGURATION_BUILTIN_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG = "open.commons.spring.web.config.ResourceConfiguration#CONFIGURATION_BUILTIN_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG";
    // -------------------------------------------------------------------------- //

    /** {@link Throwable} 과 그에 따르는 {@link HttpStatus} 매핑 설정 */
    private static final String PROPERTIES_EXCETPION_HTTPSTATUS_BINDER_PROPERTIS = "open-commons.spring.web.exception-httpstatus-binder.properties";
    /** {@link Throwable} 과 그에 따르는 {@link HttpStatus} 매핑 제공 서비스 */
    public static final String CONFIGURATION_EXCETPION_HTTPSTATUS_PROPERTIES = "open.commons.spring.web.config.ResourceConfiguration#EXCETPION_HTTPSTATUS_PROPERTIES";

    /** {@link HandlerInterceptor}에서 URL 기반으로 {@link Thread} 이름을 설정하는 대상에서 제외하는 URL 패턴 설정 경로 */
    private static final String PROPERTIES_INTERCEPTOR_IGNORE_URL_PATTERN = PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".interceptor-ignore-url-patterns";

    /** {@link Async} 어노테이션이 적용된 메소드가 실행될 때 기본값으로 사용되는 {@link Executor} 설정값 */
    public static final String CONFIGURATION_BUILTIN_THREAD_POOL_TASK_EXECUTOR_CONFIG_ON_ASYNC = "open.commons.spring.web.config.ResourceConfiguration#BUILTIN_ASYNC_THREAD_POOL_TASK_EXECUTOR_CONFIG";

    @SuppressWarnings("unused")
    private ApplicationContext context;

    private final Environment environment;

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
     * @param env
     *            TODO
     *
     * @since 2019. 6. 27.
     * @version
     */
    public ResourceConfiguration(ApplicationContext context, Environment environment) {
        this.context = context;
        this.environment = environment;
    }

    /**
     * 주어진 경로에 해당하는 정보를 객체에 적용합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param prefix
     *            데이터 경로
     * @param target
     *            객체
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private void bind(@NotBlank @Nonnull String prefix, @NotNull @Nonnull ThreadPoolTaskExecutorConfig target) {
        Binder binder = Binder.get(this.environment);
        binder.bind(prefix, Bindable.ofInstance(target));
    }

    /**
     * 내부적인 용도로 사용되는 {@link ScheduledThreadPoolExecutor} 설정값을 제공합니다.<br>
     * 단, {@link #CONFIGURATION_BUILTIN_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG} 이름을 갖는 설정({@link Bean}) 이 생성되는 경우 실행되지
     * 않습니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     * @return
     * @throws CloneNotSupportedException
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = CONFIGURATION_BUILTIN_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG)
    @ConditionalOnMissingBean(name = { CONFIGURATION_BUILTIN_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG })
    ScheduledThreadPoolExecutorConfig builtinScheduledThreadPoolExecutorConfig(
            @Qualifier(CONFIGURATION_DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG) ScheduledThreadPoolExecutorConfig config) throws CloneNotSupportedException {
        return (ScheduledThreadPoolExecutorConfig) config.clone();
    }

    /**
     * 내부적인 용도로 사용되는 {@link ThreadPoolTaskExecutor} 설정값을 제공합니다.<br>
     * 단, {@link #CONFIGURATION_BUILTIN_THREAD_POOL_TASK_EXECUTOR_CONFIG} 이름을 갖는 설정({@link Bean}) 이 생성되는 경우 실행되지 않습니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     * @return
     * @throws CloneNotSupportedException
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = CONFIGURATION_BUILTIN_THREAD_POOL_TASK_EXECUTOR_CONFIG)
    @ConditionalOnMissingBean(name = { CONFIGURATION_BUILTIN_THREAD_POOL_TASK_EXECUTOR_CONFIG })
    ThreadPoolTaskExecutorConfig builtinThreadPoolTaskExecutorConfig(@Qualifier(CONFIGURATION_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG) ThreadPoolTaskExecutorConfig config)
            throws CloneNotSupportedException {
        return (ThreadPoolTaskExecutorConfig) config.clone();
    }

    /**
     * {@link Async} 어노테이션이 적용된 메소드를 실행하는 내부 {@link ThreadPoolTaskExecutor} 설정값을 제공합니다.<br>
     * 단, {@link #CONFIGURATION_BUILTIN_THREAD_POOL_TASK_EXECUTOR_CONFIG_ON_ASYNC} 이름을 갖는 설정({@link Bean}) 이 생성되는 경우
     * 실행되지 않습니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 7. 31.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(CONFIGURATION_BUILTIN_THREAD_POOL_TASK_EXECUTOR_CONFIG_ON_ASYNC)
    @ConditionalOnMissingBean(name = { CONFIGURATION_BUILTIN_THREAD_POOL_TASK_EXECUTOR_CONFIG_ON_ASYNC })
    ThreadPoolTaskExecutorConfig builtinThreadPoolTaskExecutorConfigOnAsync() {
        ThreadPoolTaskExecutorConfig config = new ThreadPoolTaskExecutorConfig();
        config.setDaemon(true);
        return config;
    }

    /**
     * {@link Scheduled} 어노테이션이 적용된 메소드를 실행하는 내부 {@link ThreadPoolTaskScheduler}의 설정값을 제공합니다.<br>
     * 단, {@link #CONFIGURATION_BUILTIN_THREAD_POOL_TASK_SCHEDULER_CONFIG} 이름을 갖는 설정(@Bean)이 생성되는 경우 실행되지 않습니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     * @return
     * @throws CloneNotSupportedException
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = CONFIGURATION_BUILTIN_THREAD_POOL_TASK_SCHEDULER_CONFIG)
    @ConditionalOnMissingBean(name = { CONFIGURATION_BUILTIN_THREAD_POOL_TASK_SCHEDULER_CONFIG })
    ThreadPoolTaskSchedulerConfig builtinThreadPoolTaskSchedulerConfig(@Qualifier(CONFIGURATION_DEFAULT_THREAD_POOL_TASK_SCHEDULER_CONFIG) ThreadPoolTaskSchedulerConfig config)
            throws CloneNotSupportedException {
        return (ThreadPoolTaskSchedulerConfig) config.clone();
    }

    @Bean(CONFIGURATION_EXCETPION_HTTPSTATUS_PROPERTIES)
    @ConfigurationProperties(PROPERTIES_EXCETPION_HTTPSTATUS_BINDER_PROPERTIS)
    Map<String, String> configureExceptionHttpStatusProperties() {
        return new HashMap<>();
    }

    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE)
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Primary
    RestTemplate defaultRestTemplate(@Qualifier(CONFIGURATION_RESTTEMPLATE_REQUEST_SOURCE) RestTemplateRequestFactoryResource reqFactoryResource)
            throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        HttpClient httpClient = RestUtils.createHttpsClient(false);
        HttpComponentsClientHttpRequestFactory reqFactory = getRequestFactory(httpClient, reqFactoryResource);

        RestTemplate tpl = new RestTemplate(reqFactory);
        return tpl;
    }

    @Bean(name = BEAN_QUALIFIER_RESTTEMPLATE_ALLOW_PRIVATE_CA)
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    RestTemplate defaultRestTemplateAllowPrivateCA(@Qualifier(CONFIGURATION_RESTTEMPLATE_REQUEST_SOURCE) RestTemplateRequestFactoryResource reqFactoryResource)
            throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        HttpClient httpClient = RestUtils.createHttpsClient(true);
        HttpComponentsClientHttpRequestFactory reqFactory = getRequestFactory(httpClient, reqFactoryResource);

        RestTemplate tpl = new RestTemplate(reqFactory);
        return tpl;
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
    @Bean(name = CONFIGURATION_RESTTEMPLATE_REQUEST_SOURCE)
    @Primary
    @ConfigurationProperties(PROPERTIES_RESTTEMPLATE_REQUEST_SOURCE)
    RestTemplateRequestFactoryResource defaultRestTemplateRequestFactoryResource() {
        return new RestTemplateRequestFactoryResource();
    }

    /**
     * {@link ScheduledThreadPoolExecutor}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     * @return
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = BEAN_QUALIFIER_DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR)
    @Primary
    ScheduledThreadPoolExecutor defaultScheduledThreadPoolExecutor(
            @Qualifier(CONFIGURATION_DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG) ScheduledThreadPoolExecutorConfig config) {
        return createScheduledThreadPoolExecutor(config);
    }

    /**
     * {@link ScheduledThreadPoolExecutor} 설정정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 1.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = CONFIGURATION_DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG)
    @ConfigurationProperties(PROPERTIES_DEFAULT_SCHEDULED_THREAD_POOL_EXECUTOR_CONFIG)
    @Primary
    ScheduledThreadPoolExecutorConfig defaultScheduledThreadPoolExecutorConfig() {
        return new ScheduledThreadPoolExecutorConfig();
    }

    /**
     * {@link ThreadPoolTaskExecutor} 제공한다. <br>
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
    @Bean(name = BEAN_QUALIFIER_DEFAULT_THREAD_POOL_TASK_EXECUTOR, destroyMethod = "destroy")
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Primary
    ThreadPoolTaskExecutor defaultThreadPoolTaskExecutor(@Qualifier(CONFIGURATION_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG) ThreadPoolTaskExecutorConfig taskExecConfig) {
        return createThreadPoolTaskExecutor(taskExecConfig, "@builtin");
    }

    /**
     * {@link ThreadPoolTaskExecutor} 설정정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 27.		박준홍			최초 작성
     * 2025. 8. 1.      박준홍         설정경로 변경으로 기존 경로를 호환하는 구조로 변경. (추후 하나의 경로로 정리할 예정)
     * </pre>
     *
     * @return
     *
     * @since 2019. 6. 27.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @Bean(name = CONFIGURATION_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG)
    @Primary
    // @ConfigurationProperties(PROPERTIES_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG)
    ThreadPoolTaskExecutorConfig defaultThreadPoolTaskExecutorConfig() {
        ThreadPoolTaskExecutorConfig config = new ThreadPoolTaskExecutorConfig();

        if (hasPrefix(PROPERTIES_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG + ".corePoolSize")) {
            bind(PROPERTIES_DEFAULT_THREAD_POOL_TASK_EXECUTOR_CONFIG, config);
        } else if (hasPrefix(PROPERTIES_DEFAULT_ASYNC_THREAD_POOL_TASK_EXECUTOR_CONFIG + ".corePoolSize")) {
            bind(PROPERTIES_DEFAULT_ASYNC_THREAD_POOL_TASK_EXECUTOR_CONFIG, config);
        }

        return config;
    }

    /**
     * {@link ThreadPoolTaskScheduler}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = BEAN_QUALIFIER_DEFAULT_THREAD_POOL_TASK_SCHEDULER)
    @Primary
    ThreadPoolTaskScheduler defaultThreadPoolTaskScheduler(@Qualifier(CONFIGURATION_DEFAULT_THREAD_POOL_TASK_SCHEDULER_CONFIG) ThreadPoolTaskSchedulerConfig config) {
        return createThreadPoolTaskScheduler(config);
    }

    /**
     * {@link ThreadPoolTaskScheduler} 설정 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = CONFIGURATION_DEFAULT_THREAD_POOL_TASK_SCHEDULER_CONFIG)
    @Primary
    @ConfigurationProperties(PROPERTIES_DEFAULT_THREAD_POOL_TASK_SCHEDULER_CONFIG)
    ThreadPoolTaskSchedulerConfig defaultThreadPoolTaskSchedulerConfig() {
        return new ThreadPoolTaskSchedulerConfig();
    }

    @Bean(ExceptionHttpStatusBinder.BEAN_QUALIFIER)
    @Primary
    ExceptionHttpStatusBinder exceptionHttpStatusBinder(@Qualifier(CONFIGURATION_EXCETPION_HTTPSTATUS_PROPERTIES) Map<String, String> exceptionHttpStatusProperties) {
        return new ExceptionHttpStatusBinder(exceptionHttpStatusProperties);
    }

    /**
     * 주어진 값에 해댱하는 정보가 있는지 여부를 제공합니다. <br>
     * 객체 형태의 경로가 아니라 속성(leaf) 형태의 경로이어야 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param prefix
     * @return
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private boolean hasPrefix(String prefix) {
        return environment.getProperty(prefix) != null;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    @ConfigurationProperties(PROPERTIES_INTERCEPTOR_IGNORE_URL_PATTERN)
    List<InterceptorIgnoreUrlProperties> interceptorIgnoreUrlPatterns() {
        return new ArrayList<>();
    }

    /**
     * 전달받은 설정값이 적용한 {@link ScheduledThreadPoolExecutor} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 1.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     * @return
     *
     * @since 2025. 8. 1.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static ScheduledThreadPoolExecutor createScheduledThreadPoolExecutor(ScheduledThreadPoolExecutorConfig config) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(config.getCorePoolSize());

        // ThreadPoolExecutor
        executor.setMaximumPoolSize(config.getMaximumPoolSize());
        executor.setKeepAliveTime(config.getKeepAliveTime(), config.getTimeUnit());
        executor.allowCoreThreadTimeOut(config.isAllowCoreThreadTimeOut());
        // ScheduledThreadPoolExecutor
        executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(config.isContinueExistingPeriodicTasksAfterShutdown());
        executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(config.isExecuteExistingDelayedTasksAfterShutdown());
        executor.setRemoveOnCancelPolicy(config.isRemoveOnCancel());

        return executor;
    }

    /**
     * 전달받은 설정값을 적용한 {@link ThreadPoolTaskExecutor} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     * @param threadNameSymbol
     * @return
     *
     * @since 2021. 8. 19.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static ThreadPoolTaskExecutor createThreadPoolTaskExecutor(@Nonnull ThreadPoolTaskExecutorConfig config, String threadNameSymbol) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // --- org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor --- //
        executor.setCorePoolSize(config.getCorePoolSize());
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        executor.setMaxPoolSize(config.getMaxPoolSize());
        executor.setQueueCapacity(config.getQueueCapacity());
        executor.setAllowCoreThreadTimeOut(config.isAllowCoreThreadTimeOut());
        executor.setPrestartAllCoreThreads(config.isPrestartAllCoreThreads());
        // Runnable 에 대한 decoration 적용.
        executor.setTaskDecorator(new MdcTaskDecorator(threadNameSymbol));
        // -------------------------------------------------- //
        // --- org.springframework.scheduling.concurrent.ExecutorConfigurationSupport --- //
        executor.setAwaitTerminationMillis(config.getAwaitTerminationMillis());
        executor.setWaitForTasksToCompleteOnShutdown(config.isWaitForTasksToCompleteOnShutdown());
        executor.setAwaitTerminationMillis(config.getAwaitTerminationMillis());
        // -------------------------------------------------- //
        // --- org.springframework.util.CustomizableThreadCreator --- //
        executor.setDaemon(config.isDaemon());
        executor.setThreadNamePrefix(config.getThreadNamePrefix());
        executor.setThreadGroupName(config.getThreadGroupName());
        executor.setThreadPriority(config.getThreadPriority());
        // -------------------------------------------------- //

        return executor;
    }

    /**
     * 전달받을 설정값을 적용한 {@link ThreadPoolTaskScheduler} 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param config
     * @return
     *
     * @since 2025. 8. 3.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static ThreadPoolTaskScheduler createThreadPoolTaskScheduler(@Nonnull ThreadPoolTaskSchedulerConfig config) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

        // -- org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler --//
        scheduler.setPoolSize(config.getPoolSize());
        scheduler.setRemoveOnCancelPolicy(config.isRemoveOnCancelPolicy());
        scheduler.setContinueExistingPeriodicTasksAfterShutdownPolicy(config.isContinueExistingPeriodicTasksAfterShutdownPolicy());
        scheduler.setExecuteExistingDelayedTasksAfterShutdownPolicy(config.isExecuteExistingDelayedTasksAfterShutdownPolicy());
        // --------------------------------------------- //

        // --- org.springframework.scheduling.concurrent.ExecutorConfigurationSupport --- //
        scheduler.setAwaitTerminationMillis(config.getAwaitTerminationMillis());
        scheduler.setWaitForTasksToCompleteOnShutdown(config.isWaitForTasksToCompleteOnShutdown());
        scheduler.setAwaitTerminationMillis(config.getAwaitTerminationMillis());
        // ---------------------------------------------------------- //

        // --- org.springframework.util.CustomizableThreadCreator --- //
        scheduler.setDaemon(config.isDaemon());
        scheduler.setThreadGroupName(config.getThreadGroupName());
        scheduler.setThreadNamePrefix(config.getThreadNamePrefix());
        scheduler.setThreadPriority(config.getThreadPriority());
        // -------------------------------------------------- //

        return scheduler;
    }

    /**
     * 전달받은 설정이 적용된 {@link HttpComponentsClientHttpRequestFactory} 객체를 제공합니다. <br>
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
