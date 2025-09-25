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
 * Date  : 2025. 9. 25. 오전 10:22:45
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.authority;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.utils.BeanUtils;

/**
 * {@link AuthorizedObject}, {@link AuthorizedField}, {@link AuthorizedRequestData} 관련 기능을 제공하는 클래스.
 * 
 * @since 2025. 9. 25.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourceUtils {
    private AuthorizedResourceUtils() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 25.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param beanUtils
     *            {@link Bean} 정보를 제공하는 객체.
     * @param beanType
     *            Bean 유형
     * @param o
     *            타입에 정의된 어노테이션 이름
     * @param f
     *            필드에 정의된 어노테이션 이름
     * @return
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <T> T getBean(BeanUtils beanUtils, Class<T> beanType, Supplier<String> o, Supplier<String> f) {
        String beanName = null;
        if (f == null) {
            beanName = o.get();
        } else {
            String fBean = f.get();
            beanName = StringUtils.isNullOrEmptyString(fBean) //
                    ? o.get()//
                    : fBean;
        }

        return beanUtils.findBean(beanName, beanType, null, true);
    }

}
