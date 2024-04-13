package com.rlue.springbootinit.model.dto.question;

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
public class QuestionQueryRequest extends PageRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;


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