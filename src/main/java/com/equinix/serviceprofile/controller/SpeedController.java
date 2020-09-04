package com.equinix.serviceprofile.controller;

import com.equinix.serviceprofile.entity.Metro;
import com.equinix.serviceprofile.entity.Speed;
import com.equinix.serviceprofile.service.SpeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class SpeedController {

    @Autowired
    private SpeedService speedService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/speed", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Speed>> getAllSpeed() {
        return new ResponseEntity<>(speedService.getAll(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/speed/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Speed> getSpeed(@PathVariable("id") @NonNull Long id) {
        return new ResponseEntity<>(speedService.getSpeed(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/speed", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Speed>> addMetro(@RequestBody Set<Speed> speeds) {
        return new ResponseEntity<>(speedService.addSpeed(speeds), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/speed/{id}")
    public void deleteSpeed(@PathVariable("id") @NonNull Long id) {
        speedService.deleteSpeed(id);
    }
}
