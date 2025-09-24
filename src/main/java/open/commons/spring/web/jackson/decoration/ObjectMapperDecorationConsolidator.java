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
 * Date  : 2025. 6. 17. 오후 2:37:58
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.decoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import open.commons.core.utils.CollectionUtils;
import open.commons.core.utils.MapUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;

/**
 * 
 * @since 2025. 6. 17.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @deprecated {@link Jackson2ObjectMapperBuilder}를 {@link Bean}으로 제공받아 {@link ObjectMapper}를 생성하는 방식으로 변경됨에 따라 필요성이
 *             없어짐.
 */
public class ObjectMapperDecorationConsolidator implements IObjectMapperDecorationConsolidator {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.jacksons.decoration.ObjectMapperDecorationConsolidator";

    private final Logger logger = LoggerFactory.getLogger(ObjectMapperDecorationConsolidator.class);

    private List<IObjectMapperDecorator> decorators = new Vector<>();
    private boolean resolved = false;

    private Set<Module> modules;
    private Set<NamedType> namedTypes;
    private Set<Object> enables;
    private Set<Object> disables;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 17.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public ObjectMapperDecorationConsolidator() {
    }

    /**
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.jackson. decoration.IObjectMapperDecorationConsolidator#addObjectMapperDecorator(java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addObjectMapperDecorator(@NotNull Collection<IObjectMapperDecorator> decorators) {
        CollectionUtils.addAllIfNotNull(this.decorators, Vector.class, decorators);
        this.resolved = false;
    }

    /**
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.jackson. decoration.IObjectMapperDecorationConsolidator#addObjectMapperDecorator(open.commons.spring.web.jackson. decoration.IObjectMapperDecorator[])
     */
    @Override
    public void addObjectMapperDecorator(@NotNull IObjectMapperDecorator... decorators) {
        addObjectMapperDecorator(Arrays.asList(decorators));
    }

    /**
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.jackson. decoration.IObjectMapperDecorationConsolidator#configureFeature(com.fasterxml.jackson.databind.ObjectMapper)
     */
    @Override
    public void configureFeature(@NotNull ObjectMapper objectMapper) {

        // #1. set module
        objectMapper.registerModules(this.modules);

        // #2. set NamedType
        objectMapper.registerSubtypes(this.namedTypes.toArray(new NamedType[0]));

        // #3. configure ConfigFeature
        for (Object feature : this.enables) {
            configureFeature(objectMapper, feature, true);
        }
        for (Object feature : this.disables) {
            configureFeature(objectMapper, feature, false);
        }
    }

    /**
     * <p>
     * COPY FROM "org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.configureFeature(ObjectMapper,
     * Object, boolean)" on spring-web-5.3.29
     * </p>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 17.     박준홍         최초 작성
     * </pre>
     *
     * @param objectMapper
     * @param feature
     * @param enabled
     *
     * @since 2025. 6. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @SuppressWarnings("deprecation") // on Jackson 2.13: configure(MapperFeature, boolean)
    private void configureFeature(ObjectMapper objectMapper, Object feature, boolean enabled) {
        if (feature instanceof JsonParser.Feature) {
            objectMapper.configure((JsonParser.Feature) feature, enabled);
        } else if (feature instanceof JsonGenerator.Feature) {
            objectMapper.configure((JsonGenerator.Feature) feature, enabled);
        } else if (feature instanceof SerializationFeature) {
            objectMapper.configure((SerializationFeature) feature, enabled);
        } else if (feature instanceof DeserializationFeature) {
            objectMapper.configure((DeserializationFeature) feature, enabled);
        } else if (feature instanceof MapperFeature) {
            objectMapper.configure((MapperFeature) feature, enabled);
        } else {
            throw new IllegalArgumentException("Unknown feature class: " + feature.getClass().getName());
        }
    }

    @PostConstruct
    public void resolveConflicts() {
        if (this.resolved) {
            return;
        }

        // #1. Module 검증
        final Set<Module> modules = new HashSet<>();
        final Map<Object, Class<?>> moduleTypes = new HashMap<>();
        final Map<Object, Object> moduleMap = new HashMap<>();
        final Map<Object, List<Object>> moduleDup = new HashMap<>();
        this.decorators.stream().flatMap(d -> d.modules().stream()).forEach(m -> {
            modules.add(m);
            if (moduleTypes.containsKey(m.getTypeId())) {
                Supplier<List<Object>> sup = () -> new ArrayList<Object>();
                List<Object> dup = MapUtils.getOrDefault(moduleDup, m.getTypeId(), sup, true);
                dup.add(m);
                CollectionUtils.addAllIfNotNull(dup, null, moduleMap.remove(m.getTypeId()));
            } else {
                moduleTypes.put(m.getTypeId(), m.getClass());
                moduleMap.put(m.getTypeId(), m);
            }
        });

        // #2. Feature 검증
        Set<Object> featuresEnables = this.decorators.stream().flatMap(d -> d.enables().stream()).collect(Collectors.toSet());
        Set<Object> featuresDisables = this.decorators.stream().flatMap(d -> d.disables().stream()).collect(Collectors.toSet());
        Set<Object> featureConflicts = new HashSet<>();
        for (Object e : featuresEnables) {
            for (Object d : featuresDisables) {
                if (e.equals(d)) {
                    featureConflicts.add(e);
                }
            }
        }

        // #3. 검증 결과에 따라 처리.
        if (moduleDup.size() < 1 && featureConflicts.size() < 1) {
            // #1. set module
            this.modules = modules;
            // #2. set NamedType
            this.namedTypes = Stream.of(
                    // from 'namedTypes'
                    this.decorators.stream().flatMap(d -> d.namedTypes().stream()),
                    // from 'subtypes'
                    this.decorators.stream().flatMap(d -> d.subtypes().stream().map(NamedType::new))) //
                    .flatMap(s -> s)//
                    .collect(Collectors.toSet());
            // #3. configure ConfigFeature
            this.enables = featuresEnables;
            this.disables = featuresDisables;

            this.resolved = true;
            this.decorators.clear();
        } else {
            if (moduleDup.size() > 0) {
                moduleDup.forEach((id, m) -> {
                    this.logger.error("[Module 중복] {}({}) = {}", moduleTypes.get(id), id, m);
                });

            }
            if (featureConflicts.size() > 0) {
                featureConflicts.forEach(f -> {
                    this.logger.error("[Feature 충돌] 활성화/비활성화에 등록 => {}", f);
                });
            }

            throw new BeanCreationException("", String.format("ObjectMapper에 적용되는 Module, Feature에 오류가 존재합니다. 중복=%,d, 충돌=%,d", moduleDup.size(), featureConflicts.size()));
        }
    }
}
