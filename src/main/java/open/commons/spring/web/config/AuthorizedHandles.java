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

package open.commons.spring.web.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import open.commons.core.utils.StringUtils;
import open.commons.spring.web.beans.authority.builtin.ResourceHandle;
import open.commons.spring.web.beans.authority.builtin.ResourceHandle.Target;
import open.commons.spring.web.beans.authority.builtin.ResourceHandleImpl;
import open.commons.spring.web.utils.SecurityUtils;

/**
 * 
 * @since 2025. 9. 19.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
@Configuration
public class AuthorizedHandles {
    /** 문자열 암/복호화 */
    public static final int CIPHER_STRING = 0x00;
    /** 전화번호 masking */
    public static final int MASKING_PHONE_NUMBER = CIPHER_STRING + 1;
    /** 이메일 masking */
    public static final int MASKING_EMAIL = MASKING_PHONE_NUMBER + 1;
    /** 이메일 암/복호화 */
    public static final int CIPHER_EMAIL = MASKING_EMAIL + 1;
    /** IPv4 masking */
    public static final int MASKING_IPV4 = CIPHER_EMAIL + 1;
    /** IPv6 masking */
    public static final int MASKING_IPV6 = MASKING_IPV4 + 1;

    private static final String DELIM_REGEX = "[-/:_]";
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\d+([-_/:]\\d+)*$");
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(\\d{1,3})\\.?(\\d{1,3})\\.?(\\d{1,3})\\.?(\\d{1,3})$");

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 19.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 9. 19.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public AuthorizedHandles() {
    }

    @Bean
    List<ResourceHandle> authorizedResourcesHandles() {

        List<ResourceHandle> handles = new ArrayList<>();
        // CIPHER_STRING (문자열 암/복호화)
        handles.add(new ResourceHandleImpl(Target.UNAUTHORIZED, CIPHER_STRING, (Function<String, String>) SecurityUtils::encryptBySessionUUID));
        handles.add(new ResourceHandleImpl(Target.AUTHORIZED, CIPHER_STRING, (Function<String, String>) SecurityUtils::decryptBySessionUUID));

        // MASKING_PHONE_NUMBER (전화번호 마스킹)
        handles.add(new ResourceHandleImpl(Target.UNAUTHORIZED, MASKING_PHONE_NUMBER, (Function<String, String>) AuthorizedHandles::maskPhoneNumber));

        // MASKING_EMAIL (email masking)
        handles.add(new ResourceHandleImpl(Target.UNAUTHORIZED, MASKING_EMAIL, (Function<String, String>) AuthorizedHandles::maskEmail));

        // CIPHER_EMAIL (email 암/복호화)
        handles.add(new ResourceHandleImpl(Target.UNAUTHORIZED, CIPHER_EMAIL, (Function<String, String>) AuthorizedHandles::encryptEmail));
        handles.add(new ResourceHandleImpl(Target.AUTHORIZED, CIPHER_EMAIL, (Function<String, String>) AuthorizedHandles::decryptEmail));

        // MASKING_IPV4
        handles.add(new ResourceHandleImpl(Target.UNAUTHORIZED, MASKING_IPV4, (Function<String, String>) ipv4 -> AuthorizedHandles.maskIPv4(ipv4, 2, 3)));

        // MASKING_IPV6
        handles.add(new ResourceHandleImpl(Target.UNAUTHORIZED, MASKING_IPV6, (Function<String, String>) ipv4 -> AuthorizedHandles.maskIPv6(ipv4, 2, 3, 4, 5, 6, 7)));

        return handles;
    }

    public static String decryptEmail(String email) {
        if (StringUtils.isNullOrEmptyString(email)) {
            return "";
        }

        String[] emailArray = email.split("@");
        return String.join("@", SecurityUtils.decryptBySessionUUID(emailArray[0]), emailArray[1]);
    }

    public static String encryptEmail(String email) {
        if (StringUtils.isNullOrEmptyString(email)) {
            return "";
        }

        String[] emailArray = email.split("@");
        return String.join("@", SecurityUtils.encryptBySessionUUID(emailArray[0]), emailArray[1]);
    }

    public static String maskEmail(String email) {
        if (StringUtils.isNullOrEmptyString(email)) {
            return "";
        }
        String[] emailArray = email.split("@");
        return String.join("@", maskString(emailArray[0], 3, 16, '*', true), emailArray[1]);
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
}
