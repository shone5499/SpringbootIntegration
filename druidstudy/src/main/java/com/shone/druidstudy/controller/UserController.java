package com.shone.druidstudy.controller;


import com.shone.druidstudy.common.response.MyResponse;
import com.shone.druidstudy.entity.User;
import com.shone.druidstudy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.shone.druidstudy.controller.BaseController;

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
