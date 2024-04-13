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
import com.rlue.springbootinit.model.dto.reservation.ReservationAddRequest;
import com.rlue.springbootinit.model.dto.reservation.ReservationEditRequest;
import com.rlue.springbootinit.model.dto.reservation.ReservationQueryRequest;
import com.rlue.springbootinit.model.dto.reservation.ReservationUpdateRequest;
import com.rlue.springbootinit.model.entity.Reservation;
import com.rlue.springbootinit.model.entity.User;
import com.rlue.springbootinit.service.ReservationService;
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
 * 社区活动接口
 *

 */
@RestController
@RequestMapping("/reservation")
@Slf4j
public class ReservationController {

    @Resource
    private ReservationService reservationService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param reservationAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addReservation(@RequestBody ReservationAddRequest reservationAddRequest, HttpServletRequest request) {
        if (reservationAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationAddRequest, reservation);
        User loginUser = userService.getLoginUser(request);
        reservation.setUserId(loginUser.getId());
        boolean result = reservationService.save(reservation);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newReservationId = reservation.getId();
        return ResultUtils.success(newReservationId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteReservation(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Reservation oldReservation = reservationService.getById(id);
        ThrowUtils.throwIf(oldReservation == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅活动发起人或管理员可删除
        if (!oldReservation.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = reservationService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param reservationUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateReservation(@RequestBody ReservationUpdateRequest reservationUpdateRequest) {
        if (reservationUpdateRequest == null || reservationUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationUpdateRequest, reservation);
        long id = reservationUpdateRequest.getId();
        // 判断是否存在
        Reservation oldReservation = reservationService.getById(id);
        ThrowUtils.throwIf(oldReservation == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = reservationService.updateById(reservation);
        return ResultUtils.success(result);
    }



    /**
     * 分页获取列表（仅管理员）
     *
     * @param reservationQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Reservation>> listReservationByPage(@RequestBody ReservationQueryRequest reservationQueryRequest) {
        long current = reservationQueryRequest.getCurrent();
        long size = reservationQueryRequest.getPageSize();
        Page<Reservation> reservationPage = reservationService.page(new Page<>(current, size),
                reservationService.getQueryWrapper(reservationQueryRequest));
        return ResultUtils.success(reservationPage);
    }


    // endregion

    /**
     * 编辑（用户）
     *
     * @param reservationEditRequest
     * @param request
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editReservation(@RequestBody ReservationEditRequest reservationEditRequest, HttpServletRequest request) {
        if (reservationEditRequest == null || reservationEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationEditRequest, reservation);
        User loginUser = userService.getLoginUser(request);
        long id = reservationEditRequest.getId();
        // 判断是否存在
        Reservation oldReservation = reservationService.getById(id);
        ThrowUtils.throwIf(oldReservation == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅活动发起人或管理员可编辑
        if (!oldReservation.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = reservationService.updateById(reservation);
        return ResultUtils.success(result);
    }

}
