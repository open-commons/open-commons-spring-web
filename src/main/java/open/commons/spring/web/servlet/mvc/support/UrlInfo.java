/*
 * Copyright 2019 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2019. 6. 28. 오전 10:50:20
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.servlet.mvc.support;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Generatl URL Information.
 * 
 * @since 2019. 6. 28.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class UrlInfo {

    private final String method;

    private final String url;

    private final String urlPattern;

    private final Object variables;

    private final Map<String, String[]> parameters;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자			|	내용
     * ------------------------------------------
     * 2019. 6. 28    박준홍                 최초 작성
     * 2025. 2. 18.	  박준홍 (jhpark@ymtech.co.kr) javax.servlet:servlet-api -> javax.servlet:javax.servlet-api 로 변경함에 따라 {@link HttpServletRequest#getParameterMap()} 반환데이터 타입 변경.
     * </pre>
     * 
     * @since 2019. 6. 28.
     */
    public UrlInfo(String method, String url, String urlPattern, Object variables, Map<String, String[]> parameters) {
        this.method = method;
        this.url = url;
        this.urlPattern = urlPattern;
        this.variables = variables;
        this.parameters = parameters;
    }

    /**
     *
     * @return the method
     *
     * @since 2019. 6. 28.
     * 
     * @see #method
     */
    public String getMethod() {
        return method;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자           |   내용
     * ------------------------------------------
     * 2019. 6. 28    박준홍                 최초 작성
     * 2025. 2. 18.   박준홍 (jhpark@ymtech.co.kr) javax.servlet:servlet-api -> javax.servlet:javax.servlet-api 로 변경함에 따라 {@link HttpServletRequest#getParameterMap()} 반환데이터 타입 변경.
     * </pre>
     * 
     * @return the parameters
     *
     * @since 2019. 6. 28.
     * 
     * @see #parameters
     */
    public Map<String, String[]> getParameters() {
        return parameters;
    }

    /**
     *
     * @return the url
     *
     * @since 2019. 6. 28.
     * 
     * @see #url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @return the urlPattern
     *
     * @since 2019. 6. 28.
     * 
     * @see #urlPattern
     */
    public String getUrlPattern() {
        return urlPattern;
    }

    /**
     *
     * @return the variables
     *
     * @since 2019. 6. 28.
     * 
     * @see #variables
     */
    public Object getVariables() {
        return variables;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UrlInfo [method=");
        builder.append(method);
        builder.append(", url=");
        builder.append(url);
        builder.append(", urlPattern=");
        builder.append(urlPattern);
        builder.append(", variables=");
        builder.append(variables);
        builder.append(", parameters=");
        builder.append(parameters);
        builder.append("]");
        return builder.toString();
    }

}
