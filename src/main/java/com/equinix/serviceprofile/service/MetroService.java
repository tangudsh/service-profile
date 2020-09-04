package com.equinix.serviceprofile.service;

import com.equinix.serviceprofile.exception.InvalidDataException;
import com.equinix.serviceprofile.entity.Metro;

import java.util.List;
import java.util.Set;

public interface MetroService {

    List<Metro> get();

    Metro getMetro(Long id) throws InvalidDataException;

    List<Metro> addMetro(Set<Metro> metro);

    void deleteMetro(Long id) throws InvalidDataException;
}
