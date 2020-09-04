package com.equinix.serviceprofile.controller;

import com.equinix.serviceprofile.entity.Metro;
import com.equinix.serviceprofile.service.MetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class MetroController {

    @Autowired
    private MetroService metroService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/metro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Metro>> getAll() {
        return new ResponseEntity<>(metroService.get(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/metro/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Metro> getMetro(@PathVariable("id") @NonNull Long id) {
        return new ResponseEntity<>(metroService.getMetro(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/metro", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Metro>> addMetro(@RequestBody Set<Metro> metros) {
        return new ResponseEntity<>(metroService.addMetro(metros), HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/metro/{id}")
    public void deleteMetro(@PathVariable("id") @NonNull Long id) {
        metroService.deleteMetro(id);
    }


}
