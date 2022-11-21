package com.slobx.cra.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ReservationRequestDTO {

    private Long carId;
    private String userName;
    private LocalDateTime startOfReservation;
    private LocalDateTime endOfReservation;
}
