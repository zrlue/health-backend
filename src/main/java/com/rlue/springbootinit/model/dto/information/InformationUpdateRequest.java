package com.rlue.springbootinit.model.dto.information;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 更新请求
 *

 */
@Data
public class InformationUpdateRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 活动主题
     */
    private String title;

    /**
     * 活动内容
     */
    private String context;

    /**
     * 举办时间
     */
    private Date time;

    /**
     * 图片
     */
    private String pic;

    /**
     * 发起人id
     */
    private Long promoterId;

    /**
     * 是否结束
     */
    private Integer isEnd;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}