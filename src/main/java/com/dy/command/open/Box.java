package com.dy.command.open;

import com.dy.command.Command;

/**
 * 机箱，本身有按钮，即持有命令对象
 * 调用者
 * 要求命令对象执行请求，通常持有命令对象，可以持有很多命令对象
 * 这是客户端真正触发命令并要求命令执行相应操作的地方，相当于所有命令对象的入口
 * Created by Administrator on 2016/4/22.
 */
public class Box {
    /**
     * 开机命令
     */
    private Command openCommand;
    private Command resetCommand;

    public void setOpenCommand(Command openCommand) {
        this.openCommand = openCommand;
    }

    public void setResetCommand(Command resetCommand) {
        this.resetCommand = resetCommand;
    }

    /**
     * 按下按钮触发方法
     */
    public void openButtonPressed() {
        this.openCommand.execute();
    }

    /**
     * 按下按钮出发方法
     */
    public void resetButtonPressed() {
        this.resetCommand.execute();
    }

}
