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
 * Date  : 2025. 5. 16. 오후 3:34:09
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.ac;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;

import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import open.commons.spring.web.ac.AuthorizedMethod.Operator;
import open.commons.spring.web.ac.provider.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.DefaultUnauthorizedFieldHandler;
import open.commons.spring.web.beans.IAuthorizedResponseHandler;
import open.commons.spring.web.beans.IUnauthorizedFieldHandler;
import open.commons.spring.web.servlet.UnauthorizedException;

/**
 * 필드값을 사용하기 위한 접근 권한을 정의하는 클래스.<br>
 * 
 * @since 2025. 5. 16.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Documented
@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizedField {

    /**
     * 접근권한을 검증하는 Bean 정보를 설정합니다. <br>
     * 설정되는 Bean은 반드시 {@link IFieldAccessAuthorityProvider}를 구현해야 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 16.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see IFieldAccessAuthorityProvider
     */
    String authorityBean() default "";

    /**
     * 문자열 마스킹 처리시 기본 설정 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Masking masking() default @Masking;

    /**
     * 허용되지 않은 권한인 경우 처리 방식. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 16.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Mode mode() default Mode.NULLIFY;

    /**
     * 허용되지 않는 권한인 경우 데이터를 처리하는 Bean 설정.<br>
     * 설정되는 Bean은 반드시 {@link IUnauthorizedFieldHandler}를 구현하거나 {@link DefaultUnauthorizedFieldHandler}를 상속받아 처리합니다.<br>
     * 빈 문자열인 경우 {@link DefaultUnauthorizedFieldHandler}가 적용됩니다.
     * 
     * <ul>
     * <li>DENY: {@link UnauthorizedException} 발생.
     * <li>MASK: 데이터 타입이 {@link String}인 경우만 데이터 길이에 따라서 문자열 '*' 처리.
     * <li>NULLFY: 데이터 타입이 객체인 경우 null, Primitive 타입인 경우 숫자형 데이터는 최소값, boolean은 false 로 처리.
     * </ul>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 20.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see DefaultUnauthorizedFieldHandler
     */
    String modeHandleBean() default "";

    /**
     * {@link #roles()}의 값이 2개 이상인 경우 처리하는 규칙. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 16.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Operator op() default Operator.OR;

    /**
     * 접근이 허용되는 권한(ROLE_XXX) 코드 목록입니다. <br>
     * 처리정책은 '화이트 리스트' 기반입니다. 즉, 이 값이 비어 있는 경우 모든 접근을 차단합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 16.     박준홍         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see AuthorizationManager
     * @see Authentication
     * @see GrantedAuthority
     */
    String[] roles();

    /**
     * {@link AuthorizedField#mode()}를 타입 수준에서 설정하기 위한 어노테이션.
     * 
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     * 
     * @see AuthorizedField
     * @see IAuthorizedResponseHandler
     */
    @Documented
    @Inherited
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AuthorizedFieldModeHandle {

        /**
         * {@link AuthorizedField}에서 허용되지 않는 권한인 경우 데이터를 처리하는 Bean 설정.<br>
         * 설정되는 Bean은 반드시 {@link IUnauthorizedFieldHandler}를 구현하거나 {@link DefaultUnauthorizedFieldHandler}를 상속받아
         * 처리합니다.<br>
         * 빈 문자열인 경우 {@link DefaultUnauthorizedFieldHandler}가 적용됩니다.
         * <ul>
         * <li>DENY: {@link UnauthorizedException} 발생.
         * <li>MASK: 데이터 타입이 {@link String}인 경우만 데이터 길이에 따라서 문자열 '*' 처리.
         * <li>NULLFY: 데이터 타입이 객체인 경우 null, Primitive 타입인 경우 숫자형 데이터는 최소값, boolean은 false 로 처리.
         * </ul>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 5. 20.     박준홍         최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 5. 20.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         * 
         * @see DefaultUnauthorizedFieldHandler
         */
        String bean();
    }

    /**
     * 문자열 마스킹 처리 설정<br>
     * 
     * 제한조건은 다음과 같습니다.
     * <li>{@value #MIN_MASK_RATIO} <= ( {@link #prefix()} + {@link #suffix()} ) <= {@value #MAX_MASK_RATIO}
     * <li>
     * 
     * 
     * @since 2025. 5. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public @interface Masking {
        static final char DEFAULT_SIGN = '*';
        static final int DEFAULT_MIN = 6;
        static final double DEFAULT_PADDING = 1.0;
        static final double DEFAULT_PREFIX = 0.3;
        static final double DEFAULT_SUFFIX = 0.5;

        static final double MIN_PADDING = 1.0;
        static final double MIN_MASK_RATIO = 0.6;
        static final double MAX_MASK_RATIO = 0.9;

        public static final Function<Masking, Boolean> validate = m -> {
            if (
            // #1. 마스킹 범위 검증
            (m.prefix() + m.suffix() < MIN_MASK_RATIO || m.prefix() + m.suffix() > MAX_MASK_RATIO)
                    // #2. 문자열 최소 길이 설정
                    || m.min() < DEFAULT_MIN
            // #3. 패딩 길이
                    || m.padding() < MIN_PADDING //
            ) {
                return false;
            }
            return true;
        };

        /**
         * 최소 처리 문자열 길이<br>
         * 이 길이보다 작은 경우 모두 마스킹 처리합니다.
         */
        int min() default DEFAULT_MIN;

        /**
         * 마스킹 결과 문자열 고정 길이 비율 <br>
         * 1보다 작은 경우 padding 처리를 하지 않습니다. (1: 입력 문자 길이 유지)
         */
        double padding() default DEFAULT_PADDING;

        /**
         * 앞쪽 마스킹 길이 비율<br>
         * 소수점인 경우 내림처리.
         */
        double prefix() default DEFAULT_PREFIX;

        /** 마스킹 사용 문자 */
        char sign() default DEFAULT_SIGN;

        /**
         * 뒷쪽 마스킹 길이 비율<br>
         * 소수점인 경우 올림처리.
         */
        double suffix() default DEFAULT_SUFFIX;
    }

    /** 허용되지 않는 경우 처리 규칙 */
    enum Mode {
        /**
         * 일부 데이터 Masking 처리.<br>
         * 데이터 타입이 {@link String}인 경우만 지원.
         */
        MASK,
        /**
         * 데이터 null 처리<br>
         * Primitive 타입인 경우 아래와 같이 처리됩니다.
         * <ul>
         * <li>boolean: {@link Boolean#FALSE}
         * <li>byte: {@link Byte#MIN_VALUE}
         * <li>char: ''
         * <li>double: {@link Double#MIN_VALUE}
         * <li>float: {@link Float#MIN_NORMAL}
         * <li>int: {@link Integer#MIN_VALUE}
         * <li>long: {@link Long#MIN_VALUE}
         * <li>short: {@link Short#MIN_VALUE}
         * </ul>
         */
        NULLIFY,
        /** 오류 발생 */
        DENY
    }

}
