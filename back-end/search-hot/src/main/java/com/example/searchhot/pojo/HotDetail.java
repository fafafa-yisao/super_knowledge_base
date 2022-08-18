package com.example.searchhot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 热度详情表
 *
 * @author yi_sao
 * @date 2022/8/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotDetail {
    @TableId
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 排名
     */
    private Integer ranking;

    /**
     * 热度值
     */
    private Double hotValue;

    /**
     * 热度时间
     */
    private Date hotTime;

    /**
     * 热搜类型
     */
    private String searchType;

    /**
     * 热度表ID
     */
    private String hotId;
}
