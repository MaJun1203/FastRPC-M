package com.xiaoma.marpc.protocol;

/**
 * Class Name: ProtocolConstant
 * Description: 协议常量
 * Created on: 2025/5/12
 * Author: xiaoma
 */

public interface ProtocolConstant {
    /**
     * 消息头长度
     */
    int MESSAGE_HEADER_LENGTH = 17;

    /**
     * 协议魔数
     */
    byte PROTOCOL_MAGIC = 0x1;

    /**
     * 协议版本号
     */
    byte PROTOCOL_VERSION = 0x1;
}
