package com.zhy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * User实体类
 */
@Data // lombok插件注解，自动生成构造方法、setter、getter方法等
@Table(name = "tb_user") // 对应数据表
public class User implements Serializable {

    @Id
    @JsonIgnore
    private String id;

    private String username;
    private String password;

    @JsonIgnore
    private String salt;

    @JsonIgnore
    private Integer state;

    private String realName;
    private String phone;

    @JsonIgnore
    private Date createdTime;
}
