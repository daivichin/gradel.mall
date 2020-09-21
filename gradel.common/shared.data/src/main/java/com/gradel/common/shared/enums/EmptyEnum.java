package com.gradel.common.shared.enums;

import com.gradel.parent.common.util.api.enums.BaseEnum;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * User: sdeven.chen.dongwei@gmail.com
 * Date: 2017/1/5
 * Description:
 */
public enum EmptyEnum implements BaseEnum<Integer> {

    /**
     * 0、不显示；
     */
    IS_SHOW_0(0, "不显示"),
    /**
     * 1、显示；
     */
    IS_SHOW_1(1, "显示");


    @Getter
    private Integer code;
    @Getter
    private String desc;

    EmptyEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}