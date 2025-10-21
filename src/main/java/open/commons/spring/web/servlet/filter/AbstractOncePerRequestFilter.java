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
 * Date  : 2025. 8. 19. 오후 5:40:30
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import open.commons.spring.web.autoconfigure.configuration.GlobalServletConfiguration;

/**
 * 
 * @since 2025. 8. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractOncePerRequestFilter extends OncePerRequestFilter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /** {@link Thread} 이름 관리 제외 목록 */
    protected List<AntPathRequest> ignoredUrl = new ArrayList<>();

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
    public AbstractOncePerRequestFilter() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 4.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param ignoredUrl
     *            the ignoredUrl to set
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #ignoredUrl
     */
    @Autowired
    public void setIgnoredUrl(@Qualifier(GlobalServletConfiguration.BEAN_QUALIFIER_PRIMARY_ONCE_PER_REQUEST_SHOULD_NOT_PATTERNS) List<AntPathRequest> ignoredUrl) {
        if (ignoredUrl != null) {
            this.ignoredUrl = ignoredUrl.stream().filter(p -> p.matches(getClass())).collect(Collectors.toList());
        }
    }

    /**
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.filter.OncePerRequestFilter#shouldNotFilter(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return this.ignoredUrl.stream().anyMatch(p -> new AntPathRequestMatcher(p.getPattern(), p.getHttpMethodString(), p.isCaseSensitive()).matches(request));
    }
}
