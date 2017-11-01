package com.bp.ribbonconsumer.command;

import com.bp.ribbonconsumer.entity.User;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

/**
 * 这个类是用原生代码实现的熔断
 * UserService类是用HystrixCommand注解实现的熔断
 */
public class UserObservableCommand extends HystrixObservableCommand<User>{

    private RestTemplate restTemplate;
    private Integer id;

    protected UserObservableCommand(Setter setter, RestTemplate restTemplate, Integer id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected Observable<User> construct() {
        Observable<User> observableUser = Observable.create(new Observable.OnSubscribe<User>(){

            @Override
            public void call(Subscriber<? super User> observer) {
                try {
                    if (!observer.isUnsubscribed()){
                        User user = restTemplate.getForObject("http://hello-service/user/{1}", User.class, id);
                        observer.onNext(user);
                        observer.onCompleted();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    observer.onError(e);
                }
            }

        });
        return observableUser;
    }
}
