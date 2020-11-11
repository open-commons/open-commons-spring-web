/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import open.commons.spring.web.annotation.CustomInterceptor;
import open.commons.utils.ThreadUtils;

/**
 * 
 * @since 2020. 1. 17.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
@CustomInterceptor
public class DefaultGlobalInterceptor implements AsyncHandlerInterceptor {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.handler.DefaultGlobalInterceptor";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

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
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String otn = ThreadContext.get(BEAN_QUALIFIER);

        if (otn != null) {
            String reqInfo = Thread.currentThread().getName();
            logger.trace("[Restore thread-name] {} -> {}", reqInfo, otn);

            ThreadUtils.setThreadName(otn);
            ThreadContext.clearAll();
        }
    }

    /**
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String reqUri = request.getRequestURI();
        reqUri = new StringBuffer(request.getMethod()) //
                .append(' ') //
                .append(request.getRequestURI()) //
                .append(' ') //
                .append(request.getRemoteAddr()) //
                .append(':') //
                .append(String.valueOf(request.getRemotePort())) //
                .toString();

        String threadName = ThreadContext.get(BEAN_QUALIFIER);
        if (threadName == null) {
            threadName = ThreadUtils.setThreadName(reqUri);
            ThreadContext.put(BEAN_QUALIFIER, threadName);

            logger.trace("[Change thread-name] {} -> {}.", threadName, reqUri);
        }

        return true;
    }

}
