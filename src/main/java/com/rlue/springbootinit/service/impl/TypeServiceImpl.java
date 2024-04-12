package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.model.entity.Type;
import com.rlue.springbootinit.service.TypeService;
import com.rlue.springbootinit.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【type(文章类别)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:06
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




