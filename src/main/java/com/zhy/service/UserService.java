package com.zhy.service;

import com.zhy.model.User;

/**
 * User业务层接口
 */

public interface UserService {

    // 用户注册
    Boolean userReg(String username, String password, String realName, String phone);

    // 根据用户名和密码查询用户
    User queryUser(String username, String password);
}
