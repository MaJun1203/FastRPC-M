package com.xiaoma.marpc.server;

import io.vertx.core.Vertx;

/**
 * Class Name: VertxHttpServer
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public class VertxHttpServer implements HttpServer{
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();

        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
        server.requestHandler(new HttpServerHandler());

        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.out.println("Failed to bind server: " + result.cause());
            }
        });
    }
}
