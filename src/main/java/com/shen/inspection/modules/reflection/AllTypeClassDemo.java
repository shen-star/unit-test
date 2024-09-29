package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 所有具有Class类对象的类型
 */
@Slf4j
public class AllTypeClassDemo {
    public static void main(String[] args) {
        //外部类
        Class<String> stringClass = String.class;
        //接口
        Class<Serializable> serializableClass = Serializable.class;
        //基本类型
        Class<Integer> integerClass = Integer.class;
        //基本类型封装类
        Class<Double> doubleClass = double.class;
        //注解
        Class<Deprecated> deprecatedClass = Deprecated.class;
        //枚举
        Class<Thread.State> stateClass = Thread.State.class;
        //一维数组
        Class<Integer[]> aClass = Integer[].class;
        //二维数组
        Class<double[][]> aClass1 = double[][].class;
        //void数据类型
        Class<Void> voidClass = void.class;
        //Class类
        Class<Class> classClass = Class.class;

        log.info("外部类: {}", stringClass);
        log.info("接口: {}", serializableClass);
        log.info("基本类型: {}", integerClass);
        log.info("基本类型封装类: {}", doubleClass);
        log.info("注解: {}", deprecatedClass);
        log.info("枚举: {}", stateClass);
        log.info("一维数组: {}", aClass);
        log.info("二维数组: {}", aClass1);
        log.info("void数据类型: {}", voidClass);
        log.info("Class类: {}", classClass);
    }
}
