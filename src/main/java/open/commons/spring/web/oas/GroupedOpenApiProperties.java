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
 * Date  : 2025. 4. 8. 오후 5:12:16
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.oas;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springdoc.core.GroupedOpenApi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;

/**
 * {@link GroupedOpenApi} 객체를 외부 설정을 통해서 생성하기 위한 클래스.
 * 
 * @since 2025. 4. 8.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 * 
 * @see GroupedOpenApi.Builder
 */
public class GroupedOpenApiProperties {

    /**
     * The Group.<br>
     * binding: {@link GroupedOpenApi.Builder#group(String)}
     */
    private String group;

    /**
     * The Display name.<br>
     * binding: {@link GroupedOpenApi.Builder#displayName(String)}
     */
    private String displayName;

    /**
     * The Packages to scan.<br>
     * binding: {@link GroupedOpenApi.Builder#packagesToScan(String...)}
     */
    private List<String> packagesToScan;

    /**
     * The Packages to exclude.<br>
     * binding: {@link GroupedOpenApi.Builder#packagesToExclude(String...)}
     */
    private List<String> packagesToExclude;

    /**
     * The Paths to match.<br>
     * binding: {@link GroupedOpenApi.Builder#pathsToMatch(String...)}
     */
    private List<String> pathsToMatch;

    /**
     * The Paths to exclude.<br>
     * binding: {@link GroupedOpenApi.Builder#pathsToExclude(String...)}
     */
    private List<String> pathsToExclude;

    /**
     * The Headers to match.<br>
     * binding: {@link GroupedOpenApi.Builder#headersToMatch(String...)}
     */
    private List<String> headersToMatch;

    /**
     * The Produces to match.<br>
     * binding: {@link GroupedOpenApi.Builder#producesToMatch(String...)}
     */
    private List<String> producesToMatch;

    /**
     * The Consumes to match.<br>
     * binding: {@link GroupedOpenApi.Builder#consumesToMatch(String...)}
     */
    private List<String> consumesToMatch;

    /**
     * API Info.<br>
     * binding: {@link GroupedOpenApi.Builder#addOpenApiCustomiser(org.springdoc.core.customizers.OpenApiCustomiser)}
     */
    private Info info;

    /**
     * External Documentation.<br>
     * binding: {@link GroupedOpenApi.Builder#addOpenApiCustomiser(org.springdoc.core.customizers.OpenApiCustomiser)}
     */
    private ExternalDocumentation externalDocs;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public GroupedOpenApiProperties() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the consumesToMatch
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #consumesToMatch
     */

    public String[] getConsumesToMatch() {
        return consumesToMatch != null //
                ? consumesToMatch.toArray(new String[0]) //
                : new String[0];
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the displayName
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #displayName
     */

    public String getDisplayName() {
        return displayName;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the externalDocs
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #externalDocs
     */

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the group
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #group
     */

    public String getGroup() {
        return group;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the headersToMatch
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #headersToMatch
     */

    public String[] getHeadersToMatch() {
        return headersToMatch != null //
                ? headersToMatch.toArray(new String[0]) //
                : new String[0];
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the info
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #info
     */

    public Info getInfo() {
        return info;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the packagesToExclude
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #packagesToExclude
     */

    public String[] getPackagesToExclude() {
        return packagesToExclude != null //
                ? packagesToExclude.toArray(new String[0]) //
                : new String[0];
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the packagesToScan
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #packagesToScan
     */

    public String[] getPackagesToScan() {
        return packagesToScan != null //
                ? packagesToScan.toArray(new String[0]) //
                : new String[0];
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the pathsToExclude
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #pathsToExclude
     */

    public String[] getPathsToExclude() {
        return pathsToExclude != null //
                ? pathsToExclude.toArray(new String[0]) //
                : new String[0];
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the pathsToMatch
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #pathsToMatch
     */

    public String[] getPathsToMatch() {
        return pathsToMatch != null //
                ? pathsToMatch.toArray(new String[0]) //
                : new String[0];
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     * 
     * @return the producesToMatch
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #producesToMatch
     */

    public String[] getProducesToMatch() {
        return producesToMatch != null //
                ? producesToMatch.toArray(new String[0]) //
                : new String[0];
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param consumesToMatch
     *            the consumesToMatch to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #consumesToMatch
     */
    public void setConsumesToMatch(List<String> consumesToMatch) {
        this.consumesToMatch = consumesToMatch;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param displayName
     *            the displayName to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #displayName
     */
    public void setDisplayName(@NotEmpty String displayName) {
        this.displayName = displayName;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param externalDocs
     *            the externalDocs to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #externalDocs
     */
    public void setExternalDocs(ExternalDocumentation externalDocumentation) {
        this.externalDocs = externalDocumentation;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param group
     *            the group to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #group
     */
    public void setGroup(@NotEmpty String group) {
        this.group = group;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param headersToMatch
     *            the headersToMatch to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #headersToMatch
     */
    public void setHeadersToMatch(List<String> headersToMatch) {
        this.headersToMatch = headersToMatch;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param info
     *            the info to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #info
     */
    public void setInfo(Info info) {
        this.info = info;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param packagesToExclude
     *            the packagesToExclude to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #packagesToExclude
     */
    public void setPackagesToExclude(List<String> packagesToExclude) {
        this.packagesToExclude = packagesToExclude;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param packagesToScan
     *            the packagesToScan to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #packagesToScan
     */
    public void setPackagesToScan(List<String> packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param pathsToExclude
     *            the pathsToExclude to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #pathsToExclude
     */
    public void setPathsToExclude(List<String> pathsToExclude) {
        this.pathsToExclude = pathsToExclude;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param pathsToMatch
     *            the pathsToMatch to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #pathsToMatch
     */
    public void setPathsToMatch(List<String> pathsToMatch) {
        this.pathsToMatch = pathsToMatch;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 4. 8.		박준홍			최초 작성
     * </pre>
     *
     * @param producesToMatch
     *            the producesToMatch to set
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #producesToMatch
     */
    public void setProducesToMatch(List<String> producesToMatch) {
        this.producesToMatch = producesToMatch;
    }

    /**
     *
     * @since 2025. 4. 8.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GroupedOpenApiProperties [group=");
        builder.append(group);
        builder.append(", displayName=");
        builder.append(displayName);
        builder.append(", packagesToScan=");
        builder.append(packagesToScan);
        builder.append(", packagesToExclude=");
        builder.append(packagesToExclude);
        builder.append(", pathsToMatch=");
        builder.append(pathsToMatch);
        builder.append(", pathsToExclude=");
        builder.append(pathsToExclude);
        builder.append(", headersToMatch=");
        builder.append(headersToMatch);
        builder.append(", producesToMatch=");
        builder.append(producesToMatch);
        builder.append(", consumesToMatch=");
        builder.append(consumesToMatch);
        builder.append(", info=");
        builder.append(info);
        builder.append(", externalDocs=");
        builder.append(externalDocs);
        builder.append("]");
        return builder.toString();
    }

}
