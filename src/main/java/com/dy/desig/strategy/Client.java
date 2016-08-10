package com.dy.desig.strategy;

import com.dy.desig.strategy.override.BigCustomerStrategy;

/**
 * @author dy
 * @since 2016-08-10  & JDK 1.8.0_91
 */
public class Client {
    public static void main(String[] args) {

        double goodsPrice = 1000;//商品市场价

        Strategy strategy = new BigCustomerStrategy();//商品策略

        Price ctx = new Price(strategy);//上下文

        System.out.println(ctx.quote(1000));//对应报价
    }
}
