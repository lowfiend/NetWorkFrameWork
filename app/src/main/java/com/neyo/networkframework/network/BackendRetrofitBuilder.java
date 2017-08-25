package com.neyo.networkframework.network;

import com.neyo.networkframework.common.GlobalConstant;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Neyo on 2017/8/24.
 */

public class BackendRetrofitBuilder {

    private static final int DEFAULT_TIMEOUT = 5;

    protected Retrofit.Builder createRetrofitBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).retryOnConnectionFailure(true);

        OkHttpClient okHttpClient = builder.build();

        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(GlobalConstant.BASE_URL)
                .client(okHttpClient);
    }
}
