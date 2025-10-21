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
 * Date  : 2025. 8. 4. 오후 4:41:53
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.utils;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

/**
 * 
 * @since 2025. 8. 4.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class PathUtils {

    private static final Logger logger = LoggerFactory.getLogger(PathUtils.class);

    private PathUtils() {
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 25.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param env
     *            환경설정 정보
     * @param propName
     *            설정이름
     * @param actor
     *            설정값을 사용할 대상.
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static void addEnvironmentProperty(@Nonnull Environment env, String propName, @Nonnull Consumer<String> actor) {
        String value = env.getProperty(propName);
        if (value == null) {
            logger.debug("'{}' 에 해당하는 설정이 존재하지 않습니다.", propName);
            return;
        }
        actor.accept(value);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 25.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <V>
     *            변환 데이터 유형
     * @param env
     *            환경설정 정보
     * @param propName
     *            설정이름
     * @param converter
     *            설정값 변환 함수
     * @param actor
     *            변환된 값을 사용할 대상.
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static <V> void addEnvironmentProperty(@Nonnull Environment env, String propName, Function<String, V> converter, @Nonnull Consumer<V> actor) {
        String value = env.getProperty(propName);
        if (value == null) {
            logger.debug("'{}' 에 해당하는 설정이 존재하지 않습니다.", propName);
            return;
        }
        actor.accept(converter.apply(value));
    }

    /**
     * pattern (URL) AntPath 유효성 검증 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param patterns
     * @return
     *
     * @since 2025. 8. 4.
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pattern
     *            URL 패턴
     * @return
     *
     * @since 2025. 8. 4.
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 4.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param fqcn
     *            FQCN 기반의 클래스 또는 경로 정보
     * @return
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static boolean isValidFqcn(@Nonnull String fqcn) {
        try {
            Pattern.compile(fqcn.replace(".", "\\.").replace("*", ".*"));
            return true;
        } catch (PatternSyntaxException ex) {
            logger.warn("올바르지 않은 FQCN 패턴입니다. fqcn={}", fqcn);
            return false;
        }
    }
}
