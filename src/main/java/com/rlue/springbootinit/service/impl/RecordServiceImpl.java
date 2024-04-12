package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.model.entity.Record;
import com.rlue.springbootinit.service.RecordService;
import com.rlue.springbootinit.mapper.RecordMapper;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【record(健康档案)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:06
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{

}




