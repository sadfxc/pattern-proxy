package org.lcy.pattern.proxy.custom;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GPProxy {

    public static String ln = "\r\n";

    public static Object newProxyInstance(GPClassLoader classLoader,Class<?>[] interfaces,GPInvocationHander h) throws Exception {
        //1、动态生成源代码.java文件
        String src = generateSrc(interfaces);

        //2、java文件输出磁盘
        String path = GPProxy.class.getResource("").getPath();
        System.out.println(path);
        File f = new File(path + "$Proxy0.java");
        FileWriter fw = new FileWriter(f);
        fw.write(src);
        fw.flush();
        fw.close();
        //3、把生成的.java文件编译成.class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null,null);
        Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(f);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
        task.call();
        manager.close();

        //4、编译生成.class文件加载到JVM中来
        Class<?> proxyClass = classLoader.findClass("$Proxy0");
        Constructor<?> constructor = proxyClass.getConstructor(GPInvocationHander.class);
//        f.delete();

        //5、返回字节码重组以后的新的代理对象
        return constructor.newInstance(h);

    }


    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package org.lcy.pattern.proxy.custom;" + ln);
        sb.append("import org.lcy.pattern.proxy.Persion;" + ln);
        sb.append("import java.lang.reflect.Method;" + ln);
        sb.append("public class $Proxy0 implements "+interfaces[0].getName() +"{" + ln);

            sb.append("GPInvocationHander h;"+ln);
            sb.append("public $Proxy0(GPInvocationHander h) { " + ln);
                sb.append("this.h = h;");
            sb.append("}" + ln);

            for(Method m : interfaces[0].getMethods()) {
                sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "() {" + ln);
                    sb.append("try {" + ln);
                        sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\""+m.getName()+"\",new Class[]{});"+ln);
                        sb.append("this.h.invoke(this,m,null);" + ln);

                    sb.append("}catch(Throwable throwable) {" + ln);
                    sb.append("throwable.printStackTrace();}" + ln);
                sb.append("}");
            }
        sb.append("}" + ln);
        return sb.toString();

    }



}
