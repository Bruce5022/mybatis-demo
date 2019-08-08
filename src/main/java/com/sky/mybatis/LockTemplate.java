package com.sky.mybatis;

import java.util.concurrent.locks.ReentrantLock;

public class LockTemplate {
    private static int count = 0;

    /**
     * 可重入锁,公平锁
     * 需要保证多个线程使用的是同一个锁
     *
     * synchronized是否可重入?
     * 虚拟机,在ObjectMonitor.hpp定义了synchronized他怎么去重入加锁...hotspot源码
     */
    private ReentrantLock lock = new ReentrantLock(true);

    /**
     * 需要保证多个线程使用的是同一个ReentrantLock对象
     *
     * @param threadName
     */
    public void modifyResources(String threadName) {
        System.out.println("通知<管理员>线程:---->" + threadName + "准备打水");
        lock.lock();
        System.out.println("线程:--->" + threadName + "第一次加锁");
        count++;
        System.out.println("线程:--->" + threadName + "打第" + count + "桶水");
        lock.lock();
        System.out.println("线程:--->" + threadName + "第二次加锁");
        count++;
        System.out.println("线程:--->" + threadName + "打第" + count + "桶水");
        lock.unlock();
        System.out.println("线程:--->" + threadName + "释放第一个锁");
        lock.unlock();
        System.out.println("线程:--->" + threadName + "释放第二个锁");

    }

    public static void main(String[] args) {
        LockTemplate tp = new LockTemplate();
//        tp.modifyResources(Thread.currentThread().getName());
        for (int i = 0; i < 5; i++) {
            new Thread(() -> tp.modifyResources(Thread.currentThread().getName())).start();
        }
    }
}
