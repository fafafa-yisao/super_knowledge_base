package com.example.usermodule.controller.user.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户VO")
public class UserVo implements Serializable {

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱",example = "101xxxxx@qq.com")
    @Email(message = "账户必须是邮箱")
    private String userEmail;

    /**
     * 密码
     */
    @ApiModelProperty(value = "用户密码",example = "******")
    @NotBlank(message = "登录密码不能为空")
    private String password;
}
