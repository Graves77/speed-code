package com.code.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.code.member.entity.User;
import org.springframework.stereotype.Service;


public interface UserService extends IService<User> {

    //Boolean signUser(String password, String confirm, String phone, String email);

    Boolean login(User user);

    Integer selectByPhone(String phone);

    long loginIdByphone(User user);
}
