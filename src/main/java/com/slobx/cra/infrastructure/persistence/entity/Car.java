package com.slobx.cra.infrastructure.persistence.entity;

import com.slobx.cra.infrastructure.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CAR")
@Data
@Builder
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@SequenceGenerator(name = Car.SEQUENCE_NAME, sequenceName = Car.SEQUENCE_NAME, allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseEntity {

    public static final String SEQUENCE_NAME = "S_CAR";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private Long id;

    @Column(name = "CAR_MAKE", nullable = false)
    private String make;

    @Column(name = "CAR_MODEL", nullable = false)
    private String model;

    @Column(name = "CAR_UUID", nullable = false)
    private String uuid;

    @Column(name = "IS_AVAILABLE")
    private Boolean isAvailable = true;
}
