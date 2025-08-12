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
 * Date  : 2025. 6. 5. 오후 1:04:50
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.autoconfigure;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

/**
 * 
 * @since 2025. 6. 5.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class OpenCommonsSpringWebAutoConfigurationImportSelector extends AutoConfigurationImportSelector {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 6. 5.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 6. 5.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public OpenCommonsSpringWebAutoConfigurationImportSelector() {
    }

    /**
     *
     * @since 2025. 6. 5.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#getAnnotationClass()
     */
    @Override
    protected Class<?> getAnnotationClass() {
        return EnableOpenCommonsSpringWeb.class;
    }

    /**
     *
     * @since 2025. 6. 5.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#getCandidateConfigurations(org.springframework.core.type.AnnotationMetadata,
     *      org.springframework.core.annotation.AnnotationAttributes)
     */
    @Override
    protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
        List<String> configurations = new ArrayList<>(SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(), getBeanClassLoader()));
        Assert.notEmpty(configurations,
                "No auto configuration classes found in META-INF/spring.factories nor in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports. If you "
                        + "are using a custom packaging, make sure that file is correct.");
        return configurations;
    }

    /**
     *
     * @since 2025. 6. 9.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#getExclusions(org.springframework.core.type.AnnotationMetadata,
     *      org.springframework.core.annotation.AnnotationAttributes)
     */
    @Override
    protected Set<String> getExclusions(AnnotationMetadata metadata, AnnotationAttributes attributes) {
        Set<String> excluded = new LinkedHashSet<>();
        excluded.addAll(asList(attributes, "exclude"));
        return excluded;
    }

    /**
     *
     * @since 2025. 6. 5.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#getSpringFactoriesLoaderFactoryClass()
     */
    @Override
    protected Class<?> getSpringFactoriesLoaderFactoryClass() {
        return EnableOpenCommonsSpringWeb.class;
    }
}
