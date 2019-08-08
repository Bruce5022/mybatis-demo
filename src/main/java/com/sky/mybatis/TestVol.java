package com.sky.mybatis;

import java.util.concurrent.TimeUnit;

public class TestVol {

    private final static int MAX = 5;
    private volatile static int init_value = 0;

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            try {
                int localValue = init_value;
                while (localValue < MAX) {
//                    TimeUnit.SECONDS.sleep(1);
                    if (init_value != localValue) {

                        System.out.println(String.format("----->init_value的值修改为[%d]", init_value));
                        localValue = init_value;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Reader").start();

        new Thread(() -> {
            try {
                int localValue = init_value;
                while (localValue < MAX) {
                    System.out.println(String.format("++++++>init_value的值修改为[%d]", ++localValue));
                    init_value = localValue;
//                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Updater").start();

    }
}
