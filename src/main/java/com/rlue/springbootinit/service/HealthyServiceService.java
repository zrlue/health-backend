package com.rlue.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rlue.springbootinit.model.dto.healthyService.HealthyServiceQueryRequest;
import com.rlue.springbootinit.model.entity.HealthyService;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hgdc
* @description 针对表【healthy_service(健康服务)】的数据库操作Service
* @createDate 2024-04-10 21:27:05
*/
public interface HealthyServiceService extends IService<HealthyService> {

    QueryWrapper<HealthyService> getQueryWrapper(HealthyServiceQueryRequest healthyServiceQueryRequest);
}
