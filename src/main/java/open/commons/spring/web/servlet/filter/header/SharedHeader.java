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
 * Date  : 2025. 8. 20. 오전 9:30:23
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.servlet.filter.header;

import java.util.function.Predicate;

/**
 * 'frond-end' 또는 외부에서 전달한 'header' 정보 중에 공유하기 위한 설정 기능.
 * 
 * @since 2025. 8. 20.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface SharedHeader {

    /**
     * 헤더 이름을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 20.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public String getHeader();

    /**
     * 헤더값 검증 도구를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 20.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public Predicate<String> getValidator();

}
