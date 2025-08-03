/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2020. 1. 21. 오후 1:20:51
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.mvc.service;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import open.commons.core.Result;
import open.commons.core.TwoValueObject;
import open.commons.core.function.Runner;
import open.commons.core.test.StopWatch;
import open.commons.core.utils.ConvertUtils;
import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.config.ResourceConfiguration;

/**
 * 
 * @since 2020. 1. 21.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class AbstractComponent {

    /** Logger */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /** ThreadPool Executor */
    @Autowired
    @Qualifier(ResourceConfiguration.BEAN_QUALIFIER_BUILTIN_THREAD_POOL_TASK_EXECUTOR)
    protected ThreadPoolTaskExecutor threadpool;

    /** 공통 설정 정보 */
    @Autowired
    protected Environment env;

    /** Context */
    @Autowired
    protected ApplicationContext context;

    /** Active profiles */
    protected String[] profile;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------   
     * 2020. 1. 21.		박준홍			최초 작성
     * </pre>
     *
     * @since 2020. 1. 21.
     * @version
     */
    public AbstractComponent() {
    }

    /**
     * 에러 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param msg
     *            에러 메시지
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public final <T> Result<T> error(String msg) {
        return Result.error(msg);
    }

    /**
     * 에러 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param format
     *            에러 메시지 포맷
     * @param args
     *            메시지 데이터
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public final <T> Result<T> error(String format, Object... args) {
        return Result.error(format, args);
    }

    /**
     * 에러 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 8. 24.     박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     *            데이터
     * @param data
     *            메시지
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unchecked")
    public final <T> Result<T> error(T data, String msg) {
        return (Result<T>) Result.error(msg).setData(data);
    }

    /**
     * 에러 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     *            데이터
     * @param format
     *            메시지 포맷
     * @param args
     *            메시지 데이터
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    @SuppressWarnings("unchecked")
    public final <T> Result<T> error(T data, String format, Object... args) {
        return (Result<T>) Result.error(format, args).setData(data);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            작업용 파라미터 타입.
     * @param action
     *            실행할 작업
     * @param param
     *            작업용 파라미터
     * @param job
     *            작업명 (로그용)
     *
     * @since 2021. 11. 9.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    public final <T> void execute(Consumer<T> action, T param, String job) {
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            action.accept(param);
        } finally {
            watch.stop();
            logger.trace("[{} 완료] elapsed={}", job, watch.getAsPretty());
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param <R>
     *            반환할 데이터 타입.
     * @param <T>
     *            작업용 파라미터 타입.
     * @param action
     *            실행할 작업
     * @param param
     *            작업용 파라미터
     * @param job
     *            작업명 (로그용)
     * @return
     *
     * @since 2021. 11. 9.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    public final <R, T> R execute(Function<T, R> action, T param, String job) {
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            return action.apply(param);
        } finally {
            watch.stop();
            logger.trace("[{} 완료] elapsed={}", job, watch.getAsPretty());
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param action
     *            실행할 작업
     * @param job
     *            작업명. (로그를 위해서)
     * @return
     *
     * @since 2021. 11. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public final void execute(Runner action, String job) {
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            action.run();
        } finally {
            watch.stop();
            logger.trace("[{} 완료] elapsed={}", job, watch.getAsPretty());
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            반환 데이터 타입.
     * @param action
     *            실행할 작업
     * @param job
     *            작업명. (로그를 위해서)
     * @return
     *
     * @since 2021. 10. 4.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public final <T> T execute(Supplier<T> action, String job) {
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            return action.get();
        } finally {
            watch.stop();
            logger.trace("[{} 완료] elapsed={}", job, watch.getAsPretty());
        }
    }

    /**
     * {@link SpringBootApplication} 클래스의 main 함수에 전달된 파라미터에서 필요한 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 5. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            파라미터 타입.
     * @param mainArgs
     *            파라미터를 포함하고 있는 객체
     * @param argName
     *            파라미터 이름
     * @param valueType
     *            파라미터 데이터 Class
     * @return
     *
     * @since 2022. 5. 4.
     * @version 0.5.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <T> List<T> getMultiValuesArgument(@NotNull ApplicationArguments mainArgs, @NotNull String argName, @NotNull Class<T> valueType) {
        if (mainArgs == null) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "'{}'은 반드시 설정되어야 합니다. 값=null", ApplicationArguments.class);
        }

        List<String> argValues = mainArgs.getOptionValues(argName);
        if (argValues == null || argValues.size() < 1 || argValues.get(0) == null || argValues.get(0).trim().isEmpty()) {
            return null;
        }

        return argValues.stream() //
                .filter(argValue -> argValue != null && !argValue.trim().isEmpty()) //
                .map(argValue -> ConvertUtils.toPrimitiveTypeValue(valueType, argValue)) //
                .collect(Collectors.toList());
    }

    /**
     * {@link SpringBootApplication} 클래스의 main 함수에 전달된 파라미터에서 필요한 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 5. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            파라미터 타입.
     * @param mainArgs
     *            파라미터를 포함하고 있는 객체
     * @param argNameTypes
     *            <ul>
     *            <li>key: 파라미터 이름
     *            <li>value: 파라미터 값
     *            </ul>
     * @return
     *
     * @since 2022. 5. 4.
     * @version 0.5.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    protected Map<String, List<Object>> getMultiValuesArguments(@NotNull ApplicationArguments mainArgs, @NotNull Map<String, Class<?>> argNameTypes) {
        if (mainArgs == null) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "'{}'은 반드시 설정되어야 합니다. 값=null", ApplicationArguments.class);
        }

        return argNameTypes.entrySet().stream() //
                .map(nt -> new TwoValueObject<String, List<?>>(nt.getKey(), getMultiValuesArgument(mainArgs, nt.getKey(), nt.getValue()))) //
                .collect(Collectors.toMap(o -> o.first, o -> (List<Object>) o.second));
    }

    /**
     * {@link SpringBootApplication} 클래스의 main 함수에 전달된 파라미터에서 필요한 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 5. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            파라미터 타입.
     * @param mainArgs
     *            파라미터를 포함하고 있는 객체
     * @param argName
     *            파라미터 이름
     * @param valueType
     *            파라미터 데이터 Class
     * @return
     *
     * @since 2022. 5. 4.
     * @version 0.5.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    protected <T> T getSingleValueArgument(@NotNull ApplicationArguments mainArgs, @NotNull String argName, @NotNull Class<T> valueType) {
        if (mainArgs == null) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "'{}'은 반드시 설정되어야 합니다. 값=null", ApplicationArguments.class);
        }

        List<String> argValues = mainArgs.getOptionValues(argName);
        if (argValues == null || argValues.size() < 1 || argValues.get(0) == null || argValues.get(0).trim().isEmpty()) {
            return null;
        }

        String argValue = argValues.get(0);
        return String.class.equals(valueType) ? (T) argValue : ConvertUtils.toPrimitiveTypeValue(valueType, argValue);
    }

    /**
     * {@link SpringBootApplication} 클래스의 main 함수에 전달된 파라미터에서 필요한 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 5. 4.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     *            파라미터 타입.
     * @param mainArgs
     *            파라미터를 포함하고 있는 객체
     * @param argNameTypes
     *            <ul>
     *            <li>key: 파라미터 이름
     *            <li>value: 파라미터 값
     *            </ul>
     * @return
     *
     * @since 2022. 5. 4.
     * @version 0.5.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Map<String, Object> getSingleValueArguments(@NotNull ApplicationArguments mainArgs, @NotNull Map<String, Class<?>> argNameTypes) {
        if (mainArgs == null) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "'{}'은 반드시 설정되어야 합니다. 값=null", ApplicationArguments.class);
        }

        return argNameTypes.entrySet().stream() //
                .map(nt -> new TwoValueObject<String, Object>(nt.getKey(), getSingleValueArgument(mainArgs, nt.getKey(), nt.getValue()))) //
                .collect(Collectors.toMap(o -> o.first, o -> o.second));
    }

    @PostConstruct
    public void init() {
        this.profile = this.env.getActiveProfiles();
    }

    /**
     * 성공 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     *            데이터
     * @param message
     *            메시지
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public final <T> Result<T> success(T data, String message) {
        return Result.success(data).setMessage(message);
    }

    /**
     * 성공 결과 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 8. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param data
     *            데이터
     * @param format
     *            메시지 포맷
     * @param args
     *            메시지 데이터
     * @return
     *
     * @since 2021. 8. 24.
     * @version 0.3.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public final <T> Result<T> success(T data, String format, Object... args) {
        return Result.success(data).setMessage(format, args);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param isParallel
     *            Parallel {@link Stream} 생성 여부
     * @param values
     *            데이터
     * @return
     *
     * @since 2021. 12. 15.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     * 
     * @see Stream#of(Object...)
     * @see Stream#parallel()
     */
    @SuppressWarnings("unchecked")
    public static <T> Supplier<Stream<T>> streamOf(boolean isParallel, T... values) {
        return () -> isParallel ? Stream.of(values).parallel() : Stream.of(values);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param parallelProfile
     *            Parallel {@link Stream}을 요구하는 'Profile'
     * @param currentProfile
     *            현재 'Profile'
     * @param values
     *            데이터
     * @return
     *
     * @since 2021. 12. 15.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     * 
     * @see #streamOf(boolean, Object...)
     */
    @SuppressWarnings("unchecked")
    public static <T> Supplier<Stream<T>> streamOf(@NotNull String parallelProfile, String currentProfile, T... values) {
        return streamOf(parallelProfile.equalsIgnoreCase(currentProfile), values);
    }
}
