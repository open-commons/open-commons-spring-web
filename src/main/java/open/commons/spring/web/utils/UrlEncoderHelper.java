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
 * Date  : 2025. 8. 27. 오후 1:17:47
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.MapUtils;
import open.commons.spring.web.rest.service.ByPassUriTemplateVariables;
import open.commons.spring.web.rest.service.TemplateUriEncoder;
import open.commons.spring.web.rest.service.TemplateUriEncoder.Encoding;

/**
 * 
 * @since 2025. 8. 27.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@SuppressWarnings("unchecked")
public class UrlEncoderHelper {

    /**
     * <ul>
     * <li>key: 인코더 식별정보
     * <li>value: URI 인코더 함수
     * <ul>
     * <li>key: 템플릿 문자열
     * <li>value: 템플릿에 사용될 정보
     * </ul>
     * </ul>
     * 
     */
    private static final Map<Encoding, TemplateUriEncoder> ENCODERS = new HashMap<>();

    static {
        ENCODERS.put(Encoding.TEMPLATE_AND_VALUES, (template, variables) -> {
            if (variables == null || MapUtils.isNullOrEmpty(variables.getVariables())) {
                variables = ByPassUriTemplateVariables.emptyVariables();
            }
            // 'path? query?' 판단. MultiValueMap 이 Map 하위 타입이므로 MultiValueMap으로 판단
            boolean forQuery = variables.getVariables() instanceof MultiValueMap;
            Map<String, Object> encoded = encodedVariables(forQuery, Encoding.TEMPLATE_AND_VALUES, variables.getVariables());
            return UriComponentsBuilder.fromUriString(template) //
                    .encode() //
                    .build(encoded) //
                    .toString();
        });
        ENCODERS.put(Encoding.VALUES_ONLY_STRICT, (template, variables) -> {
            if (variables == null || MapUtils.isNullOrEmpty(variables.getVariables())) {
                variables = ByPassUriTemplateVariables.emptyVariables();
            }
            // 'path? query?' 판단. MultiValueMap 이 Map 하위 타입이므로 MultiValueMap으로 판단
            boolean forQuery = variables.getVariables() instanceof MultiValueMap;
            Map<String, Object> encoded = encodedVariables(forQuery, Encoding.VALUES_ONLY_STRICT, variables.getVariables());
            if (forQuery) {
                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(template);
                for (Entry<String, Object> entry : encoded.entrySet()) {
                    builder.queryParam(entry.getKey(), (List<String>) entry.getValue());
                }
                String query = builder.build().toString();
                if (query.startsWith("?")) {
                    return query.substring(1);
                } else {
                    return query;
                }
            } else {
                return UriComponentsBuilder.fromUriString(template) //
                        .build(encoded) //
                        .toString();
            }
        });
        ENCODERS.put(Encoding.VALUES_ONLY_RESERVED, (template, variables) -> {
            if (variables == null || MapUtils.isNullOrEmpty(variables.getVariables())) {
                variables = ByPassUriTemplateVariables.emptyVariables();
            }
            // 'path? query?' 판단. MultiValueMap 이 Map 하위 타입이므로 MultiValueMap으로 판단
            boolean forQuery = variables.getVariables() instanceof MultiValueMap;
            Map<String, Object> encoded = encodedVariables(forQuery, Encoding.VALUES_ONLY_RESERVED, variables.getVariables());
            return UriComponentsBuilder.fromUriString(template) //
                    .build() //
                    .expand(encoded) //
                    .toString();
        });
        ENCODERS.put(Encoding.NONE, (template, variables) -> {
            return UriComponentsBuilder.fromUriString(template) //
                    .build(true) //
                    .toString();
        });
    }

    private UrlEncoderHelper() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 27.		박준홍			최초 작성
     * </pre>
     * 
     * @param forQuery
     *            TODO
     * @param encoding
     *            '인코딩' 설정
     * @param variables
     *            변수값
     *
     * @return
     *
     * @since 2025. 8. 27.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private static Map<String, Object> encodedVariables(boolean forQuery, @Nonnull Encoding encoding, @Nonnull Map<String, ?> variables) {
        Map<String, Object> encoded = new LinkedHashMap<>();
        String varName = null;
        Object value = null;
        for (Entry<String, ?> entry : variables.entrySet()) {
            varName = entry.getKey();
            value = entry.getValue();
            if (forQuery) {
                List<String> encodeMultiValues = new ArrayList<>();
                for (String singleValue : (List<String>) value) {
                    switch (encoding) {
                        case TEMPLATE_AND_VALUES:
                        case VALUES_ONLY_STRICT:
                            encodeMultiValues.add(UriUtils.encodeQueryParam(singleValue, StandardCharsets.UTF_8));
                            break;
                        case VALUES_ONLY_RESERVED:
                            encodeMultiValues.add(UriUtils.encodeQueryParam(singleValue, StandardCharsets.UTF_8));
                            break;
                        case NONE:
                            break;
                    }
                }
                encoded.put(varName, encodeMultiValues);
            } else {
                switch (encoding) {
                    case TEMPLATE_AND_VALUES:
                    case VALUES_ONLY_STRICT:
                        encoded.put(varName, UriUtils.encodePathSegment((String) value, StandardCharsets.UTF_8));
                        break;
                    case VALUES_ONLY_RESERVED:
                        encoded.put(varName, UriUtils.encodePath((String) value, StandardCharsets.UTF_8));
                        break;
                    case NONE:
                        break;
                }
            }
        }

        return encoded;
    }

    /**
     * 사용자가 정의한 'URI 인코더'를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param encoding
     * @return
     *
     * @since 2025. 8. 27.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static TemplateUriEncoder encoder(@NotBlank @Nonnull Encoding encoding) {
        AssertUtils2.notNull(encoding);
        TemplateUriEncoder encoder = ENCODERS.get(encoding);
        return encoder != null ? encoder : ENCODERS.get(Encoding.NONE);
    }
}
