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
 * Date  : 2025. 9. 22. 오후 12:33:04
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import java.lang.reflect.Field;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Bean;

import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.authority.metadata.AuthorizedRequestDataFieldMetadata;
import open.commons.spring.web.authority.metadata.AuthorizedRequestDataObjectMetadata;

/**
 * {@link AuthorizedRequestData}를 POJO 클래스에 직접 선언하지 않거나, 3rd POJO 클래스에 적용하고자 할때 사용되메타데이터 클래스
 * 
 * @since 2025. 9. 22.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IAuthorizedRequestDataMetadata {

    /**
     * 주어진 클래스의 {@link Field}에 선언된 {@link AuthorizedRequestDataFieldMetadata} 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 22.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param targetClass
     *            클래스 유형
     * @param fieldName
     *            변수 이름
     * @return
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public AuthorizedRequestDataFieldMetadata getFieldMetadat(@NotNull @Nonnull Class<?> targetClass, @NotBlank String fieldName);

    /**
     * 주어진 클래스의 {@link Field}에 선언된 {@link IAuthorizedRequestDataHandler}를 구현한 {@link Bean} 이름을 제공합니다.<br>
     * {@link AuthorizedRequestData#handleBean()} 값과 매칭됩니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 22.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param targetClass
     *            클래스 유형
     * @param fieldName
     *            변수 이름
     * @return
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see AuthorizedRequestData#handleBean()
     */
    public String getHandleBeanName(@NotNull @Nonnull Class<?> targetClass, @NotBlank String fieldName);

    /**
     * 주어진 클래스의 {@link Field}에 선언된 {@link IAuthorizedRequestDataHandler#restoreValue(String, Object)}에 사용될 '데이터 처리방식 식별정보'를
     * 제공합니다.<br>
     * {@link AuthorizedRequestData#handleType()} 값과 매칭됩니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 22.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param targetClass
     *            클래스 유형
     * @param fieldName
     *            변수 이름
     * @return
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see AuthorizedRequestData#handleType()
     */
    public String getHandleType(@NotNull @Nonnull Class<?> targetClass, @NotBlank String fieldName);

    /**
     * 주어진 클래스에 적용될 {@link AuthorizedRequestDataObjectMetadata} 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 22.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param targetClass
     *            클래스 유형
     * @return
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public AuthorizedRequestDataObjectMetadata getObjectMetadata(@NotNull @Nonnull Class<?> targetClass);

    /**
     * 주어진 클래스가 '권한제어' 해제 대상인지 여부를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 9. 22.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param targetClass
     *            클래스 유형
     * @return
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public boolean isAuthorizedRequestDataObject(@NotNull @Nonnull Class<?> targetClass);

}
