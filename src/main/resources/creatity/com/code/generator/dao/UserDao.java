package com.code.generator.dao;

import java.util.List;
import java.util.Map;
public interface UserDao {
    /**
     * 查询
     */
    List<Map> getUserList();
    /**
     * 添加
     */
    int addUser(Map map);
    /**
     * 待修改
     */
    Map toEditUser(String name);
    /**
     * 修改
     */
    int updateUser(Map map);
    /**
     * 删除
     */
    int deleteUser(String name);
}
