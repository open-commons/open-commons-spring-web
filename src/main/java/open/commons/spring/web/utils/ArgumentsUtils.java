/*
 * Copyright 2022 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2022. 8. 10. 오전 11:43:49
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.utils;

import java.util.List;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;

import open.commons.core.log4j.appender.ProcessRollingFileAppender;

/**
 * Application Argument에 대한 지원 기능을 제공.
 * 
 * @since 2022. 8. 10.
 * @version 0.5.0
 * @author parkjunhong77@gmail.com
 */
public class ArgumentsUtils {

    public static final ArgumentsUtils INSTANCE = new ArgumentsUtils();

    private ArgumentsUtils() {
    }

    /**
     * 이름에 해당하는 옵션 값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 8. 10.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param args
     * @param name
     * @return
     *
     * @since 2022. 8. 10.
     * @author Park_Jun_Hong (jhpark@ymtech.co.kr)
     */
    public String getOptionValue(DefaultApplicationArguments args, String name) {
        List<String> values = args.getOptionValues(name);
        return values != null && values.size() > 0 //
                ? values.get(0) //
                : null;
    }

    /**
     * {@link Logger} 생성시 외부 설정정보를 적용합니다.<br>
     * 예를 들어, 로그 파일명에 프로그램 실행시 전달받는 파라미터를 적용하고자 할 때. <br>
     * 
     * 사용 예시는 {@link ProcessRollingFileAppender} 를 참조하기 바랍니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 8. 10.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param loggerName
     *            {@link Logger} 이름.
     * @param argName
     *            파라미터 이름
     * @param args
     *            외부 파라미터
     *
     * @return
     *
     * @since 2022. 8. 10.
     * @version 0.5.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public Logger setLoggerContextUsingApplicationExternalConfiguration(Class<?> loggerName, String argName, String[] args) {

        DefaultApplicationArguments argObj = new DefaultApplicationArguments(args);
        String context = getOptionValue(argObj, argName);
        ThreadContext.put(ProcessRollingFileAppender.PROCESS_CONTEXT, context);

        Logger logger = LoggerFactory.getLogger(loggerName);

        logger.info("log4j2.{}={}", ProcessRollingFileAppender.PROCESS_CONTEXT, context);

        return logger;
    }

}
