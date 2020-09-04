package com.equinix.serviceprofile.service;

import com.equinix.serviceprofile.exception.InvalidDataException;
import com.equinix.serviceprofile.exception.ProcessingException;
import com.equinix.serviceprofile.entity.Metro;
import com.equinix.serviceprofile.repository.MetroRepository;
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
public class MetroServiceImpl implements MetroService {

    @Autowired
    MetroRepository metroRepository;

    @Override
    public List<Metro> get() {
        try {
            return metroRepository.findAll();
        } catch (Exception e) {
            log.error("get| exception while getting metro", e);
            throw new ProcessingException(e);
        }
    }

    @Override
    public Metro getMetro(Long id) throws InvalidDataException {

        Metro metro;
        log.error("getService| fetching service profile with id {}", id);
        try {
            Optional<Metro> optionalService = metroRepository.findById(id);

            if (!optionalService.isPresent()) {
                log.warn("getService| metro not found with id{}", id);
                throw new InvalidDataException();
            }

            metro = optionalService.get();
        } catch (InvalidDataException e) {
            throw e;
        } catch (Exception e) {
            log.error("getService| exception while getting metro", e);
            throw new ProcessingException(e);
        }
        return metro;
    }

    @Override
    @Transactional
    public List<Metro> addMetro(Set<Metro> metros) {

        List<Metro> savedMetros;
        log.info("addMetro| save metro {}", metros);

        try {
            savedMetros = metroRepository.saveAll(metros.stream().filter(m -> Objects.isNull(metroRepository.findByMetro(m.getMetro()))).collect(Collectors.toSet()));

        } catch (Exception e) {
            log.error("addMetro| exception while persisting metro", e);
            throw new ProcessingException(e);
        }

        return savedMetros;
    }

    @Override
    @Transactional
    public void deleteMetro(Long id) throws InvalidDataException {
        try {
            metroRepository.deleteById(id);
        } catch (EmptyResultDataAccessException de) {
            log.warn("deleteMetro| metro not found with id{}", id);
            throw new InvalidDataException("invalid metro id");
        } catch (Exception e) {
            log.error("deleteMetro| exception while deleting metro", e);
            throw new ProcessingException(e);
        }
    }
}
