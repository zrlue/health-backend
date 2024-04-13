package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.common.ErrorCode;
import com.rlue.springbootinit.constant.CommonConstant;
import com.rlue.springbootinit.exception.BusinessException;
import com.rlue.springbootinit.model.dto.reservation.ReservationQueryRequest;
import com.rlue.springbootinit.model.entity.Activity;
import com.rlue.springbootinit.model.entity.Reservation;
import com.rlue.springbootinit.service.ReservationService;
import com.rlue.springbootinit.mapper.ReservationMapper;
import com.rlue.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【reservation(服务预约)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:06
*/
@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation>
    implements ReservationService{

    @Override
    public QueryWrapper<Reservation> getQueryWrapper(ReservationQueryRequest reservationQueryRequest) {
        if (reservationQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = reservationQueryRequest.getId();
        Long serviceId = reservationQueryRequest.getServiceId();
        Long userId = reservationQueryRequest.getUserId();
        String number = reservationQueryRequest.getNumber();
        Integer status = reservationQueryRequest.getStatus();
        String sortField = reservationQueryRequest.getSortField();
        String sortOrder = reservationQueryRequest.getSortOrder();
        QueryWrapper<Reservation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(serviceId != null, "serviceId", serviceId);
        queryWrapper.eq(userId != null, "userId", userId);
        queryWrapper.eq(number != null, "number", number);
        queryWrapper.eq(status != null, "status", status);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




