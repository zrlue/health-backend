package com.rlue.springbootinit.model.dto.question;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 更新请求
 *

 */
@Data
public class QuestionUpdateRequest implements Serializable {

    /**
     * 主键
     */
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


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}