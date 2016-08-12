package com.dy.desig.responsibility;

/**
 * 定义职责对象接口
 * @author dy
 * @since 2016-08-12  & JDK 1.8.0_91
 */
public abstract class Handler {

    /**
     * 通常持有下一个处理的请求对象
     */
    protected Handler nextHandler;

    public Handler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }


    /**
     * 处理申请
     * @param user
     * @param fee
     * @return
     */
    public abstract String requestToFee(String user,double fee);
}
