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
 * Date  : 2019. 6. 28. 오전 10:48:36
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.utils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import open.commons.core.collection.FIFOMap;
import open.commons.core.net.HttpStatusCode;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.servlet.mvc.support.UrlInfo;

/**
 * 
 * @since 2019. 6. 28.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class WebUtils {

    private WebUtils() {
    }

    /**
     * 요청 정보와 예외발생 정보를 이용하여 응답 메시지 를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param request
     * @param ex
     * @param status
     * @return
     *
     * @since 2019. 6. 28.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static FIFOMap<String, Object> createEntity(WebRequest request, Exception ex, HttpStatus status) {

        FIFOMap<String, Object> entity = new FIFOMap<>();

        entity.put("timestamp", System.currentTimeMillis());
        entity.put("status", String.join("/", status.toString(), status.getReasonPhrase()));
        entity.put("session", request.getSessionId());

        // Set a URI
        String uri = request.getDescription(false);
        if (uri.contains("=")) {
            uri = StringUtils.substringAfter(uri, "=");
        }
        entity.put("uri", uri);

        // Set HEADERs
        TreeMap<String, Object> headers = new TreeMap<>();
        Iterator<String> headerNames = request.getHeaderNames();
        String headerName = null;
        while (headerNames.hasNext()) {
            headers.put(headerName = headerNames.next(), request.getHeaderValues(headerName));
        }
        entity.put("headers", headers);

        // Set REQUEST PARAMETERs
        entity.put("parameters", request.getParameterMap());

        // Set EXCEPTIONS
        TreeMap<String, Object> exception = new TreeMap<>();
        exception.put("type", ex.getClass().getName());
        exception.put("cause", ex.getMessage());
        entity.put("exception", exception);

        return entity;
    }

    /**
     * 예외처리 응답 정보를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 8. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param view
     * @param status
     * @param ex
     * @param request
     *
     * @since 2018. 8. 22.
     */
    public static void createThrowableResponse(ModelAndView view, HttpStatus status, Throwable ex, HttpServletRequest request) {

        view.setStatus(status);

        HttpStatusCode httpStatusCode = HttpStatusCode.code(status.value());
        view.addObject("code", httpStatusCode.getStatusCode());
        view.addObject("status", httpStatusCode.getStatus());
        view.addObject("desc", httpStatusCode.getDesc());
        view.addObject("cause", ex.getMessage());

        view.addObject("exception", ex.getClass());
        view.addObject("timestamp", System.currentTimeMillis());

        //
        view.addObject("url", new UrlInfo(request.getMethod() //
                , (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE) //
                , (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE) //
                        + (request.getQueryString() != null ? "?" + request.getQueryString() : "") //
                , request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) //
                , request.getParameterMap()));
    }

    /**
     * Base64로 인코딩된 문자열을 디코딩하여 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 8. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param base64EncodeString
     *            Base64로 인코딩된 문자열
     * @return
     *
     * @since 2018. 8. 22.
     * @see Base64Utils#decodeFromUrlSafeString(String)
     */
    public static final String decodeFromUrlSafeString(String base64EncodeString) {
        return new String(Base64Utils.decodeFromUrlSafeString(base64EncodeString));
    }

    /**
     * Base64로 인코딩한 문자열을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 8. 22.     박준홍         최초 작성
     * </pre>
     *
     * @param plainString
     * @return
     *
     * @since 2018. 8. 22.
     * 
     * @see Base64Utils#encodeToUrlSafeString(byte[])
     */
    public static final String encodeToUrlSafeString(String plainString) {
        return Base64Utils.encodeToUrlSafeString(plainString.getBytes());
    }

    /**
     * Parameter 문자열의 인코딩을 변환하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param parameter
     *            파라미터 값
     * @param inputCharset
     *            파라미터 생성시 적용된 {@link Charset}
     * @param outputCharset
     *            변환에 사용될 {@link Charset}
     * @return
     *
     * @since 2018. 9. 27.
     */
    public static final String getParameter(String parameter, Charset inputCharset, Charset outputCharset) {

        if (parameter == null) {
            return null;
        }

        return new String(parameter.getBytes(inputCharset), outputCharset);
    }

    /**
     * Parameter 문자열의 인코딩을 변환하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param parameter
     *            파라미터 값
     * @param inputCharset
     *            파라미터 생성시 적용된 {@link Charset}
     * @param outputCharset
     *            변환에 사용될 {@link Charset}
     * @return
     *
     * @since 2018. 9. 27.
     */
    public static final String getParameter(String parameter, String inputCharset, String outputCharset) {
        return getParameter(parameter, Charset.forName(inputCharset), Charset.forName(outputCharset));
    }

    /**
     * Parameter 문자열의 인코딩을 변환하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param parameters
     *            파라미터 값
     * @param inputCharset
     *            파라미터 생성시 적용된 {@link Charset}
     * @param outputCharset
     *            변환에 사용될 {@link Charset}
     * @return
     *
     * @since 2018. 9. 27.
     */
    public static final Map<String, String> getParameters(Map<String, String> parameters, Charset inputCharset, Charset outputCharset) {

        if (parameters == null) {
            return null;
        }

        HashMap<String, String> converted = new HashMap<>();

        for (Entry<String, String> entry : parameters.entrySet()) {
            converted.put(entry.getKey(), new String(entry.getValue().getBytes(inputCharset), outputCharset));
        }

        return converted;
    }

    /**
     * Parameter 문자열의 인코딩을 변환하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2018. 9. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param parameters
     *            파라미터 값
     * @param inputCharset
     *            파라미터 생성시 적용된 {@link Charset}
     * @param outputCharset
     *            변환에 사용될 {@link Charset}
     * @return
     *
     * @since 2018. 9. 27.
     */

    public static final Map<String, String> getParameters(Map<String, String> parameters, String inputCharset, String outputCharset) {
        return getParameters(parameters, Charset.forName(inputCharset), Charset.forName(outputCharset));
    }

    /**
     * Request 요청 URL 정보를 제공한다. <br>
     * 
     * <pre>
     * Modification 
     * 
     *   수정일              수정자              수정내용
     *  ----------   ---------   -------------------------------
     *  2018. 8. 2.  Park J.H.    최초생성
     * </pre>
     *
     * @param request
     * @return
     *
     * @since 2018. 8. 2.
     */
    public static final String getUrlInfo(HttpServletRequest request) {

        StringBuffer sb = new StringBuffer();

        sb.append("Session: ");
        sb.append(request.getSession().getId());
        sb.append(", ");
        sb.append("Remote: ");
        sb.append(request.getRemoteAddr());
        sb.append(" (");
        sb.append(request.getRemoteHost());
        sb.append("), URL-Pattern: ");
        sb.append(request.getMethod());
        sb.append(" | ");
        sb.append((String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE) //
                + (request.getQueryString() != null ? "?" + request.getQueryString() : ""));
        sb.append(", URL-Variables: ");
        sb.append(request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
        sb.append(", URL: ");
        sb.append(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));

        return sb.toString();
    }

    /**
     * Intercept용 Request 요청 URL 정보를 제공한다. <br>
     * 
     * <pre>
     * Modification 
     * 
     *   수정일              수정자              수정내용
     *  ----------   ---------   -------------------------------
     *  2018. 10. 11.  Park J.H.    최초생성
     * </pre>
     *
     * @param request
     * @return
     *
     * @since 2018. 10. 11.
     */
    public static final String getUrlInfoForIntercept(HttpServletRequest request) {

        StringBuffer sb = new StringBuffer();

        sb.append("Remote: ");
        sb.append(request.getRemoteAddr());
        sb.append(" (");
        sb.append(request.getRemoteHost());
        sb.append("), URL-Pattern: ");
        sb.append(request.getMethod());
        sb.append(" | ");
        sb.append((String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE) //
                + (request.getQueryString() != null ? "?" + request.getQueryString() : ""));
        sb.append(", URL-Variables: ");
        sb.append(request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
        sb.append(", URL: ");
        sb.append(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));

        return sb.toString();
    }

}
