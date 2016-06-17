package com.dy.command.open;

/**
 * Created by Administrator on 2016/4/22.
 */
public class Client {
    public static void main(String[] args) {
        /**
         * 把机箱按钮连接线插到主板上
         */
        MainBoard mainBoard = new GigaMainBoard();
        OpenCommand openCommand = new OpenCommand(mainBoard);
        ResetCommand resetCommand = new ResetCommand(mainBoard);

        /**
         * 给机箱安装开机按钮
         */
        Box box = new Box();
        box.setOpenCommand(openCommand);
        box.setResetCommand(resetCommand);
        /**
         * 摁下开机按钮
         */
        box.openButtonPressed();
        /**
         * 重启下试试
         */
        box.resetButtonPressed();
    }
}
