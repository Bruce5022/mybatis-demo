package com.sky.mybatis;

public class SynchronizTest {
    private static int count = 0;


    /**
     * 测试synchronized是否可以重入
     * 结论:是可以重入的
     *
     * @param threadName
     */
    public synchronized void modifyResources(String threadName) {
        System.out.println("通知<管理员>线程:---->" + threadName + "准备打水");
        synchronized (this) {
            System.out.println("线程:--->" + threadName + "第一次加锁");
            count++;
            System.out.println("线程:--->" + threadName + "打第" + count + "桶水");
        }
        synchronized (this) {
            System.out.println("线程:--->" + threadName + "第二次加锁");
            count++;
            System.out.println("线程:--->" + threadName + "打第" + count + "桶水");
        }
        System.out.println("线程:--->" + threadName + "释放第一个锁");
        System.out.println("线程:--->" + threadName + "释放第二个锁");

    }

    public static void main(String[] args) {
        LockTemplate tp = new LockTemplate();
        tp.modifyResources(Thread.currentThread().getName());
    }
}
