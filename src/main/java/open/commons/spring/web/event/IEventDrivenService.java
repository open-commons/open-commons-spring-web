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
 * Date  : 2021. 9. 14. 오후 5:31:49
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.event;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import open.commons.core.Result;

/**
 * 모니터링에 필요한 기능을 정의.
 * 
 * @since 2021. 9. 9.
 * @version 0.4.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public interface IEventDrivenService {

    /**
     * 현재 이벤트별 파라미터 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 9. 9.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public Map<String, Set<Object>> getParameters();

    /**
     * 이벤트 생성자를 등록한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 9. 9.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            이벤트 정보 객체
     * @param <E>
     *            이벤트 상태.
     * @param <C>
     *            이벤트 타입.
     * @param <P>
     *            이벤트 생성을 위해서 사용되는 데이터 타입.
     * 
     * @param eventType
     *            이벤트 타입.
     * @param provider
     *            이벤트 제공 함수.
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public <T, E extends IEventStatus, C extends IEventObject<T, E>, P> void registerEventProvider(@NotNull Class<C> eventType, @NotNull Function<P, C> provider);

    /**
     * 이벤트 구독 신청을 한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 9. 9.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            이벤트 정보 객체
     * @param <E>
     *            이벤트 상태.
     * @param <C>
     *            이벤트 타입.
     * @param <P>
     *            이벤트 생성을 위해서 사용되는 데이터 타입.
     * @param eventType
     *            이벤트 타입.
     * @param parameter
     *            이벤트 생성을 위해서 사용되는 데이터 타입.
     * @return TODO
     *
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public <T, E extends IEventStatus, C extends IEventObject<T, E>, P> Result<Boolean> subscribe(@NotNull Class<C> eventType, @NotNull P parameter);

    /**
     * 이벤트 구독을 취소한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 9. 9.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     *            이벤트 정보 객체
     * @param <E>
     *            이벤트 상태.
     * @param <C>
     *            이벤트 타입.
     * @param <P>
     *            이벤트 생성을 위해서 사용되는 데이터 타입.
     * @param eventType
     *            이벤트 타입.
     * @param parameter
     *            이벤트 생성을 위해서 사용되는 데이터 타입.
     * @return TODO
     *
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public <T, E extends IEventStatus, C extends IEventObject<T, E>, P> Result<Boolean> unsubscribe(@NotNull Class<C> eventType, @NotNull P parameter);
}
