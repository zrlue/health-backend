package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.model.entity.Comment;
import com.rlue.springbootinit.service.CommentService;
import com.rlue.springbootinit.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【comment(评论)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:05
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




