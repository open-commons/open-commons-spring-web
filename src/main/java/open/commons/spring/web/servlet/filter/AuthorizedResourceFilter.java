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
 * Date  : 2025. 6. 24. 오후 4:00:14
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.Result;
import open.commons.spring.web.beans.authority.IAuthorizedResourceAuthenticationPause;
import open.commons.spring.web.thread.AuthorizedResourceContext;

/**
 * 
 * @since 2025. 6. 24.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourceFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(AuthorizedResourceFilter.class);

    private final IAuthorizedResourceAuthenticationPause auth;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
     * </pre>
     * 
     * @param auth
     *
     * @since 2025. 6. 24.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedResourceFilter(IAuthorizedResourceAuthenticationPause auth) {
        this.auth = auth;
    }

    /**
     *
     * @since 2025. 6. 24.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     *      javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            // #1. 요청 헤더 확인
            // #1-1. ServletRequest -> HttpServletRequest 로 캐스팅
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            // #1-2. 헤더 조회
            String headerDisableAuthorizedResources = httpRequest.getHeader(AuthorizedResourceContext.DISABLE_AUTHORIZED_RESOURCES);
            if (headerDisableAuthorizedResources != null && "true".equalsIgnoreCase(headerDisableAuthorizedResources)) {
                if (this.auth != null) {
                    Result<Boolean> resultAuth = auth.pause();
                    if (resultAuth.isError() || resultAuth.getData() == null) {
                        logger.warn("'{}' 헤더를 검증하는 서비스 결과에 오류가 포함되어 있습니다. => {}", AuthorizedResourceContext.DISABLE_AUTHORIZED_RESOURCES, resultAuth.getMessage());
                    } else if (resultAuth.getData()) {
                        AuthorizedResourceContext.setDisableAuthentication();
                    }
                } else {
                    logger.warn("요청 헤더에 '{}'이 포함되어 있으나, 이를 검증하는 서비스({})가 등록되지 않았습니다.", AuthorizedResourceContext.DISABLE_AUTHORIZED_RESOURCES,
                            IAuthorizedResourceAuthenticationPause.class.getName());
                }
            }
        } finally {
            chain.doFilter(request, response);
            AuthorizedResourceContext.clear();
        }
    }
}
