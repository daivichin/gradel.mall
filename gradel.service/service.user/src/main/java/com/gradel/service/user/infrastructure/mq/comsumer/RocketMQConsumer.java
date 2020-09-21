package com.gradel.service.user.infrastructure.mq.comsumer;


import com.gradel.parent.common.rocketmq.annotation.MQConsumer;
import com.gradel.parent.common.rocketmq.base.AbstractMQPushConsumer;

import java.util.Map;


/**
 * 消费者测试类
 * @author sdeven.chen.dongwei@gmail.com
 */
@MQConsumer(consumerGroup = "gradel_user", topic = "Test", tag = "Hello")
public class RocketMQConsumer extends AbstractMQPushConsumer<String> {

	@Override
	public boolean process(String message, Map<String, Object> extMap) {
		System.out.println(message);
		return true;
	}
}
