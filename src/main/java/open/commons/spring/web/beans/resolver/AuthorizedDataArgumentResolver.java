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
 * Date  : 2025. 9. 18. 오후 12:52:18
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.resolver;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.authority.AuthorizedRequestData;
import open.commons.spring.web.autoconfigure.configuration.AuthorizedResourcesConfiguration;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.servlet.InternalServerException;

/**
 * "{@link AuthorizedRequestData} && ({@link PathVariable} || {@link RequestParam})"가 선언된 파라미터를 처리합니다.<br>
 * {@link AuthorizedResourcesConfiguration}을 통해서 {@link Bean}으로 제공됩니다.
 * 
 * @since 2025. 9. 18.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedDataArgumentResolver implements IAuthorizedDataResolver {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @NotNull
    private final ApplicationContext context;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 18.		박준홍			최초 작성
     * </pre>
     * 
     * @param context
     *
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedDataArgumentResolver(@NotNull @Nonnull ApplicationContext context) {
        this.context = context;
    }

    /**
     *
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter,
     *      org.springframework.web.method.support.ModelAndViewContainer,
     *      org.springframework.web.context.request.NativeWebRequest,
     *      org.springframework.web.bind.support.WebDataBinderFactory)
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        // #1. 파라미터 이름
        boolean isPathVariable = false;
        String parameterName = null;
        if (parameter.hasParameterAnnotation(PathVariable.class)) {
            parameterName = parameter.getParameterAnnotation(PathVariable.class).value();
            isPathVariable = true;
        } else if (parameter.hasParameterAnnotation(RequestParam.class)) {
            parameterName = parameter.getParameterAnnotation(RequestParam.class).value();
        }

        if (StringUtils.isNullOrEmptyString(parameterName)) {
            parameterName = parameter.getParameterName();
        }

        // #2. 파라미터 값
        String rawValue = null;
        if (isPathVariable) {
            @SuppressWarnings("unchecked")
            Map<String, String> uriVars = (Map<String, String>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
            if (uriVars != null) {
                rawValue = uriVars.get(parameterName);
            }
        } else {
            rawValue = webRequest.getParameter(parameterName);
        }

        // #3. 데이터 원복
        AuthorizedRequestData anno = parameter.getParameterAnnotation(AuthorizedRequestData.class);
        try {
            return restoreValue(context, anno, rawValue);
        } catch (BeansException e) {
            String errMsg = String
                    .format("'권한 제어가 적용된 파라미터'를 처리하는 도중 오류가 발생하였습니다. parameter.name=%s, parameter.raw_value=%s, handle.beanname=%s, handle.type=%s, handle.class=%s, 원인=%s" //
                            , parameterName, rawValue, anno.handleBean(), anno.handleType(), IAuthorizedRequestDataHandler.class.getName(), e.getMessage());
            logger.error("{}", errMsg, e);

            throw ExceptionUtils.newException(InternalServerException.class, e, errMsg);
        }

    }

    /**
     * "{@link AuthorizedRequestData} && ({@link PathVariable} || {@link RequestParam})"가 선언된 파라미터만 지원합니다.
     * 
     * @since 2025. 9. 18.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthorizedRequestData.class) //
                && (parameter.hasParameterAnnotation(PathVariable.class) //
                        || parameter.hasParameterAnnotation(RequestParam.class));
    }

}
