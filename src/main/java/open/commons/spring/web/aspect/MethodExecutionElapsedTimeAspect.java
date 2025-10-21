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
 * Date  : 2025. 8. 12. 오후 5:20:32
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import open.commons.core.lang.IThreadLocalContext;
import open.commons.core.lang.ThreadLocalContextService;
import open.commons.core.test.StopWatch;
import open.commons.spring.web.log.LogFeature;

/**
 * 
 * @since 2025. 8. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class MethodExecutionElapsedTimeAspect extends AbstractMethodCallChainLogAspect {

    /** {@link Controller} 적용 클래스 */
    protected static final String ELAPSED_TIME_CONTROLLER = "elapsed_time.controller";
    /** {@link Service} 적용 클래스 */
    protected static final String ELAPSED_TIME_SERVICE = "elapsed_time.service";
    /** {@link Repository} 적용 클래스 */
    protected static final String ELAPSED_TIME_REPOSITORY = "elapsed_time.repository";

    protected final IThreadLocalContext CONTEXT = ThreadLocalContextService.context(getClass());

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     *  2025. 8. 12.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param context
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public MethodExecutionElapsedTimeAspect(ApplicationContext context) {
        super(context, false, false, false, false, true, true);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     *  2025. 8. 12.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param context
     * @param disableRepository
     *            {@link Repository} 설정 클래스 AOP 미적용 여부
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public MethodExecutionElapsedTimeAspect(ApplicationContext context, boolean disableRepository) {
        super(context, false, false, disableRepository, false, true, true);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     *  2025. 8. 12.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param context
     * @param disableRepository
     *            {@link Repository} 설정 클래스 AOP 미적용 여부
     * @param handleIfOriginatedFromController
     *            메소드 호출이 {@link Controller}에서부터 시작된 경우에만 AOP 적용 여부
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public MethodExecutionElapsedTimeAspect(ApplicationContext context, boolean disableRepository, boolean handleIfOriginatedFromController) {
        super(context, false, false, disableRepository, handleIfOriginatedFromController, true, true);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 12.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param context
     * @param disableRepository
     *            {@link Repository} 설정 클래스 AOP 미적용 여부
     * @param handleIfOriginatedFromController
     *            메소드 호출이 {@link Controller}에서부터 시작된 경우에만 AOP 적용 여부
     * @param enableLogRouting
     *            {@link MDC} 에 {@link LogFeature#PROP_FEATURE} 값이 존재하는 경우 해당 값에 따라서 로그 분기 적용 여부
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public MethodExecutionElapsedTimeAspect(ApplicationContext context, boolean disableRepository, boolean handleIfOriginatedFromController, boolean enableLogRouting) {
        super(context, false, false, disableRepository, handleIfOriginatedFromController, enableLogRouting, true);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 8. 12.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * * @param context
     * 
     * @param disableRepository
     *            {@link Repository} 설정 클래스 AOP 미적용 여부
     * @param handleIfOriginatedFromController
     *            메소드 호출이 {@link Controller}에서부터 시작된 경우에만 AOP 적용 여부
     * @param enableLogRouting
     *            {@link MDC} 에 {@link LogFeature#PROP_FEATURE} 값이 존재하는 경우 해당 값에 따라서 로그 분기 적용 여부
     * @param enableIndentation
     *            들여쓰기 적용 여부
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public MethodExecutionElapsedTimeAspect(ApplicationContext context, boolean disableRepository, boolean handleIfOriginatedFromController, boolean enableLogRouting,
            boolean enableIndentation) {
        super(context, false, false, disableRepository, handleIfOriginatedFromController, enableLogRouting, enableIndentation);
    }

    public void after(String contextKey, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        StopWatch watch = (StopWatch) CONTEXT.remove(contextKey);
        watch.stop();

        logger.log("[{}] elapsed={}", getShortSignature(pjp), watch.getAsPretty());
    }

    /**
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect#afterController(String,
     *      open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect.Log, org.aspectj.lang.ProceedingJoinPoint)
     */
    @Override
    public void afterController(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        after(String.join("@", ELAPSED_TIME_CONTROLLER, aspectSign), logger, pjp);
    }

    /**
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect#afterRepository(String,
     *      open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect.Log, org.aspectj.lang.ProceedingJoinPoint)
     */
    @Override
    public void afterRepository(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        after(String.join("@", ELAPSED_TIME_REPOSITORY, aspectSign), logger, pjp);
    }

    /**
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect#afterService(String,
     *      open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect.Log, org.aspectj.lang.ProceedingJoinPoint)
     */
    @Override
    public void afterService(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        after(String.join("@", ELAPSED_TIME_SERVICE, aspectSign), logger, pjp);
    }

    public void before(String contextKey, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        logger.log("[{}] started", getShortSignature(pjp));

        StopWatch watch = new StopWatch();
        watch.start();
        CONTEXT.set(contextKey, watch);
    }

    /**
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect#beforeController(String,
     *      open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect.Log, org.aspectj.lang.ProceedingJoinPoint)
     */
    @Override
    public void beforeController(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        before(String.join("@", ELAPSED_TIME_CONTROLLER, aspectSign), logger, pjp);
    }

    /**
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect#beforeRepository(String,
     *      open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect.Log, org.aspectj.lang.ProceedingJoinPoint)
     */
    @Override
    public void beforeRepository(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        before(String.join("@", ELAPSED_TIME_REPOSITORY, aspectSign), logger, pjp);
    }

    /**
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect#beforeService(String,
     *      open.commons.spring.web.aspect.MethodExecutionElapsedTimeAspect.Log, org.aspectj.lang.ProceedingJoinPoint)
     */
    @Override
    public void beforeService(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        before(String.join("@", ELAPSED_TIME_SERVICE, aspectSign), logger, pjp);
    }
}
