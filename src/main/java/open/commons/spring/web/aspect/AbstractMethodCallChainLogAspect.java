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
 * Date  : 2025. 6. 23. 오후 1:50:08
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.aspect;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import open.commons.core.utils.ArrayUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.log.LogFeature;
import open.commons.spring.web.thread.MethodLogContext;

/**
 * {@link Controller}, {@link RestController}, {@link Service}, {@link Repository} 어노테이션이 설정된 클래스의 <code>public</code>
 * 메소드의 실행 전/후에 로그를 기록하는 기능을 제공합니다.<br>
 * </p>
 * 
 * <p>
 * 반드시 {@link #pointcutRootPackage()} 메소드를 구현하여 적용하는 프로그램의 최상위 경로를 설정해야 합니다.
 * </p>
 * 
 * 어노테이션별로 AOP가 적용되는 메소드를 아래와 같습니다.
 * <li>{@link Controller}, {@link RestController}: {@link #handleController(ProceedingJoinPoint)}
 * <li>{@link Service}: {@link #handleServicve(ProceedingJoinPoint)}
 * <li>{@link Repository}: {@link #handleRespository(ProceedingJoinPoint)}
 * </p>
 * 
 * 각 클래스별 메소드 실행 전/후의 로그는 아래 메소드들을 <code>overriding</code>하여 목적에 맞게 수정합니다.
 * 
 * <pre>
 * public void afterController(Log logger, ProceedingJoinPoint pjp) throws Throwable {
 *     Object[] msg = log(pjp, "afterController...");
 *     logger.log(msg);
 * }
 *
 * public void afterRepository(Log logger, ProceedingJoinPoint pjp) throws Throwable {
 *     Object[] msg = log(pjp, "afterRepository...");
 *     logger.log(msg);
 * }
 *
 * public void afterService(Log logger, ProceedingJoinPoint pjp) throws Throwable {
 *     Object[] msg = log(pjp, "beforeService...");
 *     logger.log(msg);
 * }
 *
 * public void beforeController(Log logger, ProceedingJoinPoint pjp) throws Throwable {
 *     Object[] msg = log(pjp, "beforeController...");
 *     logger.log(msg);
 * }
 *
 * public void beforeRepository(Log logger, ProceedingJoinPoint pjp) throws Throwable {
 *     Object[] msg = log(pjp, "beforeRepository...");
 *     logger.log(msg);
 * }
 *
 * public void beforeService(Log logger, ProceedingJoinPoint pjp) throws Throwable {
 *     Object[] msg = log(pjp, "beforeService...");
 *     logger.log(msg);
 * }
 * </pre>
 * 
 * @since 2025. 6. 23.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * 
 * @see Controller
 * @see RestController
 * @see Service
 * @see Repository
 * 
 */
public abstract class AbstractMethodCallChainLogAspect extends AbstractAspectPointcuts {

    /** Aspect 시점 holder key 생성자 */
    private static final Supplier<String> HOLDER_GEN = () -> UUID.randomUUID().toString();

    /** {@link Controller} 설정 클래스 AOP 미적용 여부 */
    private final boolean disableController;
    /** {@link Service} 설정 클래스 AOP 미적용 여부 */
    private final boolean disableService;
    /** {@link Repository} 설정 클래스 AOP 미적용 여부 */
    private final boolean disableRepository;
    /** 메소드 호출이 {@link Controller}에서부터 시작된 경우에만 AOP 적용 여부 */
    private final boolean handleIfOriginatedFromController;
    /** {@link MDC} 에 {@link LogFeature#PROP_FEATURE} 값이 존재하는 경우 해당 값에 따라서 로그 분기 적용 여부 */
    private final boolean enableLogRouting;
    /** 들여쓰기 적용 여부 */
    private final boolean enableIndentation;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMethodCallChainLogAspect(ApplicationContext context) {
        this(context, false, false, false, false, true, true);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     * @param disableRepository
     *            {@link Repository} 설정 클래스 AOP 미적용 여부
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMethodCallChainLogAspect(ApplicationContext context, boolean disableRepository) {
        this(context, false, false, disableRepository, false, true, true);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     * @param disableRepository
     *            {@link Repository} 설정 클래스 AOP 미적용 여부
     * @param handleIfOriginatedFromController
     *            메소드 호출이 {@link Controller}에서부터 시작된 경우에만 AOP 적용 여부
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMethodCallChainLogAspect(ApplicationContext context, boolean disableRepository, boolean handleIfOriginatedFromController) {
        this(context, false, false, disableRepository, handleIfOriginatedFromController, true, true);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 12.		박준홍			최초 작성
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
    public AbstractMethodCallChainLogAspect(ApplicationContext context, boolean disableRepository, boolean handleIfOriginatedFromController, boolean enableLogRouting) {
        this(context, false, false, disableRepository, handleIfOriginatedFromController, enableLogRouting, true);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 12.		박준홍			최초 작성
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
    public AbstractMethodCallChainLogAspect(ApplicationContext context, boolean disableRepository, boolean handleIfOriginatedFromController, boolean enableLogRouting,
            boolean enableIndentation) {
        this(context, false, false, disableRepository, handleIfOriginatedFromController, enableLogRouting, enableIndentation);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param context
     * @param disableController
     *            {@link Controller} 설정 클래스 AOP 미적용 여부
     * @param disableService
     *            {@link Service} 설정 클래스 AOP 미적용 여부
     * @param disableRepository
     *            {@link Repository} 설정 클래스 AOP 미적용 여부
     * @param handleIfOriginatedFromController
     *            메소드 호출이 {@link Controller}에서부터 시작된 경우에만 AOP 적용 여부
     * @param enableLogRouting
     *            {@link MDC} 에 {@link LogFeature#PROP_FEATURE} 값이 존재하는 경우 해당 값에 따라서 로그 분기 적용 여부
     * @param enableIndentation
     *            들여쓰기 적용 여부
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMethodCallChainLogAspect(ApplicationContext context, boolean disableController, boolean disableService, boolean disableRepository,
            boolean handleIfOriginatedFromController, boolean enableLogRouting, boolean enableIndentation) {
        super(context);
        this.disableController = disableController;
        this.disableService = disableService;
        this.disableRepository = disableRepository;
        this.handleIfOriginatedFromController = handleIfOriginatedFromController;
        this.enableLogRouting = enableLogRouting;
        this.enableIndentation = enableIndentation;
    }

    /**
     * {@link Controller}가 적용된 클래스의 메소드가 호출된 후에 실행됩니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     * 
     * @param aspectSign
     *            AOP가 적용되어 Aspect에 기반하여 메소드 호출을 잡은 위치의 식별정보
     * @param logger
     * @param pjp
     *
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void afterController(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        defaultAfterController(logger, pjp);
    }

    /**
     * {@link Repository}가 적용된 클래스의 메소드가 호출된 후에 실행됩니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     * 
     * @param aspectSign
     *            AOP가 적용되어 Aspect에 기반하여 메소드 호출을 잡은 위치의 식별정보
     * @param logger
     * @param pjp
     *
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void afterRepository(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        defaultAfterRepository(logger, pjp);
    }

    /**
     * {@link Service }가 적용된 클래스의 메소드가 호출된 후에 실행됩니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     * 
     * @param aspectSign
     *            AOP가 적용되어 Aspect에 기반하여 메소드 호출을 잡은 위치의 식별정보
     * @param logger
     * @param pjp
     *
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void afterService(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        defaultAfterService(logger, pjp);
    }

    /**
     * {@link Controller}가 적용된 클래스의 메소드가 호출되기 전에 실행됩니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     * 
     * @param aspectSign
     *            AOP가 적용되어 Aspect에 기반하여 메소드 호출을 잡은 위치의 식별정보
     * @param logger
     * @param pjp
     *
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void beforeController(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        defaultBeforeController(logger, pjp);
    }

    /**
     * {@link Repository}가 적용된 클래스의 메소드가 호출되기 전에 실행됩니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     * 
     * @param aspectSign
     *            AOP가 적용되어 Aspect에 기반하여 메소드 호출을 잡은 위치의 식별정보
     * @param logger
     * @param pjp
     *
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void beforeRepository(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        defaultBeforeRepository(logger, pjp);
    }

    /**
     * {@link Service}가 적용된 클래스의 메소드가 호출되기 전에 실행됩니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     * 
     * @param aspectSign
     *            AOP가 적용되어 Aspect에 기반하여 메소드 호출을 잡은 위치의 식별정보
     * @param logger
     * @param pjp
     *
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void beforeService(String aspectSign, Log logger, ProceedingJoinPoint pjp) throws Throwable {
        defaultBeforeService(logger, pjp);
    }

    protected final void defaultAfterController(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "afterController...");
        logger.log(msg);
    }

    protected final void defaultAfterRepository(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "afterRepository...");
        logger.log(msg);
    }

    protected final void defaultAfterService(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "afterService...");
        logger.log(msg);
    }

    protected final void defaultBeforeController(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "beforeController...");
        logger.log(msg);
    }

    protected final void defaultBeforeRepository(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "beforeRepository...");
        logger.log(msg);
    }

    protected final void defaultBeforeService(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "beforeService...");
        logger.log(msg);
    }

    protected String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getSimpleName();
    }

    protected final String getPackage(JoinPoint joinPoint) {
        String[] pkg = joinPoint.getTarget().getClass().getName().split("\\.");
        if (pkg.length < 2) {
            return "";
        } else {
            return String.join(".", ArrayUtils.copyOf(pkg, pkg.length - 1));
        }
    }

    protected String getShortPackage(Class<?> clazz) {
        String[] pkg = clazz.getName().split("\\.");
        if (pkg.length < 2) {
            return "";
        }

        int i = 0;
        for (; i < pkg.length - 1; i++) {
            pkg[i] = String.valueOf(pkg[i].charAt(0));
        }
        pkg[i] = "";

        return String.join(".", pkg);
    }

    protected String getShortPackage(JoinPoint joinPoint) {
        return getShortPackage(joinPoint.getTarget().getClass());
    }

    protected final String getShortSignature(JoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }

    /**
     * {@link Controller}, {@link RestController} 어노테이션이 적용된 클래스의 메소드를 처리합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param pjp
     * @return
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #withinAllControllerStereotypeComponent()
     * @see #pointcutRootPackage()
     */
    @Around("pointcutRootPackage() && withinAllControllerStereotypeComponent()")
    public Object handleController(ProceedingJoinPoint pjp) throws Throwable {
        if (this.disableController) {
            return pjp.proceed();
        }

        String aspectSign = UUID.randomUUID().toString();
        final String holder = HOLDER_GEN.get();
        try {
            // 메소드 실행 전
            beforeController(aspectSign, logger(MethodLogContext.getBeforeIncrement(holder, Controller.class)), pjp);
            // 메소드 실행
            return pjp.proceed();
        } finally {
            // 메소드실행 후
            try {
                afterController(aspectSign, logger(MethodLogContext.getAfterDecrement(holder)), pjp);
            } finally {
                MethodLogContext.clear(holder);
            }
        }
    }

    /**
     * {@link Repository} 어노테이션이 적용된 클래스의 메소드를 처리합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param pjp
     * @return
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #withinRepositoryStereotypeComponent()
     * @see #pointcutRootPackage()
     */
    @Around("pointcutRootPackage() && withinRepositoryStereotypeComponent()")
    public Object handleRespository(ProceedingJoinPoint pjp) throws Throwable {
        if (this.disableRepository) {
            return pjp.proceed();
        }
        // 쓰레드 호출이 Controller로부터 시작이 되지 않은 경우
        if (this.handleIfOriginatedFromController && !MethodLogContext.originatedFrom(Controller.class)) {
            return pjp.proceed();
        }

        String aspectSign = UUID.randomUUID().toString();
        final String holder = HOLDER_GEN.get();
        try {
            // 메소드 실행 전
            beforeRepository(aspectSign, logger(MethodLogContext.getBeforeIncrement(holder, Repository.class)), pjp);
            // 메소드 실행
            return pjp.proceed();
        } finally {
            // 메소드실행 후
            try {
                afterRepository(aspectSign, logger(MethodLogContext.getAfterDecrement(holder)), pjp);
            } finally {
                MethodLogContext.clear(holder);
            }
        }
    }

    /**
     * {@link Service} 어노테이션이 적용된 클래스의 메소드를 처리합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param pjp
     * @return
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #withinServiceStereotypeComponent()
     * @see #pointcutRootPackage()
     */
    @Around("pointcutRootPackage() && withinServiceStereotypeComponent()")
    public Object handleServicve(ProceedingJoinPoint pjp) throws Throwable {

        if (this.disableService) {
            return pjp.proceed();
        }

        // 쓰레드 호출이 Controller로부터 시작이 되지 않은 경우
        if (this.handleIfOriginatedFromController && !MethodLogContext.originatedFrom(Controller.class)) {
            return pjp.proceed();
        }

        String aspectSign = UUID.randomUUID().toString();
        final String holder = HOLDER_GEN.get();
        try {
            // 메소드 실행 전
            beforeService(aspectSign, logger(MethodLogContext.getBeforeIncrement(holder, Service.class)), pjp);
            // 메소드 실행
            return pjp.proceed();
        } finally {
            // 메소드실행 후
            try {
                afterService(aspectSign, logger(MethodLogContext.getAfterDecrement(holder)), pjp);
            } finally {
                MethodLogContext.clear(holder);
            }
        }
    }

    /**
     * 들여쓰기 단계에 따라 여백문자를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param indent
     * @return
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private final String indent(int indent) {
        return this.enableIndentation ? StringUtils.nTimesString(indentString(), indent) : "";
    }

    /**
     * 들여쓰기 문자를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected String indentString() {
        return "  ";
    }

    /**
     * 메소드 'Call-Chain'로그를 출력할 경우 해당 분기 이름을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 12.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 12.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected String loadCallChainFeature() {
        return "callchain";
    }

    /**
     * 간략한 클래스 이름과 메소드 정보를 접두어로 하는 로그 데이터를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param joinPoint
     * @param msg
     * @return
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected final Object[] log(JoinPoint joinPoint, String msg) {
        String pkg = getShortPackage(joinPoint.getTarget().getClass());
        String method = joinPoint.getSignature().toShortString();

        return new Object[] { "[{}{}] {}", pkg, method, msg };
    }

    /**
     * 로그를 기록하는 객체를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param indent
     *            들여쓰기 단계
     * @return
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private final Log logger(int indent) {
        Consumer<Object[]> logger = msgData -> {
            if (msgData == null || msgData.length < 1) {
                return;
            }
            // 첫번째 데이터가 문자열인 경우, '{}'을 포함한 포맷인지 확인
            String indentStr = indent(indent);
            String format = null;
            Object[] arguments = Arrays.copyOf(msgData, msgData.length);
            if (arguments[0] instanceof String) {
                if (((String) arguments[0]).contains("{}")) {
                    format = String.join("", "{}", ((String) arguments[0]));
                    arguments[0] = indentStr;
                } else {
                    format = "{}{}";
                    arguments = ArrayUtils.prepend(arguments, indentStr);
                }
            } else {
                format = "{}{}";
                arguments = ArrayUtils.prepend(arguments, indentStr);
            }

            // this.logger.info(format, arguments);
            wrap(this.logger::info, format, arguments);

        };
        return new Log(logger);
    }

    /**
     * 응용 프로그램의 최상의 패키지 경로를 {@link Pointcut} 으로 제공합니다. <br>
     * 
     * 
     * <pre>
     * &#64;Component
     * &#64;Aspect
     * public class AspectConfig extends AbstractMethodCallChainLogAspect {
     *     public AspectConfig(@NotNull ApplicationContext context) {
     *         super(context);
     *     }
     * 
     *     &#64;Pointcut("within(com.yourcompany.yourapp..*)")
     *     &#64;Override
     *     public void pointcutRootPackage() {
     *     }
     * }
     * </pre>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 23.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public abstract void pointcutRootPackage();

    private void wrap(BiConsumer<String, Object[]> f, String format, Object[] args) {
        Map<String, String> currentMDC = MDC.getCopyOfContextMap();

        String feature = this.enableLogRouting ? MDC.get(LogFeature.PROP_FEATURE) : null;
        String callchainfeature = StringUtils.isNullOrEmptyString(feature) ? loadCallChainFeature() : String.join("-", feature, loadCallChainFeature());
        MDC.put(LogFeature.PROP_FEATURE, callchainfeature);
        f.accept(format, args);

        if (currentMDC != null) {
            MDC.setContextMap(currentMDC);
        }
    }

    protected class Log {
        private final Consumer<Object[]> logger;

        Log(Consumer<Object[]> logger) {
            this.logger = logger;
        }

        /**
         * {@link Logger#info(String, Object...)}를 이용해서 로그를 출력합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param msg
         *            메시지 포맷과 데이터를 포함한 배열.<br>
         *            일반적인 데이터 순서는 다음과 같습니다.
         *            <li>0 : '{}'가 포함된 메세지 포맷
         *            <li>1.. : 실제 데이터
         *
         * @since 2025. 6. 23.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         * 
         * @see Logger#info(String, Object...)
         */
        public void log(Object... msg) {
            logger.accept(msg);
        }
    }
}
