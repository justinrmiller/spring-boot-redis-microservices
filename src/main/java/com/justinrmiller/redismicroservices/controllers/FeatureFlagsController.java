package com.justinrmiller.redismicroservices.controllers;

import com.justinrmiller.redismicroservices.pojo.Feature;
import com.justinrmiller.redismicroservices.service.FeatureFlagsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    public Feature get(
            @PathVariable String application,
            @PathVariable String feature) throws IOException {
        logger.info("Getting a feature flag");
        return featureFlagsService.get(application, feature);
    }

    @RequestMapping(
            value = "/flags/application/{application}",
            method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<Feature> get(
            @PathVariable String application) throws IOException {
        logger.info("Getting all feature flags");
        return featureFlagsService.getAll(application);
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

