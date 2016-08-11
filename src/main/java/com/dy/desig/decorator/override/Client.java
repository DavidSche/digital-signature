package com.dy.desig.decorator.override;

import com.dy.desig.decorator.TempDB;

/**
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public class Client {

    public static void main(String[] args) {

        for (String user : TempDB.mapMonthSaleMoney.keySet()) {
            //创建基本奖金对象
            Component component = new ConcreteComponent();

            /**
             * 对基本奖金进行装饰,这里要组合各个装饰
             */
            Decorator decorator1 = new MonthPrizeDecorator(component);
            Decorator decorator2 = new SumPrizeDecorator(decorator1);
            Decorator decorator3 = new GroupPrizeDecorator(decorator2);


            //这里只需要用最后组合好的对象调用业务方法即可
            double prize = decorator3.calcPrize(user, 3);
            System.out.println(user + " " +3+"月份应得奖金:"+prize+"==>>");
        }
    }
}
