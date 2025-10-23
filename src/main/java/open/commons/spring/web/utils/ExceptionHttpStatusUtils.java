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
 * Date  : 2025. 10. 22. 오후 4:56:21
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.utils;

import javax.annotation.Nonnull;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import open.commons.core.utils.AssertUtils2;
import open.commons.spring.web.servlet.binder.ExceptionHttpStatusBinder;

/**
 * 발생한 예외 클래스(<code> ? extends {@link Throwable})과 {@link HttpStatus} 매핑에 관한 기능을 제공
 * 
 * @since 2025. 10. 22.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class ExceptionHttpStatusUtils {
    private ExceptionHttpStatusUtils() {
    }

    /**
     * 예외 클래스에 선언된 {@link HttpStatus} 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 28.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     * 
     * @param binder
     * 
     * @param ex
     *            예외클래스.
     * @param defaultStatus
     *            설정된 {@link HttpStatus} 정보가 없는 경우 제공될 기본 {@link HttpStatus}
     *
     * @return
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see ResponseStatus
     */
    public static HttpStatus resolveResponseStatus(ExceptionHttpStatusBinder binder, @Nonnull Exception ex, @Nonnull HttpStatus defaultStatus) {
        AssertUtils2.notNulls(ex, defaultStatus);

        HttpStatus status = binder != null ? binder.resolveHttpStatus(ex.getClass(), defaultStatus) : null;
        if (status != null) {
            return status;
        } else {
            ResponseStatus annotatedStatus = AnnotatedElementUtils.findMergedAnnotation(ex.getClass(), ResponseStatus.class);
            return annotatedStatus != null ? annotatedStatus.code() : defaultStatus;
        }
    }

}
