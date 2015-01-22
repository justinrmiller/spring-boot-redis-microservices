package com.justinrmiller.redismicroservices.controllers;

import com.google.common.base.Optional;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import com.justinrmiller.redismicroservices.pojo.Feature;
import com.justinrmiller.redismicroservices.service.FeatureFlagsService;

/**
 * @author Justin Miller (Copyright 2015)
 */
@RestController
public class FeatureFlagsController {
    private static Logger logger = LoggerFactory.getLogger(FeatureFlagsController.class);

    @Autowired
    private FeatureFlagsService featureFlagsService;

    @RequestMapping(
            value = "/flags/application/{application}/feature/{feature}",
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity get(
            @PathVariable String application,
            @PathVariable String feature) throws IOException {
        logger.info("Getting a feature flag");

        Optional<Feature> featureOptional = featureFlagsService.get(application, feature);

        if (featureOptional.isPresent()) {
            return new ResponseEntity<>(featureOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/flags/application/{application}",
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity get(
            @PathVariable String application) throws IOException {
        logger.info("Getting all feature flags");

        Optional<ImmutableList<Feature>> featureFlagOptional = featureFlagsService.getAll(application);

        if (featureFlagOptional.isPresent()) {
            return new ResponseEntity<>(featureFlagOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(
            value = "/flags/application/{application}/feature/{feature}",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public ResponseEntity post(
            @PathVariable String application,
            @PathVariable String feature,
            @RequestBody Feature featureData) throws IOException {
        logger.info("Feature {}", featureData);

        if (!feature.equals(featureData.getName())) {
            // error out
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        featureFlagsService.add(application, featureData);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}

