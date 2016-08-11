package com.dy.desig.decorator;

/**
 * @author dy
 * @since 2016-08-11  & JDK 1.8.0_91
 */
public class Client {
    public static void main(String[] args) {
        Prize prize = new Prize();
        prize.calcPrize(2);
    }
}
