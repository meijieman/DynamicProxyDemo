package com.major.assist;

import javassist.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
Java动态编程初探——Javassist
https://www.cnblogs.com/duanxz/p/3571217.html
JVM插码之四：Java动态代理机制的对比（JDK 和CGLIB，Javassist，ASM）
https://www.cnblogs.com/duanxz/p/3577682.html
 */
public class Main {

    public static void main(String[] args) {
//        method1();
//        method2();
//        method3();
        method4();

    }


    private static void method4() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(Dream.class.getName());
            CtClass myDream = pool.makeClass("com.demo.MyDream");
//            CtClass myDream = pool.makeClass(new FileInputStream("D:\\tmp\\PeopleDream.java"));
            System.out.println("pkgName " + myDream.getPackageName());

            myDream.addInterface(cc);
            CtMethod cm = CtMethod.make("public void makeMoney(){System.out.println(\"i want make lots of money\");}", myDream);
            myDream.addMethod(cm);

            Class clazz = myDream.toClass();
            Dream dream = (Dream) clazz.newInstance();
            dream.makeMoney();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void method3() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("com.major.assist.Point");
            CtMethod cm = cc.getDeclaredMethod("move");
            cm.insertBefore("{System.out.println($1 + \" \" + $2);}");
            String longName = cm.getLongName();
            System.out.println("longName " + longName);
            cm.insertAt(0, "{System.out.println($1 + $2);}");

//            MethodInfo methodInfo = cm.getMethodInfo();
//            CodeAttribute ca = methodInfo.getCodeAttribute();
//            LineNumberAttribute ainfo = (LineNumberAttribute) ca.getAttribute("LineNumberTable");
//            LineNumberAttribute.Pc pc = ainfo.toNearPc(10);
//            int lineNum = pc.line;
//            System.out.println("lineNum " + lineNum);

//            String s = cc.toString();
//            System.out.println("method3#s " + s);
            Class<?> clazz = cc.toClass();

            Method method = clazz.getDeclaredMethod("move", int.class, int.class);
            method.invoke(clazz.newInstance(), 2, 3);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void method2() {
        // 定义一个新类
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.makeClass("Foo");
            CtMethod cm = CtNewMethod.make("public Integer getInteger(int a) { return new Integer(a + 5); }", cc);
            cc.addMethod(cm);
            CtField f = new CtField(CtClass.intType, "i", cc);
            cc.addField(f);

            Class clazz = cc.toClass();
            Object instance = clazz.newInstance();
            Method method = clazz.getMethod("getInteger", int.class);
            Object invoke = method.invoke(instance, 4);
            System.out.println("invoke " + invoke);

        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private static void method1() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get("com.major.assist.Middle");
            CtMethod method = CtMethod.make("public void print(){System.out.println(\"打印方法\");}", cc);
            cc.addMethod(method);
            cc.setSuperclass(pool.get("com.major.assist.Base")); // 将 Base 设置为 Middle 的父类
            cc.writeFile(); // 写入到文件

            // fixme 保存生成的字节码，没有写入成功？
            cc.writeFile("D:\\tmp");

            byte[] b = cc.toBytecode();
            Class clazz = cc.toClass();

            Object obj = clazz.newInstance();
            System.out.println("obj -> " + obj);
            Method say = clazz.getMethod("say");
            say.invoke(obj);

            Method print = clazz.getMethod("print");
            print.invoke(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
