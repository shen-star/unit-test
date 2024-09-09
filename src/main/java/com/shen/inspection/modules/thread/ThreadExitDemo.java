package com.shen.inspection.modules.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程终止
 */
@Slf4j
public class ThreadExitDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.start();
        log.info("主线程:[{}]睡眠10s后,停止子线程", Thread.currentThread().getName());
        Thread.sleep(10000);
        threadTest.setThreadSwitch(false);
    }

    static class ThreadTest implements Runnable {
        private boolean threadSwitch = true;

        @Override
        public void run() {
            while (threadSwitch) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.warn("sleep exception : ", e);
                    Thread.currentThread().interrupt();
                }
                log.info("this is thread  test, thread name is {}", Thread.currentThread().getName());
            }
        }

        public void setThreadSwitch(boolean threadSwitch) {
            this.threadSwitch = threadSwitch;
        }
    }
}
