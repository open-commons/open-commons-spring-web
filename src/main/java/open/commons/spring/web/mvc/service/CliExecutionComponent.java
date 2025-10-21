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
 * Date  : 2021. 11. 16. 오후 1:24:58
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.web.mvc.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import open.commons.core.Result;
import open.commons.core.utils.IOUtils;

/**
 * CLI(Command Line Interface) 관련 기능을 제공하는 콤포넌드.
 * 
 * @since 2021. 11. 16.
 * @version 0.4.0
 * @author parkjunhong77@gmail.com
 */
public abstract class CliExecutionComponent extends AbstractComponent {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 11. 16.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     *
     * @since 2021. 11. 16.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     */
    public CliExecutionComponent() {
    }

    /**
     * 작업 명령을 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param cmdarray
     *            명령줄
     * @param workingDir
     *            작업 디렉토리
     * @param job
     *            작업명
     * @return
     * @throws IOException
     *
     * @since 2023. 11. 7.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Result<Boolean> executeNoWait(String[] cmdarray, File workingDir, String job) {
        return executeNoWait(cmdarray, null, workingDir, job);
    }

    /**
     * 작업 명령을 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2021. 11. 16.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param cmdarray
     *            명령줄
     * @param job
     *            작업명
     * @return
     *
     * @since 2021. 11. 16.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     * 
     * @see #executeNoWait(String[], String[], File, String)
     */
    protected Result<Boolean> executeNoWait(String[] cmdarray, String job) throws IOException {
        return executeNoWait(cmdarray, null, null, job);
    }

    /**
     * 작업 명령을 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param cmdarray
     *            명령줄
     * @param env
     *            환경 변수
     * @param workingDir
     *            작업 디렉토리
     * @param job
     *            작업명
     * @return
     * @throws IOException
     *
     * @since 2023. 11. 7.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #executeNoWait(String[], String[], File, String)
     */
    protected Result<Boolean> executeNoWait(String[] cmdarray, String[] env, File workingDir, String job) {
        return execute(() -> {
            try {
                Runtime.getRuntime().exec(cmdarray, env, workingDir);
                return Result.success(true);
            } catch (Exception e) {
                String errMsg = String.format("작업('%s')을/를 실패하였습니다. 명령어=%s, 환경변수=%s, 작업디렉톨=%s, 원인=%s", job, Arrays.toString(cmdarray), Arrays.toString(env), workingDir,
                        e.getMessage());
                logger.error(errMsg, e);
                return Result.error(errMsg);
            }
        }, job);
    }

    /**
     * 작업 명령을 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param cmdarray
     *            명령줄
     * @param env
     *            환경 변수
     * @param job
     *            작업명
     * @return
     * @throws IOException
     *
     * @since 2023. 11. 7.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #executeNoWait(String[], String[], File, String)
     */
    protected Result<Boolean> executeNoWait(String[] cmdarray, String[] env, String job) {
        return executeNoWait(cmdarray, env, null, job);
    }

    /**
     * Host 환경에서 명령어를 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param cmdarray
     *            명령줄
     * @param workingDir
     *            작업 디렉토리
     * @param job
     *            작업명
     * @return
     * @throws IOException
     *
     * @since 2023. 11. 7.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #executeWaitFor(String[], String[], File, String)
     */
    protected Result<Integer> executeWaitFor(String[] cmdarray, File workingDir, String job) {
        return executeWaitFor(cmdarray, null, workingDir, job);
    }

    /**
     * Host 환경에서 명령어를 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 11. 7.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param cmdarray
     *            명령줄
     * @param job
     *            작업명
     * @return
     * @throws IOException
     *
     * @since 2023. 11. 7.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #executeWaitFor(String[], String[], File, String)
     */
    protected Result<Integer> executeWaitFor(String[] cmdarray, String job) {
        return executeWaitFor(cmdarray, null, null, job);
    }

    /**
     * Host 환경에서 명령어를 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 7.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param cmdarray
     *            명령줄
     * @param env
     *            환경 변수
     * @param workingDir
     *            작업 디렉토리
     * @param job
     *            작업명
     * @return
     * @throws IOException
     *
     * @since 2023. 11. 7.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see Runtime#exec(String[], String[], File)
     */
    protected Result<Integer> executeWaitFor(String[] cmdarray, String[] env, File workingDir, String job) {
        return execute(() -> {
            Process proc = null;
            BufferedReader reader = null;
            try {
                proc = Runtime.getRuntime().exec(cmdarray, env, workingDir);

                reader = IOUtils.getReader(proc.getInputStream());
                String readline = null;
                while ((readline = reader.readLine()) != null) {
                    if (readline.trim().isEmpty())
                        continue;
                    logger.debug("{}", readline);
                }
                proc.waitFor();

                return Result.success(proc.exitValue());
            } catch (Exception e) {
                String errMsg = String.format("작업('%s')을/를 실패하였습니다. 명령어=%s, 환경변수=%s, 작업디렉톨=%s, 원인=%s", job, Arrays.toString(cmdarray), Arrays.toString(env), workingDir,
                        e.getMessage());
                logger.error(errMsg, e);
                int exitCode = -1;
                if (proc != null) {
                    proc.exitValue();
                }
                return error(exitCode, e.getMessage());
            }
        }, job);
    }

    /**
     * Host 환경에서 명령어를 실행합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2023. 11. 7.     parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param cmdarray
     *            명령줄
     * @param env
     *            환경 변수
     * @param job
     *            작업명
     * @return
     * @throws IOException
     *
     * @since 2023. 11. 7.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     * 
     * @see #executeWaitFor(String[], String[], File, String)
     */
    protected Result<Integer> executeWaitFor(String[] cmdarray, String[] env, String job) {
        return executeWaitFor(cmdarray, env, null, job);
    }
}
