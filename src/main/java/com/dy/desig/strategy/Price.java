package com.dy.desig.strategy;

/**
 * 报价管理不同等级的用户，价格不一样
 *
 * @author dy
 * @since 2016-08-10  & JDK 1.8.0_91
 */
public class Price {

    // 上下文对象，通常持有一个具体策略对象
    private Strategy strategy;

    public Price(Strategy strategy) {
        this.strategy = strategy;
    }
    /**
     * 用设计模式的解决方案
     */
    public double quote(double goodPrice){
        return this.strategy.calcPrice(goodPrice);
    }
}
