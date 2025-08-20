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
 * Date  : 2025. 8. 20. 오전 9:23:44
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet.filter.header;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.log.LogFeature;

/**
 * 'frond-end' 또는 외부에서 전달한 'header' 정보 중에 공유하기 위한 설정 클래스.
 * 
 * @since 2025. 8. 20.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class SharedHeadersBuiltinProvider {

    /** {@link LogFeature#feature()}에 사용할 정보를 전달하는 헤더 */
    public static final String X_LOG_FEATURE = "X-Log-Feature";

    /** 내부 정의 헤더 */
    private static final List<SharedHeader> HEADERS = new ArrayList<>();

    static {
        // 로그파일 분기 요청 헤더 추가
        HEADERS.add(new DefaultSharedHeader(X_LOG_FEATURE, h -> {
            return StringUtils.isNullOrEmptyString(h) ? false : h.matches(LogFeature.FEATURE_REG_EX);
        }));
    }

    public static List<SharedHeader> load() {
        return Collections.unmodifiableList(HEADERS);
    }
}
