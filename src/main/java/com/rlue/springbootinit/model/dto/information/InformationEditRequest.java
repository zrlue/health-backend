package com.rlue.springbootinit.model.dto.information;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 编辑请求
 *

 */
@Data
public class InformationEditRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 类型
     */
    private String type;

    /**
     * 资讯图片
     */
    private String pic;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}