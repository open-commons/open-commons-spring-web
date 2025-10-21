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
 * Date  : 2025. 8. 27. 오후 2:23:24
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.rest.service;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.springframework.web.util.DefaultUriBuilderFactory.EncodingMode;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * URI 문자열을 '인코딩'하는 기능을 제공합니다.
 * 
 * @since 2025. 8. 27.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@FunctionalInterface
public interface TemplateUriEncoder {

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 27.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param uriComponent
     *            'URI' 구성요소
     * @param template
     *            'URI 템플릿' 문자열
     * @param varaibles
     *            'URI 템플릿'에 사용될 정보
     *
     * @return
     *
     * @since 2025. 8. 27.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    String encode(@NotNull @Nonnull UriComponent uriComponent, @NotNull @Nonnull String template, ByPassUriTemplateVariables varaibles);

    /**
     * <pre>
    <table border="1" cellspacing="0" cellpadding="8" style=
    "table-layout: fixed; width: 100%; overflow: hidden; text-overflow: ellipsis;">
    <thead>
    <tr>
      <th style="width:150px; word-break: break-all;">{@link EncodingMode}</th>
      <th style="width:300px; word-break: break-all;">의미(요점)</th>
      <th style=
    "width:200px; word-break: break-all;">{@link org.springframework.web.util.UriComponentsBuilder.EncodingHint}<br> “가장 가까운 것”</th>
      <th style="width:300px; word-break: break-all;">왜/어떻게 비슷한가</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td><code>{@link EncodingMode#TEMPLATE_AND_VALUES}</code></td>
      <td>템플릿을 먼저 pre-encode 하고, 변수 값은 엄격(예약문자 포함) 인코딩 후 치환</td>
      <td><code>{@link org.springframework.web.util.UriComponentsBuilder.EncodingHint#ENCODE_TEMPLATE}</code></td>
      <td>둘 다 “템플릿을 미리 인코딩”한다는 점이 같음. EncodingMode 쪽은 추가로 <strong>변수값에 ‘엄격 인코딩’</strong>까지 수행</td>
    </tr>
    <tr>
      <td><code>{@link EncodingMode#VALUES_ONLY}</code></td>
      <td>템플릿은 그대로 두고, 변수 값만 엄격 인코딩 후 치환</td>
      <td>(직접 대응 없음) · 기본 <code>NONE</code>에 “변수만 따로 엄격 인코딩”을 추가한 셈</td>
      <td>UCB는 “변수만” 엄격 인코딩해 주는 힌트가 없음. 보통 템플릿 그대로 두고 값만 <code>UriUtils.encodeUriVariables(...)</code> 같은 방식으로 처리한 뒤 build</td>
    </tr>
    <tr>
      <td><code>{@link EncodingMode#URI_COMPONENT}</code></td>
      <td>변수 먼저 치환 → 최종 URI 컴포넌트별 인코딩(비ASCII/불법문자만, 예약문자 보존)</td>
      <td><code>{@link org.springframework.web.util.UriComponentsBuilder.EncodingHint#NONE}</code> + <code>.build(...).encode()</code></td>
      <td>UCB에서 별도 힌트 없이 build 후 encode() 호출하면 동일한 “컴포넌트 인코딩” 동작</td>
    </tr>
    <tr>
      <td><code>{@link EncodingMode#NONE}</code></td>
      <td>어떤 인코딩도 하지 않음(입력이 이미 인코딩돼 있다고 가정)</td>
      <td><code>{@link org.springframework.web.util.UriComponentsBuilder.EncodingHint#FULLY_ENCODED}</code></td>
      <td>UCB의 FULLY_ENCODED는 “구성요소가 이미 인코딩됨”을 뜻해 추가 인코딩을 하지 않음 — 효과가 EncodingMode.NONE과 가장 유사</td>
    </tr>
    </tbody>
    </table>
     * 
     * </pre>
     * 
     * @since 2025. 8. 27.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public static enum Encoding {
        /**
         * "URL 템플릿"와 "변수값"을 모두 "인코딩" 한 후에 치환을 합니다. 예약문자(&, =, +, /, > 등)가 보존되지 않습니다.<br>
         * 아래 설정값에 대응됩니다.
         * <li>{@link EncodingMode#TEMPLATE_AND_VALUES}
         * <li>{@link org.springframework.web.util.UriComponentsBuilder.EncodingHint#ENCODE_TEMPLATE}:
         * {@link UriComponentsBuilder#encode()} 또는 {@link UriComponentsBuilder#encode(java.nio.charset.Charset)} 를 호출한
         * 이후에 {@link UriComponentsBuilder#build()}, {@link UriComponentsBuilder#build(Object...)},
         * {@link UriComponentsBuilder#build(java.util.Map)}, {@link UriComponentsBuilder#build(boolean)}에
         * <code>false</code>를 전달하는 경우.
         */
        TEMPLATE_AND_VALUES,
        /**
         * "URL 템플릿"은 그대로 두고, "변수값"을 모두 "인코딩" 후에 치환을 합니다. 예약문자(&, =, +, /, > 등)가 보존되지 않습니다.<br>
         * 아래 설정값에 대응됩니다.
         * <li>{@link EncodingMode#VALUES_ONLY}
         * <li>
         * <li>{@link org.springframework.web.util.UriComponentsBuilder.EncodingHint}는 유사한 정의 없음.
         */
        VALUES_ONLY_STRICT,
        /**
         * "URL 템플릿"은 그대로 두고, "변수값"을 모두 "인코딩" 후에 치환을 합니다. 예약문자(&, =, +, /, > 등)가 보존됩니다.<br>
         * 아래 설정값에 대응됩니다.
         * <li>{@link EncodingMode#URI_COMPONENT}
         * <li>{@link org.springframework.web.util.UriComponentsBuilder.EncodingHint#NONE}:
         * {@link UriComponentsBuilder#encode()} 또는 {@link UriComponentsBuilder#encode(java.nio.charset.Charset)} 를 호출하지
         * 않고, {@link UriComponentsBuilder#build()}를 호출하는 경우.
         */
        VALUES_ONLY_RESERVED,
        /**
         * "URL 템플릿"과 "변수값"에 대해서 치환을 하지 않습니다.<br>
         * 아래 설정값에 대응됩니다.
         * <li>{@link EncodingMode#NONE}
         * <li>{@link org.springframework.web.util.UriComponentsBuilder.EncodingHint#FULLY_ENCODED}: 변수 치환 및 인코딩이 완료된
         * 상태를 의미 (그렇지 않은 경우 {@link IllegalArgumentException} 발생)하며, {@link UriComponentsBuilder#build(boolean)}에
         * <code>true</code>를 전달하는 경우.
         */
        NONE,
        //
        ;
    }

    /**
     * Full Qualified URL 구성요소<br>
     * 포맷: {scheme}://({userinfo@})?{host}(:{port})?(/{path}(\?{query})?(#{fragment})?)?
     * 
     * @since 2025. 8. 28.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public static enum UriComponent {
        /** URI */
        URI,
        /** scheme */
        SCHEME,
        /** userinfo */
        USER_INFO,
        /** host */
        HOST,
        /** port */
        PORT,
        /** path */
        PATH,
        /** query */
        QUERY,
        /** fragment */
        FRAGMENT,
    }
}
