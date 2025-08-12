/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 7. 5. 오후 6:57:18
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.validation;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;
import java.util.function.BiFunction;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 사용자 정의 제한조건 검증 추상 클래스.<br>
 * 
 * 사용법 참조
 * <ul>
 * <li>https://meetup.toast.com/posts/223
 * <li>https://jongmin92.github.io/2019/11/21/Spring/bean-validation-2/#Validation-Groups
 * </ul>
 * 
 * @since 2021. 7. 5.
 * @version 0.3.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public abstract class CustomConstraintValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected Validator validator;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param validator
     *
     * @since 2021. 7. 5.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public CustomConstraintValidator(Validator validator) {
        super();
        this.validator = validator;
    }

    /**
     * 제한조건 위반 메시지를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     * @param v
     *
     * @since 2021. 7. 5.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected void buildViolationMessage(ConstraintValidatorContext context, ConstraintViolation<T> v) {
        context.buildConstraintViolationWithTemplate(v.getMessageTemplate()) //
                .addPropertyNode(v.getPropertyPath().toString()) //
                .addConstraintViolation();
    }

    /**
     * 제한조건을 위반한 정보를 제공한다. #groups 클래스에 해당하는 제한조건 대상이 1개인 경우 사용한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 7. 5.      박준홍         최초 작성
     * </pre>
     *
     * @param value
     * @param groups
     * @return
     *
     * @since 2021. 7. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected ConstraintViolation<T> getViolation(T value, Class<?>... groups) {
        Set<ConstraintViolation<T>> vs = validator.validate(value, groups);
        if (vs.isEmpty()) {
            return null;
        } else {
            return vs.iterator().next();
        }
    }

    /**
     * 제한조건을 위반한 정보를 제공한다. #groups 클래스에 해당하는 제한조건 대상이 1개 또는 그 이상인 경우 사용한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 7. 5.      박준홍         최초 작성
     * </pre>
     *
     * @param value
     * @param groups
     * @return
     *
     * @since 2021. 7. 5.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected Set<ConstraintViolation<T>> getViolations(T value, Class<?>... groups) {

        logger.debug("value: {}, groups:{}", value, groups != null ? Arrays.toString(groups) : null);

        Set<ConstraintViolation<T>> vs = validator.validate(value, groups);
        if (vs.isEmpty()) {
            return null;
        } else {
            return vs;
        }
    }

    /**
     * 1개 이상의 값에 대해서 검증을 하고 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     * @param provider
     * @param value
     * @param groups
     * @return
     *
     * @since 2021. 7. 5.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected boolean validateMultiNodes(ConstraintValidatorContext context, BiFunction<T, Class<?>[], Set<ConstraintViolation<T>>> provider, T value, Class<?>... groups) {

        logger.debug("value: {}, groups:{}", value, groups != null ? Arrays.toString(groups) : null);

        Set<ConstraintViolation<T>> vs = provider.apply(value, groups);
        if (vs != null) {
            context.disableDefaultConstraintViolation();
            vs.forEach(v -> buildViolationMessage(context, v));
            return false;
        }
        return true;
    }

    /**
     * 1개 값에 대해서 검증을 하고 결과를 제공한다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 7. 5.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     * @param provider
     * @param value
     * @param groups
     * @return
     *
     * @since 2021. 7. 5.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected boolean validateSingleNode(ConstraintValidatorContext context, BiFunction<T, Class<?>[], ConstraintViolation<T>> provider, T value, Class<?>... groups) {

        logger.debug("value: {}, groups:{}", value, groups != null ? Arrays.toString(groups) : null);

        ConstraintViolation<T> v = provider.apply(value, groups);
        if (v != null) {
            context.disableDefaultConstraintViolation();
            buildViolationMessage(context, v);
            return false;
        }
        return true;
    }
}
