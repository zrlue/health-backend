package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.model.entity.HealthyService;
import com.rlue.springbootinit.service.HealthyServiceService;
import com.rlue.springbootinit.mapper.HealthyServiceMapper;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【healthy_service(健康服务)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:05
*/
@Service
public class HealthyServiceServiceImpl extends ServiceImpl<HealthyServiceMapper, HealthyService>
    implements HealthyServiceService{

}




