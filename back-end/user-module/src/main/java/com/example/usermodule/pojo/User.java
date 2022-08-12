package com.example.usermodule.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.usermodule.constant.UserStateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 *
 * @author yi_sao
 * @date 2022/8/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户邮箱
     */
    private String userEmail;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态（未确认：unconfirmed，正常：normal，注销：cancel）
     */
    private UserStateEnum state;
}
