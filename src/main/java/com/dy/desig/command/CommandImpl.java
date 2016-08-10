package com.dy.command;

/**
 * 命令实现
 * 通常持有接受者，并调用接受者的功能来完成命令要执行的操作。
 * Created by Administrator on 2016/4/22.
 */
public class CommandImpl implements Command {
    private Receiver receiver;//持有相应的接受者对象
    private String status;//命令对象可以有自己的状态

    public CommandImpl(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //通常调用接受者对象相应方法，由接受者真正执行命令
        this.receiver.action();
    }

}
