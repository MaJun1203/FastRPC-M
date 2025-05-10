package com.xiaoma.marpc.model;

import com.xiaoma.marpc.constant.RpcConstant;
import com.xiaoma.marpc.serializer.Serializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Class Name: RpcRequest
 * Description:Rpc请求类
 * Created on: 2025/5/8
 * Author: 112033918
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcRequest implements Serializable {
    /**
     * 服务名
     */
    private String serviceName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型列表
     */
    private Class<?>[] parameterTypes;
    /**
     * 参数列表
     */
    private Object[] parameters;

    /**
     * 版本号
     */
    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;
}
