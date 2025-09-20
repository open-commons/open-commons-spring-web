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
 * Date  : 2025. 6. 12. 오전 10:46:41
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.beans.authority.builtin.ForcedUnintelligibleHandler;
import open.commons.spring.web.beans.authority.builtin.ForcedUnintelligibleJudge;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedObjectForcedUnintelligibleConfiguration {

    private Logger logger = LoggerFactory.getLogger(AuthorizedObjectForcedUnintelligibleConfiguration.class);

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedObjectForcedUnintelligibleConfiguration() {
    }

    @Bean(name = ForcedUnintelligibleHandler.BEAN_QUALIFIER)
    IUnauthorizedFieldHandler forcedUnintelligibleHandler() {
        IUnauthorizedFieldHandler h = new ForcedUnintelligibleHandler();
        logger.info("[authorized-resources] authorized-object-forced-unintelligible-field-handler={}", h);
        return h;
    }

    @Bean(name = ForcedUnintelligibleJudge.BEAN_QUALIFIER)
    IFieldAccessAuthorityProvider forcedUnintelligibleJude() {
        IFieldAccessAuthorityProvider p = new ForcedUnintelligibleJudge();
        logger.info("[authorized-resources] authorized-object-forced-unintelligible-field-provider={}", p);
        return p;
    }

}
