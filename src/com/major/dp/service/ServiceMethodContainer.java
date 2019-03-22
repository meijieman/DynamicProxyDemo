package com.major.dp.service;

import java.lang.reflect.Method;

public class ServiceMethodContainer {

    private Method method;
    private Object[] args;

    public ServiceMethodContainer(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }

    public String getMethodDeclaringClassName() {
        return method.getDeclaringClass().getSimpleName();
    }

    public String getMethodName() {
        return method.getName();
    }

    public Object[] getArgs(){
        return args;
    }

    public Method getMethod(){
        return method;
    }
}
