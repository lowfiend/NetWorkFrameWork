package com.neyo.networkframework.model;

/**
 * Created by Neyo on 2017/8/25.
 */

public class ResponseStatus {
    private String StatusCode;  //錯誤碼
    private String Message;  //錯誤描述

    public String getStatusCode() {
        return StatusCode != null ? StatusCode : "";
    }

    public String getMessage() {
        return Message != null ? Message : "";
    }
}
