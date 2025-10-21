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
 * Date  : 2025. 9. 29. 오후 4:26:17
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.beans.controller;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

/**
 * 
 * @since 2025. 9. 29.
 * @version 0.8.0
 * @author parkjunhong77@gmail.com
 */
public class RestApiDecl {

    /**
     * REST API 이름<br>
     * value: {@link Operation#summary()}에 해당하는 값.
     */
    @NotBlank
    private String name;
    /**
     * REST API 설명<br>
     * value: {@link Operation#description()}에 해당하는 값.
     */
    private String description;
    /**
     * REST API Method<br>
     * value: {@link RequestMapping#method()}에 해당하는 값.
     */
    @NotBlank
    private RequestMethod method;

    /**
     * REST API Path<br>
     * value: {@link RequestMapping#path()}에 해당하는 값.
     */
    @NotBlank
    private String path;

    /**
     * {@link Controller}, {@link RestController} 클래스에 선언된 {@link RequestMapping#path()} 값
     */
    private String classPath;

    /**
     * {@link Controller}, {@link RestController} 클래스의 메소드에 선언된 'path' 값.
     * <li>{@link DeleteMapping}
     * <li>{@link GetMapping}
     * <li>{@link PatchMapping}
     * <li>{@link PostMapping}
     * <li>{@link PutMapping}
     * <li>{@link RequestMapping}
     */
    private String methodPath;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     */
    public RestApiDecl() {
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the classPath
     *
     * @since 2025. 10. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #classPath
     */

    public String getClassPath() {
        return classPath;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the description
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #description
     */

    public String getDescription() {
        return description;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the method
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #method
     */

    public RequestMethod getMethod() {
        return method;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the methodPath
     *
     * @since 2025. 10. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #methodPath
     */

    public String getMethodPath() {
        return methodPath;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the name
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #name
     */

    public String getName() {
        return name;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the path
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #path
     */

    public String getPath() {
        return path;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param classPath
     *            the classPath to set
     *
     * @since 2025. 10. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #classPath
     */
    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param description
     *            the description to set
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param method
     *            the method to set
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #method
     */
    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 10. 20.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param methodPath
     *            the methodPath to set
     *
     * @since 2025. 10. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #methodPath
     */
    public void setMethodPath(String methodPath) {
        this.methodPath = methodPath;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param name
     *            the name to set
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #name
     */
    public void setName(@NotBlank String name) {
        this.name = name;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 9. 29.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param path
     *            the path to set
     *
     * @since 2025. 9. 29.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see #path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @since 2025. 10. 20.
     * @version 0.8.0
     * @author parkjunhong77@gmail.com
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RestApiDecl [name=");
        builder.append(name);
        builder.append(", description=");
        builder.append(description);
        builder.append(", method=");
        builder.append(method);
        builder.append(", path=");
        builder.append(path);
        builder.append(", classPath=");
        builder.append(classPath);
        builder.append(", methodPath=");
        builder.append(methodPath);
        builder.append("]");
        return builder.toString();
    }
}
