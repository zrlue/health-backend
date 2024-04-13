package com.rlue.springbootinit.model.dto.comment;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 编辑请求
 *

 */
@Data
public class CommentEditRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论人id
     */
    private Long userId;

    /**
     * 活动id
     */
    private Long activityId;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}