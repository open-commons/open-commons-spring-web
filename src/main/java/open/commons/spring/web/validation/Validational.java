/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2019. 10. 10. 오후 2:21:43
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import open.commons.spring.web.utils.ValidationUtils;

/**
 * 데이터 검증을 지원하는 클래스.
 * 
 * @since 2019. 10. 10.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @see Validation
 * @see Validator
 * @see ValidatorFactory
 * @see ValidationUtils
 */
public abstract class Validational<C extends List<E>, E extends Validational<List<E>, E>> {

    /**
     * 여러 개의 데이터 검증 오류 결과를 합친다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param buf
     * @param errors
     * @param index
     * @param builder
     *
     * @since 2019. 10. 10.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private void aggregate(Collection<String> buf, Collection<String> errors, int index, StringBuffer builder) {
        for (String error : errors) {
            builder.append('[');
            builder.append(index);
            builder.append("] ");
            builder.append(error);

            buf.add(builder.toString());

            builder.setLength(0);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 10.        박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2019. 10. 10.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public abstract C getSubObjects();

    /**
     * 데이터 검증한 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2019. 10. 10.        박준홍         최초 작성
     * </pre>
     *
     * @param validator
     *            데이터 검증기
     * @return 오류가 없는 경우 null을 제공한다.
     *
     * @since 2019. 10. 10.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public Collection<String> validate(Function<Object, Collection<String>> validator) {

        Collection<String> errors = validator.apply(this);

        C subObjects = getSubObjects();
        if (subObjects == null || subObjects.isEmpty()) {
            return errors;
        }

        if (errors != null) {
            errors = new ArrayList<>(errors);
        }

        Collection<String> subErrors = null;
        int i = 0;
        StringBuffer builder = new StringBuffer();
        for (E target : subObjects) {
            try {
                subErrors = target.validate(validator);
                if (subErrors == null) {
                    continue;
                }

                if (errors == null) {
                    errors = new ArrayList<>();
                }

                builder.setLength(0);
                aggregate(errors, subErrors, i, builder);
            } finally {
                i++;
            }
        }

        return errors;
    }

}
