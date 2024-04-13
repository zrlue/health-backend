package com.rlue.springbootinit.service.impl;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.common.ErrorCode;
import com.rlue.springbootinit.constant.CommonConstant;
import com.rlue.springbootinit.exception.BusinessException;
import com.rlue.springbootinit.model.dto.activity.ActivityQueryRequest;
import com.rlue.springbootinit.model.entity.Activity;

import com.rlue.springbootinit.mapper.ActivityMapper;
import com.rlue.springbootinit.service.ActivityService;
import com.rlue.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author hgdc
 * @description 针对表【activity(社区活动)】的数据库操作Service实现
 * @createDate 2024-04-10 21:27:05
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
        implements ActivityService {

    @Override
    public QueryWrapper<Activity> getQueryWrapper(ActivityQueryRequest activityQueryRequest) {
        if (activityQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = activityQueryRequest.getId();
        String title = activityQueryRequest.getTitle();
        Long promoterId = activityQueryRequest.getPromoterId();
        Integer isEnd = activityQueryRequest.getIsEnd();
        String sortField = activityQueryRequest.getSortField();
        String sortOrder = activityQueryRequest.getSortOrder();
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        queryWrapper.eq(promoterId != null, "promoterId", promoterId);
        queryWrapper.eq(isEnd != null, "isEnd", isEnd);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




