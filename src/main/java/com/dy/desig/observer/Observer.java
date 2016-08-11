package com.dy.desig.observer;

/**
 * 观察者接口
 * 提供通知更新方法 (可以在这个方法中回调目标对象)
 * Created by Administrator on 2016/4/22.
 */
public interface Observer {
    void update(Subject subject);
}
