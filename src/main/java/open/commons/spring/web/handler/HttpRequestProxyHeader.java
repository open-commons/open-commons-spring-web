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
 * Date  : 2025. 7. 18. 오후 12:40:22
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.handler;

import javax.validation.constraints.NotEmpty;

/**
 * Proxy 서버를 통해서 전달되는 실제 클라이언트의 Http 요청 정보.<br>
 * 
 * 서비스 설정에 아래와 같이 추가합니다.
 * 
 * <pre>
 * spring:
 *   # 헤더 처리는 프레임워크에서 하도록 설정
 *   forward-headers-strategy: framework
 *
 * open-commons:
 *   proxy-header:
 *     real-ip: ${실제 클라이언트 IP 헤더이름}
 *     forwarded-for: ${프록시를 거친 모든 클라이언트 IP 목록 헤더이름}
 *     forwarded-proto: ${원 요청의 프로토콜 헤더이름}
 *     forwarded-host: ${클라이언트가 요청한 Host 헤더이름}
 *     forwarded-port: ${클라이언트가 접속한 포트 헤더이름}
 * </pre>
 * 
 * @since 2025. 7. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class HttpRequestProxyHeader {

    /**
     * 실제 클라이언트 IP<br>
     * 예) Nginx의 $remote_addr : Nginx가 받은 요청의 클라이언트 IP
     */
    private String realIp;
    /**
     * 클라이언트가 접속한 포트<br>
     * 예) Nginx의 $remote_port : 클라이언트가 Nginx에 연결한 포트
     */
    private String clientPort;
    /**
     * 프록시를 거친 모든 클라이언트 IP 목록.<br>
     * 예) Nginx의 $proxy_add_x_forwarded_for : 기존 값 뒤에 $remote_addr를 추가
     */
    private String forwardedFor;
    /**
     * 원 요청의 프로토콜 (http 또는 https)<br>
     * 예) Nginx의 $scheme : 현재 요청 스키마 (http, https)
     */
    private String forwardedProto;
    /**
     * 클라이언트가 요청한 Host 헤더<br>
     * 예) Nginx의 $host : 요청한 Host
     */
    private String forwardedHost;
    /**
     * 클라이언트가 요청한 Port 헤더<br>
     * 예) Nginx의 $server_port: 요청한 port
     */
    private String forwardedPort;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
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
     * 2025. 7. 29.		박준홍			최초 작성
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
        return clientPort;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
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
        return forwardedFor;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
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
        return forwardedHost;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
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
        return forwardedPort;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
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
        return forwardedProto;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
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
        return realIp;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param clientPort
     *            the clientPort to set
     *
     * @since 2025. 7. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #clientPort
     */
    public void setClientPort(String clientRealPort) {
        this.clientPort = clientRealPort;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param forwardedFor
     *            the forwardedFor to set
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #forwardedFor
     */
    public void setForwardedFor(@NotEmpty String forwardedFor) {
        this.forwardedFor = forwardedFor.trim();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param forwardedHost
     *            the forwardedHost to set
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #forwardedHost
     */
    public void setForwardedHost(@NotEmpty String forwardedHost) {
        this.forwardedHost = forwardedHost.trim();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param forwardedPort
     *            the forwardedPort to set
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #forwardedPort
     */
    public void setForwardedPort(String forwardedPort) {
        this.forwardedPort = forwardedPort;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param forwardedProto
     *            the forwardedProto to set
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #forwardedProto
     */
    public void setForwardedProto(@NotEmpty String forwardedProto) {
        this.forwardedProto = forwardedProto.trim();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 18.		박준홍			최초 작성
     * </pre>
     *
     * @param realIp
     *            the realIp to set
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #realIp
     */
    public void setRealIp(@NotEmpty String realIP) {
        this.realIp = realIP.trim();
    }

    /**
     *
     * @since 2025. 7. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HttpRequestProxyHeader [realIp=");
        builder.append(realIp);
        builder.append(", clientPort=");
        builder.append(clientPort);
        builder.append(", forwardedFor=");
        builder.append(forwardedFor);
        builder.append(", forwardedProto=");
        builder.append(forwardedProto);
        builder.append(", forwardedHost=");
        builder.append(forwardedHost);
        builder.append(", forwardedPort=");
        builder.append(forwardedPort);
        builder.append("]");
        return builder.toString();
    }

}
