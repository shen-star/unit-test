package com.shen.inspection.modules.reflection;

import lombok.extern.slf4j.Slf4j;

/**
 * 类加载的五个阶段
 */

@Slf4j
public class ClassLoadDemoTwo {
    public static void main(String[] args) {
        /*
        1.加载DemoOne类生成DemoOne的class对象
        2.链接 num = 0
        3.初始化阶段:依次自动收集类中所有静态变量的赋值动作和静态代码块中的语句并合并

        clinit(){
            num = 200;
            System.out.println("执行Demo类的静态代码块");
            num = 100;
        }
        合并: num = 100;
         */
        System.out.println(DemoOne.num);
    }
}

class DemoOne{
    static int num = 200;

    static {
        System.out.println("执行Demo类的静态代码块");
        num = 100;
    }
}
