package com.rlue.springbootinit.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rlue.springbootinit.annotation.AuthCheck;
import com.rlue.springbootinit.common.BaseResponse;
import com.rlue.springbootinit.common.DeleteRequest;
import com.rlue.springbootinit.common.ErrorCode;
import com.rlue.springbootinit.common.ResultUtils;
import com.rlue.springbootinit.constant.UserConstant;
import com.rlue.springbootinit.exception.BusinessException;
import com.rlue.springbootinit.exception.ThrowUtils;
import com.rlue.springbootinit.model.dto.activity.ActivityAddRequest;
import com.rlue.springbootinit.model.dto.activity.ActivityEditRequest;
import com.rlue.springbootinit.model.dto.activity.ActivityQueryRequest;
import com.rlue.springbootinit.model.dto.activity.ActivityUpdateRequest;
import com.rlue.springbootinit.model.entity.Activity;
import com.rlue.springbootinit.model.entity.User;
import com.rlue.springbootinit.service.ActivityService;
import com.rlue.springbootinit.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 社区活动接口
 *

 */
@RestController
@RequestMapping("/activity")
@Slf4j
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param activityAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addActivity(@RequestBody ActivityAddRequest activityAddRequest, HttpServletRequest request) {
        if (activityAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityAddRequest, activity);
        User loginUser = userService.getLoginUser(request);
        activity.setPromoterId(loginUser.getId());
        boolean result = activityService.save(activity);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newActivityId = activity.getId();
        return ResultUtils.success(newActivityId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteActivity(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Activity oldActivity = activityService.getById(id);
        ThrowUtils.throwIf(oldActivity == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅活动发起人或管理员可删除
        if (!oldActivity.getPromoterId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = activityService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param activityUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateActivity(@RequestBody ActivityUpdateRequest activityUpdateRequest) {
        if (activityUpdateRequest == null || activityUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityUpdateRequest, activity);
        long id = activityUpdateRequest.getId();
        // 判断是否存在
        Activity oldActivity = activityService.getById(id);
        ThrowUtils.throwIf(oldActivity == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = activityService.updateById(activity);
        return ResultUtils.success(result);
    }



    /**
     * 分页获取列表（仅管理员）
     *
     * @param activityQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Activity>> listActivityByPage(@RequestBody ActivityQueryRequest activityQueryRequest) {
        long current = activityQueryRequest.getCurrent();
        long size = activityQueryRequest.getPageSize();
        Page<Activity> activityPage = activityService.page(new Page<>(current, size),
                activityService.getQueryWrapper(activityQueryRequest));
        return ResultUtils.success(activityPage);
    }


    // endregion

    /**
     * 编辑（用户）
     *
     * @param activityEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editActivity(@RequestBody ActivityEditRequest activityEditRequest, HttpServletRequest request) {
        if (activityEditRequest == null || activityEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityEditRequest, activity);
        User loginUser = userService.getLoginUser(request);
        long id = activityEditRequest.getId();
        // 判断是否存在
        Activity oldActivity = activityService.getById(id);
        ThrowUtils.throwIf(oldActivity == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅活动发起人或管理员可编辑
        if (!oldActivity.getPromoterId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = activityService.updateById(activity);
        return ResultUtils.success(result);
    }

}
