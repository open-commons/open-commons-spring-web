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
 * Date  : 2025. 7. 30. 오후 2:07:22
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.handler;

import java.util.Collection;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 
 * @since 2025. 7. 30.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class InterceptorIgnoreValidator {

    private static final Logger logger = LoggerFactory.getLogger(InterceptorIgnoreValidator.class);

    private InterceptorIgnoreValidator() {
    }

    /**
     * 설정에 대응하는 실제 빈 존재 여부 확인 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param target
     *            FQCN 기반의 클래스 또는 경로 정보
     * @param interceptor
     *            {@link HandlerInterceptor} Bean 객체
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static boolean isAcceptable(@NotBlank @Nonnull String target, @NotNull @Nonnull HandlerInterceptor interceptor) {
        return Pattern.matches(target.replace(".", "\\.").replace("*", ".*"), interceptor.getClass().getName());
    }

    /**
     * pattern (URL) AntPath 유효성 검증 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param patterns
     * @return
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static boolean isValidAntPath(@Nonnull Collection<String> patterns) {
        for (String pattern : patterns) {
            if (!isValidAntPath(pattern)) {
                return false;
            }
        }
        return true;
    }

    /**
     * pattern (URL) AntPath 유효성 검증 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param pattern
     *            URL 패턴
     * @return
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static boolean isValidAntPath(@Nonnull String pattern) {
        return pattern != null && pattern.startsWith("/");
    }

    /**
     * 올바른 FQCN 패턴인지 검증. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param target
     *            FQCN 기반의 클래스 또는 경로 정보
     * @return
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static boolean isValidTarget(@Nonnull String target) {
        try {
            Pattern.compile(target.replace(".", "\\.").replace("*", ".*"));
            return true;
        } catch (PatternSyntaxException ex) {
            logger.warn("올바르지 않은 FQCN 패턴입니다. target={}", target);
            return false;
        }
    }
}
