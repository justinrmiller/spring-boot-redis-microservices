package com.justinrmiller.redismicroservices.controllers;

import com.google.common.base.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import com.justinrmiller.redismicroservices.pojo.TokenPermissions;
import com.justinrmiller.redismicroservices.service.AuthTokenService;

/**
 * @author Justin Miller (Copyright 2015)
 */
public class AuthTokenController {
    private static Logger logger = LoggerFactory.getLogger(AuthTokenController.class);

    @Autowired
    private AuthTokenService authTokenService;

    @RequestMapping(
            value = "/auth/token/{token}",
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity get(@PathVariable String token) throws IOException {
        logger.info("Getting a token");

        Optional<TokenPermissions> tokenPermissionsOptional = authTokenService.get(token);

        if (tokenPermissionsOptional.isPresent()) {
            return new ResponseEntity<>(tokenPermissionsOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/auth/token/{token}",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity post(
            @PathVariable String token,
            @RequestBody TokenPermissions tokenData) throws IOException {
        logger.info("Token {}", tokenData);

        authTokenService.add(token, tokenData);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
