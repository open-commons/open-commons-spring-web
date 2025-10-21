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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;

import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import open.commons.core.text.NamedTemplate;
import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.MapUtils;
import open.commons.spring.web.rest.service.ByPassUriTemplateVariables;
import open.commons.spring.web.rest.service.TemplateUriEncoder;
import open.commons.spring.web.rest.service.TemplateUriEncoder.Encoding;
import open.commons.spring.web.rest.service.TemplateUriEncoder.UriComponent;

/**
 * 
 * @since 2025. 8. 27.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class UriEncodingHelper {

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
        ENCODERS.put(Encoding.TEMPLATE_AND_VALUES, (uriComponent, template, variables) -> {
            AssertUtils2.notNull(template);
            if (variables == null || MapUtils.isNullOrEmpty(variables.getVariables())) {
                variables = ByPassUriTemplateVariables.emptyVariables();
            }
            Map<String, Object> encoded = encodeVariables(uriComponent, Encoding.TEMPLATE_AND_VALUES, variables.getVariables());

            if (uriComponent == UriComponent.QUERY) {
                return encodeQuery(template, encoded);
            } else {
                return format(template, encoded);
            }
        });
        ENCODERS.put(Encoding.VALUES_ONLY_STRICT, (uriComponent, template, variables) -> {
            AssertUtils2.notNull(template);
            if (variables == null || MapUtils.isNullOrEmpty(variables.getVariables())) {
                variables = ByPassUriTemplateVariables.emptyVariables();
            }

            Map<String, Object> encoded = encodeVariables(uriComponent, Encoding.VALUES_ONLY_STRICT, variables.getVariables());

            if (uriComponent == UriComponent.QUERY) {
                return encodeQuery(template, encoded);
            } else {
                return format(template, encoded);
            }
        });
        ENCODERS.put(Encoding.VALUES_ONLY_RESERVED, (uriComponent, template, variables) -> {
            AssertUtils2.notNull(template);
            if (variables == null || MapUtils.isNullOrEmpty(variables.getVariables())) {
                variables = ByPassUriTemplateVariables.emptyVariables();
            }
            Map<String, Object> encoded = encodeVariables(uriComponent, Encoding.VALUES_ONLY_RESERVED, variables.getVariables());
            if (uriComponent == UriComponent.QUERY) {
                return encodeQuery(template, encoded);
            } else {
                return format(template, encoded);
            }
        });
        ENCODERS.put(Encoding.NONE, (uriComponent, template, variables) -> {
            AssertUtils2.notNull(template);
            return format(template, null);
        });
    }

    private UriEncodingHelper() {
    }

    private static String encodeQuery(String template, Map<String, Object> queryVariables) {
        String query = null;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(template);
        if (template.trim().isEmpty()) {
            Object value = null;
            for (Entry<String, Object> entry : queryVariables.entrySet()) {
                value = entry.getValue();
                if (value instanceof List<?>) {
                    List<?> values = (List<?>) value;
                    builder.queryParam(entry.getKey(), values);
                } else {
                    builder.queryParam(entry.getKey(), value);
                }
            }
            query = builder.build().toString();
        } else {
            query = builder.buildAndExpand(queryVariables).toString();
        }

        if (query.startsWith("?")) {
            return query.substring(1);
        } else {
            return query;
        }
    }

    /**
     * 사용자가 정의한 'URI 인코더'를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 27.		parkjunhong77@gmail.com			최초 작성
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

    /**
     * @since 2025. 8. 28.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private static String encodeVariable(UriComponent uriComponent, Encoding encoding, String value) {
        switch (encoding) {
            case TEMPLATE_AND_VALUES:
            case VALUES_ONLY_STRICT:
                return UriUtils.encode(value, StandardCharsets.UTF_8);
            case VALUES_ONLY_RESERVED:
                switch (uriComponent) {
                    case URI:
                        return UriUtils.encode(value, StandardCharsets.UTF_8);
                    case SCHEME:
                        return UriUtils.encodeScheme(value, StandardCharsets.UTF_8);
                    case USER_INFO:
                        return UriUtils.encodeAuthority(value, StandardCharsets.UTF_8);
                    case HOST:
                        return UriUtils.encodeHost(value, StandardCharsets.UTF_8);
                    case PORT:
                        return UriUtils.encodePort(value, StandardCharsets.UTF_8);
                    case PATH:
                        return UriUtils.encodePath(value, StandardCharsets.UTF_8);
                    case QUERY:
                        return UriUtils.encodeQuery(value, StandardCharsets.UTF_8);
                    case FRAGMENT:
                        return UriUtils.encodeFragment(value, StandardCharsets.UTF_8);
                    default:
                        return value;
                }
            case NONE:
                return value;
            default:
                return value;
        }
    }

    /**
     * 데이터를 인코딩하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 27.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param uriComponent
     *            'URI' 구성요소인코딩 대상
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
    private static Map<String, Object> encodeVariables(UriComponent uriComponent, @Nonnull Encoding encoding, @Nonnull Map<String, ?> variables) {
        Map<String, Object> encoded = new LinkedHashMap<>();
        String varName = null;
        Object value = null;
        for (Entry<String, ?> entry : variables.entrySet()) {
            varName = entry.getKey();
            value = entry.getValue();
            if (value == null) {
                encoded.put(varName, null);
            } else {
                // MultiValueMap<String, ?> 인 경우
                if (value instanceof List) {
                    encoded.put(varName //
                            , ((List<?>) value).stream() //
                                    .map(v -> encodeVariable(uriComponent, encoding, v.toString())) //
                                    .collect(Collectors.toList() //
                                    ) //
                    );
                } else {
                    encoded.put(varName, encodeVariable(uriComponent, encoding, value.toString()));
                }
            }
        }
        return encoded;
    }

    private static String format(String template, Map<String, Object> encodedVariables) {
        return new NamedTemplate(template) //
                .addValues(encodedVariables != null ? encodedVariables : new HashMap<>()) //
                .format() //
                .toString();
    }
}
