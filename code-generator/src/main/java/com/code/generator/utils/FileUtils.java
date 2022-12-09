package com.code.generator.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//文件处理工具类
public class FileUtils {
    //得到相对路径
    public static String getRelativePath(File baseDir,File file){
        if(baseDir.equals(file)){
            return "";
        }
        if(baseDir.getParentFile()==null){
            //获取绝对路径
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length());
        }else {
            return file.getAbsolutePath().substring(baseDir.getAbsolutePath().length() + 1);
        }
    }
    //查询整个目录下的所有文件
    public static List<File> searchAllFile(File dir) throws IOException {
        ArrayList arrayList=new ArrayList();
        searchFiles(dir,arrayList);
        return arrayList;
    }
    //递归获取某个目录下面的所有文件
    public static void searchFiles(File dir,List<File> collect){
        //isDirectory()是检查一个对象是否是文件夹。返回值是boolean类型的。如果是则返回true，否则返回false。
        if(dir.isDirectory()){
            File[] files = dir.listFiles();
            for(int i=0;i<files.length;i++){
                searchFiles(files[i],collect);
            }
        }
        else {
            collect.add(dir);
        }
    }
    //创建文件
    public static File mkdir(String dir,String file){
        if(dir==null){
            throw new IllegalArgumentException("文件夹不许为空");
        }
        File result = new File(dir,file);
        if(result.getParentFile()!=null){
            //mkdirs()可以建立多级文件夹， mkdir()只会建立一级的文件夹
            result.getParentFile().mkdir();
        }
        return result;
    }
}
