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
 * Date  : 2025. 9. 18. 오후 2:27:13
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import open.commons.spring.web.authority.AuthorizedData;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesConfiguration;

/**
 * "{@link AuthorizedData} && ({@link ModelAttribute} || {@link ModelAttributeMethodProcessor#annotationNotRequired} )"가
 * 선언된 파라미터를 처리합니다.<br>
 * {@link AuthorizedResourcesConfiguration}을 통해서 {@link Bean}으로 제공됩니다.
 * 
 * @since 2025. 9. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedDataModelAttributeResolver extends ModelAttributeMethodProcessor implements IAuthorizedDataResolver {

    private final AuthorizedDataModelAttributeProcessor processor;

    public AuthorizedDataModelAttributeResolver(ApplicationContext applicationContext) {
        super(false);
        this.processor = new AuthorizedDataModelAttributeProcessor(applicationContext);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        if (binder instanceof ExtendedServletRequestDataBinder) {
            ((ExtendedServletRequestDataBinder) binder).bind(servletRequest);
        } else if (binder instanceof ServletRequestDataBinder) {
            ((ServletRequestDataBinder) binder).bind(servletRequest);
        } else if (binder instanceof WebRequestDataBinder) {
            ((WebRequestDataBinder) binder).bind(request);
        } else {
            throw new IllegalStateException("Unsupported WebDataBinder: " + binder.getClass());
        }

        Object target = binder.getTarget();
        if (target != null) {
            processor.process(target);
        }
    }

    /**
     * "{@link AuthorizedData} && {@link ModelAttribute}"가 선언된 파라미터만 지원합니다.
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.method.annotation.ModelAttributeMethodProcessor#supportsParameter(org.springframework.core.MethodParameter)
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return super.supportsParameter(parameter);
    }

}
