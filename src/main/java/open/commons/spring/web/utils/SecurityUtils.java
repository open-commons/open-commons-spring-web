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
 * Date  : 2025. 4. 16. 오전 10:08:27
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import open.commons.core.utils.EncryptUtils;
import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.servlet.InternalServerException;

/**
 * Http 요청에 관한 보안 기능을 제공하는 클래스.
 * 
 * @since 2025. 4. 16.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class SecurityUtils {

    public static final String PLAIN_TEXT_CHARSET = "UTF-8";
    private static final String ENCRYPTION_KEY_CHARSET = "UTF-8";

    // prevent to create new instance.
    private SecurityUtils() {
    }

    /**
     * Spring Security 인증 정보를 제거합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 14.     박준홍        최초 작성
     * 2025. 4. 16.     박준홍         이관
     * </pre>
     * 
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static void clearAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * 현재 요청에 대한 Http Session 정보를 제거합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param request
     *
     * @since 2025. 4. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static void clearSession() {

        HttpServletRequest request = getRequest();

        if (request == null) {
            return;
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    /**
     * 주어진 요청에 대한 Http Session 정보를 제거합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2017. 9. 14.     박준홍        최초 작성
     * 2025. 4. 16.		박준홍		  이관
     * </pre>
     *
     * @param request
     *            제거할 {@link HttpSession} 정보를 소유한 요청
     *
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static void clearSession(HttpServletRequest request) {

        if (request == null) {
            return;
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    /**
     * {@link HttpSession} ID를 이용하여 암호화한 문자열을 복호화하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param encText
     *            암호화된 문자열
     * @return
     * @throws InternalServerException
     *             오류 발생시
     *
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String decryptBySessionId(@NotNull String encText) throws InternalServerException {
        return decryptBySessionId(encText, PLAIN_TEXT_CHARSET);
    }

    /**
     * {@link HttpSession} ID를 이용하여 암호화한 문자열을 복호화하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param encText
     *            암호화된 문자열
     * @param encTextCharset
     *            평문일 때의 문자열 {@link Charset}
     * @return
     * @throws InternalServerException
     *             오류 발생시
     *
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String decryptBySessionId(@NotNull String encText, @NotEmpty String encTextCharset) throws InternalServerException {

        try {
            // 복호화 키
            String sessinId = getSessionId();
            return new String(
                    // decrypt text
                    EncryptUtils.decrypt(sessinId, ENCRYPTION_KEY_CHARSET //
                    // Base64 'decoding'
                            , Base64.getDecoder().decode(encText.getBytes()) //
                            , encTextCharset) //
            );
        } catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | BadPaddingException | NullPointerException e) {
            throw ExceptionUtils.newException(InternalServerException.class, e, "데이터 복호화 도중에 오류가 발생하였습니다. 원인=%s", e.getMessage());
        }
    }

    /**
     * 주어진 문자열을 {@link HttpSession} ID 를 이용하여 암호화한 결과를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param plainText
     *            암호화할 문자열
     * @return
     * @throws InternalServerException
     *             오류 발생시
     *
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String encryptBySessionId(@NotNull String text) throws InternalServerException {
        return encryptBySessionId(text, PLAIN_TEXT_CHARSET);
    }

    /**
     * 주어진 문자열을 {@link HttpSession} ID 를 이용하여 암호화한 결과를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param plainText
     *            암호화할 문자열
     * @param plainTextCharset
     *            암호화할 문자열 {@link Charset}
     * @return
     * @throws InternalServerException
     *             오류 발생시
     *
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String encryptBySessionId(@NotNull String plainText, @NotEmpty String plainTextCharset) throws InternalServerException {

        try {
            // 암호화 키
            String sessionId = getSessionId();

            return new String(
                    // Base64 'encoding'
                    Base64.getEncoder().encode(
                            // encrypt text
                            EncryptUtils.encrypt(sessionId, ENCRYPTION_KEY_CHARSET, plainText, plainTextCharset) //
                    )//
            );
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | BadPaddingException | NullPointerException e) {
            throw ExceptionUtils.newException(InternalServerException.class, e, "데이터 암호화 도중에 오류가 발생하였습니다. 원인=%s", e.getMessage());
        }
    }

    /**
     * {@link Authentication} 을 제공하거나 정보가 없는 경우 <code>null</code>을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		박준홍			최초 작성
     * </pre>
     *
     * @return {@link Authentication} 또는 <code>null</code>.
     *
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 현재 로그인한 유저의 ID를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */

    public static String getCurrentPrincipal() {

        Authentication auth = getAuthentication();

        return auth != null ? (String) auth.getPrincipal() : null;
    }

    /**
     * 현재 요청에 대한 {@link HttpSession}을 제공합니다. <code>create</code>가 <code>true</code>인 경우 새로운 {@link HttpSession}을 생성합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param create
     *            새로운 세션 생성 여부.
     * @return {@link HttpSession} 또는 <code>null</code>
     *
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static HttpSession getHttpSession(boolean create) {

        HttpServletRequest request = getRequest();

        return request != null ? request.getSession(create) : null;
    }

    /**
     * 현재 {@link HttpServletRequest} 정보를 제공하거나 현재 {@link RequestAttributes} 이 {@link ServletRequestAttributes}의 하위클래스가
     * 아닌 경우 <code>null</code> 을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		박준홍			최초 작성
     * </pre>
     *
     * @return {@link HttpServletRequest} 또는 <code>null</code>.
     *
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static HttpServletRequest getRequest() {

        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();

        if (attrs instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) attrs).getRequest();
        } else {
            return null;
        }
    }

    /**
     * 현재 요청에 대한 {@link HttpSession} ID 를 제공하거나 <code>null</code>을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		박준홍			최초 작성
     * </pre>
     *
     * @return {@link HttpServletRequest} ID 또는 <code>null</code>.
     *
     * @since 2025. 4. 16.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #getHttpSession(boolean)
     * @see #getRequest()
     */
    public static String getSessionId() {
        HttpSession session = getHttpSession(false);
        return session != null ? session.getId() : null;
    }
}
