package com.gradel.common.shared.enums;


import com.gradel.parent.common.util.api.enums.BaseEnum;

/**
 * 平台协议类型
 * @author pichengchao
 *
 */
public enum PlatformProtoclTypeEnum implements BaseEnum<Integer> {

    RULE(1, "规则"),
    PROTOCOL(2, "协议"),

    ;
    private Integer code;
    private String msg;
    PlatformProtoclTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return msg;
    }
}
