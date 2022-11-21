package com.slobx.cra.application.intefaces;

import com.slobx.cra.application.dto.ReservationRequestDTO;
import com.slobx.cra.application.dto.ReservationResponseDTO;

import java.util.List;

public interface IReservationService {

    ReservationResponseDTO reserve(ReservationRequestDTO request);
    List<ReservationResponseDTO> getAllReservationForUser(String userName);
}
