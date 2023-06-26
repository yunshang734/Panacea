package com.panacea.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class IdUtil {

    private final static String EMPTY_STRING = "";

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public static String simpleUUID() {
        return UUID.randomUUID().toString().replaceAll("-", EMPTY_STRING);
    }
}