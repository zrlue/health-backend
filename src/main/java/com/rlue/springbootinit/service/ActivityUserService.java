package com.rlue.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rlue.springbootinit.model.dto.activityUser.ActivityUserQueryRequest;
import com.rlue.springbootinit.model.entity.ActivityUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hgdc
* @description 针对表【activity_user(活动报名人)】的数据库操作Service
* @createDate 2024-04-10 21:46:17
*/
public interface ActivityUserService extends IService<ActivityUser> {

    QueryWrapper<ActivityUser> getQueryWrapper(ActivityUserQueryRequest activityUserQueryRequest);
}
