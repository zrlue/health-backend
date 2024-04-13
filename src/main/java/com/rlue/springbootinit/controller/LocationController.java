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
import com.rlue.springbootinit.model.dto.location.LocationAddRequest;
import com.rlue.springbootinit.model.dto.location.LocationEditRequest;
import com.rlue.springbootinit.model.dto.location.LocationQueryRequest;
import com.rlue.springbootinit.model.dto.location.LocationUpdateRequest;
import com.rlue.springbootinit.model.entity.Location;
import com.rlue.springbootinit.model.entity.User;
import com.rlue.springbootinit.service.LocationService;
import com.rlue.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 一键求救接口
 *

 */
@RestController
@RequestMapping("/location")
@Slf4j
public class LocationController {

    @Resource
    private LocationService locationService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param locationAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addLocation(@RequestBody LocationAddRequest locationAddRequest, HttpServletRequest request) {
        if (locationAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Location location = new Location();
        BeanUtils.copyProperties(locationAddRequest, location);
        User loginUser = userService.getLoginUser(request);
        location.setUserId(loginUser.getId());
        boolean result = locationService.save(location);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newLocationId = location.getId();
        return ResultUtils.success(newLocationId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteLocation(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Location oldLocation = locationService.getById(id);
        ThrowUtils.throwIf(oldLocation == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅活动发起人或管理员可删除
        if (!oldLocation.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = locationService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param locationUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateLocation(@RequestBody LocationUpdateRequest locationUpdateRequest) {
        if (locationUpdateRequest == null || locationUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Location location = new Location();
        BeanUtils.copyProperties(locationUpdateRequest, location);
        long id = locationUpdateRequest.getId();
        // 判断是否存在
        Location oldLocation = locationService.getById(id);
        ThrowUtils.throwIf(oldLocation == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = locationService.updateById(location);
        return ResultUtils.success(result);
    }



    /**
     * 分页获取列表（仅管理员）
     *
     * @param locationQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Location>> listLocationByPage(@RequestBody LocationQueryRequest locationQueryRequest) {
        long current = locationQueryRequest.getCurrent();
        long size = locationQueryRequest.getPageSize();
        Page<Location> locationPage = locationService.page(new Page<>(current, size),
                locationService.getQueryWrapper(locationQueryRequest));
        return ResultUtils.success(locationPage);
    }


    // endregion

    /**
     * 编辑（用户）
     *
     * @param locationEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editLocation(@RequestBody LocationEditRequest locationEditRequest, HttpServletRequest request) {
        if (locationEditRequest == null || locationEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Location location = new Location();
        BeanUtils.copyProperties(locationEditRequest, location);
        User loginUser = userService.getLoginUser(request);
        long id = locationEditRequest.getId();
        // 判断是否存在
        Location oldLocation = locationService.getById(id);
        ThrowUtils.throwIf(oldLocation == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅活动发起人或管理员可编辑
        if (!oldLocation.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = locationService.updateById(location);
        return ResultUtils.success(result);
    }

}
