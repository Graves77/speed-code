package com.code.generator.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

//需要将自定义的配置信息写入到properties文件中，配置到相对于工程的properties文件下
public class PropertiesUtils {
    public static Map<String,String> customMap = new HashMap<>();
    //静态块，预加载，将自定义的配置文件properties的内容全部加载到customMap里面
    static {
        File dir = new File("properties");
        try {
            List<File> files = FileUtils.searchAllFile(new File(dir.getAbsolutePath()));
            for(File file:files){
                if(file.getName().endsWith("properties")){
                    Properties prop=new Properties();
                    prop.load(new FileInputStream(file));
                    customMap.putAll((Map)prop);
            }
        }
    }catch (IOException e){
            e.printStackTrace();
        }
    }
    //测试预加载是否成功（看是否打印出来properties配置文件的key和value）
    public  static void main(String []args){
        for(String key:customMap.keySet()){
            System.out.println(key+"---"+customMap.get(key));
        }
    }
}