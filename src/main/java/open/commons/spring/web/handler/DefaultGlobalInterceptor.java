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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import open.commons.core.lang.IThreadLocalContext;
import open.commons.core.lang.ThreadLocalContextService;
import open.commons.spring.web.servlet.filter.RequestThreadNameFilter;

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
 * 2025. 8. 4.      박준홍(jhpark@ymtech.co.kr)    URL 기반 {@link Thread} 이름 설정 기능이 {@link RequestThreadNameFilter}로 이관되면서 기존 기능 제거됨. 추후 제거될 수 있음.
 * </pre>
 * 
 * @since 2020. 1. 17.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DefaultGlobalInterceptor implements AsyncHandlerInterceptor {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.handler.DefaultGlobalInterceptor";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @SuppressWarnings("unused")
    private final IThreadLocalContext threadLocalContext = ThreadLocalContextService.context(DefaultGlobalInterceptor.class);

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
        // 추후 상위 메소드의 구현이 변경됨에 따른 영향을 최소화하기 위해서 가장 마지막에 위치시킴.
        AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

}
