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
 * Date  : 2025. 9. 18. 오후 2:27:13
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.resolver;

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
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import open.commons.core.TwoValueObject;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesConfiguration;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.utils.ClassInspector;

/**
 * "{@link AuthorizedRequestData} && ({@link ModelAttribute} ||
 * {@link ModelAttributeMethodProcessor#annotationNotRequired} )"가 선언된 파라미터를 처리합니다.<br>
 * {@link AuthorizedResourcesConfiguration}을 통해서 {@link Bean}으로 제공됩니다.
 * 
 * @since 2025. 9. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedDataModelAttributeResolver extends ModelAttributeMethodProcessor implements IAuthorizedDataResolver {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @NotNull
    private final ApplicationContext applicationContext;

    @NotNull
    private final IAuthorizedRequestDataMetadata authorizedRequestDataMetadata;

    // 클래스별로 "처리 대상 필드 목록"을 캐싱
    // - @AuthorizedData 가 붙은 필드
    // - 혹은 nested 탐색이 필요한 컨테이너/복합타입 필드(재귀 진입용)
    private final ConcurrentHashMap<Class<?>, List<Field>> authorizedDataFieldCache = new ConcurrentHashMap<>();

    public AuthorizedDataModelAttributeResolver(ApplicationContext applicationContext, IAuthorizedRequestDataMetadata authorizedRequestDataMetadata //
    ) {
        super(false);
        this.applicationContext = applicationContext;
        this.authorizedRequestDataMetadata = authorizedRequestDataMetadata;
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        if (binder instanceof ExtendedServletRequestDataBinder) {
            ((ExtendedServletRequestDataBinder) binder).bind(servletRequest);
        } else if (binder instanceof ServletRequestDataBinder) {
            ((ServletRequestDataBinder) binder).bind(servletRequest);
        } else if (binder instanceof WebRequestDataBinder) {
            ((WebRequestDataBinder) binder).bind(request);
        } else {
            throw ExceptionUtils.newException(InternalServerException.class, "지원하는 않는 WebDataBinder('%s') 구현 클래스 입니다.", binder.getClass());
        }

        Object target = binder.getTarget();
        if (target == null) {
            return;
        }
        // 데이터 처리
        resolvePojo(target, Collections.newSetFromMap(new IdentityHashMap<>()));
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

    private boolean isSimpleValue(Class<?> type) {
        return BeanUtils.isSimpleValueType(type) //
                || String.class.equals(type) //
                || UUID.class.equals(type) //
        ;
    }

    /**
     * {@link AuthorizedRequestData} 정보와 TODO ( ) 정보를 확인하여, {@link AuthorizedRequestData#handleBean()},
     * {@link AuthorizedRequestData#handleType()}에 해당하는 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 22.		박준홍			최초 작성
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
    private TwoValueObject<String, Integer> resolveAnnotatedContext(Class<?> targetClass, String fieldName, AuthorizedRequestData anno) {
        if (anno != null) {
            return new TwoValueObject<>(anno.handleBean(), anno.handleType());
        } else {

            String handleBean = this.authorizedRequestDataMetadata.getHandleBeanName(targetClass, fieldName);
            if (StringUtils.isNullOrEmptyString(handleBean)) {
                return null;
            }
            int handleType = this.authorizedRequestDataMetadata.getHandleType(targetClass, fieldName);
            if (handleType == AuthorizedRequestData.NO_ASSINGED_HANDLE_TYPE) {
                return null;
            }

            return new TwoValueObject<>(handleBean, handleType);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 18.     박준홍         최초 작성
     * </pre>
     * 
     * @param targetValue
     *            확인할 데이터
     * @param visited
     *            {@link Field}를 스캔할 객체.
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private void resolvePojo(Object targetValue, Set<Object> visited) {
        if (targetValue == null || visited.contains(targetValue)) {
            return;
        }
        visited.add(targetValue);

        Class<?> targetClass = targetValue.getClass();
        List<Field> fields = getProcessableFields(targetClass);
        Object rawValue = null;
        String handleBean = null;
        int handleType = AuthorizedRequestData.NO_ASSINGED_HANDLE_TYPE;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                rawValue = field.get(targetValue);
                if (rawValue == null) {
                    continue;
                }
                TwoValueObject<String, Integer> annotatedValue = resolveAnnotatedContext(targetClass, field.getName(), field.getAnnotation(AuthorizedRequestData.class));
                if (annotatedValue == null) {
                    continue;
                }
                handleBean = annotatedValue.first;
                handleType = annotatedValue.second;
                Object value = resolveRawValue(rawValue, handleBean, handleType, visited);
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
    }

    private Object resolveRawValue(Object rawValue, String handleBean, int handleType, Set<Object> visited) {
        if (rawValue == null) {
            return null;
        }
        Class<?> rawValueClass = rawValue.getClass();

        if (isSimpleValue(rawValueClass)) {
            return restoreValue(applicationContext, handleBean, handleType, rawValue);
        } else if (rawValueClass.isArray()) {
            Object[] valueArr = (Object[]) rawValue;
            int len = Array.getLength(rawValue);
            for (int i = 0; i < len; i++) {
                Object elemValue = Array.get(rawValue, i);
                if (elemValue != null) {
                    // 복호화/평문화 데이터를 받아 기존 데이터를 교체.
                    valueArr[i] = resolveRawValue(elemValue, handleBean, handleType, visited);
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
                    itr.set(resolveRawValue(elemValue, handleBean, handleType, visited));
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
                valueCol.add(resolveRawValue(elem, handleBean, handleType, visited));
            }
        } else if (rawValue instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<Object, Object> valueMap = (Map<Object, Object>) rawValue;
            for (Entry<?, ?> entry : valueMap.entrySet()) {
                Object elemValue = entry.getValue();
                if (elemValue != null) {
                    valueMap.put(entry.getKey(), resolveRawValue(elemValue, handleBean, handleType, visited));
                }
            }
        } else {
            resolvePojo(rawValue, visited);
        }

        return rawValue;
    }

    /**
     * "{@link AuthorizedRequestData} && {@link ModelAttribute}"가 선언된 파라미터만 지원합니다.
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.method.annotation.ModelAttributeMethodProcessor#supportsParameter(org.springframework.core.MethodParameter)
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return super.supportsParameter(parameter);
    }

}
