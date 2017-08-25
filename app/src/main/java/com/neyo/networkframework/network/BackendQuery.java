package com.neyo.networkframework.network;

import com.neyo.networkframework.model.BaseResponse;
import com.neyo.networkframework.model.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Neyo on 2017/8/24.
 */

public interface BackendQuery {

    @GET("/user/getUser.php") Observable<BaseResponse<User>> getUser(
            @Query("username") String username);
}
