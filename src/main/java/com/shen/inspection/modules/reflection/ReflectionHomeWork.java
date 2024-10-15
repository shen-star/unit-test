package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 练习：
 * 1.利用Class类的forName方法获取File类的Class对象
 * 2.打印其所有的构造器
 * 3.通过构造器创建File对象，并创建F:\\test.txt文件
 */
@Slf4j
public class ReflectionHomeWork {
    public static void main(String[] args) throws Exception {
        //1.创建File类的Class类对象
        Class<?> fileClass = Class.forName("java.io.File");
        //2.获取所有构造器并打印
        Constructor[] constructors = fileClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            log.info(constructor.toString());
        }
        //3.获取公有的构造器创建一个File实例
        Constructor<?> createFileConstructor = fileClass.getConstructor(String.class);
        Object file = createFileConstructor.newInstance("F:\\test.txt");
        log.info(file.toString());
        //4.获取File的创建文件方法，并调用
        Method createNawFile = fileClass.getMethod("createNewFile");
        createNawFile.invoke(file);
    }
}
