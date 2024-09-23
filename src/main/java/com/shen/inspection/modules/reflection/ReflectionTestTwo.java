package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射练习-常用类使用
 */
@Slf4j
public class ReflectionTestTwo {
    public static void main(String[] args) throws Exception{
        Class cls = Class.forName("com.shen.inspection.modules.reflection.DemoEntity");
        // 可以指定构造器的参数类型，如果没有则为无参构造器
        Constructor noConstructor  = cls.getConstructor();
        log.info("无参构造器: {}",noConstructor);
        // String.class就是String类的Class对象
        Constructor allConstructor  = cls.getConstructor(String.class);
        log.info("有参构造器: {}",allConstructor);
        Object object = noConstructor.newInstance();
        Method method = cls.getMethod("hello");
        method.invoke(object);
        log.info("=================================================");
        // 获取所有公有属性，无法获取私有属性
        Field[] field = cls.getFields();
        Field oneField = cls.getField("NAME");
        log.info("NAME value is {}",oneField.get(object));

    }

}
