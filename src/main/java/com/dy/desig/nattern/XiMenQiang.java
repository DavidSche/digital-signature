package com.dy.desig.nattern;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *水浒传是这样写的：西门庆被潘金莲用竹竿敲了一下，西门庆看痴迷了，被王婆看到了，就开始撮合两人好事，
 * 王婆作为潘金莲的代理人收了不少好处费，
 * 那我们假设一下：如果没有王婆在中间牵线，这两个不要脸的能成事吗？难说得很！
 */
public class XiMenQiang {

    public static void main(String[] args) {
//        //王婆出厂
//        WangPoProxy wangPo =  new WangPoProxy();
//        //潘金莲出场
//        PanJinLian panJinLian  = new PanJinLian();
//        //告王婆代理潘金莲苟且之事
//        KindWoman woman = (KindWoman) wangPo.createProxyTarget(panJinLian);
//
//        //然后西门庆说，我要和潘金莲Happy,然后王婆就安排了西门庆丢筷子哪出戏：
//        woman.makeEyesWithMan();
//        //看到没有表面是王婆在做，其实爽的是潘金莲
//        woman.happyWithMan();
//
//
//        //西门庆勾引贾氏
//        JiaShi jiaShi = new JiaShi();
//        woman = (KindWoman) wangPo.createProxyTarget(jiaShi);
//        woman.makeEyesWithMan();
//        woman.happyWithMan();

        /**
         * cglib动态代理
         */
        WangPoCglibProxy wangPoCglibProxy = new WangPoCglibProxy();

        PanYunQiao panYunQiao = new PanYunQiao();

        PanYunQiao pan = (PanYunQiao) wangPoCglibProxy.getInstance(panYunQiao);

        pan.betrayal();
//        ConcurrentLinkedQueue
    }
}
