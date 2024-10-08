package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * 类加载：静态加载和动态加载
 */
@Slf4j
public class ClassLoadDemo {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        log.info("请输入字符串:");
        String key = scanner.nextLine();
        switch (key) {
            case "1":
                //静态加载-依赖性很强，编译无法通过
                Demo demo = new Demo();
                demo.test();
                break;
            case "2":
                //反射-动态加载，能够编译，只有运行后动态加载类找不到时会报错
                Class<?> cls = Class.forName("com.shen.inspection.modules.reflection.Hello");
                Constructor<?> constructor = cls.getConstructor();
                Object object = constructor.newInstance();
                Method method = cls.getMethod("test");
                method.invoke(object);
                break;
            default:
                log.info("非法字符");
                break;
        }
    }
}

class Demo {
    public void test() {
        System.out.println("this is a test");
    }
}

class Hello {
    public Hello() {
    }

    public void test() {
        System.out.println("hello");
    }
}
