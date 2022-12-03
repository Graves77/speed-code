package com.code.member.controller;

import com.code.common.entities.R;
import com.code.member.entity.User;
import com.code.member.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public R sign(@Valid @RequestBody User user){
        userService.save(user);
        return R.ok("注册成功");
    }
}
