package com.code.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.member.dao.UserDao;
import com.code.member.entity.User;
import com.code.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;


@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserDao, User>implements UserService {
    @Autowired
    private UserDao userDao;

//    @Override
//    public Boolean signUser(String password,String confirm,String phone,String email) {
//        User user = new User();
//        if(!confirm.equals(password)){
//            return false;
//        }
//        userDao.save(password, email, phone);
//        return true;
//    }

    @Override
    public Boolean login(User user) {
        String phone = user.getPhone();
        String password = user.getPassword();
        String truePassword =userDao.findPassword(phone);
        //User userByPhone = userDao.selectUserByPhone(phone);
        if(password.equals(truePassword)){
            return true;
        }
        else return false;
    }

    @Override
    public Integer selectByPhone(String phone) {
        return userDao.selectUserByPhone(phone);
    }

    @Override
    public long loginIdByphone(User user) {
        return userDao.selectUserIdByPhone(user.getPhone());
    }

}
