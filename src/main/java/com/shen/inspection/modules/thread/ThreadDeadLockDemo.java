package com.shen.inspection.modules.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程死锁模拟
 */
@Slf4j
public class ThreadDeadLockDemo {
    public static void main(String[] args) {
        ThreadTest threadTestA = new ThreadTest();
        ThreadTest threadTestB = new ThreadTest();
        threadTestA.setFlag(true);
        threadTestA.setName("A线程");
        threadTestB.setFlag(false);
        threadTestB.setName("B线程");
        threadTestA.start();
        threadTestB.start();
    }

    static class ThreadTest extends Thread {
        //保证线程公用同一个对象，成员属性设置为static
        static Object object1 = new Object();
        static Object object2 = new Object();
        private boolean flag = true;

        @Override
        public void run() {
            if (flag) {
                // 申请获取object1对象锁后申请获取object2对象锁
                synchronized (object1) {
                    log.info("{}  get object1 lock", Thread.currentThread().getName());
                    synchronized (object2) {
                        log.info("{}  get object2 lock", Thread.currentThread().getName());
                    }
                }
            } else {
                // 申请获取object2对象锁后申请获取object1对象锁
                synchronized (object2) {
                    log.info("{}  get object2 lock", Thread.currentThread().getName());
                    synchronized (object1) {
                        log.info("{}  get object1 lock", Thread.currentThread().getName());
                    }
                }
            }

        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}
