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
 * Date  : 2025. 9. 22. 오후 1:31:13
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.MapUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.authority.metadata.AuthorizedObjectMetadata;
import open.commons.spring.web.authority.metadata.AuthorizedRequestDataFieldMetadata;
import open.commons.spring.web.authority.metadata.AuthorizedRequestDataObjectMetadata;

/**
 * 
 * @since 2025. 9. 22.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedRequestDataMetadata implements IAuthorizedRequestDataMetadata {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.beans.authority.AuthorizedRequestDataMetadata";

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(AuthorizedRequestDataMetadata.class);

    /** {@link AuthorizedObject}가 적용되는 데이터 유형({@link Class}) */
    private final Map<Class<?>, AuthorizedRequestDataObjectMetadata> authorizedRequestClasses = new ConcurrentHashMap<>();
    /** {@link AuthorizedField}가 적용되는 필드({@link Field}) */
    private final Map<Class<?>, Set<AuthorizedRequestDataFieldMetadata>> authorizedRequestFields = new ConcurrentHashMap<>();
    /** 외부 설정 정보 */
    private Collection<AuthorizedRequestDataObjectMetadata> authorizedRequestObjectMetadata;

    /** {@link AuthorizedObjectMetadata} 데이터 분석 여부 */
    private boolean resolved = false;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 22.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedRequestDataMetadata() {
    }

    /**
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata#getFieldMetadat(java.lang.Class,
     *      java.lang.String)
     */
    @Override
    public AuthorizedRequestDataFieldMetadata getFieldMetadat(@NotNull @Nonnull Class<?> targetClass, @NotBlank String fieldName) {
        Class<?> supportingClass = supporingAuthorizedRequestDataObjectType(targetClass);
        if (supportingClass != null) {
            Optional<AuthorizedRequestDataFieldMetadata> opt = this.authorizedRequestFields.get(supportingClass).stream() //
                    .filter(fm -> fm.getName().equals(fieldName)) //
                    .findAny();
            return opt.isPresent() ? opt.get() : null;
        } else {
            return null;
        }
    }

    /**
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata#getHandleBeanName(java.lang.Class,
     *      java.lang.String)
     */
    @Override
    public String getHandleBeanName(@NotNull @Nonnull Class<?> targetClass, @NotBlank String fieldName) {
        AuthorizedRequestDataObjectMetadata om = getObjectMetadata(targetClass);
        if (om == null) {
            return null;
        }
        AuthorizedRequestDataFieldMetadata fm = getFieldMetadat(targetClass, fieldName);
        return fm != null //
                ? StringUtils.isNullOrEmptyString(fm.getHandleBean()) ? om.getHandleBean() : fm.getHandleBean() //
                : om.getHandleBean();
    }

    /**
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata#getHandleType(java.lang.Class,
     *      java.lang.String)
     */
    @Override
    public String getHandleType(@NotNull @Nonnull Class<?> targetClass, @NotBlank String fieldName) {
        AuthorizedRequestDataFieldMetadata fm = getFieldMetadat(targetClass, fieldName);
        return fm != null ? fm.getHandleType() : AuthorizedRequestData.NO_ASSINGED_HANDLE_TYPE;
    }

    /**
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata#getObjectMetadata(java.lang.Class)
     */
    @Override
    public AuthorizedRequestDataObjectMetadata getObjectMetadata(@NotNull @Nonnull Class<?> targetClass) {
        AssertUtils2.notNull(targetClass);

        // 일치하는 클래스 조회
        AuthorizedRequestDataObjectMetadata om = this.authorizedRequestClasses.get(targetClass);
        if (om != null) {
            return om;
        }

        // 상위 클래스 조회
        for (Entry<Class<?>, AuthorizedRequestDataObjectMetadata> entry : this.authorizedRequestClasses.entrySet()) {
            if (entry.getKey().isAssignableFrom(targetClass)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.web.beans.authority.IAuthorizedRequestDataMetadata#isAuthorizedRequestDataObject(java.lang.Class)
     */
    @Override
    public boolean isAuthorizedRequestDataObject(@NotNull @Nonnull Class<?> targetClass) {
        return getObjectMetadata(targetClass) != null;
    }

    @SuppressWarnings("unused")
    @PostConstruct
    public void resolveAuthorizedRequestObjectMetadata() {
        if (this.resolved) {
            return;
        }

        if (this.authorizedRequestObjectMetadata != null) {
            boolean duplicated = false;
            Set<Class<?>> errBuf = new HashSet<>();

            Class<?> aoTargetType = null;

            Supplier<AuthorizedRequestDataObjectMetadata> aomSup = null;
            Supplier<Set<AuthorizedRequestDataFieldMetadata>> afmSup = null;
            Set<AuthorizedRequestDataFieldMetadata> afmsCur = null;
            List<AuthorizedRequestDataFieldMetadata> afmsNew = null;
            Map<Class<?>, List<AuthorizedRequestDataObjectMetadata>> aoTypes = new HashMap<>();
            Supplier<List<AuthorizedRequestDataObjectMetadata>> allAomSup = null;
            for (AuthorizedRequestDataObjectMetadata objectMeta : this.authorizedRequestObjectMetadata) {
                // #1. Authorized Request Object 설정
                aoTargetType = objectMeta.getType();
                MapUtils.getOrDefault(this.authorizedRequestClasses, aoTargetType, aomSup = () -> objectMeta, true);
                // #2. Authorized Request Field 설정
                afmsNew = objectMeta.getFields();
                afmsCur = MapUtils.getOrDefault(this.authorizedRequestFields, aoTargetType, afmSup = () -> new HashSet<>(), true);
                for (AuthorizedRequestDataFieldMetadata afm : afmsNew) {
                    // 중복 체크
                    if (afmsCur.contains(afm)) {
                        errBuf.add(aoTargetType);
                        duplicated = true;
                    } else {
                        afmsCur.add(afm);
                    }
                }
                MapUtils.getOrDefault(aoTypes, aoTargetType, allAomSup = () -> new ArrayList<>(), true).add(objectMeta);
            }

            if (duplicated) {
                MapUtils.clear(this.authorizedRequestClasses, this.authorizedRequestFields);
                throw new BeanCreationException(IAuthorizedResourcesMetadata.class.getName(), //
                        String.format("중복 선언된 필드가 존재합니다. => %s", //
                                errBuf.stream().map(aoTypes::get).flatMap(List::stream).collect(Collectors.toList())) //
                );
            }

        } else {
            this.authorizedRequestObjectMetadata = new ArrayList<>();
        }

        this.resolved = true;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 22.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param authorizedRequestObjectMetadata
     *            the authorizedRequestObjectMetadata to set
     *
     * @since 2025. 9. 22.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #authorizedRequestObjectMetadata
     */
    public void setAuthorizedRequestObjectMetadata(Collection<AuthorizedRequestDataObjectMetadata> authorizedRequestObjectMetadata) {
        this.authorizedRequestObjectMetadata = authorizedRequestObjectMetadata;
        this.resolved = false;
    }

    /**
     * 대상 클래스에 해당하는 정보 또는 대상 클래스의 상위 클래스를 지원하는 정보를 제공합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param targetClass
     * @return
     *
     * @since 2025. 9. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private Class<?> supporingAuthorizedRequestDataObjectType(Class<?> targetClass) {
        AuthorizedRequestDataObjectMetadata om = getObjectMetadata(targetClass);
        return om != null ? om.getType() : null;
    }

}
