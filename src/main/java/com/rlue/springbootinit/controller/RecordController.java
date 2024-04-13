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
import com.rlue.springbootinit.model.dto.record.RecordAddRequest;
import com.rlue.springbootinit.model.dto.record.RecordEditRequest;
import com.rlue.springbootinit.model.dto.record.RecordQueryRequest;
import com.rlue.springbootinit.model.dto.record.RecordUpdateRequest;
import com.rlue.springbootinit.model.entity.Record;
import com.rlue.springbootinit.model.entity.User;
import com.rlue.springbootinit.service.RecordService;
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
 * 问诊报告接口
 *

 */
@RestController
@RequestMapping("/record")
@Slf4j
public class RecordController {

    @Resource
    private RecordService recordService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param recordAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addRecord(@RequestBody RecordAddRequest recordAddRequest, HttpServletRequest request) {
        if (recordAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Record record = new Record();
        BeanUtils.copyProperties(recordAddRequest, record);
        User loginUser = userService.getLoginUser(request);
        record.setUserId(loginUser.getId());
        boolean result = recordService.save(record);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newRecordId = record.getId();
        return ResultUtils.success(newRecordId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteRecord(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Record oldRecord = recordService.getById(id);
        ThrowUtils.throwIf(oldRecord == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅活动发起人或管理员可删除
        if (!oldRecord.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = recordService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param recordUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateRecord(@RequestBody RecordUpdateRequest recordUpdateRequest) {
        if (recordUpdateRequest == null || recordUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Record record = new Record();
        BeanUtils.copyProperties(recordUpdateRequest, record);
        long id = recordUpdateRequest.getId();
        // 判断是否存在
        Record oldRecord = recordService.getById(id);
        ThrowUtils.throwIf(oldRecord == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = recordService.updateById(record);
        return ResultUtils.success(result);
    }



    /**
     * 分页获取列表（仅管理员）
     *
     * @param recordQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Record>> listRecordByPage(@RequestBody RecordQueryRequest recordQueryRequest) {
        long current = recordQueryRequest.getCurrent();
        long size = recordQueryRequest.getPageSize();
        Page<Record> recordPage = recordService.page(new Page<>(current, size),
                recordService.getQueryWrapper(recordQueryRequest));
        return ResultUtils.success(recordPage);
    }


    // endregion

    /**
     * 编辑（用户）
     *
     * @param recordEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editRecord(@RequestBody RecordEditRequest recordEditRequest, HttpServletRequest request) {
        if (recordEditRequest == null || recordEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Record record = new Record();
        BeanUtils.copyProperties(recordEditRequest, record);
        User loginUser = userService.getLoginUser(request);
        long id = recordEditRequest.getId();
        // 判断是否存在
        Record oldRecord = recordService.getById(id);
        ThrowUtils.throwIf(oldRecord == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅活动发起人或管理员可编辑
        if (!oldRecord.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = recordService.updateById(record);
        return ResultUtils.success(result);
    }

}
