package com.shen.inspection.modules.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 通过反射访问方法
 */
public class ReflectionAccessMethod {
    public static void main(String[] args) throws Exception {
        //1.获取Student类的Class对象
        Class<?> studentClass = Class.forName("com.shen.inspection.modules.reflection.Boss");
        //2.通过创建构造器创建对象
        Constructor<?> constructor = studentClass.getConstructor();
        Object boss = constructor.newInstance();
        //3.获取public方法
        Method hello = studentClass.getMethod("hello", String.class);
        //4.调用hello方法
        hello.invoke(boss, "小刚");
        //5.获取private方法
        Method saySomething = studentClass.getDeclaredMethod("saySomething", int.class, boolean.class, String.class);
        //6.暴破，可以使用私有方法
        saySomething.setAccessible(true);
        //System.out.println(saySomething.invoke(boss, 1, true, "boss"));
        //由于saySomething是静态方法，具体实例也可以写null
        System.out.println(saySomething.invoke(null, 1, true, "boss"));

        //在反射中，如果方法有返回值，统一返回Object，但运行类型和方法定义的返回类型相同
        Object realValue = saySomething.invoke(boss, 1, false, "bot");
        System.out.println(realValue.getClass());
    }
}

class Boss {
    public int age;
    private static String name;

    public Boss() {

    }

    private static String saySomething(int a, boolean b, String c) {
        return a + "" + b + c;
    }

    public void hello(String name) {
        System.out.println("hello my name is : " + name);
    }
}
