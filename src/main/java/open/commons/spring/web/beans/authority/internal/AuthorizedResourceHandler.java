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
 * Date  : 2025. 9. 19. 오전 10:18:59
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority.internal;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.config.ResourceHandle;
import open.commons.spring.web.config.ResourceHandle.Target;

/**
 * {@link IUnauthorizedFieldHandler}, {@link IAuthorizedRequestDataHandler}를 하나의 서비스로 통합하여 제공하는 내부 컴포넌트
 * 
 * @since 2025. 9. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourceHandler implements IUnauthorizedFieldHandler, IAuthorizedRequestDataHandler {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.beans.authority.internal.AuthorizedResourceHandler";

    /** 암호화/난독화 기능을 수행 */
    private ConcurrentHashMap<Integer, Function<?, ?>> unauthorizedFieldHandlers = new ConcurrentHashMap<>();
    /** 복호화/평문화 기능을 수행 */
    private ConcurrentHashMap<Integer, Function<?, ?>> authorizedDataHandlers = new ConcurrentHashMap<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 19.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedResourceHandler() {
    }

    /**
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler#handleObject(int, java.lang.Object)
     */
    @Override
    public Object handleObject(int handle, Object data) throws UnsupportedOperationException {
        @SuppressWarnings("unchecked")
        Function<Object, Object> handler = (Function<Object, Object>) this.unauthorizedFieldHandlers.get(handle);
        if (handler == null) {
            throw ExceptionUtils.newException(UnsupportedOperationException.class, "전달받은 핸들타입(%s)에 해당하는 기능이 존재하지 않습니다.", handle);
        }
        return handler.apply(data);
    }

    /**
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler#restoreValue(int, java.lang.Object)
     */
    @Override
    public Object restoreValue(int handle, Object value) throws UnsupportedOperationException {
        @SuppressWarnings("unchecked")
        Function<Object, Object> handler = (Function<Object, Object>) this.authorizedDataHandlers.get(handle);
        if (handler == null) {
            throw ExceptionUtils.newException(UnsupportedOperationException.class, "전달받은 핸들타입(%s)에 해당하는 기능이 존재하지 않습니다.", handle);
        }
        return handler.apply(value);
    }

    /**
     * 데이터 (암호화/난독화), (복호화/평문화)기능을 설정합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param handlers
     *            데이터 처리방식별 기능
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see AuthorizedRequestData
     * @see IAuthorizedRequestDataHandler
     * @see Target#AUTHORIZED
     * 
     * @see AuthorizedField
     * @see IUnauthorizedFieldHandler
     * @see Target#UNAUTHORIZED
     */
//    @auto
    public void setAuthorizedResourceHandlers(@NotNull @Nonnull Collection<ResourceHandle> handlers) {
        handlers.forEach(h -> {
            if (Target.AUTHORIZED == h.target()) {
                this.authorizedDataHandlers.put(h.handleType(), h.handle());
            } else if (Target.UNAUTHORIZED == h.target()) {
                this.unauthorizedFieldHandlers.put(h.handleType(), h.handle());
            }
        });
    }
}
