package com.example.usermodule.controller.user;

import com.example.usermodule.convert.UserConvert;
import com.example.usermodule.service.UserService;
import com.example.usermodule.global.api.Result;
import com.example.usermodule.controller.user.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "用户服务")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result<String> login(@Validated @RequestBody UserVo vo) {
        String login = userService.login(UserConvert.INSTANCE.userVo2User(vo));
        return Result.success(login);
    }


    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Result<String> register(@Validated @RequestBody UserVo vo) {
        userService.register(UserConvert.INSTANCE.userVo2User(vo));
        return Result.success(null);
    }
}
