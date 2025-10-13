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
 * Date  : 2025. 9. 24. 오후 4:12:10
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.deserialization;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotEmpty;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.utils.BeanUtils;

import com.fasterxml.jackson.databind.JavaType;

/**
 * 컨테이너(배열 / {@link Collection} / {@link Map})로 역직렬화된 자바 객체를 재귀 순회하며 단순 타입( {@link String} / {@link Number} /
 * {@link Boolean} / {@link Character} 및 primitive)을 leaf 로 간주해 IAuthorizedRequestDataHandler.handleObject(handleType,
 * value) 를 적용한다.
 *
 * <li>- Jackson 파서를 재사용/재소모하지 않음
 * <li>- 컨테이너 내부의 중첩 컨테이너(List<List<...>>, Map<String, List<...>> 등) 처리 지원
 * <li>- primitive 배열에 대해 안전한 타입 강제(coerce) 처리
 * <li>- POJO는 이 유틸에서 손대지 않고 그대로 반환 (POJO 필드의 {@link AuthorizedRequestData} 는 Bean/Field 단위 Deserializer가 처리)
 * 
 * @since 2025. 9. 24.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedRequestDataContainerWalker {

    private AuthorizedRequestDataContainerWalker() {
    }

    /**
     * primitive 배열에 핸들링 결과(Object)를 안전하게 써넣기 위한 보정.
     */
    private static Object coercePrimitiveIfNeeded(Class<?> componentType, Object value) {
        if (!componentType.isPrimitive())
            return value;

        if (value == null) {
            if (boolean.class.equals(componentType))
                return false;
            if (char.class.equals(componentType))
                return (char) 0;
            return 0;
        }

        if (boolean.class.equals(componentType)) {
            return (value instanceof Boolean) ? value : Boolean.parseBoolean(String.valueOf(value));
        }
        if (char.class.equals(componentType)) {
            if (value instanceof Character)
                return value;
            String s = String.valueOf(value);
            return s.isEmpty() ? (char) 0 : s.charAt(0);
        }

        Number n = (value instanceof Number) ? (Number) value : parseNumber(String.valueOf(value));
        if (byte.class.equals(componentType))
            return n.byteValue();
        if (short.class.equals(componentType))
            return n.shortValue();
        if (int.class.equals(componentType))
            return n.intValue();
        if (long.class.equals(componentType))
            return n.longValue();
        if (float.class.equals(componentType))
            return n.floatValue();
        if (double.class.equals(componentType))
            return n.doubleValue();
        return value;
    }

    @SuppressWarnings("unchecked")
    private static Collection<Object> instantiateSameCollection(Collection<?> src) {
        try {
            return (Collection<Object>) src.getClass().getDeclaredConstructor().newInstance();
        } catch (Throwable ignore) {
            return new ArrayList<>(src.size());
        }
    }

    private static Number parseNumber(String s) {
        try {
            return (s.indexOf('.') >= 0) ? Double.valueOf(s) : Long.valueOf(s);
        } catch (Exception ignore) {
            return 0;
        }
    }

    /**
     * 컨테이너 타입 정보를 활용해 value 를 재귀적으로 후처리한다.
     *
     * @param rawValue
     *            컨테이너(또는 leaf) 객체
     * @param type
     *            Jackson JavaType (컨테이너/leaf 타입 메타정보)
     * @param handler
     *            평문화/복원 핸들러
     * @param handleType
     *            핸들 타입(전략 식별자)
     * @return 변환된 객체(가능하면 원본 컬렉션/맵은 제자리 갱신, 배열은 새 배열 반환)
     */
    public static Object processRecursively(Object rawValue, JavaType type, IAuthorizedRequestDataHandler handler, @NotEmpty @Nonnull String handleType) {
        AssertUtils2.isFalse("데이터 처리 식별정보는 반드시 설정되어야 합니다. handleType=" + handleType, StringUtils.isNullOrEmptyString(handleType));

        if (rawValue == null || type == null) {
            return processRecursivelyRuntime(rawValue, handler, handleType);
        }

        final Class<?> rawClass = type.getRawClass();

        // leaf: 단순 타입 → 핸들러 적용
        if (BeanUtils.isSimpleValueType(rawClass)) {
            return handler.restoreValue(handleType, rawValue);
        }

        // 배열
        if (type.isArrayType() && rawClass.isArray()) {
            final JavaType elemType = type.getContentType(); // not null for arrays
            final int len = Array.getLength(rawValue);
            final Class<?> componentType = rawClass.getComponentType();

            Object newArr = Array.newInstance(componentType, len);
            for (int i = 0; i < len; i++) {
                Object elem = Array.get(rawValue, i);
                Object nv = processRecursively(elem, elemType, handler, handleType);
                Array.set(newArr, i, coercePrimitiveIfNeeded(componentType, nv));
            }
            return newArr;
        }

        // Collection
        if (type.isCollectionLikeType() && (rawValue instanceof Collection)) {
            final JavaType elemType = type.getContentType(); // may be null in rare cases
            @SuppressWarnings("unchecked")
            Collection<Object> col = (Collection<Object>) rawValue;

            if (rawValue instanceof List) {
                // 제자리 갱신 (ListIterator#set)
                ListIterator<Object> it = ((List<Object>) col).listIterator();
                while (it.hasNext()) {
                    Object v = it.next();
                    Object nv = processRecursively(v, elemType, handler, handleType);
                    it.set(nv);
                }
                return col;
            } else {
                // 동형 컬렉션 생성 시도, 실패시 ArrayList
                Collection<Object> newCol = instantiateSameCollection(col);
                for (Object v : col) {
                    newCol.add(processRecursively(v, elemType, handler, handleType));
                }
                return newCol;
            }
        }

        // Map (값만 처리)
        if (type.isMapLikeType() && (rawValue instanceof Map)) {
            final JavaType valType = type.getContentType(); // value type
            @SuppressWarnings("unchecked")
            Map<Object, Object> map = (Map<Object, Object>) rawValue;
            for (Map.Entry<Object, Object> e : map.entrySet()) {
                Object nv = processRecursively(e.getValue(), valType, handler, handleType);
                e.setValue(nv); // in-place 교체
            }
            return map;
        }

        // 그 외(POJO 등)는 이 유틸에서 건드리지 않음. (POJO 필드는 개별 필드 deserializer에서 처리)
        return rawValue;
    }

    /**
     * type 메타정보가 없을 때(예외 상황 대비) 런타임 타입으로 best-effort 처리.
     * <li>- 단순 타입 leaf 는 핸들러 적용
     * <li>- 배열/Collection/Map 은 런타임 타입으로 재귀 처리
     * <li>- POJO 는 그대로 반환
     */
    private static Object processRecursivelyRuntime(Object value, IAuthorizedRequestDataHandler handler, String handleType) {
        if (value == null)
            return null;

        final Class<?> rawClass = value.getClass();

        if (BeanUtils.isSimpleValueType(rawClass)) {
            return handler.restoreValue(handleType, value);
        }

        if (rawClass.isArray()) {
            int len = Array.getLength(value);
            Class<?> comp = rawClass.getComponentType();
            Object newArr = Array.newInstance(comp, len);
            for (int i = 0; i < len; i++) {
                Object elem = Array.get(value, i);
                Object nv = processRecursivelyRuntime(elem, handler, handleType);
                Array.set(newArr, i, coercePrimitiveIfNeeded(comp, nv));
            }
            return newArr;
        }

        if (value instanceof Collection) {
            @SuppressWarnings("unchecked")
            Collection<Object> col = (Collection<Object>) value;

            if (value instanceof List) {
                ListIterator<Object> it = ((List<Object>) col).listIterator();
                while (it.hasNext()) {
                    Object nv = processRecursivelyRuntime(it.next(), handler, handleType);
                    it.set(nv);
                }
                return col;
            } else {
                Collection<Object> newCol = instantiateSameCollection(col);
                for (Object v : col) {
                    newCol.add(processRecursivelyRuntime(v, handler, handleType));
                }
                return newCol;
            }
        }

        if (value instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<Object, Object> map = (Map<Object, Object>) value;
            for (Map.Entry<Object, Object> e : map.entrySet()) {
                Object nv = processRecursivelyRuntime(e.getValue(), handler, handleType);
                e.setValue(nv);
            }
            return map;
        }

        // POJO 등은 그대로 반환
        return value;
    }

}
