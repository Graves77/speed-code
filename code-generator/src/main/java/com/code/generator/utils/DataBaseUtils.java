package com.code.generator.utils;



import com.code.generator.model.Column;
import com.code.generator.model.DataBase;
import com.code.generator.model.Table;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法介绍：
 * 1，获取数据库连接
 * 2，获取数据库列表
 * 3，获取数据库中的所有表和字段并构造实体类
 * 4，根据表名的截取操作生成类名
 **/
public class DataBaseUtils {
    //获取数据库连接
    public static Connection getConnection(DataBase db)throws Exception{
        //获取连接
        Class.forName(db.getDriver());//Class.forName传入com.mysql.jdbc.Driver 之后,就知道我连接的数据库是 mysql
        Connection connection = DriverManager.getConnection(db.getUrl(),db.getUserName(),db.getPassWord());
        return connection;
    }
    //获取数据库列表
    public static List<String> getShemas(DataBase db)throws Exception{
        Connection connection = getConnection(db);
        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获取所有数据库列表
        ResultSet resultSet = metaData.getCatalogs();
        List<String>list = new ArrayList<>();
        while (resultSet.next()){
            list.add(resultSet.getString(1));//rs.getString(n) ：为获取结果集当前行的第n列数据
        }
        resultSet.close();
        connection.close();
        return list;
    }
    //获取数据库中所有的表和字段并构造实体类（相当于一键生成数据库中所有的增删改查代码）
    public static List<Table> getDbInfo(DataBase db,HttpSession session)throws Exception{
        //获取连接
        Connection connection = getConnection(db);
        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        List<Table>list = new ArrayList<>();
        //获取当前数据库的所有表
        String dbss =(String) session.getAttribute("db");
        ResultSet tables = metaData.getTables(dbss,null,null,new String[]{"TABLE"});
        while(tables.next()){
            //表名
            String table_name=tables.getString("TABLE_NAME");
            //构造生成对应实体类的类名
            String className = removePrefix(table_name);
            //主键
            ResultSet primaryKeys = metaData.getPrimaryKeys(null,null,table_name);
            //对主键遍历的原因（或许一张表有多个主键）
            String keys = "";
            while(primaryKeys.next()){
                String keyName = primaryKeys.getString("COLUMN_NAME");
                keys +=keyName+",";
            }
            Table table = new Table();
            table.setName(table_name);
            table.setName2(className);
            table.setKey(keys);
            //处理表中的所有字段
            ResultSet columns = metaData.getColumns(dbss,null,table_name,null);
            List<Column> cols=new ArrayList<>();
            while(columns.next()){
                Column column = new Column();
                //列名称
                String column_name = columns.getString("COLUMN_NAME");
                //java实体的属性名
                String attName = column_name;
                //java类型和数据库类型
                String type_name = columns.getString("TYPE_NAME");
                String javaType = PropertiesUtils.customMap.get(type_name);
                column.setColumnName(column_name);
                column.setColumnName2(attName);
                column.setColumnDbType(type_name);
                column.setColumnKey(javaType);
                cols.add(column);
            }
            table.setColumnList(cols);
            list.add(table);
            //关闭连接 释放资源
            columns.close();
            primaryKeys.close();
        }
        tables.close();
        connection.close();
        return list;
    }

    //根据表名的截止操作生成类名
    public static String removePrefix(String tableName){
        //从自定义的配置文件中拿到前缀的配置
        String prefixes = PropertiesUtils.customMap.get("tableRemovePrefixes");
        //这里就不字符串处理了，直接把表名当类名用了
        String replace = tableName;
        return replace;
    }
    // 测试 获取数据库中的所有表和字段并构造实体类 的方法是否可用
//    public static void main(String[] args) throws Exception {
//        DataBase db=new DataBase("MYSQL","laji");
//        db.setUserName("root");
//        db.setPassWord("1234");
//        List<Table> dbInfo = DataBaseUtils.getDbInfo(db);
//        for(Table table:dbInfo){
//            System.out.println(dbInfo);
//        }
//    }
}
