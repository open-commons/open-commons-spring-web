/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 9. 9. 오후 5:33:43
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.event;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import open.commons.core.Result;
import open.commons.core.concurrent.ConcurrentWorker;
import open.commons.core.concurrent.Mutex;
import open.commons.core.exception.AsyncExecutionException;
import open.commons.core.lang.DefaultRunnable;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.IOUtils;
import open.commons.core.utils.MapUtils;
import open.commons.core.utils.ThreadUtils;
import open.commons.spring.web.mvc.service.AbstractComponent;
import open.commons.spring.web.servlet.BadRequestException;

/**
 * Spring Event 시스템 기반 데이터 모니터링 서비스
 * 
 * @since 2021. 9. 9.
 * @version 0.4.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public abstract class AbstractEventDrivenMonitor extends AbstractComponent implements IEventDrivenService, InitializingBean, DisposableBean {

    private static final Supplier<Set<Object>> SET_OBJECT = () -> new HashSet<>();

    /** 이벤트 분배 모듈 */
    private final ApplicationEventPublisher eventPub;
    /** 비동기 작업 실행 모듈 */
    private final ThreadPoolTaskExecutor mtrExecutor;

    /** '구독해제' 파라미터 최대 유효 시간. 단위: ms */
    private final long unsubsTtl;
    /** '구독해제' 파라미터 감시 간격. 단위: ms */
    private final long unsubsInterval;
    /** '구독해제' 파라미터 제거 모듈 */
    private UnsubscriedParametersClosure unsubsParamsClosure;

    /**
     * 이벤트 타입별 제공자
     * <ul>
     * <li>key: 이벤트 타입.
     * <li>value: 이벤트 제공 함수.
     * </ul>
     */
    protected final ConcurrentSkipListMap<String, Function<Object, IEventObject<?, ? extends IEventStatus>>> providers = new ConcurrentSkipListMap<>();
    protected final Mutex mutexProviders = new Mutex("mutex for 'Event Provider'");

    /**
     * 구독요청 이벤트 타입별 파라미터.
     * <ul>
     * <li>key: 이벤트 타입.
     * <li>value: 이벤트 생성을 위한 비동기 작업 파라미터.
     * </ul>
     */
    protected SubscribedParameterManager paramsSubscribed;

    /**
     * 비동기 작업에서 사용 중인 타입별 파라미터.
     * <ul>
     * <li>key: 이벤트 타입.
     * <li>value: 이벤트 생성을 위한 비동기 작업 파라미터.
     * </ul>
     */
    protected final AsyncParameterManager paramsAsync = new AsyncParameterManager();

    /**
     * 이벤트 타입별 파라미터.
     * <ul>
     * <li>key: 이벤트 타입 문자열
     * <li>value: 이벤트 타입 class
     * </ul>
     */
    protected final ConcurrentSkipListMap<String, Class<?>> eventTypes = new ConcurrentSkipListMap<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param eventPub
     *            이벤트 분배 모듈
     * @param mtrExecutor
     *            비동기 작업 실행 모듈
     * @param unsubsTtl
     *            '구독해제' 파라미터 최대 유효 시간. 단위: ms
     * @param unsubsInterval
     *            '구독해제' 파라미터 감시 간격. 단위: ms
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public AbstractEventDrivenMonitor(@NotNull ApplicationEventPublisher eventPub, @NotNull ThreadPoolTaskExecutor mtrExecutor, @Min(1) long unsubsTtl,
            @Min(1) long unsubsInterval) {
        this.eventPub = eventPub;
        this.mtrExecutor = mtrExecutor;
        this.unsubsTtl = unsubsTtl;
        this.unsubsInterval = unsubsInterval;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @throws Exception
     *
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // '구독해제요청' 파라미터 자원 해제
        this.unsubsParamsClosure = new UnsubscriedParametersClosure(this.unsubsTtl, this.unsubsInterval);
        this.unsubsParamsClosure.start(true);

        // '구독요청' 관리자
        this.paramsSubscribed = new SubscribedParameterManager(this.unsubsParamsClosure);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @throws Exception
     *
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    @Override
    public void destroy() throws Exception {
        try {
            this.mtrExecutor.getThreadPoolExecutor().shutdown();
            this.threadpool.getThreadPoolExecutor().shutdownNow();
            IOUtils.close(this.unsubsParamsClosure);
        } catch (Exception ignored) {
        }
    }

    /**
     * 등록된 이벤트 제공자를 실행하고, 생성된 이벤트를 게시한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 9.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void execute() {

        synchronized (mutexProviders) {
            String eventTypeKey = null;
            Function<Object, IEventObject<?, ? extends IEventStatus>> provider = null;
            Set<Object> params = null;

            Future<?> asyncJob = null;
            for (Entry<String, Function<Object, IEventObject<?, ? extends IEventStatus>>> entry : this.providers.entrySet()) {
                eventTypeKey = entry.getKey();
                // #1. 이벤트 타입에 대한 파라미터별로 작업 실행 및 완료 대기를 위해
                if ((params = this.paramsSubscribed.getParameters(eventTypeKey)) == null) {
                    continue;
                }

                logger.trace("[execute] subscribed -> async. event={}, size={}", eventTypeKey, params.size());

                provider = entry.getValue();

                for (Object param : params) {
                    // 비동기 작업에서 사용중인 파라미터로
                    this.paramsAsync.addParameter(eventTypeKey, param);

                    try {
                        Worker<IEventObject<?, ?>> worker = new Worker<IEventObject<?, ?>>(provider, param, this.eventPub);
                        asyncJob = this.mtrExecutor.submit(worker);

                        // #2. 비동기 작업 종료 및 이벤트 타입별 파라미터 추가.
                        this.mtrExecutor.submit(new AsyncJobWaiter(asyncJob, eventTypeKey, param));

                    } catch (TaskRejectedException ignored) {
                        logger.warn("데이터 모니터링 요청이 거절되었습니다. 원인={}", ignored.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Class<?> 정보를 {@link ConcurrentSkipListMap}의 'key'로 사용하기 위해서 문자열로 변환. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 9. 9.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param eventTypeKey
     * @return
     *
     * @since 2021. 9. 9.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private String getEventTypeKey(Class<?> eventType) {
        String eventTypeStr = eventType.toGenericString();
        this.eventTypes.put(eventTypeStr, eventType);
        return eventTypeStr;
    }

    /**
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.spring.web.event.IEventDrivenService#getParameters()
     */
    @Override
    public Map<String, Set<Object>> getParameters() {
        return this.paramsSubscribed.getEventParameters();
    }

    /**
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.spring.web.event.IEventDrivenService#registerEventProvider(java.lang.Class,
     *      java.util.function.Function)
     * @see {@link HashSet#add(Object)}: 파라미터 저장 객체
     * @see {@link Object#hashCode()}: 파라미터 추가를 위해 사용
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T, E extends IEventStatus, C extends IEventObject<T, E>, P> void registerEventProvider(@NotNull Class<C> eventType, @NotNull Function<P, C> provider) {
        synchronized (mutexProviders) {
            this.providers.put(getEventTypeKey(eventType), (Function<Object, IEventObject<?, ? extends IEventStatus>>) provider);

            logger.info("'{}' 이벤트 제공자가 등록되었습니다. 제공자={}", eventType, provider);
        }
    }

    /**
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.spring.web.event.IEventDrivenService#subscribe(java.lang.Class, java.lang.Object)
     * @see {@link HashSet#add(Object)}: 파라미터 저장 객체
     * @see {@link Object#hashCode()}: 파라미터 추가를 위해 사용
     */
    @Override
    public <T, E extends IEventStatus, C extends IEventObject<T, E>, P> Result<Boolean> subscribe(@NotNull Class<C> eventType, @NotNull P parameter) {
        if (parameter == null) {
            throw ExceptionUtils.newException(BadRequestException.class, "이벤트 생성용 파라미터는 null 일 수가 없습니다.");
        }

        synchronized (mutexProviders) {
            if (!this.providers.containsKey(getEventTypeKey(eventType))) {
                String msg = String.format("'%s' 이벤트를 제공하는 모듈이 존재하지 않습니다.", eventType.getName());
                logger.warn(msg);
                return error(msg);
            }

            String msg = null;
            // #1. 비동가 실행 중인 작업에 사용 중인지 확인.
            if (this.paramsAsync.contains(getEventTypeKey(eventType), parameter)) {
                msg = String.format("현재 구독 중인 파라미터 입니다. eventTypeKey={}, param={}", eventType, parameter);
            } else {
                this.paramsSubscribed.addParameter(getEventTypeKey(eventType), parameter, true);
                msg = "";
            }
            return success(true, msg);
        }
    }

    /**
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.spring.web.event.IEventDrivenService#unsubscribe(java.lang.Class, java.lang.Object)
     */
    @Override
    public <T, E extends IEventStatus, C extends IEventObject<T, E>, P> Result<Boolean> unsubscribe(@NotNull Class<C> eventType, @NotNull P parameter) {
        if (parameter == null) {
            throw ExceptionUtils.newException(BadRequestException.class, "이벤트 생성용 파라미터는 null 일 수가 없습니다.");
        }

        String eventTypeKey = getEventTypeKey(eventType);
        // #1. 비동기 실행 중인 작업에 사용 중인지 확인.
        if (this.paramsAsync.contains(eventTypeKey, parameter)) {
            // #2. 사용 중인 경우 '구독해제' 대기 목록에 추가
            UnsubscribedParameter up = new UnsubscribedParameter(eventTypeKey, parameter);
            this.unsubsParamsClosure.push(up);
        } else {
            // #3. 사용 중이 아닌 경우 '구독' 목록에서 삭제
            this.paramsSubscribed.remove(eventTypeKey, parameter);
        }

        return success(true, "");
    }

    /**
     * 비동기 작업이 종료된 후 추가적인 작업을 위한 클래스.
     * 
     * @param <T>
     * @param <E>
     * @param <C>
     * @since 2021. 9. 14.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected class AsyncJobWaiter implements Runnable {

        private final Future<?> asyncJob;
        private final String eventTypeKey;
        private final Object parameter;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         * 
         * @param asyncJob
         *            비동기 작업.
         * @param eventTypeKey
         *            TODO
         * @param parameter
         *            이벤트 생성을 위한 비동기 작업 파라미터.
         * @since 2021. 9. 14.
         */
        public AsyncJobWaiter(@NotNull Future<?> asyncJob, @NotNull String eventTypeKey, @NotNull Object parameter) {
            this.asyncJob = asyncJob;
            this.eventTypeKey = eventTypeKey;
            this.parameter = parameter;
        }

        /**
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            try {
                this.asyncJob.get();
            } catch (InterruptedException | ExecutionException ignored) {
                throw new AsyncExecutionException("비동기 작업 실행 대기 중 에러 발생.", ignored);
            } finally {
                // #1. '비동기 작업' 파라미터에서 해제
                paramsAsync.remove(this.eventTypeKey, this.parameter);
                // #2. '구독' 파라미터 목록에 추가.
                paramsSubscribed.addParameter(this.eventTypeKey, this.parameter, false);
            }
        }
    }

    /**
     * 비동기 작업에 사용 중인 파라미터 관리자
     * 
     * @since 2021. 9. 14.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected class AsyncParameterManager {

        protected final Logger logger = LoggerFactory.getLogger(getClass());
        /**
         * 비동기 작업에서 사용 중인 타입별 파라미터.
         * <ul>
         * <li>key: 이벤트 타입.
         * <li>value: 이벤트 생성을 위한 비동기 작업 파라미터.
         * </ul>
         */
        protected final ConcurrentSkipListMap<String, Set<Object>> eventParameters = new ConcurrentSkipListMap<>();
        protected final Mutex mutexParams = new Mutex("mutex for 'Async Parameters'");

        /**
         * 비동기 작업에 사용중인 파라미터 목록에 추가한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param eventTypeKey
         * @param parameter
         *
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         */
        public void addParameter(@NotNull String eventTypeKey, @NotNull Object parameter) {
            synchronized (this.mutexParams) {
                Set<Object> parameters = MapUtils.getOrDefault(this.eventParameters, eventTypeKey, SET_OBJECT, true);
                parameters.add(parameter);

                logger.trace("[async] (add) event={}, param={}, size={}", eventTypeKey, parameter, parameters.size());
            }
        }

        /**
         * 모든 파라미터를 삭제한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         *
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         */
        public void clear() {
            synchronized (this.mutexParams) {
                this.eventParameters.clear();
            }
        }

        /**
         * 비동기 실행 중인 작업에 사용 중인지 여부를 제공. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param eventTypeKey
         *            이벤트 타입
         * @param parameter
         *            이벤트 생성을 위한 비동기 작업 파라미터
         * @return
         *
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         * 
         */
        public boolean contains(String eventTypeKey, Object parameter) {
            synchronized (this.mutexParams) {
                Set<Object> parameters = this.eventParameters.get(eventTypeKey);

                boolean contains = parameters != null ? parameters.contains(parameter) : false;

                logger.trace("[async] contains={}, event={}, param={}, parameters={}", contains, eventTypeKey, parameter, parameters);

                return contains;
            }
        }

        /**
         * 파라미터를 삭제한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param eventTypeKey
         *            이벤트 타입
         * @param parameter
         *            이벤트 생성을 위한 비동기 작업 파라미터
         * @return
         *
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         */
        public boolean remove(String eventTypeKey, Object parameter) {
            synchronized (this.mutexParams) {
                Set<Object> set = MapUtils.getOrDefault(this.eventParameters, eventTypeKey, SET_OBJECT, true);

                boolean removed = set.remove(parameter);

                logger.trace("[async] (delete) event={}, param={}, size={}", eventTypeKey, parameter, set.size());

                return removed;
            }
        }

        /**
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("AsyncParameterManager [eventParameters=");
            builder.append(eventParameters);
            builder.append(", mutexParams=");
            builder.append(mutexParams);
            builder.append("]");
            return builder.toString();
        }

    }

    /**
     * '구독 요청' 파라미터 관리자.
     * 
     * @since 2021. 9. 14.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected class SubscribedParameterManager {

        protected final Logger logger = LoggerFactory.getLogger(getClass());

        /**
         * 구독요청 이벤트 타입별 파라미터.
         * <ul>
         * <li>key: 이벤트 타입.
         * <li>value: 이벤트 생성을 위한 비동기 작업 파라미터.
         * </ul>
         */
        protected final ConcurrentSkipListMap<String, Set<Object>> eventParameters = new ConcurrentSkipListMap<>();
        protected final Mutex mutexParams = new Mutex("mutex for 'Subscribed Parameters'");
        /** '구독해제' 파라미터 관리자 */
        private final UnsubscriedParametersClosure unsubsParamsClosure;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param unsubsParamsClosure
         * @since 2021. 9. 14.
         */
        public SubscribedParameterManager(UnsubscriedParametersClosure unsubsParamsClosure) {
            this.unsubsParamsClosure = unsubsParamsClosure;
        }

        /**
         * 구독 요청 파라미터 목록에 추가한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param <T>
         *            이벤트 정보 객체
         * 
         * @param <E>
         *            이벤트 상태.
         * @param <C>
         *            이벤트 타입.
         * @param <P>
         *            이벤트 생성을 위해서 사용되는 데이터 타입.
         * @param eventTypeKey
         *            이벤트 타입.
         * @param parameter
         *            이벤트 생성을 위해서 사용되는 데이터 타입.
         * @param isNew
         *            신규 구독신청 여부
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         */
        public <T, E extends IEventStatus, C extends IEventObject<T, E>, P> boolean addParameter(@NotNull String eventTypeKey, @NotNull P parameter, boolean isNew) {
            synchronized (this.mutexParams) {
                Set<Object> parameters = MapUtils.getOrDefault(this.eventParameters, eventTypeKey, SET_OBJECT, true);
                boolean containsUnsubs = this.unsubsParamsClosure.contains(eventTypeKey, parameter);

                boolean added = false;
                // 새로운 구독 요청인 경우
                if (isNew) {
                    // '구독 해제' 목록에 있는 경우
                    if (containsUnsubs) {
                        this.unsubsParamsClosure.removeForcely(eventTypeKey, parameter);
                    }
                    added = parameters.add(parameter);
                } else
                // '구독 해제' 목록에 없는 경우
                if (!containsUnsubs) {
                    added = parameters.add(parameter);
                }

                logger.trace("[subscribed] added={}, event={}, parameter={}. size={}", added, eventTypeKey, parameter, parameters.size());

                return added;
            }
        }

        /**
         * 현재 파라미터 정보를 제공한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         */
        public Map<String, Set<Object>> getEventParameters() {
            synchronized (this.mutexParams) {
                return Collections.unmodifiableMap(this.eventParameters);
            }
        }

        /**
         * 이벤트 타입에 구독된 파라미터 목록을 제공한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param eventTypeKey
         *            이벤트 타입 식별정보
         * @return
         *
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         * 
         * @see ConcurrentSkipListMap#remove(Object)
         */
        public Set<Object> getParameters(String eventTypeKey) {
            synchronized (this.mutexParams) {
                return this.eventParameters.remove(eventTypeKey);
            }
        }

        /**
         * '구독 요청' 파라미터를 제거한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param eventTypeKey
         * @param parameter
         * @return
         *
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         */
        public boolean remove(String eventTypeKey, Object parameter) {
            synchronized (this.mutexParams) {
                Set<Object> parameters = MapUtils.getOrDefault(this.eventParameters, eventTypeKey, SET_OBJECT, true);

                boolean removed = parameters.remove(parameter);

                logger.trace("[subscribed] removed={}, event={}, parameter={}. size={}", removed, eventTypeKey, parameter, parameters.size());

                return removed;
            }
        }
    }

    /**
     * 구독해제된 파라미터
     * 
     * @since 2021. 9. 14.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected class UnsubscribedParameter {
        private final String eventTypeKey;
        private final Object parameter;
        private final long timestamp;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         * 
         * @param eventTypeKey
         *            TODO
         * @param parameter
         *
         * @since 2021. 9. 14.
         */
        public UnsubscribedParameter(@NotNull String eventTypeKey, @NotNull Object parameter) {
            this.eventTypeKey = eventTypeKey;
            this.parameter = parameter;
            this.timestamp = System.currentTimeMillis();
        }

        /**
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
            UnsubscribedParameter other = (UnsubscribedParameter) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (eventTypeKey == null) {
                if (other.eventTypeKey != null)
                    return false;
            } else if (!eventTypeKey.equals(other.eventTypeKey))
                return false;
            if (parameter == null) {
                if (other.parameter != null)
                    return false;
            } else if (!parameter.equals(other.parameter))
                return false;
            return true;
        }

        /**
         * 파라미터와 동일한지 여부를 제공한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         * 
         * @param eventTypeKey
         *            이벤트 타입 식별정보
         * @param parameter
         *            이벤트 생성을 위한 비동기 작업 파라미터.
         *
         * @return
         *
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         */
        public boolean equals(String eventTypeKey, Object parameter) {
            return this.eventTypeKey.equals(eventTypeKey) && this.parameter.equals(parameter);
        }

        private AbstractEventDrivenMonitor getEnclosingInstance() {
            return AbstractEventDrivenMonitor.this;
        }

        /**
         *
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         * 
         * @return the eventTypeKey
         *
         * @since 2021. 9. 14.
         * 
         * @see #eventTypeKey
         */
        public String getEventTypeKey() {
            return eventTypeKey;
        }

        /**
         *
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         * 
         * @return the parameter
         *
         * @since 2021. 9. 14.
         * 
         * @see #parameter
         */
        public Object getParameter() {
            return parameter;
        }

        /**
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + ((eventTypeKey == null) ? 0 : eventTypeKey.hashCode());
            result = prime * result + ((parameter == null) ? 0 : parameter.hashCode());
            return result;
        }

        /**
         * 
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param ms
         * @return
         *
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         */
        public boolean isTimeout(long ms) {
            return System.currentTimeMillis() - this.timestamp > ms;
        }

        /**
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("UnsubscribedParameter [eventTypeKey=");
            builder.append(eventTypeKey);
            builder.append(", parameter=");
            builder.append(parameter);
            builder.append(", timestamp=");
            builder.append(timestamp);
            builder.append("]");
            return builder.toString();
        }
    }

    /**
     * 구독해제된 파라미터 해제 클래스.
     * 
     * @since 2021. 9. 14.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected class UnsubscriedParametersClosure extends ConcurrentWorker<UnsubscribedParameter> {

        protected final Logger logger = LoggerFactory.getLogger(getClass());

        /** 데이터 유효시간, 단위: millisecond */
        private final long ttl;
        private final long interval;

        /** 현재 TTL을 조사 중인 목록. */
        private Collection<UnsubscribedParameter> investigated = null;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param ttl
         *            파라미터 유효 시간. 단위: millisecond
         * @param interval
         *            파라미터 재검사 수행 간격. 단위: millisecond
         * @since 2021. 9. 14.
         */
        public UnsubscriedParametersClosure(long ttl, long interval) {
            this.ttl = ttl;
            this.interval = interval;
        }

        /**
         * 구독해제 여부를 제공한다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.     parkjunhong77@gmail.com         최초 작성
         * 2021. 9. 16.    parkjunhong77@gmail.com         포함 여부 변수의 혼용사용에 따른 버그 수정
         * </pre>
         *
         * @param eventTypeKey
         *            이벤트 타입 식별정보
         * @param parameter
         *            이벤트 생성을 위한 비동기 작업 파라미터.
         * @return
         *
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         */
        public boolean contains(String eventTypeKey, Object parameter) {
            UnsubscribedParameter up = new UnsubscribedParameter(eventTypeKey, parameter);

            boolean inInvestigating = this.investigated.contains(up);
            if (inInvestigating) {
                logger.trace("[unsubscribed] contains={} in 'investigated'. event={}, parameter={}", inInvestigating, eventTypeKey, parameter);
            }

            boolean inQueue = false;
            if (inQueue = contains(up)) {
                logger.trace("[unsubscribed] contains={} in 'queue'. event={}, parameter={}", inQueue, eventTypeKey, parameter);
            }

            boolean contains = inInvestigating || inQueue;

            logger.trace("[unsubscribed] contains={}. event={}, parameter={}", contains, eventTypeKey, parameter);

            return contains;
        }

        /**
         * 관리 중이 파라미터를 강제로 삭제한다.<br>
         * 동일한 이벤트 타입에 대해서 동일한 파라미터로 구독요청이 왔을 경우에 호출.
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2021. 9. 14.		parkjunhong77@gmail.com			최초 작성
         * </pre>
         *
         * @param eventTypeKey
         * @param parameter
         *
         * @since 2021. 9. 14.
         * @version 0.4.0
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         */
        public void removeForcely(String eventTypeKey, Object parameter) {
            UnsubscribedParameter up = new UnsubscribedParameter(eventTypeKey, parameter);
            // #1. 현재 조사 대기목록에서 삭제
            remove(up);
            // #2. 현재 조사 중인 목록에서 삭제
            this.investigated.remove(up);
        }

        /**
         * @since 2021. 9. 14.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see open.commons.concurrent.ConcurrentWorker#runInternal()
         */
        @Override
        protected void runInternal() {

            while (isRunning()) {
                this.investigated = flush();
                if (this.investigated != null) {

                    for (UnsubscribedParameter param : this.investigated) {
                        if (param.isTimeout(this.ttl)) {
                            logger.info("[unsubscribed] removed. event={}, parameter={}", param.getEventTypeKey(), param.getParameter());
                        } else {
                            push(param);
                        }
                    }
                    // 데이터 해제
                    this.investigated.clear();
                }

                // 재실행.
                ThreadUtils.sleep(this.interval);
            }
        }
    }

    /**
     * 비동기 작업을 실행하고 그 결과를 이벤트로 전송하는 작업.
     * 
     * @param <T>
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    protected class Worker<T extends IEventObject<?, ? extends IEventStatus>> extends DefaultRunnable {

        protected final Logger logger = LoggerFactory.getLogger(getClass());

        private final Function<Object, T> provider;
        private final Object param;
        private final ApplicationEventPublisher eventPub;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2021. 9. 9.      parkjunhong77@gmail.com         최초 작성
         * </pre>
         *
         * @param provider
         * @param param
         * @since 2021. 9. 9.
         */
        public Worker(@NotNull Function<Object, T> provider, @NotNull Object param, @NotNull ApplicationEventPublisher eventPub) {
            this.provider = provider;
            this.param = param;
            this.eventPub = eventPub;

        }

        /**
         * @since 2021. 9. 9.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see open.commons.lang.DefaultRunnable#runInternal()
         */
        @Override
        protected void runInternal() {
            T events = this.provider.apply(this.param);
            this.eventPub.publishEvent(events);
        }
    }

}
