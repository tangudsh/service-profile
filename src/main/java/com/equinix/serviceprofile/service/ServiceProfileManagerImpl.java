package com.equinix.serviceprofile.service;

import com.equinix.serviceprofile.exception.InvalidDataException;
import com.equinix.serviceprofile.exception.ProcessingException;
import com.equinix.serviceprofile.entity.Metro;
import com.equinix.serviceprofile.entity.ServiceProfile;
import com.equinix.serviceprofile.entity.Speed;
import com.equinix.serviceprofile.repository.ServiceProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class ServiceProfileManagerImpl implements ServiceProfileManager {

    @Autowired
    ServiceProfileRepository serviceProfileRepository;

    @Autowired
    MetroService metroService;

    @Autowired
    SpeedService speedService;

    @Override
    public ServiceProfile getService(Long id) {

        ServiceProfile serviceProfile;
        log.error("getService| fetching service profile with id {}", id);
        try {
            Optional<ServiceProfile> optionalService = serviceProfileRepository.findById(id);

            if (!optionalService.isPresent()) {
                log.warn("updateService| service profile not found with id{}", id);
                throw new InvalidDataException();
            }

            serviceProfile = optionalService.orElse(null);
        } catch (InvalidDataException e) {
            throw e;
        } catch (Exception e) {
            log.error("getService| exception while getting service profile", e);
            throw e;
        }
        return serviceProfile;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ServiceProfile addService(ServiceProfile serviceProfile) {

        try {
            return serviceProfileRepository.saveAndFlush(serviceProfile);
        } catch (Exception e) {
            log.error("addService| exception while adding service profile", e);
            throw new ProcessingException(e);
        }
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public ServiceProfile updateService(Long id, ServiceProfile serviceProfile) {

        try {
            Optional<ServiceProfile> optionalServiceProfile = serviceProfileRepository.findById(id);

            if (!optionalServiceProfile.isPresent()) {
                log.warn("updateService| service profile not found with id{}", id);
                throw new InvalidDataException();
            }

            ServiceProfile existingProfile = optionalServiceProfile.get();

            if (!CollectionUtils.isEmpty(serviceProfile.getServiceProfileSpeeds())) {
                existingProfile.getServiceProfileSpeeds().addAll(serviceProfile.getServiceProfileSpeeds());
            }

            if (!CollectionUtils.isEmpty(serviceProfile.getServiceProfileMetros())) {
                existingProfile.getServiceProfileMetros().addAll(serviceProfile.getServiceProfileMetros());
            }

            return serviceProfileRepository.saveAndFlush(existingProfile);

        } catch (Exception e) {
            log.error("addService| exception while adding service profile", e);
            throw new ProcessingException(e);
        }
    }

    @Override
    public void deleteService(Long id) {

        try {
            serviceProfileRepository.deleteById(id);
        } catch (EmptyResultDataAccessException de) {
            log.warn("deleteService| service profile not found with id{}", id);
            throw new InvalidDataException();
        } catch (Exception e) {
            log.error("deleteService| exception while deleting service profile");
            throw new ProcessingException(e);
        }
    }


    @Override
    public void deleteServiceProfileMetro(Long id, Long metroId) {
        try {
            Optional<ServiceProfile> profile = serviceProfileRepository.findById(id);

            if (!profile.isPresent()) {
                throw new InvalidDataException("invalid service profile id");
            }

            Metro metro = metroService.getMetro(metroId);

            if (!profile.get().getServiceProfileMetros().remove(metro)) {
                throw new InvalidDataException("metro" + metro.getMetro() + "does not exist for service profile");
            }

            serviceProfileRepository.saveAndFlush(profile.get());
        } catch (InvalidDataException e) {
            throw e;
        } catch (Exception e) {
            log.error("deleteServiceProfileSpeed| error while deleting metro from service profile metro id{} id {}", metroId, id);
            throw new ProcessingException(e);
        }
    }

    @Override
    public void deleteServiceProfileSpeed(Long id, Long speedId) {
        try {
            Optional<ServiceProfile> profile = serviceProfileRepository.findById(id);

            if (!profile.isPresent()) {
                throw new InvalidDataException("invalid service profile id");
            }

            Speed speed = speedService.getSpeed(speedId);

            if (!profile.get().getServiceProfileSpeeds().remove(speed)) {
                throw new InvalidDataException("speed" + speed.getSpeed() + " " + speed.getUom() + "does not exist for service profile");
            }

            serviceProfileRepository.saveAndFlush(profile.get());

        } catch (InvalidDataException e) {
            throw e;
        } catch (Exception e) {
            log.error("deleteServiceProfileSpeed| error while deleting metro from service profile speed id{} id {}", speedId, id);
            throw new ProcessingException(e);
        }
    }
}
