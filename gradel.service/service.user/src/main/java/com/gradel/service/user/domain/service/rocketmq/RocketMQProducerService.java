package com.gradel.service.user.domain.service.rocketmq;
/**
 * service 常规服务提供者示例
 */
public interface RocketMQProducerService {

    /**
     * 发送RocketMQ消息
     * @return
     */
    void sendMessageMQ();
}
