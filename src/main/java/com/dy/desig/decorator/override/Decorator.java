package com.dy.desig.decorator.override;

/**
 * 装饰器
 * 需要和被装饰的对象实现同样的接口
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public abstract class Decorator extends Component {

    /**
     * 持有被装饰的对象
     */
    protected  Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public double calcPrize(String user,int month) {
        double prize =  component.calcPrize(user,month);//转调组件对象的方法
        return prize;
    }
}
