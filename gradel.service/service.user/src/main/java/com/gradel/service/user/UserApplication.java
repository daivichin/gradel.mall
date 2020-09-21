package com.gradel.service.user;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.gradel.parent.common.util.util.FastJsonConfigBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.Serializable;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCreateCacheAnnotation
@EnableTransactionManagement
@MapperScan("com.gradel.gradel.service.user.query.mapper")
@EnableMethodCache(basePackages = "ccom.gradel.gradel.service.user.application.cmd")
public class UserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(UserApplication.class, args);
        ctx.start();
    }

    @Configuration
    public class RedisConfig {
        @Bean
        public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
            RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
            redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
            redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
            redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
            redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
            redisTemplate.setConnectionFactory(connectionFactory);
            return redisTemplate;
        }
    }

    @Bean
    public FastJsonConfigBean getFastJsonConfig() {
        FastJsonConfigBean fastJsonConfig = new FastJsonConfigBean();
        fastJsonConfig.setEnableDefault(true);
        fastJsonConfig.setEnableJsonUtil(true);
        return fastJsonConfig;
    }


}
