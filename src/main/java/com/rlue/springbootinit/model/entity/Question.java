package com.rlue.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 留言
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {
    /**
     * 留言主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 具体问题
     */
    private String context;

    /**
     * 问诊人
     */
    private Long userId;

    /**
     * 回答者id
     */
    private Long pid;

    /**
     * 状态
     */
    private Integer status;

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