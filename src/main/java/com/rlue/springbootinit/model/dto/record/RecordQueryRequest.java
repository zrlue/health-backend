package com.rlue.springbootinit.model.dto.record;

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
public class RecordQueryRequest extends PageRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 问诊人Id
     */
    private Long userId;

    /**
     * 操作人
     */
    private Long opeId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}