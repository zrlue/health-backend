package com.rlue.springbootinit.model.dto.healthyService;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 创建请求
 *

 */
@Data
public class HealthyServiceAddRequest implements Serializable {

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}