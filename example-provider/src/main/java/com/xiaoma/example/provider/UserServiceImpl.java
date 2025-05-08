package com.xiaoma.example.provider;

import com.xiaoma.example.common.model.User;
import com.xiaoma.example.common.service.UserService;

/**
 * Class Name: UserServiceImpl
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
