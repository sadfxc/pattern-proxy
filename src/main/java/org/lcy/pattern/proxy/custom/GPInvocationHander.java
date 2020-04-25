package org.lcy.pattern.proxy.custom;

import java.lang.reflect.Method;

public interface GPInvocationHander {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable ;
}
