package com.example.searchhot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 热度表
 *
 * @author yi_sao
 * @date 2022/8/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hot {
    @TableId
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 最大热度值
     */
    private Double maxHotValue;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 最高排名
     */
    private Integer maxRanking;

    /**
     * 热搜类型
     */
    private String searchType;

    /**
     * 链接
     */
    private String link;
}
