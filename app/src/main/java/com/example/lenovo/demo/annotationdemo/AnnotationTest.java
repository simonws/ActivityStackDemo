package com.example.lenovo.demo.annotationdemo;

import android.util.Log;

/**
 * Created by lenovo on 2018/4/2.
 */

public class AnnotationTest {
    String TAG = "AnnotationTest";
    @MyAnnotation(value1={"a","b"})
    @Deprecated
    public void execute(){
        Log.d(TAG,"method execute");
    }
}
