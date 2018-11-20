package com.zhy.controller;

import com.zhy.dto.MessageDTO;
import com.zhy.model.User;
import com.zhy.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User模块控制层
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // 用户注册
    @ApiOperation(value = "用户注册", notes = "根据User对象注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "realName", value = "真实姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号码", required = true, dataType = "String")})
    @PostMapping("register")
    public MessageDTO userReg(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String realName,
                              @RequestParam String phone) {
        try {
            Boolean flag = userService.userReg(username, password, realName, phone);
            if (flag) {
                return new MessageDTO(200, true, "注册成功", null);
            } else {
                return new MessageDTO(500, false, "注册失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(500, false, "注册失败", null);
        }

    }

    // 根据用户名和密码查询用户
    @GetMapping("query")
    public MessageDTO queryUser(@RequestParam String username, @RequestParam String password) {
        User user = this.userService.queryUser(username, password);
        if (user == null) {
            return new MessageDTO(500, false, "user not found", null);
        }
        return new MessageDTO(200, true, null, user);
    }

}
