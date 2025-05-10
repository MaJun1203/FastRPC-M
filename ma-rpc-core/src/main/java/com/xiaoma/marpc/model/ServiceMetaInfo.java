package com.xiaoma.marpc.model;

import cn.hutool.core.util.StrUtil;
import com.xiaoma.marpc.serializer.Serializer;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * Class Name: ServiceMetaInfo
 * Description:
 * Created on: 2025/5/9
 * Author: xiaoma
 */

@Data
public class ServiceMetaInfo implements Serializable {
    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务版本号
     */
    private String serviceVersion = "1.0";

    /**
     * 服务端口号
     */
    private Integer servicePort;

    /**
     * 服务主机地址
     */
    private String serviceHost;

    /**
     * 服务分组
     */
    private String serviceGroup = "default";

    /**
     * 获取服务键名
     *
     * @return
     */
    public String getServiceKey() {
        // 后续可扩展服务分组
        // return String.format("%s:%s:%s", serviceName, serviceVersion, serviceGroup);
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /**
     * 获取服务注册节点键名
     *
     * @return
     */
    public String getServiceNodeKey() {
        return String.format("%s/%s:%s", getServiceKey(), serviceHost, servicePort);
    }

    /**
     * 获取完整服务地址
     *
     * @return
     */
    public String getServiceAddress() {
        if (!StrUtil.contains(serviceHost, "http")) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }

}
