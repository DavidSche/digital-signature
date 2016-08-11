package com.dy.desig.decorator.override;

import com.dy.desig.decorator.TempDB;

/**
 * 装饰器对象 ，计算累计奖金
 *
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public class SumPrizeDecorator extends Decorator {
    public SumPrizeDecorator(Component component) {
        super(component);
    }

    @Override
    public double calcPrize(String user, int month) {
        double money = super.calcPrize(user, month);

        double sumPrize = TempDB.mapMonthSaleMoney.get(user) * 0.001;
        System.out.println(user + " " + month + "月份累计奖金:" + (sumPrize) + "==>>");

        return sumPrize + money;
    }
}
