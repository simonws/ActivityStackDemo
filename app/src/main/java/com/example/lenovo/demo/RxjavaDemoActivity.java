package com.example.lenovo.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class RxjavaDemoActivity extends AppCompatActivity {

    private static final String TAG = "RxjavaDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    protected void onResume() {
        super.onResume();


//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; i < 10; i++) {
//                    subscriber.onNext(i);
//                }
//            }
//        }).map(new Transformer<Integer, String>() {
//            @Override
//            public String call(Integer from) {
//                return "maping " + from;
//            }
//        }).subscribe(new Subscriber<String>() {
//            @Override
//            public void onNext(String var1) {
//                Log.d(TAG,"onNext == " + var1);
//            }
//            @Override
//            public void onCompleted() {}
//            @Override
//            public void onError(Throwable t) {}
//        });


        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("OnSubscribe@ "+Thread.currentThread().getName()); //new Thread
                subscriber.onNext(1);
            }})
                .subscribeOn(Scheduler.Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onNext(Integer var1) {
                        System.out.println("Subscriber@ "+Thread.currentThread().getName()); // new Thread
                        System.out.println(var1);
                    }
                });
    }
}
