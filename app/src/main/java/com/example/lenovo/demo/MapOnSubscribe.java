package com.example.lenovo.demo;

/**
 * Created by lenovo on 2018/4/1.
 */

public class MapOnSubscribe<T, R> implements Observable.OnSubscribe<R> {
    final Observable<T> source;
    final Transformer<? super T, ? extends R> transformer;
    public MapOnSubscribe(Observable<T> source, Transformer<? super T, ? extends R> transformer) {
        this.source = source;
        this.transformer = transformer;
    }
    @Override
    public void call(Subscriber<? super R> subscriber) {
        source.subscribe(new MapSubscriber<R, T>(subscriber, transformer));
    }
}
