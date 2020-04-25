package org.lcy.pattern.proxy.custom;

import org.lcy.pattern.proxy.Persion;
import org.lcy.pattern.proxy.jdk.JDKMeipo;
import org.lcy.pattern.proxy.jdk.XieMu;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class CustomProxyTest {

    public static void main(String[] args) {
        try {
            Persion obj = (Persion)new CustomMeipo().getIntance(new XieMu());
            obj.findLove();
            System.out.println(obj.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
