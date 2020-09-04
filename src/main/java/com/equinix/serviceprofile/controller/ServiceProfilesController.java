package com.equinix.serviceprofile.controller;

import com.equinix.serviceprofile.entity.ServiceProfile;
import com.equinix.serviceprofile.service.ServiceProfileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


@RestController
public class ServiceProfilesController {

    @Autowired
    ServiceProfileManager manager;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/serviceprofile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceProfile> getServiceProfile(@PathVariable("id") @NonNull Long id) {
        return new ResponseEntity<>(manager.getService(id), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/serviceprofile/{id}")
    public void removeServiceProfile(@PathVariable @NonNull Long id) {
        manager.deleteService(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/serviceprofile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceProfile> createServiceProfile(@RequestBody ServiceProfile serviceProfile) {
        return new ResponseEntity<>(manager.addService(serviceProfile), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/serviceprofile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceProfile> modifyServiceProfile(@PathVariable("id") @NonNull Long id, @RequestBody ServiceProfile serviceProfile) {
        return new ResponseEntity<>(manager.updateService(id, serviceProfile), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/serviceprofile/{id}/speed/{speedId}")
    public void removeSpeed(@PathVariable @NonNull Long id, @PathVariable @NonNull Long speedId) {
        manager.deleteServiceProfileSpeed(id, speedId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/serviceprofile/{id}/metro/{metroId}")
    public void removeMetro(@PathVariable @NonNull Long id, @PathVariable @NonNull Long metroId) {
        manager.deleteServiceProfileMetro(id, metroId);
    }

}
