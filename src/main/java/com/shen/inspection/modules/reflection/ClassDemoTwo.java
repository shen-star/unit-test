package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class类的常用方法
 */
@Slf4j
public class ClassDemoTwo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        String classPath = "com.shen.inspection.modules.reflection.DemoEntity";
        // 获取DemoEntity类对应的Class对象
        //<?> 表示不确定的java类型
        Class<?> cls = Class.forName(classPath);
        log.info("显示cls的对象(具体为哪个类的Class对象):{}", cls);
        log.info("显示cls类的类型:{}", cls.getClass());
        log.info("显示cls对象的包名:{}", cls.getPackage());
        log.info("显示cls对象的全类名:{}", cls.getName());
        //通过构造器，创建对象实例
        Constructor<?> constructor = cls.getConstructor();
        DemoEntity demoEntity = (DemoEntity) constructor.newInstance();
        log.info("打印demoEntity对象:{}", demoEntity.toString());
        //通过反射获取属性 date
        Field field = cls.getField("date");
        log.info("打印date属性值:{}", field.get(demoEntity));
        //通过反射给属性赋值
        field.set(demoEntity, "2020");
        log.info("打印date属性值:{}", field.get(demoEntity));
        //通过反射获取所有属性
        Field[] fields = cls.getFields();
        for (Field f : fields) {
            log.info("打印属性名称:{}", f.getName());
        }
    }
}
