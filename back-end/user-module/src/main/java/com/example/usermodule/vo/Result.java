package com.example.usermodule.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应结果
 *
 * @author yi_sao
 * @date 2022/8/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result implements Serializable {

    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data){
        return new Result(200, "成功", data);
    }

    public static Result error(String msg){
        return new Result(400, msg, null);
    }
}
