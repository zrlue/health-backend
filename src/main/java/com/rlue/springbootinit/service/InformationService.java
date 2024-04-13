package com.rlue.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rlue.springbootinit.model.dto.information.InformationQueryRequest;
import com.rlue.springbootinit.model.entity.Information;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hgdc
* @description 针对表【information(资讯)】的数据库操作Service
* @createDate 2024-04-10 21:27:05
*/
public interface InformationService extends IService<Information> {

    QueryWrapper<Information> getQueryWrapper(InformationQueryRequest informationQueryRequest);
}
