package com.rlue.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rlue.springbootinit.model.dto.comment.CommentQueryRequest;
import com.rlue.springbootinit.model.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hgdc
* @description 针对表【comment(评论)】的数据库操作Service
* @createDate 2024-04-10 21:27:05
*/
public interface CommentService extends IService<Comment> {

    QueryWrapper<Comment> getQueryWrapper(CommentQueryRequest commentQueryRequest);
}
