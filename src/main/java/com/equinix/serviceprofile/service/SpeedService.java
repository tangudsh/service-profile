package com.equinix.serviceprofile.service;

import com.equinix.serviceprofile.entity.Speed;

import java.util.List;
import java.util.Set;

public interface SpeedService {

    List<Speed> getAll();

    Speed getSpeed(Long id);

    List<Speed> addSpeed(Set<Speed> metro);

    void deleteSpeed(Long id);
}
