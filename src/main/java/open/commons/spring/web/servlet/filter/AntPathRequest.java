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
 * Date  : 2025. 8. 4. 오후 4:48:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet.filter;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpMethod;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.servlet.InvalidAntPathUrlPatternException;
import open.commons.spring.web.utils.PathUtils;

/**
 * HTTP 요청 정보를 "Ant Path" 기반으로 제공하는 클래스.
 * 
 * @since 2025. 8. 4.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AntPathRequest {

    /** URL 패턴 */
    @NotNull
    @Nonnull
    private String pattern;
    /** HTTP 요청 메소드 */
    private HttpMethod httpMethod;
    /** URL 패턴 대/소문자 구분 여부 */
    private boolean caseSensitive = false;

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
    public AntPathRequest() {
    }

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
     * @param pattern
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AntPathRequest(@NotNull String pattern) {
        this(pattern, null, false);
    }

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
     * @param pattern
     * @param httpMethod
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AntPathRequest(@NotNull String pattern, HttpMethod httpMethod) {
        this(pattern, httpMethod, false);
    }

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
     * @param pattern
     * @param httpMethod
     * @param caseSensitive
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AntPathRequest(@NotNull String pattern, HttpMethod httpMethod, boolean caseSensitive) {
        this.pattern = pattern;
        this.httpMethod = httpMethod;
        this.caseSensitive = caseSensitive;
    }

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
     * @return the httpMethod
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #httpMethod
     */
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public String getHttpMethodString() {
        return this.httpMethod != null ? this.httpMethod.toString() : null;
    }

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
     * @return the pattern
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #pattern
     */
    @NotNull
    @Nonnull
    public String getPattern() {
        if (this.pattern == null) {
            throw ExceptionUtils.newException(IllegalStateException.class, "'pattern'값이 설정이 되지 않았습니다. this=%s", this);
        }
        return this.pattern;
    }

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
     * @return the caseSensitive
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #caseSensitive
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

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
     * @param caseSensitive
     *            the caseSensitive to set
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #caseSensitive
     */
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

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
     * @param httpMethod
     *            the httpMethod to set
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #httpMethod
     */
    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

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
     * @param pattern
     *            the pattern to set
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #pattern
     */
    public void setPattern(@NotNull @Nonnull String pattern) {
        if (!PathUtils.isValidAntPath(pattern)) {
            throw ExceptionUtils.newException(InvalidAntPathUrlPatternException.class, "exclude.invalid=%s", pattern);
        }
        this.pattern = pattern;
    }

    /**
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AntPathRequest [pattern=");
        builder.append(pattern);
        builder.append(", httpMethod=");
        builder.append(httpMethod);
        builder.append(", caseSensitive=");
        builder.append(caseSensitive);
        builder.append("]");
        return builder.toString();
    }

}
