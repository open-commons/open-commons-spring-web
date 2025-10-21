/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2020. 11. 26. 오후 5:41:31
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.mvc.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.AsyncResult;

import open.commons.core.Result;
import open.commons.core.concurrent.AsyncJobManager;
import open.commons.spring.web.mvc.IAsyncJobHandler;

/**
 * 
 * @since 2020. 11. 26.
 * @version 0.3.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @deprecated Use {@link IAsyncJobHandler}.
 */
public interface IAsyncHandlerService {

    /**
     * 주어진 데이터를 가지고 {@link Future} 객체를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <T>
     * @param value
     * @return
     *
     * @since 2020. 11. 10.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    default <T> Future<T> future(T value) {
        return new AsyncResult<>(value);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param <T>
     * @param value
     *            데이터
     * @param result
     *            결과
     * @param msg
     *            메시지
     * @return
     *
     * @since 2020. 11. 26.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    default <T> Future<Result<T>> futureAsResult(T value, boolean result, String msg) {
        return new AsyncResult<>(new Result<T>(value, result).setMessage(msg));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 10.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param <H>
     * @param holder
     *            비동기 작업을 요청한 대상
     * @param key
     *            비동기 작업 식별정보
     *
     * @since 2020. 11. 10.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @deprecated {@link IAsyncJobHandler#unregister(Object, Object)}
     */
    default <H, K> void unregisterAsyncJob(H holder, K key) {
        // 비동기 작업 제거
        AsyncJobManager<K, ?> manager = AsyncJobManager.Builder.getManager(holder);
        if (manager != null) {
            manager.unregister(key);
        }
    }

}
