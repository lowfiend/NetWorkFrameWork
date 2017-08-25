package com.neyo.networkframework;

import com.neyo.networkframework.model.ResponseStatus;

/**
 * Created by Neyo on 2017/8/24.
 */

public class BaseResponse<T> {
    private Integer Success;  //是否成功，0：失敗； 1：成功
    private ResponseStatus Status;  //錯誤資訊
    public T Data;

    public Integer getSuccess() {
        return Success;
    }

    public ResponseStatus getStatus() {
        return Status;
    }

    public String getStatusMessage() {
        return Status != null ? Status.getMessage() : "";
    }

    public String getStatusCode() {
        return Status != null ? Status.getStatusCode() : "";
    }

    public boolean isErrorResponse() {
        return Data == null || getData() == null;
    }

    public Object getData() {
        return null;
    }

    public boolean isEmpty() {
        return isErrorResponse();
    }
}
