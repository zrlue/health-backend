package com.rlue.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rlue.springbootinit.model.dto.question.QuestionQueryRequest;
import com.rlue.springbootinit.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hgdc
* @description 针对表【question(留言)】的数据库操作Service
* @createDate 2024-04-10 21:27:06
*/
public interface QuestionService extends IService<Question> {

    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);
}
