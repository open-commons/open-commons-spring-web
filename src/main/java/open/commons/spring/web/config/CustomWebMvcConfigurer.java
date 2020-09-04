/*
 * Copyright 2019 Park Jun-Hong_(parkjunhong77/google/com)
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
 * This file is generated under this project, "open-commons-spring5".
 *
 * Date  : 2019. 6. 3. 오후 5:44:34
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring.web.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import open.commons.spring.web.annotation.RequestValueSupported;
import open.commons.spring.web.enums.EnumConverter;
import open.commons.spring.web.enums.EnumConverterFactory;
import open.commons.spring.web.enums.EnumPackages;
import open.commons.utils.ArrayUtils;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 사용자 정의 설정을 자동으로 등록해주는 클래스.
 * 
 * <h1>사용자 정의 Enum 클래스 등 import open.commons.spring.web.enums.EnumConverterFactory;록</h1>
 * <h2>1. {@link Enum} 클래스 정보가 있는 패키지 정의</h2>
 * 
 * import open.commons.spring.web.enums.EnumPackages;
 * 
 * Sprig Boot Application 설정 파일에 아래 예시처럼 항목에 대한 값으로 패키지 정보 설정.<br>
 * 
 * 예) application.yml 인 경우
 * 
 * <pre>
 * ...
 * open-commons:
 *   spring:
 *     web:
 *       factory:
 *         enum:
 *           packages:
 *             - packages1
 *             - packages2
 * 
 * ...
 * 
 * </pre>
 * 
 * <h2>2. 사용자 정의 {@link Enum} 작성법</h2>
 * 
 * <pre>
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 * import open.commons.spring5.annotation.RequestValueConverter;
 * import open.commons.spring5.annotation.RequestValueSupported;
 * 
 * &#64;RequestValueSupported
 * public enum Service {
 *     NORMAL("normal"), PREMIUM("premium"), PLATINUM("Platinum");
 * 
 *     private String service;
 * 
 *     private Service(String service) {
 *         this.service = service;
 *     }
 * 
 *     public String get() {
 *         return this.service;
 *     }
 * 
 *     public static Service get(String service) {
 *         return get(service, false);
 *     }
 * 
 *     &#64;RequestValueConverter(hasIgnoreCase = true)
 *     public static Service get(String service, boolean ignoreCase) {
 * 
 *         if (service == null) {
 *             throw new IllegalArgumentException("'service' MUST NOT be null. input: " + service);
 *         }
 * 
 *         if (ignoreCase) {
 *             for (Service value : values()) {
 *                 if (value.service.equalsIgnoreCase(service)) {
 *                     return value;
 *                 }
 *             }
 *         } else {
 *             for (Service value : values()) {
 *                 if (value.service.equals(service)) {
 *                     return value;
 *                 }
 *             }
 *         }
 * 
 *         throw new IllegalArgumentException(
 *                 "Unexpected 'service' value of 'Service'. expected: " + values0() + " & Ignore case-sensitive: " + ignoreCase + ", input: " + service);
 *     }
 * 
 *     private static List<String> values0() {
 * 
 *         List<String> valuesStr = new ArrayList<>();
 * 
 *         for (Service value : values()) {
 *             valuesStr.add(value.get());
 *         }
 * 
 *         return valuesStr;
 *     }
 * }
 * </pre>
 * 
 * 
 * <h2>3. 자동으로 등록하기</h2>
 * 
 * <pre>
 * import org.springframework.boot.SpringApplication;
 * import org.springframework.boot.autoconfigure.SpringBootApplication;
 * import org.springframework.boot.web.servlet.ServletComponentScan;
 * import org.springframework.context.annotation.Bean;
 * 
 * import open.commons.spring.web.config.CustomWebMvcConfigurer;
 * 
 * &#64;ServletComponentScan
 * &#64;SpringBootApplication
 * public class SpringExampleApplication {
 * 
 *     &#64;Bean
 *     public CustomWebMvcConfigurer registerCustomWebMvcConfigurer() {
 *         return new CustomEnumRegister();
 *     }
 * 
 *     public static void main(String[] args) {
 *         SpringApplication app = new SpringApplication(SpringExampleApplication.class);
 *         app.run(args);
 *     }
 * }
 * </pre>
 * 
 * @since 2019. 6. 3.
 * @version 0.0.3
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
@EnableWebMvc
@Configuration
@EnableSwagger2
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    /** Prefix of configurations in appliation.yml(or .properteis, or ...) */
    public static final String APPLICATION_PROPERTIES_PREFIX = "open-commons.spring.web.factory.enum";

    // start - Springfox Swagger URL : 2020. 9. 4. 오전 12:25:16
    public static final String SWAGGER_URL_UI = "/swagger-ui";
    public static final String SWAGGER_URL_HTML = "/swagger-ui.html";
    public static final String SWAGGER_URL_RESOURCES = "/swagger-resources/**";
    public static final String SWAGGER_URL_WEBJARS = "/webjars/**";
    // end - Springfox Swagger URL : 2020. 9. 4. 오전 12:25:16

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext context;

    @Autowired
    private EnumPackages enumPkgs;

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param registry
     * @param patterns
     *            URL 처리 예외 패턴
     *
     * @since 2020. 9. 3.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    private void addExcludePatternsToInterceptor(InterceptorRegistration registry, String... patterns) {
        registry.excludePathPatterns(patterns);
        logger.info("[ADD] exclude.path={}", Arrays.toString(patterns));
    }

    /**
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addFormatters(org.springframework.format.FormatterRegistry)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addFormatters(FormatterRegistry registry) {

        // enum package 를 설정하지 않은 경우.
        List<String> pkgs = enumPkgs.getPackages();
        if (pkgs == null) {
            return;
        }

        EnumConverterFactory factory = new EnumConverterFactory();
        pkgs.stream() //
                .forEach(pkg -> {
                    Reflections r = new Reflections(pkg);
                    r.getSubTypesOf(Enum.class)//
                            .stream() //
                            .filter(type -> type.getAnnotation(RequestValueSupported.class) != null) //
                            .forEach(type -> {
                                EnumConverter c = new EnumConverter<>(type);
                                factory.register(type, c);

                                logger.info("Register a Converter {}.", c);
                            });
                });

        registry.addConverterFactory(factory);
    }

    /**
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Bean 중에서 HandlerIntereceptor 를 구현한 객체를 찾아서.
        Collection<HandlerInterceptor> intcptrs = context.getBeansOfType(HandlerInterceptor.class).values();

        if (intcptrs == null || intcptrs.size() < 1) {
            InterceptorRegistration reg = registry.addInterceptor(new HandlerInterceptorAdapter() {
            });
            addSwagger2ExcludePatternsToInterceptor(reg);
            return;
        }

        intcptrs.stream() //
                .forEach(intcptr -> {
                    InterceptorRegistration reg = registry.addInterceptor(intcptr);

                    // start - support 'sprignfox-swagger-ui-2.9.2' : 2020. 9. 3. 오후 5:16:01
                    addSwagger2ExcludePatternsToInterceptor(reg);
                    // end - support 'sprignfox-swagger-ui-2.9.2' : 2020. 9. 3. 오후 5:16:01

                    logger.info("Register a Interceptor. {}.", intcptr);
                });

        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * @since 2020. 9. 3.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        addSwagger2ResourceHandlers(registry);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param registry
     * @param handlers
     *            Resource Handler, {@link AntPathMatcher}
     * @param locations
     *            Resource Locations, {@link AntPathMatcher}
     *
     * @since 2020. 9. 3.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    private void addResourceHandlers(ResourceHandlerRegistry registry, String[] handlers, String[] locations) {
        registry.addResourceHandler(handlers).addResourceLocations(locations);
        logger.info("[ADD] resource.handler={}, resource.locations={}", Arrays.toString(handlers), Arrays.toString(locations));
    }

    /**
     * Springfox-swagger-ui 지원을 위한 패턴 예외사항을 적용한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param registry
     *
     * @since 2020. 9. 3.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    private void addSwagger2ExcludePatternsToInterceptor(InterceptorRegistration registry) {
        addExcludePatternsToInterceptor(registry,
                // swagger-ui
                SWAGGER_URL_UI,
                // swagger-ui.html
                SWAGGER_URL_HTML,
                // swagger-resources
                SWAGGER_URL_RESOURCES,
                // js, css, html, font, etc ...
                SWAGGER_URL_WEBJARS);
    }

    private void addSwagger2ResourceHandlers(ResourceHandlerRegistry registry) {
        // start - support 'sprignfox-swagger-ui-2.9.2' : 2020. 9. 3. 오후 5:15:51
        addResourceHandlers(registry, ArrayUtils.add(null, SWAGGER_URL_HTML), ArrayUtils.add(null, "classpath:/META-INF/resources/"));
        addResourceHandlers(registry, ArrayUtils.add(null, SWAGGER_URL_WEBJARS), ArrayUtils.add(null, "classpath:/META-INF/resources/webjars/"));
        // end - support 'sprignfox-swagger-ui-2.9.2' : 2020. 9. 3. 오후 5:15:51
    }

    /**
     * @since 2020. 9. 3.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController(SWAGGER_URL_UI, SWAGGER_URL_HTML);
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#extendMessageConverters(java.util.List)
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        context.getBeansOfType(HttpMessageConverter.class) // Bean 중에서 HttpMessageConverter 를 구현한 객체를찾아서.
                .values() //
                .stream() //
                // .filter(p -> p.getClass().getAnnotation(CustomHttpMessageConverter.class) != null) // 사용자 정의
                // CustomHttpMessageConverter
                .forEach(converter -> {
                    converters.add(converter);

                    logger.info("Register a HttpMessageConverter. {}.", converter);
                });

        WebMvcConfigurer.super.extendMessageConverters(converters);
    }
}
