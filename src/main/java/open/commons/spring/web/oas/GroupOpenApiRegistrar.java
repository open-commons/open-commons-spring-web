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
 * Date  : 2025. 10. 21. 오전 11:11:37
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.oas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.constraints.NotNull;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.beans.controller.RequestMappingProvider;
import open.commons.spring.web.config.ResourceConfiguration;

/**
 * '서비스 설정'에 작성된 {@link GroupedOpenApi} 정보를 읽어 {@link Bean} 으로 등록해 주는 클래스.<br>
 * 
 * {@link Configuration} 클래스에 아래와 같이 추가되어야 합니다.
 * 
 * <pre>
 * &#64;Configuration
 * @Import(GroupOpenApiRegistrar.class)
 * public class ConfigurationClass {
 *     ...
 * }
 * </pre>
 * 
 * <font color="red">* {@link ResourceConfiguration}에 등록되어 있습니다.</font>
 * 
 * @since 2025. 10. 21.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class GroupOpenApiRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    /** 내부 REST API Group 명 접두어 */
    private static final String PREFIX_BUILTIN_API_GROUP = "__builtin_";
    /** 내부 REST API 제공 여부 설정 */
    private static final String SHOW_BUILTIN_API = "open-commons.springdoc.show-builtin-api";

    /** 전체 설정 정보 */
    private Environment environment;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 10. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public GroupOpenApiRegistrar() {
    }

    /**
     *
     * @since 2025. 10. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see org.springframework.context.annotation.ImportBeanDefinitionRegistrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata,
     *      org.springframework.beans.factory.support.BeanDefinitionRegistry)
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, GroupedOpenApiProperties> groups = bindProperties(this.environment);
        if (groups.isEmpty()) {
            // 설정이 없으면 아무것도 등록하지 않음
            return;
        }

        /// 내부 REST API 제공 여부
        boolean showBuiltinApi = Binder.get(this.environment).bind(SHOW_BUILTIN_API, Boolean.class).orElse(true);

        for (Map.Entry<String, GroupedOpenApiProperties> entry : groups.entrySet()) {
            // API 식별정보
            final String key = entry.getKey();
            final GroupedOpenApiProperties props = Objects.requireNonNull(entry.getValue(), "GroupedOpenApiProperties must not be null");
            final String group = StringUtils.isNullOrEmptyString(props.getGroup()) ? key : props.getGroup().trim();

            // 내부 REST API 제공 검증
            if (!showBuiltinApi && group.startsWith(PREFIX_BUILTIN_API_GROUP)) {
                continue;
            }
            // Supplier 기반 정의만 등록(인스턴스 생성은 컨테이너가 필요할 때 수행)
            BeanDefinitionBuilder bdb = BeanDefinitionBuilder.genericBeanDefinition(GroupedOpenApi.class, () -> createGroupedOpenApi(this.environment, props, group));
            registry.registerBeanDefinition("GroupOpenedApi#" + group, bdb.getBeanDefinition());
        }
    }

    /**
     *
     * @since 2025. 10. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see org.springframework.context.EnvironmentAware#setEnvironment(org.springframework.core.env.Environment)
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * Environment에서 Map<String, GroupedOpenApiProperties> 바인딩 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param env
     * @return
     *
     * @since 2025. 10. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static Map<String, GroupedOpenApiProperties> bindProperties(Environment env) {
        // Map<String, GroupedOpenApiProperties> 데이터 유형을 지정
        Bindable<Map<String, GroupedOpenApiProperties>> target = Bindable.mapOf(String.class, GroupedOpenApiProperties.class);
        // 설정정보에서 {prefix}와 데이터 유형으로 조회
        BindResult<Map<String, GroupedOpenApiProperties>> result = Binder.get(env).bind(OpenApiConfig.PROPERTIES_GROUPED_OPEN_API, target);

        return result.orElseGet(Collections::emptyMap);
    }

    /**
     * 내부적으로 제공되는 REST API 경로 기본값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 10. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static Map<String, String> builtinRestApiClassPathOcswSupported() {
        Map<String, String> info = new HashMap<>();
        info.put(RequestMappingProvider.PROPERTIES_PREFIX, RequestMappingProvider.PROPERTIES_PREFIX_VALUE);
        return info;
    }

    /**
     * {@link GroupedOpenApi} 객체를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param env
     * @param prop
     *            {@link GroupedOpenApi} 생성 정보
     * @param group
     *            Swagger UI 그룹명
     *
     * @return
     *
     * @since 2025. 10. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private static GroupedOpenApi createGroupedOpenApi(Environment env, @NotNull GroupedOpenApiProperties prop, String group) {
        // 내부 제공 REST API 그룹 정보 설정. 대상: pathsToMatch
        if (OpenApiConfig.PROPERTIES_OCSW_API_GROUP.equalsIgnoreCase(group)) {
            // 중복방지 및 정렬을 위해서 TreeSet 사용.
            Set<String> pathsToMatch = new TreeSet<>();
            for (Entry<String, String> entry : builtinRestApiClassPathOcswSupported().entrySet()) {
                // 경로값 조회
                String classPath = env.getProperty(entry.getKey());
                if (StringUtils.isNullOrEmptyString(classPath)) {
                    classPath = entry.getValue();
                }

                // 경로값 검증
                if (StringUtils.isNullOrEmptyString(classPath)) {
                    throw new IllegalArgumentException("@RestController 에 설정된 RequestMapping#path() 값은, 빈 문자열이나 null 일 수 없습니다. 설정값=" + (classPath == null ? "null" : "[빈 문자열임]"));
                } else if ('/' != classPath.charAt(0)) {
                    throw new IllegalArgumentException("@RestController 에 설정된 RequestMapping#path() 값은 반드시 '/'로 시작해야 합니다.설정값=" + classPath);
                }
                String[] paths = classPath.split("/");
                if (paths.length < 2) {
                    throw new IllegalArgumentException("@RestController 에 설정된 RequestMapping#path() 값은 반드시 '/'로 시작해야 하며, 시작 경로값이 있어야 합니다. 설정값=" + classPath);
                }
                pathsToMatch.add(StringUtils.concat("/", true, true, false, paths[1], "**"));
            }
            prop.setPathsToMatch(new ArrayList<>(pathsToMatch));
        }
        return OpenApiConfig.transform(prop, group);
    }
}
