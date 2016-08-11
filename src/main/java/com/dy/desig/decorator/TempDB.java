package com.dy.desig.decorator;

import java.util.HashMap;
import java.util.Map;

/**
 * 在内存中模拟DB，准备好测试数据
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public class TempDB {

    /**
     * 记录每人每月销售额，这里为了演示，只有人员，没有月份
     * 这里演示 假设每人每月销售业务金额都是相同的哈
     */
    public static Map<String,Double> mapMonthSaleMoney = new HashMap<>();
    public static Map<String,String> saleManager = new HashMap<>();

    static {
        mapMonthSaleMoney.put("杨过", 10000.00);
        mapMonthSaleMoney.put("令狐冲", 20000.00);
        mapMonthSaleMoney.put("韦小宝", 30000.00);
        mapMonthSaleMoney.put("dy", 10000.00);

        saleManager.put("经理", "dy");
    }
}
