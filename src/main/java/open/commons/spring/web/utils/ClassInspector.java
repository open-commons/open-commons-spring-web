/*
 * Copyright 2025 Park Jun-Hong_(parkjunhong77@gmail.com)
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

/**
 * 
 * @since 2025. 6. 16.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ClassInspector {
    private ClassInspector() {
    }

    /**
     * 주어진 클래스 및 상위 클래스의 모든 {@link Field}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 16.		박준홍			최초 작성
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
        if (clazz == null) {
            return new ArrayList<>();
        }

        List<Field> fields = new ArrayList<>();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            fields.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        }

        return fields;
    }

    /**
     * 주어진 클래스 및 상위 클래스의 모든 {@link Method}를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 16.		박준홍			최초 작성
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
        if (clazz == null) {
            return new ArrayList<>();
        }

        List<Method> fields = new ArrayList<>();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            fields.addAll(Arrays.asList(current.getDeclaredMethods()));
            current = current.getSuperclass();
        }

        return fields;
    }
}
