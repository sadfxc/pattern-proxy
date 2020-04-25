package org.lcy.pattern.proxy.staticed;

public class StaticProxyTest {


    public static void main(String[] args) {
        // 只能帮儿子找对象
        Father father = new Father(new Son());
        father.findLove();

    }


}
