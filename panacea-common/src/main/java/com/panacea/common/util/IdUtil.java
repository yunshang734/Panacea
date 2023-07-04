package com.panacea.common.util;

import com.panacea.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class IdUtil {

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public static String simpleUUID() {
        return UUID.randomUUID().toString().replaceAll("-", CommonConstant.EMPTY_STRING);
    }
}