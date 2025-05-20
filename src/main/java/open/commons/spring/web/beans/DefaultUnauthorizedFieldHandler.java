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
 * Date  : 2025. 5. 20. 오전 11:35:41
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans;

import java.lang.reflect.Field;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import open.commons.core.utils.ReflectionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.ac.AuthorizedField;
import open.commons.spring.web.ac.AuthorizedField.Masking;
import open.commons.spring.web.servlet.UnauthorizedException;

/**
 * {@link AuthorizedField}가 설정된 필드 데이터 권한 후처리 서비스.
 * 
 * @since 2025. 5. 20.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class DefaultUnauthorizedFieldHandler implements IUnauthorizedFieldHandler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final ApplicationContext context;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public DefaultUnauthorizedFieldHandler(@NotNull ApplicationContext context) {
        this.context = context;
    }

    /**
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.IUnauthorizedFieldHandler#deny(java.lang.Object, java.lang.reflect.Field)
     */
    @Override
    public void deny(@NotNull Object o, @NotNull Field field) {
        throw new UnauthorizedException("올바르지 않은 접근입니다.");
    }

    /**
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.IUnauthorizedFieldHandler#mask(java.lang.Object, java.lang.reflect.Field,
     *      Masking)
     */
    @Override
    public void mask(@NotNull Object o, @NotNull Field field, Masking mask)
            throws IllegalArgumentException, IllegalAccessException, SecurityException, NullPointerException, ExceptionInInitializerError {
        Object newValue = null;
        if (String.class == field.getType()) {
            boolean assessible = field.isAccessible();
            try {
                field.setAccessible(true);
                newValue = maskString((String) field.get(o), mask);
                field.set(o, newValue);
            } finally {
                field.setAccessible(assessible);
            }
        } else {
            maskOthers(o, field);
        }
    }

    /**
     * 필드값이 {@link String} 유혀잉 아닌 경우 처리를 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param o
     * @param field
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NullPointerException
     * @throws ExceptionInInitializerError
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected void maskOthers(@NotNull Object o, @NotNull Field field)
            throws IllegalArgumentException, IllegalAccessException, SecurityException, NullPointerException, ExceptionInInitializerError {
        ReflectionUtils.resetFieldForced(o, field);
    }

    /**
     * 문자열에 대한 마스킹 처리를합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param string
     *            마스킹할 문자열
     * @param mask
     *            마스킹 정보.
     * @return
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected String maskString(@NotNull String string, Masking mask) {
        if (string == null) {
            return null;
        }

        // Mask 검증
        Assert.isTrue(Masking.validate.apply(mask), String.format("마스킹 설정이 올바르지 않습니다. 설정=%s", mask));

        int len = string.length();
        // #1. 길이가 짧은 경우
        if (len < mask.min()) {
            return StringUtils.nTimesString(String.valueOf(mask.sign()), (int) Math.ceil(mask.min() * mask.padding()));
        } else {
            int prefix = (int) Math.ceil(len * mask.prefix());
            int suffix = (int) Math.ceil(len * mask.suffix());
            if (prefix + suffix >= len) {
                prefix -= 1;
            }
            int keep = len - (prefix + suffix);

            StringBuilder builder = new StringBuilder();
            builder.append(StringUtils.nTimesString(String.valueOf(mask.sign()), (int) Math.ceil(prefix * mask.padding())));
            builder.append(string.substring(prefix, prefix + keep));
            builder.append(StringUtils.nTimesString(String.valueOf(mask.sign()), (int) Math.ceil(suffix * mask.padding())));

            return builder.toString();
        }
    }

    /**
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.IUnauthorizedFieldHandler#nullify(java.lang.Object, java.lang.reflect.Field)
     */
    @Override
    public void nullify(@NotNull Object o, @NotNull Field field) throws IllegalArgumentException, IllegalAccessException {
        try {
            ReflectionUtils.resetFieldForced(o, field);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            logger.warn("필드 초기화 도중에 오류가 발생하였습니다. 객체={}, 필드={}, 원인={}", o, field, ex.getMessage(), ex);
            throw ex;
        }
    }
}
