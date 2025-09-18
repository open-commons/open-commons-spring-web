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
 * Date  : 2025. 9. 18. 오후 4:02:08
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.resolver;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import open.commons.spring.web.authority.AuthorizedField;

/**
 * {@link AuthorizedField}에 따라서 처리된 데이터를 원복하는 기능을 제공하는 인터페이스를 지정하기 위함.
 * 
 * @since 2025. 9. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public interface IAuthorizedDataResolver extends HandlerMethodArgumentResolver {
}
