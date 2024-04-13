package com.rlue.springbootinit.model.dto.reservation;

import com.baomidou.mybatisplus.annotation.TableField;
import com.rlue.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询请求
 *

 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ReservationQueryRequest extends PageRequest implements Serializable {

    /**
     * 主键
     */
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
     * 状态
     */
    private Integer status;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}