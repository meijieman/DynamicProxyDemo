package com.major.dp.service;

public interface AbortableFuture<T> extends InvocationFuture<T> {

    boolean abort();
}
