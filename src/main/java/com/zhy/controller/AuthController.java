package com.zhy.controller;

import com.zhy.config.JwtProperties;
import com.zhy.dto.MessageDTO;
import com.zhy.entity.UserInfo;
import com.zhy.service.impl.AuthServiceImpl;
import com.zhy.utils.CookieUtils;
import com.zhy.utils.JwtUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权中心控制层
 */
@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private JwtProperties prop;

    // 用户登录授权
    @ApiOperation(value = "用户登录授权", notes = "用户登录验证，并生成鉴权token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
    })
    @PostMapping("login")
    public MessageDTO userLogin(
            @RequestParam String username, @RequestParam String password,
            HttpServletRequest request, HttpServletResponse response) {
        // 登录
        String token = authService.userLogin(username, password);
        if (StringUtils.isBlank(token)) {
            return new MessageDTO(
                    500, false, "登录失败，用户名或密码错误", null);
        }
        // 写入cookie
        CookieUtils.newBuilder(response).httpOnly()
                .request(request).build(prop.getCookieName(), token);
        return new MessageDTO(200, true, "登录成功", null);
    }

    // 验证用户信息
    @GetMapping("verify")
    public MessageDTO verifyUser(
            @CookieValue("ZHY_TOKEN") String token,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取token信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            // 如果成功，刷新token信息
            token = JwtUtils.generateToken(userInfo, prop.getPrivateKey(), prop.getExpire());
            // 写回cookie
            CookieUtils.newBuilder(response).httpOnly()
                    .request(request).build(prop.getCookieName(), token);
            // 成功返回
            return new MessageDTO(200, true, "登录成功", userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageDTO(500, false, "登录异常", null);
        }
    }
}
