package com.major.dp.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ServiceInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO 代理类可以干事情了
        System.out.println("代理类 " + proxy.getClass() + ", method:" + method + ", args: " + Arrays.toString(args));

        ServiceMethodContainer methodContainer = new ServiceMethodContainer(method, args);
        return ServiceClient.execute(methodContainer);
    }
}



