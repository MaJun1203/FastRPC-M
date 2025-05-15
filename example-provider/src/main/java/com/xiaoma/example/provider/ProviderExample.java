package com.xiaoma.example.provider;

import com.xiaoma.example.common.service.UserService;
import com.xiaoma.marpc.bootstrap.ProviderBootstrap;
import com.xiaoma.marpc.model.ServiceRegisterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name: ProviderExample
 * Description:
 * Created on: 2025/5/10
 * Author: xiaoma
 */

public class ProviderExample {
    public static void main(String[] args) {
        // 要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo<UserService> serviceRegisterInfo = new ServiceRegisterInfo<>(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);

    }
}
