package com.zhy.mapper;

import com.zhy.model.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * User模块持久层接口，继承通用mapper
 */

@Repository
public interface UserMapper extends Mapper<User> {
}
