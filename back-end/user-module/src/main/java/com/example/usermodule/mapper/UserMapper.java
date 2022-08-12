package com.example.usermodule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.usermodule.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 *
 * @author yi_sao
 * @date 2022/8/12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
