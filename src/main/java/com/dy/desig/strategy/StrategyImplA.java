package com.dy.desig.strategy;

/**
 * @author dy
 * @since 2016-08-10  & JDK 1.8.0_91
 */
public class StrategyImplA implements Strategy {
    @Override
    public void algorithmInteface() {

    }

    @Override
    public double calcPrice(double goodPrice) {
        return goodPrice;
    }
}
