package com.rlue.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rlue.springbootinit.model.dto.type.TypeQueryRequest;
import com.rlue.springbootinit.model.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hgdc
* @description 针对表【type(文章类别)】的数据库操作Service
* @createDate 2024-04-10 21:27:06
*/
public interface TypeService extends IService<Type> {

    QueryWrapper<Type> getQueryWrapper(TypeQueryRequest typeQueryRequest);
}
