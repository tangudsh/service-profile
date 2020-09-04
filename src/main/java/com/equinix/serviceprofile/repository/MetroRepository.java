package com.equinix.serviceprofile.repository;

import com.equinix.serviceprofile.entity.Metro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetroRepository extends JpaRepository<Metro, Long> {

    Optional<Metro> findById(Long id);

    Metro findByMetro(String metro);
}
