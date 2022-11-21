package com.slobx.cra.infrastructure.persistence.entity;

import com.slobx.cra.infrastructure.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESERVATION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@SequenceGenerator(name = Reservation.SEQUENCE_NAME, sequenceName = Reservation.SEQUENCE_NAME, allocationSize = 1)
public class Reservation extends BaseEntity {

    public static final String SEQUENCE_NAME = "S_RESERVATION";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private Long id;

    @Column(name = "START_OF_RESERVATION", nullable = false)
    private LocalDateTime startOfReservation;

    @Column(name = "END_OF_RESERVATION")
    private LocalDateTime endOfReservation;

    @ManyToOne
    @JoinTable(name = "RESERVATION_CAR", joinColumns = @JoinColumn(name = "RESERVATION_ID"), inverseJoinColumns = @JoinColumn(name = "CAR_ID"))
    private Car car;

    @ManyToOne
    @JoinTable(name = "RESERVATION_USERS", joinColumns = @JoinColumn(name = "RESERVATION_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private User user;
}
