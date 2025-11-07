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
 * Date  : 2025. 8. 20. 오전 10:28:06
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet.filter.header;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

import javax.validation.constraints.NotNull;

/**
 * 'frond-end' 또는 외부에서 전달한 'header' 정보 중에 공유하기 위한 설정 기능.
 * 
 * @since 2025. 8. 20.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class DefaultSharedHeader implements SharedHeader {

    /** 헤더 이름 */
    protected final String header;
    /** 헤더 값 검증 */
    protected final BiPredicate<String, String> validator;
    /** 후처리 작업 */
    protected final BiConsumer<String, String> postAction;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param header
     * @param validator
     *
     * @since 2025. 8. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public DefaultSharedHeader(String headerName, BiPredicate<String, String> validator) {
        this(headerName, validator, (name, value) -> {
        });
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param header
     * @param validator
     * @param postAction
     *
     * @since 2025. 11. 7.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public DefaultSharedHeader(String header, BiPredicate<String, String> validator, BiConsumer<String, String> postAction) {
        this.header = header;
        this.validator = validator;
        this.postAction = postAction;
    }

    /**
     *
     * @since 2025. 8. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.servlet.filter.header.SharedHeader#header()
     */
    @Override
    public String header() {
        return this.header;
    }

    /**
     *
     * @since 2025. 11. 7.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see open.commons.spring.web.servlet.filter.header.SharedHeader#postAction()
     */
    @Override
    @NotNull
    public BiConsumer<String, String> postAction() {
        return this.postAction;
    }

    /**
     *
     * @since 2025. 8. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DefaultSharedHeader [header=");
        builder.append(header);
        builder.append(", validator=");
        builder.append(validator);
        builder.append("]");
        return builder.toString();
    }

    /**
     *
     * @since 2025. 8. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.servlet.filter.header.SharedHeader#validator()
     */
    @Override
    @NotNull
    public BiPredicate<String, String> validator() {
        return this.validator;
    }
}
