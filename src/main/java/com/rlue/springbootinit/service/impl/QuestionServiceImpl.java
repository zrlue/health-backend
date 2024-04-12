package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.model.entity.Question;
import com.rlue.springbootinit.service.QuestionService;
import com.rlue.springbootinit.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【question(留言)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:06
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




