package com.code.generator.service.serviceImpl;

import java.util.List;
import com.code.generator.pojo.User;
import com.code.generator.service.UserService;
import com.code.generator.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 217team
 * @version 1.0
 * @description: 用于实现UserService接口中的函数
 * @date 2022-12-28
 */
@Service
public class UserServiceImpl implements UserService {
@Autowired
    UserMapper userMapper;

/**
 * @return 以列表形式返回实体类对象
 * @description 查询所有数据
 * @author 217team
 * @date 2022-12-28
 */
@Override
public List<User> listUser(){
        return userMapper.listUser();
        }

/**
 * @param id 主键id
 * @return 实体类对象
 * @description 根据id获取单条数据
 * @author 217team
 * @date 2022-12-28
 */
@Override
public User getUserById(Integer id){
        return userMapper.getUserById(id);
        }

/**
 * @param page 页数
 * @param limit 每页限制数据量
 * @return 以列表形式返回实体类对象
 * @description 分页查询数据
 * @author 217team
 * @date 2022-12-28
 */
@Override
public List<User> listUserByPage(int page, int limit){
        int first = (page - 1) * limit;
        int second = limit;
        return userMapper.listUserByPage(first,second);
        }

/**
 * @param user 要添加的实体类
 * @return 大于等于1则插入成功
 * @description 插入数据
 * @author 217team
 * @date 2022-12-28
 */
@Override
public int insertUser(User user){
        return userMapper.insertUser(user);
        }

/**
 * @param user 要修改的实体类
 * @return 大于等于1则修改成功
 * @description 根据id修改数据
 * @author 217team
 * @date 2022-12-28
 */
@Override
public int updateUserById(User user){
        return userMapper.updateUserById(user);
        }

/**
 * @param id 主键id
 * @return 大于等于1则删除成功
 * @description 根据id删除数据
 * @author 217team
 * @date 2022-12-28
 */
@Override
public int deleteUserById(Integer id){
        return userMapper.deleteUserById(id);
        }
        }