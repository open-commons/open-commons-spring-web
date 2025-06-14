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
 * Date  : 2025. 6. 12. 오후 5:58:33
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.authority.metadata;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import open.commons.spring.web.authority.AuthorizedField;

/**
 * {@link AuthorizedField}를 외부에서 설정하는 클래스.
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @see AuthorizedField
 */
public class AuthorizedFieldMetadata extends AuthorizedMetadata {

    /** bind to {@link AuthorizedField#name()} */
    @NotEmpty
    private String name;
    /** bind to {@link AuthorizedField#authorityBean()} */
    private String authorityBean;
    /** bind to {@link AuthorizedField#fieldHandleBean()} */
    private String fieldHandleBean;
    /** bind to {@link AuthorizedField#handleType()} */
    private int handleType = AuthorizedField.NO_ASSINGED_HANDLE_TYPE;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedFieldMetadata() {
    }

    /**
     * {@link AuthorizedFieldMetadata}는 {@link AuthorizedObjectMetadata} 내에서 동일한 {@link #name}값을 가질 수가 없습니다. 이 내용을 기준으로
     * 동일 여부를 {@link #name}으로만 비교합니다.
     * 
     * @since 2025. 6. 13.
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
        AuthorizedFieldMetadata other = (AuthorizedFieldMetadata) obj;
        return Objects.equals(name, other.name);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     * 
     * @return the authorityBean
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #authorityBean
     */

    public String getAuthorityBean() {
        return authorityBean;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     * 
     * @return the fieldHandleBean
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #fieldHandleBean
     */

    public String getFieldHandleBean() {
        return fieldHandleBean;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 13.		박준홍			최초 작성
     * </pre>
     * 
     * @return the handleType
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #handleType
     */

    public int getHandleType() {
        return handleType;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     * 
     * @return the name
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #name
     */

    public String getName() {
        return name;
    }

    /**
     * {@link AuthorizedFieldMetadata}는 {@link AuthorizedObjectMetadata} 내에서 동일한 {@link #name}값을 가질 수가 없습니다. 이 내용을 기준으로
     * 해시코드 생성을 {@link #name}으로만 비교합니다.
     * 
     * @since 2025. 6. 13.
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
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param authorityBean
     *            the authorityBean to set
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #authorityBean
     */
    public void setAuthorityBean(String authorityBean) {
        this.authorityBean = resolveBeanName(authorityBean);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param fieldHandleBean
     *            the fieldHandleBean to set
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #fieldHandleBean
     */
    public void setFieldHandleBean(String fieldHandleBean) {
        this.fieldHandleBean = resolveBeanName(fieldHandleBean);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param handleType
     *            the handleType to set
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #handleType
     */
    public void setHandleType(@Min(AuthorizedField.NO_ASSINGED_HANDLE_TYPE + 1) int handleType) {
        this.handleType = handleType;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param name
     *            the name to set
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #name
     */
    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    /**
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthorizedFieldMetadata [name=");
        builder.append(name);
        builder.append(", authorityBean=");
        builder.append(authorityBean);
        builder.append(", fieldHandleBean=");
        builder.append(fieldHandleBean);
        builder.append(", handleType=");
        builder.append(handleType);
        builder.append("]");
        return builder.toString();
    }

}
