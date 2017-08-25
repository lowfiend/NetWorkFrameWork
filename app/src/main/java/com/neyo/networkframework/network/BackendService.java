package com.neyo.networkframework.network;

import android.content.Context;
import com.google.gson.stream.MalformedJsonException;
import com.neyo.networkframework.R;
import com.neyo.networkframework.common.ApiException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import static com.neyo.networkframework.common.Utility.showCommonDialog;

/**
 * Created by Neyo on 2017/8/24.
 */

public class BackendService<T> implements Observer<T> {
    private RequestListener mOnResultListener;
    private Disposable mDisposable;
    private Context context;

    public BackendService(Context context, RequestListener listener) {
        this.mOnResultListener = listener;
        this.context = context;
    }

    @Override public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override public void onNext(T t) {
        if (mOnResultListener != null) {
            mOnResultListener.onSuccess(t);
        }
    }

    @Override public void onError(Throwable e) {
        if (e instanceof CompositeException) {
            CompositeException compositeE = (CompositeException) e;
            String code = "";
            String msg = "";
            for (Throwable throwable : compositeE.getExceptions()) {
                if (throwable instanceof SocketTimeoutException) {
                    code = ApiException.Code_TimeOut;
                    msg = ApiException.SOCKET_TIMEOUT_EXCEPTION;
                } else if (throwable instanceof ConnectException) {
                    code = ApiException.Code_UnConnected;
                    msg = ApiException.CONNECT_EXCEPTION;
                } else if (throwable instanceof UnknownHostException) {
                    code = ApiException.Code_UnConnected;
                    msg = ApiException.CONNECT_EXCEPTION;
                } else if (throwable instanceof MalformedJsonException) {
                    code = ApiException.Code_MalformedJson;
                    msg = ApiException.MALFORMED_JSON_EXCEPTION;
                }

                if (!code.equals("")) {
                    showCommonDialog(context, context.getString(R.string.app_name), msg);
                }

            }
        } else {
            String msg = e.getMessage();
            mOnResultListener.onError(ApiException.Code_Default, msg);
        }
    }

    @Override public void onComplete() {
        unSubscribe();
    }

    public void unSubscribe() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}