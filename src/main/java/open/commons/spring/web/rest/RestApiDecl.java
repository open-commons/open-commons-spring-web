/*
 *
 * This file is generated under this project, "open-commons-spring-web".
 *
 * Date  : 2020. 8. 27. 오후 7:00:03
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.rest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import open.commons.core.text.NamedTemplate;

/**
 * REST API 정보를 제공하는 클래스.
 * 
 * @since 2020. 8. 21.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class RestApiDecl {

    @NotNull
    private HttpMethod method;
    @NotEmpty
    private String url;
    private LinkedMultiValueMap<String, String> headers;
    private LinkedMultiValueMap<String, Object> body;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @since 2020. 8. 21.
     */
    public RestApiDecl() {
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 8. 27.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the body
     *
     * @since 2020. 8. 27.
     * 
     * @see #body
     */
    public MultiValueMap<String, Object> getBody() {
        return this.body != null ? this.body.deepCopy() : new LinkedMultiValueMap<>();
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     * 
     * @return the headers
     *
     * @since 2020. 8. 21.
     * 
     * @see #headers
     */
    public MultiValueMap<String, String> getHeaders() {
        return this.headers != null ? this.headers.deepCopy() : new LinkedMultiValueMap<>();
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     * 
     * @return the method
     *
     * @since 2020. 8. 21.
     * 
     * @see #method
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     * 
     * @return the url
     *
     * @since 2020. 8. 21.
     * 
     * @see #url
     */
    public String getUrl() {
        return url;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 8. 27.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param body
     *            the body to set
     *
     * @since 2020. 8. 27.
     * 
     * @see #body
     */
    public void setBody(MultiValueMap<String, Object> body) {
        this.body = body != null //
                ? new LinkedMultiValueMap<>(body) //
                : new LinkedMultiValueMap<>();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param headers
     *            the headers to set
     *
     * @since 2020. 8. 21.
     * 
     * @see #headers
     */
    public void setHeaders(MultiValueMap<String, String> headers) {
        this.headers = headers != null//
                ? new LinkedMultiValueMap<>(headers) //
                : new LinkedMultiValueMap<>();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param method
     *            the method to set
     *
     * @since 2020. 8. 21.
     * 
     * @see #method
     */
    public void setMethod(@NotNull @NotEmpty String method) {
        this.method = HttpMethod.valueOf(method.toUpperCase());
    }

    /**
     * API URL. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 21.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param url
     *            the url to set
     *
     * @since 2020. 8. 21.
     * 
     * @see #url
     * @see NamedTemplate
     */
    public void setUrl(@NotNull @NotEmpty String url) {
        this.url = url;
    }

    /**
     * @since 2020. 8. 27.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RestApiDecl [method=");
        builder.append(method);
        builder.append(", url=");
        builder.append(url);
        builder.append(", headers=");
        builder.append(headers);
        builder.append(", body=");
        builder.append(body);
        builder.append("]");
        return builder.toString();
    }

}