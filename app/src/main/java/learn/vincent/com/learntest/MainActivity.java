package learn.vincent.com.learntest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

//TODO leanURL:http://gank.io/post/560e15be2dca930e00da1083
public class MainActivity extends AppCompatActivity {
    private TextView vTitleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vTitleView = (TextView) findViewById(R.id.title);
        vTitleView.setText("test world");
        vTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"click this",Toast.LENGTH_LONG).show();
            }
        });
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
        //Action0 --> complete ;Action1<String> --> OnNext;Action1<Throw> --> onError
        observable2.subscribe(new Action1<String>() {

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

       Observable.just("1","2","3").
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("RX","Rx-observeOn-onNext:"+e);
                    }

                    @Override
                    public void onNext(String o) {
                        Log.d("RX","Rx-observeOn-onNext:"+o);
//                        while(true){
//                            Log.d("RX","Rx-observeOn-onNext:"+o);
//                        }
                    }
                });

    }
}
