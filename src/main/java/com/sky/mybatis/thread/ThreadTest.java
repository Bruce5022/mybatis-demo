package com.sky.mybatis.thread;

import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
        Observable observableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finished done .");
            return null;
        });
        observableThread.start();
    }

    private static void test2() {
        final TaskLifecycle<String> lifecycle = new EmptyLifecycle<String>(){
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("the result is : "+ result);
            }
        };

        Observable observableThread = new ObservableThread<>(lifecycle , () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finished done .");
            return "<<< Hello Observer >>>";
        });
        observableThread.start();
    }
}
