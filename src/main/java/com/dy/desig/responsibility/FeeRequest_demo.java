package com.dy.desig.responsibility;

/**
 * 处理聚餐费用的对象
 * @author dy
 * @since 2016-08-12  & JDK 1.8.0_91
 */
public class FeeRequest_demo {


    public String requestToProjectManager(String user,double fee){
        String result = "";
        if (fee<500)
            result = this.projectManagerHandler(user, fee);
        else if (fee< 1000)
            result = this.dptManagerHandler(user, fee);
        else if (fee>1000)
            result = this.generalManagerHandler(user, fee);
        return result;
    }

    private  String projectManagerHandler(String user,double fee){
        return "项目经理同意给你报销:"+fee;
    }

    private  String dptManagerHandler(String user,double fee){
        return "部门经理同意给你报销:"+fee;
    }

    private  String generalManagerHandler(String user,double fee){
        return "总经理同意给你报销:"+fee;
    }


    public static void main(String[] args) {
        FeeRequest_demo feeRequest =new FeeRequest_demo();
        System.out.println(feeRequest.requestToProjectManager("", 300));
        System.out.println(feeRequest.requestToProjectManager("", 600));
        System.out.println(feeRequest.requestToProjectManager("", 1200));

    }
}
