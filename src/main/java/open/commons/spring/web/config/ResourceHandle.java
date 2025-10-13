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
 * Date  : 2025. 9. 19. 오전 10:59:02
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.config;

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.beans.authority.internal.AuthorizedHandles;

/**
 * 데이터 처리(암호화/난독화 <==> 복호화/평문화)에 사용되는 기능을 정의합니다.<br>
 * 객체는 {@link AuthorizedHandles#createResourceHandle(Target, String, Function)},
 * {@link AuthorizedHandles#createResourceHandle(boolean, Target, String, Function, boolean)}를 이용해서 생성을 합니다.<br>
 * 내부적으로 중복 확인에 대한 기능을 제공합니다.
 * 
 * @since 2025. 9. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface ResourceHandle {

    /**
     * 데이터 처리 함수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @NotNull
    @Nonnull
    public Function<?, ?> handle();

    /**
     * 데이터 처리 식별정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @NotEmpty
    @Nonnull
    public String handleType();

    /**
     * 데이터 처리방식 식별정보가 동일한 경우 우선 적용할지 여부. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public boolean preemptive();

    /**
     * 적용할 대상 유형 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @NotNull
    @Nonnull
    public Target target();

    /** 데이터 적용 대상 유형 정보 */
    public static enum Target {
        /**
         * '권한제어' 적용 대상<br>
         * 
         * @see {@link AuthorizedField}
         * @see {@link IUnauthorizedFieldHandler}
         */
        UNAUTHORIZED,
        /**
         * '권한제어' 해제 대상
         * 
         * @see {@link AuthorizedRequestData}
         * @see {@link IAuthorizedRequestDataHandler}
         */
        AUTHORIZED,
    }

}
