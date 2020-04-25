package org.lcy.pattern.proxy.custom;

import org.lcy.pattern.proxy.Persion;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CustomMeipo implements GPInvocationHander{

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆，我要给你找对象，现在已经拿到你的需求了");
        System.out.println("开始物色");
        method.invoke(this.target,args);
        System.out.println("开始约会");
        return null;
    }


    //被代理的对象
    private Persion target;

    public Object getIntance(Persion target) throws Exception {
        this.target = target;

        Class<?> clazz = target.getClass();


        //用来生成一个新的对象(字节码重组实现)
        return GPProxy.newProxyInstance(new GPClassLoader(),clazz.getInterfaces(),this);
    }

}




