package com.zhy.service.impl;

import com.zhy.config.JwtProperties;
import com.zhy.entity.UserInfo;
import com.zhy.model.User;
import com.zhy.service.AuthService;
import com.zhy.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * 用户授权业务层实现
 */

@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtProperties prop;

    // 用户单点登录，生成token
    @Override
    public String userLogin(String username, String password) {
        try {
            // 查询用户
            User user = userService.queryUser(username, password);
            // 判断是否为空
            if (user == null) return null;
            // 生成userInfo
            UserInfo userInfo = new UserInfo(user.getId(), user.getUsername());
            // 生成token
            String token = JwtUtils.generateToken(userInfo, prop.getPrivateKey(), prop.getExpire());
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
