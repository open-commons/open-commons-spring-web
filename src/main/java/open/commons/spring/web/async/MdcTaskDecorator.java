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
 * Date  : 2025. 7. 30. 오후 5:09:09
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.async;

import java.util.Map;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import open.commons.spring.web.log.LogFeature;
import open.commons.spring.web.mdc.MdcWrappedJob;

/**
 * {@link LogFeature}에 따라 로그파일을 분기되는 작업에 {@link MDC}를 전달하는 클래스.
 * 
 * @since 2025. 7. 30.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class MdcTaskDecorator implements TaskDecorator {

    private static final String DEFAULT_SYMBOL = "@async";

    private final String threadNameSymbol;

    private final Logger logger = LoggerFactory.getLogger(MdcTaskDecorator.class);

    public MdcTaskDecorator() {
        this(DEFAULT_SYMBOL);
    }

    public MdcTaskDecorator(String symbol) {
        this.threadNameSymbol = symbol;
    }

    /**
     *
     * @since 2025. 7. 30.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.core.task.TaskDecorator#decorate(java.lang.Runnable)
     */
    @Override
    public Runnable decorate(Runnable runnable) {
        if (runnable instanceof MdcWrappedJob //
                || runnable instanceof FutureTask //
        ) {
            logger.trace("[mdc-decorator] Skipping wrap for already-wrapped or FutureTask: {}", runnable.getClass());
            return runnable;
        }

        Map<String, String> context = MDC.getCopyOfContextMap();
        if (context != null) {
            context.put(MdcWrappedJob.MDC_PROPERTY_THREAD_SYMBOL, threadNameSymbol);
        }
        return MdcWrappedJob.wrap(context, runnable, false);
    }
}
