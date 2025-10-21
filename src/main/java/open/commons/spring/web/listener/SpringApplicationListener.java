/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2019. 6. 11. 오후 4:23:03
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * {@link ApplicationEvent}를 구현한 이벤트 타입을 처리하는 클래스. <br>
 * 
 * 추가적으로 지원할 이벤트 타입은 {@link #onApplicationEvent(ApplicationEvent)} 메소드를 수정하여 구현한다.
 * 
 * @since 2019. 6. 11.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class SpringApplicationListener implements ApplicationListener<ApplicationEvent> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @since 2019. 6. 11.
     * @version
     */
    public SpringApplicationListener() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onApplicationContextInitializedEvent(ApplicationContextInitializedEvent event) {
        logger.trace("ApplicationContextInitialized - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
        logger.trace("ApplicationEnvironmentPrepared - {}", event);
    }

    /**
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationContextInitializedEvent) {
            onApplicationContextInitializedEvent((ApplicationContextInitializedEvent) event);
        } else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            onApplicationEnvironmentPreparedEvent((ApplicationEnvironmentPreparedEvent) event);
        } else if (event instanceof ApplicationFailedEvent) {
            onApplicationFailedEvent((ApplicationFailedEvent) event);
        } else if (event instanceof ApplicationPreparedEvent) {
            onApplicationPreparedEvent((ApplicationPreparedEvent) event);
        } else if (event instanceof ApplicationReadyEvent) {
            onApplicationReadyEvent((ApplicationReadyEvent) event);
        } else if (event instanceof ApplicationStartedEvent) {
            onApplicationStartedEvent((ApplicationStartedEvent) event);
        } else if (event instanceof ApplicationStartingEvent) {
            onApplicationStartingEvent((ApplicationStartingEvent) event);
        } else if (event instanceof ContextClosedEvent) {
            onContextClosedEvent((ContextClosedEvent) event);
        } else if (event instanceof ContextRefreshedEvent) {
            onContextRefreshedEvent((ContextRefreshedEvent) event);
        } else if (event instanceof ContextStartedEvent) {
            onContextStartedEvent((ContextStartedEvent) event);
        } else if (event instanceof ContextStoppedEvent) {
            onContextStoppedEvent((ContextStoppedEvent) event);
        } else if (event instanceof ServletRequestHandledEvent) {
            onServletRequestHandledEvent((ServletRequestHandledEvent) event);
        } else if (event instanceof ServletWebServerInitializedEvent) {
            onServletWebServerInitializedEvent((ServletWebServerInitializedEvent) event);
        } else {
            onOtherApplicationEvent(event);
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
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onApplicationFailedEvent(ApplicationFailedEvent event) {
        Throwable t = event.getException();
        logger.error("ApplicationFailed - cause={}", t.getMessage(), t);

    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onApplicationPreparedEvent(ApplicationPreparedEvent event) {
        logger.trace("ApplicationPrepared - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        logger.trace("ApplicationReady - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onApplicationStartedEvent(ApplicationStartedEvent event) {
        logger.trace("ApplicationStarted - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onApplicationStartingEvent(ApplicationStartingEvent event) {
        logger.trace("ApplicationStarting - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onContextClosedEvent(ContextClosedEvent event) {
        logger.trace("ContextClosed - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onContextRefreshedEvent(ContextRefreshedEvent event) {
        logger.trace("ContextRefreshed - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onContextStartedEvent(ContextStartedEvent event) {
        logger.trace("ContextStarted - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onContextStoppedEvent(ContextStoppedEvent event) {
        logger.trace("ContextStopped - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onOtherApplicationEvent(ApplicationEvent event) {
        logger.trace("ApplicationEvent - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onServletRequestHandledEvent(ServletRequestHandledEvent event) {
        logger.trace("ServletRequestHandled - {}", event);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 11.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param event
     *
     * @since 2019. 6. 11.
     * @version
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public void onServletWebServerInitializedEvent(ServletWebServerInitializedEvent event) {
        logger.trace("ServletWebServerInitialized - {}", event);
    }
}
