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
 * Date  : 2025. 9. 25. 오후 8:19:32
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.serialization;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.beans.authority.FieldAccessAuthorityDecision;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;

/**
 * 
 * @since 2025. 9. 25.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractWrappingSerializer extends JsonSerializer<Object> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected final ApplicationContext context;

    /** serialize 데이터 유형 */
    protected final Class<?> serializedType;
    /** 권한 제어 대상 필드 */
    protected final AnnotatedField annotatedField;
    /** 필드 접근 권한 서비스 */
    protected final IFieldAccessAuthorityProvider fieldAccessor;
    /** 권한제어 유형 처리 기능 */
    protected final IUnauthorizedFieldHandler fieldHandler;
    /** {@link AuthorizedObject}, {@link AuthorizedField} 외부 설정 정보 제공 서비스 */
    protected final IAuthorizedResourcesMetadata authorizedResourcesMetadata;

    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 25.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     * @param serializedType
     *            데이터 유형
     * @param annotatedField
     *            필드 어노테이션
     * @param fieldAccessor
     *            필드 접근제어 서비스
     * @param fieldHandler
     *            필드 데이터 처리 서비스
     * @param authorizedResourcesMetadata
     *            메타데이터 제공 서비스
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractWrappingSerializer(ApplicationContext context, @Nonnull Class<?> serializedType, @Nonnull AnnotatedField annotatedField,
            IFieldAccessAuthorityProvider fieldAccessor, @Nonnull IUnauthorizedFieldHandler fieldHandler, IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
        this.context = context;
        this.serializedType = serializedType;
        this.annotatedField = annotatedField;
        this.fieldAccessor = fieldAccessor;
        this.fieldHandler = fieldHandler;
        this.authorizedResourcesMetadata = authorizedResourcesMetadata;
    }

    protected FieldAccessAuthorityDecision decide() {
        return AuthorizedFieldDecisionUtil.resolve(this.serializedType, this.annotatedField, this.fieldAccessor, this.authorizedResourcesMetadata);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param value
     * @param decision
     * @return
     *
     * @since 2025. 9. 25.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Object handleValue(Object value, FieldAccessAuthorityDecision decision) {
        return decision.accessible ? value : resolveFieldHandler(decision.handleBean).handleObject(decision.handleType, value);
    }

    protected boolean isSimpleType(Class<?> type) {
        return AuthorizedFieldDecisionUtil.isSimpleType(type);
    }

    private IUnauthorizedFieldHandler resolveFieldHandler(String handleBean) {
        if (StringUtils.isNullOrEmptyString(handleBean)) {
            return this.fieldHandler;
        } else {
            return (IUnauthorizedFieldHandler) this.context.getBean(handleBean);
        }
    }
}
