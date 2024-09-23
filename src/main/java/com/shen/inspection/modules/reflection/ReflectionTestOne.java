package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 反射练习1
 */
@Slf4j
public class ReflectionTestOne {
    public static void main(String[] args) throws Exception {
        //正常创建对象调用方法
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.hello();
        log.info("========================================================");
        //使用反射创建对象调用方法
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+ File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "demo.properties");
        Properties properties = new Properties();
        //从配置文件中读取配置信息
        properties.load(fileInputStream);
        String classPath = properties.getProperty("class.path");
        String methodName = properties.getProperty("method.name");
        //加载类，返回Class类型对象cls
        Class cls = Class.forName(classPath);
        //通过cls得到加载的类com.shen.inspection.modules.reflection.DemoEntity的对象实例
        Object object = cls.newInstance();
        //通过cls得到加载的类com.shen.inspection.modules.reflection.DemoEntity的方法对象
        //在反射中，可以把方法看作对象
        Method method = cls.getMethod(methodName);
        //反射中是 方法.invoke(对象)来调用方法
        method.invoke(object);
    }
}
