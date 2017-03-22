package learn.vincent.com.learntest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Observable.from().flatMap().filter().map().observeOn().subscribeOn().subscribe();
//        Subscription
        Observable observable1 = Observable.just("test","onNext");
        String[] params = new String[]{"test","onNext","Strings"};
        Observable observable2 = Observable.from(params);
        //params as observable feature,if Observable get numbers param,then the observer or
        //subscriber must be notice numbers time. no change to notice .observable will tell subscriber
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d("RX","Rx--observable--call");
                subscriber.onNext("RX--Hi");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(new Action1<String>() {

            @Override
            public void call(String o) {
                Log.d("RX","Rx--onNext:"+o);
            }

        });
        //TODO when subscriber subscribe observable,subscriber will call start method,and observable
        //TODO also call call method.
//        Subscription subscription =  observable.subscribe(new Subscriber() {
//            @Override
//            public void onStart() {
//                super.onStart();
//                Log.d("RX","Rx--subscriber--onStart");
//            }
//
//            @Override
//            public void onCompleted() {
//                Log.d("RX","Rx--onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("RX","Rx--onError"+e.getMessage());
//            }
//
//            @Override
//            public void onNext(Object o) {
//                Log.d("RX","Rx--onNext:"+o);
//            }
//        });

    }
}
