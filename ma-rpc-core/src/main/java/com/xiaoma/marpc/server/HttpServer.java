package com.xiaoma.marpc.server;

/**
 * Class Name: HttpServer
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public interface HttpServer {
    /**
     * 启动服务
     *
     * @param port 端口号
     */
    void doStart(int port);
}
