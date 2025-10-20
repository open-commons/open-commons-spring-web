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
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
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
import org.springframework.web.bind.annotation.RestController;

import open.commons.core.utils.CollectionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @since 2025. 9. 29.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Tag(name = "REST API 제공 서비스", description = "서비스가 제공하는 REST API 정보를 제공합니다.")
@RestController
@RequestMapping("${open-commons.spring.web.beans.controller.request-mapping-provider.prefix:/builtin/api/rest-api-metadata}")
@Validated
@AuthorizedRequest
public class RequestMappingProvider implements ApplicationListener<ApplicationReadyEvent> {

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("^\\$\\{\\s*([^:}]+)(?::([^}]*))?\\s*}$");

    private Logger log = LoggerFactory.getLogger(getClass());

    private ApplicationContext context;

    private Environment env;

    /** {@link Controller}가 설정된 클래스에서 정의한 REST API */
    private List<RestApiGroup> controllerApiGroups = new ArrayList<>();
    /** {@link RestController}가 설정된 클래스에서 정의한 REST API */
    private List<RestApiGroup> restControllerApiGroups = new ArrayList<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
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

    private void apiInfo(List<RestApiGroup> apiGroup) {
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
     * 2025. 9. 29.		박준홍			최초 작성
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
    private RestApiGroup buildRestApiGroup(Class<?> clazz, Class<? extends Annotation> controllerType) {

        // --- 클래스 조건 검사 ---
        Tag tag = AnnotatedElementUtils.findMergedAnnotation(clazz, Tag.class);
        if (tag == null) {
            log.warn("Controller [{}] 은 @Tag 어노테이션이 없습니다. 스킵합니다.", clazz.getName());
            return null;
        }

        RestApiGroup group = new RestApiGroup();
        group.setName(tag.name());
        group.setDescription(tag.description());
        List<RestApiDecl> apiDecls = new ArrayList<>();

        RequestMapping classMapping = AnnotatedElementUtils.findMergedAnnotation(clazz, RequestMapping.class);
        String[] classPaths = (classMapping != null && classMapping.path().length > 0) ? classMapping.path() : new String[] { "" };

        // --- 메소드 검사 ---
        for (Method method : clazz.getDeclaredMethods()) {
            Operation op = AnnotatedElementUtils.findMergedAnnotation(method, Operation.class);

            if (op == null) {
                continue;
            }

            RequestMapping req = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
            GetMapping get = AnnotatedElementUtils.findMergedAnnotation(method, GetMapping.class);
            PostMapping post = AnnotatedElementUtils.findMergedAnnotation(method, PostMapping.class);
            PutMapping put = AnnotatedElementUtils.findMergedAnnotation(method, PutMapping.class);
            PatchMapping patch = AnnotatedElementUtils.findMergedAnnotation(method, PatchMapping.class);
            DeleteMapping delete = AnnotatedElementUtils.findMergedAnnotation(method, DeleteMapping.class);

            // 기본값
            final List<RequestMethod> httpMethods = new ArrayList<>();
            final List<String> methodPaths = new ArrayList<>();

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
                                        String fullPath = COMPINE_PATH.apply(findConfigurationValue(classPath), findConfigurationValue(methodPath));
                                        decl.setPath(fullPath);
                                        return decl;
                                    })))
                    .collect(Collectors.toList()));
        }
        group.setRestApis(apiDecls);

        if (group.getRestApis().isEmpty()) {
            log.warn("Controller [{}] 은 유효한 REST API 메소드가 없습니다.", clazz.getName());
        }

        return group;
    }

    /**
     * Spring Environment Property Placeholder 패턴(${...:default})을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param input
     * @return
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private String findConfigurationValue(String input) {
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

    /**
     * 서비스가 제공하는 REST API 목록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Operation(summary = "REST API Metadata 제공", description = "")
    @GetMapping(path = "${open-commons.spring.web.beans.controller.request-mapping-provider.get-all:}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiMetadataDTO> getRestApiMetadata() {
        return ResponseEntity.ok(new RestApiMetadataDTO(this.controllerApiGroups, this.restControllerApiGroups));
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

        scanAllControllers();

        if (this.log.isDebugEnabled()) {
            apiInfo(this.controllerApiGroups);
            apiInfo(this.restControllerApiGroups);
        }
    }

    private void scanAllControllers() {
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
                RestApiGroup group = buildRestApiGroup(clazz, RestController.class);
                if (group != null) {
                    this.restControllerApiGroups.add(group);
                }
            }
            // --- Controller 전용 처리 ---
            else if (clazz.isAnnotationPresent(Controller.class)) {
                RestApiGroup group = buildRestApiGroup(clazz, Controller.class);
                if (group != null) {
                    this.controllerApiGroups.add(group);
                }
            }
        }
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
         * 2025. 9. 29.		박준홍			최초 작성
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
         * 2025. 9. 29.     박준홍(jhpark@ymtech.co.kr)            최초 작성
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
         * 2025. 9. 29.     박준홍(jhpark@ymtech.co.kr)            최초 작성
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
         * 2025. 9. 29.     박준홍(jhpark@ymtech.co.kr)            최초 작성
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
         * 2025. 9. 29.     박준홍(jhpark@ymtech.co.kr)            최초 작성
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
         * @author 박준홍(jhpark@ymtech.co.kr)
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
