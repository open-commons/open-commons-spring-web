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
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import open.commons.core.TwoValueObject;
import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.utils.BeanUtils;
import open.commons.spring.web.utils.ClassInspector;

/**
 * 
 * @since 2025. 9. 24.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedModelAndViewHandlerInterceptor implements HandlerInterceptor {

    public static final String VIEW_NAME_JSON_VIEW = "jsonView";

    private Logger logger = LoggerFactory.getLogger(AuthorizedModelAndViewHandlerInterceptor.class);

    // 클래스별로 "처리 대상 필드 목록"을 캐싱
    // - @AuthorizedData 가 붙은 필드
    // - 혹은 nested 탐색이 필요한 컨테이너/복합타입 필드(재귀 진입용)
    private final ConcurrentHashMap<Class<?>, List<Field>> authorizedDataFieldCache = new ConcurrentHashMap<>();

    private final ApplicationContext context;

    private final IAuthorizedResourcesMetadata authorizedResourceMetadata;

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
     * @param authorizedResourceMetadata
     *            TODO
     *
     * @since 2025. 9. 24.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedModelAndViewHandlerInterceptor(@NotNull @Nonnull ApplicationContext context, IAuthorizedResourcesMetadata authorizedResourceMetadata) {
        this.context = context;
        this.authorizedResourceMetadata = authorizedResourceMetadata;
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
            Object o = resolveRawValue(entry.getValue(), null, AuthorizedField.NO_ASSINGED_HANDLE_TYPE, visited, true);
            entry.setValue(o);
        }
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
        return this.authorizedDataFieldCache.computeIfAbsent(clazz, ClassInspector::getAllFields);
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
     * @param targetClass
     *            POJO 데이터 유형
     * @param fieldName
     *            필드 이름
     * @param anno
     *            <li>first: {@link AuthorizedRequestData#handleBean()} 정보
     *            <li>second: {@link AuthorizedRequestData#handleType()()} 정보
     *
     * @return
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private TwoValueObject<String, Integer> resolveAnnotatedContext(Class<?> targetClass, String fieldName, AuthorizedField anno) {
        if (anno != null) {
            return new TwoValueObject<>(anno.fieldHandleBean(), anno.handleType());
        } else {

            // String handleBean = this.authorizedRequestDataMetadata.getHandleBeanName(targetClass, fieldName);
            // if (StringUtils.isNullOrEmptyString(handleBean)) {
            // return null;
            // }
            // int handleType = this.authorizedRequestDataMetadata.getHandleType(targetClass, fieldName);
            // if (handleType == AuthorizedRequestData.NO_ASSINGED_HANDLE_TYPE) {
            // return null;
            // }

            // return new TwoValueObject<>(handleBean, handleType);
            return null;
        }
    }

    private Object resolveRawValue(Object rawValue, String handleBean, int handleType, Set<Object> visited, boolean fromRoot) {
        if (rawValue == null || visited.contains(rawValue)) {
            return rawValue;
        }
        visited.add(rawValue);

        Class<?> rawValueClass = rawValue.getClass();

        if (BeanUtils.isSimpleValueType(rawValueClass)) {
            return fromRoot ? rawValue : handleObject(handleBean, handleType, rawValue);
        } else if (rawValueClass.isArray()) {
            Object[] valueArr = (Object[]) rawValue;
            int len = Array.getLength(rawValue);
            for (int i = 0; i < len; i++) {
                Object elemValue = Array.get(rawValue, i);
                if (elemValue != null) {
                    // 복호화/평문화 데이터를 받아 기존 데이터를 교체.
                    valueArr[i] = resolveRawValue(elemValue, handleBean, handleType, visited, false);
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
                    itr.set(resolveRawValue(elemValue, handleBean, handleType, visited, false));
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
                valueCol.add(resolveRawValue(elem, handleBean, handleType, visited, false));
            }
        } else if (rawValue instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<Object, Object> valueMap = (Map<Object, Object>) rawValue;
            for (Entry<?, ?> entry : valueMap.entrySet()) {
                Object elemValue = entry.getValue();
                if (elemValue != null) {
                    valueMap.put(entry.getKey(), resolveRawValue(elemValue, handleBean, handleType, visited, false));
                }
            }
        } else {
            resolvePojo(rawValue, visited);
        }

        return rawValue;
    }

    private Object resolvePojo(Object targetValue, Set<Object> visited) {
        if (targetValue == null || visited.contains(targetValue)) {
            return null;
        }
        visited.add(targetValue);

        Class<?> targetClass = targetValue.getClass();
        List<Field> fields = getProcessableFields(targetClass);
        Object rawValue = null;
        String handleBean = null;
        int handleType = AuthorizedField.NO_ASSINGED_HANDLE_TYPE;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                rawValue = field.get(targetValue);
                if (rawValue == null) {
                    continue;
                }
                TwoValueObject<String, Integer> annotatedValue = resolveAnnotatedContext(targetClass, field.getName(), field.getAnnotation(AuthorizedField.class));
                if (annotatedValue == null) {
                    continue;
                }
                handleBean = annotatedValue.first;
                handleType = annotatedValue.second;
                Object value = resolveRawValue(rawValue, handleBean, handleType, visited, false);
                field.set(targetValue, value);
            } catch (BeansException e) {
                String errMsg = String
                        .format("'권한 제어가 적용된 파라미터'를 처리하는 도중 오류가 발생하였습니다. field.name=%s, field.raw_value=%s, handle.beanname=%s, handle.type=%s, handle.class=%s, 원인=%s" //
                                , field.getName(), rawValue, handleBean, handleType, IAuthorizedRequestDataHandler.class.getName(), e.getMessage());
                logger.error("{}", errMsg, e);

                throw ExceptionUtils.newException(InternalServerException.class, e, errMsg);
            } catch (IllegalAccessException e) {
                String errMsg = String.format("'%s.%s' 필드 처리시 오류가 발생하였습니다.", targetValue.getClass().getName(), field.getName());
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 24.     박준홍         최초 작성
     * </pre>
     * 
     * @param handleBean
     *            {@link AuthorizedField#fieldHandleBean()}에 해당하는 값
     * @param handleType
     *            {@link AuthorizedField#handleType()}에 해당하는 값
     * @param rawValue
     *            데이터
     *
     * @return
     * @throws BeansException
     *
     * @since 2025. 9. 24.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private Object handleObject(String handleBean, int handleType, Object rawValue) throws BeansException {
        if (rawValue == null || handleBean == null || handleType == AuthorizedField.NO_ASSINGED_HANDLE_TYPE) {
            return rawValue;
        }
        try {
            IUnauthorizedFieldHandler handler = this.context.getBean(handleBean, IUnauthorizedFieldHandler.class);
            return handler.handleObject(handleType, rawValue);
        } catch (BeansException e) {
            throw e;
        }
    }
}
