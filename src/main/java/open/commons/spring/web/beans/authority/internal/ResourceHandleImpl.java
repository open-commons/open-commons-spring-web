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
 * Date  : 2025. 9. 19. 오전 11:00:49
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority.internal;

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotEmpty;

import open.commons.core.utils.AssertUtils2;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.config.ResourceHandle;

/**
 * @since 2025. 9. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ResourceHandleImpl implements ResourceHandle {

    /** 내부에서 제공되는 설정인지 여부 */
    private boolean isBuiltin;

    /** 데이터 유형 */
    private final Target target;

    /**
     * 데이터 처리 식별정보.<br>
     * 아래 정보에 매핑됩니다.
     * <li>{@link AuthorizedField#handleType()}
     * <li>{@link AuthorizedRequestData#handleType()}
     */
    private final String handleType;

    /**
     * 데이터 처리 함수. 아래 정보에서 사용됩니다.
     * <li>{@link IUnauthorizedFieldHandler#handleObject(String, Object)}
     * <li>{@link IAuthorizedRequestDataHandler#restoreValue(String, Object)}
     */
    private final Function<?, ?> handle;

    /** 우선 적용 여부 */
    private final boolean preemptive;

    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param isBuiltin
     *            내부에서 제공되는 설정인지 여부
     * @param target
     *            데이터 유형
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param handle
     *            데이터 처리 함수
     * @param preemptive
     *            우선적용 여부
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    ResourceHandleImpl(boolean isBuiltin, @Nonnull Target target, @NotEmpty @Nonnull String handleType, @Nonnull Function<?, ?> handle, boolean preemptive) {
        AssertUtils2.notNulls(target, handle);
        this.isBuiltin = isBuiltin;
        this.target = target;
        this.handleType = handleType;
        this.handle = handle;
        this.preemptive = preemptive;
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 19.		박준홍			최초 작성
     * </pre>
     * 
     * @param target
     *            데이터 유형
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param handle
     *            데이터 처리 함수
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    ResourceHandleImpl(@Nonnull Target target, @NotEmpty @Nonnull String handleType, @Nonnull Function<?, ?> handle) {
        this(false, target, handleType, handle, false);
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param target
     *            데이터 유형
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param handle
     *            데이터 처리 함수
     * @param preemptive
     *            우선적용 여부
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ResourceHandleImpl(@Nonnull Target target, @NotEmpty @Nonnull String handleType, @Nonnull Function<?, ?> handle, boolean preemptive) {
        this(false, target, handleType, handle, preemptive);
    }

    /**
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.config.ResourceHandle#handle()
     */
    @Override
    public Function<?, ?> handle() {
        return this.handle;
    }

    /**
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.config.ResourceHandle#handleType()
     */
    @Override
    @NotEmpty
    @Nonnull
    public String handleType() {
        return this.handleType;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     * 
     * @return the isBuiltin
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #isBuiltin
     */

    public final boolean isBuiltin() {
        return isBuiltin;
    }

    /**
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.config.ResourceHandle#preemptive()
     */
    @Override
    public boolean preemptive() {
        return this.preemptive;
    }

    /**
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.config.ResourceHandle#target()
     */
    @Override
    public Target target() {
        return this.target;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ResourceHandleImpl [target=");
        builder.append(target);
        builder.append(", handleType=");
        builder.append(handleType);
        builder.append(", handle=");
        builder.append(handle);
        builder.append("]");
        return builder.toString();
    }
}
