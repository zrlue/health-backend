package com.rlue.springbootinit.service.impl;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.common.ErrorCode;
import com.rlue.springbootinit.constant.CommonConstant;
import com.rlue.springbootinit.exception.BusinessException;
import com.rlue.springbootinit.model.dto.healthyService.HealthyServiceQueryRequest;
import com.rlue.springbootinit.model.entity.Activity;
import com.rlue.springbootinit.model.entity.HealthyService;
import com.rlue.springbootinit.service.HealthyServiceService;
import com.rlue.springbootinit.mapper.HealthyServiceMapper;
import com.rlue.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author hgdc
 * @description 针对表【healthy_service(健康服务)】的数据库操作Service实现
 * @createDate 2024-04-10 21:27:05
 */
@Service
public class HealthyServiceServiceImpl extends ServiceImpl<HealthyServiceMapper, HealthyService>
        implements HealthyServiceService {

    @Override
    public QueryWrapper<HealthyService> getQueryWrapper(HealthyServiceQueryRequest healthyServiceQueryRequest) {
        if (healthyServiceQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = healthyServiceQueryRequest.getId();
        String serviceName = healthyServiceQueryRequest.getServiceName();
        String tel = healthyServiceQueryRequest.getTel();
        String sortField = healthyServiceQueryRequest.getSortField();
        String sortOrder = healthyServiceQueryRequest.getSortOrder();
        QueryWrapper<HealthyService> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.like(StringUtils.isNotBlank(serviceName), "serviceName", serviceName);
        queryWrapper.like(StringUtils.isNotBlank(tel), "tel", tel);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




