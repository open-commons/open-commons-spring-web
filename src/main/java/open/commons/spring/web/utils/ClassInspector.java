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
 * Date  : 2025. 6. 16. 오후 5:40:20
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.servlet.InternalServerException;

/**
 * 
 * @since 2025. 6. 16.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ClassInspector {
    private ClassInspector() {
    }

    private static <T> List<T> getAll(Class<?> clazz, Function<Class<?>, T[]> m) {
        List<T> all = new ArrayList<>();
        for (Class<?> cls = clazz; cls != null && cls != Object.class; cls = cls.getSuperclass()) {
            all.addAll(Arrays.asList(m.apply(cls)));
        }
        return all;
    }

    /**
     * 주어진 클래스 및 상위 클래스의 모든 {@link Field}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 16.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param clazz
     * @return
     *
     * @since 2025. 6. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        return getAll(clazz, Class::getDeclaredFields);
    }

    /**
     * 주어진 클래스 및 상위 클래스의 모든 {@link Method}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 16.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param clazz
     * @return
     *
     * @since 2025. 6. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static List<Method> getAllMethods(Class<?> clazz) {
        return getAll(clazz, Class::getDeclaredMethods);
    }

    /**
     * 이름에 해당하는 {@link Field}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 19.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param clazz
     * @param fieldName
     * @return
     *
     * @since 2025. 6. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see Class#getDeclaredField(String)
     */
    public static Field getDeclaredFieldIfExist(@NotNull Class<?> clazz, @NotEmpty String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        } catch (Exception e) {
            throw ExceptionUtils.newException(InternalServerException.class, e, "clazz=%s, field-name=%s, 오류=%s", clazz, fieldName, e.getMessage());
        }
    }

    /**
     * 이름에 해당하는 {@link Field}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 19.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param clazz
     * @param fieldName
     * @return
     *
     * @since 2025. 6. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see Class#getField(String)
     */
    public static Field getFieldIfExist(@NotNull Class<?> clazz, @NotEmpty String fieldName) {
        try {
            return clazz.getField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        } catch (Exception e) {
            throw ExceptionUtils.newException(InternalServerException.class, e, "clazz=%s, field-name=%s, 오류=%s", clazz, fieldName, e.getMessage());
        }
    }

    /**
     * 이름에 해당하는 {@link Field}가 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 19.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param clazz
     * @param fieldName
     * @return
     *
     * @since 2025. 6. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see Class#getDeclaredField(String)
     */
    public static boolean hasDeclaredField(@NotNull Class<?> clazz, @NotEmpty String fieldName) {
        return getDeclaredFieldIfExist(clazz, fieldName) != null;
    }

    /**
     * 이름에 해당하는 {@link Field}가 있는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 19.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param clazz
     * @param fieldName
     * @return
     *
     * @since 2025. 6. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see Class#getField(String)
     */
    public static boolean hasField(@NotNull Class<?> clazz, @NotEmpty String fieldName) {
        return getFieldIfExist(clazz, fieldName) != null;
    }
}
