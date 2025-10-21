/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2019. 6. 7. 오후 6:50:59
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.enums;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import open.commons.spring.web.config.CustomWebMvcConfigurer;

/**
 * {@link Converter}를 이용하여 등록될 {@link Enum} 클래스가 있는 패키지 경로 정보를 제공하는 클래스.
 * 
 * @since 2019. 6. 7.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@Component
@ConfigurationProperties(CustomWebMvcConfigurer.APPLICATION_PROPERTIES_PREFIX)
public class EnumPackages {

    private List<String> packages = new ArrayList<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @since 2019. 6. 7.
     * @version
     */
    public EnumPackages() {
    }

    /**
     * {@link Enum} 클래스가 있는 패키지 경로 정보 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 7.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     * 
     * @return the packages
     *
     * @since 2019. 6. 7.
     * @version
     * 
     * @see #packages
     */
    public List<String> getPackages() {
        return packages;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 7.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param packages
     *            the packages to set
     *
     * @since 2019. 6. 7.
     * @version
     * 
     * @see #packages
     */
    public void setPackages(List<String> packages) {
        this.packages = packages != null //
                ? packages //
                : new ArrayList<>();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CustomEnumPackages [packages=");
        builder.append(packages);
        builder.append("]");
        return builder.toString();
    }

}
