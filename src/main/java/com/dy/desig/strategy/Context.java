package com.dy.desig.strategy;

/**
 * 上下文对象，通常持有一个具体策略对象
 * @author dy
 * @since 2016-08-10  & JDK 1.8.0_91
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void contextInterface(Strategy strategy) {
        //通常会转掉具体策略对象的算法
        strategy.algorithmInteface();
    }
}
