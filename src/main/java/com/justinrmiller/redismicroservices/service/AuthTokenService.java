package com.justinrmiller.redismicroservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.common.base.Optional;

import com.justinrmiller.redismicroservices.pojo.TokenPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Justin Miller (Copyright 2015)
 */
@Component
public class AuthTokenService {
    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private StringRedisTemplate redisTemplate;

    public Optional<TokenPermissions> get(String token) throws IOException {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

        String permissionsAsJson = hashOps.get(token, "permissions");

        if (permissionsAsJson != null) {
            return Optional.of(MAPPER.readValue(permissionsAsJson, TokenPermissions.class));
        } else {
            return Optional.absent();
        }
    }

    public void add(String token, TokenPermissions permissions) throws IOException {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

        String permissionsAsJson = MAPPER.writeValueAsString(permissions);

        hashOps.put(token, "permissions", permissionsAsJson);
    }
}
