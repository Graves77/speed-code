package com.code.generator.service;

import com.code.generator.pojo.User;
import java.util.List;

/**
 * @author 217team
 * @version 1.0
 * @description: 用于User的Service提供接口
 * @date 2022-12-28
 */
public interface UserService {

    /**
     * @description 查询所有数据
     * @author 217team
     * @date 2022-12-28
     */
    List<User> listUser();

    /**
     * @description 根据id获取单条数据
     * @author 217team
     * @date 2022-12-28
     */
    User getUserById(Integer id);

    /**
     * @description 分页查询数据
     * @author 217team
     * @date 2022-12-28
     */
    List<User> listUserByPage(int page, int limit);

    /**
     * @description 插入数据
     * @author 217team
     * @date 2022-12-28
     */
    int insertUser(User user);

    /**
     * @description 根据id修改数据
     * @author 217team
     * @date 2022-12-28
     */
    int updateUserById(User user);

    /**
     * @description 根据id删除数据
     * @author 217team
     * @date 2022-12-28
     */
    int deleteUserById(Integer id);
}
Footer
