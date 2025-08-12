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
 * Date  : 2025. 7. 29. 오전 9:54:12
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.log;

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;

import open.commons.core.utils.StringUtils;

/**
 * 
 * @since 2025. 7. 29.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface ILogFeatureDecorationConsolidator {

    /**
     * {@link LogFeature} 정보에 맞는 함수를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param feature
     *            {@link LogFeature#feature()} 에 해당하는 데이터
     * @param marker
     *            {@link LogFeature#marker()}에 해당하는 내용
     * @return
     *
     * @since 2025. 7. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Nonnull
    public Function<String, String> decorator(@NotBlank @Nonnull String feature, String marker);

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param propertyValue
     *            서비스 항목 데이터
     * @return
     *
     * @since 2025. 7. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Nonnull
    public static String decorate(String propertyValue) {
        return StringUtils.isNullOrEmptyString(propertyValue = sanitize(propertyValue)) //
                ? "" //
                : new StringBuilder().append("<").append(propertyValue).append(">").toString();
    }

    /**
     * 항목값을 정리해서 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param propertyValue
     * @return
     *
     * @since 2025. 7. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Nonnull
    public static String sanitize(String propertyValue) {
        return StringUtils.isNullOrEmptyString(propertyValue) ? "" : propertyValue.trim();
    }
}
