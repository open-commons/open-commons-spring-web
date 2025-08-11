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
 * Date  : 2019. 6. 27. 오후 1:14:34
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.resources;

import javax.validation.constraints.Min;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * {@link ClientHttpRequestFactory} properties for {@link RestTemplate}.
 * 
 * @since 2019. 6. 27.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class RestTemplateRequestFactoryResource {

    private boolean bufferRequestBody = true;

    private int connectionRequestTimeout;

    private int connectionTimeout;

    private int readTimeout;

    /** 외부 설정 여부 */
    private boolean initialized = false;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 27.     박준홍         최초 작성
     * </pre>
     *
     * @since 2019. 6. 27.
     * @version
     */
    public RestTemplateRequestFactoryResource() {
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 27.     박준홍         최초 작성
     * </pre>
     * 
     * @return the connectionRequestTimeout
     *
     * @since 2019. 6. 27.
     * @version
     * 
     * @see #connectionRequestTimeout
     */
    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 27.     박준홍         최초 작성
     * </pre>
     * 
     * @return the connctionTimeout
     *
     * @since 2019. 6. 27.
     * @version
     * 
     * @see #connectionTimeout
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 27.     박준홍         최초 작성
     * </pre>
     * 
     * @return the readTimeout
     *
     * @since 2019. 6. 27.
     * @version
     * 
     * @see #readTimeout
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 27.     박준홍         최초 작성
     * </pre>
     * 
     * @return the bufferRequestBody
     *
     * @since 2019. 6. 27.
     * @version
     * 
     * @see #bufferRequestBody
     */
    public boolean isBufferRequestBody() {
        return bufferRequestBody;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 11.		박준홍			최초 작성
     * </pre>
     * 
     * @return the initialized
     *
     * @since 2025. 8. 11.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #initialized
     */

    public boolean isInitialized() {
        return initialized;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param bufferRequestBody
     *            the bufferRequestBody to set
     *
     * @since 2019. 6. 27.
     * @version
     * 
     * @see #bufferRequestBody
     * 
     * @see HttpComponentsClientHttpRequestFactory#setBufferRequestBody(boolean)
     */
    public void setBufferRequestBody(boolean bufferRequestBody) {
        this.bufferRequestBody = bufferRequestBody;

        this.initialized = true;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param connectionRequestTimeout
     *            the connectionRequestTimeout to set
     *
     * @since 2019. 6. 27.
     * @version
     * 
     * @see #connectionRequestTimeout
     * @see HttpComponentsClientHttpRequestFactory#setConnectionRequestTimeout(int)
     */
    public void setConnectionRequestTimeout(@Min(0) int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;

        this.initialized = true;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param connctionTimeout
     *            the connctionTimeout to set
     *
     * @since 2019. 6. 27.
     * @version
     * 
     * @see #connectionTimeout
     * @see HttpComponentsClientHttpRequestFactory#setConnectTimeout(int)
     */
    public void setConnectionTimeout(@Min(0) int connctionTimeout) {
        this.connectionTimeout = connctionTimeout;

        this.initialized = true;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 27.     박준홍         최초 작성
     * </pre>
     *
     * @param readTimeout
     *            the readTimeout to set
     *
     * @since 2019. 6. 27.
     * @version
     * 
     * @see #readTimeout
     * @see HttpComponentsClientHttpRequestFactory#setReadTimeout(int)
     */
    public void setReadTimeout(@Min(0) int readTimeout) {
        this.readTimeout = readTimeout;

        this.initialized = true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RestTemplateRequestFactoryResource [bufferRequestBody=");
        builder.append(bufferRequestBody);
        builder.append(", connectionRequestTimeout=");
        builder.append(connectionRequestTimeout);
        builder.append(", connectionTimeout=");
        builder.append(connectionTimeout);
        builder.append(", readTimeout=");
        builder.append(readTimeout);
        builder.append("]");
        return builder.toString();
    }
}
