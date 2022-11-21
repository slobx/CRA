package com.slobx.cra.infrastructure.persistence;

import com.slobx.cra.infrastructure.persistence.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends JpaRepository<Car, Long> {

}
