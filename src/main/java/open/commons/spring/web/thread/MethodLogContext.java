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
 * Date  : 2025. 6. 23. 오후 1:48:04
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.thread;

import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import open.commons.core.lang.IThreadLocalContext;
import open.commons.core.lang.ThreadLocalContextService;

/**
 * 
 * @since 2025. 6. 23.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class MethodLogContext {

    private static final IThreadLocalContext CONTEXT = ThreadLocalContextService.context(MethodLogContext.class);

    /** 메소드 호출의 시작 위치 식별정보 */
    private static final String HOLDER = "holder";
    /** 들여쓰기 단계 */
    private static final String INDENTATION = "indentation";
    /** 메소드 호출의 시작 위치 유형 */
    private static final String ORIGIN = "origin";

    private MethodLogContext() {
    }

    public static void clear(@NotEmpty String holder) {
        if (holder.equals(CONTEXT.get(HOLDER))) {
            CONTEXT.clear();
        }
    }

    public static int getAfterDecrement(@NotEmpty String holder) {
        initialize(holder, null);
        return internalDecrementAndGet();
    }

    public static int getBeforeIncrement(@NotEmpty String holder, Class<?> originClass) {
        initialize(holder, originClass);
        return internalGetAndIncrement();
    }

    private static void initialize(@NotEmpty String holder, Class<?> originClass) {
        Assert.hasLength(holder, "Thread Context Holder MUST not be null and not the empty string.");
        if (CONTEXT.containsNot(HOLDER)) {
            CONTEXT.set(HOLDER, holder);
            CONTEXT.set(INDENTATION, new AtomicInteger());
            if (originClass != null) {
                CONTEXT.set(ORIGIN, originClass);
            }
        }
    }

    private static int internalDecrementAndGet() {
        return ((AtomicInteger) CONTEXT.get(INDENTATION)).decrementAndGet();
    }

    private static int internalGetAndIncrement() {
        return ((AtomicInteger) CONTEXT.get(INDENTATION)).getAndIncrement();
    }

    public static boolean originatedFrom(@NotNull Class<?> clazz) {
        Assert.notNull(clazz, "The origin of method calling is MUST NOT be null.");
        return clazz.equals(CONTEXT.get(ORIGIN));
    }
}
