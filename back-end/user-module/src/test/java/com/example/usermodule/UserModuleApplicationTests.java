package com.example.usermodule;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.example.usermodule.constant.UserStateEnum;
import com.example.usermodule.mapper.UserMapper;
import com.example.usermodule.pojo.User;
import com.example.usermodule.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;

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
        User vo = new User();
        vo.setUserEmail("234");
        vo.setPassword("789");
        Boolean register = userService.register(vo);
        System.out.println(register);
        String login = userService.login(vo);
        System.out.println(login);
    }

    @Test
    void testJWT(){
        User user = new User();
        user.setId(1L);
        user.setState(UserStateEnum.UNCONFIRMED);
        user.setUserEmail("456");
        user.setPassword("111");
        TestObject object = TestObject.builder().name("张三").age(34).build();
        String sign = JWT.create().setExpiresAt(DateUtil.offsetDay(new Date(), 1))
                .setIssuedAt(DateUtil.offsetDay(new Date(), -2))
                .addPayloads(BeanUtil.beanToMap(user))
                .addPayloads(BeanUtil.beanToMap(object))
                .setSigner("HS256", "334".getBytes(StandardCharsets.UTF_8)).sign();
        System.out.println(sign);
        JSONObject jsonObject = JWT.of(sign).getPayloads();
        User bean = jsonObject.toBean(User.class);
        System.out.println(bean);
        TestObject testObject = jsonObject.toBean(TestObject.class);
        System.out.println(testObject);

        Boolean bool = true;
        try {
            JWTValidator.of(sign).validateDate();
        } catch (ValidateException e){
            bool = false;
        }
        System.out.println(bool);

        boolean verify = JWTUtil.verify(sign, "334".getBytes(StandardCharsets.UTF_8));
        System.out.println(verify);

    }

    @Test
    void testRe(){
        String re = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        boolean match = ReUtil.isMatch(re, "123");
        System.out.println(match);
    }

}
