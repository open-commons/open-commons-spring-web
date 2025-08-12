/*
 * Copyright 2022 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2022. 2. 10. 오후 5:26:38
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

import open.commons.core.utils.ExceptionUtils;
import open.commons.spring.web.mvc.service.AbstractMvcService;

/**
 * 검색결과 Pagination 기능 제공 클래스.
 * 
 * @since 2022. 2. 10.
 * @version 0.4.0
 * @author parkjunhong77@gmail.com
 */
public class PaginationUtils {

    private PaginationUtils() {
    }

    /**
     * {@link Pageable}에서 읽을 데이터 개수를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * 2022. 2. 10.     박준홍     {@link AbstractMvcService}.limit(Pageable)에서 이관시킴.
     * </pre>
     *
     * @param pageable
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    public static int limit(@NotNull Pageable pageable) {
        return pageable.getPageSize();
    }

    /**
     * {@link Pageable}에서 데이터 시작위치를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * 2022. 2. 10.     박준홍     {@link AbstractMvcService}.offset(Pageable)에서 이관시킴.
     * </pre>
     *
     * @param pageable
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     */
    public static int offset(@NotNull Pageable pageable) {
        return pageable.getPageNumber() * pageable.getPageSize();
    }

    /**
     * {@link Pageable}에서 쿼리 조회 결과 정렬 기준을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 9.     박준홍         최초 작성
     * 2022. 2. 10.     박준홍     {@link AbstractMvcService}.orderBy(Pageable)에서 이관시킴.
     * </pre>
     *
     * @param pageable
     * @return
     *
     * @since 2021. 12. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong (parkjunhong77@gmail.com)
     * 
     * @see #orderBy(String...)
     */
    public static String[] orderBy(@NotNull Pageable pageable) {
        return pageable.getSort().stream() //
                .map(sort -> String.join(" ", sort.getProperty(), sort.getDirection().toString())) //
                .collect(Collectors.toList())//
                .toArray(new String[0]);
    }

    /**
     * DBMS 정렬쿼리를 Pagination 정렬 조건으로 변환하여 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 12. 28.        박준홍         최초 작성
     * 2022. 2. 10.     박준홍     {@link AbstractMvcService}.orderBy(String...)에서 이관시킴.
     * </pre>
     *
     * @param orderByArgs
     * @return
     *
     * @since 2021. 12. 28.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     * 
     * @see #orderBy(Pageable)
     */
    public static List<Order> orderBy(String... orderByArgs) {
        return orderByArgs != null //
                ? Stream.of(orderByArgs).map(orderBy -> {
                    String[] strs = orderBy.split(" ");
                    if (strs.length == 1) {
                        return Order.asc(strs[0]);
                    } else {
                        switch (strs[1].toLowerCase()) {
                            case "asc":
                                return Order.asc(strs[0]);
                            case "desc":
                                return Order.desc(strs[0]);
                            default:
                                throw ExceptionUtils.newException(UnsupportedOperationException.class, "지원하지 않는 정보입니다. 허용=(asc,desc), 입력=%s", strs[1]);
                        }
                    }
                }).collect(Collectors.toList()) //
                : new ArrayList<>() //
        ;
    }

}
