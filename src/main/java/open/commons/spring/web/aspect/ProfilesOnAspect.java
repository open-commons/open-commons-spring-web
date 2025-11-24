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
 * Date  : 2025. 11. 21. 오후 3:24:09
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import open.commons.core.utils.ObjectUtils;
import open.commons.spring.web.context.annotation.ProfilesOn;
import open.commons.spring.web.context.annotation.ProfilesOn.DecisionRule;
import open.commons.spring.web.exception.ProfileOnDeniedException;

/**
 * 
 * @since 2025. 11. 21.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
@Aspect
@Order(AspectOrder.PROFILES_ON)
@Component
public class ProfilesOnAspect extends AbstractAspectPointcuts {

    private final String[] currentProfiles;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param context
     *
     * @since 2025. 11. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public ProfilesOnAspect(ApplicationContext context) {
        super(context);
        this.currentProfiles = ObjectUtils.getOrDefault(this.context.getEnvironment().getActiveProfiles(), () -> this.context.getEnvironment().getDefaultProfiles());

    }

    /**
     * {@link ProfilesOn} 어노테이션이 설정된 메소드. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 11. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    @Pointcut("@annotation(open.commons.spring.web.context.annotation.ProfilesOn)")
    public final void annotationProfilesOn() {
    }

    /**
     * 예외 클래스를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param profilesOn
     * @param method
     * @return
     *
     * @since 2025. 11. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private ProfileOnDeniedException createProfileOnDeniedException(ProfilesOn profilesOn, Method method) {
        throw new ProfileOnDeniedException(profilesOn, Arrays.asList(getProfilesAndMore()), method);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param standards
     * @param rule
     *            {@link Environment#getActiveProfiles()}(+추가설정)과 {@link ProfilesOn#standards()} 값을 비교하는 규칙
     * @return
     *
     * @since 2025. 11. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private boolean decideByRule(@Nonnull String[] standards, @Nonnull DecisionRule rule) {
        Set<String> filteredStandards = Stream.of(standards).filter(std -> std != null && !std.trim().isEmpty()).collect(Collectors.toSet());
        // 조건에 부합하는 'standard'가 없는 경우 모두 '매칭 성공' 처리.
        if (filteredStandards.isEmpty()) {
            return true;
        }
        // 현재 profile 정보
        String[] profiles = getProfilesAndMore();
        // 헬퍼 메소드에서 Optional<String> 결과를 받을 변수
        Optional<String> found = null;
        switch (rule) {
            case EQ:
                // [EQ] 동일한 요소 찾기 (HashSet으로 O(N_a + N_b) 최적화)
                found = Arrays.stream(profiles) //
                        .filter(filteredStandards::contains) //
                        .findFirst();
                break;
            case BEGIN:
                // [BEGIN] profiles가 standards의 요소로 시작하는지 확인 (anyMatch 단락 활용)
                found = Arrays.stream(profiles) //
                        .filter(profile -> filteredStandards.stream().anyMatch(profile::startsWith)) // profile.startsWith(standard)
                        .findFirst();
                break;
            case END:
                // [END] profiles가 standards의 요소로 끝나는지 확인 (anyMatch 단락 활용)
                found = Arrays.stream(profiles) //
                        .filter(profile -> filteredStandards.stream().anyMatch(profile::endsWith)) // profile.endsWith(standard)
                        .findFirst();
                break;
            case REGEX:
                // [REGEX] profiles가 standards의 정규식 패턴에 매칭되는지 확인 (패턴 사전 컴파일)
                final List<Pattern> patterns = filteredStandards.stream() //
                        .map(Pattern::compile) //
                        .collect(Collectors.toList());
                found = Arrays.stream(profiles) //
                        .filter(profile -> patterns.stream().anyMatch(pattern -> pattern.matcher(profile).find())) //
                        .findFirst();
                break;
            default:
                throw new UnsupportedOperationException("Unsupported DecisionRule: " + rule);
        }

        // found가 값을 가지고 있으면 (즉, 매칭되는 요소를 찾았으면) true 반환
        return found.isPresent();
    }

    private boolean decideExecution(ProfilesOn profilesOn) {
        boolean matched = decideByRule(profilesOn.standards(), profilesOn.rule());
        switch (profilesOn.strategy()) {
            case ALLOW:
                return matched;
            case DENY:
                return !matched;
            default:
                throw new UnsupportedOperationException("Unsupported Strategy: " + profilesOn.strategy());
        }
    }

    /**
     * 현재 클래스의 필드 currentProfiles를 가져오고 null이면 빈 배열로 대체 <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 24.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 11. 24.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private String[] getProfilesAndMore() {
        return ObjectUtils.getOrDefault(this.currentProfiles, new String[] {});
    }

    /**
     * {@link ProfilesOn} 설정값에 따라서 실행여부를 처리합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 11. 21.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param pjp
     * @return
     * @throws Throwable
     *
     * @since 2025. 11. 21.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see #withinAllControllerStereotypeComponent()
     * @see #annotationAllRequestMapping()
     * @see #annotationProfilesOn()
     */
    @Around("withinAllControllerStereotypeComponent()" // .
            + " && annotationAllRequestMapping()" //
            + " && annotationProfilesOn()"//
    )
    public Object handleMethodOnProfile(ProceedingJoinPoint pjp) throws Throwable {
        // 메소드
        Method invodedMethod = ((MethodSignature) pjp.getSignature()).getMethod();
        // 어노테이션
        ProfilesOn profilesOn = AnnotationUtils.findAnnotation(invodedMethod, ProfilesOn.class);
        if (decideExecution(profilesOn)) {
            return pjp.proceed();
        } else {
            throw createProfileOnDeniedException(profilesOn, invodedMethod);
        }
    }

    @PostConstruct
    void info() {
        logger.info("current.profiles={}", Arrays.toString(this.currentProfiles));
    }
}
