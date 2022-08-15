package com.example.usermodule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author yi_sao
 * @date 2022/8/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestObject {

    private String name;
    private Integer age;
}
