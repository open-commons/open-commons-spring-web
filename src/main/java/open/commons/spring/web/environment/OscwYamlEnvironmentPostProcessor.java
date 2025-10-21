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
 * Date  : 2025. 10. 21. 오전 11:41:46
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.environment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 라이브러리 내에서 정의한 설정을 이를 사용하는 '서비스 설정'에 추가하는 클래스.<br>
 * 
 * <p>
 * <a href="https://github.com/orgs/open-commons/repositories">'Open-Commons'</a>를 구성하는 패키지의 설정파일을 추가합니다.
 * <li>open-commons-core
 * <li>open-commons-ssh
 * <li>open-commons-spring-csv
 * <li>open-commons-spring-elastic
 * <li>open-commons-spring-jdbc
 * <li>open-commons-spring-oshi
 * <li>open-commons-spring-redis
 * <li>open-commons-spring-web
 * </p>
 * <br>
 * <p>
 * spring.factories에 등록 (라이브러리 JAR 내부)에 등록되어 있습니다.
 * </p>
 * 
 * <pre>
 * META-INF/spring.factories
 * org.springframework.boot.env.EnvironmentPostProcessor=\
 *   open.commons.spring.web.oas.OcswYamlEnvironmentPostProcessor
 * </pre>
 * 
 * 
 * @since 2025. 10. 21.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class OscwYamlEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final Map<String, String> RESOURCES_PATH = new HashMap<>();
    static {
        RESOURCES_PATH.put("oc-core-yaml", "META-INF/open-commons-core.yml");
        RESOURCES_PATH.put("oc-ssh-yaml", "META-INF/open-commons-ssh.yml");
        RESOURCES_PATH.put("ocs-csv-yaml", "META-INF/open-commons-spring-csv.yml");
        RESOURCES_PATH.put("ocs-elastic-yaml", "META-INF/open-commons-spring-elastic.yml");
        RESOURCES_PATH.put("ocs-jdbc-yaml", "META-INF/open-commons-spring-jdbc.yml");
        RESOURCES_PATH.put("ocs-oshi-yaml", "META-INF/open-commons-spring-oshi.yml");
        RESOURCES_PATH.put("ocs-redis-yaml", "META-INF/open-commons-spring-redis.yml");
        RESOURCES_PATH.put("ocs-web-yaml", "META-INF/open-commons-spring-web.yml");
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 10. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public OscwYamlEnvironmentPostProcessor() {
    }

    /**
     *
     * @since 2025. 10. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    /**
     *
     * @since 2025. 10. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     *
     * @see org.springframework.boot.env.EnvironmentPostProcessor#postProcessEnvironment(org.springframework.core.env.ConfigurableEnvironment,
     *      org.springframework.boot.SpringApplication)
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        for (Entry<String, String> entry : RESOURCES_PATH.entrySet()) {
            Resource resource = new ClassPathResource(entry.getValue());
            if (!resource.exists()) {
                this.logger.info("'{}' 설정 파일이 존재하지 않습니다.", entry.getValue());
                continue;
            }
            try {
                YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
                // 파일 안에 여러 문서(---)가 있으면 여러 PropertySource가 생성될 수 있음
                List<PropertySource<?>> sources = loader.load(entry.getKey(), resource);
                // 라이브러리 기본값 → 가장 낮은 우선순위로 등록하여 서비스 설정이 덮어쓰게 함
                for (PropertySource<?> ps : sources) {
                    if (environment.getPropertySources().contains(ps.getName())) {
                        // 중복 방지: 이미 같은 이름이 있으면 스킵
                        continue;
                    }
                    environment.getPropertySources().addLast(ps);
                }

                this.logger.info("'{}' 설정 파일을 정상적으로 로딩하였습니다.", entry.getValue());
            } catch (IOException e) {
                // 로깅만 하고 넘어감(라이브러리 기본값이 꼭 필요하지 않다면)
                // 적절한 로거 사용 권장
                this.logger.warn("'{}' 설정 파일을 읽는 도중 오류가 발생하였습니다.", entry.getValue(), e);
            }
        }
    }
}
