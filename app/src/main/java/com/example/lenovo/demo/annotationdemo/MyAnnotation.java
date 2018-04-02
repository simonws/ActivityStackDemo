package com.example.lenovo.demo.annotationdemo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lenovo on 2018/4/2.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String[] value1() default "abc";
}
