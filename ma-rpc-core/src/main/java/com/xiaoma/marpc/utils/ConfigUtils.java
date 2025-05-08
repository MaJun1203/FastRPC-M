package com.xiaoma.marpc.utils;

import cn.hutool.setting.dialect.Props;

/**
 * Class Name: ConfigUtils
 * Description:读取配置文件并返回配置对象，可以简化调用。
 * Created on: 2025/5/8
 * Author: 112033918
 */

public class ConfigUtils {

    /**
     * 加载配置对象
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置对象 支持区分环境
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (!environment.isEmpty()) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass, prefix);
    }
}

