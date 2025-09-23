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
 * Date  : 2025. 7. 30. 오후 2:07:22
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.handler;

import java.util.Collection;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.exception.InvalidBeanNameFqnResolveException;
import open.commons.spring.web.handler.InterceptorIgnoreUrlProperties.Scheme;
import open.commons.spring.web.utils.PathUtils;

/**
 * 
 * @since 2025. 7. 30.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class InterceptorIgnoreValidator {

    @SuppressWarnings("unused")
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
     * @param fqcn
     *            FQCN 기반의 클래스 또는 경로 정보
     * @param interceptor
     *            {@link HandlerInterceptor} Bean 객체
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static boolean isAcceptable(@NotBlank @Nonnull String fqcn, @NotNull @Nonnull HandlerInterceptor interceptor) {
        return Pattern.matches(fqcn.replace(".", "\\.").replace("*", ".*"), interceptor.getClass().getName());
    }

    /**
     * {@link HandlerInterceptor} 객체가 설정에 부합하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 7.		박준홍			최초 작성
     * </pre>
     *
     * @param prop
     * @param interceptor
     * @return
     *
     * @since 2025. 8. 7.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static boolean isAvailable(@Nonnull InterceptorIgnoreUrlProperties prop, @Nonnull HandlerInterceptor interceptor) {
        try {
            switch (prop.getScheme()) {
                case Class:
                    return Class.forName(prop.getFqcn()).equals(interceptor.getClass());
                case Instance:
                    return Class.forName(prop.getFqcn()).isInstance(interceptor.getClass());
                case Package:
                    return isAcceptable(prop.getFqcn(), interceptor);
                default:
                    throw ExceptionUtils.newException(UnsupportedOperationException.class, "지원하지 않는 'scheme'(%s) 입니다. 지원목록=%s", prop.getScheme(), Scheme.values());
            }
        } catch (ClassNotFoundException e) {
            throw ExceptionUtils.newException(InvalidBeanNameFqnResolveException.class, e, "'{}'에 해당하는 클래스 정보가 없습니다.", prop.getFqcn());
        }
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
        return PathUtils.isValidAntPath(patterns);
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
     * @param fqcn
     *            FQCN 기반의 클래스 또는 경로 정보
     * @return
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static boolean isValidFqcn(@Nonnull String fqcn) {
        return PathUtils.isValidFqcn(fqcn);
    }
}
