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
 * Date  : 2025. 11. 24. 오전 11:30:14
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.exception;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import open.commons.core.utils.ObjectUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.context.annotation.ProfilesOn;
import open.commons.spring.web.context.annotation.ProfilesOn.DecisionRule;
import open.commons.spring.web.context.annotation.ProfilesOn.Strategy;

/**
 * {@link ProfilesOn}에서 허용하지 않은 경우 발생하는 예외 클래스.
 * 
 * @since 2025. 11. 24.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ProfileOnDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ProfilesOn profilesOn;
    private final List<String> activeProfiles;
    private final String methodSignature;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param profilesOn
     * @param activeProfiles
     * @param method
     * 
     * @since 2025. 11. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public ProfileOnDeniedException(ProfilesOn profilesOn, Collection<String> activeProfiles, Method method) {
        super(buildMessage(profilesOn, activeProfiles, method));
        this.profilesOn = profilesOn;
        this.activeProfiles = activeProfiles != null ? activeProfiles.stream().collect(Collectors.toList()) : Collections.emptyList();
        this.methodSignature = method != null ? method.toGenericString() : "";
    }

    public List<String> getActiveProfiles() {
        return Collections.unmodifiableList(activeProfiles);
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public DecisionRule getRule() {
        return profilesOn.rule();
    }

    public List<String> getStandards() {
        return Collections.unmodifiableList(Arrays.asList(profilesOn.standards()));
    }

    public Strategy getStrategy() {
        return profilesOn.strategy();
    }

    private static String buildMessage(ProfilesOn profilesOn, Collection<String> activeProfiles, Method method) {

        String methodSig = method != null ? method.toGenericString() : "<unknown>";
        String standards = ObjectUtils.getOrDefault(profilesOn.standards(), _std -> StringUtils.concatenate(", ", _std), "");
        String profiles = activeProfiles != null ? String.join(", ", activeProfiles) : "";

        return new StringBuilder() //
                .append("ProfileOn denied method execution.") //
                .append(" strategy=").append(profilesOn.strategy()) //
                .append(", rule=").append(profilesOn.rule()) //
                .append(", standards=[").append(standards).append("]") //
                .append(", activeProfiles=[").append(profiles).append("]") //
                .append(", method=").append(methodSig).toString();
    }

}
