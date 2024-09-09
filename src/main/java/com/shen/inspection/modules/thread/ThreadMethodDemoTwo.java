package com.shen.inspection.modules.thread;


import lombok.extern.slf4j.Slf4j;

/**
 * 线程方法使用
 */
@Slf4j
public class ThreadMethodDemoTwo {
    public static final int FIVE_SIZE = 5;

    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.start();
        int count = 0;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.warn("sleep interrupt: ", e);
                Thread.currentThread().interrupt();
            }
            log.info("this is main thread {} times test, thread name is {}", ++count, Thread.currentThread().getName());
            if (count == FIVE_SIZE) {
                log.info("main thread reach five times, child thread first");
                thread.join();
            }
        } while (count < 20);
    }

    static class ThreadTest implements Runnable {
        private int num = 0;

        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.warn("sleep interrupt: ", e);
                    Thread.currentThread().interrupt();
                }
                log.info("this is child thread {} times test, thread name is {}", ++num, Thread.currentThread().getName());
            } while (num < 20);
        }
    }
}
