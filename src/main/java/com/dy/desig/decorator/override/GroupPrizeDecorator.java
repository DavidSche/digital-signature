package com.dy.desig.decorator.override;

import com.dy.desig.decorator.TempDB;

/**
 * 装饰器对象 ，计算累计奖金
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public class GroupPrizeDecorator extends Decorator {
    public GroupPrizeDecorator(Component component) {
        super(component);
    }

    @Override
    public double calcPrize(String user,int month) {
        double money = super.calcPrize(user,month);

        double totalPrize = 0.00;

        //团队奖金 = 团队总业务额 * 1%
        double prize = 0.00;
        for (double amount : TempDB.mapMonthSaleMoney.values()) {
            prize += amount;
        }
        prize = prize*0.01;
        System.out.println(user + " " +month+"月份应得奖金:"+(prize+money)+"==>>");
        return  money + prize;
    }
}
