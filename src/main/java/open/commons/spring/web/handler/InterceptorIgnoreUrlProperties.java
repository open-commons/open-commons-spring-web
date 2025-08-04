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
 * Date  : 2025. 7. 30. 오전 10:27:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    private final Logger logger = LoggerFactory.getLogger(InterceptorIgnoreUrlProperties.class);

    /**
     * {@link HandlerInterceptor}를 구현한 클래스 또는 확인할 경로<br>
     * 표현: FQCN 기반 Java 정규식
     */
    private String target;
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

    public void addExcludePathPattern(String excludePathPattern) {
        if (PathUtils.isValidAntPath(excludePathPattern)) {
            this.excludePathPatterns.add(excludePathPattern);
        } else {
            logger.warn("{}, target={}, exclude.invalid={}", InvalidAntPathUrlPatternException.class.getName(), this.target, excludePathPattern);
        }
    }

    public void addExcludePathPatterns(@NotNull @Nonnull Set<String> excludePathPatterns) {
        if (PathUtils.isValidAntPath(excludePathPatterns)) {
            this.excludePathPatterns.addAll(excludePathPatterns);
        } else {
            logger.warn("{}, target={}, exclude.invalid={}", InvalidAntPathUrlPatternException.class.getName(), this.target, excludePathPatterns.toString());
        }
    }

    public void addIncludePathPattern(String includePathPattern) {
        if (PathUtils.isValidAntPath(includePathPattern)) {
            this.includePathPatterns.add(includePathPattern);
        } else {
            logger.warn("{}, target={}, include.invalid={}", InvalidAntPathUrlPatternException.class.getName(), this.target, includePathPattern);
        }
    }

    public void addIncludePathPatterns(@NotNull @Nonnull Set<String> includePathPatterns) {
        if (PathUtils.isValidAntPath(includePathPatterns)) {
            this.includePathPatterns.addAll(includePathPatterns);
        } else {
            logger.warn("{}, target={}, include.invalid={}", InvalidAntPathUrlPatternException.class.getName(), this.target, includePathPatterns.toArray());
        }
    }

    /**
     *
     * @since 2025. 7. 30.
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
        return Objects.equals(target, other.target);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
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
        return Collections.unmodifiableSet(this.excludePathPatterns);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
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
        return Collections.unmodifiableSet(this.includePathPatterns);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
     * </pre>
     * 
     * @return the target
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #target
     */

    public String getTarget() {
        return target;
    }

    /**
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(target);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param excludePathPatterns
     *            the excludePathPatterns to set
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #excludePathPatterns
     */
    public void setExcludePathPatterns(@NotNull @Nonnull Set<String> excludePathPatterns) {
        if (!PathUtils.isValidAntPath(excludePathPatterns)) {
            throw ExceptionUtils.newException(InvalidAntPathUrlPatternException.class, "target=%s, exclude.invalid=%s", this.target, excludePathPatterns.toString());
        }
        this.excludePathPatterns = excludePathPatterns;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param includePathPatterns
     *            the includePathPatterns to set
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #includePathPatterns
     */
    public void setIncludePathPatterns(@NotNull @Nonnull Set<String> includePathPatterns) {
        if (!PathUtils.isValidAntPath(includePathPatterns)) {
            throw ExceptionUtils.newException(InvalidAntPathUrlPatternException.class, "target=%s, include.invalid=%s", this.target, includePathPatterns.toString());
        }
        this.includePathPatterns = includePathPatterns;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 30.		박준홍			최초 작성
     * </pre>
     *
     * @param target
     *            the target to set
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #target
     */
    public void setTarget(@NotBlank @Nonnull String target) {
        if (!InterceptorIgnoreValidator.isValidTarget(target)) {
            throw ExceptionUtils.newException(InvalidAntPathUrlPatternException.class, "target.invalid=%s", target);
        }
        this.target = "*".equals(target) ? ".*" : target;
    }

    /**
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InterceptorIgnoreUrlProperties [target=");
        builder.append(target);
        builder.append(", includePathPatterns=");
        builder.append(includePathPatterns);
        builder.append(", excludePathPatterns=");
        builder.append(excludePathPatterns);
        builder.append("]");
        return builder.toString();
    }
}
