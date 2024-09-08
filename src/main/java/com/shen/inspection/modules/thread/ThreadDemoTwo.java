package com.shen.inspection.modules.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程方法二：实现Runnable接口
 * 底层使用了设计模式中的代理模式-静态代理
 */
@Slf4j
public class ThreadDemoTwo {
    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = new Thread(threadTest);
        thread.start();
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

        //代理模式示例
        ProxyThread proxyThread = new ProxyThread(threadTest);
        proxyThread.start();

    }

    static class ThreadTest implements Runnable {
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

    /**
     * 代理模式-静态代理
     */
    static class ProxyThread implements Runnable {
        private final Runnable target;

        @Override
        public void run() {
            if (target != null) {
                target.run();
            }
        }

        public ProxyThread() {
            this.target = null;
        }

        public ProxyThread(Runnable runnable) {
            this.target = runnable;
        }

        public void start() {
            this.start0();
        }

        public void start0() {
            this.run();
        }
    }
}
