package com.neyo.networkframework.network;

/**
 * Created by Neyo on 2017/8/24.
 */

public interface RequestListener<T> {

    void onSuccess(T t);

    void onError(String code, String errorMsg);
}
