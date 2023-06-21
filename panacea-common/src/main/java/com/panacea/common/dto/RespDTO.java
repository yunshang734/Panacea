package com.panacea.common.dto;

import com.panacea.common.constant.RespEnum;
import com.panacea.common.meta.BaseMeta;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RespDTO<T extends BaseMeta> {

    private Integer code;
    private T data;
    private String message;

    public RespDTO(RespEnum respEnum) {
        this.code = respEnum.getCode();
        this.message = respEnum.getMessage();
    }

    public RespDTO(RespEnum respEnum, T data) {
        this.code = respEnum.getCode();
        this.message = respEnum.getMessage();
        this.data = data;
    }

    public RespDTO(BOResult<T> boResult) {
        this.code = boResult.getCode();
        this.message = boResult.getMessage();
        this.data = boResult.getData();
    }

    public RespDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RespDTO(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
}