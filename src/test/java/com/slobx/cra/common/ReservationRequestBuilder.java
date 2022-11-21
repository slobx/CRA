package com.slobx.cra.common;

import com.slobx.cra.application.dto.CarDTO;
import com.slobx.cra.application.dto.ReservationRequestDTO;
import com.slobx.cra.application.dto.ReservationResponseDTO;
import com.slobx.cra.application.dto.UserDTO;
import com.slobx.cra.application.mappers.IReservationMapper;
import com.slobx.cra.infrastructure.persistence.entity.Car;
import com.slobx.cra.infrastructure.persistence.entity.Reservation;
import com.slobx.cra.infrastructure.persistence.entity.User;

import java.time.LocalDateTime;

import static com.slobx.cra.common.CarRequestBuilder.buildCarEntity;
import static com.slobx.cra.common.CarRequestBuilder.buildUpdateCarDTO;
import static com.slobx.cra.common.UserRequestBuilder.buildUserDTO;
import static com.slobx.cra.common.UserRequestBuilder.buildUserEntity;

public class ReservationRequestBuilder {

    private static final LocalDateTime CURRENT_TIME = LocalDateTime.now();

    public static ReservationResponseDTO buildReservationResponseDTO() {
        CarDTO car = buildUpdateCarDTO();
        UserDTO user = buildUserDTO();


        return ReservationResponseDTO.builder()
                .id(1L)
                .startOfReservation(CURRENT_TIME)
                .endOfReservation(CURRENT_TIME.plusHours(2))
                .car(car)
                .user(user)
                .build();
    }

    public static ReservationRequestDTO buildReservationRequestDTO() {
        return ReservationRequestDTO.builder()
                .startOfReservation(CURRENT_TIME)
                .endOfReservation(CURRENT_TIME.plusHours(2))
                .carId(1L)
                .userName("test_user_name")
                .build();
    }

    public static Reservation buildReservationEntity() {
        Car car = buildCarEntity();
        User user = buildUserEntity();

        return Reservation.builder()
                .id(1L)
                .startOfReservation(CURRENT_TIME)
                .endOfReservation(CURRENT_TIME.plusHours(2))
                .car(car)
                .user(user)
                .build();
    }

    public static ReservationResponseDTO getMockMappedReservationResponseDTO(Reservation reservation) {
        return IReservationMapper.INSTANCE.entityToDTO(reservation);
    }
}
