package com.example.usermodule.vo;

import lombok.Data;

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
    private String userEmail;
    /**
     * 密码
     */
    private String password;
}
