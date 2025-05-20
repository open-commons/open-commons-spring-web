/*
 * Copyright 2025 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2025. 5. 20. 오후 4:41:27
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans;

import java.lang.reflect.Field;

import open.commons.spring.web.ac.AuthorizedField;
import open.commons.spring.web.ac.AuthorizedField.Masking;
import open.commons.spring.web.ac.AuthorizedField.Mode;

/**
 * {@link AuthorizedField#mode()} 처리를 하는 기능 정의.
 * 
 * @since 2025. 5. 20.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IUnauthorizedFieldHandler {

    /**
     * {@link AuthorizedField#mode()} 값이 {@link Mode#DENY}인 경우에 대한 처리를 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param o
     *            객체
     * @param field
     *            필드
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    void deny(Object o, Field field);

    /**
     * {@link AuthorizedField#mode()} 값이 {@link Mode#MASK}인 경우에 대한 처리를 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
     * </pre>
     * 
     * 
     * @param o
     *            객체
     * @param field
     *            필드
     * @param mask
     *            마스킹 설정
     *
     * @return
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * @throws ExceptionInInitializerError
     * @throws NullPointerException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    void mask(Object o, Field field, Masking mask) throws IllegalArgumentException, IllegalAccessException, SecurityException, NullPointerException, ExceptionInInitializerError;

    /**
     * {@link AuthorizedField#mode()} 값이 {@link Mode#NULLIFY}인 경우에 대한 처리를 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param o
     *            객체
     * @param field
     *            필드
     * 
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    void nullify(Object o, Field field) throws IllegalArgumentException, IllegalAccessException;

}