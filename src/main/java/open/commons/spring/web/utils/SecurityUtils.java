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
import java.util.UUID;
import java.util.function.Function;

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
import open.commons.core.utils.ObjectUtils;
import open.commons.spring.web.servlet.BadRequestException;
import open.commons.spring.web.servlet.InternalServerException;

/**
 * Http 요청에 관한 보안 기능을 제공하는 클래스.
 * 
 * @since 2025. 4. 16.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class SecurityUtils {

    /** 세선별 UUID에 대한 키 */
    private static final String SESSION_KEY_UUID = "__SEC_UUID__";
    /** 평문 캐릭터셋 기본 값 */
    private static final String PLAIN_TEXT_CHARSET = "UTF-8";
    /** 암호화키 캐릭터셋 기본 값 */
    private static final String ENCRYPTION_KEY_CHARSET = "UTF-8";

    /**
     * 문자열을 {@link HttpSession}에 따라 변경되는 UUID 를 이용하여 암호화한 결과를 제공합니다.
     * 
     * @param plainText
     *            문자열
     * 
     * @return 암호화된 문자열
     */
    private static final Function<String, String> ENC_BY_SESSION_UUID = plainText -> SecurityUtils.encryptBySessionUUID(plainText);

    /**
     * {@link HttpSession}에 따라 변경되는 UUID 를 이용하여 암호화한 결과를 복호화화여 제공합니다.
     * 
     * @param cipherText
     *            문자열
     * 
     * @return 암호화된 문자열
     */
    private static final Function<String, String> DEC_BY_SESSION_UUID = cipherText -> SecurityUtils.decryptBySessionUUID(cipherText);

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
     * 2017. 9. 14.     parkjunhong77@gmail.com        최초 작성
     * 2025. 4. 16.     parkjunhong77@gmail.com         이관
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
     * 2025. 4. 17.		parkjunhong77@gmail.com			최초 작성
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
     * 2017. 9. 14.     parkjunhong77@gmail.com        최초 작성
     * 2025. 4. 16.		parkjunhong77@gmail.com		  이관
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
     * 2025. 4. 16.		parkjunhong77@gmail.com			최초 작성
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
     * @deprecated {@link #decryptBySessionUUID(String)}를 사용하세요. 다음 버전에서 삭제될 예정입니다.
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
     * 2025. 4. 16.		parkjunhong77@gmail.com			최초 작성
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
     * @deprecated {@link #decryptBySessionUUID(String, String)}를 사용하세요. 다음 버전에서 삭제될 예정입니다.
     */
    public static String decryptBySessionId(@NotNull String encText, @NotEmpty String encTextCharset) throws InternalServerException {
        return decryptBySessionUUID(encText, encTextCharset);
    }

    /**
     * {@link HttpSession}에서 생성한 UUID를 이용하여 암호화한 문자열을 복호화하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param encText
     *            암호화된 문자열
     * @return
     * @throws InternalServerException
     *             오류 발생시
     *
     * @since 2025. 4. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String decryptBySessionUUID(@NotNull String encText) throws InternalServerException {
        return decryptBySessionUUID(encText, PLAIN_TEXT_CHARSET);
    }

    /**
     * {@link HttpSession}에서 생성한 UUID를 이용하여 암호화한 문자열을 복호화하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 18.		parkjunhong77@gmail.com			최초 작성
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
     * @since 2025. 4. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String decryptBySessionUUID(@NotNull String encText, @NotEmpty String encTextCharset) throws InternalServerException {
        try {
            // 복호화 키
            String decKey = getSessionUUID();

            return new String(
                    // decrypt text
                    EncryptUtils.decrypt(decKey, ENCRYPTION_KEY_CHARSET //
                    // Base64 'decoding'
                            , Base64.getUrlDecoder().decode(encText.getBytes()) //
                            , encTextCharset) //
            );
        } catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException
                | NullPointerException e) {
            throw ExceptionUtils.newException(InternalServerException.class, e, "데이터 처리 도중에 오류가 발생하였습니다. 원인=%s", e.getMessage());
        } catch (IllegalBlockSizeException | BadPaddingException | IllegalArgumentException e) {
            // 에러 발생 경우
            // #1. 암호화된 값이
            // - 길때: Input byte array has incorrect ending byte at {길이} / IllegalArgumentException
            // - 짧을 때:
            // + 첫 문자를 지울 때: Last unit does not have enough valid bits / IllegalArgumentException
            // + 끝 문자를 지울 때: Input byte array has wrong 4-byte ending unit / IllegalArgumentException
            // - 길이가 다를 때
            // + Given final block not properly padded. Such issues can arise if a bad key is used during decryption /
            // javax.crypto.BadPaddingException
            // + Input length must be multiple of 16 when decrypting with padded cipher /
            // javax.crypto.IllegalBlockSizeException
            throw ExceptionUtils.newException(BadRequestException.class, e, "잘못된 입력 데이터 입니다. 이로 인해 데이터가 처리 도중에 오류가 발생하였습니다. 원인은 다음과 같습니다. '%s'", e.getMessage());
        }
    }

    /**
     * 주어진 문자열을 {@link HttpSession} ID 를 이용하여 암호화한 결과를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		parkjunhong77@gmail.com			최초 작성
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
     * @deprecated {@link #encryptBySessionUUID(String)}를 사용하세요. 다음 버전에서 삭제될 예정입니다.
     */
    public static String encryptBySessionId(@NotNull String plainText) throws InternalServerException {
        return encryptBySessionId(plainText, PLAIN_TEXT_CHARSET);
    }

    /**
     * 주어진 문자열을 {@link HttpSession} ID 를 이용하여 암호화한 결과를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		parkjunhong77@gmail.com			최초 작성
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
     * @deprecated {@link #encryptBySessionUUID(String, String)}를 사용하세요. 다음 버전에서 삭제될 예정입니다.
     */
    public static String encryptBySessionId(@NotNull String plainText, @NotEmpty String plainTextCharset) throws InternalServerException {
        return encryptBySessionUUID(plainText, plainTextCharset);
    }

    /**
     * 주어진 문자열을 {@link HttpSession}에서 생성한 UUID를 이용하여 암호화한 결과를 제공합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param plainText
     *            암호화할 문자열
     * @return
     * @throws InternalServerException
     *             오류 발생시
     *
     * @since 2025. 4. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String encryptBySessionUUID(@NotNull String plainText) throws InternalServerException {
        return encryptBySessionUUID(plainText, PLAIN_TEXT_CHARSET);
    }

    /**
     * 주어진 문자열을 {@link HttpSession}에서 생성한 UUID를 이용하여 암호화한 결과를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 18.		parkjunhong77@gmail.com			최초 작성
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
     * @since 2025. 4. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String encryptBySessionUUID(@NotNull String plainText, @NotEmpty String plainTextCharset) throws InternalServerException {
        try {
            // 암호화 키
            String encKey = getSessionUUID();

            return new String(
                    // Base64 'encoding'
                    Base64.getUrlEncoder().encode(
                            // encrypt text
                            EncryptUtils.encrypt(encKey, ENCRYPTION_KEY_CHARSET, plainText, plainTextCharset) //
                    )//
            );
        } catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException
                | NullPointerException e) {
            throw ExceptionUtils.newException(InternalServerException.class, e, "데이터 처리 도중에 오류가 발생하였습니다. 원인=%s", e.getMessage());
        } catch (IllegalBlockSizeException | BadPaddingException | IllegalArgumentException e) {
            throw ExceptionUtils.newException(BadRequestException.class, e, "데이터 처리 도중에 오류가 발생하였습니다. 원인=%s", e.getMessage());
        }
    }

    /**
     * {@link Authentication} 을 제공하거나 정보가 없는 경우 <code>null</code>을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 16.		parkjunhong77@gmail.com			최초 작성
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
     * 2025. 4. 16.		parkjunhong77@gmail.com			최초 작성
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
     * 현재 요청에 대한 {@link HttpSession}을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 4. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static HttpSession getHttpSession() {
        return getHttpSession(false);
    }

    /**
     * 현재 요청에 대한 {@link HttpSession}을 제공합니다. <code>create</code>가 <code>true</code>인 경우 새로운 {@link HttpSession}을 생성합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 4. 16.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param request
     *            Http 요청
     * @param create
     *            요청에 세션이 없는 경우 새로운 세션 생성 여부.
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
     * 주어진 요청에 대한 {@link HttpSession}을 제공합니다. <code>create</code>가 <code>true</code>인 경우 새로운 {@link HttpSession}을 생성합니다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param request
     *            Http 요청
     * @param create
     *            요청에 세션이 없는 경우 새로운 세션 생성 여부.
     * @return {@link HttpSession} 또는 <code>null</code>
     * 
     * @since 2025. 4. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static HttpSession getHttpSession(HttpServletRequest request, boolean create) {
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
     * 2025. 4. 16.		parkjunhong77@gmail.com			최초 작성
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
     * 2025. 4. 16.		parkjunhong77@gmail.com			최초 작성
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
        HttpServletRequest request = getRequest();
        return request != null ? request.getRequestedSessionId() : null;
    }

    /**
     * 현재 요청에 대한 {@link HttpSession} ID 를 제공하거나 <code>null</code>을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param create
     *            요청에 세션이 없는 경우 새로운 세션 생성 여부.
     * @return {@link HttpSession} 또는 <code>null</code>
     *
     * @since 2025. 4. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String getSessionId(boolean create) {
        HttpSession session = getHttpSession(create);
        return session != null ? session.getId() : null;
    }

    /**
     * 주어진 요청에 대한 {@link HttpSession} ID 를 제공하거나 <code>null</code>을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param request
     *            Http 요청
     * 
     * @return {@link HttpSession} 또는 <code>null</code>
     *
     * @since 2025. 4. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String getSessionId(HttpServletRequest request) {
        return getSessionId(request, false);
    }

    /**
     * 주어진 요청에 대한 {@link HttpSession} ID 를 제공하거나 <code>null</code>을 반환합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param request
     *            Http 요청
     * @param create
     *            요청에 세션이 없는 경우 새로운 세션 생성 여부.
     * @return {@link HttpSession} 또는 <code>null</code>
     *
     * @since 2025. 4. 17.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String getSessionId(HttpServletRequest request, boolean create) {
        if (request == null) {
            return null;
        }

        HttpSession session = request.getSession(create);
        return session != null ? session.getId() : null;
    }

    /**
     * {@link HttpSession}에 저장된 UUID를 제공합니다. UUID가 없는 경우 새로 생성을 합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     * @throws NullPointerException
     *             {@link HttpSession}을 없는 경우
     *
     * @since 2025. 4. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String getSessionUUID() throws NullPointerException {
        return getSessionUUID(getHttpSession());
    }

    /**
     * {@link HttpSession}에 저장된 UUID를 제공합니다. UUID가 없는 경우 새로 생성을 합니다.<br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 18.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param session
     *            세션 정보
     * @return
     * @throws NullPointerException
     *             {@link HttpSession}이 <code>null</code>인 경우
     * 
     *
     * @since 2025. 4. 18.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static String getSessionUUID(@NotNull HttpSession session) throws NullPointerException {
        synchronized (session) {
            String uuid = (String) session.getAttribute(SESSION_KEY_UUID);
            if (uuid == null) {
                uuid = UUID.randomUUID().toString();
                session.setAttribute(SESSION_KEY_UUID, uuid);
            }
            return uuid;
        }
    }

    /**
     * 민감한 정보를 암호화하는 함수를 등록합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 9.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param srcClass
     *            정보를 제공하는 데이터 유형
     * @param targetClass
     *            정보를 받는 데이터 유형
     * @param property
     *            속성 이름
     *
     * @since 2025. 5. 9.
     * @version 0.1.0
     * @author Park Jun-Hong (jhpark@ymtech.co.kr)
     */
    public static void registerDecryptionConverter(Class<?> srcClass, Class<?> targetClass, String property) {
        ObjectUtils.registerPropertyConverter(srcClass, String.class, property, targetClass, String.class, DEC_BY_SESSION_UUID);
    }

    /**
     * 암호화된 민감한 정보를 복호화하는 함수를 등록합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2025. 5. 9.      parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param srcClass
     *            정보를 제공하는 데이터 유형
     * @param targetClass
     *            정보를 받는 데이터 유형
     * @param property
     *            속성 이름
     *
     * @since 2025. 5. 9.
     * @version 0.1.0
     * @author Park Jun-Hong (jhpark@ymtech.co.kr)
     */
    public static void registerEncryptionConverter(Class<?> srcClass, Class<?> targetClass, String property) {
        ObjectUtils.registerPropertyConverter(srcClass, String.class, property, targetClass, String.class, ENC_BY_SESSION_UUID);
    }
}
