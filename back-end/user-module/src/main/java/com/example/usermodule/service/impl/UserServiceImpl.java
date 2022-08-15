package com.example.usermodule.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.usermodule.constant.UserStateEnum;
import com.example.usermodule.mapper.UserMapper;
import com.example.usermodule.pojo.User;
import com.example.usermodule.service.UserService;
import com.example.usermodule.util.TokenUtil;
import com.example.usermodule.vo.UserVo;
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
     * @param userVo
     * @return
     */
    @Override
    public String login(UserVo userVo) {
        User user = User.builder().userEmail(userVo.getUserEmail()).build();
        User one = getOne(Wrappers.query(user));
        if(one == null){
            return null;
        }
        Boolean aBoolean = verifyPassword(one, userVo.getPassword());
        if(aBoolean){
            one.setPassword("******");
            return TokenUtil.creatToken(one);
        } else {
            return null;
        }
    }

    /**
     * 注册
     * @param userVo
     * @return
     */
    @Override
    public Boolean register(UserVo userVo) {
        User user = User.builder().userEmail(userVo.getUserEmail()).build();
        long count = count(Wrappers.query(user));
        if(count > 0){
            return false;
        }
        User build = User.builder().userEmail(userVo.getUserEmail())
                .password(encryptPassword(userVo.getPassword()))
                .state(UserStateEnum.UNCONFIRMED)
                .build();
        return save(build);
    }

    /**
     * 加密密码
     * @return
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
     * @param user
     * @param password
     * @return
     */
    private Boolean verifyPassword(User user, String password){
        String userPassword = user.getPassword();
        HMac hMac = SecureUtil.hmacSha256(userPassword.substring(0, 32));
        String digestHex = hMac.digestHex(password);
        return digestHex.equals(userPassword.substring(32));
    }
}
