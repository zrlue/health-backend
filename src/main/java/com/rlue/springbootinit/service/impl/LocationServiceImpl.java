package com.rlue.springbootinit.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.common.ErrorCode;
import com.rlue.springbootinit.constant.CommonConstant;
import com.rlue.springbootinit.exception.BusinessException;
import com.rlue.springbootinit.model.dto.location.LocationQueryRequest;
import com.rlue.springbootinit.model.entity.Activity;
import com.rlue.springbootinit.model.entity.Location;
import com.rlue.springbootinit.service.LocationService;
import com.rlue.springbootinit.mapper.LocationMapper;
import com.rlue.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【location(一键求救)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:06
*/
@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location>
    implements LocationService{

    @Override
    public QueryWrapper<Location> getQueryWrapper(LocationQueryRequest locationQueryRequest) {
        if (locationQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = locationQueryRequest.getId();
        Long userId = locationQueryRequest.getUserId();
        Integer status = locationQueryRequest.getStatus();
        String sortField = locationQueryRequest.getSortField();
        String sortOrder = locationQueryRequest.getSortOrder();
        QueryWrapper<Location> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(userId != null, "userId", userId);
        queryWrapper.eq(status != null, "status", status);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




