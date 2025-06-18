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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Bean;

import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedField;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.spring.web.utils.ClassInspector;

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

        /**
         * 
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 19.		박준홍			최초 작성
         * </pre>
         *
         * @param builder
         * @param target
         * @param postprocessors
         *            <li>key: 필드 이름
         *            <li>value: 후처리 모듈
         *
         * @since 2025. 6. 19.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        private static void set(Object builder, Object target, Map<String, Function<Object, Object>> postprocessors) {
            Class<?> targetClass = target.getClass();
            Boolean targetFieldAccessible = null;
            String targetFieldName = null;
            Method targetMethod = null;

            Class<?> builderClass = builder.getClass();
            Field builderField = null;
            Boolean builderFieldAccessible = null;
            Object value = null;
            for (Field targetField : targetClass.getDeclaredFields()) {
                try {
                    // #1. 대상 클래스 필드 접근 허용
                    targetFieldAccessible = allowAccess(targetField);
                    // #2. builder 객체에서 동일한 이름의 필드 정보 조회
                    builderField = ClassInspector.getDeclaredFieldIfExist(builderClass, targetFieldName = targetField.getName());
                    if (builderField == null) {
                        continue;
                    }

                    builderFieldAccessible = allowAccess(builderField);
                    value = builderField.get(builder);
                    // builderField.get(builder) 후처리
                    if (postprocessors != null && postprocessors.containsKey(targetFieldName)) {
                        value = postprocessors.get(builderField.getName()).apply(value);
                    }

                    if (value == null) {
                        continue;
                    }

                    targetMethod = targetClass.getMethod(String.join("", "set", StringUtils.toUpperCase(targetFieldName, 0)), targetField.getType());
                    targetMethod.invoke(target, value);
                } catch (Exception e) {
                    throw ExceptionUtils.newException(InternalServerException.class, e,
                            "데이터 처리 도중 오류가 발생하였습니다. target.class=%s, target.method=%s, target.object=%s / builder.class=%s, builder.field=%s, builder.object=%s" //
                            , targetClass, targetMethod, target, builderClass, builderField, builder);
                } finally {
                    if (targetFieldAccessible != null && targetField != null) {
                        targetField.setAccessible(targetFieldAccessible);
                    }
                    if (builderFieldAccessible != null && builderField != null) {
                        builderField.setAccessible(builderFieldAccessible);
                    }

                    targetFieldAccessible = null;
                    builderFieldAccessible = null;
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
            private int handleType;
            private String name;

            public void authorityBean(String authorityBean) {
                this.authorityBean = authorityBean;
            }

            public AuthorizedFieldMetadata build() {
                AuthorizedFieldMetadata f = new AuthorizedFieldMetadata();
                set(this, f, null);
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
        @SuppressWarnings({ "unused", "unchecked" })
        private static class AuthorizedObjectMetadataBuilder {
            static Map<String, Function<Object, Object>> pp = new HashMap<>();
            static {
                pp.put("fields", o -> ((List<FieldBuilder>) o).stream().map(b -> b.build()).collect(Collectors.toList()));
            }
            private String authorityBean;
            private String fieldHandleBean;
            private Class<?> type;
            private List<FieldBuilder> fields;

            public void authorityBean(String authorityBean) {
                this.authorityBean = authorityBean;
            }

            public AuthorizedObjectMetadata build() {
                AuthorizedObjectMetadata o = new AuthorizedObjectMetadata();
                set(this, o, pp);
                return o;
            }

            public void fieldHandleBean(String fieldHandleBean) {
                this.fieldHandleBean = fieldHandleBean;
            }

            public void fields(List<FieldBuilder> fields) {
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

            private final AuthorizedFieldMetadataBuilder fieldBuilder = new AuthorizedFieldMetadataBuilder();

            public FieldBuilderImpl() {
            }

            @Override
            public FieldBuilder authorityBean(String authorityBean) {
                fieldBuilder.authorityBean(authorityBean);
                return this;
            }

            @Override
            public AuthorizedFieldMetadata build() {
                return fieldBuilder.build();
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
            private final List<FieldBuilder> fields = new ArrayList<>();

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

            // @Override
            // public ObjectBuilder field(Consumer<FieldBuilder> consumer) {
            // FieldBuilderImpl fb = new FieldBuilderImpl();
            // consumer.accept(fb);
            // fields.add(fb);
            // return this;
            // }

            @SuppressWarnings("unused")
            @Override
            public ObjectBuilder field(Function<FieldBuilder, FieldBuilder> consumer) {
                FieldBuilderImpl fb = new FieldBuilderImpl();
                fields.add(consumer.apply(fb));
                return this;
            }

            @Override
            public ObjectBuilder fieldHandleBean(String fieldHandleBean) {
                objectBuilder.fieldHandleBean(fieldHandleBean);
                return this;
            }

            @Override
            public ObjectBuilder type(@NotNull Class<?> type) {
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
        AuthorizedFieldMetadata build();

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

        // /**
        // * {@link AuthorizedFieldMetadata} Builder를 생성합니다. <br>
        // *
        // * <pre>
        // * [개정이력]
        // * 날짜 | 작성자 | 내용
        // * ------------------------------------------
        // * 2025. 6. 19. 박준홍 최초 작성
        // * </pre>
        // *
        // * @param consumer
        // * @return
        // *
        // * @since 2025. 6. 19.
        // * @version 0.8.0
        // * @author Park, Jun-Hong parkjunhong77@gmail.com
        // */
        // ObjectBuilder field(Consumer<FieldBuilder> consumer);

        /**
         * {@link AuthorizedFieldMetadata} Builder를 생성합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 6. 19.		박준홍			최초 작성
         * </pre>
         *
         * @param consumer
         * @return
         *
         * @since 2025. 6. 19.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        ObjectBuilder field(Function<FieldBuilder, FieldBuilder> consumer);

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
