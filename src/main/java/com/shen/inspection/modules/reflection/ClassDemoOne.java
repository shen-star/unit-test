package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

/**
 * class类对象的创建
 */
@Slf4j
public class ClassDemoOne {
    public static void main(String[] args) throws ClassNotFoundException {
        //通过类图发现Class也是类，也继承Object类

        //Class类对象不是new出来的，而是系统创建的
        //常规方式新建一个类对象
        /**     ClassLoader类构建
         *     public Class<?> loadClass(String name) throws ClassNotFoundException {
         *         return loadClass(name, false);
         *     }
         */
//        DemoEntity demoEntityOne = new DemoEntity();


        //反射方式创建类对象，同样是通过ClassLoader类创建
        Class<?> cls1 = Class.forName("com.shen.inspection.modules.reflection.DemoEntity");
        //一个类的对象在内存中只有一份，因为类加载只有一次
        Class<?> cls2 = Class.forName("com.shen.inspection.modules.reflection.DemoEntity");
        log.info("demoOne hascode is : {}, demoTwo hascode is : {}", cls1.hashCode(), cls2.hashCode());
    }
}
