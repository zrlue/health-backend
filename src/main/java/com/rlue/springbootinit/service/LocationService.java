package com.rlue.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rlue.springbootinit.model.dto.location.LocationQueryRequest;
import com.rlue.springbootinit.model.entity.Location;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hgdc
* @description 针对表【location(一键求救)】的数据库操作Service
* @createDate 2024-04-10 21:27:06
*/
public interface LocationService extends IService<Location> {

    QueryWrapper<Location> getQueryWrapper(LocationQueryRequest locationQueryRequest);
}
