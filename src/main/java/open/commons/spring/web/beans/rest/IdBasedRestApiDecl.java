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
 * Date  : 2025. 7. 3. 오후 5:12:00
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * "문자열 식별정보"를 기반으로 REST API 정보를 구분하는 클래스.<br>
 *
 * application.yml 일부
 * 
 * <pre>
 * backend:
 *   rest-apis:
 *     - 
 *       id: "{REST API 식별정보#1}"
 *       title: "{REST API 설명#1}"
 *       method: "{Http Method}"
 *       path: "URL Path"
 *       # 기본 헤더
 *       headers:
 *         - "Content-Type: application/json"
 *         - "X-Example-Header: example#1"
 *       # 쿼리에 사용되는 파라미터 이름
 *       queries:
 *         - {쿼리 파라미터 이름1}
 *         - {쿼리 파라미터 이름2}
 *         - 
 *       id: "{REST API 식별정보#2}"
 *       title: "{REST API 설명#2}"
 *       method: "{Http Method}"
 *       path: "URL Path"
 *       # 기본 헤더
 *       headers:
 *         - "Content-Type: application/json"
 *         - "X-Example-Header: example#2"
 *       # 쿼리에 사용되는 파라미터 이름
 *       queries:
 *         - {쿼리 파라미터 이름1}
 *         - {쿼리 파라미터 이름2}
 * </pre>
 * 
 * @since 2025. 7. 3.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class IdBasedRestApiDecl {

    /** REST API 식별정보 */
    @NotEmpty
    private String id;
    /** REST API 설명 */
    private String title;
    /** Http Method */
    private HttpMethod method;
    /** REST API Endpoint Path */
    @NotEmpty
    private String path;
    /** headers */
    private MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    /** Request Query 파라미터 */
    private Set<String> queries;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public IdBasedRestApiDecl() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @return the headers
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #headers
     */

    public MultiValueMap<String, String> getHeaders() {
        return headers;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @return the id
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #id
     */

    public String getId() {
        return id;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @return the method
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #method
     */

    public HttpMethod getMethod() {
        return method;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @return the path
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #path
     */

    public String getPath() {
        return path;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @return the queries
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #queries
     */

    public Set<String> getQueries() {
        return this.queries != null ? this.queries : new HashSet<>();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     * 
     * @return the title
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #title
     */

    public String getTitle() {
        return title;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param headers
     *            the headers to set
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #headers
     */
    public void setHeaders(List<String> headers) {
        for (String header : headers) {
            int sepIdx = header.indexOf(':');
            if (sepIdx == -1) {
                throw new IllegalArgumentException("Invalid header format: " + header);
            }

            String key = header.substring(0, sepIdx).trim();
            String value = header.substring(sepIdx + 1).trim();

            this.headers.add(key, value);
        }
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param id
     *            the id to set
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param method
     *            the method to set
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #method
     */
    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param path
     *            the path to set
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param queries
     *            the queries to set
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #queries
     */
    public void setQueries(Set<String> queries) {
        this.queries = queries;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 3.		박준홍			최초 작성
     * </pre>
     *
     * @param title
     *            the title to set
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @since 2025. 7. 3.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("IdBasedRestApiDecl [id=");
        builder.append(id);
        builder.append(", title=");
        builder.append(title);
        builder.append(", method=");
        builder.append(method);
        builder.append(", path=");
        builder.append(path);
        builder.append(", headers=");
        builder.append(headers);
        builder.append(", queries=");
        builder.append(queries);
        builder.append("]");
        return builder.toString();
    }

}
