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
import open.commons.spring.web.jackson.AuthorizedFieldSerializer;
import open.commons.spring.web.jackson.AuthorizedFieldSerializerModifier;
import open.commons.spring.web.jackson.AuthorizedObjectJackson2HttpMessageConverter;

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
    public AuthorizedObjectMessageConfigure(@NotNull AuthorizedObjectJackson2HttpMessageConverter authorizeObjectMessageConverter) {
        this.authorizeObjectMessageConverter = authorizeObjectMessageConverter;
    }

    /**
     * 내부 로직은 Spring 기반 웹 서비스 구동시 내부적으로 아래와 같은 {@link HttpMessageConverter}가 생성되는 것을 확인하고 진행되었습니다.<br>
     * {@link AuthorizedObjectJackson2HttpMessageConverter}는 {@link MappingJackson2HttpMessageConverter}를 상속받아 구현되었고 상위
     * 클래스 기능을 모두 제공하기 때문에 {@link MappingJackson2HttpMessageConverter}보다 앞에 위치시키게 되었습니다.<br>
     * 그 이유는 Spring 내부에서 serialize 하려는 대상에 따라 배열에서 순차적으로 {@link HttpMessageConverter} 구현체를 꺼내고,
     * {@link HttpMessageConverter#canWrite(Class, org.springframework.http.MediaType)} 의 결과에 따라서 사용여부를 결정하기 때문엡니다.<br>
     * 이러한 방식으로 인해서 {@link AuthorizedObjectJackson2HttpMessageConverter}가 {@link MappingJackson2HttpMessageConverter} 뒤에
     * 위치하게 되면 {@link AuthorizedObject} 어노테이션이 설정된 데이터 유형일지라도 {@link MappingJackson2HttpMessageConverter}가 처리할 수 있기 때문에
     * {@link AuthorizedObjectJackson2HttpMessageConverter}를 앞에 위치시키고, {@link AuthorizedObject}가 설정된 데이터 유형은
     * {@link AuthorizedFieldSerializerModifier} 및 {@link AuthorizedFieldSerializer}에 의해서 serialize 되고, 그렇지 않은 데이터 유형은
     * {@link MappingJackson2HttpMessageConverter}와 동일하게 {@link MappingJackson2HttpMessageConverter#getObjectMapper()}에
     * 의해서 serialize 됩니다.
     * 
     * <ul>
     * <li>[0] {@link org.springframework.http.converter.ByteArrayHttpMessageConverter}
     * <li>[1] {@link org.springframework.http.converter.StringHttpMessageConverter}
     * <li>[2] {@link org.springframework.http.converter.StringHttpMessageConverter}
     * <li>[3] {@link org.springframework.http.converter.ResourceHttpMessageConverter}
     * <li>[4] {@link org.springframework.http.converter.ResourceRegionHttpMessageConverter}
     * <li>[5] {@link org.springframework.http.converter.xml.SourceHttpMessageConverter}
     * <li>[6] {@link org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter}
     * <li>[7] {@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter}
     * <li>[8] {@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter}
     * <li>[9] {@link org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter}
     * </ul>
     * 
     * @param converters
     *            시스템에 등록된 {@link HttpMessageConverter} 목록
     * 
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureMessageConverters(java.util.List)
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(c -> c instanceof MappingJackson2HttpMessageConverter);
        converters.add(this.authorizeObjectMessageConverter);
//        int index = 0;
//        for (HttpMessageConverter<?> c : converters) {
//            if (c instanceof MappingJackson2HttpMessageConverter) {
//                break;
//            }
//            index++;
//        }
//        converters.add(index, this.authorizeObjectMessageConverter);
    }
}
