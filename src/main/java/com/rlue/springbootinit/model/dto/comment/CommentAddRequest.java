package com.rlue.springbootinit.model.dto.comment;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建请求
 *

 */
@Data
public class CommentAddRequest implements Serializable {

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