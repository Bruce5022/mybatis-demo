package com.sky.mybatis.thread;

public interface TaskLifecycle<T> {

    // 任务启动时会触发onStart方法
    void onStart(Thread thread);

    // 任务运行时会触发onRunning方法
    void onRunning(Thread thread);

    // 任务结束时会触发onFinish方法
    void onFinish(Thread thread, T result);

    // 任务运行报错时会触发onError方法
    void onError(Thread thread,Exception e);
}
