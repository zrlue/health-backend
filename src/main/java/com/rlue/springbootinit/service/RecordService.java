package com.rlue.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rlue.springbootinit.model.dto.record.RecordQueryRequest;
import com.rlue.springbootinit.model.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hgdc
* @description 针对表【record(健康档案)】的数据库操作Service
* @createDate 2024-04-10 21:27:06
*/
public interface RecordService extends IService<Record> {

    QueryWrapper<Record> getQueryWrapper(RecordQueryRequest recordQueryRequest);
}
