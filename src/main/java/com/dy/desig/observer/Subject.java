package com.dy.desig.observer;

import java.util.ArrayList;
import java.util.List;


/**
 * 目标对象 ,作为被观察者
 * 它知道观察者，并提供注册和删除观察者
 * Created by Administrator on 2016/4/22.
 */
public class Subject {

    private List<Observer> observerList = new ArrayList<>();

    /**
     * 注册观察者 ，报纸订阅者
     * @param observer
     */
    public void attach(Observer observer){
        observerList.add(observer);
    }

    /**
     * 删除观察者
     * @param observer
     */
    public void detach(Observer observer){
        observerList.remove(observer);
    }

    /**
     * 通知所有观察者
     * 每当报纸被印出来，及时主动通知所有订阅者，好让他们知道
     */
    protected void notifyObserver(){
        for (Observer observer:observerList){
            observer.update(this);
        }
    }
}
