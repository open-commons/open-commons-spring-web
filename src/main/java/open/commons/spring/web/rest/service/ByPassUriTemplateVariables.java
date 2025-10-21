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
 * Date  : 2025. 8. 27. 오후 3:12:59
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.rest.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.util.UriComponents.UriTemplateVariables;

import open.commons.core.utils.StringUtils;

/**
 * '변수'에 해당하는 값이 없는 경우 변수패턴("{변수}")을 제공하는 {@link Map}기반의 {@link UriTemplateVariables} 클래스.
 * 
 * @since 2025. 8. 27.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ByPassUriTemplateVariables implements MapUriTemplateVariables {

    /** URI 템플릿에 사용될 정보. */
    private Map<String, ?> variables;

    private ByPassUriTemplateVariables() {
        this.variables = new HashMap<>();
    }

    public ByPassUriTemplateVariables(Map<String, ?> variables) {
        this.variables = variables != null ? variables : new HashMap<>();
    }

    /**
     * '변수'에 해당하는 값이 존재하지 않는 경우, 템플릿형태("{변수이름}")를 반환.
     *
     * @since 2025. 8. 27.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.util.UriComponents.UriTemplateVariables#getValue(java.lang.String)
     */
    @Override
    public Object getValue(String name) {
        if (StringUtils.isNullOrEmptyString(name)) {
            return UriTemplateVariables.class;
        }
        Object value = this.variables.get(name);
        return value != null //
                ? value //
                : UriTemplateVariables.class;
    }

    /**
     *
     * @since 2025. 8. 27.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.rest.service.MapUriTemplateVariables#getVariables()
     */
    @Override
    public Map<String, ?> getVariables() {
        return this.variables;
    }

    /**
     * '변수값'이 없는 객체. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 27.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 27.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static ByPassUriTemplateVariables emptyVariables() {
        return new ByPassUriTemplateVariables();
    }
}
