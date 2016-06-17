package com.dy.desig.nattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 *
 * 王婆变身动态代理
 */
public class WangPoProxy implements InvocationHandler{

    private Object target;

    //绑定委托对象并返回一个代理类
    public Object createProxyTarget(Object target){
        this.target = target;
        //取得代理对象,要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.err.println("+++++++++开始切入++++++++++++");

        Object result=method.invoke(target, args);   //执行方法

        System.err.println("+++++++++++结束切入++++++++++");

        return result;
    }
}
