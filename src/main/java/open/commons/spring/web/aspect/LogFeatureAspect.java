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
 * Date  : 2025. 7. 28. 오후 4:29:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.aspect;

import java.lang.reflect.Method;

import javax.annotation.Nonnull;
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
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.log.ILogFeatureDecorationConsolidator;
import open.commons.spring.web.log.InvalidLogFeatureException;
import open.commons.spring.web.log.LogFeature;
import open.commons.spring.web.log.LogFeature.Target;

/**
 * 
 * @since 2025. 7. 28.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Aspect
@Order(AbstractAuthorizedResourceAspect.ORDER_REQUEST + 1)
public class LogFeatureAspect extends AbstractAspectPointcuts {

    private final ILogFeatureDecorationConsolidator logDecorator;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		박준홍			최초 작성
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
     * {@link LogFeature}이 설정된 메소드. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("@annotation(open.commons.spring.web.log.LogFeature)")
    public final void annotationServiceMetadata() {
    }

    /**
     * {@link RestController}, {@link Controller}, {@link Service} 어노테이션이 선언된 클래스 중에 {@link LogFeature} 어노테이션이 클래스에
     * 선언되었거나 메소드에 선언된 경우 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		박준홍			최초 작성
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
     * @see #withinServiceStereotypeComponent()
     * @see #annotationServiceMetadata()
     * @see #withinServiceMetadata()
     */
    @Around(" ( withinAllControllerStereotypeComponent() || withinServiceStereotypeComponent() ) " //
            + " && ( annotationServiceMetadata() || withinServiceMetadata() ) ")
    public Object handleServiceLog(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object target = pjp.getTarget();
            Method invokedMethod = ((MethodSignature) pjp.getSignature()).getMethod();

            // #1. 타입에 설정된 정보
            LogFeature annoType = AnnotationUtils.getAnnotation(target.getClass(), LogFeature.class);
            // #2. 메소드에 설정된 정보
            LogFeature annoMethod = AnnotationUtils.getAnnotation(invokedMethod, LogFeature.class);

            // 어노테이션이 메소드에 설정이 되어 있거나 클래스에 설정된 경우 대상이 '모두'인 경우
            if (annoMethod != null || annoType.target().equals(Target.ALL)) {
                String feature = getAttribute(annoMethod, annoType, LogFeature.PROP_FEATURE, f -> f != null && !f.trim().isEmpty());
                // #3. 'feature' 값 검증
                if ((feature = feature.trim()).isEmpty()) {
                    throw ExceptionUtils.newException(InvalidLogFeatureException.class, "클래스 또는 메소드중에 반드시 1개는 'feature'값이 설정되어야 합니다. type=%s, method=%s", annoType, annoMethod);
                } else if (!feature.matches(LogFeature.FEATURE_REG_EX)) {
                    throw ExceptionUtils.newException(InvalidLogFeatureException.class, "설정된 'feature' 정보에 허용하지 않은 문자가 포함되어 있습니다. 허용하는 문자열=[a-zA-Z0-9-_], feature=%s", feature);
                }
                MDC.put(LogFeature.PROP_FEATURE, feature);
                // #4. 'marker' 설정
                String marker = getAttribute(annoMethod, annoType, LogFeature.PROP_MARKER, m -> m != null && !m.trim().isEmpty());
                if (!(marker = marker.trim()).isEmpty()) {
                    MDC.put(LogFeature.PROP_MARKER, this.logDecorator.decorator(feature, marker).apply(marker));
                }
            }

            return pjp.proceed();
        } finally {
            MDC.clear();
        }
    }

    /**
     * {@link LogFeature}이 설정된 클래스. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 28.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Pointcut("@within(open.commons.spring.web.log.LogFeature)")
    public final void withinServiceMetadata() {
    }
}
