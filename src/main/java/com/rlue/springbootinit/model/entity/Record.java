package com.rlue.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 健康档案
 * @TableName record
 */
@TableName(value ="record")
@Data
public class Record implements Serializable {
    /**
     * 健康档案主键
     * (使用雪花算法生成唯一Id)
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户主键
     */
    private Long userId;

    /**
     * 详细记录
     */
    private String record;

    /**
     * 报告
     */
    private String report;

    /**
     * 操作人
     */
    private Long opeId;

    /**
     * 建议
     */
    private String advise;

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