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
 * Date  : 2025. 6. 12. 오후 8:25:42
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import java.lang.reflect.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Bean;

import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.authority.metadata.AuthorizedFieldMetadata;
import open.commons.spring.web.authority.metadata.AuthorizedObjectMetadata;

/**
 * {@link AuthorizedObject}, {@link AuthorizedField}를 POJO 클래스에 직접 선언하지 않거나, 3rd POJO 클래스에 적용하고자 할때 사용되메타데이터 클래스
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IAuthorizedResourcesMetadata {

    /**
     * 데이터 유형({@link Class})에 설정된 {@link IFieldAccessAuthorityProvider}를 구현한 {@link Bean} 을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 12.     박준홍         최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형 ({@link Class})
     * @return
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public String getAuthorityBeanName(@NotNull Class<?> clazz);

    /**
     * {@link Field}에 설정된 {@link IFieldAccessAuthorityProvider}를 구현한 {@link Bean} 을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형 ({@link Class})
     * @param fieldName
     *            {@link AuthorizedField} metadata가 적용된 필드({@link Field})
     * @return
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public String getAuthorityBeanName(@NotNull Class<?> clazz, String fieldName);

    /**
     * {@link AuthorizedFieldMetadata}를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형 ({@link Class})
     * @param fieldName
     *            {@link AuthorizedField} metadata가 적용된 필드({@link Field})
     * @return
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public AuthorizedFieldMetadata getAuthorizedFieldMetadata(@NotNull Class<?> clazz, String fieldName);

    /**
     * {@link AuthorizedObjectMetadata} 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형 ({@link Class})
     * @return
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public AuthorizedObjectMetadata getAuthorizedObjectMetadata(@NotNull Class<?> clazz);

    /**
     * 
     * 데이터 유형({@link Class})에 설정된 {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 12.     박준홍         최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형 ({@link Class})
     * @return
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public String getFieldHandleBeanName(@NotNull Class<?> clazz);

    /**
     * {@link Field}에 설정된 {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형 ({@link Class})
     * @param fieldName
     *            {@link AuthorizedField} metadata가 적용된 필드({@link Field})
     * @return
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public String getFieldHandleBeanName(@NotNull Class<?> clazz, String fieldName);

    /**
     * {@link AuthorizedField} metadata 적용여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param clazz
     *            {@link AuthorizedObject} metadata가 적용된 데이터 유형 ({@link Class})
     * 
     * @param fieldName
     *            {@link AuthorizedField} metadata가 적용된 필드({@link Field})
     * @return
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public boolean isAuthorizedField(@NotNull Class<?> clazz, @NotEmpty String fieldName);

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
    public boolean isAuthorizedObject(@NotNull Class<?> clazz);

}
