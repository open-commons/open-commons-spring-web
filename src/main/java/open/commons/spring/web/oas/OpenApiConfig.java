/*
 * Copyright 2023 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2023. 7. 19. 오전 11:09:41
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.oas;

import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import open.commons.core.collection.FIFOMap;
import open.commons.core.utils.StringUtils;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * <a href="https://springdoc.org/">Spring Docs</a> {@link OpenAPI}를 사용하기 위한 설정.<br>
 * 
 * <p>
 * application.yml 내 속성정의는 다음과 같습니다.<br>
 * 
 * 설정 내용 중에 "open-commons.springdoc.open-api.info.contact.[email, name]"은 필수항목입니다.<br>
 * 
 * <pre>
 * open-commons:
 *   springdoc:
 *    open-api:
 *      info:
 *        contact:
 *          email: "user@a.b.c"
 *          name: "user"
 *          url: "..."
 *          extensions:
 *            key: "contact's extensions"
 *        description: "..."
 *        extensions:
 *          key: "value"
 *        license:
 *          identifier: "..."
 *          name: "..."
 *          url: "..."
 *          extensions:
 *            key: "value"
 *        summary: "..."
 *        terms-of-service: "..."
 *        title: "..."
 *      external-docs:
 *        description: "SpringDocs Open API Documentation..."
 *        url: "https://springdoc.org/"
 * </pre>
 * </p>
 * 
 * @since 2023. 7. 19.
 * @version 0.6.0
 * @author parkjunhong77@gmail.com
 * @see <a href="https://springdoc.org/">springdoc.org</a>
 */
@Configuration(OpenApiConfig.BEAN_QUALIFIER)
public class OpenApiConfig {
    private static Logger logger = LoggerFactory.getLogger(OpenApiConfig.class);

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.oas.OpenApiConfig";
    public static final String BEAN_QUALIFIER_OPEN_API_INFO = "open.commons.spring.web.oas.OpenApiConfig#OPEN_API_INFO";
    public static final String BEAN_QUALIFIER_OPEN_API_EXT_DOCS = "open.commons.spring.web.oas.OpenApiConfig#OPEN_API_EXT_DOCS";
    /**
     * {@link GroupedOpenApi}를 지원하기 위한 {@link Bean}<br>
     * <code>
     * configuration properties path={@value #PROPERTIES_GROUPED_OPEN_API}
     * </code>
     * 
     * @since 0.8.0
     */
    public static final String BEAN_QUALIFIER_GROUPED_OPEN_API_PROPERTIES = "open.commons.spring.web.oas.OpenApiConfig#GROUPED_OPEN_API_PROPERTIES";
    /** configuration properties path for {@link Info}. */
    private static final String PROPERTIES_OPEN_API_INFO = "open-commons.springdoc.open-api.info";
    /** configuration properties path for {@link ExternalDocumentation}. */
    private static final String PROPERTIES_OPEN_API_EXT_DOCS = "open-commons.springdoc.open-api.external-docs";

    /**
     * configuration properties path for {@link GroupedOpenApiProperties}.
     * 
     * @since 0.8.0
     */
    private static final String PROPERTIES_GROUPED_OPEN_API = "open-commons.springdoc.grouped-open-api";

    private ApplicationContext context;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 19.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     *
     * @since 2023. 7. 19.
     * @version 0.6.0
     * @author parkjunhong77@gmail.com
     */
    public OpenApiConfig(@Autowired ApplicationContext context) {
        this.context = context;
    }

    /**
     * Open API 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return Open API 정보. 단 필수정보(email, name)가 없는 경우 생성되지 않습니다.
     *
     * @since 2023. 7. 19.
     * @version 0.6.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean
    @ConditionalOnProperty(prefix = PROPERTIES_OPEN_API_INFO, name = { "contact.email", "contact.name" })
    public OpenAPI createOpenAPIInfo() {

        OpenAPI api = new OpenAPI();

        if (this.context.containsBeanDefinition(BEAN_QUALIFIER_OPEN_API_INFO)) {
            Info info = this.context.getBean(BEAN_QUALIFIER_OPEN_API_INFO, Info.class);
            api.setInfo(info);
        }

        if (this.context.containsBeanDefinition(BEAN_QUALIFIER_OPEN_API_EXT_DOCS)) {
            ExternalDocumentation extDoc = this.context.getBean(BEAN_QUALIFIER_OPEN_API_EXT_DOCS, ExternalDocumentation.class);
            api.setExternalDocs(extDoc);
        }

        logger.trace("[Registered] Open API: {}", api);

        return api;
    }

    /**
     * Open API 외부 문서 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return Open API 외부 문서 정보. 단, 필수정보(url)가 없는 경우 생성되지 않습니다.
     *
     * @since 2023. 7. 19.
     * @version 0.6.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(name = BEAN_QUALIFIER_OPEN_API_EXT_DOCS)
    @ConditionalOnProperty(prefix = PROPERTIES_OPEN_API_EXT_DOCS, name = "url")
    @ConfigurationProperties(PROPERTIES_OPEN_API_EXT_DOCS)
    public ExternalDocumentation getOpenAPIExternalDocumentation() {
        return new ExternalDocumentation();
    }

    /**
     * Open API 정보를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 7. 19.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2023. 7. 19.
     * @version 0.6.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #getOpenAPIInfo()
     * @see #getOpenAPIExternalDocumentation()
     */
    @Bean(name = BEAN_QUALIFIER_OPEN_API_INFO)
    @ConditionalOnProperty(prefix = PROPERTIES_OPEN_API_INFO, name = { "contact.email", "contact.name" })
    @ConfigurationProperties(PROPERTIES_OPEN_API_INFO)
    public Info getOpenAPIInfo() {
        return new Info();
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Bean(BEAN_QUALIFIER_GROUPED_OPEN_API_PROPERTIES)
    @ConfigurationProperties(PROPERTIES_GROUPED_OPEN_API)
    public Map<String, GroupedOpenApiProperties> loadGroupedOpenApiProperties() {
        return new FIFOMap<String, GroupedOpenApiProperties>();
    }

    /**
     * 주어진 API 이름에 해당하는 Open API 정보를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param props
     *            여러 개의 API 정보
     * @param apiName
     *            API 이름
     * 
     *            <pre>
     * open-commons:
     *   grouped-open-api:
     *     # 'API' 이름
     *     {API name}:
     *       # {string}: API Group Name. Swagger UI에서 API 선택창에 보여지는 정보.
     *       group: Admin API
     *       # {string}: Swagger UI를 통해서 보여지는 정보
     *       displayName: admin
     *       # {array of string}: package 경로 기준으로 등록하는 API 정보
     *       packagesToScan:
     *       # {array of string}: package 경로 기준으로 제외하는 API 정보
     *       packagesToExclude:
     *       # {array of string}: REST API Path 기준으로 등록하는 API 정보
     *       pathsToMatch:
     *         - /.../**
     *       ...
     *            </pre>
     * 
     * @return
     *
     * @since 2025. 4. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static GroupedOpenApi loadGroupedOpenApi(@NotNull Map<String, GroupedOpenApiProperties> props, @NotEmpty String apiName) {
        GroupedOpenApiProperties prop = props.get(apiName);
        if (prop == null) {
            logger.warn("'{}'를 위한 설정이 존재하지 않습니다.", apiName);
            return null;
        }

        return transform(prop, apiName);
    }

    /**
     * {@link GroupedOpenApiProperties} 설정을 {@link GroupedOpenApi} 객체로 변환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param prop
     *            {@link GroupedOpenApi} 생성을 위한 설정.
     * @param name
     * 
     * @return
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static GroupedOpenApi transform(@NotNull GroupedOpenApiProperties prop, String name) {
        GroupedOpenApi api = GroupedOpenApi.builder()//
                .group(prop.getGroup())//
                .displayName(prop.getDisplayName())//
                .consumesToMatch(prop.getConsumesToMatch())//
                .headersToMatch(prop.getHeadersToMatch())//
                .packagesToExclude(prop.getPackagesToExclude())//
                .packagesToScan(prop.getPackagesToScan())//
                .pathsToExclude(prop.getPathsToExclude())//
                .pathsToMatch(prop.getPathsToMatch())//
                .producesToMatch(prop.getProducesToMatch()) //
                .addOpenApiCustomiser(openapi -> {
                    openapi.setInfo(prop.getInfo());
                    openapi.setExternalDocs(prop.getExternalDocs());
                }) //
                .build();

        if (!StringUtils.isNullOrEmptyString(name)) {
            logger.info("'{}' 를 위한 Grouped Open API가 생성되었습니다.", name);
        }

        return api;

    }

}
