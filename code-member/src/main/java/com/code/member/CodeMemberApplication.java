package com.code.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.code.member.dao")
@SpringBootApplication
public class CodeMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeMemberApplication.class, args);
    }

}
