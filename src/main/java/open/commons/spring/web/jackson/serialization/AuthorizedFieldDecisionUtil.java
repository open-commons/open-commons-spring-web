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
 * Date  : 2025. 9. 25. 오후 8:33:57
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson.serialization;

import java.lang.reflect.Field;
import java.util.UUID;

import org.springframework.core.annotation.AnnotationUtils;

import open.commons.core.Result;
import open.commons.core.TwoValueObject;
import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.authority.metadata.AuthorizedFieldMetadata;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.utils.BeanUtils;

import com.fasterxml.jackson.databind.introspect.AnnotatedField;

/**
 * 
 * @since 2025. 9. 25.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedFieldDecisionUtil {

    private AuthorizedFieldDecisionUtil() {
    }

    public static boolean isSimpleType(Class<?> type) {
        return BeanUtils.isSimpleValueType(type) || UUID.class.equals(type);
    }

    public static TwoValueObject<Boolean, Integer> resolve(Class<?> serializedType, AnnotatedField annotatedField, IFieldAccessAuthorityProvider fieldAccessor,
            IAuthorizedResourcesMetadata metadata) {

        final Field field = annotatedField.getAnnotated();
        final Class<?> declaringClass = annotatedField.getDeclaringClass();
        final String fieldName = annotatedField.getName();

        int handle = AuthorizedField.NO_ASSINGED_HANDLE_TYPE;
        boolean accessible = false;

        // 1) @AuthorizedField 우선
        AuthorizedField authField = AnnotationUtils.findAnnotation(field, AuthorizedField.class);
        if (authField != null) {
            handle = authField.handleType();
        } else {
            // 2) 메타데이터 조회 (serializedType → declaringClass 순)
            AuthorizedFieldMetadata afm = metadata.getAuthorizedFieldMetadata(serializedType, fieldName);
            if (afm == null) {
                afm = metadata.getAuthorizedFieldMetadata(declaringClass, fieldName);
            }
            if (afm != null) {
                handle = afm.getHandleType();
            }
        }

        try {
            // 3) 아직 미지정이면 권한서비스로 접근/처리결정 조회
            if (handle == AuthorizedField.NO_ASSINGED_HANDLE_TYPE) {
                Result<TwoValueObject<Boolean, Integer>> res = fieldAccessor.isAllowed(declaringClass.getName(), fieldName);
                if (res == null || res.isError() || res.getData() == null) {
                    throw ExceptionUtils.newException(InternalServerException.class, "필드 접근권한 조회 실패. type=%s, field=%s, result=%s", declaringClass, fieldName, res);
                }
                TwoValueObject<Boolean, Integer> r = res.getData();
                accessible = r.first;
                handle = r.second;
            } else {
                // handleType이 정해져 있으면 접근은 허용으로 간주 (필요 시 정책 조정)
                accessible = false; // → false로 두고 'handle' 에 따라 변환 수행
            }
        } catch (Exception e) {
            throw ExceptionUtils.newException(InternalServerException.class, e, "필드 처리결정 중 오류. type=%s, field=%s, 원인=%s", declaringClass, fieldName, e.getMessage());
        }

        return new TwoValueObject<>(accessible, handle);
    }
}
