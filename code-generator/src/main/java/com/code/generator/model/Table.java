package com.code.generator.model;



import lombok.Data;

import java.util.List;

@Data
public class Table {
    private String name;//表名称
    private String name2;//处理后的表名称
    private String comment;//介绍
    private String key;//主键列
    private List<Column> columnList;
}
