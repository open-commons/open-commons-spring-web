/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2020. 11. 26. 오후 5:33:33
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.spring.web.mvc.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Value;

import open.commons.core.Result;
import open.commons.core.function.TripleFunction;
import open.commons.core.utils.MapUtils;
import open.commons.spring.web.servlet.InternalServerException;
import open.commons.ssh.SshConnection;
import open.commons.ssh.file.FileTransfer;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.Session;

/**
 * SSH기반의 통신 기능을 제공한다.
 * 
 * @since 2020. 11. 26.
 * @version 0.4.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class AbstractSshService extends AbstractGenericService {

    /**
     * {@link SshConnection} 식별정보 제공 함수.
     * <ul>
     * <li>user: username
     * <li>host: Host IP or Domain Name
     * <li>port: Port
     * </ul>
     */
    protected static final TripleFunction<String, String, Integer, String> SSH_CONNECTION_KEY_GEN = (user, host, port) -> {
        return String.join(":", user, host, String.valueOf(port));
    };

    /** {@link Session} 생성을 위한 Mutex 객체 */
    protected final ReentrantLock mutexSession = new ReentrantLock();

    /**
     * 접속 서버별로 관리되는 {@link Session} 정보.
     * <ul>
     * <li>key: sessin key
     * <li>value: {@link Session} instance
     * </ul>
     */
    protected ConcurrentSkipListMap<String, SshConnection> sessions = new ConcurrentSkipListMap<>();

    /**
     * SSH 접속대기 제한시간. 단위: ms
     */
    @Value("${application.ssh.connect-timeout:10000}")
    protected int connectTimeout;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 11. 26.        박준홍         최초 작성
     * </pre>
     *
     * @since 2020. 11. 26.
     */
    public AbstractSshService() {
    }

    /**
     * 파일을 다운로드 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @param srcPath
     *            작업 대상 파일경로 (절대경로)
     * @param dstOutput
     *            작업 결과 데이터
     * @param autoClose
     *            {@link OutputStream} 형태의 결과 데이터 자동 종료 여부
     * @return
     *
     * @since 2025. 7. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Result<Boolean> download(@NotBlank String host, @Min(1) int port, @NotBlank String username, @NotBlank String password, @NotBlank String srcPath,
            OutputStream dstOutput, boolean autoClose) {

        Result<Boolean> resultExistSrcFile = existsRemoteFile(host, port, username, password, srcPath);

        if (resultExistSrcFile.isSuccess()) {
            if (resultExistSrcFile.getResult()) {
                return transfer(host, port, username, password, sftp -> {
                    try {
                        return sftp.download(srcPath, dstOutput, autoClose);
                    } catch (Exception e) {
                        String errMsg = String.format("파일 다운로드(%s)를 실패하였습니다. 원인=%s", srcPath, e.getMessage());
                        logger.error("{}", errMsg, e);
                        return Result.error(errMsg);
                    }
                }, false);
            } else {
                logger.warn("{}", resultExistSrcFile.getMessage());
                return resultExistSrcFile;
            }
        } else {
            return Result.copyOf(resultExistSrcFile);
        }
    }

    /**
     * 파일을 다운로드 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 20.		박준홍			최초 작성
     * </pre>
     * 
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @param srcPath
     *            작업 대상 파일경로 (절대경로)
     * @param dstPath
     *            작업 결과 파일경로 (절대경로)
     *
     * @return
     *
     * @since 2023. 11. 20.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Result<Boolean> download(@NotBlank String host, @Min(1) int port, @NotBlank String username, @NotBlank String password, @NotBlank String srcPath,
            @NotBlank String dstPath) {
        try (OutputStream out = new FileOutputStream(dstPath);) {
            return download(host, port, username, password, srcPath, out, true);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 원격지에 파일이 존재하는지 여부를 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @param srcFile
     *            작업 대상 파일경로 (절대경로)
     * @return
     *
     * @since 2025. 7. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected final Result<Boolean> existsRemoteFile(@NotBlank String host, @Min(1) int port, @NotBlank String username, @NotBlank String password, @NotBlank String srcFile) {
        try ( //
                FileTransfer sftp = new FileTransfer(getConnection(username, password, host, port));
        //
        ) {
            // #1. 대상파일 존재 확인
            Result<List<LsEntry>> resultList = sftp.list(srcFile);
            if (resultList.isError()) {
                // 연결실패 등의 에러
                String errMsg = String.format("[%s:%s] %s => %s", host, port, resultList.getMessage(), srcFile);
                return Result.error(errMsg);
            } else if (resultList.getData().isEmpty()) {
                // 파일이 없는 경우
                return Result.success(false).setMessage("[%s:%s] No File. => %s", host, port, srcFile);
            } else {
                return Result.success(true);
            }
        } catch (Exception e) {
            String errMsg = String.format("'%s' 파일 존재 확인 중 오류가 발생하였습니다. 원인=%s", srcFile, e.getMessage());
            logger.error("[{}] {}", errMsg, e);
            return Result.error(errMsg);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 20.		박준홍			최초 작성
     * </pre>
     *
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @return
     *
     * @since 2023. 11. 20.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected SshConnection getConnection(String username, String password, String host, int port) {
        ReentrantLock lock = this.mutexSession;
        try {
            lock.lock();
            Supplier<SshConnection> supplier = () -> new SshConnection(username, password, host, port);
            return MapUtils.getOrDefault(this.sessions, SSH_CONNECTION_KEY_GEN.apply(username, host, port), supplier, true);
        } catch (Exception e) {
            logger.error("SSH 세션 생성 도중 에러가 발생하였습니다. username={}, host={}, port={}, 원인={}", username, host, port, e.getMessage(), e);
            throw new InternalServerException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 디렉토리 내 목록을 제공합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @param dir
     *            디렉토리 경로
     * @return
     *
     * @since 2025. 7. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected final Result<List<LsEntry>> list(@NotBlank String host, @Min(1) int port, @NotBlank String username, @NotBlank String password, @NotBlank String dir) {
        try ( //
                FileTransfer sftp = new FileTransfer(getConnection(username, password, host, port));
        //
        ) {
            // #1. 대상파일 존재 확인
            return sftp.list(dir);
        } catch (Exception e) {
            String errMsg = String.format("디렉토리(%s)내 목록을 조회하는 도중 오류가 발생하였습니다. 원인=%s", dir, e.getMessage());
            logger.error("[{}] {}", errMsg, e);
            return Result.error(errMsg);
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @param action
     *            파일 전송 작업.
     * @param isUpload
     *            업로드 여부
     * @return
     *
     * @since 2025. 7. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Result<Boolean> transfer(@NotBlank String host, @Min(1) int port, @NotBlank String username, @NotBlank String password,
            Function<FileTransfer, Result<Boolean>> action, boolean isUpload) {
        return execute(() -> {
            try ( //
                    FileTransfer sftp = new FileTransfer(getConnection(username, password, host, port));
            //
            ) {
                return action.apply(sftp);
            } catch (Exception e) {
                return Result.error(e.getMessage());
            }
        }, "파일 " + (isUpload ? "업로드" : "다운로드"));
    }

    /**
     * 파일을 업로드 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @param srcInput
     *            입력 데이터
     * @param dstPath
     *            작업 결과 파일경로 (절대경로)
     * @param autoClose
     *            {@link InputStream} 형태의 입력 데이터 자동 종료 여부
     * @return
     *
     * @since 2025. 7. 23.
     * @version 0.8.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Result<Boolean> upload(@NotBlank String host, @Min(1) int port, @NotBlank String username, @NotBlank String password, InputStream srcInput, @NotBlank String dstPath,
            boolean autoClose) {
        return transfer(host, port, username, password, sftp -> {
            return sftp.upload(srcInput, dstPath, autoClose);
        }, true);
    }

    /**
     * 파일을 업로드 합니다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2023. 11. 20.		박준홍			최초 작성
     * </pre>
     * 
     * @param host
     *            sftp host
     * @param port
     *            sftp port
     * @param username
     *            sftp 접속 계정
     * @param password
     *            sftp 접속 비밀번호
     * @param srcPath
     *            작업 대상 파일경로 (절대경로)
     * @param dstPath
     *            작업 결과 파일경로 (절대경로)
     * @return
     *
     * @since 2023. 11. 20.
     * @version 0.7.0
     * @author Park, Jun-Hong parkjunhong77@gmail.com
     */
    protected Result<Boolean> upload(@NotBlank String host, @Min(1) int port, @NotBlank String username, @NotBlank String password, String srcPath, @NotBlank String dstPath) {
        // #1. 대상파일 존재 확인
        if (!Files.exists(Paths.get(srcPath))) {
            // 파일이 없는 경우
            return Result.success(false);
        }

        try (FileInputStream input = new FileInputStream(srcPath);) {
            return upload(host, port, username, password, input, dstPath, true);
        } catch (Exception e) {
            String errMsg = String.format("파일 업로드(%s)를 실패하였습니다. 원인=%s", srcPath, e.getMessage());
            logger.error("{}", errMsg, e);
            return Result.error(errMsg);
        }
    }
}
