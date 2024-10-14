package com.shen.inspection.modules.reflection;

import java.lang.reflect.Constructor;

/**
 * 通过反射创建对象
 */
public class ReflectionCreateInstance {
    public static void main(String[] args) throws Exception {
        //1.获取User类的Class对象
        Class<?> userClass = Class.forName("com.shen.inspection.modules.reflection.User");
        //2.通过public的无参构造创建实例
        Object user1 = userClass.newInstance();
        System.out.println(user1);
        ///3.通过public的有参构造器创建实例
        //创建对应构造器
        Constructor<?> constructor1 = userClass.getConstructor(String.class);
        //创建实例，传入实参
        Object user2 = constructor1.newInstance("小强");
        System.out.println(user2);
        //4.通过private的有参构造器创建实例
        //创建私有构造器
        Constructor<?> constructor2 = userClass.getDeclaredConstructor(Integer.TYPE, String.class);
        //使用暴破，可以访问私有的构造器
        constructor2.setAccessible(true);
        Object user3 = constructor2.newInstance(20,"小刚");
        System.out.println(user3);

    }
}

class User {
    private int age = 18;
    private String name = "小明";

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    private User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String toString() {
        return "user age:" + age + ", name:" + name + ";";
    }
}
