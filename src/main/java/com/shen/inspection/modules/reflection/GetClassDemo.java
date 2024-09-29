package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

/**
 * 获取Class类对象的不同方式(6种)
 */
@Slf4j
public class GetClassDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        //1.Class.forName
        String classPath = "com.shen.inspection.modules.reflection.DemoEntity";
        Class<?> cls1 = Class.forName(classPath);

        //2.类名.class
        Class<?> cls2 = DemoEntity.class;

        //3.对象.getClass
        DemoEntity demoEntity = new DemoEntity();
        Class<?> cls3 = demoEntity.getClass();

        //4.通过类加载器获取类对象
        ClassLoader classLoader = DemoEntity.class.getClassLoader();
        Class<?> cls4 = classLoader.loadClass(classPath);

        //四种方式创建的类对象是同一个
        log.info("cls1: {}, hashcode is {}", cls1, cls1.hashCode());
        log.info("cls2: {}, hashcode is {}", cls1, cls2.hashCode());
        log.info("cls3: {}, hashcode is {}", cls1, cls3.hashCode());
        log.info("cls4: {}, hashcode is {}", cls1, cls4.hashCode());

        //5.基本数据类型可以通过类型.class的方式获取类对象
        Class<Character> characterClass = char.class;
        Class<Short> shortClass = short.class;
        Class<Integer> integerClass = int.class;
        Class<Long> longClass = long.class;
        Class<Byte> byteClass = byte.class;
        Class<Boolean> booleanClass = boolean.class;
        Class<Float> floatClass = float.class;
        Class<Double> doubleClass = double.class;
        log.info("int Class is : {}, hascode is {}", integerClass, integerClass.hashCode());

        //6.基本数据类型的封装类可以通过.TYPE的方式获取类对象
        Class<Integer> type = Integer.TYPE;
        log.info("Integer Class is : {}, hascode is {}", type, type.hashCode());


    }
}
