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
 * Date  : 2025. 7. 10. 오후 6:37:31
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.rest;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.Assert;

/**
 * 
 * @since 2025. 7. 10.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class QueryParam implements Comparable<QueryParam> {
    /** 파라미터 이름 */
    @NotEmpty
    private String name;
    /** 파리미터 필수 여부 */
    private boolean required;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 10.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public QueryParam() {
    }

    /**
     *
     * @since 2025. 7. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(QueryParam o) {
        return 0;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     * @param required
     *
     * @since 2025. 7. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public QueryParam(@NotEmpty String name, boolean required) {
        super();
        this.name = name;
        this.required = required;
    }

    /**
     *
     * @since 2025. 7. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QueryParam other = (QueryParam) obj;
        return Objects.equals(name, other.name);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 10.		박준홍			최초 작성
     * </pre>
     * 
     * @return the name
     *
     * @since 2025. 7. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #name
     */

    public String getName() {
        Assert.hasLength(this.name, "Query Parameter 이름은 반드시 설정되어야 합니다.");
        return name;
    }

    /**
     *
     * @since 2025. 7. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 10.		박준홍			최초 작성
     * </pre>
     * 
     * @return the required
     *
     * @since 2025. 7. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #required
     */

    public boolean isRequired() {
        return required;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     *            the name to set
     *
     * @since 2025. 7. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #name
     */
    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param required
     *            the required to set
     *
     * @since 2025. 7. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #required
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     *
     * @since 2025. 7. 10.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("QueryParam [name=");
        builder.append(name);
        builder.append(", required=");
        builder.append(required);
        builder.append("]");
        return builder.toString();
    }

}
