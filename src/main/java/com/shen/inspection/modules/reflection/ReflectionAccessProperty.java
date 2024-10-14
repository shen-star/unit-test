package com.shen.inspection.modules.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 通过反射访问属性
 */
public class ReflectionAccessProperty {
    public static void main(String[] args) throws Exception {
        //1.获取Student类的Class对象
        Class<?> studentClass = Class.forName("com.shen.inspection.modules.reflection.Student");
        //2.通过创建构造器创建对象
        Constructor<?> constructor = studentClass.getConstructor();
        Object student = constructor.newInstance();
        //3.通过反射获取属性
        Field age = studentClass.getField("age");
        //给student实例的age属性赋值
        age.set(student, 20);
        //获取student实例的age属性的值
        System.out.println(age.get(student));
        Field name = studentClass.getDeclaredField("name");
        //使用暴破，可以操作私有属性
        name.setAccessible(true);
        //name.set(student, "小刚");
        //由于name属性是静态的，赋值时，具体实例也可以为null
        name.set(null, "小明");
        //System.out.println(name.get(student));
        System.out.println(name.get(null));
        System.out.println(student);
    }
}

class Student {
    public int age;
    private static String name;

    public Student() {

    }

    public String toString() {
        return "student age:" + age + ", name:" + name + ";";
    }

}
