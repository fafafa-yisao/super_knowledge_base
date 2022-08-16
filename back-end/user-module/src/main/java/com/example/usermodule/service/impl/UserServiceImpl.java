package com.example.usermodule.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.usermodule.constant.UserStateEnum;
import com.example.usermodule.exception.DefaultException;
import com.example.usermodule.mapper.UserMapper;
import com.example.usermodule.pojo.User;
import com.example.usermodule.service.UserService;
import com.example.usermodule.util.TokenUtil;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author yi_sao
 * @date 2022/8/12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 登录
     * @param user 用户信息
     */
    @Override
    public String login(User user) {
        String password = user.getPassword();
        user.setPassword(null);
        User one = getOne(Wrappers.query(user));
        if(one == null){
            throw  DefaultException.exception("用户名不存在");
        }


        Boolean aBoolean = verifyPassword(one, password);

        if(aBoolean){
            one.setPassword("******");
            return TokenUtil.creatToken(one);
        } else {
            throw  DefaultException.exception("密码错误");
        }
    }

    /**
     * 注册
     * @param user 用户信息
     */
    @Override
    public Boolean register(User user) {
        long count = count(Wrappers.query(user));
        if(count > 0)
            throw DefaultException.exception("账户已被注册");

        User build = User.builder().userEmail(user.getUserEmail())
                .password(encryptPassword(user.getPassword()))
                .state(UserStateEnum.UNCONFIRMED)
                .build();
        return save(build);
    }

    /**
     * 加密密码
     */
    private String encryptPassword(String password){
        UUID uuid = UUID.randomUUID(true);
        String key = uuid.toString().replaceAll(StrUtil.DASHED, StrUtil.EMPTY);
        HMac hMac = SecureUtil.hmacSha256(key);
        String digestHex = hMac.digestHex(password);
        return key + digestHex;
    }

    /**
     * 验证密码
     * @param user 用户信息
     * @param password 密码
     */
    private Boolean verifyPassword(User user, String password){
        String userPassword = user.getPassword();
        HMac hMac = SecureUtil.hmacSha256(userPassword.substring(0, 32));
        String digestHex = hMac.digestHex(password);
        return digestHex.equals(userPassword.substring(32));
    }
}
