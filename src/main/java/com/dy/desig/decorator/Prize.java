package com.dy.desig.decorator;

/**
 * 计算奖金对象
 *
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public class Prize {

    /**
     * 计算某人当月的业务奖金
     *
     * @param user
     * @param month
     * @return
     */
    private double monthPrize(String user, int month) {

        //当月业务奖金 = 业务额*3%
        double prize = TempDB.mapMonthSaleMoney.get(user) * 0.03;
        System.out.println(user + " " + month + "月份业务奖金:" + prize);
        return prize;
    }

    /**
     * 计算某人当月累计奖金
     *
     * @param user
     * @param month
     * @return
     */
    private double sumPrize(String user, int month) {

        //这里演示 假设每人每月销售业务金额都是相同的哈
        //累计业务奖金 = 累计业务金额*0.1%
        double prize = TempDB.mapMonthSaleMoney.get(user) * 0.001;
        System.out.println(user + " " +month+"月份累计奖金:"+prize);
        return prize;
    }


    /**
     * 是否是业务经理
     *
     * @param user
     * @return
     */
    private boolean isManager(String user) {
        return TempDB.saleManager.get("经理").equals(user);
    }

    /**
     * 计算当月团队奖金
     *
     * @param user
     * @param month
     * @return
     */
    private double groupPrize(String user, int month) {

        //团队奖金 = 团队总业务额 * 1%
        double prize = 0.00;
        for (double amount : TempDB.mapMonthSaleMoney.values()) {
            prize += amount;
        }
        prize = prize * 0.01;
        System.out.println(user + " " +month+"月份团队奖金:"+prize);
        return prize;
    }

    /**
     * 计算奖金
     * @param month
     * @return
     */
    public void calcPrize(int month) {
        for (String user:TempDB.mapMonthSaleMoney.keySet()){

            double monthPrize = this.monthPrize(user, month);

            double sumPrize = this.sumPrize(user, month);

            double groupPrize = 0.00;
            if (isManager(user)){
                groupPrize = groupPrize(user, month);

            }

            System.out.println(user + " " +month+"月份应得奖金:"+(monthPrize+sumPrize+groupPrize)+"==>>");
        }

    }
}
