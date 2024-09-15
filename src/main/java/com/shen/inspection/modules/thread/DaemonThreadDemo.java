package com.shen.inspection.modules.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaemonThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        DaemonThreadTest threadTest = new DaemonThreadTest();
        //将threadTest设置为守护线程
        threadTest.setDaemon(true);
        threadTest.start();
        for (int i = 0; i <= 5; i++) {
            Thread.sleep(1000);
            log.info("main thread {} times thread test:{}", i, Thread.currentThread().getName());
        }
    }


    static class DaemonThreadTest extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.warn("sleep exception: ", e);
                    Thread.currentThread().interrupt();
                }
                log.info("this is a test by {}", Thread.currentThread().getName());
            }
        }
    }
}
