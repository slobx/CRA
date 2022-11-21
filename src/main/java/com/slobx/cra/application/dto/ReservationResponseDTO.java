package com.slobx.cra.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private LocalDateTime startOfReservation;
    private LocalDateTime endOfReservation;
    private CarDTO car;
    private UserDTO user;
}
