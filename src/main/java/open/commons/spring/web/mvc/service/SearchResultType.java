/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 12. 6. 오후 5:14:42
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.mvc.service;

import java.util.ArrayList;
import java.util.List;

import open.commons.spring.web.annotation.RequestValueConverter;
import open.commons.spring.web.annotation.RequestValueSupported;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * 
 * @since 2021. 12. 6.
 * @version 0.4.0
 * @author parkjunhong77@gmail.com
 */
@RequestValueSupported
public enum SearchResultType {

    /** Pagination */
    PAGINATION("pagination"), //
    /** All */
    ALL("all"), //
    ;

    private String type;

    private SearchResultType(String type) {
        this.type = type;
    }

    /**
     *
     * @return a string of an instance of {@link SearchResultType}
     *
     * @since 2021. 12. 6.
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    @JsonValue
    public String get() {
        return this.type;
    }

    /**
     * @since 2021. 12. 6.
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     *
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return String.join(":", name(), this.type);
    }

    /**
     * 
     * @param type
     *            a string for {@link SearchResultType} instance.
     *
     * @return an instance of {@link SearchResultType}
     *
     * @since 2021. 12. 6.
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     *
     * @see #get(String, boolean)
     */
    public static SearchResultType get(String type) {
        return get(type, false);
    }

    /**
     *
     * @param type
     *            a string for an instance of {@link SearchResultType}.
     * @param ignoreCase
     *            ignore <code><b>case-sensitive</b></code> or not.
     *
     * @return an instance of {@link SearchResultType}
     *
     * @since 2021. 12. 6.
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    @RequestValueConverter(hasIgnoreCase = true)
    public static SearchResultType get(String type, boolean ignoreCase) {

        if (type == null) {
            throw new IllegalArgumentException("'type' MUST NOT be null. input: " + type);
        }

        if (ignoreCase) {
            for (SearchResultType value : values()) {
                if (value.type.equalsIgnoreCase(type)) {
                    return value;
                }
            }
        } else {
            for (SearchResultType value : values()) {
                if (value.type.equals(type)) {
                    return value;
                }
            }
        }

        throw new IllegalArgumentException(
                "Unexpected 'type' value of 'SearchResultType'. expected: " + values0() + " & Ignore case-sensitive: " + ignoreCase + ", input: " + type);
    }

    private static List<String> values0() {

        List<String> valuesStr = new ArrayList<>();

        for (SearchResultType value : values()) {
            valuesStr.add(value.get());
        }

        return valuesStr;
    }

}
