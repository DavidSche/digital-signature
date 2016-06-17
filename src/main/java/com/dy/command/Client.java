package com.dy.command;

/**
 * 创建具体命令对象，并设置命令对象的接受者
 * Created by Administrator on 2016/4/22.
 */
public class Client {
    public void doCommand() {
        Receiver receiver = new Receiver();  //创建接受者者
        Command command = new CommandImpl(receiver);//创建命令对象
        Invoker invoker = new Invoker();       //创建Invoker
        invoker.setCommand(command);           //设置命令对象
        invoker.run();
    }

}

class Test{
    public static void main(String[] args) {
        Client client = new Client();
        client.doCommand();
    }
}
