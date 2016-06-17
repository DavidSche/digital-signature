package com.dy.observer;

/**
 * 读者，报纸订阅者
 *
 * 观察者实现类
 * 实现更新方法，使自身状态和目标状态保持一致
 * Created by Administrator on 2016/4/22.
 */
public class Reader implements Observer {


    private String observerStatus;
    private String name;

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        //实现更新方法，使自身状态和目标状态保持一致
        //采用拉的方式
        observerStatus = ((NewsPaper) subject).getSubjectStatus();
        System.out.println(name+"收到了报纸，阅读它，内容是："+((NewsPaper) subject).getContent());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
