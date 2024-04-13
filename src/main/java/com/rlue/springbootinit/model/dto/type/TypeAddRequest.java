package com.rlue.springbootinit.model.dto.type;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建请求
 *

 */
@Data
public class TypeAddRequest implements Serializable {

    /**
     * 分类名称
     */
    private String typeName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}