package com.dy.command.open;

import com.dy.command.Command;

/**
 * 命令实现
 * 通常持有接受者，并调用接受者的功能来完成命令要执行的操作。
 * Created by Administrator on 2016/4/22.
 */
public class ResetCommand implements Command {
    /**
     * 持有真正的命令实现-接受者
     */
    private MainBoard mainBoard;

    public ResetCommand(MainBoard mainBoard) {
        this.mainBoard = mainBoard;
    }

    @Override
    public void execute() {
        //对于命令对象根本不需要关心命令如何执行(这里指如何开机)，转掉接受者方法
        this.mainBoard.reset();
    }
}
