package com.code.generator.test;

import com.code.generator.utils.GeneratorFacade;
import com.code.generator.model.DataBase;
import com.code.generator.model.Settings;

import org.junit.Test;



public class testdemo {
    /**
     * @description 自动生成Spring增删查改代码
     * @throws Exception
     * @date 2022.9.15更新
     */
    @Test
    public void autoNewCode() throws Exception {
        //配置项目（项目包名，项目名）
        String packagename = "com.code.generator";
        String projectEngName = "qaq";
        //配置数据库（数据库IP,数据库端口，数据库名，数据库用户名，数据库密码，数据库类型）
        String ip = "localhost";
        String port = "3306";
        String db = "example";
        String username = "root";
        String password = "zhongge666";
        String dbKind = "MYSQL";
        //生成代码的位置
        String fileUrl = "D:\\generator";
        Settings settings = new Settings();
        //包名(com.ftx.demo)
        settings.setPPackage(packagename);
        //split(".")无法分割字符串，必须加上\\
        String[] split = packagename.split("\\.");
        //com
        settings.setPath1(split[0]);
        //ftx
        settings.setPath2(split[1]);
        //demo
        settings.setPath3(split[2]);
        //项目名（没啥用）
        settings.setProject(projectEngName);
        //默认只支持mysql数据库吧，oracle暂时先不处理，先写死为mysql
        DataBase dbs = new DataBase("MYSQL", ip, port,db);
        dbs.setUserName(username);
        dbs.setPassWord(password);
        GeneratorFacade generatorFacade = new GeneratorFacade(dbKind, fileUrl, settings, dbs);
        System.out.println(generatorFacade.toString());
        boolean b = generatorFacade.generatorByDataBase(db);
        if( b ){
            System.out.println("代码已生成，请在 " + fileUrl + " 中查看");
        }else{
            System.out.println("代码生成失败");
        }
    }
}
