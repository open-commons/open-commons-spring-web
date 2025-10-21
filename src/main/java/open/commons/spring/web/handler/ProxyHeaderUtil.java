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
 * Date  : 2025. 7. 18. 오후 1:02:47
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.handler;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import open.commons.core.utils.StringUtils;

/**
 * {@link HttpServletRequest}와 {@link HttpRequestProxyHeader}를 이용하여 실제 클라이언트의 연결정보를 제공하는 클래스.
 * 
 * @since 2025. 7. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ProxyHeaderUtil {
    private ProxyHeaderUtil() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param value
     *            {@link HttpServletRequest}에서 값을 제공하는 함수
     * @param header
     *            {@link HttpRequestProxyHeader} 설정값을 이용하여 {@link HttpServletRequest}의 헤더의 값을 제공하는 함수.
     * @param header
     *            {@link HttpRequestProxyHeader} 설정값
     * @return
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private static String get(Supplier<String> value, Function<String, String> header, String headerName) {

        if (StringUtils.isNullOrEmptyString(headerName)) {
            return value.get();
        } else {
            String headerValue = header.apply(headerName.trim());
            if (StringUtils.isNullOrEmptyString(headerValue)) {
                return value.get();
            } else {
                return headerValue;
            }
        }
    }

    /**
     * Http Reqeust 클라이언트의 IP를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param request
     * @param header
     * @return
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String getClientRealIP(@NotNull HttpServletRequest request, HttpRequestProxyHeader header) {
        if (header == null) {
            return request.getRemoteAddr();
        } else {
            return get(request::getRemoteAddr, h -> request.getHeader(h), header.getRealIp());
        }
    }

    /**
     * Http Reqeust 클라이언트의 Port를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 7. 18.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param request
     * @param header
     * @return
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String getClientRealPort(@NotNull HttpServletRequest request, HttpRequestProxyHeader header) {
        if (header == null) {
            return String.valueOf(request.getRemotePort());
        } else {
            return get(() -> String.valueOf(request.getRemotePort()), h -> request.getHeader(h), String.valueOf(header.getForwardedPort()));
        }
    }

    /**
     * 서비스의 Host 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 7. 18.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param request
     * @param header
     * @return
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String getRequestedHost(@NotNull HttpServletRequest request, HttpRequestProxyHeader header) {
        if (header == null) {
            return request.getHeader("Host");
        } else {
            return get(() -> request.getHeader("Host"), h -> request.getHeader(h), header.getForwardedHost());
        }
    }

    /**
     * 서비스 프로토콜(Scheme)를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 7. 18.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param request
     * @param header
     * @return
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String getRequestedScheme(@NotNull HttpServletRequest request, HttpRequestProxyHeader header) {
        if (header == null) {
            return request.getScheme();
        } else {
            return get(request::getScheme, h -> request.getHeader(h), header.getForwardedProto());
        }
    }
}
