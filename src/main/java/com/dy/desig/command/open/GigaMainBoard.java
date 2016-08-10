package com.dy.command.open;

/**
 * 命令接受者，真正执行命令的对象
 * Created by Administrator on 2016/4/22.
 */
public class GigaMainBoard implements MainBoard {
    @Override
    public void open() {
        System.out.println("技嘉主板正在开机，请稍后...");
        System.out.println("接通电源...");
        System.out.println("设备检查...");
        System.out.println("启动系统...");
        System.out.println("系统启动完成，进入桌面...");
    }

    @Override
    public void reset() {
        System.out.println("====电脑开始重启===");
        System.out.println("电脑关机中...");
        open();
        System.out.println("====电脑重启完成===");
    }
}
