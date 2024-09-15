package com.shen.inspection.modules.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 查看线程状态
 */
@Slf4j
public class ThreadStateDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        log.info("{} thread state is {}", threadTest.getName(), threadTest.getState());
        threadTest.start();
        while (Thread.State.TERMINATED != threadTest.getState()) {
            log.info("{} thread state is {}", threadTest.getName(), threadTest.getState());
            Thread.sleep(500);
        }
        log.info("{} thread state is {}", threadTest.getName(), threadTest.getState());
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
            } while (num < 5);
        }
    }
}
