package com.rlue.springbootinit.model.dto.type;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 编辑请求
 *

 */
@Data
public class TypeEditRequest implements Serializable {

    /**
     * 社区活动主键
     */
    private Long id;

    /**
     * 分类名称
     */
    private String typeName;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}