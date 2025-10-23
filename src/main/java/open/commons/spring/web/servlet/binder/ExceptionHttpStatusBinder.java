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
 * Date  : 2025. 5. 28. 오후 2:18:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet.binder;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.StringUtils;

/**
 * 
 * @since 2025. 5. 28.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class ExceptionHttpStatusBinder {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.servlet.binder.ExceptionHttpStatusBinder";

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * {@link Exception}과 {@link HttpStatus}의 매핑 정보
     */
    private final Map<Class<? extends Throwable>, HttpStatus> mappings = new HashMap<>();

    /**
     * 읽어들인 설정 정보. (검증 대상).<br>
     * 예: java.lang.NullPointerException: org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
     */
    private Map<String, String> properties;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param properties
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ExceptionHttpStatusBinder(Map<String, String> properties) {
        this.properties = properties != null ? properties : new HashMap<>();
    }

    /**
     * 로그를 출력하고 메핑 정보를 초기화 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param valid
     * @param logFormat
     * @param logArgs
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private void loggingAndClear(AtomicBoolean valid, String logFormat, Object... logArgs) {
        logger.error(logFormat, logArgs);
        valid.set(false);
        mappings.clear();

    }

    /**
     * {@link Throwable} 클래스에 설정된 {@link HttpStatus} 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 28.		parkjunhong77@gmail.com		최초 작성
     * 2025. 10. 22.    parkjunhong77@gmail.com     기본 상태 추가 및 {@link ResponseStatus} 적용 확인 추가
     * </pre>
     *
     * @param exClass
     *            예외 클래스 유형.
     * @param defaultStatus
     *            전달받은 예외 클래스(<code>ex</code>)와 연결된 {@link HttpStatus}가 없는 경우
     * @return
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public <EX extends Throwable> HttpStatus resolveHttpStatus(@NotNull Class<EX> exClass, HttpStatus defaultStatus) {

        if (exClass == null) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, new NullPointerException("an instance of Throwable is null."), "예외클래스 정보는 반드시 존재해야 합니다.");
        }

        HttpStatus status = this.mappings.get(exClass);
        if (status == null) {
            ResponseStatus resStatus = AnnotatedElementUtils.findMergedAnnotation(exClass, ResponseStatus.class);
            status = resStatus != null ? resStatus.code() : defaultStatus;
        }

        return status;
    }

    /**
     * {@link Exception}과 {@link HttpStatus} 관계 정보를 검증하고 데이터를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 5. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    @PostConstruct
    public void validateAndInitialize() {

        String exceptionClassName = null;
        Class<? extends Throwable> exceptionClass = null;
        String httpStatusName = null;
        HttpStatus httpStatus = null;
        HttpStatus oldHttpStatus = null;

        AtomicBoolean valid = new AtomicBoolean(true);
        for (Entry<String, String> entry : this.properties.entrySet()) {
            exceptionClassName = entry.getKey();
            httpStatusName = entry.getValue();

            if (StringUtils.isNullOrEmptyStringOr(exceptionClassName, httpStatusName)) {
                loggingAndClear(valid, "예외 클래스 ({}) 또는 HttpStatus ({}) 설정이 올바르지 않습니다.", exceptionClassName, httpStatusName);
                continue;
            }

            try {
                // #1. 예외 클래스 검증
                exceptionClass = (Class<? extends Throwable>) Class.forName(exceptionClassName);
                if (!Throwable.class.isAssignableFrom(exceptionClass)) {
                    loggingAndClear(valid, "'{}'는 '{}'의 하위 클래스가 아닙니다.", exceptionClassName, Throwable.class.toString());
                    continue;
                }

                // #2. HttpStatus 검증
                httpStatus = HttpStatus.valueOf(httpStatusName);
                oldHttpStatus = this.mappings.get(exceptionClass);
                if (oldHttpStatus != null) {
                    logger.warn("[매핑변경] {} : {} <-- {}", exceptionClassName, httpStatus, oldHttpStatus);
                }
                httpStatus = this.mappings.put(exceptionClass, httpStatus);

            } catch (ClassNotFoundException e) {
                loggingAndClear(valid, "'{}' 정보를 찾지 못했습니다. 오류={}", exceptionClassName, e.getMessage());
            } catch (IllegalArgumentException e) {
                loggingAndClear(valid, "'{}' 클래스에서 '{}' 정보를 찾지 못했습니다. 오류={}", HttpStatus.class.toString(), httpStatusName, e.getMessage());
            }
        }

        if (!valid.get()) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "'%s'과 '%s' 매핑 정보 설정이 올바르지 않습니다.", Exception.class.toString(), HttpStatus.class.toString());
        }

        this.mappings.forEach((c, s) -> {
            logger.info("[managed-exceptioned-http-status] exception={}, http-status={}", c, s);
        });
    }
}
