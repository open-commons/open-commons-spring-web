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
import java.util.Objects;

import org.springframework.context.annotation.Bean;

import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;

/**
 * '암호화/난독화' 되어 있는 데이터({@link Field})에 대한 처리하는 정보를 기술하는 클래스.
 * 
 * @since 2025. 9. 20.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedRequestDataFieldMetadata extends AuthorizedMetadata {

    /** 변수 이름 */
    private String name;

    /**
     * {@link IAuthorizedRequestDataHandler} 를 구현한 {@link Bean} 이름<br>
     * 
     * @see {@link AuthorizedRequestData#handleBean()}
     */
    private String handleBean;

    /**
     * {@link IAuthorizedRequestDataHandler#restoreValue(int, Object)} 에서 첫번째 파라미터로 사용<br>
     * 
     * @see {@link AuthorizedRequestData#handleType()}
     */
    private int handleType;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 20.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 9. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedRequestDataFieldMetadata() {
    }

    /**
     *
     * @since 2025. 9. 22.
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
        AuthorizedRequestDataFieldMetadata other = (AuthorizedRequestDataFieldMetadata) obj;
        return Objects.equals(name, other.name);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 20.		박준홍			최초 작성
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
     * 2025. 9. 20.		박준홍			최초 작성
     * </pre>
     * 
     * @return the handleType
     *
     * @since 2025. 9. 20.
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
     * 2025. 9. 20.		박준홍			최초 작성
     * </pre>
     * 
     * @return the fieldName
     *
     * @since 2025. 9. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #name
     */

    public String getName() {
        return name;
    }

    /**
     *
     * @since 2025. 9. 22.
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
     * 2025. 9. 20.		박준홍			최초 작성
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
     * 2025. 9. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param handleType
     *            the handleType to set
     *
     * @since 2025. 9. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #handleType
     */
    public void setHandleType(int handleType) {
        this.handleType = handleType;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param fieldName
     *            the fieldName to set
     *
     * @since 2025. 9. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #name
     */
    public void setName(String fieldName) {
        this.name = fieldName;
    }

    /**
     *
     * @since 2025. 9. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthorizedDataMetadata [fieldName=");
        builder.append(name);
        builder.append(", handleBean=");
        builder.append(handleBean);
        builder.append(", handleType=");
        builder.append(handleType);
        builder.append("]");
        return builder.toString();
    }
}
