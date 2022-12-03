package com.code.member.entity;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class User implements Serializable {

  private long id;

  @NotBlank(message = "密码不能为空")
  private String password;

  @NotBlank(message = "邮箱不能为空")
  private String email;

  @NotBlank(message = "电话不能为空")
  private String phone;

  void createUser(String password,String email,String phone){
    this.password=password;
    this.email=email;
    this.phone=phone;
  }

}
