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
import com.rlue.springbootinit.model.dto.type.TypeAddRequest;
import com.rlue.springbootinit.model.dto.type.TypeEditRequest;
import com.rlue.springbootinit.model.dto.type.TypeQueryRequest;
import com.rlue.springbootinit.model.dto.type.TypeUpdateRequest;
import com.rlue.springbootinit.model.entity.Type;
import com.rlue.springbootinit.service.TypeService;
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
@RequestMapping("/type")
@Slf4j
public class TypeController {

    @Resource
    private TypeService typeService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param typeAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addType(@RequestBody TypeAddRequest typeAddRequest) {
        if (typeAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Type type = new Type();
        BeanUtils.copyProperties(typeAddRequest, type);

        boolean result = typeService.save(type);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newTypeId = type.getId();
        return ResultUtils.success(newTypeId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteType(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long id = deleteRequest.getId();
        // 判断是否存在
        Type oldType = typeService.getById(id);
        ThrowUtils.throwIf(oldType == null, ErrorCode.NOT_FOUND_ERROR);

        boolean b = typeService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param typeUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateType(@RequestBody TypeUpdateRequest typeUpdateRequest) {
        if (typeUpdateRequest == null || typeUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Type type = new Type();
        BeanUtils.copyProperties(typeUpdateRequest, type);
        long id = typeUpdateRequest.getId();
        // 判断是否存在
        Type oldType = typeService.getById(id);
        ThrowUtils.throwIf(oldType == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = typeService.updateById(type);
        return ResultUtils.success(result);
    }



    /**
     * 分页获取列表（仅管理员）
     *
     * @param typeQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<Type>> listTypeByPage(@RequestBody TypeQueryRequest typeQueryRequest) {
        long current = typeQueryRequest.getCurrent();
        long size = typeQueryRequest.getPageSize();
        Page<Type> typePage = typeService.page(new Page<>(current, size),
                typeService.getQueryWrapper(typeQueryRequest));
        return ResultUtils.success(typePage);
    }


    // endregion

    /**
     * 编辑（用户）
     *
     * @param typeEditRequest
     * @return
     */
    @PostMapping("/edit")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> editType(@RequestBody TypeEditRequest typeEditRequest) {
        if (typeEditRequest == null || typeEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Type type = new Type();
        BeanUtils.copyProperties(typeEditRequest, type);
        long id = typeEditRequest.getId();
        // 判断是否存在
        Type oldType = typeService.getById(id);
        ThrowUtils.throwIf(oldType == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = typeService.updateById(type);
        return ResultUtils.success(result);
    }

}
