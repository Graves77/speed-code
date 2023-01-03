package com.code.generator.controller;

import com.code.generator.utils.R;
import com.code.generator.utils.GeneratorFacade;
import com.code.generator.model.DataBase;
import com.code.generator.model.Settings;

import com.code.generator.utils.ZipUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;


@RestController
@ResponseBody
@RequestMapping("/generator")
public class GeneratorController {

    //生成代码
    /**
     * 确认生成按钮传来的参数
     *
     * @param dbKind         选择模板，这里没啥用，因为已经写死模板位置了  resources/springboot程序的模板  文件夹
     * @param projectEngName 项目英文名称 没啥用
     * @param packagename    包名 com.ftx.demo
     * @param db             选择的数据库名
     * @throws Exception 下面用sesson的目的很简单，不要想多了，是存了数据库名，到后来的方法要取出来，我这是把它当成全局变量使用了
     */
    @RequestMapping("/create")
    public R autoNewCode(String dbKind, String projectEngName, String packagename,
                         String db, String username, String password,
                         String ip, String port, HttpServletRequest request) throws Exception {

        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        //String realpath = request.getServletContext().getRealPath("/WEB-INF/files");
        //暂时就不保存在WEB-INF那里，先放在files
        String realpath = request.getServletContext().getRealPath("/");
        String fileUrl = realpath+projectEngName;
        //String fileUrl = "D:\\generator\\模板\\resources";
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
        DataBase dbs = new DataBase("MYSQL", ip, port, db);
        dbs.setUserName(username);
        dbs.setPassWord(password);
        GeneratorFacade generatorFacade = new GeneratorFacade(dbKind, fileUrl, settings, dbs);
        //System.out.println(generatorFacade.toString());
        boolean b = generatorFacade.generatorByDataBase(db);
        if (b) {
            ZipUtils zipUtils = new ZipUtils();
            FileOutputStream fos1 = new FileOutputStream(new File( fileUrl+".zip"));
            zipUtils.toZip(fileUrl, fos1, true);
            return R.ok("代码已生成，回文件看看").put("ZipName",projectEngName+".zip");
        } else {
            return R.error("代码生成失败！");
        }
    }


}
