package com.rlue.springbootinit.model.dto.record;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建请求
 *

 */
@Data
public class RecordAddRequest implements Serializable {

    /**
     * 问诊人Id
     */
    private Long userId;

    /**
     * 详细记录
     */
    private String record;

    /**
     * 报告
     */
    private String report;

    /**
     * 操作人
     */
    private Long opeId;

    /**
     * 建议
     */
    private String advise;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}