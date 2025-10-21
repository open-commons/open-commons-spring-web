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
 * Date  : 2025. 6. 12. 오전 10:43:31
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority.internal;

import java.lang.reflect.Field;

import javax.validation.constraints.NotEmpty;

import open.commons.core.Result;
import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.beans.authority.FieldAccessAuthorityDecision;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler.ForcedUnintelligibleHandleType;
import open.commons.spring.web.servlet.InternalServerException;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ForcedUnintelligibleJudge implements IFieldAccessAuthorityProvider {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleJudge";

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ForcedUnintelligibleJudge() {
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider#isAllowed(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Result<FieldAccessAuthorityDecision> isAllowed(@NotEmpty String type, @NotEmpty String fieldName) {
        try {
            Class<?> typeClass = Class.forName(type);
            Field field = typeClass.getDeclaredField(fieldName);
            Class<?> fieldClass = field.getType();

            return Result.success(new FieldAccessAuthorityDecision(false, ForcedUnintelligibleHandleType.find(fieldClass), ForcedUnintelligibleHandler.BEAN_QUALIFIER));
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
            throw ExceptionUtils.newException(InternalServerException.class, e, "필드유형에 따른 처리 방식을 결정하는 도중에 오류가 발생하였습니다. 원인=%s", e.getMessage());
        }
    }
}
