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
 * Date  : 2025. 8. 26. 오전 11:07:06
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.client;

import java.io.Closeable;
import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import open.commons.spring.web.utils.CloseableUtils;

/**
 * {@link RestTemplate}에 {@link Closeable} 인터페이스를 적용하여, 내부적으로 사용하는 {@link ClientHttpRequestFactory} 자원을 해제하는 기능을 지원하는
 * Wrapper 클래스.
 * 
 * @since 2025. 8. 26.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class CloseableRestTemplate extends RestTemplate implements Closeable {

    /** {@link RestTemplate}에서 내부적으로 사용하는 객체에 대한 참조. */
    private ClientHttpRequestFactory requestFactory;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public CloseableRestTemplate() {
        super();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param requestFactory
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public CloseableRestTemplate(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param messageConverters
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public CloseableRestTemplate(List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
    }

    /**
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author parkjunhong77@gmail.comk
     *
     * @see java.io.Closeable#close()
     */
    @Override
    @PreDestroy
    public void close() {
        CloseableUtils.close(this.requestFactory);
    }

    /**
     * @see #requestFactory
     */
    @Override
    public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
        super.setRequestFactory(requestFactory);
        this.requestFactory = getRequestFactory();
    }
}
