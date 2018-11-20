package com.zhy.dto;

import lombok.Data;

/**
 * 封装消息bean
 */
@Data
public class MessageDTO {

    // 返回码
    private Integer code;
    // 返回flag，用于前台判断
    private Boolean flag;
    // 返回消息
    private String msg;
    // 返回数据主体
    private Object data;

    public MessageDTO() {
    }

    public MessageDTO(Integer code, Boolean flag, String msg, Object data) {
        this.code = code;
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }
}
