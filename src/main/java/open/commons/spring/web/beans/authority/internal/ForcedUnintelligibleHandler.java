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
 * Date  : 2025. 6. 12. 오전 10:43:52
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority.internal;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotEmpty;

import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ForcedUnintelligibleHandler implements IUnauthorizedFieldHandler {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler";

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ForcedUnintelligibleHandler() {
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler#handleObject(String, java.lang.Object)
     */
    @Override
    public Object handleObject(@NotEmpty @Nonnull String handle, Object data) {
        if (data == null) {
            return null;
        }

        switch (handle) {
            case ForcedUnintelligibleHandleType.BOOLEAN:
                return false;
            case ForcedUnintelligibleHandleType.CHARACTER:
                return null;
            case ForcedUnintelligibleHandleType.BYTE:
                return Byte.MIN_VALUE;
            case ForcedUnintelligibleHandleType.SHORT:
                return Short.MIN_VALUE;
            case ForcedUnintelligibleHandleType.DOUBLE:
                return Double.MIN_EXPONENT;
            case ForcedUnintelligibleHandleType.FLOAT:
                return Float.MIN_EXPONENT;
            case ForcedUnintelligibleHandleType.INTEGER:
                return Integer.MIN_VALUE;
            case ForcedUnintelligibleHandleType.LONG:
                return Long.MIN_VALUE;
            case ForcedUnintelligibleHandleType.STRING:
                return (String) null;
            case ForcedUnintelligibleHandleType.CHAR_SEQUENCE:
                return (String) null;
            default:
                return null;
            // throw ExceptionUtils.newException(InternalServerException.class, "지원하지 않는 처리방식입니다. 입력=%s", handle);
        }
    }

    public static class ForcedUnintelligibleHandleType {

        /** 없는 유형 */
        public static final String NONE = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.NONE";
        /** boolean, {@link Boolean} */
        public static final String BOOLEAN = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.BOONEAN";
        /** char, {@link Character} */
        public static final String CHARACTER = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.CHARACTER";
        /** byte, {@link Byte} */
        public static final String BYTE = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.BYTE";
        /** short, {@link Short} */
        public static final String SHORT = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.SHORT";
        /** int, {@link Integer} */
        public static final String INTEGER = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.INTEGER";
        /** long, {@link Long} */
        public static final String LONG = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.LONG";
        /** float, {@link Float} */
        public static final String FLOAT = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.FLOAT";
        /** double, {@link Double} */
        public static final String DOUBLE = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.DOUBLE";
        /** {@link String} */
        public static final String STRING = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.STRING";
        /** {@link CharSequence} */
        public static final String CHAR_SEQUENCE = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType.CHAR_SEQUENCE";

        private ForcedUnintelligibleHandleType() {
        }

        public static String find(Class<?> fieldType) {
            if (boolean.class.equals(fieldType) || Boolean.class.equals(fieldType)) {
                return BOOLEAN;
            } else if (char.class.equals(fieldType) || Character.class.equals(fieldType)) {
                return CHARACTER;
            } else if (byte.class.equals(fieldType) || Byte.class.equals(fieldType)) {
                return BYTE;
            } else if (short.class.equals(fieldType) || Short.class.equals(fieldType)) {
                return SHORT;
            } else if (int.class.equals(fieldType) || Integer.class.equals(fieldType)) {
                return INTEGER;
            } else if (long.class.equals(fieldType) || Long.class.equals(fieldType)) {
                return LONG;
            } else if (float.class.equals(fieldType) || Float.class.equals(fieldType)) {
                return FLOAT;
            } else if (double.class.equals(fieldType) || Double.class.equals(fieldType)) {
                return DOUBLE;
            } else if (String.class.equals(fieldType)) {
                return STRING;
            } else if (CharSequence.class.equals(fieldType)) {
                return CHAR_SEQUENCE;
            } else {
                return NONE;
            }
        }
    }
}
