package com.rlue.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rlue.springbootinit.model.dto.reservation.ReservationQueryRequest;
import com.rlue.springbootinit.model.entity.Reservation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hgdc
* @description 针对表【reservation(服务预约)】的数据库操作Service
* @createDate 2024-04-10 21:27:06
*/
public interface ReservationService extends IService<Reservation> {

    QueryWrapper<Reservation> getQueryWrapper(ReservationQueryRequest reservationQueryRequest);
}
