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

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;

import open.commons.core.utils.MapUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.authority.configuratioon.AuthorizedFieldMetadata;
import open.commons.spring.web.authority.configuratioon.AuthorizedObjectMetadata;

/**
 * 
 * @since 2025. 6. 12.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedResourcesMetadata implements IAuthorizedResourcesMetadata {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.beans.authority.AuthorizedResourcesMetadata";

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(AuthorizedResourcesMetadata.class);

    /** {@link AuthorizedObject}가 적용되는 데이터 유형({@link Class}) */
    private final Map<Class<?>, AuthorizedObjectMetadata> authorizedClasses = new ConcurrentHashMap<>();
    /** {@link AuthorizedField}가 적용되는 필드({@link Field}) */
    private final Map<Class<?>, Set<AuthorizedFieldMetadata>> authorizedFields = new ConcurrentHashMap<>();

    private List<AuthorizedObjectMetadata> authorizedObjectMetadata;

    /** {@link AuthorizedObjectMetadata} 데이터 분석 여부 */
    private boolean resolved = false;

    public AuthorizedResourcesMetadata() {

    }

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
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedResourcesMetadata(List<AuthorizedObjectMetadata> metadata) {

        boolean duplicated = false;
        Set<Class<?>> errBuf = new HashSet<>();

        Class<?> aoTargetType = null;
        for (AuthorizedObjectMetadata aoMeta : metadata) {
            aoTargetType = aoMeta.getType();
            if (duplicated) {
                if (this.authorizedClasses.containsKey(aoTargetType)) {
                    errBuf.add(aoTargetType);
                }
            } else {
                if (this.authorizedClasses.containsKey(aoTargetType)) {
                    errBuf.add(aoTargetType);
                    duplicated = true;
                } else {
                    this.authorizedClasses.put(aoTargetType, aoMeta);
                    MapUtils.getOrDefault(this.authorizedFields, aoTargetType, () -> new HashSet<>(), true) //
                            .addAll(aoMeta.getFields());
                }
            }
        }

        if (duplicated) {
            MapUtils.clear(this.authorizedClasses, this.authorizedFields);
            throw new BeanCreationException(BEAN_QUALIFIER, String.format("중복 선언된 클래스가 존재합니다. => %s", errBuf));
        }
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata#getAuthorityBeanName(java.lang.Class)
     */
    @Override
    public String getAuthorityBeanName(@NotNull Class<?> clazz) {
        return isAuthorizedObject(clazz) ? this.authorizedClasses.get(clazz).getAuthorityBean() : null;
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata#getAuthorityBeanName(java.lang.Class,
     *      java.lang.String)
     */
    @Override
    public String getAuthorityBeanName(@NotNull Class<?> clazz, String fieldName) {
        if (isAuthorizedObject(clazz)) {
            Optional<String> opt = this.authorizedFields.get(clazz).stream()//
                    .filter(afm -> afm.getName().equals(fieldName)) //
                    .map(afm -> afm.getAuthorityBean()) //
                    .findAny();
            return opt.isPresent() ? opt.get() : null;
        } else {
            return null;
        }
    }

    /**
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata#getAuthorizedFieldMetadata(java.lang.Class,
     *      java.lang.String)
     */
    @Override
    public AuthorizedFieldMetadata getAuthorizedFieldMetadata(@NotNull Class<?> clazz, String fieldName) {
        if (isAuthorizedObject(clazz)) {
            Optional<AuthorizedFieldMetadata> opt = this.authorizedFields.get(clazz).stream()//
                    .filter(afm -> afm.getName().equals(fieldName)) //
                    .findAny();
            return opt.isPresent() ? opt.get() : null;
        } else {
            return null;
        }
    }

    /**
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata#getAuthorizedObjectMetadata(java.lang.Class)
     */
    @Override
    public AuthorizedObjectMetadata getAuthorizedObjectMetadata(@NotNull Class<?> clazz) {
        return this.authorizedClasses.get(clazz);
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata#getFieldHandleBeanName(java.lang.Class)
     */
    @Override
    public String getFieldHandleBeanName(@NotNull Class<?> clazz) {
        return isAuthorizedObject(clazz) ? this.authorizedClasses.get(clazz).getFieldHandleBean() : null;
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata#getFieldHandleBeanName(java.lang.Class,
     *      java.lang.String)
     */
    @Override
    public String getFieldHandleBeanName(@NotNull Class<?> clazz, String fieldName) {
        if (isAuthorizedObject(clazz)) {
            Optional<String> opt = this.authorizedFields.get(clazz).stream()//
                    .filter(afm -> afm.getName().equals(fieldName)) //
                    .map(afm -> afm.getFieldHandleBean()) //
                    .findAny();
            return opt.isPresent() ? opt.get() : null;
        } else {
            return null;
        }
    }

    /**
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata#isAuthorizedField(java.lang.Class,
     *      java.lang.String)
     */
    @Override
    public boolean isAuthorizedField(@NotNull Class<?> clazz, @NotEmpty String fieldName) {
        if (isAuthorizedObject(clazz)) {
            return this.authorizedFields.get(clazz).stream()//
                    .filter(afm -> afm.getName().equals(fieldName))//
                    .findAny()//
                    .isPresent();
        } else {
            return false;
        }
    }

    /**
     *
     * @since 2025. 6. 12.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata#isAuthorizedObject(java.lang.Class)
     */
    @Override
    public boolean isAuthorizedObject(@NotNull Class<?> clazz) {
        return this.authorizedClasses.containsKey(clazz);
    }

    @PostConstruct
    public void resolveAuthorizedObjectMetadata() {
        if (resolved) {
            return;
        }

        boolean duplicated = false;
        Set<Class<?>> errBuf = new HashSet<>();

        Class<?> aoTargetType = null;
        for (AuthorizedObjectMetadata aoMeta : authorizedObjectMetadata) {
            aoTargetType = aoMeta.getType();
            if (duplicated) {
                if (this.authorizedClasses.containsKey(aoTargetType)) {
                    errBuf.add(aoTargetType);
                }
            } else {
                if (this.authorizedClasses.containsKey(aoTargetType)) {
                    errBuf.add(aoTargetType);
                    duplicated = true;
                } else {
                    this.authorizedClasses.put(aoTargetType, aoMeta);
                    MapUtils.getOrDefault(this.authorizedFields, aoTargetType, () -> new HashSet<>(), true) //
                            .addAll(aoMeta.getFields());
                }
            }
        }

        if (duplicated) {
            MapUtils.clear(this.authorizedClasses, this.authorizedFields);
            throw new BeanCreationException(BEAN_QUALIFIER, String.format("중복 선언된 클래스가 존재합니다. => %s", errBuf));
        }

        resolved = false;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 6. 13.     박준홍         최초 작성
     * </pre>
     *
     * @param authorizedObjectMetadata
     *            the authorizedObjectMetadata to set
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #authorizedObjectMetadata
     */
    public void setAuthorizedObjectMetadata(List<AuthorizedObjectMetadata> authorizedObjectMetadata) {
        this.authorizedObjectMetadata = authorizedObjectMetadata;
    }

}
