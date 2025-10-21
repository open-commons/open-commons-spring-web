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
 * Date  : 2025. 7. 18. 오후 12:40:22
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.handler;

import java.util.HashMap;

/**
 * Proxy 서버를 통해서 전달되는 실제 클라이언트의 Http 요청 정보.<br>
 * 
 * 서비스 설정에 아래와 같이 추가합니다.
 * 
 * <pre>
 * server:
 *   # 헤더 처리는 프레임워크에서 하도록 설정
 *   forward-headers-strategy: framework
 *
 * open-commons:
 *   proxy-header:
 *     real-ip: ${실제 클라이언트 IP 헤더이름}
 *     client-port: ${실제 클라이언토 Port 헤더 이름}
 *     forwarded-for: ${프록시를 거친 모든 클라이언트 IP 목록 헤더이름}
 *     forwarded-proto: ${원 요청의 프로토콜 헤더이름}
 *     forwarded-host: ${클라이언트가 요청한 Host 헤더이름}
 *     forwarded-port: ${클라이언트가 요청한 Port 헤더이름}
 *     ...
 * </pre>
 * 
 * <pre>
 * [개정이력]
 *      날짜    	| 작성자			|	내용
 * ------------------------------------------
 * 2025. 7. 18.     parkjunhong77@gmail.com(jhpark@ymtech.co.kr)    최초 작성
 * 2025. 7. 30.     parkjunhong77@gmail.com(jhpark@ymtech.co.kr)    추후 전달되는 헤더의 확장을 위하여 {@link HashMap}을 상속받음.
 *                                                  * 기본 헤더 목록
 *                                                   - real-ip
 *                                                   - client-port
 *                                                   - forwarded-for
 *                                                   - forwarded-proto
 *                                                   - forwarded-host
 *                                                   - forwarded-port 
 * </pre>
 * 
 * @since 2025. 7. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class HttpRequestProxyHeader extends HashMap<String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * 실제 클라이언트 IP<br>
     * 예) Nginx의 $remote_addr : Nginx가 받은 요청의 클라이언트 IP
     */
    public static final String HEADER_REAL_IP = "real-ip";
    /**
     * 클라이언트가 접속한 포트<br>
     * 예) Nginx의 $remote_port : 클라이언트가 Nginx에 연결한 포트
     */
    public static final String HEADER_CLIENT_PORT = "client-port";
    /**
     * 프록시를 거친 모든 클라이언트 IP 목록.<br>
     * 예) Nginx의 $proxy_add_x_forwarded_for : 기존 값 뒤에 $remote_addr를 추가
     */
    public static final String HEADER_FORWARDED_FOR = "forwarded-for";
    /**
     * 원 요청의 프로토콜 (http 또는 https)<br>
     * 예) Nginx의 $scheme : 현재 요청 스키마 (http, https)
     */
    public static final String HEADER_FORWARDED_PROTO = "forwarded-proto";
    /**
     * 클라이언트가 요청한 Host 헤더<br>
     * 예) Nginx의 $host : 요청한 Host
     */
    public static final String HEADER_FORWARDED_HOST = "forwarded-host";
    /**
     * 클라이언트가 요청한 Port 헤더<br>
     * 예) Nginx의 $server_port: 요청한 port
     */
    public static final String HEADER_FORWARDED_PORT = "forwarded-port";

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public HttpRequestProxyHeader() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the clientPort
     *
     * @since 2025. 7. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #clientPort
     */

    public String getClientPort() {
        return get(HEADER_CLIENT_PORT);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the forwardedFor
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #forwardedFor
     */

    public String getForwardedFor() {
        return get(HEADER_FORWARDED_FOR);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the forwardedHost
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #forwardedHost
     */

    public String getForwardedHost() {
        return get(HEADER_FORWARDED_HOST);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the forwardedPort
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #forwardedPort
     */

    public String getForwardedPort() {
        return get(HEADER_FORWARDED_PORT);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the forwardedProto
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #forwardedProto
     */

    public String getForwardedProto() {
        return get(HEADER_FORWARDED_PROTO);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the realIp
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #realIp
     */

    public String getRealIp() {
        return get(HEADER_REAL_IP);
    }
}
