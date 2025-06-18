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
 * Date  : 2025. 6. 18. 오후 2:30:05
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.authority.metadata;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Bean;

import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.servlet.InternalServerException;

/**
 * {@link AuthorizedObjectMetadata} 객체를 생성하는 "계층형 Builder".
 * 
 * @since 2025. 6. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedMetadataBuilder {

    /**
     * 진입점
     */
    public static Builder builder() {
        return new BuilderImpl();
    }

    /**
     * Stage 1: 시작점
     */
    public interface Builder {
        ObjectBuilder object();
    }

    /**
     * 단계별 내부 클래스 구조: Stage 인터페이스 별로 개별 클래스
     */
    private static class BuilderImpl implements Builder {

        @Override
        public ObjectBuilder object() {
            return new ObjectBuilderImpl();
        }

        private static boolean allowAccess(AccessibleObject o) {
            boolean accessible = o.isAccessible();
            o.setAccessible(true);
            return accessible;
        }

        private static void set(Object builder, Object target) {
            Class<?> builderClass = builder.getClass();
            Class<?> targetClass = target.getClass();
            Method targetMethod = null;
            boolean accessible = false;
            for (Field builderField : builderClass.getDeclaredFields()) {
                try {
                    accessible = allowAccess(builderField);
                    if (builderField.get(builder) != null) {
                        targetMethod = targetClass.getMethod(String.join("", "set", StringUtils.toUpperCase(builderField.getName(), 0)), builderField.getType());
                        targetMethod.invoke(target, builderField.get(builder));
                    }
                } catch (Exception e) {
                    throw ExceptionUtils.newException(InternalServerException.class, e, "데이터 처리 도중 오류가 발생하였습니다. class=%s, method=%s, object=%s", builderClass, targetMethod, target);
                } finally {
                    builderField.setAccessible(accessible);
                }
            }
        }

        /**
         * 내부 FieldMetadata Builder
         */
        @SuppressWarnings("unused")
        private static class AuthorizedFieldMetadataBuilder {
            private String authorityBean;
            private String fieldHandleBean;
            private int handleType = AuthorizedField.NO_ASSINGED_HANDLE_TYPE;
            private String name;

            public void authorityBean(String authorityBean) {
                this.authorityBean = authorityBean;
            }

            public AuthorizedFieldMetadata build() {
                AuthorizedFieldMetadata f = new AuthorizedFieldMetadata();
                set(this, f);
                return f;
            }

            public void fieldHandleBean(String fieldHandleBean) {
                this.fieldHandleBean = fieldHandleBean;
            }

            public void handleType(int handleType) {
                this.handleType = handleType;
            }

            public void name(String name) {
                this.name = name;
            }
        }

        /**
         * 내부 ObjectMetadata Builder
         */
        @SuppressWarnings("unused")
        private static class AuthorizedObjectMetadataBuilder {
            private String authorityBean;
            private String fieldHandleBean;
            private Class<?> type;
            private List<AuthorizedFieldMetadata> fields;

            public void authorityBean(String authorityBean) {
                this.authorityBean = authorityBean;
            }

            public AuthorizedObjectMetadata build() {
                AuthorizedObjectMetadata o = new AuthorizedObjectMetadata();
                set(this, o);
                return o;
            }

            public void fieldHandleBean(String fieldHandleBean) {
                this.fieldHandleBean = fieldHandleBean;
            }

            public void fields(List<AuthorizedFieldMetadata> fields) {
                this.fields = fields;
            }

            public void type(Class<?> type) {
                this.type = type;
            }
        }

        /**
         * FieldBuilder 단계 전용 클래스
         */
        private static class FieldBuilderImpl implements FieldBuilder {

            private final ObjectBuilderImpl parent;
            private final List<AuthorizedFieldMetadata> fields;
            private final AuthorizedFieldMetadataBuilder fieldBuilder = new AuthorizedFieldMetadataBuilder();

            public FieldBuilderImpl(ObjectBuilderImpl parent, List<AuthorizedFieldMetadata> fields) {
                this.parent = parent;
                this.fields = fields;
            }

            @Override
            public FieldBuilder authorityBean(String authorityBean) {
                fieldBuilder.authorityBean(authorityBean);
                return this;
            }

            @Override
            public AuthorizedObjectMetadata build() {
                fields.add(fieldBuilder.build());
                return parent.build();
            }

            @Override
            public FieldBuilder field() {
                fields.add(fieldBuilder.build());
                return new FieldBuilderImpl(parent, fields);
            }

            @Override
            public FieldBuilder fieldHandleBean(String fieldHandleBean) {
                fieldBuilder.fieldHandleBean(fieldHandleBean);
                return this;
            }

            @Override
            public FieldBuilder handleType(int handleType) {
                fieldBuilder.handleType(handleType);
                return this;
            }

            @Override
            public FieldBuilder name(String name) {
                fieldBuilder.name(name);
                return this;
            }
        }

        /**
         * ObjectBuilder 단계 전용 클래스
         */
        private static class ObjectBuilderImpl implements ObjectBuilder {

            private final AuthorizedObjectMetadataBuilder objectBuilder = new AuthorizedObjectMetadataBuilder();
            private final List<AuthorizedFieldMetadata> fields = new ArrayList<>();

            @Override
            public ObjectBuilder authorityBean(String authorityBean) {
                objectBuilder.authorityBean(authorityBean);
                return this;
            }

            @Override
            public AuthorizedObjectMetadata build() {
                objectBuilder.fields(fields);
                return objectBuilder.build();
            }

            @Override
            public FieldBuilder field() {
                return new FieldBuilderImpl(this, fields);
            }

            @Override
            public ObjectBuilder fieldHandleBean(String fieldHandleBean) {
                objectBuilder.fieldHandleBean(fieldHandleBean);
                return this;
            }

            @Override
            public ObjectBuilder type(Class<?> type) {
                objectBuilder.type(type);
                return this;
            }
        }
    }

    /**
     * Stage 3: Field 단계
     */
    public interface FieldBuilder {
        /**
         * {@link AuthorizedField#authorityBean()}에 해당하는 값을 설정합니다.
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 18.		박준홍			최초 작성
         * </pre>
         *
         * @param authorityBean
         *            {@link IFieldAccessAuthorityProvider}를 구현함 {@link Bean} 이름.
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         * 
         * @see AuthorizedFieldMetadata#setAuthorityBean(String)
         */
        FieldBuilder authorityBean(String authorityBean);

        /**
         * {@link AuthorizedObjectMetadata} 객체를 생성합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 18.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        AuthorizedObjectMetadata build();

        /**
         * 새로운 {@link AuthorizedFieldMetadata}를 생성합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 18.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        FieldBuilder field();

        /**
         * {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean} 이름을 설정합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 18.		박준홍			최초 작성
         * </pre>
         *
         * @param fieldHandleBean
         *            {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean} 이름
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        FieldBuilder fieldHandleBean(String fieldHandleBean);

        /**
         * 데이터 처리 유형을 설정합니다.<br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 18.		박준홍			최초 작성
         * </pre>
         *
         * @param handleType
         *            데이터 처리 유형
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        FieldBuilder handleType(int handleType);

        /**
         * {@link Field} 이름을 설정합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 18.		박준홍			최초 작성
         * </pre>
         *
         * @param name
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        FieldBuilder name(String name);
    }

    /**
     * Stage 2: Object 단계
     */
    public interface ObjectBuilder {

        /**
         * {@link AuthorizedField#authorityBean()}에 해당하는 값을 설정합니다.
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 6. 18.     박준홍         최초 작성
         * </pre>
         *
         * @param authorityBean
         *            {@link IFieldAccessAuthorityProvider}를 구현함 {@link Bean} 이름.
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        ObjectBuilder authorityBean(String authorityBean);

        /**
         * {@link AuthorizedObjectMetadata} 객체를 생성합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 18.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        AuthorizedObjectMetadata build();

        /**
         * {@link AuthorizedFieldMetadata}를 생성합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 18.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        FieldBuilder field();

        /**
         * {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean} 이름을 설정합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜      | 작성자   |   내용
         * ------------------------------------------
         * 2025. 6. 18.     박준홍         최초 작성
         * </pre>
         *
         * @param fieldHandleBean
         *            {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean} 이름
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        ObjectBuilder fieldHandleBean(String fieldHandleBean);

        /**
         * 권한설정 데이터 유형을 설정합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 18.		박준홍			최초 작성
         * </pre>
         *
         * @param type
         * @return
         *
         * @since 2025. 6. 18.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        ObjectBuilder type(@NotNull Class<?> type);
    }
}
