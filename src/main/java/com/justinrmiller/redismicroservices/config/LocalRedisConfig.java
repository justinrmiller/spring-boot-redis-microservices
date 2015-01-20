package com.justinrmiller.redismicroservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Justin Miller (Copyright 2015)
 */

@Configuration
@ComponentScan(basePackages="com.justinrmiller.redismicroservices.service")
public class LocalRedisConfig {
    @Bean
    public RedisConnectionFactory jedisConnectionFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.maxActive = 100;
        poolConfig.maxIdle = 50;
        poolConfig.minIdle = 50;
        poolConfig.testOnBorrow = true;
        poolConfig.testOnReturn = true;
        poolConfig.testWhileIdle = true;
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        return jedisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(jedisConnectionFactory());
        return redisTemplate;
    }
}