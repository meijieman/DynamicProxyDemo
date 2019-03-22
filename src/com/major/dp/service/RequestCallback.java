package com.major.dp.service;

public interface RequestCallback<T> {

    void onSuccess(T data);

    void onFailed(int fail);

    void onException(Throwable th);
}