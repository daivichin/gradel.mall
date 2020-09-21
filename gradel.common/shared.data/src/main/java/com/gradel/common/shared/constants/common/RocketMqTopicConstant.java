package com.gradel.common.shared.constants.common;

public class RocketMqTopicConstant {


    public static final class Order {

    }

    public static final class User {
        /**
         *  测试事务消息
         */
        public static final String TEST_TRANSACTIONAL = "test_transactional";
        /**
         *  测试普通消息
         */
        public static final String TEST_MESSAGE = "test_message";

        /**
         *  测试延时消息
         */
        public static final String TEST_MESSAGE_DELAY = "test_message_delay";

        /**
         *
         */
        public static final class UserTag {


        }
    }

}
