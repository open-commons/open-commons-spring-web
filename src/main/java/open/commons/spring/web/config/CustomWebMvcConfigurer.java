/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import open.commons.core.utils.ArrayUtils;
import open.commons.spring.web.annotation.RequestValueSupported;
import open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration;
import open.commons.spring.web.beans.resolver.IAuthorizedDataResolver;
import open.commons.spring.web.enums.EnumConverter;
import open.commons.spring.web.enums.EnumConverterFactory;
import open.commons.spring.web.enums.EnumPackages;
import open.commons.spring.web.handler.InterceptorIgnoreUrlProperties;
import open.commons.spring.web.handler.InterceptorIgnoreValidator;
import open.commons.spring.web.handler.PostProcessingHandlerInterceptor;

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
 * <pre>
 * [개정이력]
 *      날짜    	| 작성자			|	내용
 * ------------------------------------------
 * 2019. 6.3.         parkjunhong        최초 작성
 * 2025. 4. 18.       parkjunhong        @Configuration, @EnableWebMvc, @SpringBootAppliication 제거: 구현 클래스에서 적용하도록 허용.
 * </pre>
 * 
 * @since 2019. 6. 3.
 * @version 0.0.3
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)S
 */
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    /** 사용자 정의 {@link WebMvcConfigurer} 들 중에서 가장 마지막으로 실행하기 위한 설정값 */
    public static final int ORDER = Ordered.LOWEST_PRECEDENCE;

    /**
     * Prefix of configurations in appliation.yml(or .properteis, or ...)<br>
     * 
     * @deprecated {@link #setEnumPkgs(EnumPackages)} 메소드 내부에서 {@link AutoConfigurationPackages}를 이용해서 BasePackage 정보를
     *             추출해서 사용함.<br>
     *             <font color="RED">추후 삭제됨.</font>
     * 
     */
    public static final String APPLICATION_PROPERTIES_PREFIX = "open-commons.spring.web.factory.enum";

    /** 정적 자원 경로 alias 패턴 */
    private static final String SPRING_MVC_STATIC_PATH_PATTERN = "spring.mvc.static-path-pattern";
    /**
     * 사용자 정의 정적 자원 경로 alias 패턴<br>
     * 
     * @since 2025. 11. 20.
     */
    private static final String SPRING_MVC_STATIC_PATH_PATTERN_X = "spring.mvc.static-path-pattern-x";
    /** 정적 자원 실제 경로 */
    private static final String SPRING_WEB_RESOURCES_STATIC_LOCATIONS = "spring.web.resources.static-locations";

    /** 기본 정적 자원 실제 경로 */
    private static final String[] DEFAULTS_STATIC_LOCATIONS = { "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/" };

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final ApplicationContext context;
    private final Environment environment;

    /** {@link HandlerInterceptor}에서 처리하지 않을 URL 패턴 설정 */
    private Set<InterceptorIgnoreUrlProperties> interceptorIgnoreUrlConfigurations;

    /** 커스텀 {@link HandlerMethodArgumentResolver} */
    private List<IAuthorizedDataResolver> argumentResolvers = new ArrayList<>();

    // @Value("${" + SPRING_WEB_RESOURCES_STATIC_LOCATIONS + "}")
    // private String[] staticLocations;

    /**
     * @deprecated {@link #setEnumPkgs(EnumPackages)} 메소드 내부에서 {@link AutoConfigurationPackages}를 이용해서 BasePackage 정보를
     *             추출해서 사용함.<br>
     *             <font color="RED">추후 삭제됨.</font>
     */
    @SuppressWarnings("unused")
    private EnumPackages enumPkgs;

    public CustomWebMvcConfigurer(ApplicationContext context, Environment env) {
        this.context = context;
        this.environment = env;
    }

    /**
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addArgumentResolvers(java.util.List)
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        this.argumentResolvers.forEach(r -> {
            resolvers.add(0, r);
        });
    }

    /**
     * {@link HandlerInterceptor}가 처리하지 않을 URL 패턴을 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param registry
     * @param patterns
     *            URL 패턴
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected void addExcludePatternsToInterceptor(InterceptorRegistration registry, List<String> patterns) {
        registry.excludePathPatterns(patterns);
        logger.info("[interceptor-excluded-pattern] registery={}, include.path={}", registry, patterns);
    }

    /**
     * {@link HandlerInterceptor}가 처리하지 않을 URL 패턴을 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param registry
     * @param patterns
     *            URL 패턴
     *
     * @since 2020. 9. 3.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected void addExcludePatternsToInterceptor(InterceptorRegistration registry, String... patterns) {
        addExcludePatternsToInterceptor(registry, Arrays.asList(patterns));
    }

    /**
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addFormatters(org.springframework.format.FormatterRegistry)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addFormatters(FormatterRegistry registry) {

        List<String> basePackages = AutoConfigurationPackages.get(this.context);

        List<String> pkgs = new ArrayList<>();
        // default package.
        pkgs.add("open.commons");
        // 사용자 정의 package
        pkgs.addAll(basePackages);

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

                                logger.info("[enum-converter] converter={}.", c);
                            });
                });

        registry.addConverterFactory(factory);
    }

    /**
     * {@link HandlerInterceptor}가 처리할 URL 패턴을 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param registry
     * @param patterns
     *            URL 패턴
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected void addIncludePatternsToInterceptor(InterceptorRegistration registry, List<String> patterns) {
        registry.addPathPatterns(patterns);
        logger.info("[interceptor-included-pattern] registery={}, include.path={}", registry, patterns);
    }

    /**
     * {@link HandlerInterceptor}가 처리할 URL 패턴을 추가합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param registry
     * @param patterns
     *            URL 패턴
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected void addIncludePatternsToInterceptor(InterceptorRegistration registry, String... patterns) {
        addIncludePatternsToInterceptor(registry, Arrays.asList(patterns));
    }

    /**
     *
     * @since 2023. 7. 21.
     * @version _._._
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        WebMvcConfigurer.super.addInterceptors(registry);

        // Bean 중에서 HandlerInterceptor 를 구현한 객체를 찾아서.
        Collection<HandlerInterceptor> customIntcptrs = context.getBeansOfType(HandlerInterceptor.class).values();

        customIntcptrs.stream() //
                .forEach(intcptr -> {
                    InterceptorRegistration intcptrReg = registry.addInterceptor(intcptr);
                    if (intcptr instanceof PostProcessingHandlerInterceptor) {
                        ((PostProcessingHandlerInterceptor) intcptr).afterRegistered(intcptrReg);
                    }
                    for (InterceptorIgnoreUrlProperties p : this.interceptorIgnoreUrlConfigurations) {
                        if (InterceptorIgnoreValidator.isAvailable(p, intcptr)) {
                            addIncludePatternsToInterceptor(intcptrReg, p.getIncludePathPatterns().stream().collect(Collectors.toList()));
                            addExcludePatternsToInterceptor(intcptrReg, p.getExcludePathPatterns().stream().collect(Collectors.toList()));
                        }
                    }
                    logger.info("[handler-interceptor] interceptor={}", intcptr);
                });
    }

    /**
     * @since 2020. 9. 3.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        addStaticResourceHandlers(registry);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param registry
     * @param handlers
     *            Resource Handler, {@link AntPathMatcher}
     * @param locations
     *            Resource Locations, {@link AntPathMatcher}
     *
     * @since 2025. 8. 11.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry, String handler, String[] locations) {
        registry.addResourceHandler(handler).addResourceLocations(locations);
        logger.info("[resource-handler-registry] resource.handler={}, resource.locations={}", handler, Arrays.toString(locations));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 9. 3.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param registry
     * @param handlers
     *            Resource Handler, {@link AntPathMatcher}
     * @param locations
     *            Resource Locations, {@link AntPathMatcher}
     *
     * @since 2020. 9. 3.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry, String[] handlers, String[] locations) {
        registry.addResourceHandler(handlers).addResourceLocations(locations);
        logger.info("[resource-handler-registry] resource.handler={}, resource.locations={}", Arrays.toString(handlers), Arrays.toString(locations));
    }

    /**
     * 서비스 설정에 기반하여 '정적 자원 경로'를 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 11.		parkjunhong77@gmail.com			최초 작성
     * 2025. 11. 20.    parkjunhong77@gmail.com     사용자 정의 정적 자원 경로 alias 패턴 처리 추가.
     * </pre>
     *
     * @param registry
     *
     * @since 2025. 8. 11.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected void addStaticResourceHandlers(ResourceHandlerRegistry registry) {
        String springMvcStaticPathPattern = bindProperties(this.environment, SPRING_MVC_STATIC_PATH_PATTERN, String.class, "/static/**");
        // 사용자 정의 패턴
        String[] customStaticPathPatterns = bindProperties(this.environment, SPRING_MVC_STATIC_PATH_PATTERN_X, String[].class, null);
        // '정적 자원 요청 패턴 검증'
        String[] handlers = Stream.of(ArrayUtils.add(customStaticPathPatterns, springMvcStaticPathPattern)) //
                .filter(new AntPathMatcher()::isPattern) //
                .toArray(String[]::new);
        // '정적 자원 경로'
        String[] locations = bindProperties(this.environment, SPRING_WEB_RESOURCES_STATIC_LOCATIONS, String[].class, DEFAULTS_STATIC_LOCATIONS);
        addResourceHandlers(registry, handlers, locations);
    }

    /**
     *
     * @since 2025. 8. 11.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#extendMessageConverters(java.util.List)
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        WebMvcConfigurer.super.extendMessageConverters(converters);

        // context.getBeansOfType(HttpMessageConverter.class) // Bean 중에서 HttpMessageConverter 를 구현한 객체를찾아서.
        // .values() //
        // .stream() //
        // // .filter(p -> p.getClass().getAnnotation(CustomHttpMessageConverter.class) != null) // 사용자 정의
        // // CustomHttpMessageConverter
        // .forEach(converter -> {
        // converters.add(converter);
        //
        // logger.info("Register a HttpMessageConverter. {}.", converter);
        // });

    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param argResolver
     * @param modelResolver
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Autowired
    public void setAuthorizedDataResolver(@Qualifier(CustomWebMvcAutoConfiguration.BEAN_QUALIFIER_AUTHORIZED_DATA_RESOLVERS) List<IAuthorizedDataResolver> resolvers) {
        this.argumentResolvers.addAll(resolvers);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param enumPkgs
     *            the enumPkgs to set
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #enumPkgs
     * 
     * @deprecated {@link #setEnumPkgs(EnumPackages)} 메소드 내부에서 {@link AutoConfigurationPackages}를 이용해서 BasePackage 정보를
     *             추출해서 사용함.<br>
     *             <font color="RED">추후 삭제됨.</font>
     */
    @Autowired
    public void setEnumPkgs(EnumPackages enumPkgs) {
        this.enumPkgs = enumPkgs;
    }

    /**
     * {@link HandlerInterceptor}에서 처리하지 않을 URL 패턴 설정을 등록합니다. <br>
     * {@link InterceptorRegistration#excludePathPatterns(String...)}에 사용될 정보입니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param interceptorIgnoreUrlConfigurations
     *            the interceptorIgnoreUrlConfigurations to set
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #interceptorIgnoreUrlConfigurations
     */
    @Autowired
    public void setInterceptorIgnoreUrlConfigurations(
            @Qualifier(GlobalServletConfiguration.BEAN_QUALIFIER_PRIMARY_INTERCEPTOR_IGNORE_URL_PATTERNS) Set<InterceptorIgnoreUrlProperties> interceptorIgnoreUrlConfigurations) {
        this.interceptorIgnoreUrlConfigurations = interceptorIgnoreUrlConfigurations;
    }

    /**
     * 요청한 설정 정보를 제공하고, 존재하지 않는 경우는 기본값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 12.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param env
     *            전체 서비스 설정
     * @param property
     *            설정 이름
     * @param propClass
     *            설정 데이터 유형
     * @param defaultValue
     *            기본 설정값
     * @return
     *
     * @since 2025. 11. 12.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static <T> T bindProperties(Environment env, String property, Class<T> propClass, T defaultValue) {
        return Binder.get(env).bind(property, propClass).orElse(defaultValue);
    }
}
