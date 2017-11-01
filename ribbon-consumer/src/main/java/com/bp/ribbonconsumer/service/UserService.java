package com.bp.ribbonconsumer.service;

import com.bp.ribbonconsumer.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

/**
 * 这个类是用@HystrixCommand注解实现的熔断
 * UserCommand和UserObservableCommand是用原生代码实现的熔断
 */
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Hystrix默认超时为2000ms，可以设置
     */
    @HystrixCommand(fallbackMethod = "userBack",
            groupKey = "user",
            commandKey = "getUserBySync",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000")
            })
    public User getUserBySync(Integer id){
        //同步方式请求数据
        System.out.println(id);
        User user = new User(id);
        return restTemplate.postForObject("http://hello-service/user", user, User.class);
    }


    @HystrixCommand(fallbackMethod = "userBack",
            groupKey = "user",
            commandKey = "getUserByAsync",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000")
            })
    public Future<User> getUserByAsync(final Integer id){
        //异步的方式请求数据
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                User user = new User(id);
                return restTemplate.postForObject("http://hello-service/user", user, User.class);
            }
        };
    }


    @HystrixCommand(fallbackMethod = "userBack",
            groupKey = "user",
            commandKey = "getUserByObservable",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            //默认方式，表示使用observe()执行方式
            //返回一个Hot Observable，在observe()调用的时候立即执行
            //当Observable每次被订阅的时候会重放它的行为
            observableExecutionMode = ObservableExecutionMode.EAGER
            //表示使用toObservable()执行方式
            //返回一个Cold Observable，在toObservable()调用的时候不会立即执行
            //当所有订阅者都订阅它后才会执行
            //observableExecutionMode = ObservableExecutionMode.LAZY
    )
    public Observable<User> getUserByObservable(final Integer id){
        Observable<User> user = Observable.create(new Observable.OnSubscribe<User>(){
            @Override
            public void call(Subscriber<? super User> observable) {
                try {
                    if (!observable.isUnsubscribed()){
                        User temp = new User(id);
                        temp = restTemplate.postForObject("http://hello-service/user", temp, User.class);
                        observable.onNext(temp);
                        observable.onCompleted();
                    }
                }catch (Exception e){
                    observable.onError(e);
                }
            }
        });
        return user;
    }


    @HystrixCommand(fallbackMethod = "userBack",
            groupKey = "user",
            commandKey = "getUserByToObservable",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            observableExecutionMode = ObservableExecutionMode.LAZY
    )
    public Observable<User> getUserByToObservable(final Integer id){
        Observable<User> user = Observable.create(new Observable.OnSubscribe<User>(){
            @Override
            public void call(Subscriber<? super User> observable) {
                try {
                    if (!observable.isUnsubscribed()){
                        User temp = new User(id);
                        temp = restTemplate.postForObject("http://hello-service/user", temp, User.class);
                        observable.onNext(temp);
                        observable.onCompleted();
                    }
                }catch (Exception e){
                    observable.onError(e);
                }
            }
        });
        return user;
    }

    public User userBack(Integer id){
        return new User();
    }

}
