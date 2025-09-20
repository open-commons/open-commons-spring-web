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
 * Date  : 2025. 5. 19. 오후 4:33:07
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure.configuration;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.MapUtils;
import open.commons.core.utils.StreamUtils;
import open.commons.spring.web.aspect.AuthorizedMethodAspect;
import open.commons.spring.web.aspect.AuthorizedRequestAspect;
import open.commons.spring.web.beans.authority.IAuthorizedResourceAuthenticationPause;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IMethodAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IRequestAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.beans.authority.builtin.AuthorizedResourceHandler;
import open.commons.spring.web.beans.authority.builtin.ResourceHandle;
import open.commons.spring.web.exception.BeanMergeFailedException;
import open.commons.spring.web.jackson.AuthorizedFieldSerializerModifier;
import open.commons.spring.web.servlet.filter.AuthorizedResourceFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 
 * @since 2025. 5. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@AutoConfigureAfter({ AuthorizedObjectForcedUnintelligibleConfiguration.class })
public class AuthorizedResourcesConfiguration {

    public static final String BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER = "open.commons.spring.web.autoconfigure.AuthorizedResourcesConfiguration#AUTHORIZED_OBJECT_MAPPER";

    public static final String BEAN_QUALIFIER_AUTHORIZED_RESOURCE_HANDLERS = "open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesConfiguration#AUTHORIZED_RESOURCE_HANDLERS";

    private static final Logger logger = LoggerFactory.getLogger(AuthorizedResourcesConfiguration.class);

    public AuthorizedResourcesConfiguration() {
    }

    @Bean
    @ConditionalOnBean(IMethodAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedMethodAspect authorizedMethodAspect(ApplicationContext context) {
        AuthorizedMethodAspect aspect = new AuthorizedMethodAspect(context);
        logger.info("[Registered] authorized-method-aspect={}", aspect);
        return aspect;
    }

    @Bean(name = BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER)
    @ConditionalOnBean({ IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    ObjectMapper authorizedObjectMapper(ApplicationContext context //
            , @NotNull @Nonnull IAuthorizedResourcesMetadata authorizedResourcesMetadata //
            , @NotNull @Nonnull Jackson2ObjectMapperBuilder objectMapperBuilder) {
        // #1. ObjectMapper 생성
        ObjectMapper mapper = objectMapperBuilder.build();
        // #2. AuthorizedObject 처리 모듈 등록
        SimpleModule module = new SimpleModule();
        module.setSerializerModifier(new AuthorizedFieldSerializerModifier(context, authorizedResourcesMetadata));
        mapper.registerModule(module);

        logger.info("[authorized-resources] authorized-object-mapper={}", mapper);

        return mapper;
    }

    @Bean
    @ConditionalOnBean(IRequestAccessAuthorityProvider.class)
    @ConditionalOnMissingBean
    AuthorizedRequestAspect authorizedRequestAspect(ApplicationContext context) {
        AuthorizedRequestAspect aspect = new AuthorizedRequestAspect(context);
        logger.info("[authorized-resources] authorized-request-aspect={}", aspect);
        return aspect;
    }

    @Bean
    @ConditionalOnBean({ IFieldAccessAuthorityProvider.class, IUnauthorizedFieldHandler.class })
    @Order(Integer.MAX_VALUE)
    AuthorizedResourceFilter authorizedResourceFilter(ApplicationContext context) {
        IAuthorizedResourceAuthenticationPause auth = null;
        try {
            auth = context.getBean(IAuthorizedResourceAuthenticationPause.class);
        } catch (BeansException ignored) {
        }
        AuthorizedResourceFilter f = new AuthorizedResourceFilter(auth);
        logger.info("[authorized-resources] authorized-resources-filter={}", f);
        return f;
    }

    @Bean(BEAN_QUALIFIER_AUTHORIZED_RESOURCE_HANDLERS)
    @Primary
    List<ResourceHandle> authorizedResourceHandlerConfigurations(@NotNull @Nonnull Map<String, ResourceHandle> single //
            , @NotNull @Nonnull Map<String, List<ResourceHandle>> multi) {

        // #1. 데이터 병합
        List<ResourceHandle> merged = MapUtils.toList(single, multi, h -> String.format("%s#%s", h.target(), h.handleType()), (h1, h2) -> {
            if (h2.preemptive()) {
                return h2;
            } else {
                return h1;
            }
        });
        // #2. 중복 '데이터 처리 식별정보' 검증
        MultiValueMap<String, ResourceHandle> mayBeDuplicated = StreamUtils.toMap(merged.stream(),
                (Function<ResourceHandle, String>) h -> String.format("%s#%s", h.target(), h.handleType()), Function.identity(), LinkedMultiValueMap::new);

        boolean duplicated = false;
        for (Entry<String, List<ResourceHandle>> entry : mayBeDuplicated.entrySet()) {
            if (entry.getValue().size() > 1) {
                logger.debug("{}에 대한 설정이 {}개 존재합니다. 목록은 다음과 같습니다.\n\t{}\n" //
                        , entry.getKey() // FQCN 값
                        , entry.getValue().size() // 중복 데이터 개수
                        , String.join("\n\t", entry.getValue().stream().map(Object::toString).collect(Collectors.toList())) // 모든
                                                                                                                            // 설정
                );
                duplicated = true;
            }
        }

        if (duplicated) {
            ExceptionUtils.newException(BeanMergeFailedException.class, "", ResourceHandle.class);
            throw new BeanMergeFailedException("동일한 '데이터 처리 방식(%s)'에 2개 이상의 기능이 설정되었습니다. 자세한 내용은 로그를 확인하시기 바랍니다.", ResourceHandle.class);
        }

        return merged;
    }

    @Bean(AuthorizedResourceHandler.BEAN_QUALIFIER)
    @ConditionalOnBean(name = { BEAN_QUALIFIER_AUTHORIZED_RESOURCE_HANDLERS })
    AuthorizedResourceHandler authorizedResourceHandlers() {
        AuthorizedResourceHandler h = new AuthorizedResourceHandler();
        logger.info("[authorized-resource-handlers] authorized-resources-handlers={}", h);
        return h;
    }
}
