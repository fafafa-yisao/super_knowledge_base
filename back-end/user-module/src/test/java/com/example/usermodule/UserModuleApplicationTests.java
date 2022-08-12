package com.example.usermodule;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.usermodule.constant.UserStateEnum;
import com.example.usermodule.mapper.UserMapper;
import com.example.usermodule.pojo.User;
import com.example.usermodule.service.UserService;
import com.example.usermodule.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.List;

@SpringBootTest
@Slf4j
class UserModuleApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
//        User user = new User();
//        user.setUserEmail("123");
//        user.setPassword("345");
//        user.setState(UserStateEnum.UNCONFIRMED);
//        int i = userMapper.insert(user);
//        System.out.println(i);
    }

    @Test
    void testLoginAndRegister(){
        UserVo vo = new UserVo();
        vo.setUserEmail("234");
        vo.setPassword("789");
        Boolean register = userService.register(vo);
        System.out.println(register);
        String login = userService.login(vo);
        System.out.println(login);
    }

}
