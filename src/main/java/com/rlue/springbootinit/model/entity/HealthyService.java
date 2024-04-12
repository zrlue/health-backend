package com.rlue.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 健康服务
 * @TableName healthy_service
 */
@TableName(value ="healthy_service")
@Data
public class HealthyService implements Serializable {
    /**
     * 健康服务主键
     * (使用雪花算法生成唯一Id)
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务介绍
     */
    private String intro;

    /**
     * 服务封面
     */
    private String cover;

    /**
     * 服务费
     */
    private BigDecimal price;

    /**
     * 联系电话
     */
    private String tel;

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