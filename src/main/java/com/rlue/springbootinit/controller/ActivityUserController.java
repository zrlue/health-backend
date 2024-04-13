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

import com.rlue.springbootinit.model.dto.activityUser.ActivityUserAddRequest;
import com.rlue.springbootinit.model.dto.activityUser.ActivityUserEditRequest;
import com.rlue.springbootinit.model.dto.activityUser.ActivityUserQueryRequest;
import com.rlue.springbootinit.model.dto.activityUser.ActivityUserUpdateRequest;
import com.rlue.springbootinit.model.entity.ActivityUser;
import com.rlue.springbootinit.service.ActivityUserService;
import com.rlue.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章类型接口
 *

 */
@RestController
@RequestMapping("/activityUser")
@Slf4j
public class ActivityUserController {

    @Resource
    private ActivityUserService activityUserService;

    @Resource
    private UserService userService;

    // region 增删改查`


    /**
     * 创建
     *
     * @param activityUserAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addActivityUser(@RequestBody ActivityUserAddRequest activityUserAddRequest) {
        if (activityUserAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ActivityUser activityUser = new ActivityUser();
        BeanUtils.copyProperties(activityUserAddRequest, activityUser);

        boolean result = activityUserService.save(activityUser);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newActivityUserId = activityUser.getId();
        return ResultUtils.success(newActivityUserId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteActivityUser(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long id = deleteRequest.getId();
        // 判断是否存在
        ActivityUser oldActivityUser = activityUserService.getById(id);
        ThrowUtils.throwIf(oldActivityUser == null, ErrorCode.NOT_FOUND_ERROR);

        boolean b = activityUserService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param activityUserUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateActivityUser(@RequestBody ActivityUserUpdateRequest activityUserUpdateRequest) {
        if (activityUserUpdateRequest == null || activityUserUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ActivityUser activityUser = new ActivityUser();
        BeanUtils.copyProperties(activityUserUpdateRequest, activityUser);
        long id = activityUserUpdateRequest.getId();
        // 判断是否存在
        ActivityUser oldActivityUser = activityUserService.getById(id);
        ThrowUtils.throwIf(oldActivityUser == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = activityUserService.updateById(activityUser);
        return ResultUtils.success(result);
    }



    /**
     * 分页获取列表（仅管理员）
     *
     * @param activityUserQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<ActivityUser>> listActivityUserByPage(@RequestBody ActivityUserQueryRequest activityUserQueryRequest) {
        long current = activityUserQueryRequest.getCurrent();
        long size = activityUserQueryRequest.getPageSize();
        Page<ActivityUser> activityUserPage = activityUserService.page(new Page<>(current, size),
                activityUserService.getQueryWrapper(activityUserQueryRequest));
        return ResultUtils.success(activityUserPage);
    }


    // endregion

    /**
     * 编辑（用户）
     *
     * @param activityUserEditRequest
     * @return
     */
    @PostMapping("/edit")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> editActivityUser(@RequestBody ActivityUserEditRequest activityUserEditRequest) {
        if (activityUserEditRequest == null || activityUserEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ActivityUser activityUser = new ActivityUser();
        BeanUtils.copyProperties(activityUserEditRequest, activityUser);
        long id = activityUserEditRequest.getId();
        // 判断是否存在
        ActivityUser oldActivityUser = activityUserService.getById(id);
        ThrowUtils.throwIf(oldActivityUser == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = activityUserService.updateById(activityUser);
        return ResultUtils.success(result);
    }

}
