package com.dy.desig.observer.jdk;

/**
 * Created by Administrator on 2016/4/22.
 */
public class Client {
    public static void main(String[] args) {

        //报纸被观察者
        NewsPaper jdk_subject = new NewsPaper();

        //报纸订阅者，也就是观察者
        Reader reader = new Reader("张三");
        Reader reader1 = new Reader("李四");
        Reader reader2 = new Reader("王二");

        //注册订阅者
        jdk_subject.addObserver(reader);
        jdk_subject.addObserver(reader1);
        jdk_subject.addObserver(reader2);

        //印刷出报纸内容
        jdk_subject.setContent("本期内容介绍java JDK实现观察者模式");

    }
}
