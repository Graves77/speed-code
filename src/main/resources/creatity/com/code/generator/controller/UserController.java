package com.code.generator.controller;
import com.code.generator.pojo.User;
import com.code.generator.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;
import java.util.List;

/**
 * @author 217team
 * @version 1.0
 * @description User的Controller层
 * @data 2022-12-28
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
            UserService userService;

    /**
     * @param user 添加的实体类
     * @return Map<String, Object> 自定义响应体
     * @description 添加数据
     * @author 217team
     * @data 2022-12-28
     */
    @PostMapping("/insert")
    @ApiOperation(value = "添加数据")
    public Map<String, Object> insertUser(@Valid User user) {
        try {
            if( userService.insertUser(user) >= 1 ){
                return StatusCode.success("添加成功");
            }else{
                return StatusCode.success("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return StatusCode.error("服务器内部错误：" + e.toString());
        }
    }


    /**
     * @param id 主键id
     * @return Map<String, Object> 自定义响应体
     * @description 根据id获取单条数据
     * @author 217team
     * @data 2022-12-28
     */
    @GetMapping("/get")
    @ApiOperation(value = "根据id获取单条数据")
    public Map<String, Object> getUserById(@RequestParam("id") Integer id) {
        try {
            return StatusCode.success(userService.getUserById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return StatusCode.error(3001, "服务器内部错误：" + e.toString());
        }
    }

    /**
     * @param page 查询的页数
     * @return Map<String, Object> 自定义响应体
     * @description 分页查询数据（备注：limit默认为10）
     * @author 217team
     * @data 2022-12-28
     */
    @GetMapping("/list/page")
    @ApiOperation(value = "分页查询数据")
    public Map<String, Object> listUserByPage(@RequestParam("page") int page) {
        try {
            //limit默认为10
            return StatusCode.success(userService.listUserByPage(page,10));
        } catch (Exception e) {
            e.printStackTrace();
            return StatusCode.error("服务器内部错误：" + e.toString());
        }
    }

    /**
     * @param user 需要修改的实体类
     * @return Map<String, Object> 自定义响应体
     * @description 根据id修改数据
     * @author 217team
     * @data 2022-12-28
     */
    @PutMapping("/update")
    @ApiOperation(value = "根据id修改数据")
    public Map<String, Object> updateUserById(@Valid User user) {
        try {
            if( userService.updateUserById(user) >= 1 ){
                return StatusCode.success("修改成功");
            }else{
                return StatusCode.success("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return StatusCode.error("服务器内部错误：" + e.toString());
        }
    }

    /**
     * @return Map<String, Object> 自定义响应体
     * @description 查询所有数据（备注：不常用）
     * @author 217team
     * @data 2022-12-28
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有数据")
    public Map<String, Object> listUser() {
        try {
            return StatusCode.success(userService.listUser());
        } catch (Exception e) {
            e.printStackTrace();
            return StatusCode.error("服务器内部错误：" + e.toString());
        }
    }

    /**
     * @param id 主键id
     * @return Map<String, Object> 自定义响应体
     * @description 根据id删除数据
     * @author 217team
     * @data 2022-12-28
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除数据")
    public Map<String, Object> deleteUserById(@RequestParam("id") Integer id) {
        try {
            if( userService.deleteUserById(id) >= 1 ){
                return StatusCode.success("删除成功");
            }else{
                return StatusCode.success("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return StatusCode.error("服务器内部错误：" + e.toString());
        }
    }

}