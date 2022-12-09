package com.code.generator.core;


import com.code.generator.utils.FileUtils;
import freemarker.template.Configuration;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator {
    private String templatePath;//模板路径
    private String outPath;//代码生成路径
    private Configuration cfg;
    public Generator(String templatePath,String outPath)throws Exception{
        this.outPath=outPath;
        //实例化Configuration对象
        cfg  =new Configuration();
        //指定模板加速器
        //在模板中动态加载jar、资源文件的时候，首先应该是使用Thread.currentThread().getContextClassLoader()
        //ClassLoader类的getResource(String name),getResourceAsStream(String name)等方法，使用相对于当前项目的classpath的相对路径来查找资源。
        String templates = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"\\springboot程序的模板\\";
        this.templatePath = templates;
        FileTemplateLoader fileTemplateLoader=new FileTemplateLoader(new File(templates));
        cfg.setTemplateLoader(fileTemplateLoader);
    }
    /**
     * 代码生成
     *      1，扫描模板路径下的所有模板
     *      2，对每个模板进行文件生成（数据模板）
     *      3，参数：数据模板
     */
    public  void scanAndGenerator(Map<String,Object> dataModel)throws Exception{
        //根据模板路径找到此路径下的所有模板文件
        List<File> fileList = FileUtils.searchAllFile(new File(templatePath));
        //对每个模板进行文件生成
        for(File file:fileList){
            //参数1：数据模型  参数2：文件模板
            excuteGenerator(dataModel,file);
        }
    }
    //对模板进行文件生成
    //参数1：数据模型  参数2：文件模板
    private void excuteGenerator(Map<String, Object> dataModel, File file) throws Exception {
        //1，文件路径处理
        /**
         * file:D:/模板存在的文件夹/${path1}/${path2}/${path3}/${classname}.java  绝对路径
         * replace的目的：得到文件名，文件名之前的路径都不要了，只留包名之后的内容
         */
        //  得到模板文件的这样的路径 ${path1}/${path2}/${path3}/${className}.java
        String one=file.getAbsolutePath();
        int i = one.indexOf("$");
        String templateFileName = one.substring(i);
        //把${path1}/${path2}/${path3}/${className}.java 替换成 com/ftx/demoUser.java (数据模型中的内容)
        String outFileName = processString(templateFileName, dataModel);
        //2，读取文件模板
        //上面把模板整个文件夹都加载到了模板加载器，所以这里拿模板只需要传入该文件夹下的文件名即可
        Template template = cfg.getTemplate(templateFileName);//相对路径 ${path1}/${path2}/${path3}/${classname}.java
        template.setOutputEncoding("utf-8");//指定生成文件的字符集编码
        //3，创建文件
        File file1 = FileUtils.mkdir(this.outPath, outFileName);
        //4，模板处理（文件生成）
        FileWriter fileWriter=new FileWriter(file1);
        System.err.println(dataModel);
        template.process(dataModel,fileWriter);
        fileWriter.close();
    }
    /**
     * 把${path1}/${path2}/${path3}/${className}.java 替换成 com/ftx/demoUser.java (数据模型中的内容)
     * FreeMarker的字符串模板 替换
     */
    public String processString(String templateString,Map dataModel) throws Exception{
        StringWriter stringWriter=new StringWriter();
        Template template=new Template("ts",new StringReader(templateString),cfg);
        template.process(dataModel,stringWriter);
        return stringWriter.toString();
    }
    /**
     * 测试代码生成主类scanAndGenerator是否管用
     */
    public static void main(String[] args) throws Exception {
        String templatePath="H:";
        String outPath="H:";
        Generator generator=new Generator(templatePath,outPath);
        Map<String,Object> dataModel=new HashMap<>();
        dataModel.put("username","张三");
        generator.scanAndGenerator(dataModel);
    }
}
