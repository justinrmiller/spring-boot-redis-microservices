package com.justinrmiller.redismicroservices.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import com.justinrmiller.redismicroservices.pojo.Feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author Justin Miller (Copyright 2015)
 */
@Component
public class FeatureFlagsService {
    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private StringRedisTemplate redisTemplate;

    public Optional<Feature> get(String application, String feature) throws IOException {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

        String featureJson = hashOps.get(application, feature);

        if (featureJson != null) {
            return Optional.of(MAPPER.readValue(featureJson, Feature.class));
        } else {
            return Optional.absent();
        }
    }

    public Optional<ImmutableList<Feature>> getAll(String application) throws IOException {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

        List<String> featureJson = hashOps.values(application);

        if (featureJson != null) {
            ImmutableList.Builder<Feature> builder = new ImmutableList.Builder<>();

            for (String s : featureJson) {
                builder.add(MAPPER.readValue(s, Feature.class));
            }
            return Optional.of(builder.build());
        } else {
            return Optional.absent();
        }
    }

    public void add(String application, Feature feature) throws IOException {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

        String featureAsJson = MAPPER.writeValueAsString(feature);

        hashOps.put(application, feature.getName(), featureAsJson);
    }
}
