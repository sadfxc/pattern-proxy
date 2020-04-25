package org.lcy.pattern.proxy.staticed;

public class Father {

    Son son;

    //没办法扩张
    public Father(Son persion){
        this.son = persion;
    }

    //目标对象的引用给拿到
    public void findLove() {
        System.out.println("根据你的要求物色");
        this.son.findLove();
        System.out.println("双方父母是否同意");


    }


}
