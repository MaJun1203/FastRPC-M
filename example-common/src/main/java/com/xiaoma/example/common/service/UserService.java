package com.xiaoma.example.common.service;

import com.xiaoma.example.common.model.User;

/**
 * Class Name: UserService
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public interface UserService {
    User getUser(User user);

    /**
     * 新方法 - 获取数字
     */
    default short getNumber() {
        return 1;
    }
}
