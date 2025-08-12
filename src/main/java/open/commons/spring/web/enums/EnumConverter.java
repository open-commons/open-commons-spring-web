/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * This file is generated under this project, "open-commons-spring5".
 *
 * Date  : 2019. 6. 3. 오후 5:26:16
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.enums;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import open.commons.spring.web.annotation.RequestValueConverter;
import open.commons.spring.web.annotation.RequestValueSupported;

/**
 * {@link RequestValueSupported}, {@link RequestValueConverter} 가 적용된 {@link Enum}를 위한 {@link Converter} 클래스.
 * 
 * @since 2019. 6. 3.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@SuppressWarnings("rawtypes")
public class EnumConverter<E extends Enum> implements Converter<String, E> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private Class<E> enumType;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 3.      박준홍         최초 작성
     * </pre>
     *
     * @since 2019. 6. 3.
     * @version
     */
    public EnumConverter(Class<E> enumType) {
        this.enumType = enumType;
    }

    /**
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public E convert(String source) {

        logger.trace("Try to convert '{}' to '{}'", source, this.enumType.toString());

        List<Method> methods = getAnnotatedMethods(this.enumType, RequestValueConverter.class).stream() //
                .filter(m -> Modifier.isStatic(m.getModifiers())) // filtering
                .collect(Collectors.toList());

        logger.trace("Found !!! methods: '{}'", methods);

        RequestValueConverter anno = null;
        for (Method m : methods) {
            try {
                anno = m.getAnnotation(RequestValueConverter.class);
                if (anno.hasIgnoreCase()) {
                    return (E) m.invoke(null, source, true);
                } else {
                    return (E) m.invoke(null, source);
                }
            } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            }
        }

        throw new UnsupportedOperationException(String.join("", "There is not a converter (", Converter.class.toString(), ") for ", this.enumType.toString()));
    }

    private <T extends Annotation> List<Method> getAnnotatedMethods(Class<?> typeClass, Class<T> annotationClass) {
        ArrayList<Method> methods = new ArrayList<>();

        Arrays.stream(typeClass.getDeclaredMethods()) // create methods stream
                .forEach(m -> {
                    boolean accessible = false;
                    try {
                        accessible = m.isAccessible();

                        if (m.isAnnotationPresent(annotationClass)) {
                            methods.add(m);
                        }
                    } catch (Throwable ignored) {
                        // ignored
                    } finally {
                        m.setAccessible(accessible);
                    }
                });

        return methods;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 3.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 6. 3.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public String getType() {
        return this.enumType != null ? this.enumType.getName() : null;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer builder = new StringBuffer();
        builder.append("EnumConverter [enumType=");
        builder.append(enumType);
        builder.append("]");
        return builder.toString();
    }

}
