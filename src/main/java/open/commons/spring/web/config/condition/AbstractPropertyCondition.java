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
 * Date  : 2025. 8. 8. 오후 3:12:38
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.config.condition;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 외부 설정에 해당하는 객체가 유효한지를 판단하는 클래스.
 * 
 * @since 2025. 8. 8.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractPropertyCondition<T> extends SpringBootCondition {

    /** 설정 경로 */
    protected final String prefix;
    /** 객체 제공자 */
    protected final Bindable<T> bindable;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @param prefix
     *            설정 경로
     * @since 2025. 8. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractPropertyCondition(@NotBlank @Nonnull String prefix) {
        this.prefix = prefix;
        this.bindable = bindable();
    }

    /**
     * 객체 제공자를 생성합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 8.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 8.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @Nonnull
    protected abstract Bindable<T> bindable();

    /**
     *
     * @since 2025. 8. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.boot.autoconfigure.condition.SpringBootCondition#getMatchOutcome(org.springframework.context.annotation.ConditionContext,
     *      org.springframework.core.type.AnnotatedTypeMetadata)
     */
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Environment env = context.getEnvironment();
        Binder binder = Binder.get(env);

        if (validate(binder.bind(this.prefix, this.bindable).orElse(null))) {
            return ConditionOutcome.match(messageOnMatch());
        } else {
            return ConditionOutcome.noMatch(messageOnMatch());
        }
    }

    /**
     * 응용프로그램 설정에 {@link #prefix}에 해당하는 값이 존재하여 유효한 객체가 생성되었을 경우에 제공하는 메시지. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 8.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 8.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */

    protected String messageOnMatch() {
        return "matched";
    }

    /**
     * 응용프로그램 설정에 {@link #prefix}에 해당하는 값이 존재하지 않거나 객체가 유효하지 않은 경우에 제공하는 메시지. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 8.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 8. 8.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected String messageOnNomatch() {
        return "nomatched";
    }

    /**
     * {@link #getMatchOutcome(ConditionContext, AnnotatedTypeMetadata)}를 통해서 생성한 객체를 검증하여 최종 결과를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 8. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param bound
     * @return
     *
     * @since 2025. 8. 8.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected abstract boolean validate(T bound);

}
