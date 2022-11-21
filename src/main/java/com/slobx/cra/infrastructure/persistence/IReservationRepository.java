package com.slobx.cra.infrastructure.persistence;

import com.slobx.cra.infrastructure.persistence.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUserUserName (String userName);
}
