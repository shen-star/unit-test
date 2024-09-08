package com.shen.inspection.modules.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程方法一：继承Thread类
 * 通过命令行使用jconsole监控可以发现，进程中主线程结束后不一定就结束，取决于所有线程是否都已结束
 */
@Slf4j
public class ThreadDemoOne {
    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();
        int num = 0;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.warn("sleep exception : ", e);
                Thread.currentThread().interrupt();
            }
            log.info("this is main {} times test, thread name is {}", ++num, Thread.currentThread().getName());
        } while (num < 20);
    }

    static class ThreadTest extends Thread {
        private int num = 0;

        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.warn("sleep exception : ", e);
                    Thread.currentThread().interrupt();
                }
                log.info("this is thread {} times test, thread name is {}", ++num, Thread.currentThread().getName());
            } while (num < 30);
        }
    }
}
