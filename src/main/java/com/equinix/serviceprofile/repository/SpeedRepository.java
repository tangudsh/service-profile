package com.equinix.serviceprofile.repository;

import com.equinix.serviceprofile.entity.Speed;
import com.equinix.serviceprofile.entity.SpeedUOM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeedRepository extends JpaRepository<Speed, Long> {

    Speed findBySpeedAndUom(Integer speed, SpeedUOM uom);

}
