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
 * Date  : 2025. 9. 18. 오후 2:46:42
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.resolver;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.authority.AuthorizedData;
import open.commons.spring.web.beans.authority.IAuthorizedDataHandler;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.utils.ClassInspector;

/**
 * 
 * @since 2025. 9. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedDataModelAttributeProcessor {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @NotNull
    private final ApplicationContext applicationContext;

    // 클래스별로 "처리 대상 필드 목록"을 캐싱
    // - @AuthorizedData 가 붙은 필드
    // - 혹은 nested 탐색이 필요한 컨테이너/복합타입 필드(재귀 진입용)
    private final ConcurrentHashMap<Class<?>, List<Field>> authorizedDataFieldCache = new ConcurrentHashMap<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 18.		박준홍			최초 작성
     * </pre>
     * 
     * @param applicationContext
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedDataModelAttributeProcessor(@NotNull @Nonnull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * {@link AuthorizedData}가 설정된 {@link Field}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 18.		박준홍			최초 작성
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
            List<Field> out = new ArrayList<>();
            for (Field f : ClassInspector.getAllFields(c)) {
                if (f.isAnnotationPresent(AuthorizedData.class) //
                        || !isSimpleValue(f.getType())) {
                    out.add(f);
                }
            }
            return out;
        });
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 18.		박준홍			최초 작성
     * </pre>
     * 
     * @param target
     *            확인할 데이터
     * @param visited
     *            {@link Field}를 스캔할 객체.
     * 
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private void handleRecursive(Object target, Set<Object> visited) {
        if (target == null || visited.contains(target)) {
            return;
        }
        visited.add(target);

        Class<?> clazz = target.getClass();
        List<Field> fields = getProcessableFields(clazz);

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object rawValue = field.get(target);
                if (rawValue == null) {
                    continue;
                }

                String handleBean = field.getAnnotation(AuthorizedData.class).handleBean();
                int handleType = field.getAnnotation(AuthorizedData.class).handleType();

                if (isSimpleValue(field.getType())) {
                    IAuthorizedDataHandler handler = null;
                    try {
                        handler = this.applicationContext.getBean(handleBean, IAuthorizedDataHandler.class);
                        Object value = handler.restoreValue(handleType, rawValue);
                        field.set(target, value);
                    } catch (BeansException e) {
                        String errMsg = String
                                .format("'권한 제어가 적용된 파라미터'를 처리하는 도중 오류가 발생하였습니다. field.name=%s, field.raw_value=%s, handle.beanname=%s, handle.type=%s, handle.class=%s, 원인=%s" //
                                        , field.getName(), rawValue, handleBean, handleType, IAuthorizedDataHandler.class.getName(), e.getMessage());
                        logger.error("{}", errMsg, e);

                        throw ExceptionUtils.newException(InternalServerException.class, e, errMsg);
                    }
                } else {
                    // nested / container 타입은 재귀 탐색
                    recurseIntoContainerOrObject(rawValue, visited);
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to handle @AuthorizedData for field: " + field.getName() + " in " + target.getClass().getName(), e);
            }
        }
    }

    private boolean isSimpleValue(Class<?> type) {
        return type.isPrimitive() //
                || String.class.equals(type) //
                || Number.class.isAssignableFrom(type) //
                || Boolean.class.equals(type) //
                || Character.class.equals(type) //
                || BigDecimal.class.equals(type) //
                || BigInteger.class.equals(type) //
                || UUID.class.equals(type) //
                || Date.class.isAssignableFrom(type) //
                || Temporal.class.isAssignableFrom(type) //
                || Enum.class.isAssignableFrom(type);
    }

    /**
     * 데이터를 확인하여 {@link AuthorizedData}가 설정된 정보를 복원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 18.		박준홍			최초 작성
     * </pre>
     * 
     * @param target
     * 
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void process(Object target) {
        if (target == null) {
            return;
        }
        handleRecursive(target, Collections.newSetFromMap(new IdentityHashMap<>()));
    }

    private void recurseIntoContainerOrObject(Object value, Set<Object> visited) {
        if (value == null) {
            return;
        }
        Class<?> type = value.getClass();

        if (type.isArray()) {
            int len = Array.getLength(value);
            for (int i = 0; i < len; i++) {
                handleRecursive(Array.get(value, i), visited);
            }
        } else if (value instanceof Iterable) {
            for (Object elem : (Iterable<?>) value) {
                handleRecursive(elem, visited);
            }
        } else if (value instanceof Map) {
            for (Object v : ((Map<?, ?>) value).values()) {
                handleRecursive(v, visited);
            }
        } else if (!isSimpleValue(type)) {
            handleRecursive(value, visited);
        }
    }
}
