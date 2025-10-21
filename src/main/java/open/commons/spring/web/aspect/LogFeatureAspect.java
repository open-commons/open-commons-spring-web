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
 * Date  : 2025. 7. 28. 오후 4:29:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.aspect;

import java.lang.reflect.Method;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;

import open.commons.core.lang.IThreadLocalContext;
import open.commons.core.lang.ThreadLocalContextService;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.log.ILogFeatureDecorationConsolidator;
import open.commons.spring.web.log.InvalidLogFeatureException;
import open.commons.spring.web.log.LogFeature;
import open.commons.spring.web.log.LogFeature.Target;
import open.commons.spring.web.servlet.filter.RequestHeaderFilter;
import open.commons.spring.web.servlet.filter.RequestThreadNameFilter;
import open.commons.spring.web.servlet.filter.header.SharedHeadersBuiltinProvider;

import io.micrometer.core.lang.Nullable;

/**
 * 
 * @since 2025. 7. 28.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Aspect
@Order(AbstractAuthorizedResourceAspect.ORDER_REQUEST + 1)
public class LogFeatureAspect extends AbstractAspectPointcuts {

    /** {@link MDC}에 공유하고자 하는 Thread 이름을 위한 속성 */
    public static final String FORWARDED_THREAD_NAME = RequestThreadNameFilter.THREAD_NAME_INTERCEPTED_URL;
    /**
     * {@link OncePerRequestFilter}와 {@link ThreadLocal} 정보를 공유하는 객체 <br>
     * {@link OncePerRequestFilter} -> {@link LogFeatureAspect} 까지 동일한 {@link Thread} 로 연결되고 있음.
     */
    private static final IThreadLocalContext REQUEST_THREAD_NAME_FILTER_CONTEXT = ThreadLocalContextService.context(RequestThreadNameFilter.class);
    /** Request 헤더의 정보를 공유하는 컨텍스트 */
    private static final IThreadLocalContext REQUEST_HEADER_FILTER_CONTEXT = ThreadLocalContextService.context(RequestHeaderFilter.class);
    /** {@link LogFeature#marker()} 값을 'pretty'하게 출력하는 정보 */
    private final ILogFeatureDecorationConsolidator logDecorator;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param context
     * @param logDecorator
     *
     * @since 2025. 7. 28.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public LogFeatureAspect(ApplicationContext context, @NotNull @Nonnull ILogFeatureDecorationConsolidator logDecorator) {
        super(context);
        this.logDecorator = logDecorator;
    }

    /**
     * {@link LogFeature} 어노테이션이 설정된 메소드. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("@annotation(open.commons.spring.web.log.LogFeature)")
    public final void annotationLogFeature() {
    }

    /**
     * {@link Scheduled} 어노테이션이 설정된 메소드. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 31.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public final void annotationScheduled() {

    }

    /**
     * {@link HandlerInterceptor} 이후에 실행되는 {@link RestController}, {@link Controller} 어노테이션이 선언된 클래스 중에
     * {@link LogFeature} 어노테이션이 클래스에 선언되었거나 메소드에 선언된 경우에 대해서 처리합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param pjp
     * @return
     * @throws Throwable
     *
     * @since 2025. 7. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #withinAllControllerStereotypeComponent()
     * @see #withinComponentStereotypeComponent()
     * @see #annotationLogFeature()
     * @see #withinLogFeature()
     */
    @Around(" ( withinAllControllerStereotypeComponent() || withinComponentStereotypeComponent() ) " //
            + " && ( annotationLogFeature() || withinLogFeature() ) ")
    public final Object handleExternalRequest(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object target = pjp.getTarget();
            Method invokedMethod = ((MethodSignature) pjp.getSignature()).getMethod();

            // #1. 타입에 설정된 정보
            LogFeature annoType = AnnotationUtils.getAnnotation(target.getClass(), LogFeature.class);
            // #2. 메소드에 설정된 정보
            LogFeature annoMethod = AnnotationUtils.getAnnotation(invokedMethod, LogFeature.class);

            // 어노테이션이 메소드에 설정이 되어 있거나 클래스에 설정된 경우 대상이 '모두'인 경우
            if (annoMethod != null || annoType.target().equals(Target.ALL)) {
                // #3. 'feature'
                String requestFeature = (String) REQUEST_HEADER_FILTER_CONTEXT.get(SharedHeadersBuiltinProvider.X_LOG_FEATURE);
                String feature = requestFeature != null // Http 요청시 LogFeature 설정이 있었는지 확인
                        ? requestFeature //
                        : getAttribute(annoMethod, annoType, LogFeature.PROP_FEATURE, f -> f != null && !f.trim().isEmpty());
                // #4. 'marker'
                String marker = getAttribute(annoMethod, annoType, LogFeature.PROP_MARKER, m -> m != null && !m.trim().isEmpty());
                // #5. 'thread'
                // HandlerInterceptor.preHandle(...)에서 설정한 HTTP 요청 URL 기반 Thread 이름을 MDC에 추가.
                String intcptorThreadName = (String) REQUEST_THREAD_NAME_FILTER_CONTEXT.get(RequestThreadNameFilter.THREAD_NAME_INTERCEPTED_URL);
                String annoThread = getAttribute(annoMethod, annoType, LogFeature.PROP_THREAD, m -> m != null && !m.trim().isEmpty());
                String thread = StringUtils.isNullOrEmptyString(intcptorThreadName) //
                        ? annoThread.trim() //
                        : intcptorThreadName.trim();
                // 'feature', 'marker' 설정
                logger.trace("[log-aspected] feature={}, marker={}, thread={}", feature, marker, thread);

                setLogFeature(feature, marker, thread, String.format("클래스 또는 메소드중에 반드시 1개는 'feature'값이 설정되어야 합니다. type=%s, method=%s", annoType, annoMethod));
            }

            return pjp.proceed();
        } finally {
            MDC.clear();
        }
    }

    /**
     * {@link Component} 어노테이션이 설정된 클래스의 메소드 중에 {@link Scheduled}와 {@link LogFeature}가 모두 선언된 메소드를 처리합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 31.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param pjp
     * @return
     * @throws Exception
     *
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #withinComponentStereotypeComponent()
     * @see #annotationScheduled()
     * @see #withinLogFeature()
     */
    @Around(" withinComponentStereotypeComponent() " //
            + " && annotationScheduled() " //
            + " && annotationLogFeature() ")
    public final Object handleInternalTrigger(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Method invokedMethod = ((MethodSignature) pjp.getSignature()).getMethod();
            // #1. 메소드에 설정된 정보
            LogFeature annoMethod = AnnotationUtils.getAnnotation(invokedMethod, LogFeature.class);
            // 'feature'
            String feature = annoMethod.feature();
            // 'marker'
            String marker = annoMethod.marker();
            // 'thread'
            String thread = annoMethod.thread();
            // 'feature', 'marker' 설정
            logger.trace("[log-aspected] feature={}, marker={}, thread={}", feature, marker, thread);

            setLogFeature(feature, marker, thread, String.format("'feature'값이 설정되어야 합니다. annotation=%s", annoMethod));

            return pjp.proceed();
        } finally {
            MDC.clear();
        }
    }

    /**
     * {@link MDC}에 'feature', 'marker' 값을 저장합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 31.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param feature
     * @param marker
     * @param thread
     * @param featureNotBlankAsserMsg
     * @since 2025. 7. 31.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private void setLogFeature(@NotBlank @Nonnull String feature, @Nullable String marker, String thread, String featureNotBlankAsserMsg) {

        // 'feature' 설정
        if (LogFeature.VALUE_THREAD_NULL.equals(feature) || (feature = feature.trim()).isEmpty()) {
            throw ExceptionUtils.newException(InvalidLogFeatureException.class, featureNotBlankAsserMsg);
        } else if (!feature.matches(LogFeature.FEATURE_REG_EX)) {
            throw ExceptionUtils.newException(InvalidLogFeatureException.class, "설정된 'feature' 정보에 허용하지 않은 문자가 포함되어 있습니다. 허용하는 정규식=%s, feature=%s", LogFeature.FEATURE_REG_EX,
                    feature);
        }
        MDC.put(LogFeature.PROP_FEATURE, feature);

        // 'marker' 설정
        if (!(LogFeature.VALUE_FEATURE_NULL.equals(marker) || StringUtils.isNullOrEmptyString(marker))) {
            MDC.put(LogFeature.PROP_MARKER, this.logDecorator.decorator(feature, marker.trim()).apply(marker.trim()));
        }

        // 'thread' 설정
        if (!(LogFeature.VALUE_THREAD_NULL.equals(thread) || StringUtils.isNullOrEmptyString(thread))) {
            MDC.put(FORWARDED_THREAD_NAME, thread.trim());
        }
    }

    /**
     * {@link LogFeature} 어노테이션이 설정된 클래스. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("@within(open.commons.spring.web.log.LogFeature)")
    public final void withinLogFeature() {
    }
}
