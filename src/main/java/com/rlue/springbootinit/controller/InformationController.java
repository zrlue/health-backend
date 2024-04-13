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
import com.rlue.springbootinit.model.dto.information.InformationAddRequest;
import com.rlue.springbootinit.model.dto.information.InformationEditRequest;
import com.rlue.springbootinit.model.dto.information.InformationQueryRequest;
import com.rlue.springbootinit.model.dto.information.InformationUpdateRequest;
import com.rlue.springbootinit.model.entity.Information;
import com.rlue.springbootinit.service.InformationService;
import com.rlue.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章内容接口
 *

 */
@RestController
@RequestMapping("/information")
@Slf4j
public class InformationController {

    @Resource
    private InformationService informationService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param informationAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addInformation(@RequestBody InformationAddRequest informationAddRequest) {
        if (informationAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Information information = new Information();
        BeanUtils.copyProperties(informationAddRequest, information);

        boolean result = informationService.save(information);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newInformationId = information.getId();
        return ResultUtils.success(newInformationId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteInformation(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long id = deleteRequest.getId();
        // 判断是否存在
        Information oldInformation = informationService.getById(id);
        ThrowUtils.throwIf(oldInformation == null, ErrorCode.NOT_FOUND_ERROR);

        boolean b = informationService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param informationUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateInformation(@RequestBody InformationUpdateRequest informationUpdateRequest) {
        if (informationUpdateRequest == null || informationUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Information information = new Information();
        BeanUtils.copyProperties(informationUpdateRequest, information);
        long id = informationUpdateRequest.getId();
        // 判断是否存在
        Information oldInformation = informationService.getById(id);
        ThrowUtils.throwIf(oldInformation == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = informationService.updateById(information);
        return ResultUtils.success(result);
    }



    /**
     * 分页获取列表（仅管理员）
     *
     * @param informationQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<Information>> listInformationByPage(@RequestBody InformationQueryRequest informationQueryRequest) {
        long current = informationQueryRequest.getCurrent();
        long size = informationQueryRequest.getPageSize();
        Page<Information> informationPage = informationService.page(new Page<>(current, size),
                informationService.getQueryWrapper(informationQueryRequest));
        return ResultUtils.success(informationPage);
    }


    // endregion

    /**
     * 编辑（用户）
     *
     * @param informationEditRequest
     * @return
     */
    @PostMapping("/edit")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> editInformation(@RequestBody InformationEditRequest informationEditRequest) {
        if (informationEditRequest == null || informationEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Information information = new Information();
        BeanUtils.copyProperties(informationEditRequest, information);
        long id = informationEditRequest.getId();
        // 判断是否存在
        Information oldInformation = informationService.getById(id);
        ThrowUtils.throwIf(oldInformation == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = informationService.updateById(information);
        return ResultUtils.success(result);
    }

}
