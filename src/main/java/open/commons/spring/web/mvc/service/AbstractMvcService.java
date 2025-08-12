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
 * Date  : 2021. 12. 9. 오후 1:12:40
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import open.commons.core.Result;
import open.commons.core.annotation.Getter;
import open.commons.core.annotation.Setter;
import open.commons.core.function.QuadFunction;
import open.commons.core.function.Runner;
import open.commons.core.function.TripleFunction;
import open.commons.core.utils.ObjectUtils;
import open.commons.spring.web.utils.PaginationUtils;

/**
 * 
 * @since 2021. 12. 9.
 * @version 0.4.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractMvcService extends AbstractGenericService {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 9.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMvcService() {
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            DTO 타입
     * @param data
     *            데이터 제공 함수
     * @param count
     *            데이터 개수 제공 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    private <E> Result<Page<E>> executePagination(Supplier<Result<List<E>>> data, Supplier<Result<Integer>> count, int offset, int limit, String[] orderByArgs) {

        final String propData = "content";
        final String propCount = "count";

        Map<String, Object> map = new HashMap<>();

        Runner rData = () -> map.put(propData, data.get());
        Runner rCount = () -> map.put(propCount, count.get());

        // #1. 실행
        Stream.of(rData, rCount).forEach(r -> r.run());

        // #2. 데이터 생성
        Result<List<E>> resultData = (Result<List<E>>) map.get(propData);
        if (resultData.isError()) {
            return Result.error(resultData.getMessage());
        }

        Result<Integer> resultCount = (Result<Integer>) map.get(propCount);
        if (resultCount.isError()) {
            return Result.error(resultCount.getMessage());
        }

        int totalCount = resultCount.getData();

        // #3. Pagination 정보 생성
        Pageable p = PageRequest.of(offset / limit, limit, orderByArgs != null ? Sort.by(orderBy(orderByArgs)) : Sort.unsorted());

        return Result.success(new PageImpl<E>(resultData.getData(), p, totalCount));
    }

    /**
     * <code>DAO(Data Access Object)</code> 조회 결과가 중복인지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.        박준홍         최초 작성
     * </pre>
     *
     * @param <D>
     * @param resultDao
     * @param data
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    protected <D> Result<Boolean> exists(Result<?> resultDao, D data) {
        if (resultDao.isError()) {
            return Result.error(resultDao.getMessage());
        }

        if (resultDao.getData() != null) {
            String errMsg = String.format("동일한 식별정보를 갖는 데이터가 이미 존재합니다. 식별정보=%s", data);
            logger.warn(errMsg);
            return Result.success(true).setMessage(errMsg);
        }
        return Result.success(false);
    }

    /**
     * {@link Pageable}에서 읽을 데이터 개수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * 2022. 2. 10.     박준홍     구현부를 {@link PaginationUtils#limit(Pageable)} 로 이관.
     * </pre>
     *
     * @param pageable
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     * 
     * @see PaginationUtils#limit(Pageable)
     */
    protected int limit(Pageable pageable) {
        return PaginationUtils.limit(pageable);
    }

    /**
     * {@link Pageable}에서 데이터 시작위치를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * 2022. 2. 10.     박준홍     구현부를 {@link PaginationUtils#offset(Pageable)} 로 이관.
     * </pre>
     *
     * @param pageable
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     * 
     * @see PaginationUtils#offset(Pageable)
     */
    protected int offset(Pageable pageable) {
        return PaginationUtils.offset(pageable);
    }

    /**
     * {@link Pageable}에서 쿼리 조회 결과 정렬 기준을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * 2022. 2. 10.     박준홍     구현부를 {@link PaginationUtils#orderBy(Pageable)} 로 이관.
     * </pre>
     *
     * @param pageable
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     * 
     * @see PaginationUtils#orderBy(Pageable)
     */
    protected String[] orderBy(Pageable pageable) {
        return PaginationUtils.orderBy(pageable);
    }

    /**
     * DBMS 정렬쿼리를 Pagination 정렬 조건으로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 28.		박준홍			최초 작성
     * 2022. 2. 10.     박준홍     구현부를 {@link PaginationUtils#orderBy(String...)} 로 이관.
     * </pre>
     *
     * @param orderByArgs
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     * 
     * @see PaginationUtils#orderBy(String...)
     */
    protected List<Order> orderBy(String... orderByArgs) {
        return PaginationUtils.orderBy(orderByArgs);
    }

    /**
     * DTO 데이터를 Entity로 변환하여 DB에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param <D>
     *            DTO 데이터 타입.
     * @param <E>
     *            Entity 데이터 타입.
     * @param data
     *            DB에 추가할 데이터.
     * @param entityType
     *            DB Table 데이터 타입.
     * @param funcSave
     *            DB Table 추가 함수.
     * @return
     *
     * @since 2021. 12. 24.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    @SuppressWarnings("unchecked")
    protected <D, E> Result<Integer> save(List<D> data, Class<E> entityType, Function<List<E>, Result<Integer>> funcSave) {
        if (data == null || data.size() < 1) {
            return Result.success(0);
        }
        return funcSave.apply(convertMultiResult(data, ObjectUtils.getTransformer((Class<D>) data.get(0).getClass(), true, entityType, true)));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E> Result<List<E>> selectMulti(SearchResultType type //
            , Function<String[], Result<List<E>>> funcAll //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String... orderByArgs) {
        switch (type) {
            case ALL:
                return funcAll.apply(orderByArgs);
            case PAGINATION:
                return funcPagination.apply(offset, limit, orderByArgs);
            default:
                throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * 2021. 12. 30.    박준홍     DTO Class<?> 파라미터 삭제.
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 6.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D> Result<List<D>> selectMulti(SearchResultType type //
            , Function<String[], Result<List<E>>> funcAll //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String[] orderByArgs //
            , Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, funcAll, funcPagination, offset, limit, orderByArgs), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param pageable
     *            데이터 결과 Pagination 정보
     * @return
     *
     * @since 2022. 1. 10.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E> Result<List<E>> selectMulti(SearchResultType type //
            , Function<String[], Result<List<E>>> funcAll //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination //
            , Pageable pageable //
    ) {
        if (SearchResultType.ALL.equals(type)) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE, pageable.getSort());
        }

        return selectMulti(type, funcAll, funcPagination, offset(pageable), limit(pageable), orderBy(pageable));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param pageable
     *            데이터 결과 Pagination 설정
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D> Result<List<D>> selectMulti(SearchResultType type //
            , Function<String[], Result<List<E>>> funcAll //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination //
            , Pageable pageable //
            , Function<E, D> converter) {

        if (SearchResultType.ALL.equals(type)) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE, pageable.getSort());
        }

        return selectMulti(type, funcAll, funcPagination, offset(pageable), limit(pageable), orderBy(pageable), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, P> Result<List<E>> selectMulti(SearchResultType type //
            , P param //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String... orderByArgs) {
        switch (type) {
            case ALL:
                return funcAll.apply(param, orderByArgs);
            case PAGINATION:
                return funcPagination.apply(param, offset, limit, orderByArgs);
            default:
                throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * 2021. 12. 30.    박준홍     DTO Class<?> 파라미터 삭제.
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 6.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D, P> Result<List<D>> selectMulti(SearchResultType type //
            , P param //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String[] orderByArgs //
            , Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, param, funcAll, funcPagination, offset, limit, orderByArgs), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param pageable
     *            데이터 결과 Pagination 설정
     * @return
     *
     * @since 2022. 1. 10.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, P> Result<List<E>> selectMulti(SearchResultType type //
            , P param //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination //
            , Pageable pageable) {

        if (SearchResultType.ALL.equals(type)) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE, pageable.getSort());
        }

        return selectMulti(type, param, funcAll, funcPagination, offset(pageable), limit(pageable), orderBy(pageable));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param pageable
     *            데이터 결과 Pagination 설정
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D, P> Result<List<D>> selectMulti(SearchResultType type //
            , P param //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination //
            , Pageable pageable//
            , Function<E, D> converter) {

        if (SearchResultType.ALL.equals(type)) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE, pageable.getSort());
        }

        return selectMulti(type, param, funcAll, funcPagination, offset(pageable), limit(pageable), orderBy(pageable), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 8.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @return
     *
     * @since 2021. 12. 8.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, P> Result<List<E>> selectMulti(SearchResultType type //
            , P param //
            , Function<P, Result<List<E>>> funcAll //
            , TripleFunction<P, Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
    ) {
        switch (type) {
            case ALL:
                return funcAll.apply(param);
            case PAGINATION:
                return funcPagination.apply(param, offset, limit);
            default:
                throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * 2021. 12. 30.    박준홍     DTO Class<?> 파라미터 삭제.
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 6.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D, P> Result<List<D>> selectMulti(SearchResultType type //
            , P param //
            , Function<P, Result<List<E>>> funcAll //
            , TripleFunction<P, Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, param, funcAll, funcPagination, offset, limit), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @return
     *
     * @since 2021. 12. 6.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E> Result<List<E>> selectMulti(SearchResultType type //
            , Supplier<Result<List<E>>> funcAll //
            , BiFunction<Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
    ) {
        switch (type) {
            case ALL:
                return funcAll.get();
            case PAGINATION:
                return funcPagination.apply(offset, limit);
            default:
                throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 6.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param type
     *            검색 유형.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 6.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D> Result<List<D>> selectMulti(SearchResultType type //
            , Supplier<Result<List<E>>> funcAll //
            , BiFunction<Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , Function<E, D> converter) {
        return convertMultiResult(selectMulti(type, funcAll, funcPagination, offset, limit), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <P>
     *            파라미터 타입
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @return
     *
     * @since 2022. 1. 10.
     * @version 1.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, P> Result<Page<E>> selectMultiPagination(P param //
            , Function<P, Result<Integer>> funcCount //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String... orderByArgs) {

        // #1-1. 데이터 조회
        Supplier<Result<List<E>>> data = () -> funcPagination.apply(param, offset, limit, orderByArgs);
        // #1-2. 개수 조회
        Supplier<Result<Integer>> count = () -> funcCount.apply(param);

        return executePagination(data, count, offset, limit, orderByArgs);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 1.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D, P> Result<Page<D>> selectMultiPagination(P param //
            , Function<P, Result<Integer>> funcCount //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String[] orderByArgs //
            , Function<E, D> converter) {
        return convertMultiPaginationResult(selectMultiPagination(param, funcCount, funcPagination, offset, limit, orderByArgs), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <P>
     *            파라미터 타입
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param pageable
     *            데이터 결과 Pagination 설정
     * @return
     *
     * @since 2022. 1. 10.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, P> Result<Page<E>> selectMultiPagination(P param //
            , Function<P, Result<Integer>> funcCount //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination //
            , Pageable pageable) {
        return selectMultiPagination(param, funcCount, funcPagination, offset(pageable), limit(pageable), orderBy(pageable));
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcPagination
     *            Pagination 검색 함수
     * @param pageable
     *            데이터 결과 Pagination 설정
     * @return
     *
     * @since 2022. 1. 10.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D, P> Result<Page<D>> selectMultiPagination(P param //
            , Function<P, Result<Integer>> funcCount //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination //
            , Pageable pageable //
            , Function<E, D> converter) {
        return selectMultiPagination(param, funcCount, funcPagination, offset(pageable), limit(pageable), orderBy(pageable), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <P>
     *            파라미터 타입
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 1.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, P> Result<Page<E>> selectMultiPagination(P param //
            , Supplier<Result<Integer>> funcCount //
            , TripleFunction<P, Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
    ) {
        // #1-1. 데이터 조회
        Supplier<Result<List<E>>> data = () -> funcPagination.apply(param, offset, limit);
        // #1-2. 개수 조회
        Supplier<Result<Integer>> count = () -> funcCount.get();

        return executePagination(data, count, offset, limit, null);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 1.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D, P> Result<Page<D>> selectMultiPagination(P param //
            , Supplier<Result<Integer>> funcCount //
            , TripleFunction<P, Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , Function<E, D> converter) {
        return convertMultiPaginationResult(selectMultiPagination(param, funcCount, funcPagination, offset, limit), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, P> Result<Page<E>> selectMultiPagination(SearchResultType type //
            , P param //
            , Function<P, Result<Integer>> funcCount //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String... orderByArgs) {

        // #1-1. 데이터 조회
        Supplier<Result<List<E>>> data = () -> {
            switch (type) {
                case ALL:
                    return funcAll.apply(param, orderByArgs);
                case PAGINATION:
                    return funcPagination.apply(param, offset, limit, orderByArgs);
                default:
                    throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
            }
        };

        // #1-2. 개수 조회
        Supplier<Result<Integer>> count = () -> funcCount.apply(param);

        return executePagination(data, count, offset, limit, orderByArgs);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D, P> Result<Page<D>> selectMultiPagination(SearchResultType type //
            , P param //
            , Function<P, Result<Integer>> funcCount //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination//
            , int offset//
            , int limit //
            , String[] orderByArgs //
            , Function<E, D> converter) {
        return convertMultiPaginationResult(selectMultiPagination(type, param, funcCount, funcAll, funcPagination, offset, limit, orderByArgs), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param pageable
     *            데이터 결과 Pagination 설정
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D, P> Result<Page<D>> selectMultiPagination(SearchResultType type //
            , P param //
            , Function<P, Result<Integer>> funcCount //
            , BiFunction<P, String[], Result<List<E>>> funcAll //
            , QuadFunction<P, Integer, Integer, String[], Result<List<E>>> funcPagination //
            , Pageable pageable //
            , Function<E, D> converter) {

        if (SearchResultType.ALL.equals(type)) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE, pageable.getSort());
        }

        return selectMultiPagination(type, param, funcCount, funcAll, funcPagination, offset(pageable), limit(pageable), orderBy(pageable), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, P> Result<Page<E>> selectMultiPagination(SearchResultType type //
            , P param //
            , Supplier<Result<Integer>> funcCount //
            , Function<P, Result<List<E>>> funcAll //
            , TripleFunction<P, Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
    ) {
        // #1-1. 데이터 조회
        Supplier<Result<List<E>>> data = () -> {
            switch (type) {
                case ALL:
                    return funcAll.apply(param);
                case PAGINATION:
                    return funcPagination.apply(param, offset, limit);
                default:
                    throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
            }
        };

        // #1-2. 개수 조회
        Supplier<Result<Integer>> count = () -> funcCount.get();

        return executePagination(data, count, offset, limit, null);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param <P>
     *            파라미터 타입
     * @param type
     *            검색 유형.
     * @param param
     *            검색 파라미터
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D, P> Result<Page<D>> selectMultiPagination(SearchResultType type //
            , P param //
            , Supplier<Result<Integer>> funcCount //
            , Function<P, Result<List<E>>> funcAll //
            , TripleFunction<P, Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , Function<E, D> converter) {
        return convertMultiPaginationResult(selectMultiPagination(type, param, funcCount, funcAll, funcPagination, offset, limit), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.    박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param type
     *            검색 유형.
     * @param funcCount
     *            데이터 개수 제공 함수
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E> Result<Page<E>> selectMultiPagination(SearchResultType type //
            , Supplier<Result<Integer>> funcCount //
            , Function<String[], Result<List<E>>> funcAll //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String... orderByArgs) {

        // #1-1. 데이터 조회
        Supplier<Result<List<E>>> data = () -> {
            switch (type) {
                case ALL:
                    return funcAll.apply(orderByArgs);
                case PAGINATION:
                    return funcPagination.apply(offset, limit, orderByArgs);
                default:
                    throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
            }
        };

        // #1-2. 개수 조회
        Supplier<Result<Integer>> count = () -> funcCount.get();

        return executePagination(data, count, offset, limit, orderByArgs);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param type
     *            검색 유형.
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D> Result<Page<D>> selectMultiPagination(SearchResultType type //
            , Supplier<Result<Integer>> funcCount //
            , Function<String[], Result<List<E>>> funcAll //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String[] orderByArgs //
            , Function<E, D> converter) {
        return convertMultiPaginationResult(selectMultiPagination(type, funcCount, funcAll, funcPagination, offset, limit, orderByArgs), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param type
     *            검색 유형.
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param pageable
     *            데이터 결과 Pagination 설정
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D> Result<Page<D>> selectMultiPagination(SearchResultType type //
            , Supplier<Result<Integer>> funcCount //
            , Function<String[], Result<List<E>>> funcAll //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination //
            , Pageable pageable //
            , Function<E, D> converter) {

        if (SearchResultType.ALL.equals(type)) {
            pageable = PageRequest.of(0, Integer.MAX_VALUE, pageable.getSort());
        }

        return selectMultiPagination(type, funcCount, funcAll, funcPagination, offset(pageable), limit(pageable), orderBy(pageable), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param type
     *            검색 유형.
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E> Result<Page<E>> selectMultiPagination(SearchResultType type //
            , Supplier<Result<Integer>> funcCount //
            , Supplier<Result<List<E>>> funcAll //
            , BiFunction<Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
    ) {

        // #1-1. 데이터 조회
        Supplier<Result<List<E>>> data = () -> {
            switch (type) {
                case ALL:
                    return funcAll.get();
                case PAGINATION:
                    return funcPagination.apply(offset, limit);
                default:
                    throw new UnsupportedOperationException(String.format("지원하지 않음. type=%s", type.get()));
            }
        };

        // #1-2. 개수 조회
        Supplier<Result<Integer>> count = () -> funcCount.get();

        return executePagination(data, count, offset, limit, null);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.     박준홍         최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param type
     *            검색 유형.
     * @param funcCount
     *            데이터 개수 제공 함수
     * @param funcAll
     *            전체 검색 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    protected <E, D> Result<Page<D>> selectMultiPagination(SearchResultType type //
            , Supplier<Result<Integer>> funcCount //
            , Supplier<Result<List<E>>> funcAll //
            , BiFunction<Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , Function<E, D> converter) {
        return convertMultiPaginationResult(selectMultiPagination(type, funcCount, funcAll, funcPagination, offset, limit), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 1.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E> Result<Page<E>> selectMultiPagination( //
            Supplier<Result<Integer>> funcCount //
            , BiFunction<Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
    ) {

        // #1-1. 데이터 조회
        Supplier<Result<List<E>>> data = () -> funcPagination.apply(offset, limit);
        // #1-2. 개수 조회
        Supplier<Result<Integer>> count = () -> funcCount.get();

        return executePagination(data, count, offset, limit, null);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param funcCount
     *            데이터 개수 제공 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 1.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D> Result<Page<D>> selectMultiPagination(Supplier<Result<Integer>> funcCount //
            , BiFunction<Integer, Integer, Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , Function<E, D> converter) {
        return convertMultiPaginationResult(selectMultiPagination(funcCount, funcPagination, offset, limit), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param funcCount
     *            데이터 개수 제공 함수
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @return
     *
     * @since 2022. 1. 10.
     * @version 1.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E> Result<Page<E>> selectMultiPagination(Supplier<Result<Integer>> funcCount //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String... orderByArgs) {

        // #1-1. 데이터 조회
        Supplier<Result<List<E>>> data = () -> funcPagination.apply(offset, limit, orderByArgs);
        // #1-2. 개수 조회
        Supplier<Result<Integer>> count = () -> funcCount.get();

        return executePagination(data, count, offset, limit, orderByArgs);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcPagination
     *            Pagination 검색 함수
     * @param offset
     *            데이터를 읽기 위한 시작 위치 (0부터 시작)
     * @param limit
     *            읽을 데이터 개수
     * @param orderByArgs
     *            정렬 기준.<br>
     *            <b>데이터 정의</b><br>
     *            <li>포맷: {column} {direction}<br>
     *            <li>예: name asc
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 1.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D> Result<Page<D>> selectMultiPagination(Supplier<Result<Integer>> funcCount //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination //
            , int offset//
            , int limit //
            , String[] orderByArgs //
            , Function<E, D> converter) {
        return convertMultiPaginationResult(selectMultiPagination(funcCount, funcPagination, offset, limit, orderByArgs), converter);
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 10.		박준홍			최초 작성
     * </pre>
     *
     * @param <E>
     *            Table Entity 타입
     * @param <D>
     *            DTO 타입
     * @param funcCount
     *            데이터 개수 제공 함수.
     * @param funcPagination
     *            Pagination 검색 함수
     * @param pageable
     *            데이터 결과 Pagination 설정
     * @param converter
     *            Entity -> DTO 변환 함수
     * @return
     *
     * @since 2022. 1. 10.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected <E, D> Result<Page<D>> selectMultiPagination(Supplier<Result<Integer>> funcCount //
            , TripleFunction<Integer, Integer, String[], Result<List<E>>> funcPagination //
            , Pageable pageable //
            , Function<E, D> converter) {
        return selectMultiPagination(funcCount, funcPagination, offset(pageable), limit(pageable), orderBy(pageable), converter);
    }

    /**
     * {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcObj
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetType
     *            데이터를 전달받은 객체 타입
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2021. 12. 22.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     * 
     * @see ObjectUtils#getTransformer(Object, boolean, Class, boolean)
     */
    protected static <S, T> T transform(S srcObj, boolean lookupSrcSupper, Class<T> targetType, boolean lookupTargetSupper) {
        return ObjectUtils.getTransformer(srcObj, lookupSrcSupper, targetType, lookupTargetSupper).apply(srcObj);
    }

    /**
     * {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcObj
     *            입력 데이타
     * @param lookupSrcSuper
     *            입력 데이타 클래스 상위 인터페이스/클래스 확장 여부
     * @param targetObj
     *            데이터를 전달받은 객체 타입
     * @param lookupTargetSuper
     *            대상 객체 상위 인터페이스/클래스 확장 여부
     * @return
     *
     * @since 2022. 1. 26.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected static <S, T> T transform(S srcObj, boolean lookupSrcSupper, T targetObj, boolean lookupTargetSupper) {
        return ObjectUtils.getTransformer(srcObj, lookupSrcSupper, targetObj, lookupTargetSupper).apply(srcObj);
    }

    /**
     * {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 22.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcObj
     *            입력 데이타
     * @param targetType
     *            데이터를 전달받은 객체 타입.
     * @return
     *
     * @since 2021. 12. 22.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     * 
     * @see #transform(Object, boolean, Class, boolean)
     */
    protected static <S, T> T transform(S srcObj, Class<T> targetType) {
        return transform(srcObj, true, targetType, true);
    }

    /**
     * {@link Getter}, {@link Setter} 어노테이션이 적용된 객체를 변환하여 새로운 타입의 객체로 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2022. 1. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param <S>
     *            입력 데이터 타입 정의.
     * @param <T>
     *            신규 데이터 타입 정의.
     * @param srcObj
     *            입력 데이타
     * @param targetObj
     *            데이터를 전달받은 객체.
     * @return
     *
     * @since 2022. 1. 26.
     * @version 0.4.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected static <S, T> T transform(S srcObj, T targetObj) {
        return transform(srcObj, true, targetObj, true);
    }
}
