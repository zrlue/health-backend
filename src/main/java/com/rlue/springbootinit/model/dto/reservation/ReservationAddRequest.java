package com.rlue.springbootinit.model.dto.reservation;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建请求
 *

 */
@Data
public class ReservationAddRequest implements Serializable {

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


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}