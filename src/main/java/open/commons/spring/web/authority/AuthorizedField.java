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
 * Date  : 2025. 5. 16. 오후 3:34:09
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

import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.beans.authority.internal.AuthorizedHandles;
import open.commons.spring.web.beans.authority.internal.AuthorizedResourceHandler;
import open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleJudge;
import open.commons.spring.web.config.ResourceHandle;

/**
 * 필드값을 사용하기 위한 접근 권한을 정의하는 클래스.<br>
 * 
 * @since 2025. 5. 16.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Documented
@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizedField {

    /** 데이터 처리 방식을 설정하지 않은 값. */
    public static final int NO_ASSINGED_HANDLE_TYPE = Integer.MIN_VALUE;

    /**
     * 접근권한을 검증하는 Bean 정보를 설정합니다. <br>
     * 설정되는 Bean은 반드시 {@link IFieldAccessAuthorityProvider}를 구현해야 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 16.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see IFieldAccessAuthorityProvider
     */
    String authorityBean() default ForcedUnintelligibleJudge.BEAN_QUALIFIER;

    /**
     * 데이터 유형 설명 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 26.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    String descr() default "";

    /**
     * 권한제어 기능을 제공하는 Bean 이름(식별정보)을 설정합니다. <br>
     * 설정되는 {@link Bean}은 반드시 {@link IUnauthorizedFieldHandler}를 구현해야 합니다.<br>
     * 별도로 설정하지 않는 경우 기본값 {@link AuthorizedHandles}이 적용됩니다.<br>
     * 이 경우 {@link #handleType()}에 사용하는 값으 {@link AuthorizedHandles}에서 제공하는 값을 사용하거나 {@link ResourceHandle}를 추가 등록해서 사용할
     * 수 있습니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 26.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    String fieldHandleBean() default AuthorizedResourceHandler.BEAN_QUALIFIER;

    /**
     * 데이터 처리 방식 <br>
     * 데이터 유형(class)와 필드(field)에 값에 따라 {@link #authorityBean()}의 결과를 이용하지 않고, 강제적으로 처리 방식을 설정할 때 이용합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 13.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    int handleType() default NO_ASSINGED_HANDLE_TYPE;

    /**
     * 데이터 유형 이름<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 26.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    String name() default "";
}
