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
 * Date  : 2025. 6. 24. 오전 10:26:02
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.thread;

import open.commons.core.lang.IThreadLocalContext;
import open.commons.core.lang.ThreadLocalContextService;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.authority.metadata.AuthorizedFieldMetadata;
import open.commons.spring.web.authority.metadata.AuthorizedObjectMetadata;

/**
 * 
 * @since 2025. 6. 24.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourceContext {

    /** Http 요청시 일시적으로 '권한기반 자원 제어'를 해제하는 요청 정보 헤더 */
    public static final String DISABLE_AUTHORIZED_RESOURCES = "X-Disable-Authorized-Resources";

    private static final IThreadLocalContext CONTEXT = ThreadLocalContextService.context(AuthorizedResourceContext.class);

    private AuthorizedResourceContext() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 24.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static void clear() {
        CONTEXT.clear();
    }

    /**
     * 권한기반 데이터 제어(Authorized-Resources)를 비활성화 여부를 반환합니다.<br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 24.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static boolean isDisableAuthentication() {
        return !isEnableAuthentication();
    }

    /**
     * 권한기반 데이터 제어(Authorized-Resources)를 활성화 여부를 반환합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 24.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static boolean isEnableAuthentication() {
        return CONTEXT.containsNot(DISABLE_AUTHORIZED_RESOURCES) || !(boolean) CONTEXT.get(DISABLE_AUTHORIZED_RESOURCES);
    }

    /**
     * 권한기반 데이터 제어(Authorized-Resources)를 비활성화 시킵니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 24.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 24.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see AuthorizedObject
     * @see AuthorizedObjectMetadata
     * @see AuthorizedField
     * @see AuthorizedFieldMetadata
     */
    public static void setDisableAuthentication() {
        CONTEXT.set(DISABLE_AUTHORIZED_RESOURCES, true);
    }
}
