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
 * Date  : 2025. 6. 12. 오전 10:39:11
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority.builtin;

/**
 * Primitive 데이터 유형에 따라서 강제적으로 처리하기 위한 타입 정의.
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ForcedUnintelligibleHandleType {

    /** 없는 유형 */
    public static final int NONE = 0x00;
    /** boolean, {@link Boolean} */
    public static final int BOOLEAN = NONE + 1;
    /** char, {@link Character} */
    public static final int CHARACTER = BOOLEAN + 1;
    /** byte, {@link Byte} */
    public static final int BYTE = CHARACTER + 1;
    /** short, {@link Short} */
    public static final int SHORT = BYTE + 1;
    /** int, {@link Integer} */
    public static final int INTEGER = SHORT + 1;
    /** long, {@link Long} */
    public static final int LONG = INTEGER + 1;
    /** float, {@link Float} */
    public static final int FLOAT = LONG + 1;
    /** double, {@link Double} */
    public static final int DOUBLE = FLOAT + 1;
    /** {@link String} */
    public static final int STRING = DOUBLE + 1;
    /** {@link CharSequence} */
    public static final int CHAR_SEQUENCE = STRING + 1;

    private ForcedUnintelligibleHandleType() {
    }

    public static int find(Class<?> fieldType) {
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
