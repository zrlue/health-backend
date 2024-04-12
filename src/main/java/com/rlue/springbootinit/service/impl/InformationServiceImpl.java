package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.model.entity.Information;
import com.rlue.springbootinit.service.InformationService;
import com.rlue.springbootinit.mapper.InformationMapper;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【information(资讯)】的数据库操作Service实现
* @createDate 2024-04-10 21:27:05
*/
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information>
    implements InformationService{

}




