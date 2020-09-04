package com.equinix.serviceprofile.repository;

import com.equinix.serviceprofile.entity.ServiceProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceProfileRepository extends JpaRepository<ServiceProfile, Long> {

    Optional<ServiceProfile> findById(Long id);
}
