package com.example.usermodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.usermodule.pojo.User;

/**
 * 用户服务
 *
 * @author yi_sao
 * @date 2022/8/12
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param user 用户信息
     */
    String login(User user);

    /**
     * 注册
     * @param user 用户信息
     */
    Boolean register(User user);
}
