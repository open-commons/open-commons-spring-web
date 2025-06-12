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
 * Date  : 2025. 6. 12. 오전 10:43:52
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority.forced;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.servlet.InternalServerException;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ForcedUnintelligibleHandler implements IUnauthorizedFieldHandler {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.beans.authority.forced.ForcedUnintelligibleHandler";

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
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
     * @see open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler#handleObject(int, java.lang.Object)
     */
    @Override
    public Object handleObject(int handle, Object data) {
        if (data == null) {
            return null;
        }

        switch (handle) {
            case ForceUnintelligibleHandleType.BOOLEAN:
                return false;
            case ForceUnintelligibleHandleType.CHARACTER:
                return "".charAt(0);
            case ForceUnintelligibleHandleType.BYTE:
                return Byte.MIN_VALUE;
            case ForceUnintelligibleHandleType.SHORT:
                return Short.MIN_VALUE;
            case ForceUnintelligibleHandleType.DOUBLE:
                return Double.MIN_EXPONENT;
            case ForceUnintelligibleHandleType.FLOAT:
                return Float.MIN_EXPONENT;
            case ForceUnintelligibleHandleType.INTEGER:
                return Integer.MIN_VALUE;
            case ForceUnintelligibleHandleType.LONG:
                return Long.MIN_VALUE;
            case ForceUnintelligibleHandleType.STRING:
                return (String) null;
            case ForceUnintelligibleHandleType.CHAR_SEQUENCE:
                return (String) null;
            default:
                throw ExceptionUtils.newException(InternalServerException.class, "지원하지 않는 처리방식입니다. 입력=%s", handle);
        }
    }
}
