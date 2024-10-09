package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过反射类获取类结构信息
 */
@Slf4j
public class ReflectionUtils {
    public static void main(String[] args) throws Exception {
        //得到Class对象
        Class<?> cls = Class.forName("com.shen.inspection.modules.reflection.Dog");
        //获取全类名
        log.info("全类名:{}", cls.getName());
        //获取简单类名
        log.info("简单类名:{}", cls.getSimpleName());
        //获取所有public修饰的属性，包含本类以及父类
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            log.info("获取本类以及父类的公有属性:{}", field.getName());
        }
        //获取本类的所有属性
        Field[] declareFields = cls.getDeclaredFields();
        for (Field declareField : declareFields) {
            log.info("获取本类的所有属性:{}", declareField.getName());
        }
        //获取所有public修饰的方法，包含本类以及父类
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            log.info("获取本类以及父类的公有方法:{}", method.getName());
        }
        //获取本类的所有方法
        Method[] declareMethods = cls.getDeclaredMethods();
        for (Method declareMethod : declareMethods) {
            log.info("获取本类的所有属性:{}", declareMethod.getName());
        }
        //获取所有public修饰的构造器，包含本类
        Constructor[] constructors = cls.getConstructors();
        for (Constructor constructor : constructors) {
            log.info("获取本类以及父类的公有构造器:{}", constructor.getName());
        }
        //获取本类的所有构造器
        Constructor[] declareConstructors = cls.getDeclaredConstructors();
        for (Constructor declareConstructor : declareConstructors) {
            log.info("获取本类的所有构造器:{}", declareConstructor.getName());
        }
        //返回包信息
        log.info("包信息:{}", cls.getPackage());
        //返回父类信息
        log.info("父类信息:{}", cls.getSuperclass());
        //返回接口信息
        Class<?>[] interfaces = cls.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            log.info("接口信息:{}", anInterface.getName());
        }
        //返回注解信息
        Annotation[] annotations = cls.getAnnotations();
        for (Annotation annotation : annotations) {
            log.info("注解信息:{}", annotation);
        }
    }
}

class Animal {
    public String id;

    public Animal() {

    }

    public void animalTestOne() {

    }

    private void animalHello() {

    }

}

interface Train {

}

interface Feed {

}

@Deprecated
class Dog extends Animal implements Train, Feed {
    public String name;
    protected String age;
    private String gender;
    String birthday;

    public Dog() {

    }

    private Dog(String name) {
        this.name = name;
    }

    public void dogTestOne() {

    }

    private void dogHello() {

    }
}
