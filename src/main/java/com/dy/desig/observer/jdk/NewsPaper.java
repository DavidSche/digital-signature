package com.dy.desig.observer.jdk;

import java.util.Observable;

/**
 * Created by Administrator on 2016/4/22.
 */
public class NewsPaper extends Observable {

    /**
     * 报纸内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        //内容有了，说明报纸被印刷出来了，通知所有观察者

        //注意JDK的observer模式的时候，下面这句代码不可少
        this.setChanged();
        //主动通知，这里用推的方式
        this.notifyObservers(this.content);
        //如果用的拉的方式，如下代码示例
        //this.notifyObservers();
    }

}
