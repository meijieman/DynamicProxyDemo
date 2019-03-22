package com.major.dp.service;

import com.major.dp.api.Service;
import com.major.dp.api.impl.AuthServiceImpl;
import com.major.dp.api.impl.UserServiceImpl;
import com.major.dp.api.AuthService;
import com.major.dp.api.UserService;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ServiceMethodExecuter {

    private final Map<String, A> sServiceMap = new HashMap<>(); // interfaceName - A

    ServiceMethodExecuter() {
        System.out.println("Register Service Start");

        //TODO 使用注解来关联
        addService(AuthService.class, AuthServiceImpl.class);
//        addService(UserService.class, UserServiceImpl.class);

        System.out.println("Register Service End");
    }

    public final Object invoke(ServiceMethodContainer container) {
        ServiceMethodExecuter.A a;
        if ((a = sServiceMap.get(container.getMethodDeclaringClassName())) == null) {
            System.out.println("调用不存在 " + container.getMethod().getDeclaringClass());
            System.out.println("调用不存在 " + container.getMethodDeclaringClassName());
            return null;
        } else {
            try {
                Object object = a.invoke(container);
                System.out.println("使用代理 " + object);
                return object;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void addService(Class<?> interfaceCls, Class<? extends Service> implCls) {
        sServiceMap.put(interfaceCls.getSimpleName(), new ServiceMethodExecuter.A(interfaceCls, implCls));
    }

    // 保存接口与实现类之间的关联关系
    private static class A {
        private final Map<String, Method> mMethodMap = new HashMap<>(); // methodName - Method
        private Service mRealService;

        public A (Class<?> interfaceCls, Class<? extends Service> implCls) {
            Method[] methods;
            int length = (methods = interfaceCls.getDeclaredMethods()).length;
            for (int i = 0; i < length; i++) {
                Method method = methods[i];
                mMethodMap.put(method.getName(), method);
            }
            try {
                mRealService = implCls.newInstance();
                System.out.println("创建实现类 " + mRealService);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        public final Object invoke(ServiceMethodContainer container) throws Exception {
            System.out.println("原始调用地方 mRealService " + mRealService);
            return mMethodMap.get(container.getMethodName()).invoke(mRealService, container.getArgs());
        }
    }
}

