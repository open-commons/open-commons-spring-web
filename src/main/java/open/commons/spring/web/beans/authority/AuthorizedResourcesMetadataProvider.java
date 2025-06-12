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
 * Date  : 2025. 6. 12. 오후 8:31:05
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import java.util.List;

import javax.validation.constraints.NotNull;

import open.commons.spring.web.authority.configuratioon.AuthorizedObjectMetadata;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourcesMetadataProvider implements IAuthorizedResourcesMetadataProvider {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.beans.authority.AuthorizedResourcesMetadataProvider";

    private final List<AuthorizedObjectMetadata> authorizedObjectMetadata;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 12.		박준홍			최초 작성
     * </pre>
     * 
     * @param metadata
     *            TODO
     *
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedResourcesMetadataProvider(List<AuthorizedObjectMetadata> metadata) {
        this.authorizedObjectMetadata = metadata;
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadataProvider#getAuthorityBeanName(java.lang.Class)
     */
    @Override
    public String getAuthorityBeanName(@NotNull Class<?> clazz) {
        return null;
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadataProvider#getAuthorityBeanName(java.lang.Class,
     *      java.lang.String)
     */
    @Override
    public String getAuthorityBeanName(@NotNull Class<?> clazz, String fieldName) {
        return null;
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadataProvider#getFieldHandleBeanName(java.lang.Class)
     */
    @Override
    public String getFieldHandleBeanName(@NotNull Class<?> clazz) {
        return null;
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadataProvider#getFieldHandleBeanName(java.lang.Class,
     *      java.lang.String)
     */
    @Override
    public String getFieldHandleBeanName(@NotNull Class<?> clazz, String fieldName) {
        return null;
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadataProvider#isAuthorized(java.lang.Class)
     */
    @Override
    public boolean isAuthorized(@NotNull Class<?> clazz) {
        return false;
    }

}
