package com.example.usermodule.global.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "统一响应")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result<T> implements Serializable {
    @ApiModelProperty(value = "响应码",example = "200")
    private Integer code;

    @ApiModelProperty(value = "提示信息",example = "成功")
    private String msg;

    @ApiModelProperty(value = "响应数据")
    private T data;

    public static <T> Result<T> success(T data){
        return new Result<>(200, "成功", data);
    }

    public static  Result<String> error(String msg){
        return new Result<>(400, msg, null);
    }
}
