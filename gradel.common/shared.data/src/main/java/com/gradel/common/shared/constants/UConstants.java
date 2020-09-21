package com.gradel.common.shared.constants;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 应用常量
 */
public interface UConstants {
    /**
     * UTF-8编码
     */
    String UTF8 = "UTF-8";

    /**
     * UTF-8编码字符集
     */
    Charset UTF8_CHARSET = Charset.forName(UTF8);

    /**
     * 空的byte数组
     */
    byte[] EMPTY_BYTES = new byte[0];

    /**
     * 空List集合
     */
    List EMPTY_LIST = Collections.EMPTY_LIST;

    /**
     * 空Map集合
     */
    Map EMPTY_MAP = Collections.EMPTY_MAP;

    /**
     * 空Set集合
     */
    Set EMPTY_SET = Collections.EMPTY_SET;

    /**
     * 空 object
     */
    Object EMPTY_OBJECT = new Object();

    //空string 数组
    String[] EMPTY_STRING_ARRAY = new String[0];
}
