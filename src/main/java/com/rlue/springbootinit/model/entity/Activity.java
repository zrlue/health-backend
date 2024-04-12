package com.rlue.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 社区活动
 * @TableName activity
 */
@TableName(value ="activity")
@Data
public class Activity implements Serializable {
    /**
     * 社区活动主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 活动主题
     */
    private String title;

    /**
     * 活动内容
     */
    private String context;

    /**
     * 举办时间
     */
    private Date time;

    /**
     * 图片
     */
    private String pic;

    /**
     * 发起人id
     */
    private Long promoterId;

    /**
     * 是否结束
     */
    private Integer isEnd;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}