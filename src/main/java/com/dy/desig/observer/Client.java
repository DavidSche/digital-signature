package com.dy.desig.observer;

/**
 * Created by Administrator on 2016/4/22.
 */
public class Client {
    public static void main(String[] args) {

        //报纸被观察者
        NewsPaper newsPager = new NewsPaper();

        //报纸订阅者，也就是观察者
        Reader reader = new Reader("张三");
        Reader reader1 = new Reader("李四");
        Reader reader2 = new Reader("王二");

        //注册订阅者
        newsPager.attach(reader);
        newsPager.attach(reader1);
        newsPager.attach(reader2);

        //印刷出报纸内容
        newsPager.setContent("本期内容介绍观察者模式");
    }
}
