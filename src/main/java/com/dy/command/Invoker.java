package com.dy.command;

/**
 * 调用者
 * 要求命令对象执行请求，通常持有命令对象，可以持有很多命令对象
 * 这是客户端真正触发命令并要求命令执行相应操作的地方，相当于所有命令对象的入口
 * Created by Administrator on 2016/4/22.
 */
public class Invoker {

    private Command command;//持有命令对象

    /*
    *设置命令
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 要求命令执行请求
     */
    public void run(){
        command.execute();
    }
}
