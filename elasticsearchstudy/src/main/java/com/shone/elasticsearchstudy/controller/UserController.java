package com.shone.elasticsearchstudy.controller;


import com.shone.elasticsearchstudy.common.response.MyResponse;
import com.shone.elasticsearchstudy.entity.User;
import com.shone.elasticsearchstudy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author xiaoguojian
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/list")
    public MyResponse<List<User>> list(){
        return new MyResponse<>(iUserService.list());
    }

}
