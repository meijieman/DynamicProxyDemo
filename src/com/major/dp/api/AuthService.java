package com.major.dp.api;

import com.major.dp.service.AbortableFuture;
import com.major.dp.LoginInfo;
import com.sun.istack.internal.NotNull;

public interface AuthService extends Service {

    @NotNull
    AbortableFuture<String> login(LoginInfo var1);

    void logout();
}
