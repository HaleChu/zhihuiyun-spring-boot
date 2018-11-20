package com.zhy.service;

/**
 * 用户授权业务层接口
 */
public interface AuthService {

    // 用户登录
    String userLogin(String username, String password);
}
