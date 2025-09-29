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
 * Date  : 2025. 9. 29. 오후 4:26:11
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @since 2025. 9. 29.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class RestApiGroup {

    /**
     * REST API 그룹<br>
     * value: {@link Tag#name()}에 해당하는 값.
     */
    @NotBlank
    private String name;
    /**
     * REST API 그룹 설명<br>
     * value: {@link Tag#description()}에 해당하는 값.
     */
    private String description;

    /** REST API 목록 */
    @NotNull
    private List<RestApiDecl> restApis;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public RestApiGroup() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     * 
     * @return the description
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #description
     */

    public String getDescription() {
        return description;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     * 
     * @return the name
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #name
     */

    public String getName() {
        return name;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     * 
     * @return the restApis
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #restApis
     */

    public List<RestApiDecl> getRestApis() {
        return restApis;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param description
     *            the description to set
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     *            the name to set
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #name
     */
    public void setName(@NotBlank String name) {
        this.name = name;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param restApis
     *            the restApis to set
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #restApis
     */
    public void setRestApis(@NotNull List<RestApiDecl> restApis) {
        this.restApis = restApis;
    }

    /**
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RestApiGroup [name=");
        builder.append(name);
        builder.append(", description=");
        builder.append(description);
        builder.append(", restApis=");
        builder.append(restApis);
        builder.append("]");
        return builder.toString();
    }
}
