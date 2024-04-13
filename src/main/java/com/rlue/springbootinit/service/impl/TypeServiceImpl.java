package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.common.ErrorCode;
import com.rlue.springbootinit.constant.CommonConstant;
import com.rlue.springbootinit.exception.BusinessException;
import com.rlue.springbootinit.model.dto.type.TypeQueryRequest;
import com.rlue.springbootinit.model.entity.Activity;
import com.rlue.springbootinit.model.entity.Type;
import com.rlue.springbootinit.service.TypeService;
import com.rlue.springbootinit.mapper.TypeMapper;
import com.rlue.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【type(文章类别)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:06
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{


    @Override
    public QueryWrapper<Type> getQueryWrapper(TypeQueryRequest typeQueryRequest) {
        if (typeQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = typeQueryRequest.getId();
        String typeName = typeQueryRequest.getTypeName();
        String sortField = typeQueryRequest.getSortField();
        String sortOrder = typeQueryRequest.getSortOrder();
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null,"id",id);
        queryWrapper.like(StringUtils.isNotBlank(typeName), "typeName", typeName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




