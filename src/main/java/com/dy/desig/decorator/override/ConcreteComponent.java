package com.dy.desig.decorator.override;

/**
 * 基本的实现类,默认实现,也是被装饰的对象
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public class ConcreteComponent extends Component {
    @Override
    public double calcPrize(String user,int month) {
        System.out.println("默认没有奖金!");
        return 0;
    }
}
