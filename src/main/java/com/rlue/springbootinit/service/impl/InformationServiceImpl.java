package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.common.ErrorCode;
import com.rlue.springbootinit.constant.CommonConstant;
import com.rlue.springbootinit.exception.BusinessException;
import com.rlue.springbootinit.model.dto.information.InformationQueryRequest;
import com.rlue.springbootinit.model.entity.Activity;
import com.rlue.springbootinit.model.entity.Information;
import com.rlue.springbootinit.service.InformationService;
import com.rlue.springbootinit.mapper.InformationMapper;
import com.rlue.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【information(资讯)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:05
*/
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information>
    implements InformationService{

    @Override
    public QueryWrapper<Information> getQueryWrapper(InformationQueryRequest informationQueryRequest) {
        if (informationQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = informationQueryRequest.getId();
        String title = informationQueryRequest.getTitle();
        String type = informationQueryRequest.getType();
        String sortField = informationQueryRequest.getSortField();
        String sortOrder = informationQueryRequest.getSortOrder();
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        queryWrapper.like(StringUtils.isNotBlank(type), "type", type);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




