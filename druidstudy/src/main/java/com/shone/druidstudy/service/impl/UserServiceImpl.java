package com.shone.druidstudy.service.impl;

import com.shone.druidstudy.entity.User;
import com.shone.druidstudy.mapper.UserMapper;
import com.shone.druidstudy.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author xiaoguojian
 * @since 2020-07-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
