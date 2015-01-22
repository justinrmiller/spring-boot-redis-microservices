package com.justinrmiller.redismicroservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Justin Miller (Copyright 2015)
 *
 * This relies on a milestone release of Spring Data
 * see:
 * https://github.com/spring-projects/spring-data-redis/pull/116
 */
@Component
public class HyperLogLogService {
    @Autowired private StringRedisTemplate redisTemplate;

    public Long count(String ... key) {
        return redisTemplate.opsForHyperLogLog().size(key);
    }

    public void add(String key, String ... values) {
        redisTemplate.opsForHyperLogLog().add(key, values);
    }
}
