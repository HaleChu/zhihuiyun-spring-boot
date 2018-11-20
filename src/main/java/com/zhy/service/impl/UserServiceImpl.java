package com.zhy.service.impl;

import com.zhy.mapper.UserMapper;
import com.zhy.model.User;
import com.zhy.service.UserService;
import com.zhy.utils.CodecUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * User业务层实现
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean userReg(String username, String password, String realName, String phone) {
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setUsername(username);
        user.setRealName(realName);
        user.setPhone(phone);
        // 生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        // 设置加密密码
        user.setPassword(CodecUtils.md5Hex(password, salt));
        // 设置账号状态，为未激活状态
        user.setState(0);
        user.setCreatedTime(new Date());
        int count = userMapper.insert(user);
        if (count != 1) {
            return false;
        }
        return true;
    }

    // 根据用户名和密码查询用户
    @Override
    public User queryUser(String username, String password) {
        // 查询
        User user = new User();
        user.setUsername(username);
        User result = userMapper.selectOne(user);
        // 校检用户名
        if (result == null) return null;
        // 校检密码
        if (!result.getPassword().equals(CodecUtils.md5Hex(password, result.getSalt())))
            return null;
        // 用户名和密码都正确
        return result;
    }

    // 测试获取MD加密密码
    public static void main(String[] args) {
        String password = "123";
        String salt = CodecUtils.generateSalt();
        System.out.println(salt);
        System.out.println(CodecUtils.md5Hex(password, salt));
    }

}
