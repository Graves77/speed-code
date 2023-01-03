package com.code.member.controller;


import com.code.member.entity.R;
import com.code.member.entity.User;
import com.code.member.exception.BizCodeEnum;
import com.code.member.service.UserService;

import com.code.member.valid.registerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@ResponseBody
@RestController
@RequestMapping("/user")
public class UserRegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public R register(@Validated({registerGroup.class}) @RequestBody User user){
        Integer exit = userService.selectByPhone(user.getPhone());
        if(exit!=null){
            return R.error(BizCodeEnum.PHONE_EXISTS_EXCEPTION.getCode(), BizCodeEnum.PHONE_EXISTS_EXCEPTION.getMsg());
        }
        else
        {
            userService.save(user);
            return R.ok("注册成功");
        }

    }

}
