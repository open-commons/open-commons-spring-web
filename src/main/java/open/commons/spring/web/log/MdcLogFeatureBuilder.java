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
 * Date  : 2025. 7. 29. 오전 10:40:12
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.log;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * {@link IMdcLogFeatureDecorator} 객체를 생성하는 "계층형 Builder"
 * 
 * @since 2025. 7. 29.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class MdcLogFeatureBuilder {

    private MdcLogFeatureBuilder() {
    }

    /** 진입점 */
    public static Builder builder() {
        return new BuilderImpl();
    }

    public interface Builder {

        /**
         * 1개의 {@link IMdcLogFeatureDecorator}를 생성하는 빌더를 제공합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 7. 29.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 7. 29.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        public ObjectBuilder object();

        /**
         * 여러 개의 {@link IMdcLogFeatureDecorator}를 생성하는 빌더를 제공합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 7. 29.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 7. 29.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        public ObjectsBuilder objects();

    }

    private static class BuilderImpl implements Builder {

        /**
         *
         * @since 2025. 7. 29.
         * @version 0.8.0
         * @author parkjunhong77@gmail.com
         *
         * @see open.commons.spring.web.log.MdcLogFeatureBuilder.Builder#object()
         */
        @Override
        public ObjectBuilder object() {
            return new ObjectBuilderImpl();
        }

        /**
         *
         * @since 2025. 7. 29.
         * @version 0.8.0
         * @author parkjunhong77@gmail.com
         *
         * @see open.commons.spring.web.log.MdcLogFeatureBuilder.Builder#objects()
         */
        @Override
        public ObjectsBuilder objects() {
            return new ObjectsBuilderImpl();
        }

        private static class MdcPropertyLogDecoratorBuilder {
            private String feature;
            private String marker;
            private Function<String, String> decorator;

            /**
             * {@link IMdcLogFeatureDecorator} 객체를 생성합니다. <br>
             * 
             * <pre>
             * [개정이력]
             *      날짜    	| 작성자	|	내용
             * ------------------------------------------
             * 2025. 7. 29.		박준홍			최초 작성
             * </pre>
             *
             * @return
             *
             * @since 2025. 7. 29.
             * @version 0.8.0
             * @author Park, Jun-Hong parkjunhong77@gmail.com
             */
            public IMdcLogFeatureDecorator build() {
                return new IMdcLogFeatureDecorator() {

                    @Override
                    public Function<String, String> decorator() {
                        return decorator;
                    }

                    @Override
                    public String feature() {
                        return feature;
                    }

                    @Override
                    public String marker() {
                        return marker;
                    }
                };
            }

            /**
             * 'marker'({@link LogFeature#marker()}) 항목에 해당하는 값을 처리하는 함수를 설정합니다. <br>
             * 
             * <pre>
             * [개정이력]
             *      날짜    	| 작성자	|	내용
             * ------------------------------------------
             * 2025. 7. 29.		박준홍			최초 작성
             * </pre>
             *
             * @param decorator
             *
             * @since 2025. 7. 29.
             * @version 0.8.0
             * @author Park, Jun-Hong parkjunhong77@gmail.com
             */
            public void decorator(@NotNull @Nonnull Function<String, String> decorator) {
                this.decorator = decorator;
            }

            /**
             * 'feature'({@link LogFeature#feature()}) 항목에 해당하는 값을 설정합니다. <br>
             * 
             * <pre>
             * [개정이력]
             *      날짜    	| 작성자	|	내용
             * ------------------------------------------
             * 2025. 7. 29.		박준홍			최초 작성
             * </pre>
             *
             * @param feature
             *
             * @since 2025. 7. 29.
             * @version 0.8.0
             * @author Park, Jun-Hong parkjunhong77@gmail.com
             */
            public void feature(@NotBlank @Nonnull String feature) {
                this.feature = feature;
            }

            /**
             * 'marker'({@link LogFeature#marker()}) 항목에 해당하는 값을 설정합니다. <br>
             * 
             * <pre>
             * [개정이력]
             *      날짜    	| 작성자	|	내용
             * ------------------------------------------
             * 2025. 7. 29.		박준홍			최초 작성
             * </pre>
             *
             * @param marker
             *
             * @since 2025. 7. 29.
             * @version 0.8.0
             * @author Park, Jun-Hong parkjunhong77@gmail.com
             */
            public void marker(String marker) {
                this.marker = marker;
            }

        }

        private static class ObjectBuilderImpl implements ObjectBuilder {

            private final MdcPropertyLogDecoratorBuilder builder = new MdcPropertyLogDecoratorBuilder();

            /**
             *
             * @since 2025. 7. 29.
             * @version 0.8.0
             * @author parkjunhong77@gmail.com
             *
             * @see open.commons.spring.web.log.MdcLogFeatureBuilder.ObjectBuilder#build()
             */
            @Override
            public IMdcLogFeatureDecorator build() {
                return builder.build();
            }

            /**
             *
             * @since 2025. 7. 29.
             * @version 0.8.0
             * @author parkjunhong77@gmail.com
             *
             * @see open.commons.spring.web.log.MdcLogFeatureBuilder.ObjectBuilder#decorator(java.util.function.Function)
             */
            @Override
            public ObjectBuilder decorator(@NotNull Function<String, String> decorator) {
                this.builder.decorator(decorator);
                return this;
            }

            /**
             *
             * @since 2025. 7. 29.
             * @version 0.8.0
             * @author parkjunhong77@gmail.com
             *
             * @see open.commons.spring.web.log.MdcLogFeatureBuilder.ObjectBuilder#feature(java.lang.String)
             */
            @Override
            public ObjectBuilder feature(@NotBlank String feature) {
                this.builder.feature(feature);
                return this;
            }

            /**
             *
             * @since 2025. 7. 29.
             * @version 0.8.0
             * @author parkjunhong77@gmail.com
             *
             * @see open.commons.spring.web.log.MdcLogFeatureBuilder.ObjectBuilder#marker(java.lang.String)
             */
            @Override
            public ObjectBuilder marker(@NotNull String marker) {
                this.builder.marker(marker);
                return this;
            }

        }

        private static class ObjectsBuilderImpl implements ObjectsBuilder {

            private final List<ObjectBuilder> objects = new ArrayList<>();

            /**
             *
             * @since 2025. 7. 29.
             * @version 0.8.0
             * @author parkjunhong77@gmail.com
             *
             * @see open.commons.spring.web.log.MdcLogFeatureBuilder.ObjectsBuilder#build()
             */
            @Override
            public List<IMdcLogFeatureDecorator> build() {
                return objects.stream().map(b -> b.build()).collect(Collectors.toList());
            }

            /**
             *
             * @since 2025. 7. 29.
             * @version 0.8.0
             * @author parkjunhong77@gmail.com
             *
             * @see open.commons.spring.web.log.MdcLogFeatureBuilder.ObjectsBuilder#object(java.util.function.Function)
             */
            @Override
            public ObjectsBuilder object(@NotNull Function<ObjectBuilder, ObjectBuilder> consumer) {
                ObjectBuilder builder = new ObjectBuilderImpl();
                objects.add(consumer.apply(builder));
                return this;
            }

        }
    }

    public interface ObjectBuilder {

        /**
         * {@link IMdcLogFeatureDecorator} 객체를 제공합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 7. 29.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2025. 7. 29.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        IMdcLogFeatureDecorator build();

        /**
         * 'marker'({@link LogFeature#marker()}) 항목에 해당하는 값을 처리하는 함수를 설정합니다. <br>
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 7. 29.		박준홍			최초 작성
         * </pre>
         *
         * @param decorator
         * @return
         *
         * @since 2025. 7. 29.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        ObjectBuilder decorator(@NotNull @Nonnull Function<String, String> decorator);

        /**
         * 'feature'({@link LogFeature#feature()}) 항목에 해당하는 값을 설정합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 7. 29.		박준홍			최초 작성
         * </pre>
         *
         * @param feature
         * @return
         *
         * @since 2025. 7. 29.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        ObjectBuilder feature(@NotBlank @Nonnull String feature);

        /**
         * 'marker'({@link LogFeature#marker()}) 항목에 해당하는 값을 설정합니다. <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2025. 7. 29.		박준홍			최초 작성
         * </pre>
         *
         * @param marker
         * @return
         *
         * @since 2025. 7. 29.
         * @version 0.8.0
         * @author Park, Jun-Hong parkjunhong77@gmail.com
         */
        ObjectBuilder marker(@NotNull @Nonnull String marker);

    }

    public interface ObjectsBuilder {

        List<IMdcLogFeatureDecorator> build();

        ObjectsBuilder object(@NotNull @Nonnull Function<ObjectBuilder, ObjectBuilder> consumer);

    }

}
