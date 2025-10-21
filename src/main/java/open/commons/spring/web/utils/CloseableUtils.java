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
 * Date  : 2025. 8. 26. 오전 11:41:05
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import org.springframework.http.client.AbstractClientHttpRequestFactoryWrapper;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import open.commons.spring.web.client.CloseableRestTemplate;

/**
 * 
 * @since 2025. 8. 26.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class CloseableUtils {
    private CloseableUtils() {
    }

    /**
     * {@link ClientHttpRequestFactory} 자원을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param chrf
     *            {@link ClientHttpRequestFactory} 객체.
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static void close(ClientHttpRequestFactory chrf) {
        close(chrf, Collections.newSetFromMap(new IdentityHashMap<>()));
    }

    /**
     * {@link ClientHttpRequestFactory} 자원을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param chrf
     *            {@link ClientHttpRequestFactory} 객체.
     * @param visited
     *            동일 객체에 대한 중복방지를 위한 기록
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static void close(ClientHttpRequestFactory chrf, Set<Object> visited) {
        // 이미 포함하고 있는 경우.
        if (!visited.add(chrf)) {
            return;
        }

        if (chrf instanceof AbstractClientHttpRequestFactoryWrapper) {
            boolean accessible = false;
            for (Field f : ClassInspector.getAllFields(chrf.getClass())) {
                accessible = f.isAccessible();
                f.setAccessible(true);
                try {
                    if (ClientHttpRequestFactory.class.isAssignableFrom(f.getType())) {
                        try {
                            close((ClientHttpRequestFactory) f.get(chrf), visited);
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                        }
                    }
                } finally {
                    f.setAccessible(accessible);
                }
            }
        }
        if (chrf instanceof HttpComponentsClientHttpRequestFactory) {
            try {
                ((HttpComponentsClientHttpRequestFactory) chrf).destroy();
            } catch (Exception e) {
            }
        }
    }

    /**
     * {@link RestTemplate}가 사용하는 {@link ClientHttpRequestFactory} 자원을 해제합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param restTemplate
     *            {@link RestTemplate} 객체
     *
     * @since 2025. 8. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static void close(RestTemplate restTemplate) {
        if (restTemplate instanceof CloseableRestTemplate) {
            ((CloseableRestTemplate) restTemplate).close();
        } else {
            close(restTemplate.getRequestFactory());
        }
    }
}
