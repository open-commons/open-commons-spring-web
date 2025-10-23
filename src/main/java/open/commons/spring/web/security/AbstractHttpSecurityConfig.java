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
 * Date  : 2025. 10. 23. 오후 12:59:04
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.security;

import java.lang.reflect.Method;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity.RequestMatcherConfigurer;
import org.springframework.security.config.annotation.web.configurers.AnonymousConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.configurers.ChannelSecurityConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.JeeConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.PasswordManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.PortMapperConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.config.annotation.web.configurers.ServletApiConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.X509Configurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2ClientConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.configurers.saml2.Saml2LoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.saml2.Saml2LogoutConfigurer;
import org.springframework.util.ClassUtils;

import open.commons.core.function.ThrowableFunction;
import open.commons.core.utils.ExceptionUtils;

/**
 * 
 * @since 2025. 10. 23.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public abstract class AbstractHttpSecurityConfig {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /** @see #httpBasic(HttpBasicConfigurer) */
    protected boolean enableHttpBasic;
    /** @see #formLogin(FormLoginConfigurer) */
    protected boolean enableFormLogin;
    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우
     * "org.springframework.security.oauth2.client.registration.ClientRegistrationRepository" 등을 구현한 {@link Bean}이 필요할 수
     * 있습니다.
     * 
     * @see #oauth2Login(OAuth2LoginConfigurer)
     */
    protected boolean enableOauth2Login;
    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 "org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager" 등을
     * 구현한 {@link Bean}이 필요할 수 있습니다.
     * 
     * @see #oauth2Client(OAuth2ClientConfigurer)
     */
    protected boolean enableOauth2Client;
    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 기능에 필요한 {@link Bean}을 확인하기 바랍니다.
     * 
     * @see #oauth2ResourceServer(OAuth2ResourceServerConfigurer)
     */
    protected boolean enableOauth2ResourceServer;
    /** @see #x509(X509Configurer) */
    protected boolean enableX509;
    /** @see #jee(JeeConfigurer) */
    protected boolean enableJee;
    /**
     * <code>true</code>인 경우 {@link #authorizeHttpRequests(AuthorizationManagerRequestMatcherRegistry)} 를 사용하고,
     * <code>false</code>인 경우 {@link #authorizeRequests(ExpressionInterceptUrlRegistry)} 를 사용합니다.
     * 
     * @see #authorizeHttpRequests(AuthorizationManagerRequestMatcherRegistry)
     * @see #authorizeRequests(ExpressionInterceptUrlRegistry)
     */
    protected boolean enableAuthorizeHttpRequests = true;

    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 기능에 필요한 {@link Bean}을 확인하기 바랍니다.
     * 
     * @see #passwordManagement(PasswordManagementConfigurer)
     */
    protected boolean enablePasswordManagement;

    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 기능에 필요한 {@link Bean}을 확인하기 바랍니다.
     * 
     * @see #portMapper(PortMapperConfigurer)
     */
    protected boolean enablePortMapper;

    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 기능에 필요한 {@link Bean}을 확인하기 바랍니다.
     * 
     * @see #rememberMe(RememberMeConfigurer)
     */
    protected boolean enableRememberMe;
    /**
     * 이 옵션을 <code>true</code>로 설정하는 경우 기능에 필요한 {@link Bean}을 확인하기 바랍니다.
     * 
     * @see #saml2Login(Saml2LoginConfigurer)
     * @see #saml2Logout(Saml2LogoutConfigurer)
     */
    protected boolean enableSaml2;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public AbstractHttpSecurityConfig() {
    }

    /**
     * {@link HttpSecurity#anonymous(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#anonymous(org.springframework.security.config.Customizer)
     */
    protected void anonymous(AnonymousConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity}에서 {@link AuthenticationManager}, {@link AuthenticationProvider}를 설정합니다.<br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param http
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#authenticationManager(org.springframework.security.authentication.AuthenticationManager)
     * @see HttpSecurity#authenticationProvider(org.springframework.security.authentication.AuthenticationProvider)
     */
    protected void authenticationProviders(HttpSecurity http) {
    }

    /**
     * {@link HttpSecurity#authorizeHttpRequests(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#authorizeHttpRequests(org.springframework.security.config.Customizer)
     */
    protected void authorizeHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configurer) {
    }

    /**
     * {@link HttpSecurity#authorizedRequests(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableAuthorizeHttpRequests} 값을 <code>false</code>로
     * 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#authorizedRequests(org.springframework.security.config.Customizer)
     */
    protected void authorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configurer) {
    }

    protected final void configure(HttpSecurity http) throws Exception {

        // #1. (선택) 체인 범위/요청 매처 — 필요 시 사용
        http.requestMatchers(this::requestMatchers);

        // #2. 인증 메커니즘
        if (this.enableHttpBasic) {
            executeIfOverride(this::httpBasic, http::httpBasic, "httpBasic", HttpBasicConfigurer.class);
        }
        if (this.enableFormLogin) {
            executeIfOverride(this::formLogin, http::formLogin, "formLogin", FormLoginConfigurer.class);
        }
        if (this.enableOauth2Login) {
            executeIfOverride(this::oauth2Login, http::oauth2Login, "oauth2Login", OAuth2LoginConfigurer.class);
        }
        if (this.enableOauth2Login) {
            executeIfOverride(this::oauth2Client, http::oauth2Client, "oauth2Client", OAuth2ClientConfigurer.class);
        }
        if (this.enableOauth2Login) {
            executeIfOverride(this::oauth2ResourceServer, http::oauth2ResourceServer, "oauth2ResourceServer", OAuth2ResourceServerConfigurer.class);
        }
        if (this.enableX509) {
            executeIfOverride(this::x509, http::x509, "x509", X509Configurer.class);
        }
        if (this.enableJee) {
            executeIfOverride(this::jee, http::jee, "jee", JeeConfigurer.class);
        }
        http.logout(this::logout);

        // #3. 예외처리 (전역 EntryPoint/DeniedHandler 통일)
        http.exceptionHandling(this::exceptionHandling);

        // #4. 권한 규칙 (좁은 규칙 -> 넓은 규칙)
        if (enableAuthorizeHttpRequests) {
            executeIfOverride(this::authorizeHttpRequests, http::authorizeHttpRequests, "authorizeHttpRequests", AuthorizationManagerRequestMatcherRegistry.class);
        } else {
            executeIfOverride(this::authorizeRequests, http::authorizeRequests, "authorizeRequests", ExpressionInterceptUrlRegistry.class);
        }

        // #5. AuthentationProvider, AuthenticationManager 'Hook'
        this.authenticationProviders(http);

        // #6. 필터 'Hook'
        this.filters(http);

        // #7. 익명 사용자 처리
        http.anonymous(this::anonymous);

        // #7. 이하
        http.sessionManagement(this::sessionManagement);
        http.headers(this::headers);
        http.csrf(this::csrf);
        http.cors(this::cors);
        http.servletApi(this::servletApi);
        if (enablePasswordManagement) {
            executeIfOverride(this::passwordManagement, http::passwordManagement, "passwordManagement", PasswordManagementConfigurer.class);
        }
        http.requestCache(this::requestCache);
        if (enablePortMapper) {
            executeIfOverride(this::portMapper, http::portMapper, "portMapper", PortMapperConfigurer.class);
        }
        if (enableRememberMe) {
            executeIfOverride(this::rememberMe, http::rememberMe, "rememberMe", RememberMeConfigurer.class);
        }
        http.securityContext(this::securityContext);
        http.requiresChannel(this::requiresChannel);
        if (enableSaml2) {
            executeIfOverride(this::saml2Login, http::saml2Login, "saml2Login", Saml2LoginConfigurer.class);
            executeIfOverride(this::saml2Logout, http::saml2Logout, "saml2Logout", Saml2LogoutConfigurer.class);
        }
    }

    /**
     * {@link HttpSecurity#cors(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#cors(org.springframework.security.config.Customizer)
     */
    protected void cors(CorsConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#csrf(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#csrf(org.springframework.security.config.Customizer)
     */
    protected void csrf(CsrfConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#exceptionHandling(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#exceptionHandling(org.springframework.security.config.Customizer)
     */
    protected void exceptionHandling(ExceptionHandlingConfigurer<HttpSecurity> configurer) {
    }

    // private final <T> void executeIfOverride(Customizer<T> configurer, ThrowableFunction<Customizer<T>, HttpSecurity>
    // applier) throws Exception {
    private final <T> void executeIfOverride(Customizer<T> configurer, ThrowableFunction<Customizer<T>, HttpSecurity> applier, String methodName, Class<?>... argTypes)
            throws Exception {
        if (isOverrided(methodName, argTypes)) {
            try {
                applier.apply(configurer);
            } catch (Throwable e) {
                throw new Exception("", e);
            }
        } else {
            logger.warn("'{}' 옵션을 활성화(true) 시켰으나, '{}' 메소드를 'overriding' 하지 않았습니다.", methodName, getMethod("httpBasic", HttpBasicConfigurer.class));
        }
    }

    /**
     * {@link HttpSecurity}에서 {@link Filter}를 설정합니다.<br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 10. 23.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#addFilter(javax.servlet.Filter)
     * @see HttpSecurity#addFilterAfter(javax.servlet.Filter, Class)
     * @see HttpSecurity#addFilterAt(javax.servlet.Filter, Class)
     * @see HttpSecurity#addFilterBefore(javax.servlet.Filter, Class)
     */
    protected void filters(HttpSecurity http) {
    }

    /**
     * {@link HttpSecurity#formLogin(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableFormLogin} 값을 <code>true</code>로 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#formLogin(org.springframework.security.config.Customizer)
     */
    protected void formLogin(FormLoginConfigurer<HttpSecurity> configurer) {
    }

    /**
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param methodName
     *            메소드 이름
     * @param argTypes
     *            메소드 파라미터 유형
     * @return
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private final Method getMethod(String methodName, Class<?>... argTypes) {
        Class<?> userClass = ClassUtils.getUserClass(this);
        try {
            return userClass.getDeclaredMethod(methodName, argTypes);
        } catch (NoSuchMethodException e) {
            throw ExceptionUtils.newException(RuntimeException.class, "'%s'클래스에 '%s' 메소드가 존재하지 않습니다.", userClass.getName(), methodName, e);
        }
    }

    /**
     * {@link HttpSecurity#headers(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#headers(org.springframework.security.config.Customizer)
     */
    protected void headers(HeadersConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#httpBasic(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableHttpBasic} 값을 <code>true</code>로 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#httpBasic(org.springframework.security.config.Customizer)
     */
    protected void httpBasic(HttpBasicConfigurer<HttpSecurity> configurer) {
    }

    /**
     * 메소드가 'overriding' 되었는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param methodName
     *            {@link SecurityConfigurer}를 설정하는 메소드 이름
     * @param argTypes
     *            메소드 파라미터 유형
     * @return
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    private final boolean isOverrided(String methodName, Class<?>... argTypes) {
        return getMethod(methodName, argTypes).getDeclaringClass() != AbstractHttpSecurityConfig.class;
    }

    /**
     * {@link HttpSecurity#jee(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableJee} 값을 <code>true</code>로 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#jee(org.springframework.security.config.Customizer)
     */
    protected void jee(JeeConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#logout(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#logout(org.springframework.security.config.Customizer)
     */
    protected void logout(LogoutConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#oauth2Client(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableOauth2Client} 값을 <code>true</code>로 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#oauth2Client(org.springframework.security.config.Customizer)
     */
    protected void oauth2Client(OAuth2ClientConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#oauth2Login(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableOauth2Login} 값을 <code>true</code>로 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#oauth2Login(org.springframework.security.config.Customizer)
     */
    protected void oauth2Login(OAuth2LoginConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#oauth2ResourceServer(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableOauth2ResourceServer} 값을 <code>true</code>로
     * 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#oauth2ResourceServer(org.springframework.security.config.Customizer)
     */
    protected void oauth2ResourceServer(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#passwordManagement(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enablePasswordManagement} 값을 <code>true</code>로
     * 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#passwordManagement(org.springframework.security.config.Customizer)
     */
    protected void passwordManagement(PasswordManagementConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#portMapper(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#portMapper(org.springframework.security.config.Customizer)
     */
    protected void portMapper(PortMapperConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#rememberMe(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableRememberMe} 값을 <code>true</code>로 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#rememberMe(org.springframework.security.config.Customizer)
     */
    protected void rememberMe(RememberMeConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#requestCache(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#requestCache(org.springframework.security.config.Customizer)
     */
    protected void requestCache(RequestCacheConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#requestMatchers(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#requestMatchers(org.springframework.security.config.Customizer)
     */
    protected void requestMatchers(RequestMatcherConfigurer configurer) {
    }

    /**
     * {@link HttpSecurity#requiresChannel(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#requiresChannel(org.springframework.security.config.Customizer)
     */
    protected void requiresChannel(ChannelSecurityConfigurer<HttpSecurity>.ChannelRequestMatcherRegistry configurer) {
    }

    /**
     * {@link HttpSecurity#saml2Login(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableSaml2} 값을 <code>true</code>로 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#saml2Login(org.springframework.security.config.Customizer)
     */
    protected void saml2Login(Saml2LoginConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#saml2Logout(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableSaml2} 값을 <code>true</code>로 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#saml2Logout(org.springframework.security.config.Customizer)
     */
    protected void saml2Logout(Saml2LogoutConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#securityContext(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#securityContext(org.springframework.security.config.Customizer)
     */
    protected void securityContext(SecurityContextConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#servletApi(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#servletApi(org.springframework.security.config.Customizer)
     */
    protected void servletApi(ServletApiConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#sessionManagement(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#sessionManagement(org.springframework.security.config.Customizer)
     */
    protected void sessionManagement(SessionManagementConfigurer<HttpSecurity> configurer) {
    }

    /**
     * {@link HttpSecurity#x509(org.springframework.security.config.Customizer)}에 전달되는 정보를 제공합니다. <br>
     * 하위 클래스는 필요에 따라서 이 메소드를 <code>overriding</code> 합니다.<br>
     * <font color="red">'overriding'한 메소드를 사용하기 위해서는 {@link #enableX509} 값을 <code>true</code>로 설정합니다.</font>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param configurer
     * @return
     * @throws Exception
     *
     * @since 2025. 10. 23.
     * @version 2.1.0
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     * 
     * @see HttpSecurity#x509(org.springframework.security.config.Customizer)
     */
    protected void x509(X509Configurer<HttpSecurity> configurer) {
    }
}
