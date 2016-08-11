package com.dy.desig.decorator.override;

import com.dy.desig.decorator.TempDB;

/**
 * 装饰器对象 ，计算累计奖金
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public class SumPrizeDecorator extends Decorator {
    public SumPrizeDecorator(Component component) {
        super(component);
    }

    @Override
    public double calcPrize(String user,int month) {
        double money = super.calcPrize(user,month);

        double totalPrize = 0.00;

        for (String userKey:TempDB.mapMonthSaleMoney.keySet()){

            double sumPrize = TempDB.mapMonthSaleMoney.get(userKey) * 0.001;

            System.out.println(userKey + " " +month+"月份应得奖金:"+(sumPrize+money)+"==>>");

            totalPrize+=sumPrize + money;
        }
        return  totalPrize;
    }
}
