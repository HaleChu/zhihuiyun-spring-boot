package com.zhy.test;

import com.zhy.config.JwtProperties;
import com.zhy.entity.UserInfo;
import com.zhy.utils.JwtUtils;
import com.zhy.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author: Hao.Chu
 * @create: 2018/11/19 16:43
 */
public class JwtTest {

    private static final String pubKeyPath = "D:\\zhy\\rsa\\rsa.pub";

    private static final String priKeyPath = "D:\\zhy\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Autowired
    private JwtProperties prop;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "123");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(
                new UserInfo("20", "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjo" +
                "iamFjayIsImV4cCI6MTU0MjYxOTA3OX0.M3BU68gc9TAIWlhwwdi_V2x1EzAX" +
                "eRbSalbFx22xFOW4E1G4RMU-GBGDTs3E4L6M2WLjAodW8x38e_M8J7F8P296leegwSAR" +
                "X0-MpvHJG6ABB2wUTmt5xjhQDNx2SUTXjNKLVvz84-Qbb_WcNzl4Ti2tiF0N-nYn4VcSnUug0bU";
        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }

}

