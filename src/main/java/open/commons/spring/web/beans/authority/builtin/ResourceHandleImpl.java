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

package open.commons.spring.web.beans.authority.builtin;

import java.util.function.Function;

import javax.annotation.Nonnull;

import open.commons.core.utils.AssertUtils2;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;

/**
 * @since 2025. 9. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ResourceHandleImpl implements ResourceHandle {
    /** 데이터 유형 */
    private final Target target;

    /**
     * 데이터 처리 식별정보.<br>
     * 아래 정보에 매핑됩니다.
     * <li>{@link AuthorizedField#handleType()}
     * <li>{@link AuthorizedRequestData#handleType()}
     */
    private final int handleType;

    /**
     * 데이터 처리 함수. 아래 정보에서 사용됩니다.
     * <li>{@link IUnauthorizedFieldHandler#handleObject(int, Object)}
     * <li>{@link IAuthorizedRequestDataHandler#restoreValue(int, Object)}
     */
    private final Function<?, ?> handle;

    /** 우선 적용 여부 */
    private final boolean preemptive;

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
    public ResourceHandleImpl(@Nonnull Target target, int handleType, @Nonnull Function<?, ?> handle) {
        this(target, handleType, handle, false);
    }

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
     * @param target
     *            데이터 유형
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param handle
     *            데이터 처리 함수
     * @param preemtive
     *            우선적용 여부
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ResourceHandleImpl(@Nonnull Target target, int handleType, @Nonnull Function<?, ?> handle, boolean preemtive) {
        AssertUtils2.notNulls(target, handle);
        this.target = target;
        this.handleType = handleType;
        this.handle = handle;
        this.preemptive = preemtive;
    }

    /**
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.builtin.ResourceHandle#handle()
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
     * @see open.commons.spring.web.beans.authority.builtin.ResourceHandle#handleType()
     */
    @Override
    public int handleType() {
        return this.handleType;
    }

    /**
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.builtin.ResourceHandle#preemptive()
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
     * @see open.commons.spring.web.beans.authority.builtin.ResourceHandle#target()
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
