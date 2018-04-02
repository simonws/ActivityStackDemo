package com.example.lenovo.demo.annotationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lenovo.demo.R;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationActivityDemo extends AppCompatActivity {

    private static final String TAG = "AnnotationActivityDemo" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation_demo);
    }


    @Override
    protected void onResume() {
        super.onResume();

        AnnotationTest annotationTest = new AnnotationTest();
        //获取AnnotationTest2的Class实例
        Class<AnnotationTest> c = AnnotationTest.class;
        //获取需要处理的方法Method实例
        Method method = null;
        try {
            method = c.getMethod("execute", new Class[]{});

            //判断该方法是否包含MyAnnotation注解
            if(method.isAnnotationPresent(MyAnnotation.class)){
                //获取该方法的MyAnnotation注解实例
                MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);

                String[] value0 = myAnnotation.value1();
                Log.d(TAG," ==== value0 =" + value0[0]);
                //执行该方法
                    method.invoke(annotationTest, new Object[]{});
                //获取myAnnotation
                String[] value1 = myAnnotation.value1();
                Log.d(TAG," ==== value1 =" + value1[0]);
            }
            //获取方法上的所有注解
            Annotation[] annotations = method.getAnnotations();
            for(Annotation annotation : annotations){
                Log.d(TAG, " == " + annotation);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
