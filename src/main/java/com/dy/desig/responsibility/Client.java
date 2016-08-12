package com.dy.desig.responsibility;

/**
 * @author dy
 * @since 2016-08-12  & JDK 1.8.0_91
 */
public class Client {


    public static void main(String[] args) {
        /**
         * 组装职责链
         */
        Handler generalHandler = new GeneralManagerHandler(null);
        Handler depHandler = new DptManagerHandler(generalHandler);
        Handler prjHandler = new ProjectManagerHandler(depHandler);


        System.out.println(prjHandler.requestToFee("", 300));
        System.out.println(prjHandler.requestToFee("", 600));
        System.out.println(prjHandler.requestToFee("", 1200));
    }
}
