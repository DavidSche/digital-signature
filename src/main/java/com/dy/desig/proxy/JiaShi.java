package com.dy.desig.nattern;

/**
 * 那我们再考虑一下，水浒里面还有没有这类型的女人？
 * 有，卢俊义的老婆贾氏（就是和那个管家苟合的那个），这个名字起的：“贾氏”，那我们也让王婆做她的代理：
 */
public class JiaShi implements KindWoman{
    @Override
    public void happyWithMan() {
        System.out.println("贾氏和男人在做那个...");

    }

    @Override
    public void makeEyesWithMan() {
        System.out.println("贾氏抛媚眼...");

    }
}
