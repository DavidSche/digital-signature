package com.dy.desig.decorator.override;

/**
 * 计算金额的组件接口
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public abstract class Component {
    /**
     * 计算奖金
     * @param month
     */
    public abstract double calcPrize(String user,int month);
}
