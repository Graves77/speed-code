package com.code.member.controller;

import com.code.common.entities.R;
import com.code.common.exception.BizCodeEnum;
import com.code.common.valid.loginGroup;
import com.code.common.valid.registerGroup;
import com.code.member.entity.User;
import com.code.member.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
