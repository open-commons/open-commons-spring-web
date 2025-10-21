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
 * Date  : 2025. 7. 30. 오전 10:27:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.handler;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.servlet.InvalidAntPathUrlPatternException;
import open.commons.spring.web.utils.PathUtils;

/**
 * {@link Set} 형태의 데이터에 대해서 특정 목적에 맞는 타입을 지정하기 위한 클래스.
 * 
 * @since 2025. 7. 30.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class InterceptorIgnoreUrlProperties {

    public static final String SCHEME_PACKAGE = "package";
    public static final String SCHEME_CLASS = "class";
    public static final String SCHEME_INSTANCE = "instance";
    /**
     * <pre>
     *  
     * | group    | description                  | case  |
     * | -------- | ---------------------------- | ----- |
     * | group(1) | `"package"`                  | #1    |
     * | group(2) | `com.example`                | #1    |
     * | group(3) | `"class"` or `"instance"`    | #2    |
     * | group(4) | `com.example`                | #2    |
     * | group(5) | `MyClass` or `MyClass$Inner` | #2    |
     * | group(6) | no scheme -> `com.example`   | #3    |
     * </pre>
     */
    private static final Pattern PATTERN = Pattern //
            .compile("^(?:" //
                    // CASE 1: package:<fqcn>.*
                    + "(?i)(" + SCHEME_PACKAGE + "):([a-zA-Z_][\\w]*(?:\\.[a-zA-Z_][\\w]*)*)\\.\\*" //
                    + "|"
                    // CASE 2: class|instance:<fqcn>.<ClassName>
                    + "(?i)(" + SCHEME_CLASS + "|" + SCHEME_INSTANCE + "):([a-zA-Z_][\\w]*(?:\\.[a-zA-Z_][\\w]*)*)\\.([$_a-zA-Z][\\w$]*)" //
                    + "|"
                    // CASE 3: no scheme, just <fqcn>.*
                    + "([a-zA-Z_][\\w]*(?:\\.[a-zA-Z_][\\w]*)*)\\.\\*" //
                    + ")$" //
            );

    private final Logger logger = LoggerFactory.getLogger(InterceptorIgnoreUrlProperties.class);

    /**
     * {@link #fqcn} 정보 유형.
     *
     * <p>
     * <li>pakcage: FQCN 경로 하위에 있는 경우, 단 "*"은 모두 허용.
     * <li>class: FQCN 클래스와 정확히 일치하는 경우.
     * <li>instance: FQCN 클래스의 하위 클래스인 경우.
     * </p>
     */
    private Scheme scheme;
    /**
     * {@link HandlerInterceptor}를 구현한 클래스 또는 확인할 경로<br>
     * 표현: FQCN 기반 Java 정규식
     */
    private String fqcn;
    /**
     * {@link HandlerInterceptor}에서 처리할 URL<br>
     * 설정하지 않는 경우 모든 URL을 처리하게 됨.
     */
    private Set<String> includePathPatterns = new HashSet<>();
    /**
     * {@link HandlerInterceptor}에서 처리하지 않을 URL<br>
     * {@link #includePathPatterns}과 함께 처리되면, {@link #excludePathPatterns}의 적용 순위가 높음.
     */
    private Set<String> excludePathPatterns = new HashSet<>();

    public InterceptorIgnoreUrlProperties() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param excludePathPattern
     *            처리 대상에서 제외시킬 URL 패턴 (AntPathPattern)
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public InterceptorIgnoreUrlProperties addExcludePathPattern(String excludePathPattern) {
        if (PathUtils.isValidAntPath(excludePathPattern)) {
            this.excludePathPatterns.add(excludePathPattern);
        } else {
            logger.warn("{}, fqcn={}, exclude.invalid={}", InvalidAntPathUrlPatternException.class.getName(), this.fqcn, excludePathPattern);
        }
        return this;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param excludePathPatterns
     *            처리 대상에서 제외시킬 URL 패턴 (AntPathPattern)
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public InterceptorIgnoreUrlProperties addExcludePathPatterns(@NotNull @Nonnull Set<String> excludePathPatterns) {
        if (PathUtils.isValidAntPath(excludePathPatterns)) {
            this.excludePathPatterns.addAll(excludePathPatterns);
        } else {
            logger.warn("{}, fqcn={}, exclude.invalid={}", InvalidAntPathUrlPatternException.class.getName(), this.fqcn, excludePathPatterns.toString());
        }
        return this;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param includePathPattern
     *            처리 대상에 포함시킬 URL 패턴 (AntPathPattern)
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public InterceptorIgnoreUrlProperties addIncludePathPattern(String includePathPattern) {
        if (PathUtils.isValidAntPath(includePathPattern)) {
            this.includePathPatterns.add(includePathPattern);
        } else {
            logger.warn("{}, fqcn={}, include.invalid={}", InvalidAntPathUrlPatternException.class.getName(), this.fqcn, includePathPattern);
        }
        return this;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 4.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param includePathPatterns
     *            처리 대상에 포함시킬 URL 패턴 (AntPathPattern)
     *
     * @since 2025. 8. 4.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public InterceptorIgnoreUrlProperties addIncludePathPatterns(@NotNull @Nonnull Set<String> includePathPatterns) {
        if (PathUtils.isValidAntPath(includePathPatterns)) {
            this.includePathPatterns.addAll(includePathPatterns);
        } else {
            logger.warn("{}, fqcn={}, include.invalid={}", InvalidAntPathUrlPatternException.class.getName(), this.fqcn, includePathPatterns.toArray());
        }
        return this;
    }

    /**
     *
     * @since 2025. 8. 7.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InterceptorIgnoreUrlProperties other = (InterceptorIgnoreUrlProperties) obj;
        return scheme == other.scheme && Objects.equals(fqcn, other.fqcn);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the excludePathPatterns
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #excludePathPatterns
     */
    public Set<String> getExcludePathPatterns() {
        return new HashSet<>(this.excludePathPatterns);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the fqcn
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #fqcn
     */
    public String getFqcn() {
        return fqcn;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the includePathPatterns
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #includePathPatterns
     */
    public Set<String> getIncludePathPatterns() {
        return new HashSet<>(this.includePathPatterns);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the scheme
     *
     * @since 2025. 8. 7.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #scheme
     */

    public Scheme getScheme() {
        return scheme;
    }

    /**
     * {@link #scheme}와 {@link #fqcn} 정보를 병합하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 7.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public String getTarget() {
        return String.join(":", this.scheme.get(), this.fqcn);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param excludePathPatterns
     *            처리 대상에서 제외시킬 URL 패턴 (AntPathPattern)
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #excludePathPatterns
     */
    public InterceptorIgnoreUrlProperties setExcludePathPatterns(@NotNull @Nonnull Set<String> excludePathPatterns) {
        if (!PathUtils.isValidAntPath(excludePathPatterns)) {
            throw ExceptionUtils.newException(InvalidAntPathUrlPatternException.class, "fqcn=%s, exclude.invalid=%s", this.fqcn, excludePathPatterns.toString());
        }
        this.excludePathPatterns = excludePathPatterns;

        return this;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param includePathPatterns
     *            처리 대상에 포함시킬 URL 패턴 (AntPathPattern)
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #includePathPatterns
     */
    public InterceptorIgnoreUrlProperties setIncludePathPatterns(@NotNull @Nonnull Set<String> includePathPatterns) {
        if (!PathUtils.isValidAntPath(includePathPatterns)) {
            throw ExceptionUtils.newException(InvalidAntPathUrlPatternException.class, "fqcn=%s, include.invalid=%s", this.fqcn, includePathPatterns.toString());
        }
        this.includePathPatterns = includePathPatterns;

        return this;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param fqcn
     *            the fqcn to set
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #fqcn
     */
    public InterceptorIgnoreUrlProperties setTarget(@NotBlank @Nonnull String target) {

        if ("*".equals(target) || "package:*".equalsIgnoreCase(target)) {
            this.scheme = Scheme.Package;
            this.fqcn = ".*";
        } else {
            Matcher m = PATTERN.matcher(target);
            if (m.matches()) {
                /**
                 * <pre>
                 * | group    | description                  | case  |
                 * | -------- | ---------------------------- | ----- |
                 * | group(1) | `"package"`                  | #1    |
                 * | group(2) | `com.example`                | #1    |
                 * | group(3) | `"class"` or `"instance"`    | #2    |
                 * | group(4) | `com.example`                | #2    |
                 * | group(5) | `MyClass` or `MyClass$Inner` | #2    |
                 * | group(6) | no scheme -> `com.example`   | #3    |
                 * </pre>
                 */
                if (m.group(1) != null) {
                    this.scheme = Scheme.Package;
                    this.fqcn = m.group(2) + ".*";
                } else if (m.group(3) != null) {
                    this.scheme = Scheme.get(m.group(3).toLowerCase());
                    this.fqcn = m.group(4) + "." + m.group(5); // class_name
                } else if (m.group(6) != null) {
                    this.scheme = Scheme.Package;
                    this.fqcn = m.group(6) + ".*";
                }
            } else {
                throw ExceptionUtils.newException(InvalidAntPathUrlPatternException.class, "fqcn.invalid=%s", target);
            }
        }

        logger.info("[interceptor-ignored-url-property]) scheme={}, fqcn={}", this.scheme, this.fqcn);
        return this;
    }

    /**
     *
     * @since 2025. 8. 7.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InterceptorIgnoreUrlProperties [scheme=");
        builder.append(scheme);
        builder.append(", fqcn=");
        builder.append(fqcn);
        builder.append(", includePathPatterns=");
        builder.append(includePathPatterns);
        builder.append(", excludePathPatterns=");
        builder.append(excludePathPatterns);
        builder.append("]");
        return builder.toString();
    }

    public static enum Scheme {
        Package(SCHEME_PACKAGE), //
        Class(SCHEME_CLASS), //
        Instance(SCHEME_INSTANCE), //
        ;

        private final String scheme;

        private Scheme(String s) {
            this.scheme = s;
        }

        public String get() {
            return this.scheme;
        }

        public static Scheme get(String scheme) {

            for (Scheme s : values()) {
                if (s.scheme.equals(scheme)) {
                    return s;
                }
            }

            throw ExceptionUtils.newException(IllegalArgumentException.class, "'%s'에 해당하는 %s이 존재하지 않습니다.", scheme, Scheme.class.getName());
        }
    }
}
