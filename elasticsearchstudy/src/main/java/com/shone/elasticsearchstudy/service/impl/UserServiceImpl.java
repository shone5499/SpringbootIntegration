package com.shone.elasticsearchstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shone.elasticsearchstudy.entity.User;
import com.shone.elasticsearchstudy.mapper.UserMapper;
import com.shone.elasticsearchstudy.service.IUserService;
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
