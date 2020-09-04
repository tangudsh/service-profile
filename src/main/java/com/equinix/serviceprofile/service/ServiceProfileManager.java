package com.equinix.serviceprofile.service;


import com.equinix.serviceprofile.entity.ServiceProfile;

public interface ServiceProfileManager {

    ServiceProfile getService(Long id);

    ServiceProfile addService(ServiceProfile serviceProfile);

    ServiceProfile updateService(Long id, ServiceProfile serviceProfile);

    void deleteService(Long id);

    void deleteServiceProfileMetro(Long id, Long metroId);

    void deleteServiceProfileSpeed(Long id, Long speedId);


}
