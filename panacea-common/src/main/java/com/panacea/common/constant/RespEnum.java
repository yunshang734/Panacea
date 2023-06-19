package com.panacea.common.constant;

public enum RespEnum {

    SUCCESS(200, true, "成功"),
    PARAM_ERROR(400, false, "参数错误"),
    RECV_MSG_SIGN_FAIL(401, false, "验签错误"),
    NOT_FOUND(404, false, "未找到资源"),
    SERVER_ERROR(500, false, "服务器错误"),
    FAIL(503, false, "错误");

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