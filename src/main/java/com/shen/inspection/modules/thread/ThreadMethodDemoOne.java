package com.shen.inspection.modules.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程方法使用
 */
@Slf4j
public class ThreadMethodDemoOne {
    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.setName("test");
        thread.setPriority(1);
        thread.start();
        log.info("默认的优先级为: {}",Thread.currentThread().getPriority());
        Thread.sleep(1000);
        thread.interrupt();
    }

    static class ThreadTest implements Runnable {
        private int num = 0;

        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    log.warn("sleep interrupt: ", e);
                }
                log.info("this is thread {} times test, thread name is {}, priority is {}", ++num, Thread.currentThread().getName(),Thread.currentThread().getPriority());
            } while (num < 30);
        }
    }
}
