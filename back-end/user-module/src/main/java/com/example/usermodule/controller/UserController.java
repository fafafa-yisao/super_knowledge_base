package com.example.usermodule.controller;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.example.usermodule.service.UserService;
import com.example.usermodule.vo.Result;
import com.example.usermodule.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务
 *
 * @author yi_sao
 * @date 2022/8/15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Result login(@RequestBody UserVo vo){
        String login = userService.login(vo);
        if(StrUtil.isEmpty(login)){
            return Result.error("用户名或者密码错误");
        } else {
            return Result.success(login);
        }
    }


    @PostMapping("/register")
    public Result register(@RequestBody UserVo vo){

        String re = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        boolean match = ReUtil.isMatch(re, vo.getUserEmail());
        if(!match){
            return Result.error("账户必须是邮箱");
        }

        Boolean register = userService.register(vo);
        if(register){
            return Result.error("账户已被注册");
        } else {
            return Result.success(null);
        }
    }
}
