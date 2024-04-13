package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.common.ErrorCode;
import com.rlue.springbootinit.constant.CommonConstant;
import com.rlue.springbootinit.exception.BusinessException;
import com.rlue.springbootinit.model.dto.activityUser.ActivityUserQueryRequest;
import com.rlue.springbootinit.model.entity.Activity;
import com.rlue.springbootinit.model.entity.ActivityUser;
import com.rlue.springbootinit.service.ActivityUserService;
import com.rlue.springbootinit.mapper.ActivityUserMapper;
import com.rlue.springbootinit.utils.SqlUtils;
import org.springframework.stereotype.Service;

/**
 * @author hgdc
 * @description 针对表【activity_user(活动报名人)】的数据库操作Service实现
 * @createDate 2024-04-10 21:46:17
 */
@Service
public class ActivityUserServiceImpl extends ServiceImpl<ActivityUserMapper, ActivityUser>
        implements ActivityUserService {

    @Override
    public QueryWrapper<ActivityUser> getQueryWrapper(ActivityUserQueryRequest activityUserQueryRequest) {
        if (activityUserQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = activityUserQueryRequest.getId();
        Long activityId = activityUserQueryRequest.getActivityId();
        Long applicantId = activityUserQueryRequest.getApplicantId();
        String sortField = activityUserQueryRequest.getSortField();
        String sortOrder = activityUserQueryRequest.getSortOrder();
        QueryWrapper<ActivityUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(activityId != null, "activityId", activityId);
        queryWrapper.eq(applicantId != null, "applicantId", id);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




