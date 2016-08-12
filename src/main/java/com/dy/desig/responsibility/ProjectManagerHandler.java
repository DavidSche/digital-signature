package com.dy.desig.responsibility;

/**
 * @author dy
 * @since 2016-08-12  & JDK 1.8.0_91
 */
public class ProjectManagerHandler extends Handler {
    public ProjectManagerHandler(Handler nextHandler) {
        super(nextHandler);
    }
    @Override
    public String requestToFee(String user, double fee) {
        if (fee<500){
            return "项目经理同意给你报销:"+fee;
        }
        else{
            if (nextHandler != null) {
                return nextHandler.requestToFee(user, fee);
            }
        }
        return "非法处理";
    }
}
