package com.rlue.springbootinit.model.dto.activityUser;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建请求
 *

 */
@Data
public class ActivityUserAddRequest implements Serializable {

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 报名人id
     */
    private Long applicantId;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}