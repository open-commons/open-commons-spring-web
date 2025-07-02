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
 * Date  : 2019. 10. 24. 오후 1:04:31
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.rest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.net.ssl.SSLContext;

import org.apache.http.NoHttpResponseException;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.SchemePortResolver;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.DefaultSchemePortResolver;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import open.commons.core.Result;
import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.ThreadUtils;

/**
 * {@link RestTemplate}을 이용하는 유틸리티 클래스.
 * 
 * @since 2019. 10. 24.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class RestUtils {

    private static final Logger logger = LoggerFactory.getLogger(RestUtils.class);

    private RestUtils() {
    }

    /**
     * 헤더에 새로운 정보를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param headers
     *            기존 헤더 객체
     * @param headerPairs
     *            헤더 정보
     * @return
     * @throws IllegalArgumentException
     *
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated Use {@link #headers(MultiValueMap, String...)}.
     */
    public static HttpHeaders addHeaders(HttpHeaders headers, String... headerPairs) throws IllegalArgumentException {
        return headers(headers, headerPairs);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 25.     박준홍         최초 작성
     * </pre>
     *
     * @param headers
     *            HTTP Headers
     * @param values
     *            데이터. (키/값 으로 이루어진)
     * @return
     *
     * @since 2020. 8. 25.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static HttpEntity<Map<String, Object>> buildHttpEntity(MultiValueMap<String, String> headers, Object... values) {

        Map<String, Object> map = new HashMap<>();

        for (int i = 0; (i + 1) < values.length; i++) {
            map.put(String.valueOf(values[i++]), values[i]);
        }

        return new HttpEntity<Map<String, Object>>(map, headers);
    }

    public static CloseableHttpClient createClient() {

        // Lookup
        RegistryBuilder<ConnectionSocketFactory> regBuilder = RegistryBuilder.create();
        regBuilder.register("http", new PlainConnectionSocketFactory());

        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory();

        // use org.apache.http.conn.SchemePortResolver for default port resolution and org.apache.http.config.Registry
        // for socket factory lookups.
        SchemePortResolver schemePortResolver = new DefaultSchemePortResolver();
        DnsResolver dnsResolver = new SystemDefaultDnsResolver();

        HttpClientConnectionManager manager = new BasicHttpClientConnectionManager(regBuilder.build(), connectionFactory, schemePortResolver, dnsResolver);

        // connection config
        ConnectionConfig.Builder conBuilder = ConnectionConfig.custom();
        conBuilder.setCharset(Charset.forName("UTF-8"));

        ConnectionConfig conConfig = conBuilder.build();

        // client
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setDefaultConnectionConfig(conConfig) //
                .setConnectionManager(manager);

        CloseableHttpClient client = httpClientBuilder.build();

        return client;
    }

    /**
     * 
     * HTTPS 연결객체를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2016. 11. 21     박준홍         최초 작성
     * 2019. 4. 9.      박준홍         기능 확장. (사설 인증서 지원)
     * </pre>
     *
     * @param allowPrivateCA
     *            사설인증서 지원 여부
     * @return
     * @throws KeyManagementException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     *
     * @since 2019. 4. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static CloseableHttpClient createHttpsClient(boolean allowPrivateCA) throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {

        // Lookup
        RegistryBuilder<ConnectionSocketFactory> regBuilder = createRegistryBuilder(allowPrivateCA);

        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory();

        // use org.apache.http.conn.SchemePortResolver for default port resolution and org.apache.http.config.Registry
        // for socket factory lookups.
        DnsResolver dnsResolver = new SystemDefaultDnsResolver();

        // begin - PATCH [2020. 12. 9.]: Thread Safe 지원 | Park_Jun_Hong_(parkjunhong77@gmail.com)
        HttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(regBuilder.build(), connectionFactory, dnsResolver);
        // end - Park_Jun_Hong_(parkjunhong77@gmail.com), 2020. 12. 9.

        // connection config
        ConnectionConfig.Builder conBuilder = ConnectionConfig.custom();
        conBuilder.setCharset(Charset.forName("UTF-8"));

        ConnectionConfig conConfig = conBuilder.build();

        // client
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setDefaultConnectionConfig(conConfig) //
                .setConnectionManager(manager);

        CloseableHttpClient client = httpClientBuilder.build();

        return client;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 4. 9.      박준홍         최초 작성
     * </pre>
     *
     * @param allowPrivateCA
     *            사설인증서 자동 허용 여부
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws KeyStoreException
     *
     * @since 2019. 4. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private static RegistryBuilder<ConnectionSocketFactory> createRegistryBuilder(boolean allowPrivateCA)
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        // Lookup
        RegistryBuilder<ConnectionSocketFactory> regBuilder = RegistryBuilder.create();
        if (allowPrivateCA) {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();
            regBuilder.register("https", new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier()));
            regBuilder.register("http", new PlainConnectionSocketFactory());
        } else {
            SSLContext sslContext = SSLContext.getDefault();
            regBuilder.register("https", new SSLConnectionSocketFactory(sslContext));
            regBuilder.register("http", new PlainConnectionSocketFactory());
        }

        return regBuilder;
    }

    /**
     * 요청 URL 를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 8. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     *            REST API Context
     * @param url
     *            REST API URL
     * @param parameters
     *            Query Parameters
     * @return
     *
     * @since 2020. 8. 27.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String createUrl(String context, String url, MultiValueMap<String, Object> parameters) {

        StringBuffer requestUrl = new StringBuffer();

        if (context != null && !context.trim().isEmpty()) {
            requestUrl.append('/');
            requestUrl.append(context);
        }
        requestUrl.append(url);

        if (parameters == null || parameters.isEmpty()) {
            return requestUrl.toString();
        }

        StringBuffer paramBuf = new StringBuffer();
        parameters.entrySet().stream()//
                .map(e -> {
                    if (e.getValue() == null || e.getValue().isEmpty()) {
                        return null;
                    }

                    StringBuffer param = new StringBuffer();
                    String name = e.getKey();
                    for (Object o : e.getValue()) {
                        param.append('&');
                        param.append(name);
                        param.append('=');
                        param.append(o);
                    }
                    return param.toString();
                }) //
                .filter(p -> p != null) //
                .forEach(param -> paramBuf.append(param));

        if (paramBuf.length() > 0) {
            String paramStr = paramBuf.toString().substring(1);
            if (!paramStr.isEmpty()) {
                requestUrl.append('?');
                requestUrl.append(paramStr);
            }
        }

        return requestUrl.toString();
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 24.        박준홍         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @return
     *
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, HttpEntity<REQ> entity,
            Class<RES> responseType) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, response -> {
            Result<RES> result = null;
            HttpStatus status = response.getStatusCode();
            if (status.is2xxSuccessful()) {
                result = new Result<>(response.getBody(), true);
            } else {
                result = new Result<>(response.getBody(), false);
                result.setMessage(status.getReasonPhrase());
            }
            return result;
        }, error -> {
            Result<RES> result = new Result<RES>();
            result.setMessage(error.getMessage());

            return result;
        });
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated Use
     *             {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity, Class, Function, Function)}
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, HttpEntity<REQ> entity,
            Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RES>> onSuccess //
            , Function<Exception, Result<RES>> onError //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @return
     *
     * @since 2020. 11. 20.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, HttpEntity<REQ> entity,
            ParameterizedTypeReference<RES> responseType) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, response -> {
            Result<RES> result = null;
            HttpStatus status = response.getStatusCode();
            if (status.is2xxSuccessful()) {
                result = new Result<>(response.getBody(), true);
            } else {
                result = new Result<>(response.getBody(), false);
                result.setMessage(status.getReasonPhrase());
            }
            return result;
        }, error -> {
            Result<RES> result = new Result<RES>();
            result.setMessage(error.getMessage());

            return result;
        });
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2020. 11. 20.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated Use
     *             {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, HttpEntity, ParameterizedTypeReference, Function, Function)}
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, HttpEntity<REQ> entity,
            ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RES>> onSuccess //
            , Function<Exception, Result<RES>> onError //
    ) {
        return exchange(restTemplate, method, scheme, host, port, path, null, entity, responseType, onSuccess, onError);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 24.        박준홍         최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @return
     *
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, String query,
            HttpEntity<REQ> entity, Class<RES> responseType) {
        return exchange(restTemplate, method, scheme, host, port, path, query, entity, responseType, response -> {
            Result<RES> result = null;
            HttpStatus status = response.getStatusCode();
            if (status.is2xxSuccessful()) {
                result = new Result<>(response.getBody(), true);
            } else {
                result = new Result<>(response.getBody(), false);
                result.setMessage(status.getReasonPhrase());
            }
            return result;
        }, error -> {
            Result<RES> result = new Result<RES>();
            result.setMessage(error.getMessage());

            return result;
        });
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @deprecated Use
     *             {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity, Class, Function, Function)}
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, String query,
            HttpEntity<REQ> entity, Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RES>> onSuccess //
            , Function<Exception, Result<RES>> onError//
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @return
     *
     * @since 2020. 11. 20.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, String query,
            HttpEntity<REQ> entity, ParameterizedTypeReference<RES> responseType) {
        return exchange(restTemplate, method, scheme, host, port, path, query, entity, responseType, response -> {
            Result<RES> result = null;
            HttpStatus status = response.getStatusCode();
            if (status.is2xxSuccessful()) {
                result = new Result<>(response.getBody(), true);
            } else {
                result = new Result<>(response.getBody(), false);
                result.setMessage(status.getReasonPhrase());
            }
            return result;
        }, error -> {
            Result<RES> result = new Result<RES>();
            result.setMessage(error.getMessage());

            return result;
        });
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param scheme
     *            Connection Protocol
     * @param host
     *            Target Service IP or Hostname
     * @param port
     *            Target Service Port
     * @param path
     *            URL Path
     * @param query
     *            URL Query Parameters
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2020. 11. 20.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @deprecated Use
     *             {@link RestUtils2#exchange(RestTemplate, HttpMethod, String, String, int, String, String, HttpEntity, ParameterizedTypeReference, Function, Function)}
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, String scheme, String host, int port, String path, String query,
            HttpEntity<REQ> entity, ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RES>> onSuccess //
            , Function<Exception, Result<RES>> onError//
    ) {
        try {
            return exchange(restTemplate, method, new URI(scheme, null, host, port, path, query, null), entity, responseType, onSuccess, onError);
        } catch (URISyntaxException e) {
            logger.warn("method={}, scheme={}, host={}, port={}, path={}, query={}, entity={}, response.type={}", method, scheme, host, port, path, query, entity, responseType);
            return onError.apply(e);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 10. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2019. 10. 24.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @deprecated Use {@link RestUtils2#exchange(RestTemplate, HttpMethod, URI, HttpEntity, Class, Function, Function)}
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, URI uri, HttpEntity<REQ> entity, Class<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RES>> onSuccess //
            , Function<Exception, Result<RES>> onError//
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchange(sup, method, uri, entity, responseType, onSuccess, onError);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     *            요청 데이터 타입
     * @param <RES>
     *            수신 데이터 타입
     * @param restTemplate
     *            {@link RestTemplate} 객체
     * @param method
     *            Http 메소드
     * @param uri
     *            대상 URI 정보
     * @param entity
     *            요청 데이터
     * @param responseType
     *            수신 데이터 타입
     * @param onSuccess
     *            요청 성공 처리자
     * @param onError
     *            요청 실패 처리자
     * @return
     *
     * @since 2020. 11. 20.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @deprecated Use
     *             {@link RestUtils2#exchange(RestTemplate, HttpMethod, URI, HttpEntity, ParameterizedTypeReference, Function, Function)}
     */
    public static <REQ, RES> Result<RES> exchange(RestTemplate restTemplate, HttpMethod method, URI uri, HttpEntity<REQ> entity, ParameterizedTypeReference<RES> responseType //
            , Function<ResponseEntity<RES>, Result<RES>> onSuccess //
            , Function<Exception, Result<RES>> onError//
    ) {
        Supplier<ResponseEntity<RES>> sup = () -> restTemplate.exchange(uri, method, entity, responseType);
        return exchange(sup, method, uri, entity, responseType, onSuccess, onError);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param <REQ>
     * @param <RES>
     * @param sup
     * @param method
     * @param uri
     * @param entity
     * @param responseType
     * @param onSuccess
     * @param onError
     * @return
     *
     * @since 2020. 11. 23.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private static <REQ, RES> Result<RES> exchange(Supplier<ResponseEntity<RES>> sup, HttpMethod method, URI uri, HttpEntity<REQ> entity, Object responseType //
            , Function<ResponseEntity<RES>, Result<RES>> onSuccess, Function<Exception, Result<RES>> onError) {
        final int RETRY_MAX_COUNT = 5;
        int retrial = 0;

        Exception unhandled = null;
        while (retrial < RETRY_MAX_COUNT) {
            try {
                ResponseEntity<RES> response = sup.get();

                HttpStatus statusCode = response.getStatusCode();

                // redirection
                if (statusCode.is3xxRedirection()) {
                    logger.info("URL is redirectioned. status={}, information={}", statusCode, response.getBody());
                } else
                // success
                if (statusCode.is2xxSuccessful()) {
                    logger.debug("Success to send information. target={}", uri.toString());
                } else
                // informational...
                if (statusCode.is1xxInformational()) {
                    logger.debug("Information. status={}, information={}", statusCode, response.getBody());
                }

                return onSuccess.apply(response);
            } catch (HttpClientErrorException e) {

                logger.warn("method={}, uri={}, req.entity={}, res.type={}", method, uri, entity, responseType);

                HttpStatus statusCode = e.getStatusCode();

                // remote server internal error
                if (statusCode.is5xxServerError()) {
                    logger.warn("Remote Server Error. status={}", statusCode);
                } else
                // request error
                if (statusCode.is4xxClientError()) {
                    logger.warn("Request Client Error. status={}", statusCode);
                }

                logger.warn("res.status={}, res.status.raw={}, res.status.text={}, res.body={}", e.getStatusCode(), e.getRawStatusCode(), e.getStatusText(),
                        e.getResponseBodyAsString());

                return onError.apply(e);
            } catch (Exception e) {
                unhandled = e;
                logger.warn("{} Occured {}", "* * * * * ", e.getClass().getName());
                if (NoHttpResponseException.class.isAssignableFrom(e.getClass()) //
                        || ResourceAccessException.class.isAssignableFrom(e.getClass()) //
                ) {
                    retrial++;
                    logger.warn("{} Retry {} by {}", "* * * * * ", retrial, e.getClass().getName());
                    ThreadUtils.sleep(1000);
                } else {
                    throw ExceptionUtils.newException(RuntimeException.class, e, "예상하지 못한 에러가 발생하였습니다. 원인=%s, parent=%s", e.getMessage(), e);
                }
            }
        }
        return onError.apply(unhandled);
    }

    /**
     * 기본 헤더에 새로운 헤더를 추가하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 8. 28.     박준홍         최초 작성
     * </pre>
     *
     * @param headers
     *            기존 header
     * @param headerEntries
     *            새로운 header 정보
     * @return
     *
     * @since 2020. 8. 28.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static final HttpHeaders headers(MultiValueMap<String, String> headers, String... headerEntries) {
        AssertUtils2.assertNotNulls(IllegalArgumentException.class, (Object[]) headerEntries);

        if (headerEntries == null) {
            return new HttpHeaders(headers);
        } else {
            HttpHeaders newHeaders = new HttpHeaders();
            for (int i = 0; (i + 1) < headerEntries.length; i++) {
                newHeaders.add(String.valueOf(headerEntries[i++]), headerEntries[i]);
            }
            newHeaders.addAll(headers);
            return newHeaders;
        }
    }

    /**
     * 쿼리 파라미터 데이터를 하나의 문자열로 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 21.		박준홍			최초 작성
     * 2025. 7. 2.          박준홍     key=value에 {@link URLEncoder#encode(String)} 적용
     * </pre>
     *
     * @param parameters
     * @return
     *
     * @since 2020. 10. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String queryParameters(MultiValueMap<String, Object> parameters) {
        List<String> paramBuf = new ArrayList<>();
        parameters.entrySet().stream()//
                .map(e -> {
                    if (e.getValue() == null || e.getValue().isEmpty()) {
                        return null;
                    }

                    StringBuffer param = new StringBuffer();
                    String name = e.getKey();
                    for (Object o : e.getValue()) {
                        if (o == null) {
                            continue;
                        }
                        param.append(encode(name));
                        param.append('=');
                        param.append(encode(o.toString()));
                    }
                    return param.toString();
                }) //
                .filter(p -> p != null) //
                .forEach(param -> paramBuf.add(param));

        return String.join("&", paramBuf);
    }

    /**
     * URI 쿼리 파라미터 데이터를 인코딩합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 2.		박준홍			최초 작성
     * </pre>
     *
     * @param value
     * @return
     *
     * @since 2025. 7. 2.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Encoding failed for: " + value, e);
        }
    }

    /**
     * 쿼리 파라미터 데이터를 하나의 문자열로 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 21.		박준홍			최초 작성
     * 2025. 7. 2.          박준홍     key=value에서 key 의 <code>null</code> 여부 확인.
     * </pre>
     *
     * @param parameters
     *            쿼리 파라미터. 반드시 <name=value>의 쌍을 이루어야 한다.
     * @return
     *
     * @since 2020. 10. 21.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static String queryParameters(String... parameters) {
        if (parameters == null) {
            return "";
        }
        if (parameters.length % 2 != 0) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "올바르지 않은 파라미터 입니다. paramters=%s", Arrays.toString(parameters));
        }
        LinkedMultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        for (int i = 0; i < parameters.length; i += 2) {
            if (parameters[i] == null) {
                continue;
            }
            paramMap.add(parameters[i], parameters[i + 1]);
        }
        return queryParameters(paramMap);
    }
}
