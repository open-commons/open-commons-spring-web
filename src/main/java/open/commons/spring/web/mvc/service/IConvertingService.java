/*
 * Copyright 2021 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2021. 12. 3. 오후 3:29:34
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.mvc.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import open.commons.core.Result;
import open.commons.core.utils.ObjectUtils;

/**
 * DTO 와 Entity 간 데이터 변환을 지원하는 기능 정의.
 * 
 * @since 2021. 12. 3.
 * @version 0.4.0
 * @author parkjunhong77@gmail.com
 */
public interface IConvertingService {

    /**
     * 여러 개의 데이터 변환을 지원합니다. <br>
     *
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 28.		박준홍			최초 작성
     * 2021. 12. 30.    박준홍     변환 이후 Class<?> 파라미터 삭제.
     * </pre>
     *
     * @param <S>
     *            변환 이전 데이터 타입
     * @param <T>
     *            변환 이후 데이터 타입
     * @param resultSrc
     *            변환 이전 데이터 조회 결과
     * @param converter
     *            변환 함수
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    default <S, T> Result<Page<T>> convertMultiPaginationResult(@NotNull Result<Page<S>> resultSrc, @NotNull Function<S, T> converter) {
        if (resultSrc.isSuccess()) {
            Page<S> page = resultSrc.getData();
            List<S> srcContent = page.getContent();
            List<T> targetContent = convertMultiResult(srcContent, converter);

            return Result.success(new PageImpl<T>(targetContent, page.getPageable(), page.getTotalElements()));
        } else {
            return Result.error(resultSrc.getMessage());
        }
    }

    /**
     * 여러 개의 데이터 변환을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 3.     박준홍         최초 작성
     * 2021. 12. 30.    박준홍     변환 이후 Class<?> 파라미터 삭제.
     * </pre>
     *
     * @param <S>
     *            변환 이전 데이터 타입
     * @param <T>
     *            변환 이후 데이터 타입
     * @param source
     *            변환 이전 데이터
     * @param converter
     *            변환 함수
     * @return
     *
     * @since 2021. 12. 3.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    default <S, T> List<T> convertMultiResult(@NotNull List<S> source, @NotNull Function<S, T> converter) {
        return convertMultiResultAsStream(source, converter).collect(Collectors.toList());
    }

    /**
     * 소스 객체에서 정보를 이용하서 대상 객체를 생성해서 제공합니다.<br>
     * 소스 및 대상 객체와 상위 객체에서 제공하는 모든 정보를 이용합니다. <br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 11. 9.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            원본 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * @param resultSrc
     *            원본 데이터
     * @param returnType
     *            대상 데이터 타입
     * @return
     *
     * @since 2022. 11. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (jhpark@ymtech.co.kr)
     */
    default <S, T> Result<List<T>> convertMultiResult(@NotNull Result<List<S>> resultSrc, @NotNull Class<T> returnType) {
        return convertMultiResult(resultSrc, srcObj -> ObjectUtils.transform(srcObj, true, returnType, true));
    }

    /**
     * 여러 개의 데이터 변환을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 3.     박준홍         최초 작성
     * 2021. 12. 30.    박준홍     변환 이후 Class<?> 파라미터 삭제.
     * </pre>
     *
     * @param <S>
     *            변환 이전 데이터 타입
     * @param <T>
     *            변환 이후 데이터 타입
     * @param resultSrc
     *            변환 이전 데이터 조회 결과
     * @param converter
     *            변환 함수
     * @return
     *
     * @since 2021. 12. 3.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    default <S, T> Result<List<T>> convertMultiResult(@NotNull Result<List<S>> resultSrc, @NotNull Function<S, T> converter) {
        if (resultSrc.isSuccess()) {
            return Result.success(convertMultiResult(resultSrc.getData(), converter));
        } else {
            return Result.error(resultSrc.getMessage());
        }
    }

    /**
     * 여러 개의 데이터 변환을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * 2021. 12. 30.    박준홍     변환 이후 Class<?> 파라미터 삭제.
     * </pre>
     *
     * @param <S>
     *            변환 이전 데이터 타입
     * @param <T>
     *            변환 이후 데이터 타입
     * @param source
     *            변환 이전 데이터
     * @param converter
     *            변환 함수
     * @return
     *
     * @since 2021. 12. 6.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    default <S, T> Stream<T> convertMultiResultAsStream(@NotNull List<S> source, @NotNull Function<S, T> converter) {
        return source.stream().map(converter);
    }

    /**
     * 소스 객체에서 정보를 이용하서 대상 객체를 생성해서 제공합니다.<br>
     * 소스 및 대상 객체와 상위 객체에서 제공하는 모든 정보를 이용합니다. <br>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 11. 9.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            원본 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * @param resultSrc
     *            원본 데이터
     * @param returnType
     *            대상 데이터 타입
     * @return
     *
     * @since 2022. 11. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (jhpark@ymtech.co.kr)
     */
    default <S, T> Result<T> convertSingleResult(@NotNull Result<S> resultSrc, @NotNull Class<T> returnType) {
        return convertSingleResult(resultSrc, srcObj -> ObjectUtils.transform(srcObj, true, returnType, true));
    }

    /**
     * 단일 데이터 변환을 지원합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 3.     박준홍         최초 작성
     * 2021. 12. 30.    박준홍     변환 이후 Class<?> 파라미터 삭제.
     * </pre>
     *
     * @param <S>
     *            변환 이전 데이터 타입
     * @param <T>
     *            변환 이후 데이터 타입
     * @param resultSrc
     *            변환 이전 데이터 조회 결과
     * @param converter
     *            변환 함수
     * @return
     *
     * @since 2021. 12. 3.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    default <S, T> Result<T> convertSingleResult(@NotNull Result<S> resultSrc, @NotNull Function<S, T> converter) {
        if (resultSrc.isSuccess()) {
            return resultSrc.getData() != null //
                    ? Result.success(converter.apply(resultSrc.getData())) //
                    : Result.success(null);
        } else {
            return Result.error(resultSrc.getMessage());
        }
    }

    /**
     * 소스 객체에서 정보를 이용하서 대상 객체를 생성해서 제공합니다.<br>
     * 소스 및 대상 객체와 상위 객체에서 제공하는 모든 정보를 이용합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2022. 11. 2.     박준홍         최초 작성
     * </pre>
     *
     * @param <S>
     *            원본 데이터 타입
     * @param <T>
     *            대상 데이터 타입
     * 
     * @param srcObj
     *            원본 객체
     * @param targetClass
     *            대상 클래스
     * @return
     *
     * @since 2022. 11. 2.
     * @version 0.4.0
     * @author Park_Jun_Hong (jhpark@ymtech.co.kr)
     */
    default <S, T> T transformAll(S srcObj, Class<T> targetClass) {
        return ObjectUtils.transform(srcObj, true, targetClass, true);
    }
}
