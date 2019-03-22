package com.major.dp.service;

import com.major.dp.api.Service;

public class ServiceClient {

    private static ServiceCache sServiceCache = new ServiceCache();
    private static ServiceMethodExecuter sExecute = new ServiceMethodExecuter();

    public static <T extends Service> T getService(Class<T> cls) {
        T service = sServiceCache.getService(cls);
        System.out.println("ServiceClient service " + service);
        return service;
    }

    public static Object execute(ServiceMethodContainer container) {
        return sExecute.invoke(container);
    }
}
