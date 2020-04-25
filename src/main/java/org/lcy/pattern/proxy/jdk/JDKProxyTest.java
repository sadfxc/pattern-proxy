package org.lcy.pattern.proxy.jdk;

import org.lcy.pattern.proxy.Persion;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class JDKProxyTest {

    public static void main(String[] args) {
        try {
            Persion obj = (Persion)new JDKMeipo().getIntance(new XieMu());
            obj.findLove();
            System.out.println(obj.getClass());

            //原理：
            //1、拿到被代理对象的引用，并且获取到它所有的接口（反射获取）
            //2、JDK Proxy类重新生成一个新的类，同时新的类要实现被代理类所有实现的接口
            //3、动态生成JAVA代码，把新加的业务逻辑方法由一定的逻辑代码上调用(在代码上提现)
            //4、编译新生成的java代码.class
            //5、在重新加载到JVM中运行
            //以上这个过程就叫字节码重组

            //JDK中又规范，只要是$开头的一般都是自动生成的

            //通过反编译工具可以查看源代码
            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Persion.class});
            FileOutputStream os = new FileOutputStream("F://$Proxy0.class");
            os.write(bytes);
            os.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
