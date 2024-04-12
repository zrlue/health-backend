package com.rlue.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资讯
 * @TableName information
 */
@TableName(value ="information")
@Data
public class Information implements Serializable {
    /**
     * 资讯主键
     * (使用雪花算法生成唯一Id)
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 类型
     */
    private String type;

    /**
     * 资讯图片
     */
    private String pic;

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