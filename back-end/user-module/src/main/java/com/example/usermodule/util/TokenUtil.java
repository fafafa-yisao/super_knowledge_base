package com.example.usermodule.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.example.usermodule.pojo.User;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Token工具
 *
 * @author yi_sao
 * @date 2022/8/15
 */
public class TokenUtil {

    private final static byte[] KEY_BYTES = "J0eXAiOiJKV1QiLCJhbGciOiJIUz".getBytes(StandardCharsets.UTF_8);

    private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 生成token
     * @param user
     * @return
     */
    public static String creatToken(User user){
        String sign = JWT.create().setExpiresAt(DateUtil.offsetDay(new Date(), 1))
                .setIssuedAt(new Date())
                .addPayloads(BeanUtil.beanToMap(user))
                .setSigner("HS256", KEY_BYTES).sign();
        return sign;
    }

    /**
     * 校验token
     * @param sign
     * @return
     */
    public static Boolean verifyToken(String sign){
        Boolean verify = true;

        //校验时间是否过期
        try {
            JWTValidator.of(sign).validateDate();
        } catch (ValidateException e){
            verify = false;
        }

        //校验内容
        verify = JWTUtil.verify(sign, KEY_BYTES);

        JSONObject jsonObject = JWT.of(sign).getPayloads();
        User bean = jsonObject.toBean(User.class);
        THREAD_LOCAL.set(bean);

        return verify;
    }

    /**
     * 获取token中的用户信息
     *
     * 前提是已通过验签
     * @return
     */
    public static User getTokenValue(){
        return THREAD_LOCAL.get();
    }
}
