package com.code.generator.model;


import lombok.Data;

@Data
public class Column {
    private String columnName;//列名称
    private String columnName2;//处理后的列名称
    private String columnType;//列类型
    private String columnDbType;//列在数据库中的类型
    //本工程暂不处理备注和主键
    private String columnComment;//列备注id
    private String columnKey;//是否是主键

}
