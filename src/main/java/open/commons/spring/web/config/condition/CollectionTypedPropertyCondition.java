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
 * Date  : 2025. 8. 8. 오후 3:59:04
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.config.condition;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import open.commons.core.utils.CollectionUtils;

/**
 * 
 * @since 2025. 8. 8.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class CollectionTypedPropertyCondition<E> extends AbstractPropertyCondition<Collection<E>> {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 8.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param prefix
     * @param bindable
     *
     * @since 2025. 8. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public CollectionTypedPropertyCondition(@NotNull @Nonnull String prefix) {
        super(prefix);
    }

    /**
     *
     * @since 2025. 8. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.config.condition.AbstractPropertyCondition#validate(java.lang.Object)
     */
    @Override
    protected boolean validate(Collection<E> bound) {
        return !CollectionUtils.isNullOrEmpty(bound);
    }
}
