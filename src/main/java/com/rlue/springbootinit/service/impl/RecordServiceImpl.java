package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.common.ErrorCode;
import com.rlue.springbootinit.constant.CommonConstant;
import com.rlue.springbootinit.exception.BusinessException;
import com.rlue.springbootinit.model.dto.record.RecordQueryRequest;
import com.rlue.springbootinit.model.entity.Activity;
import com.rlue.springbootinit.model.entity.Record;
import com.rlue.springbootinit.service.RecordService;
import com.rlue.springbootinit.mapper.RecordMapper;
import com.rlue.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【record(健康档案)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:06
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{

    @Override
    public QueryWrapper<Record> getQueryWrapper(RecordQueryRequest recordQueryRequest) {
        if (recordQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = recordQueryRequest.getId();
        Long userId = recordQueryRequest.getUserId();
        Long opeId = recordQueryRequest.getOpeId();
        String sortField = recordQueryRequest.getSortField();
        String sortOrder = recordQueryRequest.getSortOrder();
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(userId != null, "userId", userId);
        queryWrapper.eq(opeId != null, "opeId", opeId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




