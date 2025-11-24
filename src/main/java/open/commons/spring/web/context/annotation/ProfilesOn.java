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
 * Date  : 2025. 11. 21. 오후 2:59:56
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link Controller}, {@link RestController} 이 설정된 'controller' 클래스의 메소드에 설정하여,<br>
 * 'active profile'이나 기타 설정(추후 개발)과 함께 메소드의 실행 여부를 제어하는 정보. <br>
 * 
 * <pre>
 * [개정이력]
 *      날짜    	| 작성자			|	내용
 * ------------------------------------------
 * 2025. 11. 21.	박준홍(jhpark@ymtech.co.kr)  {@link Environment#getActiveProfiles()} 연동만 지원			
 * </pre>
 * 
 * @since 2025. 11. 21.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProfilesOn {

    /**
     * {@link Environment#getActiveProfiles()}(+추가설정) 과 {@link #standards()} 을 비교/판단하는 방법
     */
    DecisionRule rule();

    /** {@link Environment#getActiveProfiles()}(+추가설정) 과 비교/판단할 정보 */
    String[] standards();

    /** {@link #standards()}에 포함된 정보를 적용하는 방식 */
    Strategy strategy();

    /**
     * {@link Environment#getActiveProfiles()}(+추가설정)과 {@link ProfilesOn#standards()} 값을 비교하는 규칙
     * 
     * @since 2025. 11. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static enum DecisionRule {
        /** {@link Environment#getActiveProfiles()}(+추가설정) 중 하나와 {@link ProfilesOn#standards()} 중 하나가 정확히 일치하는지 */
        EQ,
        /** {@link Environment#getActiveProfiles()}(+추가설정)가 {@link ProfilesOn#standards()} 중 하나로 '시작'하는지 */
        BEGIN,
        /** {@link Environment#getActiveProfiles()}(+추가설정)가 {@link ProfilesOn#standards()} 중 하나로 '끝'나는지 */
        END,
        /** {@link Environment#getActiveProfiles()}(+추가설정)가 {@link ProfilesOn#standards()} 중 하나의 정규식에 매칭되는지 */
        REGEX
    }

    /**
     * {@link Environment#getActiveProfiles()}(+추가설정)과 "{@link ProfilesOn#standards()} x {@link ProfilesOn#rule()}"의
     * 비교/판단 결과를 적용하는 방식.
     * 
     * @since 2025. 11. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static enum Strategy {
        /** 허용 */
        ALLOW,
        /** 불가 */
        DENY,
    }
}
