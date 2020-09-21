package com.gradel.common.shared.constants.common;

/**
 * author:sdeven.chen.dongwei@gmail.com
 * Date:2018/10/29
 */
public final class RedisConstant {

    /**
     * 统一 empty 过期时间 防止 雪崩效应
     * 单位：秒
     */
    public static final Integer EMPTY_EXPIRE_TIME = 120;


    /**
     * 统一 懒加载 分布式锁 过期时间
     */
    public static final Integer LOCK_EXPIRE_TIME = 120;


    /**
     * jetcache 区域配置
     */
    public static final class Area{

        /**
         * 默认
         */
        public static final String DEFAULT = "default";
        public static final String DEFAULT_ADDRESS = "default_address";

        /**
         * 用户
         */
        public static final String ZOUBEI = "zoubei";




    }
    /**
     * 定义Redis 中Key
     */
    public static final class Key {

        /**
         * 字典前缀
         */
        public static final String DICTIONARY_PRE= "dictionary:";

        /**
         * 用户前缀
         */
        public static final String ZOUBEI_PRE= "zoubei:";
        public static final String DEFAULT_ADDRESS= "default_address:";

        /**
         * key：微信App
         */
        public static final String WECHARTAPP = "wechartApp:";

        /**
         * key：微信App
         */
        public static final String TOKEN = "token";

        /**
         * key：微信App
         */
        public static final String ACTIONID = "actionid";
    }

    /**
     * 定义Redis 中初始值
     */
    public static final class InitValue {

    }
}
