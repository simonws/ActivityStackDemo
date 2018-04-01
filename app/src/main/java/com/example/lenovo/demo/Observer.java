package com.example.lenovo.demo;

/**
 * Created by lenovo on 2018/4/1.
 */

public interface Observer<T> {
    void onCompleted();
    void onError(Throwable t);
    void onNext(T var1);
}
