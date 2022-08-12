package com.example.usermodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.usermodule.pojo.User;
import com.example.usermodule.vo.UserVo;

/**
 * 用户服务
 *
 * @author yi_sao
 * @date 2022/8/12
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param userVo
     * @return
     */
    String login(UserVo userVo);

    /**
     * 注册
     * @param userVo
     * @return
     */
    Boolean register(UserVo userVo);
}
