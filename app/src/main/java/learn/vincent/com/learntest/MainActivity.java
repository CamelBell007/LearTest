package learn.vincent.com.learntest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import learn.vincent.com.learntest.bean.Course;
import learn.vincent.com.learntest.bean.Student;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

//TODO leanURL:http://gank.io/post/560e15be2dca930e00da1083
public class MainActivity extends AppCompatActivity {
    private TextView vTitleView;
    private ImageView vIconImage;
    private Student[] mStudnets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vTitleView = (TextView) findViewById(R.id.title);
        vIconImage = (ImageView) findViewById(R.id.icon_image);
        vTitleView.setText("test world");
        vTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click this", Toast.LENGTH_LONG).show();
            }
        });
        mStudnets = new Student[2];
        ArrayList<Course> maryCourse = new ArrayList<Course>();
        maryCourse.add(new Course("china"));
        maryCourse.add(new Course("english"));
        ArrayList<Course> selinaCourse = new ArrayList<Course>();
        selinaCourse.add(new Course("history"));
        selinaCourse.add(new Course("chemistry"));
        mStudnets[0] = new Student("mary", 15, maryCourse);
        mStudnets[1] = new Student("selina", 14, selinaCourse);
//        Observable.from().flatMap().filter().map().observeOn().subscribeOn().subscribe();
//        Subscription
//        Observable observable1 = Observable.just("test","onNext");
//        String[] params = new String[]{"test","onNext","Strings"};
//        Observable observable2 = Observable.from(params);
//        //params as observable feature,if Observable get numbers param,then the observer or
//        //subscriber must be notice numbers time. no change to notice .observable will tell subscriber
//        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                Log.d("RX","Rx--observable--call");
//                subscriber.onNext("RX--Hi");
//                subscriber.onCompleted();
//            }
//        });
//        //Action0 --> complete ;Action1<String> --> OnNext;Action1<Throw> --> onError
//        observable2.subscribe(new Action1<String>() {
//
//            @Override
//            public void call(String o) {
//                Log.d("RX","Rx--onNext:"+o);
//            }
//
//        });
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

//       Observable.just("1","2","3").
//                subscribeOn(Schedulers.io()).
//                observeOn(AndroidSchedulers.mainThread()).
//                subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("RX","Rx-observeOn-onNext:"+e);
//                    }
//
//                    @Override
//                    public void onNext(String o) {
//                        Log.d("RX","Rx-observeOn-onNext:"+o);
////                        while(true){
////                            Log.d("RX","Rx-observeOn-onNext:"+o);
////                        }
//                    }
//                });
        //map将一个参数变换之后转变为两外一个参数
        //flatmap将一个集合的数据转换为N个单一的参数，过程中可以有很多变化求值。
//                Observable.just("images/ic_launcher.png")//传入的参数T泛型，什么类型也都是可以接收的
//                        .map(new Func1<String, Bitmap>() {
//                            @Override
//                            public Bitmap call(String s) {
//
//                                return getBitmapFromPath(s);
//                            }
//                        })
//                        .subscribe(new Action1<Bitmap>() {
//                            @Override
//                            public void call(Bitmap bitmap) {
//
//                            }
//                        });
//    }
//                Subscriber subscriber = new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(String string) {
//                        Log.d("RX","Rx-flatMap-onNext:"+string);
//                    }
//                };
//                 Observable.from(mStudnets)
//                        .map(new Func1<Student,String>() {
//
//                            @Override
//                            public String call(Student student) {
//                                return student.getName();
//                            }
//                        })
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(Schedulers.newThread())
//                        .subscribe(subscriber);

        Subscriber subscriber4 = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course string) {
                Log.d("RX", "Rx-flatMap-onNext:" + string.getName());
            }
        };
        //flatMap()中返回的是一个Observable对象，并且这个对象并不是直接被送到了Subscriber的回调方法中。
        //flatMap的原理是这样的：
        //1:使用传入的事件对象创建一个Observable对象；
        //2：并发送这个对象而是激活它，于是它开始发送事件。
        //3:每一个创建出来的Observable发送的事件都被汇入同一个Observable,而这个Observable负责将这些事件统一交给
        //subscriber的回调方法。
        Observable.from(mStudnets)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //类似onStart方法。这个方法可以指定所处的线程。
                    }
                })
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                }).subscribe(subscriber4);
        //TODO变换的功能虽然各有不同，但是实质上都是针对事件序列的处理和发送life(operator)
    }
}
