package com.shen.inspection.modules.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 方法同步解决同时售票问题(对象互斥锁)
 */
@Slf4j
public class SynchronizedThreadDemo {
    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        Thread threadOne = new Thread(threadTest);
        Thread threadTwo = new Thread(threadTest);
        Thread threadThree = new Thread(threadTest);
        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }

    public static class ThreadTest implements Runnable {

        private int ticketNum = 100;
        private boolean runSwitch = true;
        //一个对象
        private final Object object = new Object();

        //如果是静态方法的锁对象是当前类本身
        public static synchronized  void staticSellTicket() {
            log.info("test");
        }
        public static void staticSellTicket2() {
            synchronized (ThreadTest.class){
                log.info("test");
            }
        }

        //如果是非静态方法的锁对象可以是this或其他对象(对象要求是同一个)
        //同步方法
        public synchronized void sellTicket() {
            if (ticketNum <= 0) {
                log.info("tickets sell out");
                runSwitch = false;
                return;
            }
            log.info("{} 售票口 卖出一张车票，还剩{}张", Thread.currentThread().getName(), --ticketNum);
        }
        //同步代码块
        public void sellTicket2() {
            //this锁
//            synchronized (this) {
//                if (ticketNum <= 0) {
//                    log.info("tickets sell out");
//                    runSwitch = false;
//                    return;
//                }
//                log.info("{} 售票口 卖出一张车票，还剩{}张", Thread.currentThread().getName(), --ticketNum);
//            }
            //同一个对象锁
            synchronized (object) {
                if (ticketNum <= 0) {
                    log.info("tickets sell out");
                    runSwitch = false;
                    return;
                }
                log.info("{} 售票口 卖出一张车票，还剩{}张", Thread.currentThread().getName(), --ticketNum);
            }
        }

        @Override
        public void run() {
            while (runSwitch) {
                sellTicket2();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    log.info("thread interrupt :", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
