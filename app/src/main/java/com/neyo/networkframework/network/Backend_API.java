package com.neyo.networkframework.network;

import com.neyo.networkframework.BaseResponse;
import com.neyo.networkframework.common.ApiException;
import com.neyo.networkframework.common.GlobalConstant;
import com.neyo.networkframework.model.User;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Neyo on 2017/8/24.
 */

public class Backend_API extends BackendRetrofitBuilder {

    private BackendQuery backendQuery;
    private volatile static Backend_API instance;

    private Backend_API() {
        backendQuery = createRetrofitBuilder().build().create(BackendQuery.class);
    }

    public static Backend_API getInstance() {
        if (instance == null) {
            synchronized (Backend_API.class) {
                if (instance == null) {
                    instance = new Backend_API();
                }
            }
        }
        return instance;
    }

    private <T> void toSubscribe(Observable<BaseResponse<T>> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io()).map(new Function<BaseResponse<T>, T>() {
            @Override public T apply(@NonNull BaseResponse<T> response) throws Exception {
                int code = response.getSuccess();
                if (code != GlobalConstant.SUCCESS_CODE) {
                    throw new ApiException(response.getStatus().getStatusCode(),
                            response.getStatus().getMessage());
                } else {
                    return response.Data;
                }
            }
        }).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s);
    }

    public void getUser(Observer<User> subscriber, String username) {
        toSubscribe(backendQuery.getUser(username), subscriber);
    }

    public void getEamil(Observer<User> subscriber, String username) {
        toSubscribe(backendQuery.getUser(username), subscriber);
    }
}
