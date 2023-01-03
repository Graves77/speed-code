package com.code.generator.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * @author 217team
 * @version 1.0
 * @description User的实体类
 * @date 2022-12-28
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {  //?cap_first freemarker内置函数 首字母大写
private String name;
private Integer id;
public String getName() {
        return this.name;
        }
public void setName(String name) {
        this.name = name;
        }
public Integer getId() {
        return this.id;
        }
public void setId(String id) {
        this.id = id;
        }
        }
