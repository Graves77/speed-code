package com.code.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.member.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserDao extends BaseMapper<User> {
    void save(@Param("password") String password, @Param("email") String email, @Param("phone") String  phone);
}
