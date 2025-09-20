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
 * Date  : 2025. 9. 19. 오후 2:52:46
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.exception;

import javax.annotation.Nonnull;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Bean;

/**
 * 동일한 유형의 설정을 병합하여 하나의 {@link Bean} 데이터를 생성하는 도중 발생하는 예외 상황.
 * 
 * @since 2025. 9. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class BeanMergeFailedException extends BeansException {

    private static final long serialVersionUID = 1L;

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
     * @param format
     * @param configurationType
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public BeanMergeFailedException(@Nonnull String format, @Nonnull Class<?> configurationType) {
        this(format, configurationType, null);
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
     * @param format
     * @param configurationType
     * @param cause
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public BeanMergeFailedException(@Nonnull String format, @Nonnull Class<?> configurationType, Throwable cause) {
        super(String.format(format, configurationType.getName()), cause);
    }
}
