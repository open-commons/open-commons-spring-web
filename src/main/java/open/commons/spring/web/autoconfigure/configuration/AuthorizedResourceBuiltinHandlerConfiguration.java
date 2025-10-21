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

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.MapUtils;
import open.commons.core.utils.StreamUtils;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleHandler;
import open.commons.spring.web.beans.authority.internal.ForcedUnintelligibleJudge;
import open.commons.spring.web.beans.authority.internal.ResourceHandleImpl;
import open.commons.spring.web.config.ResourceHandle;
import open.commons.spring.web.exception.BeanMergeFailedException;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourceBuiltinHandlerConfiguration {

    public static final String BEAN_QUALIFIER_AUTHORIZED_RESOURCE_HANDLERS = "open.commons.spring.web.autoconfigure.configuration.AuthorizedResourceBuiltinHandlerConfiguration#AUTHORIZED_RESOURCE_HANDLERS";

    private Logger logger = LoggerFactory.getLogger(AuthorizedResourceBuiltinHandlerConfiguration.class);

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
    public AuthorizedResourceBuiltinHandlerConfiguration() {
    }

    @Bean(BEAN_QUALIFIER_AUTHORIZED_RESOURCE_HANDLERS)
    @Primary
    List<ResourceHandle> authorizedResourceHandlerConfigurations(@NotNull @Nonnull Map<String, ResourceHandle> single //
            , @NotNull @Nonnull Map<String, List<ResourceHandle>> multi) {

        // #1. 데이터 병합
        List<ResourceHandle> merged = MapUtils.toList(single, multi, h -> String.format("%s#%s", h.target(), h.handleType()), (h1, h2) -> {
            if (h2.preemptive()) {
                return h2;
            } else if (h1.preemptive()) {
                return h1;
            } else if (h1 instanceof ResourceHandleImpl && ((ResourceHandleImpl) h1).isBuiltin()) {
                return h1;
            } else if (h2 instanceof ResourceHandleImpl && ((ResourceHandleImpl) h2).isBuiltin()) {
                return h2;
            } else {
                return h2;
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
