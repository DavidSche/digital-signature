package com.dy.observer;

/**
 * 目标对象的继承者
 * 负责具体观察者状态
 * 当自己状态变化，通知所有观察者
 * Created by Administrator on 2016/4/22.
 */
public class NewsPaper extends Subject {
    private String subjectStatus;
    /**
     * 报纸内容
     */
    private String content;

    public String getSubjectStatus() {
        return subjectStatus;
    }


    public void setSubjectStatus(String subjectStatus) {
        this.subjectStatus = subjectStatus;
        //状态改变，通知所有观察者
        this.notifyObserver();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        //内容有了，说明报纸被印刷出来了，通知所有观察者
        this.notifyObserver();
    }
}
