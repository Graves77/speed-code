package com.code.generator.controller;


import com.code.generator.core.GeneratorFacade;
import com.code.generator.model.DataBase;
import com.code.generator.model.Settings;
import com.code.generator.utils.DataBaseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GeneratorController {

    @RequestMapping("/enter")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/enter1")
    public String index1(String databases, String username, String password, Model model){
        model.addAttribute("databases",databases);
        model.addAttribute("username",username);
        model.addAttribute("password",password);
        return "index1.html";
    }
    //点击测试连接按钮，填充选择数据库下拉框
    @RequestMapping("/testConnection")
    @ResponseBody
    public List<String> testConnection(String dbKind, String ip, String port,
                                       String username, String password) throws Exception {
        DataBase dataBase = new DataBase(dbKind,ip,port,"");
        dataBase.setUserName(username);
        dataBase.setPassWord(password);
        List<String> dbs = DataBaseUtils.getShemas(dataBase);
        if(dbs.size()>0){
            return dbs;
        }
        else {
            return null;
        }

    }
    //生成代码
    /**
     * 确认生成按钮传来的参数
     * @param dbKind   选择模板，这里没啥用，因为已经写死模板位置了  resources/springboot程序的模板  文件夹
     * @param fileUrl   代码生成路径
     * @param projectEngName    项目英文名称 没啥用
     * @param packagename      包名 com.ftx.demo
     * @param projectChinaName  项目中文名称，也没啥用
     * @param db    选择的数据库名
     * @param root  数据库的用户名root
     * @param psd   数据库的密码
     * @throws Exception
     *
     * 下面用sesson的目的很简单，不要想多了，是存了数据库名，到后来的方法要取出来，我这是把它当成全局变量使用了
     */
    @RequestMapping("/create")
    public String create(String dbKind, String fileUrl, String projectEngName, String packagename, String projectChinaName,
                         String db, String root, String psd, HttpSession session, Model model) throws Exception{
        Settings settings = new Settings();
        //包名
        settings.setPPackage(packagename);//com.code.demo
        String[] split = packagename.split("\\.");//split(".")无法分割字符串，必须加上\\
        settings.setPath1(split[0]);//com
        settings.setPath2(split[1]);//code
        settings.setPath3(split[2]);//demo
        //项目名
        settings.setProject(projectEngName);
        //默认只支持mysql数据库
        session.setAttribute("db",db);
        DataBase dbs = new DataBase("MYSQL",db);
        dbs.setUserName(root);
        dbs.setPassWord(psd);
        GeneratorFacade generatorFacade=new GeneratorFacade(dbKind,fileUrl,settings,dbs);
        boolean b = generatorFacade.generatorByDataBase(session);
        if(b){
            model.addAttribute("success","代码已生成，回文件查看！");
            return "result";
        }else{
            model.addAttribute("success","代码生成失败！");
            return "result";
        }
    }

    }
