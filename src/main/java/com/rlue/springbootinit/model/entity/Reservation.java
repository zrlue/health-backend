package com.rlue.springbootinit.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 服务预约
 * @TableName reservation
 */
@TableName(value ="reservation")
@Data
public class Reservation implements Serializable {
    /**
     * 服务预约主键
     * (使用雪花算法生成唯一Id)
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 服务Id
     */
    private Long serviceId;

    /**
     * 预约人id
     */
    private Long userId;

    /**
     * 预约号
     */
    private String number;

    /**
     * 留言
     */
    private String message;

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
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}