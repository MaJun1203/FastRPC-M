package com.xiaoma.marpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Name: ServiceRegisterInfo
 * Description:
 * Created on: 2025/5/14
 * Author: xiaoma
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRegisterInfo<T> {

    private String serviceName;

    private Class<? extends T> implClass;
}
