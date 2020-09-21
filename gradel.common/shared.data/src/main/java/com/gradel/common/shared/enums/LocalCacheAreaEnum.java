package com.gradel.common.shared.enums;

import lombok.Getter;


/**
 * Description: JetCacheLocalAreaConfigEnum
 *
 * @Author sdeven
 * @Date 2020/02/20
 */
@Getter
public enum LocalCacheAreaEnum implements CodeNameEnum {
    DEFAULT(0, "default");

    Integer code;
    String name;

    LocalCacheAreaEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static LocalCacheAreaEnum getArea(int value) {
        for (LocalCacheAreaEnum area : values()) {
            if (area.getCode().equals(value)) {
                return area;
            }
        }
        return null;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}