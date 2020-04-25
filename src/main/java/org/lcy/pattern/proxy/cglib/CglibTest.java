package org.lcy.pattern.proxy.cglib;

public class CglibTest {

    public static void main(String[] args) throws Exception {
        ZhangSan obj = (ZhangSan)new CglibMeipo().getInstance(ZhangSan.class);
        obj.findLove();
        System.out.println("----------");
        System.out.println(obj.getClass());

    }
}
