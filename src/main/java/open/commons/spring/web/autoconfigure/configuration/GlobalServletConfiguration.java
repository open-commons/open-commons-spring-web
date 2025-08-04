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
 * Date  : 2025. 6. 5. 오후 4:57:30
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure.configuration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.HandlerInterceptor;

import open.commons.spring.web.config.ResourceConfiguration;
import open.commons.spring.web.handler.DefaultGlobalInterceptor;
import open.commons.spring.web.handler.HttpRequestProxyHeader;
import open.commons.spring.web.handler.InterceptorIgnoreUrlProperties;

/**
 * 
 * @since 2025. 6. 5.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class GlobalServletConfiguration {

    /** {@link HandlerInterceptor}에서 URL 기반으로 {@link Thread} 이름을 설정하는 대상에서 제외하는 URL 패턴 설정 */
    public static final String BEAN_QUALIFIER_INTERCEPTOR_IGNORE_URL_PATTERNS = "open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration#INTERCEPTOR_IGNORE_URL_PATTERNS";

    /** Proxy 서버를 통해서 전달되는 실제 클라이언트의 Http 요청 정보 @ */
    private static final String PROPERTIES_HTTP_REQUEST_PROXY_HEADER = ResourceConfiguration.PROPERTIES_OPEN_COMMONS_SPRING_WEB_ROOT_PATH + ".proxy-header";

    private static final Logger logger = LoggerFactory.getLogger(GlobalServletConfiguration.class);

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 5.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 5.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public GlobalServletConfiguration() {
    }

    @Bean(DefaultGlobalInterceptor.BEAN_QUALIFIER)
    @ConditionalOnMissingBean
    @Order(Ordered.LOWEST_PRECEDENCE)
    DefaultGlobalInterceptor defaultGlobalInterceptor() {
        return new DefaultGlobalInterceptor();
    }

    @Bean
    @Primary
    @ConfigurationProperties(PROPERTIES_HTTP_REQUEST_PROXY_HEADER)
    HttpRequestProxyHeader getProxyHeader() {
        return new HttpRequestProxyHeader();
    }

    @Bean(BEAN_QUALIFIER_INTERCEPTOR_IGNORE_URL_PATTERNS)
    @Primary
    Set<InterceptorIgnoreUrlProperties> interceptorIgnoreUrlPatterns( //
            @NotNull @Nonnull Map<String, InterceptorIgnoreUrlProperties> singleConfigurations //
            , @NotNull @Nonnull Map<String, List<InterceptorIgnoreUrlProperties>> multiConfigurations) {

        List<InterceptorIgnoreUrlProperties> merged = Stream //
                // 하나의 stream으로 병합
                .of(singleConfigurations.values().stream() //
                        , multiConfigurations.values().stream().flatMap(List::stream) //
                ) //
                .flatMap(s -> s).collect(Collectors.toList());

        // 중복 검증
        // key: FQCN 기반의 target 정보, value: 동일한 target 정보인 InterceptorIgnoreUrlProperties 객체들
        MultiValueMap<String, InterceptorIgnoreUrlProperties> mayBeDuplicated = merged.stream() //
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(InterceptorIgnoreUrlProperties::getTarget) //
                        , LinkedMultiValueMap::new));

        mayBeDuplicated.forEach((k, v) -> {
            if (v.size() > 1) {
                logger.warn("{}에 대한 설정이 {}개 존재합니다. 목록은 다음과 같습니다.\n\t{}\n" //
                        , k // FQCN 값
                        , v.size() // 중복 데이터 개수
                        , String.join("\n\t", v.stream().map(Object::toString).collect(Collectors.toList())) // 모든 설정
                );

            }
        });

        return merged.stream().collect(Collectors.toSet());
    }

    /**
     * swagger 리소스 경로 제외 추가. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 4.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    InterceptorIgnoreUrlProperties swaggerInterceptorIgnoreUrlPatterns() {
        InterceptorIgnoreUrlProperties prop = new InterceptorIgnoreUrlProperties();
        prop.setTarget(DefaultGlobalInterceptor.class.getName());
        prop.setExcludePathPatterns(Stream.of("/index.html", "/static/**", "/api-docs/**", "/swagger/**", "/swagger-ui/**").collect(Collectors.toSet()));

        return prop;
    }
}
