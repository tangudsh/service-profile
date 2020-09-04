package com.equinix.serviceprofile.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "service_profile")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceProfile {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "service_profile_speeds", joinColumns = @JoinColumn(name = "service_profile_id"), inverseJoinColumns = @JoinColumn(name = "speed_id"))
    private Set<Speed> serviceProfileSpeeds = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "service_profile_metros", joinColumns = @JoinColumn(name = "service_profile_id"), inverseJoinColumns = @JoinColumn(name = "metro_id"))
    private Set<Metro> serviceProfileMetros = new HashSet<>();

}
