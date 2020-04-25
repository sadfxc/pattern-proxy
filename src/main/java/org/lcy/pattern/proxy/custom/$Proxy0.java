package org.lcy.pattern.proxy.custom;

import org.lcy.pattern.proxy.Persion;

import java.lang.reflect.Method;

public class $Proxy0 implements Persion {
    GPInvocationHander h;

    public $Proxy0(GPInvocationHander h) {
        this.h = h;
    }

    public void findLove() {
        try {
            Method m = Persion.class.getMethod("findLove", new Class[]{});
            this.h.invoke(this, m, null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
