package com.example.usermodule.controller;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.example.usermodule.convert.UserConvert;
import com.example.usermodule.service.UserService;
import com.example.usermodule.vo.Result;
import com.example.usermodule.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户服务
 *
 * @author yi_sao
 * @date 2022/8/15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody UserVo vo) {
        String login = userService.login(UserConvert.INSTANCE.userVo2User(vo));
        return Result.success(login);
    }


    @PostMapping("/register")
    public Result<String> register(@Validated @RequestBody UserVo vo) {
        userService.register(UserConvert.INSTANCE.userVo2User(vo));
        return Result.success(null);
    }
}
