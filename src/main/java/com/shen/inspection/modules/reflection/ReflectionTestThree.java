package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 测试反射的效率与优化后效率
 */
@Slf4j
public class ReflectionTestThree {
    public static void main(String[] args) throws Exception {
        normalInvoke();
        reflectionInvoke();
        optimizeReflectionInvoke();
    }

    public static void normalInvoke() {
        DemoEntity demoEntity = new DemoEntity();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            demoEntity.hello();
        }
        long end = System.currentTimeMillis();
        log.warn("normal invoke method use {} ms", end - start);
    }

    public static void reflectionInvoke() throws Exception {
        Class cls = Class.forName("com.shen.inspection.modules.reflection.DemoEntity");
        Constructor noConstructor = cls.getConstructor();
        Object object = noConstructor.newInstance();
        Method method = cls.getMethod("hello");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            method.invoke(object);
        }
        long end = System.currentTimeMillis();
        log.warn("reflection invoke method use {} ms", end - start);
    }

    /**
     * 反射调用调优-关闭访问检查
     *
     * @throws Exception exception
     */
    public static void optimizeReflectionInvoke() throws Exception {
        Class cls = Class.forName("com.shen.inspection.modules.reflection.DemoEntity");
        Constructor noConstructor = cls.getConstructor();
        Object object = noConstructor.newInstance();
        Method method = cls.getMethod("hello");
        //在反射调用方式时，取消访问检查
        method.setAccessible(true);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            method.invoke(object);
        }
        long end = System.currentTimeMillis();
        log.warn("optimize reflection invoke method use {} ms", end - start);
    }
}
