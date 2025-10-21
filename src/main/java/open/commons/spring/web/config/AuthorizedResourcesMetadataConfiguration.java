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
 * Date  : 2025. 6. 12. 오후 6:04:48
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

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import open.commons.core.utils.MapUtils;
import open.commons.spring.web.authority.metadata.AuthorizedObjectMetadata;
import open.commons.spring.web.authority.metadata.AuthorizedRequestDataObjectMetadata;
import open.commons.spring.web.beans.authority.AuthorizedRequestDataMetadata;
import open.commons.spring.web.beans.authority.AuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.utils.BeanUtils;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
public class AuthorizedResourcesMetadataConfiguration {

    private static final String PREFIX_OPEN_COMMONS_APPLICATION = "open-commons.application";
    private static final String NAME_AUTHORIZED_OBJECT_METADATA = "authorized-object-metadata[0].type";

    private final Logger logger = LoggerFactory.getLogger(AuthorizedResourcesMetadataConfiguration.class);

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedResourcesMetadataConfiguration() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 22.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param singleAuthorizedObjectMetadata
     *            단일 데이터 유형에 대한 메타데이터 정보
     *            <li>key: Bean 이름
     *            <li>value: Bean 데이터
     * @param multiAuthorizedObjectMetadata
     *            여러 데이터 유형에 대한 메타데이터 정보
     *            <li>key: Bean 이름
     *            <li>value: Bean 데이터
     * @return
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = AuthorizedRequestDataMetadata.BEAN_QUALIFIER)
    @Primary
    IAuthorizedRequestDataMetadata authorizedRequestDataMetadataProvider(//
            @NotNull Map<String, AuthorizedRequestDataObjectMetadata> single //
            , @NotNull Map<String, List<AuthorizedRequestDataObjectMetadata>> multi//
    ) {
        AuthorizedRequestDataMetadata arm = new AuthorizedRequestDataMetadata();
        Collection<AuthorizedRequestDataObjectMetadata> merged = MapUtils.toList(single, multi);
        arm.setAuthorizedRequestObjectMetadata(merged);

        logger.info("[authorized-resources] authorized-request-data-metadata={}", arm);

        return arm;

    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param single
     *            단일 데이터 유형에 대한 메타데이터 정보
     *            <li>key: Bean 이름
     *            <li>value: Bean 데이터
     * @param multi
     *            여러 데이터 유형에 대한 메타데이터 정보
     *            <li>key: Bean 이름
     *            <li>value: Bean 데이터
     * @return
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = AuthorizedResourcesMetadata.BEAN_QUALIFIER)
    @Primary
    IAuthorizedResourcesMetadata authorizedResourcesMetadataProvider( //
            @NotNull Map<String, AuthorizedObjectMetadata> single //
            , @NotNull Map<String, List<AuthorizedObjectMetadata>> multi //
    ) {
        AuthorizedResourcesMetadata arm = new AuthorizedResourcesMetadata();

        Collection<AuthorizedObjectMetadata> aoms = Stream //
                .of(single.values().stream() //
                        , multi.values().stream() //
                                .flatMap(List::stream)) //
                .flatMap(s -> s) //
                .collect(Collectors.toList());

        arm.setAuthorizedObjectMetadata(aoms);

        logger.info("[authorized-resources] authorized-resources-metadata={}", arm);

        return arm;
    }

    /**
     * <code>open-commons.application.authorized-object-metadata</code> 항목으로 설정된 값을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param environment
     * @return
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    @ConditionalOnProperty(prefix = PREFIX_OPEN_COMMONS_APPLICATION, name = NAME_AUTHORIZED_OBJECT_METADATA)
    List<AuthorizedObjectMetadata> builtInAuthorizedObjectMetadata(Environment environment) {
        List<AuthorizedObjectMetadata> aoms = BeanUtils.listOf( //
                environment, //
                String.join(".", PREFIX_OPEN_COMMONS_APPLICATION, NAME_AUTHORIZED_OBJECT_METADATA.replace("[0].type", "")), //
                AuthorizedObjectMetadata.class //
        );

        logger.info("[authorized-resources] authorized-object-metadata-multiple={}", aoms);

        return aoms;
    }
}
