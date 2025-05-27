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
 * Date  : 2025. 5. 26. 오후 4:41:00
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.config;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.jackson.ConditionalJackson2HttpMessageConverter;

/**
 * {@link AuthorizedObject} 어노테이션이 설정된 타입을 serialize를 처리하는 {@link HttpMessageConverter}를 등록하는 서비스.
 * 
 * @since 2025. 5. 26.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedObjectMessageConfigure implements WebMvcConfigurer {

    private final MappingJackson2HttpMessageConverter authorizeObjectMessageConverter;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param authorizeObjectMessageConverter
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedObjectMessageConfigure(@NotNull ConditionalJackson2HttpMessageConverter authorizeObjectMessageConverter) {
        this.authorizeObjectMessageConverter = authorizeObjectMessageConverter;
    }

    /**
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureMessageConverters(java.util.List)
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        int index = 0;
        for (HttpMessageConverter<?> c : converters) {
            if (c instanceof MappingJackson2HttpMessageConverter) {
                break;
            }
            index++;
        }
        converters.add(index, this.authorizeObjectMessageConverter);
    }
}
