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
 * Date  : 2025. 8. 19. 오후 4:09:24
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;

import open.commons.core.lang.IThreadLocalContext;
import open.commons.core.lang.ThreadLocalContextService;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration;
import open.commons.spring.web.servlet.filter.header.SharedHeader;

/**
 * 
 * @since 2025. 8. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class RequestHeaderFilter extends AbstractOncePerRequestFilter {

    /** {@link Order}에 사용할 값 */
    public static final int ORDER = RequestThreadNameFilter.ORDER + 1;

    private static final IThreadLocalContext THREAD_SHARED_CONTEXT = ThreadLocalContextService.context(RequestHeaderFilter.class);

    private final List<SharedHeader> sharedHeaders = new ArrayList<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 19.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 8. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public RequestHeaderFilter() {
    }

    /**
     *
     * @since 2025. 8. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String headerName = null;
            String headerValue = null;
            for (SharedHeader hdConfig : this.sharedHeaders) {
                // #1. 헤더 조회
                headerValue = request.getHeader(headerName = hdConfig.header());
                if (StringUtils.isNullOrEmptyString(headerValue)) {
                    continue;
                }
                // #2. 헤더값 검증 후 공유
                if (hdConfig.validator().test(headerName, headerValue)) {
                    THREAD_SHARED_CONTEXT.set(headerName, headerValue);
                    // start - 공유 헤더로 설정한 이후에 추가 동작 지원. : 2025. 11. 7. 오후 2:27:11
                    hdConfig.postAction().accept(headerName, headerValue);
                    // end - 공유 헤더로 설정한 이후에 추가 동작 지원. : 2025. 11. 7. 오후 2:27:11
                } else {
                    logger.warn("'{}'로 사용할 정보({})가 올바르지 않습니다.", headerName, headerValue);
                }
            }
        } finally {
            try {
                filterChain.doFilter(request, response);
            } finally {
                THREAD_SHARED_CONTEXT.clear();
            }
        }
    }

    @Autowired
    public void setSharedHeaders(@Qualifier(GlobalServletConfiguration.BEAN_QUALIFIER_PRIMARY_SHARED_HEADERS) @NotNull @Nonnull List<SharedHeader> headers) {
        this.sharedHeaders.addAll(headers);
    }
}
