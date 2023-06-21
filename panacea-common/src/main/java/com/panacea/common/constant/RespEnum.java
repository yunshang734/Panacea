package com.panacea.common.constant;

public enum RespEnum {

    SUCCESS(200, true, "Success"),
    PARAM_ERROR(400, false, "Parameter Error"),
    RECV_MSG_SIGN_FAIL(401, false, "Need Authentication"),
    FORBIDDEN(403, false, "Forbidden"),
    NOT_FOUND(404, false, "Not Found"),
    NOT_ACCEPTABLE(406, false, "Not Acceptable"),
    SERVER_ERROR(500, false, "Server Error"),
    FAIL(503, false, "Service Unavaliable");

    private Integer code;
    private Boolean success;
    private String message;

    RespEnum(Integer code, Boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}