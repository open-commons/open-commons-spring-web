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
 * Date  : 2025. 7. 29. 오전 11:26:41
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import open.commons.spring.web.log.IMdcLogFeatureDecorationConsolidator;
import open.commons.spring.web.log.IMdcLogFeatureDecorator;
import open.commons.spring.web.log.MdcLogFeatureDecorationConsolidator;
import open.commons.spring.web.log.LogFeature;

/**
 * 
 * @since 2025. 7. 29.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
public class MdcPropertyLogDecorationConfiguration {

    private final Logger logger = LoggerFactory.getLogger(MdcPropertyLogDecorationConfiguration.class);

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 29.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 7. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public MdcPropertyLogDecorationConfiguration() {
    }

    /**
     * 'marker'({@link LogFeature#marker()}) 항목에 대한 데이터를 처리하는 함수들을 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param singleMdcPropertyLogDecorator
     *            단일 항목에 대한 설정
     *            <li>key: Bean 이름
     *            <li>value: Bean 데이터
     * @param multiMdcPropertyLogDecorator
     *            여러 항목에 대한 설정
     *            <li>key: Bean 이름
     *            <li>value: Bean 데이터
     * @return
     *
     * @since 2025. 7. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    @Primary
    IMdcLogFeatureDecorationConsolidator mdcPropertyLogDecorationConsolidator( //
            Map<String, IMdcLogFeatureDecorator> singleMdcPropertyLogDecorator //
            , Map<String, List<IMdcLogFeatureDecorator>> multiMdcPropertyLogDecorator //
    ) {
        MdcLogFeatureDecorationConsolidator consolidator = new MdcLogFeatureDecorationConsolidator();

        Collection<IMdcLogFeatureDecorator> mplds = Stream //
                .of(singleMdcPropertyLogDecorator.values().stream() //
                        , multiMdcPropertyLogDecorator.values().stream() //
                                .flatMap(List::stream)) //
                .flatMap(s -> s) //
                .collect(Collectors.toList());
        consolidator.setMdcPropertyLogDecoratorConfigurations(mplds);

        logger.info("[feature-based-logging] consolidator={}", consolidator);
        return consolidator;
    }

}
