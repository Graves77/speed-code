package com.code.member.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.member.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserDao extends BaseMapper<User> {
    void save(@Param("password") String password, @Param("email") String email, @Param("phone") String  phone);

    @Select("SELECT `password`  from `user`  where phone=#{phone}")
    String findPassword(@Param("phone") String phone);


    @Select("SELECT 1 FROM `user` WHERE phone=#{phone} LIMIT 1")
    Integer selectUserByPhone(@Param("phone")String phone);

    @Select("SELECT id FROM `user` WHERE phone=#{phone} ")
    long selectUserIdByPhone(@Param("phone") String phone);
}
