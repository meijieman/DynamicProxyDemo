package com.major.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class Main {

    public static void main(String[] args) {
        // 获取 class 文件字节码，可以将该字节码保存到文件
//        byte[] proxyClassFile = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Interface.class});
//        saveToFile(proxyClassFile);

        Main main = new Main();
//        main.method();
        main.method2();
    }

    private void method() {
        Interface anInterface = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[]{Interface.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("method 干点什么 " + method.getName());
                        return null;
                    }
                });
        System.out.println("anInterface " + anInterface.getWord());
//        anInterface.doSomething();
//        System.out.println("anInterface 2 " + anInterface);
    }


    private void method2() {
        Interface anInterface = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[]{Interface.class}, new IH(new InterfaceImpl()));

        System.out.println("anInterface " + anInterface);
//        System.out.println("anInterface " + anInterface.getWord());
//        anInterface.doSomething();
//        System.out.println("anInterface 2 " + anInterface);
    }

    static class IH implements InvocationHandler {

        Object proxy;

        public IH(Object proxy) {
            this.proxy = proxy;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("干点什么 " + method.getName());
            Object invoke = method.invoke(this.proxy, args);
            System.out.println("收拾 " + invoke);
            return invoke;
//            return null;
        }
    }

    static class InterfaceImpl implements Interface {

        @Override
        public void doSomething() {
            System.out.println("说干就干~");
        }

        @Override
        public String getWord() {
            System.out.println("调用了getWord");
            return "没有新消息";
        }
    }

    private static void saveToFile(byte[] proxyClassFile) {
        File file = new File("./proxy.class");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(proxyClassFile);
            fos.flush();
            fos.close();
            System.out.println("saved...");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
