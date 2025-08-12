/*
 * Copyright 2019 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * This file is generated under this project, "open-commons-spring5".
 *
 * Date  : 2019. 6. 3. 오후 5:27:38
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.enums;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * {@link Enum} 변환기를 등록/제공하는 클래스.
 * 
 * @since 2019. 6. 3.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
@SuppressWarnings("rawtypes")
public class EnumConverterFactory implements ConverterFactory<String, Enum> {

    private Map<Class<?>, Converter<String, Enum>> converters = new HashMap<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2019. 6. 3.		박준홍			최초 작성
     * </pre>
     *
     * @since 2019. 6. 3.
     * @version
     */
    public EnumConverterFactory() {
    }

    /**
     * @see org.springframework.core.convert.converter.ConverterFactory#getConverter(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return (Converter<String, T>) this.converters.get(targetType);
    }

    public void register(Class<?> enumType, Converter<String, Enum> converter) {
        this.converters.put(enumType, converter);
    }
}
