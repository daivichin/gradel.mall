package com.gradel.service.user.domain.service.impl.rocketmq;

import com.alibaba.fastjson.JSON;
import com.gradel.parent.common.rocketmq.annotation.MQTransactionProducer;
import com.gradel.parent.common.rocketmq.base.AbstractMQTransactionProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;

/**
 * @description: 测试事务发送类
 * @author: sdeven.chen.dongwei@gmail.com
 **/
@Slf4j
@MQTransactionProducer(producerGroup = "gradel_user",topic = "Test",tag = "Hello")
public class RocketMQExamplesListener extends AbstractMQTransactionProducer {
	@Override
	public LocalTransactionState getLocalTransaction(Object o) {
		log.info("获取本地事务状态");
		log.info(JSON.toJSONString(o));

		//return LocalTransactionState.UNKNOW;

		return LocalTransactionState.COMMIT_MESSAGE;
	}

	@Override
	public LocalTransactionState checkLocalTransaction(Object o) {
		log.info("回查本地事务状态");
		log.info(JSON.toJSONString(o));
		//return LocalTransactionState.UNKNOW;
		return LocalTransactionState.COMMIT_MESSAGE;
	}
}
