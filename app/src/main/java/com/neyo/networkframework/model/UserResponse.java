package com.neyo.networkframework.model;

import com.neyo.networkframework.BaseResponse;

/**
 * Created by Neyo on 2017/8/25.
 */

public class UserResponse extends BaseResponse<User> {

    @Override public User getData() {
        return Data != null ? Data : new User();
    }

    @Override public boolean isErrorResponse() {
        return super.isErrorResponse() || getData() == null;
    }

    @Override public boolean isEmpty() {
        return super.isEmpty() || getData() == null;
    }
}
