package com.dy.desig.responsibility;

/**
 * @author dy
 * @since 2016-08-12  & JDK 1.8.0_91
 */
public class GeneralManagerHandler extends Handler {

    public GeneralManagerHandler(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    public String requestToFee(String user, double fee) {
        if (fee>1000){
            return "总经理同意给你报销:"+fee;
        }
        else{
            if (nextHandler != null) {
                return nextHandler.requestToFee(user, fee);
            }
        }
        return "非法处理";
    }
}
