package com.dy.desig.decorator.override;

import com.dy.desig.decorator.TempDB;

/**
 * 装饰器对象 ，计算当月业务奖金
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public class MonthPrizeDecorator extends Decorator {
    public MonthPrizeDecorator(Component component) {
        super(component);
    }

    @Override
    public double calcPrize(String user,int month) {
        double money = super.calcPrize(user,month);

        double totalPrize = 0.00;

        for (String userKey:TempDB.mapMonthSaleMoney.keySet()){

            double monthPrize = TempDB.mapMonthSaleMoney.get(userKey) * 0.03;

            System.out.println(userKey + " " +month+"月份应得奖金:"+(monthPrize+money)+"==>>");

            totalPrize+=monthPrize + money;
        }
        return  totalPrize;
    }
}
