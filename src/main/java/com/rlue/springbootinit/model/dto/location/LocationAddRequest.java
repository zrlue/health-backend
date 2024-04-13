package com.rlue.springbootinit.model.dto.location;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建请求
 *

 */
@Data
public class LocationAddRequest implements Serializable {

    /**
     * 经度
     */
    private String lat;

    /**
     * 纬度
     */
    private String lng;

    /**
     * 求救人Id
     */
    private Long userId;

    /**
     * 状态
     */
    private Integer status;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}