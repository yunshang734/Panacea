package com.panacea.common.dto;

import com.panacea.common.constant.RespEnum;
import com.panacea.common.meta.BaseMeta;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BOResult<T extends BaseMeta> {

    private Boolean success;
    private Integer code;
    private T data;
    private String message;

    public BOResult(RespEnum respEnum) {
        this.success = respEnum.getSuccess();
        this.code = respEnum.getCode();
        this.message = respEnum.getMessage();
    }

    public BOResult(RespEnum respEnum, T data) {
        this.success = respEnum.getSuccess();
        this.code = respEnum.getCode();
        this.message = respEnum.getMessage();
        this.data = data;
    }

    public BOResult(RespEnum respEnum, String message) {
        this.success = respEnum.getSuccess();
        this.code = respEnum.getCode();
        this.message = message;
    }
}