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
 * Date  : 2025. 9. 24. 오후 2:17:12
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.handler;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import open.commons.core.Result;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.authority.AuthorizedResourceUtils;
import open.commons.spring.web.authority.metadata.AuthorizedFieldMetadata;
import open.commons.spring.web.authority.metadata.AuthorizedObjectMetadata;
import open.commons.spring.web.beans.authority.FieldAccessAuthorityDecision;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.utils.BeanUtils;
import open.commons.spring.web.utils.ClassInspector;
import open.commons.spring.web.utils.PathUtils;

/**
 * 
 * @since 2025. 9. 24.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedModelAndViewHandlerInterceptor implements PostProcessingHandlerInterceptor {

    public static final String VIEW_NAME_JSON_VIEW = "jsonView";

    private Logger logger = LoggerFactory.getLogger(AuthorizedModelAndViewHandlerInterceptor.class);

    /** 동적으로 bean을 제공 */
    private final BeanUtils BEANS;
    /** {@link AuthorizedObjectMetadata} 정보 제공 */
    private final IAuthorizedResourcesMetadata authorizedResourcesMetadata;
    // 클래스별로 "처리 대상 필드 목록"을 캐싱
    // - @AuthorizedData 가 붙은 필드
    // - 혹은 nested 탐색이 필요한 컨테이너/복합타입 필드(재귀 진입용)
    private final ConcurrentHashMap<Class<?>, List<Field>> authorizedDataFieldCache = new ConcurrentHashMap<>();
    /** 클래스별로 필드에 대한 어노테이션 정보 */
    private final ConcurrentHashMap<String, AnnotatedContext> authorizedContextCache = new ConcurrentHashMap<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 24.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     * @param authorizedResourcesMetadata
     *            {@link AuthorizedObjectMetadata} 정보 제공
     *
     * @since 2025. 9. 24.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedModelAndViewHandlerInterceptor(@NotNull @Nonnull ApplicationContext context, @NotNull IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
        this.BEANS = BeanUtils.context(context);
        this.authorizedResourcesMetadata = authorizedResourcesMetadata;
    }

    /**
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.handler.PostProcessingHandlerInterceptor#afterRegistered(org.springframework.web.servlet.config.annotation.InterceptorRegistration)
     */
    @Override
    public void afterRegistered(InterceptorRegistration registry) {
        Environment environment = BEANS.context().getEnvironment();
        registry.addPathPatterns("/**");

        // 웹서비스 개발시 정적 자원 경로: ${spring.mvc.static-path-pattern}
        PathUtils.addEnvironmentProperty(environment, "spring.mvc.static-path-pattern", registry::excludePathPatterns);
        // --> begin: Swagger API
        // Swager Doc. API 경로: ${springdoc.api-docs.path}
        PathUtils.addEnvironmentProperty(environment, "springdoc.api-docs.path", v -> {
            if (v.endsWith("/**")) {
                registry.excludePathPatterns(v);
            } else {
                registry.excludePathPatterns(String.join("", v, "/**"));
            }
        });
        // Swagger 웹 페이지 자원 경로
        registry.excludePathPatterns("/swagger/**", "/swagger-ui/**");
    }

    /**
     * {@link AuthorizedRequestData}가 설정된 {@link Field}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 18.     박준홍         최초 작성
     * </pre>
     *
     * @param clazz
     * @return
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private List<Field> getProcessableFields(Class<?> clazz) {
        return this.authorizedDataFieldCache.computeIfAbsent(clazz, c -> {
            return ClassInspector.getAllFields(c).stream().map(f -> {
                f.setAccessible(true);
                return f;
            }).collect(Collectors.toList());
        });
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 24.     박준홍         최초 작성
     * </pre>
     * 
     * @param annoCtx
     * @param rawValue
     *            데이터
     * @return
     * @throws BeansException
     *
     * @since 2025. 9. 24.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private Object handleObject(AnnotatedContext annoCtx, Object rawValue) {
        if (rawValue == null || annoCtx == null) {
            return rawValue;
        }
        // #1. 데이터 접근여부
        boolean accessible = false;
        int handleType = annoCtx.handleType;
        String handleBean = null;

        if (annoCtx.handleType == AuthorizedField.NO_ASSINGED_HANDLE_TYPE) {
            FieldAccessAuthorityDecision fieldAccessible = isAllowed(annoCtx.authority, annoCtx.targetClass.getName(), null);
            accessible = fieldAccessible.accessible;
            handleType = fieldAccessible.handleType;
            handleBean = fieldAccessible.handleBean;
        }

        if (accessible) {
            return rawValue;
        } else {
            if (StringUtils.isNullOrEmptyString(handleBean)) {
                return annoCtx.unauthorized.handleObject(handleType, rawValue);
            } else {
                return this.BEANS.findBean(handleBean, IUnauthorizedFieldHandler.class, null, true).handleObject(handleType, rawValue);
            }
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param authority
     *            {@link Field} 접근제어 서비스
     * @param fqcn
     *            Fully Qualified Class Name
     * @param fieldName
     *            {@link Field} 이름
     * @return
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private FieldAccessAuthorityDecision isAllowed(IFieldAccessAuthorityProvider authority, String fqcn, String fieldName) {
        Result<FieldAccessAuthorityDecision> resultFieldAccessible = authority.isAllowed(fqcn, fieldName);
        if (resultFieldAccessible == null) {
            throw ExceptionUtils.newException(InternalServerException.class,
                    "Field 접근에 대한 판단은 'null'일 수가 없습니다. 원인=open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider.isAllowed(String, String) 구현이 올바르지 않습니다.");
        } else if (resultFieldAccessible.isError()) {
            throw ExceptionUtils.newException(InternalServerException.class, "필드 접근권한 조회시 오류가 발생하였습니다. 원인=%s", resultFieldAccessible.getMessage());
        } else if (resultFieldAccessible.getData() == null) {
            throw ExceptionUtils.newException(InternalServerException.class, "필드 접근권한 조회시 오류가 발생하였습니다. 원인='권한조회결과가 존재하지 않습니다.'");
        }

        return resultFieldAccessible.getData();
    }

    /**
     *
     * @since 2025. 9. 24.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView == null) {
            return;
        }

        Map<String, Object> model = modelAndView.getModel();
        // 'jsonView'는 데이터를 'MappingJackson2HttpMessageConverter'를 통해서 변환함.
        if (VIEW_NAME_JSON_VIEW.equals(modelAndView.getViewName())) {
            return;
        }

        Set<Object> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        for (Entry<String, Object> entry : model.entrySet()) {
            Object o = resolveRawValue(entry.getValue(), null, visited, true);
            o.toString();
            entry.setValue(o);
        }
    }

    /**
     * {@link AuthorizedRequestData} 정보와 TODO ( ) 정보를 확인하여, {@link AuthorizedRequestData#handleBean()},
     * {@link AuthorizedRequestData#handleType()}에 해당하는 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 22.     박준홍         최초 작성
     * </pre>
     * 
     * @param objectClass
     *            POJO 데이터 유형
     * @param field
     *            변수(필드) 정보
     * @return
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private AnnotatedContext resolveAnnotatedContext(Class<?> objectClass, Field field) {
        return this.authorizedContextCache.computeIfAbsent(
                // "{클래스}#{필드}" 형태의 식별정보
                String.join("#", objectClass.getName(), field.toString()) //
                , key -> {
                    final AuthorizedObject annoAuthorizedObject = objectClass.getAnnotation(AuthorizedObject.class);
                    final AuthorizedField annoAuthorizedField = field.getAnnotation(AuthorizedField.class);
                    final String fieldName = field.getName();

                    IFieldAccessAuthorityProvider authority = null;
                    IUnauthorizedFieldHandler unauthorized = null;

                    Supplier<String> authorityBeanNameOnObject = null;
                    Supplier<String> authorityBeanNameOnField = null;
                    Supplier<String> fieldHandleBeanNamOnObject = null;
                    Supplier<String> fieldHandleBeanNamOnField = null;

                    int handleType = AuthorizedField.NO_ASSINGED_HANDLE_TYPE;
                    // 조건1: ao 와 af 가 반드시 있어야 합니다.
                    if (annoAuthorizedObject != null && annoAuthorizedField != null) {
                        authorityBeanNameOnObject = annoAuthorizedObject::authorityBean;
                        authorityBeanNameOnField = annoAuthorizedField::authorityBean;
                        fieldHandleBeanNamOnObject = annoAuthorizedObject::fieldHandleBean;
                        fieldHandleBeanNamOnField = annoAuthorizedField::fieldHandleBean;

                        handleType = annoAuthorizedField.handleType();
                    }
                    // serialize 대상 데이터 유형(class)을 기준으로 검색
                    else if (this.authorizedResourcesMetadata.isAuthorizedField(objectClass, fieldName)) {
                        AuthorizedObjectMetadata aom = this.authorizedResourcesMetadata.getAuthorizedObjectMetadata(objectClass);
                        authorityBeanNameOnObject = () -> aom.getAuthorityBean();
                        fieldHandleBeanNamOnObject = () -> aom.getFieldHandleBean();

                        AuthorizedFieldMetadata afm = this.authorizedResourcesMetadata.getAuthorizedFieldMetadata(objectClass, fieldName);
                        authorityBeanNameOnField = () -> afm.getAuthorityBean();
                        fieldHandleBeanNamOnField = () -> afm.getFieldHandleBean();

                        handleType = afm.getHandleType();

                    } // 실제 field가 선언된 데이터 유형(class)을 기준으로 검색
                    else if (this.authorizedResourcesMetadata.isAuthorizedField(field.getDeclaringClass(), fieldName)) {
                        Class<?> declaringClass = field.getDeclaringClass();

                        AuthorizedObjectMetadata aom = this.authorizedResourcesMetadata.getAuthorizedObjectMetadata(declaringClass);
                        authorityBeanNameOnObject = () -> aom.getAuthorityBean();
                        fieldHandleBeanNamOnObject = () -> aom.getFieldHandleBean();

                        AuthorizedFieldMetadata afm = this.authorizedResourcesMetadata.getAuthorizedFieldMetadata(declaringClass, fieldName);
                        authorityBeanNameOnField = () -> afm.getAuthorityBean();
                        fieldHandleBeanNamOnField = () -> afm.getFieldHandleBean();

                        handleType = afm.getHandleType();
                    } else {
                        return AnnotatedContext.NULL;
                    }

                    authority = AuthorizedResourceUtils.getBean(this.BEANS, IFieldAccessAuthorityProvider.class, authorityBeanNameOnObject, authorityBeanNameOnField, false);
                    unauthorized = AuthorizedResourceUtils.getBean(this.BEANS, IUnauthorizedFieldHandler.class, fieldHandleBeanNamOnObject, fieldHandleBeanNamOnField, true);

                    AnnotatedContext annoCtx = new AnnotatedContext(field.getType(), authority, unauthorized, handleType);
                    return annoCtx;
                });
    }

    /**
     * POJO 형태이 데이터를 처리합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param targetValue
     *            POJO 형태의 데이터
     * @param visited
     *            중복 방지 기록
     * @return
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private Object resolvePojo(Object targetValue, Set<Object> visited) {
        if (targetValue == null || visited.contains(targetValue)) {
            return targetValue;
        }
        visited.add(targetValue);

        Class<?> pojoClass = targetValue.getClass();
        List<Field> fields = getProcessableFields(pojoClass);
        Object fieldRawValue = null;
        AnnotatedContext annoCtx = null;
        for (Field field : fields) {
            try {
                fieldRawValue = field.get(targetValue);
                if (fieldRawValue == null) {
                    continue;
                }
                annoCtx = resolveAnnotatedContext(pojoClass, field);
                if (annoCtx == AnnotatedContext.NULL) {
                    continue;
                }
                Object value = resolveRawValue(fieldRawValue, annoCtx, visited, false);
                field.set(targetValue, value);
            } catch (Exception e) {
                String errMsg = String.format(
                        "'권한 제어가 적용된 파라미터'를 처리하는 도중 오류가 발생하였습니다. pojo.class=%s, field.class=%s, field.name=%s, field.raw_value=%s, authority.beane=%s, unauthorized.bean=%s, handle.type=%s, 원인=%s" //
                        , pojoClass, field.getType(), field.getName(), fieldRawValue //
                        , annoCtx != null ? annoCtx.authority : null, annoCtx != null ? annoCtx.unauthorized : null, annoCtx != null ? annoCtx.handleType : null //
                        , e.getMessage());
                logger.error("{}", errMsg, e);

                throw ExceptionUtils.newException(InternalServerException.class, e, errMsg);
            }
        }

        return targetValue;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param rawValue
     *            처리할 데이터
     * @param annoCtx
     *            데이터 처리 정보
     * @param visited
     *            중복 방지 기록
     * @param fromRoot
     *            {@link #postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)}에서 호출한지 여부.<br>
     *            {@link ModelAndView#getModel()}의 값에 해당하는 정보를 처리하는 것이기 때문에, 'simple type'인 경우 값을 반환하기 위함.
     * @return
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private Object resolveRawValue(Object rawValue, AnnotatedContext annoCtx, Set<Object> visited, boolean fromRoot) {
        if (rawValue == null || visited.contains(rawValue)) {
            return rawValue;
        }

        Class<?> rawValueClass = rawValue.getClass();

        if (BeanUtils.isSimpleValueType(rawValueClass)) {
            return fromRoot ? rawValue : handleObject(annoCtx, rawValue);
        } else if (rawValueClass.isArray()) {
            Object[] valueArr = (Object[]) rawValue;
            int len = Array.getLength(rawValue);
            for (int i = 0; i < len; i++) {
                Object elemValue = Array.get(rawValue, i);
                if (elemValue != null) {
                    // 복호화/평문화 데이터를 받아 기존 데이터를 교체.
                    valueArr[i] = resolveRawValue(elemValue, annoCtx, visited, false);
                }
            }
        }
        // 명확한 List 처리
        else if (rawValue instanceof List) {
            @SuppressWarnings("unchecked")
            ListIterator<Object> itr = ((List<Object>) rawValue).listIterator();
            while (itr.hasNext()) {
                Object elemValue = itr.next();
                if (elemValue != null) {
                    itr.set(resolveRawValue(elemValue, annoCtx, visited, false));
                }
            }
        }
        // List를 제외한 Collection 자식 처리
        else if (rawValue instanceof Collection) {
            @SuppressWarnings("unchecked")
            Collection<Object> valueCol = (Collection<Object>) rawValue;
            Collection<Object> tempCol = valueCol.stream().collect(Collectors.toList());
            // 복호화/평문화 데이터를 받기 위해 기존 객체를 유지하고 내용만 비움.
            valueCol.clear();
            for (Object elem : tempCol) {
                valueCol.add(resolveRawValue(elem, annoCtx, visited, false));
            }
        } else if (rawValue instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<Object, Object> valueMap = (Map<Object, Object>) rawValue;
            for (Entry<?, ?> entry : valueMap.entrySet()) {
                Object elemValue = entry.getValue();
                if (elemValue != null) {
                    valueMap.put(entry.getKey(), resolveRawValue(elemValue, annoCtx, visited, false));
                }
            }
        } else {
            // @AuthorizedObject 어노테이션이 있거나, AuthorizedObjectMetadata 설정이 있거나.
            if (rawValueClass.getAnnotation(AuthorizedObject.class) != null || this.authorizedResourcesMetadata.isAuthorizedObject(rawValueClass)) {
                resolvePojo(rawValue, visited);
            }
        }

        return rawValue;
    }

    static class AnnotatedContext {

        static final AnnotatedContext NULL = new AnnotatedContext();

        /** 변수(필드) 클래스 */
        final Class<?> targetClass;
        /** {@link Field} 접근 제어 서비스 */
        final IFieldAccessAuthorityProvider authority;
        /** '접근제어' 기능 적용 서비스 */
        final IUnauthorizedFieldHandler unauthorized;
        /** '접근제어' 데이터 처리 방식 */
        final int handleType;

        private AnnotatedContext() {
            this(null, null, null, Integer.MIN_VALUE);
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 9. 25.		박준홍			최초 작성
         * </pre>
         *
         * @param targetClass
         *            변수(필드) 클래스
         * @param authority
         *            {@link Field} 접근 제어 서비스
         * @param unauthorized
         *            '접근제어' 기능 적용 서비스
         * @param handleType
         *            '접근제어' 데이터 처리 방식
         * 
         * @since 2025. 9. 25.
         * @version 0.8.0
         * @author parkjunhong77@gmail.com
         */
        public AnnotatedContext(Class<?> targetClass, IFieldAccessAuthorityProvider authority, IUnauthorizedFieldHandler unauthorized, int handleType) {
            this.targetClass = targetClass;
            this.authority = authority;
            this.unauthorized = unauthorized;
            this.handleType = handleType;
        }

        /**
         *
         * @since 2025. 9. 25.
         * @version 0.8.0
         * @author parkjunhong77@gmail.com
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("AnnotatedContext [targetClass=");
            builder.append(targetClass);
            builder.append(", authority=");
            builder.append(authority);
            builder.append(", unauthorized=");
            builder.append(unauthorized);
            builder.append(", handleType=");
            builder.append(handleType);
            builder.append("]");
            return builder.toString();
        }
    }
}
