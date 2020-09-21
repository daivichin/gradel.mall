package com.gradel.service.user.domain.service.impl.rocketmq;

import com.gradel.service.user.domain.service.rocketmq.RocketMQProducerService;
import com.gradel.parent.common.rocketmq.base.DelayTimeLevel;
import com.gradel.parent.common.rocketmq.config.MQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service 常规服务提供者示例
 */
@Service
public class RocketMQProducerServiceImpl implements RocketMQProducerService {

    @Autowired
    MQProducer producer;

    @Override
    public void sendMessageMQ() {
        try {
            //事务消息
            producer.sendMessageInTransaction("Test", "Hello", "Hello World", System.currentTimeMillis() + "");

            /*//发送异步回调消息
            producer.sendAsync("Test", "Hello", "Hello World", System.currentTimeMillis() + "", new SendCallback() {
                        @Override
                        public void onSuccess(SendResult var1) {
                            System.out.printf("异步发送成功", var1);
                        }

                        @Override
                        public void onException(Throwable var1) {
                            System.out.printf("异步发送失败", var1);
                        }
                    }

            );
            //发送异步延时消息并回调
            producer.sendAsync("Test", "Hello", "Hello World", System.currentTimeMillis() + "", new SendCallback() {
                        @Override
                        public void onSuccess(SendResult var1) {
                            System.out.printf("异步发送成功", var1);
                        }

                        @Override
                        public void onException(Throwable var1) {
                            System.out.printf("异步发送失败", var1);
                        }
                    },DelayTimeLevel.SECOND_10
            );*/

            //顺序消息
            producer.sendOnewayOrderByKey("Test", "Hello", "Hello World", System.currentTimeMillis() + "", new Object(){});

            //普通消息
            producer.sendSync("Test", "Hello", "Hello World333", System.currentTimeMillis() + "");

            //延时消息，延时10秒
            producer.sendSync("Test", "Hello", "Hello World444", System.currentTimeMillis() + "", DelayTimeLevel.SECOND_10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


