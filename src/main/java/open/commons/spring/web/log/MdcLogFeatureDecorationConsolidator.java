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
 * Date  : 2025. 7. 29. 오전 10:05:41
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.log;

import java.util.Collection;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.utils.StringUtils;

/**
 * 
 * @since 2025. 7. 29.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class MdcLogFeatureDecorationConsolidator implements IMdcLogFeatureDecorationConsolidator {

    private final Logger logger = LoggerFactory.getLogger(MdcLogFeatureDecorationConsolidator.class);

    /**
     * <li>key: 'feature'({@link LogFeature#feature()})와 'marker'({@link LogFeature#marker()})의 조합
     * <li>value: 'marker' 값을 처리하는 함수.
     */
    private final ConcurrentSkipListMap<String, Function<String, String>> decorators = new ConcurrentSkipListMap<>();
    /** 외부 설정 */
    private Collection<IMdcLogFeatureDecorator> decoratorConfigurations;
    private boolean resolved = false;
    private final BiFunction<String, String, String> DECORATOR_KEY = (feature, marker) -> {
        return String.join("#", feature, StringUtils.isNullOrEmptyString(marker) ? "" : marker.toString());
    };

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
    public MdcLogFeatureDecorationConsolidator() {
    }

    /**
     *
     * @since 2025. 7. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.log.IMdcLogFeatureDecorationConsolidator#decorator(java.lang.String,
     *      java.lang.String)
     */
    @Override
    @NotNull
    @Nonnull
    public Function<String, String> decorator(@NotBlank @Nonnull String feature, String marker) {
        Function<String, String> f = decorators.get(DECORATOR_KEY.apply(feature, marker));
        return f != null ? f : IMdcLogFeatureDecorationConsolidator::decorate;
    }

    @PostConstruct
    public void resolveMdcPropertyLogDecoratorConfigurations() {
        if (this.resolved) {
            return;
        }

        if (this.decoratorConfigurations != null) {
            this.decoratorConfigurations.stream().filter(d -> {
                boolean nulpty = StringUtils.isNullOrEmptyString(d.feature());
                if (nulpty) {
                    logger.warn("feature('{}') is null or emptry. marker={}, decorator={}", d.feature(), d.marker(), d.decorator());
                }
                return !nulpty;
            }).forEach(d -> {
                decorators.put(DECORATOR_KEY.apply(d.feature(), d.marker()), d.decorator());
            });
        }

        // 분석 완료
        this.resolved = true;
    }

    public void setMdcPropertyLogDecoratorConfigurations(Collection<IMdcLogFeatureDecorator> decoratorConfigurations) {
        this.decoratorConfigurations = decoratorConfigurations;
        this.resolved = false;
    }
}
