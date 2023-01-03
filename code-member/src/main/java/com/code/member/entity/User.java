package com.code.member.entity;


import com.code.member.valid.loginGroup;
import com.code.member.valid.registerGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class User implements Serializable {

  private long id;

  @NotBlank(message = "密码不能为空",groups = {loginGroup.class, registerGroup.class})
  private String password;

  @NotBlank(message = "邮箱不能为空",groups = {registerGroup.class})
  private String email;

  @NotBlank(message = "电话不能为空",groups = {loginGroup.class, registerGroup.class})
  private String phone;

  void createUser(String password,String email,String phone){
    this.password=password;
    this.email=email;
    this.phone=phone;
  }

}
