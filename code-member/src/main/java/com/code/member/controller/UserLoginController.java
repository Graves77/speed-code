package com.code.member.controller;


import com.code.member.entity.R;
import com.code.member.entity.User;
import com.code.member.exception.BizCodeEnum;
import com.code.member.service.UserService;
import com.code.member.utils.JwtUtils;
import com.code.member.valid.loginGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@ResponseBody
@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/test")
    public String test(){
        return "test is ok!";
    }

    @RequestMapping("/login")
    public R login(@Validated({loginGroup.class}) @RequestBody User user){


        if(userService.login(user)){
            //用户登录
            //long userId = userService.loginIdByphone(user);
            //生成token
            String token = jwtUtils.generateToken(user.getPhone());
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("expire", jwtUtils.getExpire());
            return R.ok(map);
        }
        else return R.error(BizCodeEnum.LOGIN_ACCOUNT_PASSWORD_INVALID.getCode(),BizCodeEnum.LOGIN_ACCOUNT_PASSWORD_INVALID.getMsg());
    }
}
