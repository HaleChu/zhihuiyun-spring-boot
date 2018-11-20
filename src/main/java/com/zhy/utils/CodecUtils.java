package com.zhy.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * 编解码工具类CodecUtils
 */
public class CodecUtils {


    public static String md5Hex(String data, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(
                salt + org.apache.commons.codec.digest.DigestUtils.md5Hex(data));
    }

    public static String shaHex(String data, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode() + "";
        }
        return org.apache.commons.codec.digest.DigestUtils.sha512Hex(
                salt + org.apache.commons.codec.digest.DigestUtils.sha512Hex(data));
    }

    public static String generateSalt() {
        return StringUtils.replace(
                UUID.randomUUID().toString(), "-", "");
    }

}
