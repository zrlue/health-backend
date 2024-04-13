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
import com.rlue.springbootinit.model.dto.healthyService.HealthyServiceAddRequest;
import com.rlue.springbootinit.model.dto.healthyService.HealthyServiceEditRequest;
import com.rlue.springbootinit.model.dto.healthyService.HealthyServiceQueryRequest;
import com.rlue.springbootinit.model.dto.healthyService.HealthyServiceUpdateRequest;
import com.rlue.springbootinit.model.entity.HealthyService;
import com.rlue.springbootinit.service.HealthyServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 健康服务接口
 *
 */
@RestController
@RequestMapping("/healthyService")
@Slf4j
public class HealthyServiceController {

    @Resource
    private HealthyServiceService healthyServiceService;
    
    // region 增删改查

    /**
     * 创建
     *
     * @param healthyServiceAddRequest
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addHealthyService(@RequestBody HealthyServiceAddRequest healthyServiceAddRequest) {
        if (healthyServiceAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        HealthyService healthyService = new HealthyService();
        BeanUtils.copyProperties(healthyServiceAddRequest, healthyService);

        boolean result = healthyServiceService.save(healthyService);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newHealthyServiceId = healthyService.getId();
        return ResultUtils.success(newHealthyServiceId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteHealthyService(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long id = deleteRequest.getId();
        // 判断是否存在
        HealthyService oldHealthyService = healthyServiceService.getById(id);
        ThrowUtils.throwIf(oldHealthyService == null, ErrorCode.NOT_FOUND_ERROR);

        boolean b = healthyServiceService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param healthyServiceUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateHealthyService(@RequestBody HealthyServiceUpdateRequest healthyServiceUpdateRequest) {
        if (healthyServiceUpdateRequest == null || healthyServiceUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        HealthyService healthyService = new HealthyService();
        BeanUtils.copyProperties(healthyServiceUpdateRequest, healthyService);
        long id = healthyServiceUpdateRequest.getId();
        // 判断是否存在
        HealthyService oldHealthyService = healthyServiceService.getById(id);
        ThrowUtils.throwIf(oldHealthyService == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = healthyServiceService.updateById(healthyService);
        return ResultUtils.success(result);
    }



    /**
     * 分页获取列表（仅管理员）
     *
     * @param healthyServiceQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<HealthyService>> listHealthyServiceByPage(@RequestBody HealthyServiceQueryRequest healthyServiceQueryRequest) {
        long current = healthyServiceQueryRequest.getCurrent();
        long size = healthyServiceQueryRequest.getPageSize();
        Page<HealthyService> healthyServicePage = healthyServiceService.page(new Page<>(current, size),
                healthyServiceService.getQueryWrapper(healthyServiceQueryRequest));
        return ResultUtils.success(healthyServicePage);
    }


    // endregion

    /**
     * 编辑（用户）
     *
     * @param healthyServiceEditRequest
     * @return
     */
    @PostMapping("/edit")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> editHealthyService(@RequestBody HealthyServiceEditRequest healthyServiceEditRequest) {
        if (healthyServiceEditRequest == null || healthyServiceEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        HealthyService healthyService = new HealthyService();
        BeanUtils.copyProperties(healthyServiceEditRequest, healthyService);
        long id = healthyServiceEditRequest.getId();
        // 判断是否存在
        HealthyService oldHealthyService = healthyServiceService.getById(id);
        ThrowUtils.throwIf(oldHealthyService == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = healthyServiceService.updateById(healthyService);
        return ResultUtils.success(result);
    }

}
