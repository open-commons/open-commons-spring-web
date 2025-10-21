/*
 *
 * This file is generated under this project, "open-commons-spring-web".
 *
 * Date  : 2020. 8. 27. 오후 7:01:11
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.rest;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 
 * @since 2020. 8. 27.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class RestApiServer {

    /** 통신 프로포콜 */
    @NotEmpty
    private String scheme;
    /** 서버 IP */
    @NotEmpty
    private String host;
    /** 서버 포트 */
    @Min(1)
    @Max(65535)
    private int port;
    /** REST API Context */
    private String context;
    /** REST API 목록 */
    @Valid
    private Map<String, RestApiDecl> apiList;

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
    public RestApiServer() {
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
     * @return the apiList
     *
     * @since 2020. 8. 21.
     * 
     * @see #apiList
     */
    public Map<String, RestApiDecl> getApiList() {
        return apiList;
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
     * @return the context
     *
     * @since 2020. 8. 21.
     * 
     * @see #context
     */
    public String getContext() {
        return context;
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
     * @return the host
     *
     * @since 2020. 8. 21.
     * 
     * @see #host
     */
    public String getHost() {
        return host;
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
     * @return the port
     *
     * @since 2020. 8. 21.
     * 
     * @see #port
     */
    public int getPort() {
        return port;
    }

    /**
     * API 요청 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 25.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param name
     *            API 이름
     * @return
     *
     * @since 2020. 8. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public RestApiDecl getRestApiDecl(String name) {
        return this.apiList.get(name);
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
     * @return the scheme
     *
     * @since 2020. 8. 21.
     * 
     * @see #scheme
     */
    public String getScheme() {
        return scheme;
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
     * @param apiList
     *            the apiList to set
     *
     * @since 2020. 8. 21.
     * 
     * @see #apiList
     */
    public void setApiList(@Valid Map<String, RestApiDecl> apiList) {
        this.apiList = apiList;
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
     * @param context
     *            the context to set
     *
     * @since 2020. 8. 21.
     * 
     * @see #context
     */
    public void setContext(String context) {
        this.context = context;
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
     * @param host
     *            the host to set
     *
     * @since 2020. 8. 21.
     * 
     * @see #host
     */
    public void setHost(@NotEmpty String host) {
        this.host = host;
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
     * @param port
     *            the port to set
     *
     * @since 2020. 8. 21.
     * 
     * @see #port
     */
    public void setPort(@Min(1) @Max(65535) int port) {
        this.port = port;
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
     * @param scheme
     *            the scheme to set
     *
     * @since 2020. 8. 21.
     * 
     * @see #scheme
     */
    public void setScheme(@NotEmpty String scheme) {
        this.scheme = scheme;
    }

    /**
     * @since 2020. 8. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DataServerConfig [scheme=");
        builder.append(scheme);
        builder.append(", host=");
        builder.append(host);
        builder.append(", port=");
        builder.append(port);
        builder.append(", context=");
        builder.append(context);
        builder.append(", apiList=");
        builder.append(apiList);
        builder.append("]");
        return builder.toString();
    }

}
