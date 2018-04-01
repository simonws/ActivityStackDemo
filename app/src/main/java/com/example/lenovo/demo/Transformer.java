package com.example.lenovo.demo;

/**
 * Created by lenovo on 2018/4/1.
 */

public interface Transformer<T, R> {
    R call(T from);
}
