package com.equinix.serviceprofile.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "speed")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Speed {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "uom")
    @Enumerated(EnumType.STRING)
    private SpeedUOM uom;

}
