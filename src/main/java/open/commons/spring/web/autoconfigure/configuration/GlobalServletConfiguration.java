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
 * Date  : 2025. 6. 5. 오후 4:57:30
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;

import open.commons.core.utils.CollectionUtils;
import open.commons.core.utils.MapUtils;
import open.commons.core.utils.StreamUtils;
import open.commons.spring.web.config.ResourceConfiguration;
import open.commons.spring.web.handler.DefaultGlobalInterceptor;
import open.commons.spring.web.handler.HttpRequestProxyHeader;
import open.commons.spring.web.handler.InterceptorIgnoreUrlProperties;
import open.commons.spring.web.servlet.filter.AntPathRequest;
import open.commons.spring.web.servlet.filter.RequestHeaderFilter;
import open.commons.spring.web.servlet.filter.RequestThreadNameFilter;
import open.commons.spring.web.servlet.filter.header.SharedHeader;
import open.commons.spring.web.servlet.filter.header.SharedHeadersBuiltinProvider;
import open.commons.spring.web.utils.PathUtils;

/**
 * 
 * @since 2025. 6. 5.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class GlobalServletConfiguration {

    /** {@link HandlerInterceptor}에서 URL 기반으로 처리 대상에서 제외하는 URL 패턴 설정 */
    public static final String BEAN_QUALIFIER_PRIMARY_INTERCEPTOR_IGNORE_URL_PATTERNS = "open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration#INTERCEPTOR_IGNORE_URL_PATTERNS";
    /** {@link HandlerInterceptor}에서 URL 기반으로 처리 대상에서 제외하는 URL 패턴 기본 설정 */
    public static final String CONFIGURATION_BUILTIN_INTERCEPTOR_IGNORE_URL_PATTERNS = "open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration#CONFIGURATION_BUILTIN_INTERCEPTOR_IGNORE_URL_PATTERNS";
    /** {@link HandlerInterceptor}에서 URL 기반으로 처리 대상에서 제외하는 URL 패턴 설정 기본 경로 */
    public static final String PROPERTIES_INTERCEPTOR_IGNORE_URL_PATTERNS = ResourceConfiguration.PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".interceptor-ignore-url-patterns";

    /** {@link OncePerRequestFilter}에서 제외시킬 {@link HttpServletRequest} 패턴 */
    public static final String BEAN_QUALIFIER_PRIMARY_ONCE_PER_REQUEST_SHOULD_NOT_PATTERNS = "open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration#REQUEST_FILTER_SHOULD_NOT_PATTERNS";

    /** Proxy 서버를 통해서 전달되는 실제 클라이언트의 Http 요청 정보 */
    public static final String BEAN_QUALIFIER_PRIMARY_HTTP_REQUEST_PROXY_HEADER = "open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration#PRIMAY_HTTP_REQUEST_PROXY_HEADER";
    /** Proxy 서버를 통해서 전달되는 실제 클라이언트의 Http 요청 정보 설정 */
    public static final String CONFIGURATION_BUILTIN_HTTP_REQUEST_PROXY_HEADER = "open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration#CONFIGURATION_BUILTIN_HTTP_REQUEST_PROXY_HEADER";
    /** Proxy 서버를 통해서 전달되는 실제 클라이언트의 Http 요청 정보 경로 */
    public static final String PROPERTIES_HTTP_REQUEST_PROXY_HEADER = ResourceConfiguration.PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".proxy-header";

    /** 외부에서 전달되는 로그 분기 정보를 처리하는 {@link SharedHeader} 제공 */
    public static final String BEAN_QUALIFIER_PRIMARY_SHARED_HEADERS = "open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration#PRIMARY_SHARED_HEADERS";

    private static final Logger logger = LoggerFactory.getLogger(GlobalServletConfiguration.class);

    @SuppressWarnings("unused")
    private final ApplicationContext context;
    private final Environment environment;

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 5.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param context
     * @param environment
     *
     * @since 2025. 6. 5.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public GlobalServletConfiguration(ApplicationContext context, Environment environment) {
        this.context = context;
        this.environment = environment;
    }

    @Bean(name = DefaultGlobalInterceptor.BEAN_QUALIFIER)
    @ConditionalOnMissingBean
    @Order(Ordered.LOWEST_PRECEDENCE)
    DefaultGlobalInterceptor beanDefaultGlobalInterceptor() {
        return new DefaultGlobalInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    @Order(RequestHeaderFilter.ORDER)
    RequestHeaderFilter beanDefaultRequestHeaderFilter() {
        return new RequestHeaderFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    @Order(RequestThreadNameFilter.ORDER)
    RequestThreadNameFilter beanDefaultRequestThreadNameFilter() {
        return new RequestThreadNameFilter();
    }

    /**
     * {@link RequestThreadNameFilter} 이후에 위치시켜, {@link Thread} 이름 제어에 따르고 보안검증 이전에 Request 헤더 정보를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 19.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param filter
     * @return
     *
     * @since 2025. 8. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    FilterRegistrationBean<RequestHeaderFilter> beanFilterRegistrationRequestHeaderFilter(RequestHeaderFilter filter) {
        FilterRegistrationBean<RequestHeaderFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.setOrder(RequestHeaderFilter.ORDER); // 우선순위 상단
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * {@link SecurityFilterChain} 보다 앞에 위치시켜, 보안검증 이전의 {@link HttpServletRequest}에 대해서도 감지를 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param filter
     * @return
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    FilterRegistrationBean<RequestThreadNameFilter> beanFilterRegistrationThreadNamingFilter(RequestThreadNameFilter filter) {
        FilterRegistrationBean<RequestThreadNameFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.setOrder(RequestThreadNameFilter.ORDER); // 우선순위 최상단
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * 통합 {@link HttpRequestProxyHeader} 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param headers
     * @return
     *
     * @since 2025. 8. 7.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = BEAN_QUALIFIER_PRIMARY_HTTP_REQUEST_PROXY_HEADER)
    @Primary
    HttpRequestProxyHeader beanPrimaryForwardedProxyHeader(@Nonnull Map<String, HttpRequestProxyHeader> headers) {

        // #0. 내부 설정값을 마지막에 적용하기 위해 추출
        HttpRequestProxyHeader builtin = headers.remove(CONFIGURATION_BUILTIN_HTTP_REQUEST_PROXY_HEADER);

        // #1. 통합
        final BiConsumer<HttpRequestProxyHeader, HttpRequestProxyHeader> aggregator = (aggr, data) -> {
            // #2. 내부 설정값 통합
            data.forEach((k, v) -> {
                String o = aggr.put(k, v);
                if (o != null) {
                    logger.info("[http-request-proxy-header] Updated! key={}, value={} <- {}", k, v, o);
                }
            });
        };

        HttpRequestProxyHeader merged = new HttpRequestProxyHeader();
        headers.values().forEach(hd -> {
            aggregator.accept(merged, hd);
        });

        // #2. 내부 설정값 통합
        aggregator.accept(merged, builtin);

        return merged;
    }

    @Bean(name = BEAN_QUALIFIER_PRIMARY_INTERCEPTOR_IGNORE_URL_PATTERNS)
    @Primary
    Set<InterceptorIgnoreUrlProperties> beanPrimaryInterceptorIgnoreUrlPatterns( //
            @NotNull @Nonnull Map<String, InterceptorIgnoreUrlProperties> single //
            , @NotNull @Nonnull Map<String, List<InterceptorIgnoreUrlProperties>> multi) {

        List<InterceptorIgnoreUrlProperties> merged = MapUtils.toList(single, multi);

        // 중복 검증
        // key: FQCN 기반의 target 정보, value: 동일한 target 정보인 InterceptorIgnoreUrlProperties 객체들
        MultiValueMap<String, InterceptorIgnoreUrlProperties> mayBeDuplicated = StreamUtils.toMap(merged.stream(), InterceptorIgnoreUrlProperties::getTarget, Function.identity(),
                LinkedMultiValueMap::new);

        mayBeDuplicated.forEach((k, v) -> {
            if (v.size() > 1) {
                logger.debug("{}에 대한 설정이 {}개 존재합니다. 목록은 다음과 같습니다.\n\t{}\n" //
                        , k // FQCN 값
                        , v.size() // 중복 데이터 개수
                        , String.join("\n\t", v.stream().map(Object::toString).collect(Collectors.toList())) // 모든 설정
                );

            }
        });

        Set<InterceptorIgnoreUrlProperties> result = CollectionUtils.toSet(merged, InterceptorIgnoreUrlProperties::getTarget //
                , // 설정 객체 데이터 변조 방지를 위해 새로운 객체 생성
                p -> {
                    InterceptorIgnoreUrlProperties n = new InterceptorIgnoreUrlProperties();
                    n.setTarget(p.getTarget());
                    n.setExcludePathPatterns(p.getExcludePathPatterns());
                    n.setIncludePathPatterns(p.getIncludePathPatterns());
                    return n;
                }, (old, income) -> {
                    old.addExcludePathPatterns(income.getExcludePathPatterns());
                    old.addIncludePathPatterns(income.getIncludePathPatterns());
                    return old;
                });

        return result;
    }

    @Bean(name = BEAN_QUALIFIER_PRIMARY_ONCE_PER_REQUEST_SHOULD_NOT_PATTERNS)
    @Primary
    List<AntPathRequest> beanPrimaryOncePerRequestShouldNotFilters( //
            @NotNull @Nonnull Map<String, AntPathRequest> single//
            , @NotNull @Nonnull Map<String, List<AntPathRequest>> multi) {
        return MapUtils.toList(single, multi);
    }

    @Bean(name = BEAN_QUALIFIER_PRIMARY_SHARED_HEADERS)
    @Primary
    List<SharedHeader> beanPrimarySharedHeaders( //
            @NotNull @Nonnull Map<String, SharedHeader> single //
            , @NotNull @Nonnull Map<String, List<SharedHeader>> multi //
    ) {
        return MapUtils.toList(single, multi);
    }

    /**
     * 시스템 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		parkjunhong77@gmail.com			최초 작성
     * 2025. 8. 7.      parkjunhong77@gmail.com         기본적으로 지원하는 헤더를 내부 코드로 강제화 시킴.
     *                                  - real-ip: X-Real-IP
     *                                  - client-port: X-Client-Port
     *                                  - forwarded-for: X-Forwarded-For
     *                                  - forwarded-proto: X-Forwarded-Proto
     *                                  - forwarded-host: X-Forwarded-Host
     *                                  - forwarded-port: X-Forwarded-Port
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 7.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = CONFIGURATION_BUILTIN_HTTP_REQUEST_PROXY_HEADER)
    HttpRequestProxyHeader configBuiltinForwardedProxyHeader() {

        HttpRequestProxyHeader header = new HttpRequestProxyHeader();

        header.put(HttpRequestProxyHeader.HEADER_REAL_IP, "X-Real-IP");
        header.put(HttpRequestProxyHeader.HEADER_CLIENT_PORT, "X-Client-Port");
        header.put(HttpRequestProxyHeader.HEADER_FORWARDED_FOR, "X-Forwarded-For");
        header.put(HttpRequestProxyHeader.HEADER_FORWARDED_PROTO, "X-Forwarded-Proto");
        header.put(HttpRequestProxyHeader.HEADER_FORWARDED_HOST, "X-Forwarded-Host");
        header.put(HttpRequestProxyHeader.HEADER_FORWARDED_PORT, "X-Forwarded-Port");

        return header;
    }

    /**
     * Spring 및 Swagger 관련 예외 목록 추가<br>
     * 
     * <p>
     * Spring
     * <li>${spring.mvc.statis-path-pattern}: /static/**
     * </p>
     * <p>
     * Swagger
     * <li>${springdoc.api-docs.path}: /api-docs => AntPath 패턴으로 변환 ("/**" 추가)
     * <li>/swagger/**
     * <li>/swagger-ui/**
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 7.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    InterceptorIgnoreUrlProperties configBuiltinInterceptorIgnoreUrlProperties() {

        InterceptorIgnoreUrlProperties prop = new InterceptorIgnoreUrlProperties();
        prop.setTarget("open.commons.spring.web.handler.*");

        // 웹서비스 개발시 정적 자원 경로: ${spring.mvc.static-path-pattern}
        PathUtils.addEnvironmentProperty(this.environment, "spring.mvc.static-path-pattern", prop::addExcludePathPattern);
        // --> begin: Swagger API
        // Swager Doc. API 경로: ${springdoc.api-docs.path}
        PathUtils.addEnvironmentProperty(this.environment, "springdoc.api-docs.path", v -> {
            if (v.endsWith("/**")) {
                prop.addExcludePathPattern(v);
            } else {
                prop.addExcludePathPattern(String.join("", v, "/**"));
            }
        });
        // Swagger 웹 페이지 자원 경로
        prop.addExcludePathPattern("/swagger/**") //
                .addExcludePathPattern("/swagger-ui/**") //
        ;

        return prop;
    }

    /**
     * Spring 및 Swagger 관련 예외 목록 추가<br>
     * 
     * <p>
     * Spring
     * <li>${spring.mvc.statis-path-pattern}: /static/**
     * </p>
     * <p>
     * Swagger
     * <li>${springdoc.api-docs.path}: /api-docs => AntPath 패턴으로 변환 ("/**" 추가)
     * <li>/swagger/**
     * <li>/swagger-ui/**
     * </p>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 8.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    List<AntPathRequest> configBuiltinOncePerRequestShouldNotFilters() {

        List<AntPathRequest> paths = new ArrayList<>();

        // 웹서비스 개발시 정적 자원 경로: ${spring.mvc.static-path-pattern}
        PathUtils.addEnvironmentProperty(this.environment, "spring.mvc.static-path-pattern", AntPathRequest::new, paths::add);
        // --> begin: Swagger API
        // Swager Doc. API 경로: ${springdoc.api-docs.path}
        PathUtils.addEnvironmentProperty(this.environment, "springdoc.api-docs.path", v -> {
            if (v.endsWith("/**")) {
                paths.add(new AntPathRequest(v));
            } else {
                paths.add(new AntPathRequest(String.join("", v, "/**")));
            }
        });
        // Swagger 웹 페이지 자원 경로
        paths.add(new AntPathRequest("/swagger/**", HttpMethod.GET));
        paths.add(new AntPathRequest("/swagger-ui/**", HttpMethod.GET));

        return paths;
    }

    /**
     * 내부에서 정의된 {@link SharedHeader} 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    List<SharedHeader> configBuiltinSharedHeaders() {
        return SharedHeadersBuiltinProvider.load();
    }
}
