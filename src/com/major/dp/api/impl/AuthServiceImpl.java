package com.major.dp.api.impl;

import com.major.dp.service.AbortableFuture;
import com.major.dp.api.AuthService;
import com.major.dp.LoginInfo;
import com.major.dp.service.RequestCallback;

public class AuthServiceImpl implements AuthService {

    @Override
    public AbortableFuture<String> login(LoginInfo var1) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ("111".equals(var1.getUsrname())) {
            return new AbortableFuture<String>() {
                @Override
                public void setCallback(RequestCallback<String> var1) {
                    var1.onSuccess("登录成功");
                }

                @Override
                public boolean abort() {
                    return false;
                }
            };
        } else {
            return new AbortableFuture<String>() {
                @Override
                public void setCallback(RequestCallback<String> var1) {
                    var1.onFailed(404);
                }

                @Override
                public boolean abort() {
                    return false;
                }
            };
        }




    }

    @Override
    public void logout() {

    }
}
