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
 * Date  : 2025. 9. 29. 오후 4:01:13
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import open.commons.core.utils.CollectionUtils;
import open.commons.core.utils.StreamUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Spring기반 어플리케이션이 제공하는 REST API 정보를 제공합니다.<br>
 * {@link Controller}, {@link RestController} 를 구현한 클래스에는 {@link Tag#name()} 어노테이션이 설정되어야 하며,<br>
 * 메소드에는 {@link Operation#summary()} 어노테이션 정보가 설정되어야 합니다.<br>
 * 
 * 설정파일(yaml 파일 기준)에는 아래와 같이 설정하여 기본 Path 정보를 수정할 수 있습니다.
 * 
 * <pre>
 * open-commons:
 *   spring:
 *     web:
 *       beans:
 *         controller:
 *          {controller-name}:
 *            prefix: {RequestMapping.path of controller class}
 *            {method1-path}: {path of method1}
 *            {method2-path}: {path of method2}
 * </pre>
 * 
 * 기본 Path 정보는 아래 설정과 동일하게 적용됩니다.
 * 
 * <pre>
 * open-commons:
 *   spring:
 *     web:
 *       beans:
 *         controller:
 *          request-mapping-provider:
 *            prefix: /builtin/api/rest-api-metadata
 *            get-all:
 * </pre>
 * 
 * @since 2025. 9. 29.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Tag(name = "REST API 제공 서비스", description = "서비스가 제공하는 REST API 정보를 제공합니다.")
@RestController
@RequestMapping("${" + RequestMappingProvider.PROPERTIES_PREFIX + ":" + RequestMappingProvider.PROPERTIES_PREFIX_VALUE + "}")
@Validated
@AuthorizedRequest
public class RequestMappingProvider implements ApplicationListener<ApplicationReadyEvent> {

    /** REST API Metadata 제공 'controll'의 {@link RequestMapping#path()} 항목명 */
    public static final String PROPERTIES_PREFIX = "open-commons.spring.web.beans.controller.request-mapping-provider.prefix";
    /** REST API Metadata 제공 'controll'의 {@link RequestMapping#path()} 기본값 */
    public static final String PROPERTIES_PREFIX_VALUE = "/builtin/api/rest-api-metadata";

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("^\\$\\{\\s*([^:}]+)(?::([^}]*))?\\s*}$");

    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext context;
    private Environment env;

    /** {@link Controller}가 설정된 클래스에서 정의한 REST API */
    private List<RestApiGroup> controllerApiGroups = new ArrayList<>();
    /** {@link RestController}가 설정된 클래스에서 정의한 REST API */
    private List<RestApiGroup> restControllerApiGroups = new ArrayList<>();

    /** {@link RestApiGroup} 정렬 */
    private final Function<OrderBy, Comparator<RestApiGroup>> GROUP_ORDER = orderBy -> (g1, g2) -> {
        switch (orderBy) {
            case name:
                return g1.getName().compareTo(g2.getName());
            case path:
                return g1.getGroupPath().compareTo(g2.getGroupPath());
            default:
                // unreachable code
                throw new IllegalArgumentException("허용되지 않는 정보입니다. 허용범위=" + OrderBy.name + ", " + OrderBy.path);
        }
    };
    /** {@link RestApiDecl} 정렬 */
    private final Function<OrderBy, Comparator<RestApiDecl>> API_ORDER = orderBy -> (a1, a2) -> {
        switch (orderBy) {
            case name:
                return a1.getName().compareTo(a2.getName());
            case path:
                return a1.getPath().compareTo(a2.getPath());
            default:
                // unreachable code
                throw new IllegalArgumentException("허용되지 않는 정보입니다. 허용범위=" + OrderBy.name + ", " + OrderBy.path);
        }
    };

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param context
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public RequestMappingProvider(ApplicationContext context) {
        this.context = context;
        this.env = context.getEnvironment();
    }

    private void _apiInfo(List<RestApiGroup> apiGroup) {
        // 여기서 groups 를 로깅하거나, Bean 으로 등록하거나, 별도 API 로 제공 가능
        apiGroup.forEach(g -> {
            String tagDesc = null;
            this.log.debug("Group: {}{}", g.getName(), StringUtils.isNullOrEmptyString(tagDesc = g.getDescription()) ? "" : String.join(tagDesc, " (", ")"));
            g.getRestApis().forEach(api -> {
                String opDesc = null;
                this.log.debug("  - {} {}, {}{}", String.format("%-8s", String.join(api.getMethod().toString(), "[", "]")), api.getPath(), api.getName(),
                        StringUtils.isNullOrEmptyString(opDesc = api.getDescription()) ? "" : String.join(opDesc, " (", ")"));
            });
        });
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param controllerType
     *            검색할 컨트롤러 어노테이션
     * @return
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private List<RestApiGroup> _buildRestApiGroup(Class<?> clazz, Class<? extends Annotation> controllerType) {

        // --- 클래스 조건 검사 ---
        Tag tag = AnnotatedElementUtils.findMergedAnnotation(clazz, Tag.class);
        if (tag == null) {
            log.warn("Controller [{}] 은 @Tag 어노테이션이 없습니다. 스킵합니다.", clazz.getName());
            return null;
        }

        List<RestApiDecl> apiDecls = new ArrayList<>();

        RequestMapping classMapping = AnnotatedElementUtils.findMergedAnnotation(clazz, RequestMapping.class);
        String[] classPaths = (classMapping != null && classMapping.path().length > 0) ? classMapping.path() : new String[] { "" };

        // --- 메소드 검사 ---
        for (Method method : clazz.getDeclaredMethods()) {
            Operation op = AnnotatedElementUtils.findMergedAnnotation(method, Operation.class);
            if (op == null) {
                continue;
            }

            // 기본값
            final List<RequestMethod> httpMethods = new ArrayList<>();
            final List<String> methodPaths = new ArrayList<>();

            _findMethodAndPath(httpMethods, methodPaths, method);

            // 조건 검사
            if (httpMethods.size() < 1) {
                continue;
            }

            // RequestMapping#path() 또는 RequestMapping#value() 값을 설정하지 않은 경우에 대한 방어 코드
            if (methodPaths.size() < 1) {
                methodPaths.add("");
            }

            BiFunction<String, String, String> COMPINE_PATH = (base, path) -> {
                if (base == null)
                    base = "";
                if (path == null)
                    path = "";
                return String.join("", base, path).replaceAll("//+", "/");
            };

            // --- 조합 생성 (N × M × K) ---
            apiDecls.addAll(Stream.of(classPaths) //
                    .flatMap(classPath -> methodPaths.stream() //
                            .flatMap(methodPath -> httpMethods.stream() //
                                    .map(httpMethod -> {
                                        RestApiDecl decl = new RestApiDecl();
                                        decl.setName(op.summary());
                                        decl.setDescription(op.description());
                                        decl.setMethod(httpMethod);
                                        String cp = _findConfigurationValue(classPath);
                                        String mp = _findConfigurationValue(methodPath);
                                        String fullPath = COMPINE_PATH.apply(cp, _findConfigurationValue(methodPath));
                                        decl.setPath(fullPath);
                                        decl.setClassPath(cp);
                                        decl.setMethodPath(mp);
                                        return decl;
                                    })))
                    .collect(Collectors.toList()));
        }

        if (apiDecls.isEmpty()) {
            log.warn("Controller [{}] 은 유효한 REST API 메소드가 없습니다.", clazz.getName());
            return new ArrayList<>();
        }

        List<RestApiGroup> apiGroups = StreamUtils.toMap(apiDecls.stream(), api -> api.getClassPath(), api -> api) //
                .entrySet().stream() //
                .map(entry -> {
                    RestApiGroup g = new RestApiGroup();
                    g.setName(tag.name());
                    g.setDescription(tag.description());
                    g.setGroupPath(entry.getKey());
                    g.setRestApis(entry.getValue());

                    return g;
                })//
                .collect(Collectors.toList());

        // return group;
        return apiGroups;
    }

    private void _findAndAddRestApiGroups(Class<?> clazz, Class<? extends Annotation> controllerType, List<RestApiGroup> restApiGroupBucket) {
        List<RestApiGroup> groups = _buildRestApiGroup(clazz, controllerType);
        if (groups != null) {
            restApiGroupBucket.addAll(groups);
        }
    }

    /**
     * Spring Environment Property Placeholder 패턴(${...:default})을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param input
     * @return
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private String _findConfigurationValue(String input) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(input);
        if (matcher.matches()) {
            String propertyName = matcher.group(1); // 속성 이름
            String defaultValue = matcher.group(2); // 기본값 (없으면 null)

            String configValue = this.env.getProperty(propertyName);
            if (StringUtils.isNullOrEmptyString(configValue)) {
                return defaultValue;
            } else {
                return configValue;
            }
        } else {
            return input;
        }
    }

    private void _findMethodAndPath(List<RequestMethod> httpMethods, List<String> methodPaths, Method method) {
        RequestMapping req = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
        GetMapping get = AnnotatedElementUtils.findMergedAnnotation(method, GetMapping.class);
        PostMapping post = AnnotatedElementUtils.findMergedAnnotation(method, PostMapping.class);
        PutMapping put = AnnotatedElementUtils.findMergedAnnotation(method, PutMapping.class);
        PatchMapping patch = AnnotatedElementUtils.findMergedAnnotation(method, PatchMapping.class);
        DeleteMapping delete = AnnotatedElementUtils.findMergedAnnotation(method, DeleteMapping.class);

        // 기본값
        if (get != null) {
            httpMethods.add(RequestMethod.GET);
            methodPaths.addAll(Arrays.asList(get.path()));
        } else if (post != null) {
            httpMethods.add(RequestMethod.POST);
            methodPaths.addAll(Arrays.asList(post.path()));
        } else if (put != null) {
            httpMethods.add(RequestMethod.PUT);
            methodPaths.addAll(Arrays.asList(put.path()));
        } else if (patch != null) {
            httpMethods.add(RequestMethod.PATCH);
            methodPaths.addAll(Arrays.asList(patch.path()));
        } else if (delete != null) {
            httpMethods.add(RequestMethod.DELETE);
            methodPaths.addAll(Arrays.asList(delete.path()));
        } else if (req != null) {
            httpMethods.addAll(req.method().length > 0 ? Arrays.asList(req.method()) : CollectionUtils.newList(RequestMethod.GET));
            methodPaths.addAll(req.path().length > 0 ? Arrays.asList(req.path()) : CollectionUtils.newList(""));
        } else {
            methodPaths.add("");
        }
    }

    private void _scanAllControllers() {
        Map<String, Object> controllers = this.context.getBeansWithAnnotation(RestController.class);

        for (Object controller : controllers.values()) {

            // CGLIB 정보 제거
            Class<?> clazz = ClassUtils.getUserClass(controller);
            if (clazz == null) {
                // JDK Proxy 정보 제고
                clazz = AopUtils.getTargetClass(controller);
            }

            // --- RestController 전용 처리 ---
            if (clazz.isAnnotationPresent(RestController.class)) {
                _findAndAddRestApiGroups(clazz, RestController.class, this.restControllerApiGroups);
            }
            // --- Controller 전용 처리 ---
            else if (clazz.isAnnotationPresent(Controller.class)) {
                _findAndAddRestApiGroups(clazz, Controller.class, this.controllerApiGroups);
            }
        }
    }

    /**
     * @param groupOrder
     *            {@link RestApiGroup} 정렬
     * @param apiOrder
     *            {@link RestApiDecl} 정렬
     * @return
     *
     * @since 2025. 10. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private RestApiMetadataDTO createRestApiMetadata(Comparator<RestApiGroup> groupOrder, Comparator<RestApiDecl> apiOrder) {

        List<RestApiGroup> sortCtrlApiGroups = this.controllerApiGroups.stream() //
                .sorted(groupOrder) //
                .map(g -> {
                    g.setRestApis(g.getRestApis().stream()//
                            .sorted(apiOrder)//
                            .collect(Collectors.toList()));
                    return g;
                }).collect(Collectors.toList());

        List<RestApiGroup> sortRestCtrlApiGroups = this.restControllerApiGroups.stream() //
                .sorted(groupOrder) //
                .map(g -> {
                    g.setRestApis(g.getRestApis().stream()//
                            .sorted(apiOrder)//
                            .collect(Collectors.toList()));
                    return g;
                }).collect(Collectors.toList());

        return new RestApiMetadataDTO(sortCtrlApiGroups, sortRestCtrlApiGroups);
    }

    /**
     * 서비스가 제공하는 REST API 목록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 29.     parkjunhong77@gmail.com         최초 작성
     * 2025. 10. 20.    parkjunhong77@gmail.com
     * </pre>
     *
     * @param groupOrder
     * @param apiOrder
     * @return
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param groupOrder
     * @param apiOrder
     * @return
     *
     * @since 2025. 10. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Operation(summary = "REST API Metadata 제공", description = "")
    @GetMapping(path = "${open-commons.spring.web.beans.controller.request-mapping-provider.get-all:}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiMetadataDTO> getRestApiMetadata(@RequestParam(name = "groupOrder", defaultValue = "name") OrderBy groupOrder,
            @RequestParam(name = "apiOrder", defaultValue = "name") OrderBy apiOrder) {
        return ResponseEntity.ok(createRestApiMetadata(GROUP_ORDER.apply(groupOrder), API_ORDER.apply(apiOrder)));
    }

    /**
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        _scanAllControllers();

        if (this.log.isDebugEnabled()) {
            _apiInfo(this.controllerApiGroups);
            _apiInfo(this.restControllerApiGroups);
        }
    }

    public static enum OrderBy {
        name, path;
    }

    class RestApiMetadataDTO {

        List<RestApiGroup> controller;
        List<RestApiGroup> restController;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
         * </pre>
         *
         * @param controller
         * @param restController
         *
         * @since 2025. 9. 29.
         * @version 0.8.0
         * @author parkjunhong77@gmail.com
         */
        public RestApiMetadataDTO(List<RestApiGroup> controller, List<RestApiGroup> restController) {
            this.controller = controller;
            this.restController = restController;
        }

        /**
         *
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 9. 29.     parkjunhong77@gmail.com(jhpark@ymtech.co.kr)            최초 작성
         * </pre>
         * 
         * @return the controller
         *
         * @version 0.1.0
         * @since 2025. 9. 29.
         * 
         * @see #controller
         */
        public List<RestApiGroup> getController() {
            return controller;
        }

        /**
         *
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 9. 29.     parkjunhong77@gmail.com(jhpark@ymtech.co.kr)            최초 작성
         * </pre>
         * 
         * @return the restController
         *
         * @version 0.1.0
         * @since 2025. 9. 29.
         * 
         * @see #restController
         */
        public List<RestApiGroup> getRestController() {
            return restController;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 9. 29.     parkjunhong77@gmail.com(jhpark@ymtech.co.kr)            최초 작성
         * </pre>
         *
         * @param controller
         *            the controller to set
         *
         * @version 0.1.0
         * @since 2025. 9. 29.
         * 
         * @see #controller
         */
        public void setController(List<RestApiGroup> controller) {
            this.controller = controller;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 9. 29.     parkjunhong77@gmail.com(jhpark@ymtech.co.kr)            최초 작성
         * </pre>
         *
         * @param restController
         *            the restController to set
         *
         * @version 0.1.0
         * @since 2025. 9. 29.
         * 
         * @see #restController
         */
        public void setRestController(List<RestApiGroup> restController) {
            this.restController = restController;
        }

        /**
         * @version 0.1.0
         * @since 2025. 9. 29.
         * @author parkjunhong77@gmail.com(jhpark@ymtech.co.kr)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("RestApiMeta [controller=");
            builder.append(controller);
            builder.append(", restController=");
            builder.append(restController);
            builder.append("]");
            return builder.toString();
        }
    }
}
