package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.model.entity.Reservation;
import com.rlue.springbootinit.service.ReservationService;
import com.rlue.springbootinit.mapper.ReservationMapper;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【reservation(服务预约)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:06
*/
@Service
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation>
    implements ReservationService{

}




