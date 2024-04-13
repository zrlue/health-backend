package com.rlue.springbootinit.model.dto.type;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class TypeQueryRequest extends PageRequest implements Serializable {

    /**
     * 分类名称主键
     */
    private Long id;

    /**
     * 分类名称
     */
    private String typeName;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}