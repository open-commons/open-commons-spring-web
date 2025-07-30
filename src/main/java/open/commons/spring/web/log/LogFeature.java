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
 * Date  : 2025. 7. 28. 오후 3:40:34
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.constraints.Pattern;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link RestController}/{@link Controller}, {@link Service}, {@link Component}에서 제공하는 기능을 구분하여 별도의 로그파일 저장하기 위한 정보를
 * 제공합니다.
 * 
 * @since 2025. 7. 28.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface LogFeature {
    /** 'feature' 속성 이름 */
    public static final String PROP_FEATURE = "feature";
    /** 'marker' 속성 이름 */
    public static final String PROP_MARKER = "marker";
    
    /** 'feature' 속성 기본 값 */
    public static final String VALUE_FEATURE_NULL = "";
    /** 'feature' 정보 정규식 */
    public static final String FEATURE_REG_EX = "^[a-zA-Z0-9-_]+$";

    /**
     * 서비스 기능 <br>
     * 이 정보를 이용하여 제공하는 기능을 구분하고, log4j2 기반 로그파일을 분리합니다.<br>
     * 클래스와 메소드에 모두 사용할 수있으며, 메소드에 설정된 정보를 우선 사용합니다.<br>
     * 단, 클래스 및 메소드에 설정된 값이 빈 문자열이나 허용되지 않은 문자열이 포함된 경우 오류가 발생합니다.<br>
     * 허용된 문자열은 다음과 같습니다.
     * <li>알파벳 대/소문자(a-z, A-Z)
     * <li>숫자(0-9)
     * <li>하이픈(-)
     * <li>밑줄(_)
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 7. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @AliasFor("value")
    String feature() default VALUE_FEATURE_NULL;

    /**
     * 로그 내용에 포함되는 정보로 동일한 기능(feature)이지만, 로그 내용에서 구분하고자 할 때 사용합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 7. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    String marker() default "";

    /**
     * 이 어노테이션이 클래스에 설정된 경우에 로그 분리를 적용하는 범위.<br>
     * <li>{@link Target#ALL}: AOP로 접근가능한 메소드
     * <li>{@link Target#SPECIFIED}: {@link LogFeature} 어노테이션이 설정된 메소드만.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 7. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    Target target() default Target.ALL;

    @AliasFor("feature")
    String value() default VALUE_FEATURE_NULL;

    public static enum Target {
        /** AOP로 접근가능한 메소드 */
        ALL,
        /** {@link LogFeature} 어노테이션이 설정된 메소드 */
        SPECIFIED,
    }
}
