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
 * Date  : 2021. 12. 16. 오후 2:14:43
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Validator;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.servlet.InternalServerException;

/**
 * 
 * 아래 속성을 제공하는 {@link Annotation}을 검증하는 클래스.
 * 
 * <ul>
 * <li>T[] allowed()
 * <li>T[] disallowed()
 * <li>boolean emptyIsAllowed()
 * </ul>
 * 
 * @since 2021. 12. 16.
 * @version 0.4.0
 * @author parkjunhong77@gmail.com
 */
public class EnumConstraintValidator<C extends Annotation, T extends Enum<T>> extends CustomConstraintValidator<C, T> {

    protected Set<T> allowed = new HashSet<>();
    protected Set<T> disallowed = new HashSet<>();
    protected boolean emptyIsAllowed = true;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 16.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2021. 12. 16.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    public EnumConstraintValidator(Validator validator) {
        super(validator);
    }

    /**
     * 제한조건 객체에서 주어진 속성값(#method)을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param <R>
     * @param constraint
     * @param method
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     *
     * @since 2021. 12. 16.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    private <R> R getProperty(C constraint, String method)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method m = constraint.getClass().getMethod(method);
        return (R) m.invoke(constraint);
    }

    /**
     *
     * @since 2021. 12. 16.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     *
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(C constraint) {
        super.initialize(constraint);
        try {
            // method#1: T[] allowed()
            this.allowed.addAll(Arrays.asList(getProperty(constraint, "allowed")));
            // method#2: T[] disallowed()
            this.disallowed.addAll(Arrays.asList(getProperty(constraint, "disallowed")));
            // method#3: boolean emptyIsAllowed()
            this.emptyIsAllowed = getProperty(constraint, "emptyIsAllowed");
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            String errMsg = String.format("'%s' 속성 정보를 읽는 도정 에러가 발생하였습니다. 원인=%s", constraint.getClass(), e.getMessage());
            logger.error(errMsg, e);
            throw ExceptionUtils.newException(InternalServerException.class, errMsg);
        }
    }

    /**
     *
     * @since 2021. 12. 16.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     *
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(T value, ConstraintValidatorContext context) {
        // #1. 허용 목록 검증.
        if (this.allowed.size() > 0) {
            return this.allowed.contains(value);
        } else
        // #2. 불허용 목록 검증.
        if (this.disallowed.size() > 0) {
            return !this.disallowed.contains(value);
        } else {
            return this.emptyIsAllowed;
        }
    }

}
