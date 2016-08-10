package com.dy.desig.strategy;

/**
 * 策略接口
 * @author dy
 * @since 2016-08-10  & JDK 1.8.0_91
 */
public interface Strategy {
    /**
     * 某算法接口
     */
    void algorithmInteface();

    /**
     * 计算价格
     * @param goodPrice
     * @return
     */
    double calcPrice(double goodPrice);
}
