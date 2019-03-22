package com.major.dp.service;

import com.major.dp.api.Service;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ServiceCache {

    private final Map<Class<? extends Service>, Service> mCaches = new HashMap<>();

    public ServiceCache() {
    }

    public final <T extends Service> T getService(Class<T> cls) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException("only accept interface: " + cls);
        } else {
            synchronized (mCaches) {
                T hitProxy;
                if ((hitProxy = (T) mCaches.get(cls)) == null) {
                    System.out.println("创建代理类 " + cls.getName());
                    hitProxy = (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new ServiceInvocationHandler());
                    mCaches.put(cls, hitProxy);
                }
                return hitProxy;
            }
        }
    }
}
