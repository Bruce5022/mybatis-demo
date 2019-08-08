package com.sky.mybatis.thread;

@FunctionalInterface
public interface Task<T> {
    T call();
}
