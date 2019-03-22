package com.major.dp;

import com.major.dp.api.AuthService;
import com.major.dp.service.AbortableFuture;
import com.major.dp.service.RequestCallback;
import com.major.dp.service.ServiceClient;
import com.sun.javafx.runtime.SystemProperties;

/*
如何将你的服务优雅的暴露出去
https://juejin.im/post/5c482f0ae51d4567680e429a
 */
public class Main {

    public static void main(String[] args) {

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsrname("111");
        AuthService authService = ServiceClient.getService(AuthService.class);
        AbortableFuture<String> login = authService.login(loginInfo);
//        System.out.println("authService222 " + authService);
//
//        login.setCallback(new RequestCallback<String>() {
//            @Override
//            public void onSuccess(String data) {
//                System.out.println("登录成功");
//            }
//
//            @Override
//            public void onFailed(int fail) {
//                System.out.println("登录失败");
//            }
//
//            @Override
//            public void onException(Throwable th) {
//                System.out.println("onException");
//            }
//        });

    }

}
