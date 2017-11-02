package com.bp.ribbonconsumer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bp.ribbonconsumer.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

    public HttpEntity<User> getHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<User> httpEntity = new HttpEntity<>(headers);
        //如果是用request传送报文的话，可以使用下面这种，参数params型就给规定个请求头类型就行了
        //HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        return httpEntity;
    }

    /**
     * Hystrix默认超时为2000ms，可以设置
     */
    @HystrixCommand(fallbackMethod = "userBack",
            groupKey = "user",
            commandKey = "getUserBySyncPOST",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000")
            })
    public User getUserBySyncPOST(Integer id){
        //同步方式请求数据
        System.out.println("进来了："+id);
        return restTemplate.postForObject("http://hello-service/post-user?id={1}", getHttpEntity(), User.class, id);
    }

    @HystrixCommand(fallbackMethod = "userBack",
            groupKey = "user",
            commandKey = "getUserBySyncGET",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000")
            })
    public User getUserBySyncGET(Integer id){
        //同步方式请求数据
        System.out.println("GET进来了："+id);
        User user = restTemplate.getForObject("http://hello-service/get-user/{1}", User.class, id.toString());
        return user;
    }


    @HystrixCommand(fallbackMethod = "userBack",
            groupKey = "user",
            commandKey = "getUserByAsyncPOST",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000")
            })
    public Future<User> getUserByAsyncPOST(final Integer id){
        //异步的方式请求数据
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.postForObject("http://hello-service/post-user?id={1}", getHttpEntity(), User.class, id);
            }
        };
    }


    @HystrixCommand(fallbackMethod = "userBack",
            groupKey = "user",
            commandKey = "getUserByObservablePOST",
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
    public Observable<User> getUserByObservablePOST(final Integer id){
        Observable<User> user = Observable.create(new Observable.OnSubscribe<User>(){
            @Override
            public void call(Subscriber<? super User> observable) {
                try {
                    if (!observable.isUnsubscribed()){
                        User temp = restTemplate.postForObject("http://hello-service/post-user?id={1}", getHttpEntity(), User.class, id);
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
            commandKey = "getUserByToObservablePOST",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            observableExecutionMode = ObservableExecutionMode.LAZY
    )
    public Observable<User> getUserByToObservablePOST(final Integer id){
        Observable<User> user = Observable.create(new Observable.OnSubscribe<User>(){
            @Override
            public void call(Subscriber<? super User> observable) {
                try {
                    if (!observable.isUnsubscribed()){
                        User temp = restTemplate.postForObject("http://hello-service/post-user?id={1}", getHttpEntity(), User.class, id);
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
