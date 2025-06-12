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
 * Date  : 2025. 6. 12. 오후 8:25:42
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Bean;

import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IAuthorizedResourcesMetadataProvider {

    /**
     * {@link IFieldAccessAuthorityProvider}를 구현한 {@link Bean} 을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 12.     박준홍         최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형
     * @return
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public String getAuthorityBeanName(@NotNull Class<?> clazz);

    /**
     * {@link IFieldAccessAuthorityProvider}를 구현한 {@link Bean} 을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형
     * @param fieldName
     *            {@link AuthorizedField} metadata가 적용된 필드
     * @return
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public String getAuthorityBeanName(@NotNull Class<?> clazz, String fieldName);

    /**
     * {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 12.     박준홍         최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형
     * @return
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public String getFieldHandleBeanName(@NotNull Class<?> clazz);

    /**
     * {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형
     * @param fieldName
     *            {@link AuthorizedField} metadata가 적용된 필드
     * @return
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public String getFieldHandleBeanName(@NotNull Class<?> clazz, String fieldName);

    /**
     * {@link AuthorizedObject} metadata 적용여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata 적용여부를 확인하는 데이터 유형
     * @return
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public boolean isAuthorized(@NotNull Class<?> clazz);

}
