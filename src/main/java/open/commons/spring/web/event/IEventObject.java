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
 * Date  : 2021. 9. 9. 오전 11:09:38
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.event;

/**
 * 이벤트 객체 기능 정의.
 * 
 * @param <T>
 *            이벤트 정보.
 * @param <E>
 *            이벤트 상태 정보.
 * @version 0.4.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public interface IEventObject<T, E extends IEventStatus> extends Cloneable {

    /**
     * 해당 객체와 동일한 정보를 가진 객체를 제공한다.
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 9. 9.      박준홍         최초 작성
     * </pre>
     *
     * @return
     * @throws CloneNotSupportedException
     *
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException;

    /**
     * 이벤트를 발생시킨 데이터를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 9.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public T getSource();

    /**
     * 이벤트 상세타입 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 9.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public E getType();

}
