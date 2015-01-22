package com.justinrmiller.redismicroservices.controllers;

import com.justinrmiller.redismicroservices.service.HyperLogLogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Justin Miller (Copyright 2015)
 */
@RestController
public class HyperLogLogController {
    private static Logger logger = LoggerFactory.getLogger(HyperLogLogController.class);

    @Autowired
    private HyperLogLogService hyperLogLogService;

    @RequestMapping(
            value = "/hyperloglog/key/{key}",
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity get(@PathVariable String key) {
        logger.info("Retrieving HyperLogLog count: {}", key);

        Long count = hyperLogLogService.count(key);

        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/hyperloglog/key/{key}/value/{value}",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity post(@PathVariable String key, @PathVariable String value) {
        logger.info("Key: {}, Value: {}", key, value);

        hyperLogLogService.add(key, value);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
