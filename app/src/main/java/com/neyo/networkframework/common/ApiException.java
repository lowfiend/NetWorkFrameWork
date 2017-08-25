package com.neyo.networkframework.common;

public class ApiException extends RuntimeException {
    public static final String Code_TimeOut = "1000";
    public static final String Code_UnConnected = "1001";
    public static final String Code_MalformedJson = "1020";
    public static final String Code_Default = "1003";
    public static final String CONNECT_EXCEPTION = "網路連線異常，請檢查您的網路狀態";
    public static final String SOCKET_TIMEOUT_EXCEPTION = "網路連結超時，請檢查您的網路狀態，稍後重試";
    public static final String MALFORMED_JSON_EXCEPTION = "數據解析錯誤";

    public ApiException(String resultCode, String msg) {
        this(getApiExceptionMessage(resultCode, msg));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(String code, String msg) {
        String message;
        switch (code) {
            default:
                message = code + "#" + msg;
        }
        return message;
    }
}

