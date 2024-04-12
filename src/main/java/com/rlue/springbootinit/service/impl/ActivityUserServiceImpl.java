package com.rlue.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rlue.springbootinit.model.entity.ActivityUser;
import com.rlue.springbootinit.service.ActivityUserService;
import com.rlue.springbootinit.mapper.ActivityUserMapper;
import org.springframework.stereotype.Service;

/**
* @author hgdc
* @description 针对表【activity_user(活动报名人)】的数据库操作Service实现
* @createDate 2024-04-10 21:46:17
*/
@Service
public class ActivityUserServiceImpl extends ServiceImpl<ActivityUserMapper, ActivityUser>
    implements ActivityUserService{

}




