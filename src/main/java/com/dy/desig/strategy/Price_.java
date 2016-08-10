package com.dy.desig.strategy;

/**
 * 报价管理不同等级的用户，价格不一样
 * @author dy
 * @since 2016-08-10  & JDK 1.8.0_91
 */
public class Price_ {
    /**
     * 这里不用设计模式的解决方案
     */
    public double quote(double goodPrice,String customerType){
        switch (customerType){
            case "普通客户":
                return goodPrice;
            case "老客户":
                return goodPrice*0.95;
            case "大客户":
                return goodPrice*0.90;
            default:
                return goodPrice;
        }
    }
}
