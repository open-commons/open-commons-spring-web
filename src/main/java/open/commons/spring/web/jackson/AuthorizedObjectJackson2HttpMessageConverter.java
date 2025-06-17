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
 * Date  : 2025. 5. 26. 오후 4:26:48
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.jackson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.TypeUtils;
import org.springframework.web.bind.annotation.RestController;

import open.commons.spring.web.authority.AuthorizedObject;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesConfiguration;
import open.commons.spring.web.beans.authority.IAuthorizedResourcesMetadata;
import open.commons.spring.web.beans.authority.IFieldAccessAuthorityProvider;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.config.AuthorizedResourcesMetadataConfiguration;
import open.commons.spring.web.utils.ClassInspector;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.ser.FilterProvider;

/**
 * 사용자 권한에 기반하여 {@link RestController} 또는 {@link Controller}에서 반환하는 데이터(VO)의 값을 제어하는 클래스.<br>
 * 이 클래스를 사용하기 위해서는 {@link IFieldAccessAuthorityProvider}와 {@link IUnauthorizedFieldHandler}를 구현한 {@link Bean}이 제공되어야
 * 합니다.
 * 
 * @since 2025. 5. 26.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @see IFieldAccessAuthorityProvider
 * @see IUnauthorizedFieldHandler
 * @see AuthorizedResourcesMetadataConfiguration
 */
public class AuthorizedObjectJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public static final String BEAN_QUALIFIER = "open.commons.spring.web.jackson.AuthorizedObjectJackson2HttpMessageConverter";

    private static final String DEFAULT_JACKSON_OBJECT_MAPPER = "jacksonObjectMapper";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @NotNull
    private final Map<String, ObjectMapper> allObjectMappers;

    @NotNull
    private final IAuthorizedResourcesMetadata authorizedResourcesMetadata;

    @Nullable
    private Map<Class<?>, Map<MediaType, ObjectMapper>> objectMapperRegistrations;

    @Nullable
    private final PrettyPrinter ssePrettyPrinter;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 26.		박준홍			최초 작성
     * </pre>
     * 
     * @param objectMapper
     *            {@link Primary} {@link ObjectMapper}
     * @param allObjectMappers
     *            모든 {@link ObjectMapper}
     * @param authorizedResourcesMetadata
     *            TODO
     *
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedObjectJackson2HttpMessageConverter(ObjectMapper objectMapper, @NotNull Map<String, ObjectMapper> allObjectMappers,
            @NotNull IAuthorizedResourcesMetadata authorizedResourcesMetadata) {
        super(objectMapper);
        this.allObjectMappers = allObjectMappers;
        this.authorizedResourcesMetadata = authorizedResourcesMetadata;
        this.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentObjectsWith(new DefaultIndenter("  ", "\ndata:"));
        this.ssePrettyPrinter = prettyPrinter;
    }

    /**
     * 자신 및 상위 클래스의 정보가 외부설정(메타정보)에서 설정되었는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param clazz
     * @return
     *
     * @since 2025. 6. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private boolean annotatedOnMetadata(Class<?> clazz) {
        while (!Object.class.equals(clazz)) {
            if (this.authorizedResourcesMetadata.isAuthorizedObject(clazz)) {
                return true;
            }
            clazz = clazz.getSuperclass();
        }

        return false;
    }

    /**
     * {@link AbstractJackson2HttpMessageConverter}가 {@link ObjectMapper}를 선택하는 메커니즘을 최대한 따르기 위해서
     * {@link AbstractJackson2HttpMessageConverter}의
     * <code>private Map<Class<?>, Map<MediaType, ObjectMapper>> getObjectMapperRegistrations()</code>를 이관한 메소드입니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 27.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 5. 27.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private Map<Class<?>, Map<MediaType, ObjectMapper>> getObjectMapperRegistrations() {
        return (this.objectMapperRegistrations != null ? this.objectMapperRegistrations : Collections.emptyMap());
    }

    private boolean hasAuthorizedObject(Object obj, Set<Object> visited) {
        if (obj == null || visited.contains(obj))
            return false;

        visited.add(obj);

        Class<?> clazz = obj.getClass();

        // direct class check
        if (clazz.isAnnotationPresent(AuthorizedObject.class) || annotatedOnMetadata(clazz)) {
            return true;
        }

        // array
        if (clazz.isArray()) {
            int len = Array.getLength(obj);
            for (int i = 0; i < len; i++) {
                Object element = Array.get(obj, i);
                if (hasAuthorizedObject(element, visited))
                    return true;
            }
        }

        // collection
        if (obj instanceof Collection) {
            for (Object item : (Collection<?>) obj) {
                if (hasAuthorizedObject(item, visited))
                    return true;
            }
        }

        // map
        if (obj instanceof Map) {
            for (Object value : ((Map<?, ?>) obj).values()) {
                if (hasAuthorizedObject(value, visited))
                    return true;
            }
        }

        // recursive field scan
        List<Field> fields = ClassInspector.getAllFields(clazz);
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()))
                continue;

            field.setAccessible(true);
            try {
                Object fieldValue = field.get(obj);
                if (hasAuthorizedObject(fieldValue, visited))
                    return true;
            } catch (IllegalAccessException ignored) {
            }
        }

        return false;
    }

    @PostConstruct
    public void logAllObjectMappers() {
        AtomicBoolean checkDefaultObjectMapper = new AtomicBoolean(false);

        List<String> oms = new ArrayList<>();
        this.allObjectMappers.forEach((name, om) -> {
            oms.add(String.join("->", name, om.getClass().toString()));
            if (DEFAULT_JACKSON_OBJECT_MAPPER.equals(name)) {
                checkDefaultObjectMapper.set(true);
            }
            logger.trace("ObjectMapper 등록됨: {} -> {}", name, om.getClass().getName());
        });
    }

    /**
     * {@link AbstractJackson2HttpMessageConverter}가 {@link ObjectMapper}를 선택하는 메커니즘을 최대한 따르기 위해서
     * <code>overriding</code>한 메소드 입니다.<br>
     * 메소드 내부 코드가 {@link AbstractJackson2HttpMessageConverter#registerObjectMappersForType(Class, Consumer)}와 동일한데 별도로
     * <code>overriding</code>이유는
     * {@link AbstractJackson2HttpMessageConverter#registerObjectMappersForType(Class, Consumer)} 메소드가 내부에서 private 필드를
     * 사용했기 때문입니다.<br>
     * 이에 동일한 필드({@link #objectMapperRegistrations})를 정의하여 <code>overriding</code> 하였습니다.
     *
     * @since 2025. 5. 27.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter#registerObjectMappersForType(java.lang.Class,
     *      java.util.function.Consumer)
     */
    public void registerObjectMappersForType(Class<?> clazz, Consumer<Map<MediaType, ObjectMapper>> registrar) {
        if (this.objectMapperRegistrations == null) {
            this.objectMapperRegistrations = new LinkedHashMap<>();
        }
        Map<MediaType, ObjectMapper> registrations = this.objectMapperRegistrations.computeIfAbsent(clazz, c -> new LinkedHashMap<>());
        registrar.accept(registrations);
    }

    /**
     * {@link AbstractJackson2HttpMessageConverter#writeInternal(Object, Type, HttpOutputMessage)} 를 로직을 그대로 차용하기 위해서
     * 내부적으로 사용되는 private ObjectMapper selectObjectMapper(Class, MediaType) 메소드에 대한 Wrapper 메소드입니다.<br>
     * 이를 통해서 private ObjectMapper AbstractJackson2HttpMessageConverter.selectObjectMapper(Class, MediaType) 메소드를 그대로
     * {@link #selectObjectMapper(Class, MediaType)}로 이관하였고 이를 통해서 {@link AbstractJackson2HttpMessageConverter}에서
     * {@link ObjectMapper}를 선택하는 메커니즘을 최대한 따르게 되었습니다.
     * 
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param object
     * @param targetType
     * @param targetMediaType
     * @return
     *
     * @since 2025. 5. 27.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private ObjectMapper resolveMapper(Object object, Class<?> targetType, @Nullable MediaType targetMediaType) {

        ObjectMapper om = null;
        if (object == null) {
            om = selectObjectMapper(targetType, targetMediaType);
            return om != null ? om : getObjectMapper();
        }

        // unwrap ResponseEntity
        if (object instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) object;
            object = responseEntity.getBody();
        }

        Set<Object> visited = new HashSet<Object>();
        try {
            if (hasAuthorizedObject(object, visited)) {
                return allObjectMappers.get(AuthorizedResourcesConfiguration.BEAN_QUALIFIER_AUTHORIZED_OBJECT_MAPPER);
            } else {
                om = selectObjectMapper(targetType, targetMediaType);
                return om != null ? om : getObjectMapper();
            }
        } finally {
            visited.clear();
        }
    }

    /**
     * {@link AbstractJackson2HttpMessageConverter}가 {@link ObjectMapper}를 선택하는 메커니즘을 최대한 따르기 위해서
     * {@link AbstractJackson2HttpMessageConverter}의
     * <code>private Object selectObjectMapper(Class<?>, MediaType)</code>를 이관한 메소드입니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 5. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param targetType
     * @param targetMediaType
     * @return
     *
     * @since 2025. 5. 27.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #registerObjectMappersForType(Class, Consumer)
     */
    @Nullable
    private ObjectMapper selectObjectMapper(Class<?> targetType, @Nullable MediaType targetMediaType) {
        if (targetMediaType == null || CollectionUtils.isEmpty(this.objectMapperRegistrations)) {
            return this.defaultObjectMapper;
        }
        for (Map.Entry<Class<?>, Map<MediaType, ObjectMapper>> typeEntry : getObjectMapperRegistrations().entrySet()) {
            if (typeEntry.getKey().isAssignableFrom(targetType)) {
                for (Map.Entry<MediaType, ObjectMapper> objectMapperEntry : typeEntry.getValue().entrySet()) {
                    if (objectMapperEntry.getKey().includes(targetMediaType)) {
                        return objectMapperEntry.getValue();
                    }
                }
                // No matching registrations
                return null;
            }
        }

        // No registrations
        return this.defaultObjectMapper;
    }

    /**
     * {@link AbstractJackson2HttpMessageConverter}가 {@link ObjectMapper}를 선택하는 메커니즘을 최대한 따르기 위해서
     * {@link AbstractJackson2HttpMessageConverter#canWrite(Type, Class, MediaType)} 코드를 거의 그대로 차용한 메소드입니다ㅏ.<br>
     * 다른 부분은 {@link ObjectMapper}를 선택하는 부분입니다.<br>
     * <ul>
     * <li>{@link AbstractJackson2HttpMessageConverter#writeInternal(Object, Type, HttpOutputMessage)}:
     * selectObject(Class, MediaType)
     * <li>{@link AuthorizedObjectJackson2HttpMessageConverter#write(Object, MediaType, HttpOutputMessage)}:
     * {@link #resolveMapper(Object, Class, MediaType)}
     * </ul>
     * 
     * @since 2025. 5. 26.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter#writeInternal(java.lang.Object,
     *      java.lang.reflect.Type, org.springframework.http.HttpOutputMessage)
     */
    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        MediaType contentType = outputMessage.getHeaders().getContentType();
        JsonEncoding encoding = getJsonEncoding(contentType);

        Class<?> clazz = object instanceof MappingJacksonValue //
                ? ((MappingJacksonValue) object).getValue().getClass() //
                : object.getClass();

        ObjectMapper objectMapper = resolveMapper(object, clazz, contentType);
        Assert.state(objectMapper != null, () -> "No ObjectMapper for " + clazz.getName());

        try ( //
                ByteArrayOutputStream serializeBuffer = new ByteArrayOutputStream();
                JsonGenerator generator = objectMapper.getFactory().createGenerator(serializeBuffer, encoding);
                OutputStream outputStream = StreamUtils.nonClosing(outputMessage.getBody()); //
        ) {
            writePrefix(generator, object);

            Object value = object;
            Class<?> serializationView = null;
            FilterProvider filters = null;
            JavaType javaType = null;

            if (object instanceof MappingJacksonValue) {
                MappingJacksonValue container = (MappingJacksonValue) object;
                value = container.getValue();
                serializationView = container.getSerializationView();
                filters = container.getFilters();
            }
            if (type != null && TypeUtils.isAssignable(type, value.getClass())) {
                javaType = getJavaType(type, null);
            }

            ObjectWriter objectWriter = (serializationView != null ? objectMapper.writerWithView(serializationView) : objectMapper.writer());
            if (filters != null) {
                objectWriter = objectWriter.with(filters);
            }
            if (javaType != null && javaType.isContainerType()) {
                objectWriter = objectWriter.forType(javaType);
            }
            SerializationConfig config = objectWriter.getConfig();
            if (contentType != null && contentType.isCompatibleWith(MediaType.TEXT_EVENT_STREAM) && config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
                objectWriter = objectWriter.with(this.ssePrettyPrinter);
            }

            // #1. serialize to buffer
            objectWriter.writeValue(generator, value);
            writeSuffix(generator, object);
            generator.flush();
            // #2. copy buffer to outputMessage
            outputStream.write(serializeBuffer.toByteArray());

        } catch (InvalidDefinitionException ex) {
            throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
        } catch (Exception ex) {
            throw new HttpMessageNotWritableException("Could not write JSON", ex);
        }
    }
}
