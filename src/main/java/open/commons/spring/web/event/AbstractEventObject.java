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
 * Date  : 2021. 9. 9. 오전 11:11:43
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.event;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * @param <T>
 *            이벤트 정보.
 * @param <E>
 *            이벤트 상태 정보.
 * @since 2021. 9. 9.
 * @version 0.4.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public abstract class AbstractEventObject<T, E extends IEventStatus> extends ApplicationEvent implements IEventObject<T, E> {

    private static final long serialVersionUID = -1077392713769446688L;

    private static final SimpleDateFormat DF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /** 이벤트 타입 */
    protected final E type;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            이벤트 발생 정보.
     * @param type
     *            이벤트 상태 정보.
     *
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public AbstractEventObject(@NotNull Object source, @NotNull E type) {
        super(source);
        this.type = type;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 9. 9.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            이벤트 발생 정보.
     * @param type
     *            이벤트 상태 정보.
     * @param clock
     * @since 2021. 9. 9.
     * @version 0.4.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public AbstractEventObject(@NotNull Object source, @NotNull E type, @NotNull Clock clock) {
        super(source, clock);
        this.type = type;
    }

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
    public abstract Object clone() throws CloneNotSupportedException;

    /**
     * <br>
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
     *
     * @see java.util.EventObject#getSource()
     */
    @SuppressWarnings("unchecked")
    @Override
    public T getSource() {
        return (T) super.getSource();
    }

    /**
     * <br>
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
     *
     * @see open.commons.spring.web.event.IEventObject#getType()
     */
    @Override
    public E getType() {
        return this.type;
    }

    /**
     * <br>
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
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(" [source=");
        builder.append(source);
        builder.append(", type=");
        builder.append(type);
        builder.append(", date=");
        builder.append(DF.format(new Date(getTimestamp())));
        builder.append(", timestamp=");
        builder.append(getTimestamp());
        builder.append("]");
        return builder.toString();
    }
}
