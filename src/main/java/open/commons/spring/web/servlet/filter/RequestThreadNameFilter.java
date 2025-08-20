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
 * Date  : 2025. 8. 4. 오후 2:51:36
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import open.commons.core.lang.IThreadLocalContext;
import open.commons.core.lang.ThreadLocalContextService;
import open.commons.core.utils.ThreadUtils;
import open.commons.spring.web.handler.HttpRequestProxyHeader;
import open.commons.spring.web.handler.ProxyHeaderUtil;

/**
 * 
 * @since 2025. 8. 4.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class RequestThreadNameFilter extends AbstractOncePerRequestFilter {
    /** {@link Order}에 사용할 값 */
    public static final int ORDER = Ordered.HIGHEST_PRECEDENCE;

    public static final String THREAD_NAME_DEFAULT = "open.commons.spring.web.servlet.filter.RequestThreadNameFilter#THREAD_NAME_DEFAULT";
    public static final String THREAD_NAME_INTERCEPTED_URL = "open.commons.spring.web.servlet.filter.RequestThreadNameFilter#THREAD_NAME_INTERCEPTED_URL";

    private static final IThreadLocalContext THREAD_LOCAL_CONTEXT = ThreadLocalContextService.context(RequestThreadNameFilter.class);

    /** proxy 서버를 이용하여 제공되는 서비스인 경우 HTTP 요청 정보를 forwardig 하기 위한 헤더 */
    private HttpRequestProxyHeader proxyHeader;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 4.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public RequestThreadNameFilter() {
    }

    /**
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // #1. Servlet 경로 (Context Path 제외)
        String servletPath = request.getServletPath();
        // #2. remote host, remote port 정보 추가
        String reqUri = new StringBuffer(request.getMethod()) //
                .append(' ') //
                .append(servletPath) //
                .append(' ') //
                .append(ProxyHeaderUtil.getClientRealIP(request, this.proxyHeader)) //
                .append(':') //
                .append(ProxyHeaderUtil.getClientRealPort(request, this.proxyHeader)) //
                .toString();

        final String threadName = ThreadUtils.setThreadName(reqUri);
        THREAD_LOCAL_CONTEXT.set(THREAD_NAME_DEFAULT, threadName);
        THREAD_LOCAL_CONTEXT.set(THREAD_NAME_INTERCEPTED_URL, reqUri);
        MDC.put(THREAD_NAME_INTERCEPTED_URL, reqUri);

        try {
            filterChain.doFilter(request, response);
        } finally {
            String otn = (String) THREAD_LOCAL_CONTEXT.get(THREAD_NAME_DEFAULT);

            if (otn != null) {
                ThreadUtils.setThreadName(otn);
                THREAD_LOCAL_CONTEXT.clear();
            }

            MDC.clear();
        }
    }

    @Autowired
    public void setProxyHeader(HttpRequestProxyHeader proxyHeader) {
        this.proxyHeader = proxyHeader;
    }
}
