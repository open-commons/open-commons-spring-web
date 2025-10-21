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
 * Date  : 2025. 9. 20. 오후 2:34:29
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.authority.metadata;

import java.lang.reflect.Field;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.context.annotation.Bean;

import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;

/**
 * '암호화/난독화' 되어 있는 데이터({@link Field})를 포함하고 있는 POJO 객체를 기술하는 클래스.
 * 
 * @since 2025. 9. 20.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedRequestDataObjectMetadata extends AuthorizedMetadata {

    /** 데이터 유형 */
    private Class<?> type;

    /**
     * {@link IAuthorizedRequestDataHandler} 를 구현한 {@link Bean} 이름<br>
     * 
     * @see {@link AuthorizedRequestData#handleBean()}
     */
    private String handleBean;

    /** {@link AuthorizedRequestData} 정보 */
    @NotEmpty
    private List<AuthorizedRequestDataFieldMetadata> fields;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 9. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedRequestDataObjectMetadata() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 22.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the fields
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #fields
     */

    public List<AuthorizedRequestDataFieldMetadata> getFields() {
        return fields;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the handleBean
     *
     * @since 2025. 9. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #handleBean
     */

    public String getHandleBean() {
        return handleBean;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the type
     *
     * @since 2025. 9. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #type
     */

    public Class<?> getType() {
        return type;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 22.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param fields
     *            the fields to set
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #fields
     */
    public void setFields(List<AuthorizedRequestDataFieldMetadata> fields) {
        this.fields = fields;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param handleBean
     *            the handleBean to set
     *
     * @since 2025. 9. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #handleBean
     */
    public void setHandleBean(String handleBean) {
        this.handleBean = handleBean;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param type
     *            the type to set
     *
     * @since 2025. 9. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #type
     */
    public void setType(Class<?> type) {
        this.type = type;
    }

    /**
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthorizedRequestObjectDataMetadata [type=");
        builder.append(type);
        builder.append(", handleBean=");
        builder.append(handleBean);
        builder.append(", fields=");
        builder.append(fields);
        builder.append("]");
        return builder.toString();
    }
}
