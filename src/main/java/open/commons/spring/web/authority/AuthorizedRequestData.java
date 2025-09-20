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
 * Date  : 2025. 9. 18. 오후 12:40:01
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.authority;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Bean;

import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.beans.authority.builtin.AuthorizedResourceHandler;
import open.commons.spring.web.beans.authority.builtin.ResourceHandle;
import open.commons.spring.web.config.AuthorizedHandles;

/**
 * {@link AuthorizedField}를 통해서 처리된 데이터라는 것을 선언하는 어노테이션.
 * 
 * @since 2025. 9. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Documented
@Inherited
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizedRequestData {

    /** 데이터 처리 방식을 설정하지 않은 값. */
    public static final int NO_ASSINGED_HANDLE_TYPE = Integer.MIN_VALUE;

    /**
     * {@link AuthorizedField#fieldHandleBean()}를 통해서 처리된 데이터를 원복하는 기능을 제공하는 {@link Bean} 이름. <br>
     * 설정되는 {@link Bean}은 반드시 {@link IAuthorizedRequestDataHandler}를 구현해야 합니다.<br>
     * 별도로 설정하지 않는 경우 기본값 {@link AuthorizedHandles}이 적용됩니다.<br>
     * 이 경우 {@link #handleType()}에 사용하는 값으 {@link AuthorizedHandles}에서 제공하는 값을 사용하거나 {@link ResourceHandle}를 추가 등록해서 사용할
     * 수 있습니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 18.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    String handleBean() default AuthorizedResourceHandler.BEAN_QUALIFIER;

    /**
     * {@link AuthorizedField#handleType()} 방식으로 처리된 데이터를 원복하는 방식.<br>
     * 구현할 때는 동일한 값으로 '원복'하는 방법을 설정하는 것을 추천합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 18.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    int handleType() default NO_ASSINGED_HANDLE_TYPE;

}
