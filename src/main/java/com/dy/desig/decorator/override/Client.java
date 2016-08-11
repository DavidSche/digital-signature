package com.dy.desig.decorator.override;

/**
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public class Client {

    public static void main(String[] args) {
        //创建基本奖金对象
        Component component = new ConcreteComponent();

        /**
         * 对基本奖金进行装饰,这里要组合各个装饰
         */
        Decorator decorator1 = new MonthPrizeDecorator(component);
        Decorator decorator2 = new SumPrizeDecorator(decorator1);

        //这里只需要用最后组合好的对象调用业务方法即可
        double prize = decorator2.calcPrize(null, 2);
        System.out.println("<<==财务需要支付金额:"+prize);
    }
}
