package com.example.usermodule.vo;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户参数
 *
 * @author yi_sao
 * @date 2022/8/12
 */
@Data
public class UserVo implements Serializable {

    /**
     * 用户邮箱
     */
    @Email(message = "账户必须是邮箱")
    private String userEmail;

    /**
     * 密码
     */
    @NotBlank(message = "登录密码不能为空")
    private String password;
}
