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
 * Date  : 2025. 6. 23. 오후 1:50:08
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.aspect;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Consumer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import open.commons.core.utils.ArrayUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.thread.MethodContextHandler;

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
public abstract class AbstractMethodLogAspect extends AbstractAspectPointcuts {

    private final boolean disableController;
    private final boolean disableService;
    private final boolean disableRepository;

    private final boolean handleIfOriginatedFromController;

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
    public AbstractMethodLogAspect(ApplicationContext context) {
        this(context, null, false, false, false, false);
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
    public AbstractMethodLogAspect(ApplicationContext context, boolean disableRepository) {
        this(context, null, false, false, disableRepository, false);
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
    public AbstractMethodLogAspect(ApplicationContext context, boolean disableRepository, boolean handleIfOriginatedFromController) {
        this(context, null, false, false, disableRepository, handleIfOriginatedFromController);
    }

    /**
     * 
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
     * @param logger
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMethodLogAspect(ApplicationContext context, Logger logger) {
        this(context, logger, false, false, false, false);
    }

    /**
     * 
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
     * @param logger
     * @param disableRepository
     *            {@link Repository} 설정 클래스 AOP 미적용 여부
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMethodLogAspect(ApplicationContext context, Logger logger, boolean disableRepository) {
        this(context, logger, false, false, disableRepository, false);
    }

    /**
     * 
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
     * @param logger
     * @param disableRepository
     *            {@link Repository} 설정 클래스 AOP 미적용 여부
     * @param handleIfOriginatedFromController
     *            메소드 호출이 {@link Controller}에서부터 시작된 경우에만 AOP 적용 여부
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMethodLogAspect(ApplicationContext context, Logger logger, boolean disableRepository, boolean handleIfOriginatedFromController) {
        this(context, logger, false, false, disableRepository, handleIfOriginatedFromController);
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
     * @param logger
     * @param disableController
     *            {@link Controller} 설정 클래스 AOP 미적용 여부
     * @param disableService
     *            {@link Service} 설정 클래스 AOP 미적용 여부
     * @param disableRepository
     *            {@link Repository} 설정 클래스 AOP 미적용 여부
     * @param handleIfOriginatedFromController
     *            메소드 호출이 {@link Controller}에서부터 시작된 경우에만 AOP 적용 여부
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMethodLogAspect(ApplicationContext context, Logger logger, boolean disableController, boolean disableService, boolean disableRepository,
            boolean handleIfOriginatedFromController) {
        super(context, logger);
        this.disableController = disableController;
        this.disableService = disableService;
        this.disableRepository = disableRepository;
        this.handleIfOriginatedFromController = handleIfOriginatedFromController;
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
     * @param logger
     * @param pjp
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void afterController(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "afterController...");
        logger.log(msg);
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
     * @param logger
     * @param pjp
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void afterRepository(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "afterRepository...");
        logger.log(msg);
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
     * @param logger
     * @param pjp
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void afterService(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "beforeService...");
        logger.log(msg);
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
     * @param logger
     * @param pjp
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void beforeController(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "beforeController...");
        logger.log(msg);
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
     * @param logger
     * @param pjp
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void beforeRepository(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "beforeRepository...");
        logger.log(msg);
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
     * @param logger
     * @param pjp
     * @throws Throwable
     *
     * @since 2025. 6. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public void beforeService(Log logger, ProceedingJoinPoint pjp) throws Throwable {
        Object[] msg = log(pjp, "beforeService...");
        logger.log(msg);
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

        final String holder = UUID.randomUUID().toString();
        int indent = MethodContextHandler.getBeforeIncrement(holder, Controller.class);
        try {
            // 메소드 실행 전
            beforeController(logger(indent), pjp);
            // 메소드 실행
            return pjp.proceed();
        } finally {
            indent = MethodContextHandler.getAfterDecrement(holder);
            // 메소드실행 후
            afterController(logger(indent), pjp);

            MethodContextHandler.clear(holder);
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
        if (this.handleIfOriginatedFromController && !MethodContextHandler.originatedFrom(Controller.class)) {
            return pjp.proceed();
        }

        final String holder = UUID.randomUUID().toString();
        int indent = MethodContextHandler.getBeforeIncrement(holder, Service.class);
        try {
            // 메소드 실행 전
            beforeRepository(logger(indent), pjp);
            // 메소드 실행
            return pjp.proceed();
        } finally {
            indent = MethodContextHandler.getAfterDecrement(holder);
            // 메소드실행 후
            afterRepository(logger(indent), pjp);

            MethodContextHandler.clear(holder);
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
        if (this.handleIfOriginatedFromController && !MethodContextHandler.originatedFrom(Controller.class)) {
            return pjp.proceed();
        }

        final String holder = UUID.randomUUID().toString();
        int indent = MethodContextHandler.getBeforeIncrement(holder, Repository.class);
        try {
            // 메소드 실행 전
            beforeService(logger(indent), pjp);
            // 메소드 실행
            return pjp.proceed();
        } finally {
            indent = MethodContextHandler.getAfterDecrement(holder);
            // 메소드실행 후
            afterService(logger(indent), pjp);

            MethodContextHandler.clear(holder);
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
        return StringUtils.nTimesString(indentString(), indent);
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
            this.logger.info(format, arguments);
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
     * public class AspectConfig extends AbstractMethodLogAspect {
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
