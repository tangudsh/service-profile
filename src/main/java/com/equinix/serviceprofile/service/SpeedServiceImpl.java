package com.equinix.serviceprofile.service;

import com.equinix.serviceprofile.exception.InvalidDataException;
import com.equinix.serviceprofile.exception.ProcessingException;
import com.equinix.serviceprofile.entity.Speed;
import com.equinix.serviceprofile.repository.SpeedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SpeedServiceImpl implements SpeedService {

    @Autowired
    SpeedRepository speedRepository;

    @Override
    public List<Speed> getAll() {
        try {
            return speedRepository.findAll();
        } catch (Exception e) {
            throw new ProcessingException(e);
        }
    }

    @Override
    public Speed getSpeed(Long id) {
        Speed speed;
        log.error("getService| fetching service profile with id {}", id);
        try {
            Optional<Speed> optionalService = speedRepository.findById(id);

            if (!optionalService.isPresent()) {
                log.warn("getSpeed| speed not found with id{}", id);
                throw new InvalidDataException();
            }

            speed = optionalService.get();
        } catch (InvalidDataException e) {
            throw e;
        } catch (Exception e) {
            log.error("getSpeed| exception while getting speed", e);
            throw new ProcessingException(e);
        }
        return speed;
    }

    @Transactional
    @Override
    public List<Speed> addSpeed(Set<Speed> speeds) {
        List<Speed> savedMetros;

        log.info("addSpeed| save speeds {}", speeds);

        try {
            savedMetros = speedRepository.saveAll(speeds.stream().filter(s -> Objects.isNull(speedRepository.findBySpeedAndUom(s.getSpeed(), s.getUom()))).collect(Collectors.toSet()));

        } catch (Exception e) {
            log.error("addSpeed| exception while persisting speed", e);
            throw new ProcessingException(e);
        }

        return savedMetros;
    }

    @Override
    @Transactional
    public void deleteSpeed(Long id) {

        try {
            speedRepository.deleteById(id);
        } catch (EmptyResultDataAccessException de) {
            log.warn("deleteSpeed| speed not found with id{}", id);
            throw new InvalidDataException("invalid speed with id");
        } catch (Exception e) {
            log.error("deleteSpeed| exception while deleting speed", e);
            throw new ProcessingException(e);
        }
    }
}
