package com.example.lenovo.demo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by lenovo on 2018/4/1.
 */

public class Scheduler {
    final Executor executor;
    public Scheduler(Executor executor) {
        this.executor = executor;
    }
    public Worker createWorker() {
        return new Worker(executor);
    }
    public static class Worker {
        final Executor executor;
        public Worker(Executor executor) {
            this.executor = executor;
        }
        // 这里接受的是Runnable而不是Action0，其实这没什么关系，主要是懒得自定义函数式接口了。
        public void schedule(Runnable runnable) {
            executor.execute(runnable);
        }
    }


    public class Schedulers {
        private  final Scheduler ioScheduler = new Scheduler(Executors.newSingleThreadExecutor());
        public  Scheduler io() {
            return ioScheduler;
        }
    }
}
