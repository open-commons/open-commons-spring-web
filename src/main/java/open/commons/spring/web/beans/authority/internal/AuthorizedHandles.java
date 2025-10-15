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
 * Date  : 2025. 9. 19. 오후 3:19:39
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.authority.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.MapUtils;
import open.commons.core.utils.StringUtils;
import open.commons.spring.web.beans.authority.IAuthorizedRequestDataHandler;
import open.commons.spring.web.beans.authority.IUnauthorizedFieldHandler;
import open.commons.spring.web.config.ResourceHandle;
import open.commons.spring.web.config.ResourceHandle.Target;
import open.commons.spring.web.utils.SecurityUtils;

/**
 * 
 * @since 2025. 9. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class AuthorizedHandles {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizedHandles.class);

    /** 문자열 암/복호화 */
    public static final String CIPHER_STRING = "open.commons.spring.web.beans.authority.internal.AuthorizedHandles.CIPHER_STRING";
    /** 전화번호 masking */
    public static final String MASKING_PHONE_NUMBER = "open.commons.spring.web.beans.authority.internal.AuthorizedHandles.MASKING_PHONE_NUMBER";
    /** 이메일 masking */
    public static final String MASKING_EMAIL = "open.commons.spring.web.beans.authority.internal.AuthorizedHandles.MASKING_EMAIL";
    /** 이메일 암/복호화 */
    public static final String CIPHER_EMAIL = "open.commons.spring.web.beans.authority.internal.AuthorizedHandles.CIPHER_EMAIL";
    /** IPv4 masking */
    public static final String MASKING_IPV4 = "open.commons.spring.web.beans.authority.internal.AuthorizedHandles.MASKING_IPV4";
    /** IPv6 masking */
    public static final String MASKING_IPV6 = "open.commons.spring.web.beans.authority.internal.AuthorizedHandles.MASKING_IPV6";

    private static final MultiValueMap<String, Target> HANDLE_TYPES = new LinkedMultiValueMap<>();
    private static final List<ResourceHandle> BUILTIN_HANDLES = new ArrayList<>();

    static {
        // CIPHER_STRING (문자열 암/복호화)
        registerResourceHandles(CIPHER_STRING, (Function<String, String>) SecurityUtils::encryptBySessionUUID, (Function<String, String>) SecurityUtils::decryptBySessionUUID,
                false);

        // MASKING_PHONE_NUMBER (전화번호 마스킹)
        registerResourceHandle(MASKING_PHONE_NUMBER, Target.UNAUTHORIZED, (Function<String, String>) AuthorizedHandles::maskPhoneNumber, false);

        // MASKING_EMAIL (email masking)
        registerResourceHandle(MASKING_EMAIL, Target.UNAUTHORIZED, (Function<String, String>) AuthorizedHandles::maskEmail, false);

        // CIPHER_EMAIL (email 암/복호화)
        registerResourceHandles(CIPHER_EMAIL, (Function<String, String>) AuthorizedHandles::encryptEmail, (Function<String, String>) AuthorizedHandles::decryptEmail, false);

        // MASKING_IPV4
        registerResourceHandle(MASKING_IPV4, Target.UNAUTHORIZED, (Function<String, String>) ipv4 -> AuthorizedHandles.maskIPv4(ipv4, 2, 3), false);

        // MASKING_IPV6
        registerResourceHandle(MASKING_IPV6, Target.UNAUTHORIZED, (Function<String, String>) ipv4 -> AuthorizedHandles.maskIPv6(ipv4, 2, 3, 4, 5, 6, 7), false);
    }

    private static final String DELIM_REGEX = "[-/:_]";

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\d+([-_/:]\\d+)*$");
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(\\d{1,3})\\.?(\\d{1,3})\\.?(\\d{1,3})\\.?(\\d{1,3})$");

    private AuthorizedHandles() {
    }

    /**
     * 등록하려는 '데이터 처리방식' 식별정보가 사용 가능한지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param handleType
     *            '데이터 처리방식' 식별정보
     * @param targetType
     *            적옹 대상 유형
     * @param preemptive
     *            이미 존재하는 경우 교체 여부.
     * @return
     *
     * @since 2025. 9. 27.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static void assertUsableHandleType(@NotEmpty @Nonnull String handleType, @Nonnull Target targetType, boolean preemptive) {
        AssertUtils2.notNull(targetType);
        AssertUtils2.isFalse("데이터 처리 방식은 반드시 설정되어야 합니다.", StringUtils.isNullOrEmptyString(handleType));
        if (preemptive //
                || !HANDLE_TYPES.containsKey(handleType) //
                || !HANDLE_TYPES.get(handleType).contains(targetType) //
        ) {
            update(handleType, targetType);
            return;
        }

        throw ExceptionUtils.newException(IllegalArgumentException.class, "'%s' 데이터 유형에 대한 처리방식(%s)이 존재합니다. 교체여부=%s", targetType, handleType, preemptive);
    }

    /**
     * 내부에 등록된 {@link ResourceHandle}을 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see Collections#unmodifiableList(List)
     */
    public static List<ResourceHandle> authorizedResourcesHandles() {
        return Collections.unmodifiableList(BUILTIN_HANDLES);
    }

    /**
     * {@link ResourceHandle} 객체를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param isBuiltin
     *            내부에서 제공되는 설정인지 여부
     * @param target
     *            데이터 유형
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param handle
     *            데이터 처리 함수
     * @param preemptive
     *            우선적용 여부
     * @return
     *
     * @since 2025. 10. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static ResourceHandle createResourceHandle(boolean isBuiltin, @Nonnull Target target, @NotEmpty @Nonnull String handleType, @Nonnull Function<?, ?> handle,
            boolean preemptive) {
        assertUsableHandleType(handleType, target, preemptive);
        return new ResourceHandleImpl(target, handleType, handle, preemptive);
    }

    /**
     * {@link ResourceHandle} 객체를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 13.		박준홍			최초 작성
     * </pre>
     *
     * * @param target 데이터 유형
     * 
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param handle
     *            데이터 처리 함수
     * @return
     *
     * @since 2025. 10. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static ResourceHandle createResourceHandle(@Nonnull Target target, @NotEmpty @Nonnull String handleType, @Nonnull Function<?, ?> handle) {
        return createResourceHandle(false, target, handleType, handle, false);
    }

    /**
     * {@link ResourceHandle} 객체를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param isBuiltin
     *            내부에서 제공되는 설정인지 여부
     * @param target
     *            데이터 유형
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param handle
     *            데이터 처리 함수
     * @param preemptive
     *            우선적용 여부
     * @return
     *
     * @since 2025. 10. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static ResourceHandle createResourceHandle(@Nonnull Target target, @NotEmpty @Nonnull String handleType, @Nonnull Function<?, ?> handle, boolean preemptive) {
        return createResourceHandle(false, target, handleType, handle, preemptive);
    }

    /**
     * {@link ResourceHandle} 객체를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param isBuiltin
     *            내부에서 제공되는 설정인지 여부
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param unauthorizedHandle
     *            {@link IUnauthorizedFieldHandler#handleObject(String, Object)} 함수
     * @param authorizedHandle
     *            {@link IAuthorizedRequestDataHandler#restoreValue(String, Object)} 함수
     * @param preemptive
     *            우선적용 여부
     * @return
     *
     * @since 2025. 10. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    static Collection<ResourceHandle> createResourceHandles(boolean isBuiltin, @NotEmpty @Nonnull String handleType, @Nonnull Function<?, ?> unauthorizedHandle,
            @Nonnull Function<?, ?> authorizedHandle, boolean preemptive) {

        assertUsableHandleType(handleType, Target.UNAUTHORIZED, preemptive);
        assertUsableHandleType(handleType, Target.AUTHORIZED, preemptive);

        Collection<ResourceHandle> handles = new ArrayList<>();
        handles.add(new ResourceHandleImpl(isBuiltin, Target.UNAUTHORIZED, handleType, unauthorizedHandle, preemptive));
        handles.add(new ResourceHandleImpl(isBuiltin, Target.AUTHORIZED, handleType, authorizedHandle, preemptive));

        return handles;
    }

    /**
     * {@link ResourceHandle} 객체를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param unauthorizedHandle
     *            {@link IUnauthorizedFieldHandler#handleObject(String, Object)}에서 사용되는 함수
     * @param authorizedHandle
     *            {@link IAuthorizedRequestDataHandler#restoreValue(String, Object)}에서 사용되는 함수
     * @return
     *
     * @since 2025. 10. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static Collection<ResourceHandle> createResourceHandles(@NotEmpty @Nonnull String handleType, @Nonnull Function<?, ?> unauthorizedHandle,
            @Nonnull Function<?, ?> authorizedHandle) {
        return createResourceHandles(false, handleType, unauthorizedHandle, authorizedHandle, false);
    }

    /**
     * {@link ResourceHandle} 객체를 제공합니다.
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param unauthorizedHandle
     *            {@link IUnauthorizedFieldHandler#handleObject(String, Object)}에서 사용되는 함수
     * @param authorizedHandle
     *            {@link IAuthorizedRequestDataHandler#restoreValue(String, Object)}에서 사용되는 함수
     * @param preemptive
     *            우선적용 여부
     * @return
     *
     * @since 2025. 10. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    public static Collection<ResourceHandle> createResourceHandles(@NotEmpty @Nonnull String handleType, @Nonnull Function<?, ?> unauthorizedHandle,
            @Nonnull Function<?, ?> authorizedHandle, boolean preemptive) {
        return createResourceHandles(false, handleType, unauthorizedHandle, authorizedHandle, preemptive);
    }

    public static String decryptEmail(String email) {
        if (StringUtils.isNullOrEmptyString(email)) {
            return "";
        }

        int atPos = email.indexOf('@');

        String idStr = null;
        String domainStr = null;
        if (atPos < 0) {
            idStr = email;
            domainStr = "";
        } else if (atPos == 0) {
            idStr = "";
            domainStr = email;
        } else {
            idStr = email.substring(0, atPos);
            domainStr = email.substring(atPos + 1);
        }

        return String.join("@", SecurityUtils.decryptBySessionUUID(idStr), domainStr);
    }

    public static String encryptEmail(String email) {
        if (StringUtils.isNullOrEmptyString(email)) {
            return "";
        }

        int atPos = email.indexOf('@');

        String idStr = null;
        String domainStr = null;
        if (atPos < 0) {
            return email;
        } else if (atPos == 0) {
            idStr = "";
            domainStr = email;
        } else {
            idStr = email.substring(0, atPos);
            domainStr = email.substring(atPos + 1);
        }

        return String.join("@", SecurityUtils.encryptBySessionUUID(idStr), domainStr);
    }

    public static String maskEmail(String email) {
        if (StringUtils.isNullOrEmptyString(email)) {
            return "";
        }

        int atPos = email.indexOf('@');

        String idStr = null;
        String domainStr = null;
        if (atPos < 0) {
            idStr = email;
            domainStr = "";
        } else if (atPos == 0) {
            idStr = "";
            domainStr = email;
        } else {
            idStr = email.substring(0, atPos);
            domainStr = email.substring(atPos + 1);
        }

        int emailLen = email.length();
        int maxLen = (int) (emailLen * Math.random());

        return String.join("@", maskString(idStr, 2, 4 + maxLen, '*', true), domainStr);
    }

    /**
     * IPv4 주소를 마스킹하는 메소드.
     *
     * @param ipv4Address
     *            마스킹할 IPv4 주소
     * @param maskPositions
     *            마스킹할 위치 (1-4) 배열
     * @return 마스킹된 IPv4 주소
     */
    public static String maskIPv4(String ipv4Address, int... maskPositions) {
        if (ipv4Address == null || ipv4Address.isEmpty() || maskPositions == null) {
            return ipv4Address;
        }

        Matcher matcher = IPV4_PATTERN.matcher(ipv4Address);

        String[] parts = new String[4];
        if (matcher.matches()) {
            // 유효한 IPv4 형식일 경우 옥텟을 추출
            for (int i = 0; i < 4; i++) {
                parts[i] = matcher.group(i + 1);
            }

            // 옥텟 값의 유효성 검사 (0-255 범위)
            for (String part : parts) {
                try {
                    int octet = Integer.parseInt(part);
                    if (octet < 0 || octet > 255) {
                        // 범위 벗어날 경우 정규식에 맞는 부분만 사용
                        parts = new String[4];
                        String[] originalParts = ipv4Address.split("\\.");
                        System.arraycopy(originalParts, 0, parts, 0, Math.min(originalParts.length, 4));
                        break;
                    }
                } catch (NumberFormatException e) {
                    // 숫자 형식이 아니면 마스킹 처리
                    parts = ipv4Address.split("\\.");
                    break;
                }
            }
        } else {
            // 정규식에 맞지 않으면 "."을 기준으로 분리
            parts = ipv4Address.split("\\.");
            // 4개보다 많으면 처음 4개만 사용
            if (parts.length > 4) {
                String[] tempParts = new String[4];
                System.arraycopy(parts, 0, tempParts, 0, 4);
                parts = tempParts;
            } else if (parts.length < 4) {
                // 4개 미만일 경우 부족한 부분을 빈 문자열로 채움
                String[] tempParts = new String[4];
                System.arraycopy(parts, 0, tempParts, 0, parts.length);
                for (int i = parts.length; i < 4; i++) {
                    tempParts[i] = "";
                }
                parts = tempParts;
            }
        }

        StringBuilder maskedIp = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            boolean isMasked = false;
            for (int pos : maskPositions) {
                if (pos == i + 1) {
                    isMasked = true;
                    break;
                }
            }

            if (isMasked) {
                maskedIp.append("***");
            } else {
                maskedIp.append(parts[i]);
            }

            if (i < 3) {
                maskedIp.append(".");
            }
        }

        return maskedIp.toString();
    }

    /**
     * IPv6 주소를 마스킹하는 메소드.
     *
     * @param ipv6Address
     *            마스킹할 IPv6 주소
     * @param maskPositions
     *            마스킹할 위치 (1-8) 배열
     * @return 마스킹된 IPv6 주소
     */
    public static String maskIPv6(String ipv6Address, int... maskPositions) {
        if (ipv6Address == null || ipv6Address.isEmpty() || maskPositions == null) {
            return ipv6Address;
        }

        String[] parts = ipv6Address.split(":");
        List<String> expandedParts = new ArrayList<>();
        boolean doubleColonFound = false;

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty() && !doubleColonFound && i > 0 && i < parts.length - 1) {
                int missingParts = 8 - (parts.length - 1);
                for (int j = 0; j < missingParts; j++) {
                    expandedParts.add("0");
                }
                doubleColonFound = true;
            } else if (part.isEmpty() && i == parts.length - 1 && parts.length > 1 && ipv6Address.endsWith("::")) {
                // Ignore trailing empty part if it's due to a valid '::'
            } else {
                expandedParts.add(part);
            }
        }

        // 압축이 아닌데 8개보다 많거나 적으면, 확장 없이 바로 마스킹 처리
        if (ipv6Address.contains("::") && expandedParts.size() != 8) {
            // If it was supposed to be compressed but didn't expand to 8, we handle it as an invalid format.
            expandedParts.clear();
            expandedParts.addAll(Arrays.asList(parts));
        } else if (!ipv6Address.contains("::")) {
            expandedParts.clear();
            expandedParts.addAll(Arrays.asList(parts));
        }

        StringBuilder maskedIp = new StringBuilder();
        for (int i = 0; i < expandedParts.size(); i++) {
            boolean isMasked = false;
            for (int pos : maskPositions) {
                if (pos == i + 1) {
                    isMasked = true;
                    break;
                }
            }

            if (isMasked) {
                maskedIp.append("****");
            } else {
                maskedIp.append(expandedParts.get(i));
            }

            if (i < expandedParts.size() - 1) {
                maskedIp.append(":");
            }
        }

        return maskedIp.toString();
    }

    /**
     * 전화번호를 주어진 규칙에 따라 마스킹하는 메소드.
     *
     * @param phoneNumber
     *            원본 전화번호
     * @return 마스킹된 전화번호
     */
    public static String maskPhoneNumber(String phoneNumber) {
        if (StringUtils.isNullOrEmptyString(phoneNumber)) {
            return "";
        }

        // 숫자와 구분자로만 이루어졌는지 확인
        if (!PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
            return phoneNumber; // 유효하지 않은 형식은 그대로 반환
        }

        String cleanedNumber = phoneNumber.replaceAll(DELIM_REGEX, "");
        String[] parts = phoneNumber.split(DELIM_REGEX);

        String maskedNumber;
        int lastFourDigitsLength = 4;
        String lastFourDigits = cleanedNumber.substring(cleanedNumber.length() - lastFourDigitsLength);

        if (parts.length == 1) {
            // 전부 숫자만 있는 경우
            int remainingLength = cleanedNumber.length() - lastFourDigitsLength;
            if (remainingLength < 5) {
                // 5개보다 작은 경우: ****-{끝4자리}
                maskedNumber = "****-" + lastFourDigits;
            } else if (cleanedNumber.startsWith("01")) {
                // '01'로 시작하면 앞에 3자리 살리고, 나머지 문자개수만큼 '*' 추가
                // 결과: 01{3번째문자}-{나머지 문자개수 만큼 '*'}-{끝4자리}
                String firstThree = cleanedNumber.substring(0, 3);
                int middleLength = remainingLength - firstThree.length();
                StringBuilder maskedMiddle = new StringBuilder();
                for (int i = 0; i < middleLength; i++) {
                    maskedMiddle.append('*');
                }
                maskedNumber = firstThree + "-" + maskedMiddle.toString() + "-" + lastFourDigits;
            } else {
                // 나머지 경우
                StringBuilder maskedMiddle = new StringBuilder();
                for (int i = 0; i < remainingLength; i++) {
                    maskedMiddle.append('*');
                }
                maskedNumber = maskedMiddle.toString() + "-" + lastFourDigits;
            }
        } else if (parts.length == 2) {
            // 구분자로 2개로 나뉘는 경우
            String firstPart = parts[0];
            if (firstPart.length() == 3) { // 3 / 4 형식
                maskedNumber = "***-" + lastFourDigits;
            } else if (firstPart.length() == 4) { // 4 / 4 형식
                maskedNumber = "****-" + lastFourDigits;
            } else {
                maskedNumber = "*********" + "-" + lastFourDigits; // 기타 미정의 형식
            }
        } else if (parts.length == 3) {
            // 구분자로 3개로 나뉘는 경우
            String firstPart = parts[0];
            String secondPart = parts[1];

            if (firstPart.length() == 2 && secondPart.length() == 3) { // 2 / 3 / 4
                maskedNumber = firstPart + "-***-" + lastFourDigits;
            } else if (firstPart.length() == 2 && secondPart.length() == 4) { // 2 / 4 / 4
                maskedNumber = firstPart + "-****-" + lastFourDigits;
            } else if (firstPart.length() == 3 && secondPart.length() == 3) { // 3 / 3 / 4
                maskedNumber = firstPart + "-***-" + lastFourDigits;
            } else if (firstPart.length() == 3 && secondPart.length() == 4) { // 3 / 4 / 4
                maskedNumber = firstPart + "-****-" + lastFourDigits;
            } else {
                maskedNumber = firstPart + "-********-" + lastFourDigits; // 기타 미정의 형식
            }
        } else {
            return phoneNumber; // 예상치 못한 경우 그대로 반환
        }

        return maskedNumber;
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param str
     *            원본 문자열
     * @param allowed
     *            남겨둘 문자열
     * @param max
     *            전체 문자열 길이
     * @param padChar
     *            채워질 문자
     * @param dir
     *            masking 방향.
     *            <li>true: right padding
     *            <li>false: left padding
     * @return
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private static String maskString(String str, int allowed, int max, char padChar, boolean dir) {
        if (allowed > max) {
            throw new IllegalArgumentException("허용길이는 전체 길이보다 작아야 합니다.");
        }
        int visibleLen = Math.min(str.length(), allowed);
        if (dir) {
            return StringUtils.rightPad(str.substring(0, visibleLen), max, padChar);
        } else {
            return StringUtils.leftPad(str.substring(str.length() - visibleLen), max, padChar);
        }
    }

    /**
     * 내부적으로 {@link ResourceHandle} 등록 절차를 통합하여 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		박준홍			최초 작성
     * </pre>
     *
     * @param handleType
     * @param targetType
     * @param function
     * @param preemptive
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private static void registerResourceHandle(@Nonnull String handleType, Target targetType, Function<?, ?> function, boolean preemptive) {
        assertUsableHandleType(handleType, targetType, preemptive);
        BUILTIN_HANDLES.add(new ResourceHandleImpl(true, targetType, handleType, function, preemptive));
    }

    /**
     * 내부적으로 {@link ResourceHandle} 등록 절차를 통합하여 실행합니다. <br>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 13.		박준홍			최초 작성
     * </pre>
     *
     * @param handleType
     *            데이터 처리유형 식별정보
     * @param unauthorizedHandle
     *            {@link IUnauthorizedFieldHandler#handleObject(String, Object)}에서 사용되는 함수
     * @param authorizedHandle
     *            {@link IAuthorizedRequestDataHandler#restoreValue(String, Object)}에서 사용되는 함수
     * @param preemptive
     *            우선적용 여부
     *
     * @since 2025. 10. 13.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private static void registerResourceHandles(@Nonnull String handleType, Function<?, ?> unuathorizedHandle, Function<?, ?> authorizedHandle, boolean preemptive) {
        registerResourceHandle(handleType, Target.UNAUTHORIZED, unuathorizedHandle, preemptive);
        registerResourceHandle(handleType, Target.AUTHORIZED, authorizedHandle, preemptive);
    }

    /**
     * 새로운 데이터인 경우 추가합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 27.		박준홍			최초 작성
     * </pre>
     *
     * @param handleType
     * @param targetType
     *
     * @since 2025. 9. 27.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    private static void update(@NotEmpty @Nonnull String handleType, Target targetType) {
        List<Target> targets = MapUtils.getOrDefault(HANDLE_TYPES, handleType, (Supplier<List<Target>>) () -> {
            return new ArrayList<>();
        }, true);
        if (!targets.contains(targetType)) {
            targets.add(targetType);

            LOGGER.debug("새로운 핸들타입이 추가되었습니다. handleType={}, targetType={}", handleType, targetType);
        }
    }
}
