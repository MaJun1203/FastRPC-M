package com.xiaoma.marpc.server.tcp;

import com.xiaoma.marpc.RpcApplication;
import com.xiaoma.marpc.model.RpcRequest;
import com.xiaoma.marpc.model.RpcResponse;
import com.xiaoma.marpc.protocol.ProtocolMessage;
import com.xiaoma.marpc.protocol.ProtocolMessageDecoder;
import com.xiaoma.marpc.protocol.ProtocolMessageEncoder;
import com.xiaoma.marpc.protocol.ProtocolMessageTypeEnum;
import com.xiaoma.marpc.registry.LocalRegistry;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Class Name: TcpServerHandler
 * Description:
 * Created on: 2025/5/12
 * Author: xiaoma
 */

public class TcpServerHandler implements Handler<NetSocket> {
    @Override
    public void handle(NetSocket socket) {
        TcpBufferHandlerWrapper bufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
            // 处理请求代码
        });
        socket.handler(bufferHandlerWrapper);
    }
}
