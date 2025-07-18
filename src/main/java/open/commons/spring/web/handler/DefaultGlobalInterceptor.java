/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2020. 1. 17. 오후 1:19:18
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import open.commons.core.utils.ThreadUtils;

/**
 * Http 요청 정보를 {@link Thread} 이름으로 적용하고 응답완료시 기존 {@link Thread}이름으로 반환하는 기능을 제공.<br>
 * 
 * 
 * 
 * <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜      | 작성자		         	   |	내용
 * ------------------------------------------
 * 2020. 1. 17.     박준홍                         최초 작성
 * 2025. 6. 23.     박준홍(jhpark@ymtech.co.kr)    {@link #postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)}을 제거하고 {@link #afterCompletion(HttpServletRequest, HttpServletResponse, Object, Exception)}로 변경. 
 * </pre>
 * 
 * @since 2020. 1. 17.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DefaultGlobalInterceptor implements AsyncHandlerInterceptor {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.handler.DefaultGlobalInterceptor";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private HttpRequestProxyHeader proxyHeader;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 1. 17.		박준홍			최초 작성
     * </pre>
     *
     * @since 2020. 1. 17.
     * @version
     */
    public DefaultGlobalInterceptor() {
    }

    /**
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        String otn = ThreadContext.get(BEAN_QUALIFIER);

        if (otn != null) {
            String reqInfo = Thread.currentThread().getName();
            logger.trace("[Restore thread-name] {} -> {}", reqInfo, otn);

            ThreadUtils.setThreadName(otn);
            ThreadContext.clearAll();
        }
    }

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String reqUri = request.getRequestURI();
        reqUri = new StringBuffer(request.getMethod()) //
                .append(' ') //
                .append(request.getRequestURI()) //
                .append(' ') //
                .append(ProxyHeaderUtil.getClientIP(request, this.proxyHeader)) //
                .append(':') //
                .append(ProxyHeaderUtil.getClientPort(request, this.proxyHeader)) //
                .toString();

        String threadName = ThreadContext.get(BEAN_QUALIFIER);
        if (threadName == null) {
            threadName = ThreadUtils.setThreadName(reqUri);
            ThreadContext.put(BEAN_QUALIFIER, threadName);

            logger.trace("[Change thread-name] {} -> {}.", threadName, reqUri);
        }

        return true;
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
     * @param proxyHeader
     *            the proxyHeader to set
     *
     * @since 2025. 7. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #proxyHeader
     */
    @Autowired
    public void setProxyHeader(HttpRequestProxyHeader proxyHeader) {
        this.proxyHeader = proxyHeader;
    }

}
