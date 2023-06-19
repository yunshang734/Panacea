package com.panacea.common.meta;

import com.alibaba.fastjson.JSON;

public class BaseMeta {

    public String toString() {
        return JSON.toJSONString(this);
    }
}