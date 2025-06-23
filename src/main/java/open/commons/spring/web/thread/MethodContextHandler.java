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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

/**
 * 
 * @since 2025. 6. 23.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class MethodContextHandler {

    private static final String HOLDER = "holder";
    private static final String INDENTATION = "indentation";
    private static final String ORIGIN = "origin";

    /**
     * {@link Thread} 별 데이터 목록
     * 
     * <li>holder: {@link String}
     * <li>indentation: {@link AtomicInteger}
     * <li>root: Class<?>
     */
    private static final ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<>();

    public static void clear(@NotEmpty String holder) {
        if (CONTEXT.get() == null) {
            return;
        }

        if (holder.equals(CONTEXT.get().get(HOLDER))) {
            CONTEXT.remove();
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
        if (CONTEXT.get() == null) {
            Map<String, Object> props = new HashMap<>();
            props.put(HOLDER, holder);
            props.put(INDENTATION, new AtomicInteger());
            if (originClass != null) {
                props.put(ORIGIN, originClass);
            }

            CONTEXT.set(props);
        }
    }

    private static int internalDecrementAndGet() {
        return ((AtomicInteger) CONTEXT.get().get(INDENTATION)).decrementAndGet();
    }

    private static int internalGetAndIncrement() {
        return ((AtomicInteger) CONTEXT.get().get(INDENTATION)).getAndIncrement();
    }

    public static boolean originatedFrom(@NotNull Class<?> clazz) {
        Assert.notNull(clazz, "The origin of method calling is MUST NOT be null.");
        if (CONTEXT.get() == null) {
            return false;
        }

        return clazz.equals(CONTEXT.get().get(ORIGIN));
    }
}
