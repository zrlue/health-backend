package com.rlue.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rlue.springbootinit.model.dto.activity.ActivityQueryRequest;
import com.rlue.springbootinit.model.entity.Activity;

/**
* @author hgdc
* @description 针对表【activity(社区活动)】的数据库操作Service
* @createDate 2024-04-10 21:27:05
*/
public interface ActivityService extends IService<Activity> {
    /**
     * 获取查询条件
     *
     * @param activityQueryRequest
     * @return
     */
    QueryWrapper<Activity> getQueryWrapper(ActivityQueryRequest activityQueryRequest);
}
