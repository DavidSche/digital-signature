package com.dy.observer.java;


import java.util.Observable;
import java.util.Observer;

/**
 * 读者，报纸订阅者
 *
 * 观察者实现类
 * 实现更新方法，使自身状态和目标状态保持一致
 * Created by Administrator on 2016/4/22.
 */
public class Reader implements Observer {

    private String name;

    public Reader(String name) {
        this.name = name;
    }
    @Override
    public void update(Observable o, Object obj) {
        System.out.println(name+"收到了报纸，目标推过来的内容是："+obj);
        //拉的方式你自己想想就知道了((NewsPaper) obj).getContent())
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
