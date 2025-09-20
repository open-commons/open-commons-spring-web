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
 * Date  : 2025. 6. 12. 오후 5:58:20
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.authority.metadata;

import java.util.List;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Bean;

import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @see AuthorizedObject
 * @see AuthorizedField
 */
public class AuthorizedObjectMetadata extends AuthorizedMetadata {

    /** 데이터 유형 */
    @NotNull
    private Class<?> type;
    /** bind to {@link AuthorizedObject#authorityBean()} */
    private String authorityBean;
    /** bind to {@link AuthorizedObject#fieldHandleBean()} */
    private String fieldHandleBean;
    /** {@link AuthorizedField} 정보 */
    @NotEmpty
    private List<AuthorizedFieldMetadata> fields;

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
    public AuthorizedObjectMetadata() {
    }

    /**
     * {@link IFieldAccessAuthorityProvider}를 구현한 {@link Bean} 이름을 제공합니다.<br>
     * {@link AuthorizedFieldMetadata#getAuthorityBean()}에도 설정되어 있는 경우에는
     * {@link AuthorizedFieldMetadata#getAuthorityBean ()}이 사용됩니다.
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
     * {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean} 이름을 제공합니다.<br>
     * {@link AuthorizedFieldMetadata#getFieldHandleBean()}에도 설정되어 있는 경우에는
     * {@link AuthorizedFieldMetadata#getFieldHandleBean()}이 사용됩니다.
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
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     * 
     * @return the fields
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #fields
     */

    public List<AuthorizedFieldMetadata> getFields() {
        return fields;
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
     * @return the type
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #type
     */

    public Class<?> getType() {
        return type;
    }

    /**
     * {@link IFieldAccessAuthorityProvider}를 구현한 {@link Bean} 이름을 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param authorityBean
     *            {@link IFieldAccessAuthorityProvider}를 구현한 {@link Bean} 이름
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #authorityBean
     * @see IFieldAccessAuthorityProvider
     */
    public void setAuthorityBean(String authorityBean) {
        this.authorityBean = resolveBeanName(authorityBean);
    }

    /**
     * {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean} 이름을 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param fieldHandleBean
     *            {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean} 이름
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #fieldHandleBean
     * @see IUnauthorizedFieldHandler
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
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param fields
     *            the fields to set
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #fields
     */
    public void setFields(@NotEmpty @Nonnull List<AuthorizedFieldMetadata> fields) {
        this.fields = fields;
    }

    /**
     * {@link AuthorizedObject}를 적용할 데이터 유형을 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param type
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void setType(@NotNull @Nonnull Class<?> type) {
        this.type = type;
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthorizedObjectMetadata [type=");
        builder.append(type);
        builder.append(", authorityBean=");
        builder.append(authorityBean);
        builder.append(", fieldHandleBean=");
        builder.append(fieldHandleBean);
        builder.append(", fields=");
        builder.append(fields);
        builder.append("]");
        return builder.toString();
    }

}
