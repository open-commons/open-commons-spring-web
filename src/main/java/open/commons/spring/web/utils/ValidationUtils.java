/*
 * Copyright 2019 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Date  : 2019. 10. 8. 오후 1:26:57
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.spring.web.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * {@link Validator} 기능 지원 클래스.
 * 
 * @since 2019. 6. 18.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class ValidationUtils {

    private static final Validator validator;
    static {
        ValidatorFactory vfactory = Validation.buildDefaultValidatorFactory();
        validator = vfactory.getValidator();
    }

    // prevent to be created
    private ValidationUtils() {
    }

    private static <T> Collection<String> makeErrorMessage(Set<ConstraintViolation<T>> violations) {
        if (violations.size() < 1) {
            return null;
        }

        ArrayList<String> msg = new ArrayList<>();
        violations.forEach(v -> {
            msg.add(String.join(" => ", String.join(".", v.getRootBeanClass().getCanonicalName(), v.getPropertyPath().toString()), v.getMessage()));
        });

        return msg;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     박준홍         최초 작성
     * </pre>
     *
     * @param object
     * @param groups
     * @return
     *
     * @since 2019. 6. 18.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     * @see Validator#validate(Object, Class...)
     */
    public static <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        return validator.validate(object);
    }

    /**
     * 객체를 검증한 후 검증통과 실패 원인을 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     박준홍         최초 작성
     * </pre>
     *
     * @param object
     * @param groups
     * @return 검증통과 실패 원인. 검증에 통과한 경우 null 반환.
     *
     * @since 2019. 6. 18.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see Validator#validate(Object, Class...)
     */
    public static <T> Collection<String> validateAndErrorMsg(T object, Class<?>... groups) {
        return makeErrorMessage(validate(object, groups));

    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     박준홍         최초 작성
     * </pre>
     *
     * @param object
     * @param propertyName
     * @param groups
     * @return 검증통과 실패 원인. 검증에 통과한 경우 null 반환.
     *
     * @since 2019. 6. 18.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see Validator#validateProperty(Object, String, Class...)
     */
    public static <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
        return validator.validateProperty(object, propertyName, groups);
    }

    /**
     * 객체를 검증한 후 검증통과 실패 원인을 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     박준홍         최초 작성
     * </pre>
     *
     * @param object
     * @param propertyName
     * @param groups
     * @return 검증통과 실패 원인. 검증에 통과한 경우 null 반환.
     * 
     * @since 2019. 6. 18.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see Validator#validateProperty(Object, String, Class...)
     */
    public static <T> Collection<String> validatePropertyAndErrorMsg(T object, String propertyName, Class<?>... groups) {
        return makeErrorMessage(validateProperty(object, propertyName, groups));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     박준홍         최초 작성
     * </pre>
     *
     * @param beanType
     * @param propertyName
     * @param value
     * @param groups
     * @return
     *
     * @since 2019. 6. 18.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see Validator#validateValue(Class, String, Object, Class...)
     */
    public static <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        return validator.validateValue(beanType, propertyName, value, groups);
    }

    /**
     * 
     * 객체를 검증한 후 검증통과 실패 원인을 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 6. 18.     박준홍         최초 작성
     * </pre>
     *
     * @param beanType
     * @param propertyName
     * @param value
     * @param groups
     * @return 검증통과 실패 원인. 검증에 통과한 경우 null 반환.
     *
     * @since 2019. 6. 18.
     * @version
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @see Validator#validateValue(Class, String, Object, Class...)
     */
    public static <T> Collection<String> validateValueAndErrorMsg(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        return makeErrorMessage(validateValue(beanType, propertyName, value, groups));
    }
}

