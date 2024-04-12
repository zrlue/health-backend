package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.model.entity.Location;
import com.rlue.springbootinit.service.LocationService;
import com.rlue.springbootinit.mapper.LocationMapper;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【location(一键求救)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:06
*/
@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location>
    implements LocationService{

}




